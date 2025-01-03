package org.thinhdev.thebankproject.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thinhdev.thebankproject.dto.TransactionDto;
import org.thinhdev.thebankproject.entity.Transaction;
import org.thinhdev.thebankproject.repository.TransactionRepository;
import org.thinhdev.thebankproject.service.TransactionService;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Transactional
@Slf4j
public class TransactionServiceImpl implements TransactionService
{
    TransactionRepository transactionRepository;

    @Override
    public void saveTransaction(TransactionDto transactionDto)
    {
        Transaction transaction = Transaction.builder()
                .transactionType(transactionDto.getTransactionType())
                .amount(transactionDto.getAmount())
                .accountNumber(transactionDto.getAccountNumber())
                .status("SUCCESS")
                .build();
        transactionRepository.save(transaction);
        log.info("Saved transaction successfully: {}", transaction);
    }
}
