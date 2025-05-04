package com.example.expenseTracker.adaptors.persistence.transaction.periodic;

import com.example.expenseTracker.application.ports.transaction.periodic.PeriodicTransactionPort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Repository
public class PeriodicTransactionJPARepository implements PeriodicTransactionPort {
    private final SpringDataPeriodicTransactionTemplateRepo templateRepo;
    private final SpringDataTransactionRepo transactionRepo;

    public PeriodicTransactionJPARepository(
            SpringDataPeriodicTransactionTemplateRepo templateRepo,
            SpringDataTransactionRepo transactionRepo
    ) {
        this.templateRepo = templateRepo;
        this.transactionRepo = transactionRepo;
    }

    /**
     * For each periodic‐template belonging to userId, generates and saves
     * all transactions that should have occurred between lastRun and now.
     */
    @Override
    @Transactional
    public void createMissingTransactionsForUser(Long userId, Instant since) {
        List<PeriodicTransactionTemplateEntity> templates =
                templateRepo.findByUserId(userId);

        Instant now = Instant.now();
        for (PeriodicTransactionTemplateEntity template : templates) {
            // start scheduling from the later of (lastRun or since)
            Instant nextRun = template.getLastRun().isAfter(since)
                    ? template.getLastRun().plus(template.getInterval())
                    : since;

            while (!nextRun.isAfter(now)) {
                TransactionJPAEntity tx = new TransactionJPAEntity();
                tx.setUserId(userId);
                tx.setAmount(template.getAmount());
                tx.setDescription(template.getDescription());
                tx.setTimestamp(nextRun);

                transactionRepo.save(tx);
                nextRun = nextRun.plus(template.getInterval());
            }

            // update the template’s lastRun so we don’t double-create
            template.setLastRun(now);
            templateRepo.save(template);
        }
    }

}
