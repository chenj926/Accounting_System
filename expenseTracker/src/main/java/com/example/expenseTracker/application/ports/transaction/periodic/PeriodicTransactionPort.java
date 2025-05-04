package com.example.expenseTracker.application.ports.transaction.periodic;

import org.springframework.stereotype.Repository;

@Repository
public interface PeriodicTransactionPort {
    void createMissingTransactionsForUser(Long userId, java.time.Instant since);
}
