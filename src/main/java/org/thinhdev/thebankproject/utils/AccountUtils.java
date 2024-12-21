package org.thinhdev.thebankproject.utils;


import java.security.SecureRandom;
import java.time.Year;


public class AccountUtils
{
    public static final String ACCOUNT_EXISTS_CODE = "001";
    public static final String ACCOUNT_EXISTS_MESSAGE = "This user already has an account Fail-Created!";
    public static final String ACCOUNT_CREATION_SUCCESS = "002";
    public static final String ACCOUNT_CREATION_SUCCESS_MESSAGE = "Account has been successfully Created!";

    private static final int MIN = 100000;
    private static final int MAX = 999999;
    private static final SecureRandom RANDOM = new SecureRandom();

    // Private constructor to prevent instantiation
    private AccountUtils() {
    }

    public static String generateAccountNumber()
    {
        int randomNumber = RANDOM.nextInt((MAX - MIN) + 1) + MIN;
        String currentYear = Year.now().toString();
        return currentYear + randomNumber;
    }
}
