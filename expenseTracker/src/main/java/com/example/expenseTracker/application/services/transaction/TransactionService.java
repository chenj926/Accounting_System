package com.example.expenseTracker.application.services.transaction;

import com.example.expenseTracker.application.ports.transaction.periodic.PeriodicTransactionRepository;
import com.example.expenseTracker.application.ports.transaction.TransactionRepository;
import com.example.expenseTracker.application.ports.user_acc.UserAccountRepository;
import com.example.expenseTracker.application.use_case_ports.transaction.TransactionUseCase;
import com.example.expenseTracker.domain.entity.account.user_acc.UserAccount;
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
    private final UserAccountRepository userAccountRepository;

    /* ---------- one‑time ---------- */
    @Override
    // @Transactional // Already on class level, but can be specified for clarity or different propagation
    public Long addOneTimeTx(Long accountId,
                           BigDecimal amount,
                           String description,
                           Instant when,
                           Transaction.Txtype type,
                           String category) {

        OneTimeTransaction oneTimeTx = this.transactionRepository.save(new OneTimeTransaction(
                null, accountId, amount, description, when, type, category));

        // Fetch UserAccount, update its balance, and save it
        UserAccount userAccount = userAccountRepository.findById(accountId)
                .orElseThrow(() -> new IllegalArgumentException("Account not found for ID: " + accountId));

        userAccount.addTransaction(oneTimeTx); // Updates balances in the domain object
        userAccountRepository.saveUser(userAccount); // Persist updated account (with new balances)

        // Save the transaction itself
        OneTimeTransaction savedTx = this.transactionRepository.save(oneTimeTx);
        return savedTx.getId();
    }

    /* ---------- periodic ---------- */
    @Override
    public Long schedulePeriodicTx(Long accountId,
                                   BigDecimal amount,
                                   String description,
                                   String category,
                                   Duration every,
                                   Transaction.Txtype type,
                                   Instant firstTxTime) {

        // Validate accountId exists (optional here, but good practice)
        userAccountRepository.findById(accountId)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Account not found for ID: "
                        + accountId + " while scheduling periodic tx."
                ));

        Instant ruleCreateTime = Instant.now(); // When the rule itself is defined
        Instant initialLastExecutedAt;

        if (firstTxTime != null) {
            // Set lastExecutedAt so that lastExecutedAt.plus(interval) == firstTxTime
            initialLastExecutedAt = firstTxTime.minus(every);
        } else {
            // Default behavior if no specific firstTxTime is provided by the user.
            // Option 1: Start processing based on the next interval from now
            // (first instance will be 'every' duration from now).
            initialLastExecutedAt = Instant.now();
        }

        PeriodicTransaction savedRule = this.periodicTransactionRepository.save(
                new PeriodicTransaction(
                        null,
                        accountId,
                        amount,
                        description,
                        ruleCreateTime,     // Rule's own creation timestamp
                        type,
                        category,
                        every,              // The interval
                        initialLastExecutedAt // Calculated initial lastExecutedAt
                )
        );
        return savedRule.getId();
    }

//    @Override
//    // @Transactional // Already on class level
//    public void materialiseDue(Long accountId) {
//        System.out.println("materialiseDue START for accountId: " + accountId);
//        Instant now = Instant.now();
//        System.out.println("Current time (now): " + now);
//
//        List<PeriodicTransaction> periodicRules =
//                this.periodicTransactionRepository.duePeriodic(accountId, now);
//
//        if (periodicRules.isEmpty()) {
//            return;
//        }
//
//        // Fetch UserAccount once for all transactions being materialized for this account
//        UserAccount userAccount = userAccountRepository.findById(accountId)
//                .orElseThrow(() -> new IllegalArgumentException("Account not found for ID: " + accountId));
//
//        List<OneTimeTransaction> realisedTransactions = new ArrayList<>();
//        List<PeriodicTransaction> rulesToUpdate = new ArrayList<>();
//
//        // keep update
//        for (PeriodicTransaction pdTxRule : periodicRules) {
//            boolean ruleUpdated = false;
//            while (pdTxRule.isExecutionDue(now)) {  // isExecutionDue should use the rule's nextExecutionTime
//                Instant executionTime = pdTxRule.nextExecutionTime();
//                if (executionTime == null || now.isBefore(executionTime)) { // Safety break if nextExecutionTime is null or in future
//                    break;
//                }
//                pdTxRule.markExecuted(executionTime);  // Mark with the actual execution time
//                ruleUpdated = true;
//
//                OneTimeTransaction oneTimeTx = pdTxRule.createInstance(executionTime);
//                realisedTransactions.add(oneTimeTx);
//
//                userAccount.addTransaction(oneTimeTx); // Update balances in domain object for each materialized tx
//            }
//
//            if(ruleUpdated) {
//                rulesToUpdate.add(pdTxRule);
//            }
//        }
//
//        if (!realisedTransactions.isEmpty()) {
//            // Save all new one-time transactions
//            // Note: transactionRepository.save() is for single entities.
//            // If you have many, a saveAll would be more efficient if available.
//            // For now, saving one by one:
//            for (OneTimeTransaction tx : realisedTransactions) {
//                this.transactionRepository.save(tx);
//            }
//
//            // Save the updated user account (with new balances)
//            userAccountRepository.saveUser(userAccount);
//        }
//
//        if(!rulesToUpdate.isEmpty()){
//            // Persist updates to periodic rules (lastExecutedAt)
//            this.periodicTransactionRepository.saveAll(rulesToUpdate);
//        }
//    }
    @Override
    @Transactional
    public void materialiseDue(Long accountId) {
        System.out.println("materialiseDue START for accountId: " + accountId);
        Instant now = Instant.now();
        System.out.println("Current time (now): " + now);

        try {
            List<PeriodicTransaction> periodicRules =
                    this.periodicTransactionRepository.duePeriodic(accountId, now);
            System.out.println("Fetched " + periodicRules.size() + " periodic rules due.");

            if (periodicRules.isEmpty()) {
                System.out.println("No periodic rules due. Exiting materialiseDue.");
                return;
            }

            UserAccount userAccount = userAccountRepository.findById(accountId)
                    .orElseThrow(() -> {
                        System.err.println("Account not found for ID: " + accountId + " in materialiseDue");
                        return new IllegalArgumentException("Account not found for ID: " + accountId);
                    });
            System.out.println("Fetched UserAccount: " + userAccount.getUsername() + ", Initial Balance: " + userAccount.getTotalBalance());

            List<OneTimeTransaction> realisedTransactions = new ArrayList<>();
            List<PeriodicTransaction> rulesToUpdate = new ArrayList<>();

            for (PeriodicTransaction pdTxRule : periodicRules) {
                System.out.println("Processing rule ID: " + pdTxRule.getId() +
                        ", LastExecutedAt: " + pdTxRule.getLastExecutedAt() +
                        ", CreateTime: " + pdTxRule.getCreateTime() +
                        ", Interval: " + pdTxRule.getInterval());
                boolean ruleUpdated = false;
                while (pdTxRule.isExecutionDue(now)) {
                    System.out.println("Rule ID: " + pdTxRule.getId() + " is due.");
                    Instant executionTime = pdTxRule.nextExecutionTime();
                    System.out.println("Calculated executionTime: " + executionTime);

                    if (executionTime == null || now.isBefore(executionTime)) {
                        System.out.println("executionTime is null or in the future. Breaking inner loop.");
                        break;
                    }
                    pdTxRule.markExecuted(executionTime);
                    ruleUpdated = true;
                    System.out.println("Rule ID: " + pdTxRule.getId() + " marked executed at: " + pdTxRule.getLastExecutedAt());

                    OneTimeTransaction oneTimeTx = pdTxRule.createInstance(executionTime);
                    System.out.println("Created OneTimeTransaction: Amount=" + oneTimeTx.getAmount() +
                            ", Type=" + oneTimeTx.getTxtype() +
                            ", CreateTime (Timestamp)=" + oneTimeTx.getCreateTime()); // CRITICAL: Check this value

                    realisedTransactions.add(oneTimeTx);

                    System.out.println("Before userAccount.addTransaction - Balance: " + userAccount.getTotalBalance());
                    userAccount.addTransaction(oneTimeTx);
                    System.out.println("After userAccount.addTransaction - Balance: " + userAccount.getTotalBalance() +
                            ", Income: " + userAccount.getTotalIncome() +
                            ", Outflow: " + userAccount.getTotalOutflow());
                }

                if (ruleUpdated) {
                    rulesToUpdate.add(pdTxRule);
                    System.out.println("Rule ID: " + pdTxRule.getId() + " added to rulesToUpdate.");
                }
            }

            if (!realisedTransactions.isEmpty()) {
                System.out.println("Attempting to save " + realisedTransactions.size() + " realised transactions.");
                for (OneTimeTransaction tx : realisedTransactions) {
                    System.out.println("Saving OneTimeTransaction with CreateTime: " + tx.getCreateTime() + ", Amount: " + tx.getAmount());
                    this.transactionRepository.save(tx); // Check for issues here
                    System.out.println("OneTimeTransaction presumably saved.");
                }

                System.out.println("Attempting to save UserAccount. Current Balance: " + userAccount.getTotalBalance());
                userAccountRepository.saveUser(userAccount);
                System.out.println("UserAccount presumably saved.");
            } else {
                System.out.println("No realised transactions to save.");
            }

            if (!rulesToUpdate.isEmpty()) {
                System.out.println("Attempting to saveAll " + rulesToUpdate.size() + " updated periodic rules.");
                this.periodicTransactionRepository.saveAll(rulesToUpdate);
                System.out.println("Updated periodic rules presumably saved.");
            } else {
                System.out.println("No periodic rules to update.");
            }
            System.out.println("materialiseDue END for accountId: " + accountId);

        } catch (Exception e) {
            System.err.println("!!!!!!!! Exception in materialiseDue for accountId: " + accountId + " !!!!!!!!");
            e.printStackTrace(); // Print the full stack trace
            // Depending on your transaction manager, you might need to manually mark for rollback
            // or ensure the exception propagates to be handled by Spring's transactional aspect.
            // For debugging, you might re-throw if it's a RuntimeException:
            if (e instanceof RuntimeException) {
                throw (RuntimeException) e;
            }
            throw new RuntimeException("Wrapped exception in materialiseDue", e); // Or handle appropriately
        }
    }

    /* ---------- query ---------- */

    @Override
    public List<TransactionView> list(Long accountId, Instant from, Instant to) {
        // Ensure account exists before querying transactions
        userAccountRepository.findById(accountId)
                .orElseThrow(() -> new IllegalArgumentException("Account not found for ID: " + accountId));

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
        txView.category = oneTimeTx.getTransactionCategory(); // Add if you want category in view
        return txView;
    }

}
