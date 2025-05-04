package com.example.expenseTracker.application.ports.transaction.periodic;

import com.example.expenseTracker.domain.entity.transaction.periodic.PeriodicTransaction;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

public interface PeriodicTransactionRepository {
//    void createMissingTransactionsForUser(Long userId, java.time.Instant since);
    PeriodicTransaction save(PeriodicTransaction periodicTransaction);
    List<PeriodicTransaction> duePeriodic(Long accountId, Instant now);
    void saveAll(List<PeriodicTransaction> periodicTransactions);
}

