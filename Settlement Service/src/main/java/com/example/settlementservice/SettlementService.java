package com.example.settlementservice;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class SettlementService {
    private final SettlementRepository settlementRepository;

    public Mono<Settlement> settleTrade(Settlement settlement) {
        return settlementRepository.save(settlement);
    }

    public Flux<Settlement> getAllSettlements() {
        return settlementRepository.findAll();
    }

    public Mono<Settlement> getSettlementById(Long id) {
        return settlementRepository.findById(id);
    }
}