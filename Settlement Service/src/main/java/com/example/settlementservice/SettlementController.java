package com.example.settlementservice;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/settlements")
public class SettlementController {
    private final SettlementService settlementService;

    public SettlementController(SettlementService settlementService) {
        this.settlementService = settlementService;
    }

    @PostMapping
    public Mono<Settlement> createSettlement(@RequestBody @Valid Settlement settlement) {
        return settlementService.settleTrade(settlement);
    }

    @GetMapping
    public Flux<Settlement> getAllSettlements() {
        return settlementService.getAllSettlements();
    }

    @GetMapping("/{id}")
    public Mono<Settlement> getSettlementById(@PathVariable Long id) {
        return settlementService.getSettlementById(id);
    }
}
