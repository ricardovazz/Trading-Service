package com.example.accountingservice;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
@AllArgsConstructor
public class AccountingService {
    private final Map<Long, TradeConfirmation> confirmations = new ConcurrentHashMap<>();

    private final WebClient webClient;

    public Mono<TradeConfirmation> confirmTrade(Long tradeId, TradeConfirmation tradeConfirmation) {

        tradeConfirmation.setTradeId(tradeId);
        tradeConfirmation.setStatus("CONFIRMED");
        tradeConfirmation.setConfirmationTimestamp(LocalDateTime.now());
        confirmations.put(tradeId, tradeConfirmation);

        return webClient.post()
                .uri("http://settlement/settlements")
                .bodyValue(new Settlement(null, tradeId, tradeConfirmation.getConfirmedQuantity(), tradeConfirmation.getPrice(),tradeConfirmation.getConfirmationTimestamp()))
                .retrieve()
                .bodyToMono(Settlement.class)
                .map(settlement -> new TradeConfirmation(settlement.getTradeId(), tradeConfirmation.getStatus(), settlement.getAmount(), settlement.getPrice(), tradeConfirmation.getConfirmationTimestamp()));
    }

    public Flux<TradeConfirmation> getAllConfirmations() {
        return Flux.fromIterable(confirmations.values());
    }
}
