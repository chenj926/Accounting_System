package com.example.expenseTracker.adaptors.web.controller.transaction;

import com.example.expenseTracker.adaptors.web.dto.OneTimeRequestDto;
import com.example.expenseTracker.adaptors.web.dto.PeriodicRequestDto;
import com.example.expenseTracker.application.use_case_ports.transaction.TransactionUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionUseCase txUseCase;

    /* ---------- endpoints ---------- */

    @PostMapping("/one-time")
    public Long addOneTimeTx(@RequestBody OneTimeRequestDto dto) {
        return this.txUseCase.addOneTimeTx(
                dto.getAccountId(),
                dto.getAmount(),
                dto.getDescription(),
                dto.getWhen(),
                dto.getType(),
                dto.getCategory());
    }

    @PostMapping("/periodic")
    public Long schedulePeriodicTx(@RequestBody PeriodicRequestDto dto) {
        return this.txUseCase.schedulePeriodicTx(
                dto.getAccountId(), dto.getAmount(), dto.getDescription(),
                dto.getCategory(), dto.getEvery(), dto.getType());
    }

    @GetMapping("/{accountId}")
    public List<TransactionUseCase.TransactionView> list(
            @PathVariable Long accountId,
            @RequestParam Instant from,
            @RequestParam Instant to) {
        return this.txUseCase.list(accountId, from, to);
    }
}
