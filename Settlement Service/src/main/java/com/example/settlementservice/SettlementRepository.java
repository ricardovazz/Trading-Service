package com.example.settlementservice;

import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class SettlementRepository {
    private final AtomicLong counter = new AtomicLong();
    private final Map<Long, Settlement> settlements = new ConcurrentHashMap<>();

    public Mono<Settlement> save(Settlement settlement) {
        Long id = settlement.getId() == null ? counter.incrementAndGet() : settlement.getId();
        settlement.setId(id);
        settlements.put(id, settlement);
        return Mono.just(settlement);
    }

    public Flux<Settlement> findAll() {
        return Flux.fromIterable(settlements.values());
    }

    public Mono<Settlement> findById(Long id) {
        return Mono.justOrEmpty(settlements.get(id));
    }
}
