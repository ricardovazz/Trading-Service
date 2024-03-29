package com.example.accountingservice;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class TradeConfirmation {

    private Long tradeId;
    @NotBlank(message = "Status is required.")
    private String status;
    @NotNull(message = "Quantity is required.")
    @Positive(message = "Quantity must be positive.")
    private BigDecimal confirmedQuantity;
    @NotNull(message = "Confirmation timestamp is required.")
    private LocalDateTime confirmationTimestamp;
}
