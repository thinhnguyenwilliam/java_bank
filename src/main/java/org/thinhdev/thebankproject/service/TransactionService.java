package org.thinhdev.thebankproject.service;

import org.thinhdev.thebankproject.dto.TransactionDto;


public interface TransactionService
{
    void saveTransaction(TransactionDto transactionDto);
}
