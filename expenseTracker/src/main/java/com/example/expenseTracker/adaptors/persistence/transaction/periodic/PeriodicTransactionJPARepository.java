package com.example.expenseTracker.adaptors.persistence.transaction.periodic;

import com.example.expenseTracker.application.ports.transaction.periodic.PeriodicTransactionRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

public interface PeriodicTransactionJPARepository extends JpaRepository<PeriodicTransactionJPAEntity, Long> {
    @Query(
            "SELECT p from PeriodicTransactionJPAEntity p " +
                    "WHERE p.accountId = :accountId " +
                    "AND p.lastExecutedAt is NULL " +
                    "OR p.lastExecutedAt + p.interval < :now "
    )
    List<PeriodicTransactionJPAEntity> duePeriodic(@Param("accountId") Long accountId,
                                                   @Param("now") Instant now);

}
