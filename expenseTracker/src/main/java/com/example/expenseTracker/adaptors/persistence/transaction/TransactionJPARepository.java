package com.example.expenseTracker.adaptors.persistence.transaction;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.Instant;
import java.util.List;

public interface TransactionJPARepository extends JpaRepository<TransactionJPAEntity, Long> {
    @Query(
            "SELECT t from TransactionJPAEntity t " +
                    "WHERE t.accountId = :accountId " +
                    "AND t.timestamp between :from AND :to "
    )
    List<TransactionJPAEntity> findByAccountAndRange(
            @Param("accountId") Long accountId,
            @Param("from") Instant from,
            @Param("to") Instant to
    );
}
