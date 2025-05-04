package com.example.expenseTracker.application.usecase.transaction;

import com.example.expenseTracker.domain.entity.transaction.Transaction;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;
import java.util.List;

public interface TransactionUseCase {
    Long addOneTimeTx(Long accountId,
                      BigDecimal amount,
                      String description,
                      Instant when,
                      Transaction.Txtype txtype,
                      String category);

    Long schedulePeriodicTx(Long accountId,
                            BigDecimal amount,
                            String description,
                            String category,
                            Duration every,
                            Transaction.Txtype type);

    List<TransactionView> list(Long accountId, Instant from, Instant to);

    /** Called on login or nightly cron. */
    void materialiseDue(Long accountId);

    /* DTO (class, not record) */
    final class TransactionView {
        public Long id;
        public BigDecimal amount;
        public String description;
        public Instant timestamp;
        public Transaction.Txtype txtype;
    }
}