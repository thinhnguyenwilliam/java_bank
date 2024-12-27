package org.thinhdev.thebankproject.service;

import org.thinhdev.thebankproject.dto.request.CreditDebitRequest;
import org.thinhdev.thebankproject.dto.request.EnquiryRequest;
import org.thinhdev.thebankproject.dto.request.UserRequest;
import org.thinhdev.thebankproject.dto.response.BankResponse;


public interface UserService
{
    BankResponse createAccount(UserRequest userRequest);
    BankResponse balanceEnquiry(EnquiryRequest enquiryRequest);
    String nameEnquiry(EnquiryRequest enquiryRequest);
    BankResponse creditAccount(CreditDebitRequest creditDebitRequest);
}
