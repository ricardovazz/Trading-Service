package com.example.accountingservice;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/accounting")
@Validated
public class AccountingController {
    private final AccountingService accountingService;

    public AccountingController(AccountingService accountingService) {
        this.accountingService = accountingService;
    }

    @PostMapping("/confirmations/{tradeId}")
    public Mono<TradeConfirmation> confirmTrade(@PathVariable Long tradeId, @RequestBody TradeConfirmation tradeConfirmation) {
        return accountingService.confirmTrade(tradeId, tradeConfirmation);
    }

    @GetMapping("/confirmations")
    public Flux<TradeConfirmation> getAllConfirmations() {
        return accountingService.getAllConfirmations();
    }

}
