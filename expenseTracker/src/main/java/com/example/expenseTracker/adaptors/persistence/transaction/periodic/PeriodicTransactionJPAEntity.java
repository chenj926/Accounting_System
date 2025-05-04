package com.example.expenseTracker.adaptors.persistence.transaction.periodic;

import com.example.expenseTracker.domain.entity.transaction.Transaction;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;

@Entity
@Table(name = "periodic_tx")
public class PeriodicTransactionJPAEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
    Long accountId;
    BigDecimal amount;
    String description;
    String transactionCategory;
    Instant createdAt;
    @Enumerated(EnumType.STRING)
    Transaction.Txtype txtype;
    Duration interval;
    Instant lastExecutedAt;
}
