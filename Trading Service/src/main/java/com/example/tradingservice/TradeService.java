package com.example.tradingservice;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class TradeService {
    private final Map<Long, Trade> trades = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong();

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

    public Mono<Trade> createTrade(Trade trade) {
        long id = idGenerator.incrementAndGet();
        trade.setId(id);
        trades.put(id, trade);
        return Mono.just(trade);
    }

}
