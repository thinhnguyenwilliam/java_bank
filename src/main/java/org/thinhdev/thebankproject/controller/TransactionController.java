package org.thinhdev.thebankproject.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.thinhdev.thebankproject.entity.Transaction;
import org.thinhdev.thebankproject.service.impl.BankStatement;

import java.util.List;

@RestController
@RequestMapping("/api/bankStatement")
@AllArgsConstructor
public class TransactionController {

    private final BankStatement bankStatement;

    @GetMapping
    public List<Transaction> generateBankStatement(
            @RequestParam String accountNumber,
            @RequestParam String startDate,
            @RequestParam String endDate) {

        return bankStatement.generateStatement(accountNumber, startDate, endDate);
    }
}
