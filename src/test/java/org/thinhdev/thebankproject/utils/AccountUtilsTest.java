package org.thinhdev.thebankproject.utils;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.Year;

class AccountUtilsTest
{
    @Test
    void testGenerateAccountNumber() {
        // Generate an account number
        String accountNumber = AccountUtils.generateAccountNumber();

        // Get the current year
        String currentYear = Year.now().toString();

        // Validate the length
        assertEquals(currentYear.length() + 6, accountNumber.length(),
                "Account number should be year + 6 digits");

        // Validate that it starts with the current year
        assertTrue(accountNumber.startsWith(currentYear),
                "Account number should start with the current year");

        // Extract and validate the random number part
        String randomPart = accountNumber.substring(currentYear.length());
        int randomNumber = Integer.parseInt(randomPart);
        assertTrue(randomNumber >= 100000 && randomNumber <= 999999,
                "Random number should be in the range 100000 to 999999");
    }

    @Test
    void testGenerateAccountNumberIsUnique() {
        // Generate multiple account numbers
        String accountNumber1 = AccountUtils.generateAccountNumber();
        String accountNumber2 = AccountUtils.generateAccountNumber();

        // Validate they are not the same
        assertNotEquals(accountNumber1, accountNumber2,
                "Generated account numbers should be unique");
    }
}
