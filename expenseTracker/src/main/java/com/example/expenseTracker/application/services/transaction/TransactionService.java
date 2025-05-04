package com.example.expenseTracker.application.services.transaction;

import com.example.expenseTracker.application.ports.transaction.periodic.PeriodicTransactionRepository;
import com.example.expenseTracker.application.ports.transaction.TransactionRepository;
import com.example.expenseTracker.application.usecase.transaction.TransactionUseCase;
import com.example.expenseTracker.domain.entity.transaction.Transaction;
import com.example.expenseTracker.domain.entity.transaction.onetime.OneTimeTransaction;
import com.example.expenseTracker.domain.entity.transaction.periodic.PeriodicTransaction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class TransactionService implements TransactionUseCase {

    private final TransactionRepository transactionRepository;
    private final PeriodicTransactionRepository periodicTransactionRepository;

    /* ---------- one‑time ---------- */
    @Override
    public Long addOneTimeTx(Long accountId,
                           BigDecimal amount,
                           String description,
                           Instant when,
                           Transaction.Txtype type,
                           String category) {

        OneTimeTransaction saved = this.transactionRepository.save(new OneTimeTransaction(
                null, accountId, amount, description, when, type, category));
        return saved.getId();
    }

    /* ---------- periodic ---------- */
    @Override
    public Long schedulePeriodicTx(Long accountId,
                                  BigDecimal amount,
                                  String description,
                                  String category,
                                  Duration every,
                                  Transaction.Txtype type) {

        PeriodicTransaction saved = this.periodicTransactionRepository.save(
                new PeriodicTransaction(
                        null, accountId, amount, description,
                        Instant.now(), type, category, every, null));
        return saved.getId();
    }

    @Override
    public void materialiseDue(Long accountId) {
        Instant now = Instant.now();

        List<PeriodicTransaction> periodicTx =
                this.periodicTransactionRepository.duePeriodic(accountId, now);

        List<OneTimeTransaction> realised = new ArrayList<>();
        for (PeriodicTransaction pdTx : periodicTx) {
            if (pdTx.isExecutionDue(now)) {
                pdTx.markExecuted(now);
                OneTimeTransaction oneTimeTx = pdTx.createInstance(now);
                realised.add(oneTimeTx);
            }
        }

        realised.forEach(this.transactionRepository::save);
        this.periodicTransactionRepository.saveAll(periodicTx);           // persist lastExecutedAt updates
    }

    /* ---------- query ---------- */

    @Override
    public List<TransactionView> list(Long accountId, Instant from, Instant to) {
        return this.transactionRepository.findByAccountAndRange(accountId, from, to)
                .stream()
                .map(this::toView)
                .collect(Collectors.toList());
    }

    private TransactionView toView(OneTimeTransaction oneTimeTx) {
        TransactionView txView = new TransactionView();
        txView.id         = oneTimeTx.getId();
        txView.amount     = oneTimeTx.getAmount();
        txView.description= oneTimeTx.getDescription();
        txView.timestamp  = oneTimeTx.getCreateTime();
        txView.txtype     = oneTimeTx.getTxtype();
        return txView;
    }

}
