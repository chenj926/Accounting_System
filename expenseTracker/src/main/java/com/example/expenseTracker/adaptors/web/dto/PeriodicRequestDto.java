package com.example.expenseTracker.adaptors.web.dto;

import com.example.expenseTracker.domain.entity.transaction.Transaction;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;

@Getter
@Setter
public class PeriodicRequestDto {
    @NotNull
    Long accountId;
    @NotNull
    BigDecimal amount;
    @NotBlank
    String description;
    @NotBlank
    String category;
    @NotNull
    Duration every;
    @NotNull
    Transaction.Txtype type;
}
