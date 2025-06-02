package com.example.expenseTracker.adaptors.persistence.transaction.periodic;

import com.example.expenseTracker.domain.entity.transaction.Transaction;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;

@Entity
@Table(name = "periodic_tx")
@Data  // ← Lombok: generates getters/setters, toString, etc.
public class PeriodicTransactionJPAEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    Long accountId;

    BigDecimal amount;

    String description;

    String transactionCategory;

    @Column(name="create_at")
    Instant createdAt;

    @Enumerated(EnumType.STRING)
    Transaction.Txtype txtype;

    @Column(name="run_interval")
    Duration interval;

    @Column(name="last_executed_at")
    Instant lastExecutedAt;
}
