package com.example.accountingservice;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class AccountingService {
    private final Map<Long, TradeConfirmation> confirmations = new ConcurrentHashMap<>();

    public Mono<TradeConfirmation> confirmTrade(Long tradeId, TradeConfirmation tradeConfirmation) {

        tradeConfirmation.setTradeId(tradeId);
        tradeConfirmation.setStatus("CONFIRMED");
        tradeConfirmation.setConfirmationTimestamp(LocalDateTime.now());
        confirmations.put(tradeId, tradeConfirmation);
        return Mono.just(tradeConfirmation);
    }

    public Flux<TradeConfirmation> getAllConfirmations() {
        return Flux.fromIterable(confirmations.values());
    }
}
