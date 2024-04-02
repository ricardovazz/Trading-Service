package com.example.tradingservice;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
@AllArgsConstructor
public class TradeService {
    private final Map<Long, Trade> trades = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong();
    private final WebClient webClient;

    public Flux<Trade> getAllTrades() {
        return Flux.fromIterable(trades.values());
    }

    public Mono<Trade> getTradeById(Long id) {
        Trade trade = trades.get(id);
        if (trade == null) {
            throw new TradeNotFoundException("Trade not found with id: " + id);
        }
        return Mono.just(trade);
    }

    public Mono<TradeConfirmation> createTrade(Trade trade) {
        long id = idGenerator.incrementAndGet();
        trade.setId(id);
        trades.put(id, trade);

        return webClient.post()
                .uri("http://accounting-service/confirmations")
                .bodyValue(new TradeConfirmation(trade.getId(), "", trade.getQuantity(), trade.getPrice(), trade.getTimestamp()))
                .retrieve()
                .bodyToMono(TradeConfirmation.class);
    }
}
