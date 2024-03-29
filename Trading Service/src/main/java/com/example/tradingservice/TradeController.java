package com.example.tradingservice;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/trades")
public class TradeController {
    private final TradeService tradeService;

    public TradeController(TradeService tradeService) {
        this.tradeService = tradeService;
    }

    @GetMapping
    public Flux<Trade> getAllTrades() {
        return tradeService.getAllTrades();
    }

    @GetMapping("/{id}")
    public Mono<Trade> getTradeById(@PathVariable @Valid Long id) {
        return tradeService.getTradeById(id);
    }

    @PostMapping
    public Mono<Trade> createTrade(@RequestBody @Valid Trade trade) {
        return tradeService.createTrade(trade);
    }
}
