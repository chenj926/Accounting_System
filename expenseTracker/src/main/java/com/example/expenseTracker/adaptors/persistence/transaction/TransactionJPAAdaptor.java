package com.example.expenseTracker.adaptors.persistence.transaction;

import com.example.expenseTracker.application.ports.transaction.TransactionRepository;
import com.example.expenseTracker.domain.entity.transaction.onetime.OneTimeTransaction;
import lombok.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class TransactionJPAAdaptor implements TransactionRepository {
    private final TransactionJPARepository txJPARepo;
    private final TxMapper mapper;

    @Override
    public OneTimeTransaction save(OneTimeTransaction tx) {
        return mapper.toDomain(this.txJPARepo.save(mapper.toJpa(tx)));
    }

    @Override
    public Optional<OneTimeTransaction> findById(Long id) {
        return this.txJPARepo.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<OneTimeTransaction> findByAccountAndRange(
            Long accountId, Instant from, Instant to
    ) {
        return this.txJPARepo.findByAccountAndRange(accountId, from, to)
                .stream().map(mapper::toDomain).toList();
    }
}

@Mapper(componentModel = "spring")
interface TxMapper {
    //    @Mapping(target = "totalIncome", ignore = true)
//    @Mapping(target = "totalOutflow", ignore = true)
//    @Mapping(target = "totalBalance", ignore = true)
//    @Mapping(target = "sharedAccounts", ignore = true)
//    @Mapping(target = "transactions", ignore = true)   // <- new unmapped field mentioned in the warning
    @Mapping(target="createTime", source="timestamp")
    OneTimeTransaction toDomain(TransactionJPAEntity e);

    @Mapping(target="timestamp", source="createTime")
    TransactionJPAEntity toJpa(OneTimeTransaction d);
}
