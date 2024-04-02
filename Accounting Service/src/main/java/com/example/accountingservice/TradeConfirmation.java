package com.example.accountingservice;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class TradeConfirmation {

    private Long tradeId;
    private String status;
    @NotNull(message = "Quantity is required.")
    @Positive(message = "Quantity must be positive.")
    private BigDecimal confirmedQuantity;
    @NotNull(message = "Price is required.")
    @Positive(message = "Price must be positive.")
    private BigDecimal price;
    @NotNull(message = "Confirmation timestamp is required.")
    private LocalDateTime confirmationTimestamp;
}
