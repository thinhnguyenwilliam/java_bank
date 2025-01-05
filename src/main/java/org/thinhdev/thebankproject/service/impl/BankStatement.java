package org.thinhdev.thebankproject.service.impl;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.thinhdev.thebankproject.entity.Transaction;
import org.thinhdev.thebankproject.repository.TransactionRepository;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
@AllArgsConstructor
@Slf4j
public class BankStatement {

    private final TransactionRepository transactionRepository;
    private static final String FILE = "C:\\BackendJava\\The_java_academic\\MyStatement.pdf";

    public List<Transaction> generateStatement(String accountNumber, String startDate, String endDate) {
        // Parse start and end dates
        LocalDate start = LocalDate.parse(startDate, DateTimeFormatter.ISO_DATE);
        LocalDate end = LocalDate.parse(endDate, DateTimeFormatter.ISO_DATE);

        // Filter transactions
        return transactionRepository.findAll()
                .stream()
                .filter(transaction -> transaction.getAccountNumber().equals(accountNumber))
                .filter(transaction -> !transaction.getCreatedAt().isBefore(start) && !transaction.getCreatedAt().isAfter(end))
                .toList();
    }

    public void createStatementPdf(String accountNumber, String startDate, String endDate) {
        try {
            List<Transaction> transactions = generateStatement(accountNumber, startDate, endDate);

            if (transactions.isEmpty()) {
                log.warn("No transactions found for the given criteria.");
                return;
            }

            // Create PDF document
            Document document = new Document(PageSize.A4);
            OutputStream outputStream = new FileOutputStream(FILE);
            PdfWriter.getInstance(document, outputStream);
            document.open();

            // Add bank header information
            PdfPTable bankInfoTable = new PdfPTable(1);
            PdfPCell bankName = new PdfPCell(new Phrase("Mystery Company", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, BaseColor.WHITE)));
            bankName.setBorder(0);
            bankName.setBackgroundColor(BaseColor.BLUE);
            bankName.setPadding(10f);
            bankName.setHorizontalAlignment(Element.ALIGN_CENTER);
            bankInfoTable.addCell(bankName);
            document.add(bankInfoTable);

            // Add empty space
            document.add(new Paragraph(" "));

            // Add transaction table
            PdfPTable transactionTable = new PdfPTable(4);
            transactionTable.setWidthPercentage(100);
            transactionTable.setSpacingBefore(10f);

            // Add table headers
            transactionTable.addCell(new PdfPCell(new Phrase("Date", FontFactory.getFont(FontFactory.HELVETICA_BOLD))));
            transactionTable.addCell(new PdfPCell(new Phrase("Description", FontFactory.getFont(FontFactory.HELVETICA_BOLD))));
            transactionTable.addCell(new PdfPCell(new Phrase("Amount", FontFactory.getFont(FontFactory.HELVETICA_BOLD))));
            transactionTable.addCell(new PdfPCell(new Phrase("Balance", FontFactory.getFont(FontFactory.HELVETICA_BOLD))));

            // Add transactions
            for (Transaction transaction : transactions) {
                transactionTable.addCell(transaction.getCreatedAt().toString());
                transactionTable.addCell(String.valueOf(transaction.getAmount()));
            }
            document.add(transactionTable);

            // Close the document
            document.close();
            outputStream.close();

            log.info("Bank statement PDF generated successfully at: {}", FILE);
        } catch (Exception e) {
            log.error("Error generating bank statement PDF", e);
        }
    }
}
