package com.example.expenseTracker.adaptors.persistence.transaction;

import com.example.expenseTracker.domain.entity.transaction.Transaction;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "transactions")
@Getter
@Setter
public class TransactionJPAEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Long accountId;
    BigDecimal amount;
    String description;
    //
    Instant timestamp;
    String transactionCategory;

    @Enumerated(EnumType.STRING)
    Transaction.Txtype txtype;
}
