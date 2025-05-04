package com.example.expenseTracker.application.ports.transaction;


import com.example.expenseTracker.domain.entity.transaction.onetime.OneTimeTransaction;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

public interface TransactionRepository {
    OneTimeTransaction save(OneTimeTransaction transaction);
    Optional<OneTimeTransaction> findById(Long id);
    List<OneTimeTransaction> findByAccountAndRange(Long accountId, Instant startTime, Instant endTime);
}
