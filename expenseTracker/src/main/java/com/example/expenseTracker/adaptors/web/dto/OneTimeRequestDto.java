package com.example.expenseTracker.adaptors.web.dto;

import com.example.expenseTracker.domain.entity.transaction.Transaction;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
public class OneTimeRequestDto {
    @NotNull
    Long accountId;
    @NotNull
    BigDecimal amount;
    @NotBlank
    String description;
    @NotBlank
    String category;
    @NotNull
    Instant when;
    @NotNull
    Transaction.Txtype type;
}
