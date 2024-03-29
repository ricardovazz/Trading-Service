package com.example.tradingservice;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class Trade {
    private Long id;
    @NotEmpty(message = "Symbol is required.")
    private String symbol;
    @NotNull(message = "Quantity is required.")
    @Positive(message = "Quantity must be positive.")
    private BigDecimal quantity;
    @NotNull(message = "Price is required.")
    @Positive(message = "Price must be positive.")
    private BigDecimal price;
    private LocalDateTime timestamp;
}
