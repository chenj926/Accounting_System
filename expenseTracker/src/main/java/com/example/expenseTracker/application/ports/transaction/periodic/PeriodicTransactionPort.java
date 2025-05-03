package com.example.expenseTracker.application.ports.transaction.periodic;

public interface PeriodicTransactionPort {
    void createMissingTransactionsForUser(Long userId, java.time.Instant since);
}
