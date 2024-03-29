package com.example.settlementservice;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class Settlement {

    private Long id;

    @NotNull(message = "Trade ID is required.")
    private Long tradeId;

    @NotNull(message = "Settlement amount is required.")
    private BigDecimal amount;

    @NotNull
    private LocalDateTime settlementDate;
}
