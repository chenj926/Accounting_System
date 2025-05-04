package com.example.expenseTracker.adaptors.persistence.transaction.periodic;

import com.example.expenseTracker.application.ports.transaction.periodic.PeriodicTransactionRepository;

import com.example.expenseTracker.domain.entity.transaction.periodic.PeriodicTransaction;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

interface SpringDataPeriodicRepo extends JpaRepository<PeriodicTransactionJPAEntity, Long> {
    /* JPQL uses DATE_ADD via a vendor‑neutral function call.  intervalSecs is passed as LONG. */
    @Query(value = """
      SELECT *
       FROM periodic_tx p
       WHERE p.account_id = :accountId
         AND (p.last_executed_at IS NULL
              OR DATE_ADD(p.last_executed_at, INTERVAL p.interval_secs SECOND) <= :now)
      """, nativeQuery = true
    )
    List<PeriodicTransactionJPAEntity> duePeriodic(@Param("accountId") Long accountId,
                                                   @Param("now") Instant now);
}

// Map Struct mapper
@Mapper(componentModel = "spring")
interface PeriodicTxMapper {
    PeriodicTransaction toDomain(PeriodicTransactionJPAEntity e);
    PeriodicTransactionJPAEntity toJpa(PeriodicTransaction d);
}

@Repository
@RequiredArgsConstructor
public class PeriodicTransactionJPARepository implements PeriodicTransactionRepository {

    private final SpringDataPeriodicRepo jpa;
    private final PeriodicTxMapper       mapper;
//    private final PeriodicTransactionJPARepository pdTxJPARepo;

    @Override
    public List<PeriodicTransaction> duePeriodic(Long accountId, Instant now) {
        return jpa.duePeriodic(accountId, now)
                .stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void saveAll(List<PeriodicTransaction> txs) {
        List<PeriodicTransactionJPAEntity> ents =
                txs.stream().map(mapper::toJpa).toList();
        jpa.saveAll(ents);
    }

    /* ---------- single‑row save ---------- */
    @Override
    public PeriodicTransaction save(PeriodicTransaction tx) {
        PeriodicTransactionJPAEntity saved = jpa.save(mapper.toJpa(tx));
        return mapper.toDomain(saved);
    }

}
