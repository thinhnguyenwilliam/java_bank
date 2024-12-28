package org.thinhdev.thebankproject.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thinhdev.thebankproject.dto.AccountInfo;
import org.thinhdev.thebankproject.dto.EmailDetails;
import org.thinhdev.thebankproject.dto.request.CreditDebitRequest;
import org.thinhdev.thebankproject.dto.request.EnquiryRequest;
import org.thinhdev.thebankproject.dto.request.UserRequest;
import org.thinhdev.thebankproject.dto.response.BankResponse;
import org.thinhdev.thebankproject.entity.User;
import org.thinhdev.thebankproject.enums.UserStatus;
import org.thinhdev.thebankproject.repository.UserRepository;
import org.thinhdev.thebankproject.service.EmailService;
import org.thinhdev.thebankproject.service.UserService;
import org.thinhdev.thebankproject.utils.AccountUtils;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Transactional
@Slf4j
public class UserServiceImpl implements UserService {

    private static final String FULL_NAME_FORMAT = "%s %s %s";

    UserRepository userRepository;
    EmailService emailService;

    @Override
    public BankResponse createAccount(UserRequest userRequest) {
        if (Boolean.TRUE.equals(userRepository.existsByEmail(userRequest.getEmail()))) {
            return BankResponse.builder()
                    .responseCode(AccountUtils.ACCOUNT_EXISTS_CODE)
                    .responseMessage(AccountUtils.ACCOUNT_EXISTS_MESSAGE)
                    .accountInfo(null)
                    .build();
        }

        User newUser = User.builder()
                .firstName(userRequest.getFirstName())
                .lastName(userRequest.getLastName())
                .otherName(userRequest.getOtherName())
                .gender(userRequest.getGender())
                .address(userRequest.getAddress())
                .stateOfOrigin(userRequest.getStateOfOrigin())
                .accountNumber(AccountUtils.generateAccountNumber())
                .email(userRequest.getEmail())
                .accountBalance(BigDecimal.ZERO)
                .phoneNumber(userRequest.getPhoneNumber())
                .alternativePhoneNumber(userRequest.getAlternativePhoneNumber())
                .status(UserStatus.ACTIVE.name())
                .build();

        User savedUser = userRepository.save(newUser);
        String accountName = String.format(FULL_NAME_FORMAT,
                savedUser.getFirstName(),
                savedUser.getLastName(),
                savedUser.getOtherName() != null ? savedUser.getOtherName() : "").trim();

        String emailMessage = String.format(
                "Account created successfully.%nAccount Number: %s%nAccount Name: %s",
                savedUser.getAccountNumber(), accountName);

        EmailDetails emailDetails = EmailDetails.builder()
                .recipient(savedUser.getEmail())
                .subject("Account Created")
                .messageBody(emailMessage)
                .build();
        emailService.sendEmailAlert(emailDetails);

        return BankResponse.builder()
                .responseCode(AccountUtils.ACCOUNT_CREATION_SUCCESS)
                .responseMessage(AccountUtils.ACCOUNT_CREATION_SUCCESS_MESSAGE)
                .accountInfo(AccountInfo.builder()
                        .accountBalance(savedUser.getAccountBalance())
                        .accountNumber(savedUser.getAccountNumber())
                        .accountName(accountName)
                        .build())
                .build();

    }

    @Override
    public BankResponse balanceEnquiry(EnquiryRequest enquiryRequest) {
        boolean isAccountExist = Boolean.TRUE.equals(userRepository.existsByAccountNumber(enquiryRequest.getAccountNumber()));

        if (!isAccountExist) {
            return BankResponse.builder()
                    .responseCode(AccountUtils.ACCOUNT_NOT_EXIST_CODE)
                    .responseMessage(AccountUtils.ACCOUNT_NOT_EXIST_MESSAGE)
                    .accountInfo(null)
                    .build();
        }

        User user = userRepository.findByAccountNumber(enquiryRequest.getAccountNumber());
        return BankResponse.builder()
                .responseCode(AccountUtils.ACCOUNT_FOUND_CODE)
                .responseMessage(AccountUtils.ACCOUNT_FOUND_MESSAGE)
                .accountInfo(AccountInfo.builder()
                        .accountBalance(user.getAccountBalance())
                        .accountNumber(enquiryRequest.getAccountNumber())
                        .accountName(String.format(FULL_NAME_FORMAT,
                                user.getFirstName(),
                                user.getLastName(),
                                user.getOtherName()).trim())
                        .build())
                .build();
    }

    @Override
    public String nameEnquiry(EnquiryRequest enquiryRequest) {
        boolean isAccountExist = Boolean.TRUE.equals(userRepository.existsByAccountNumber(enquiryRequest.getAccountNumber()));
        if (!isAccountExist) {
            return AccountUtils.ACCOUNT_NOT_EXIST_MESSAGE;
        }

        User user = userRepository.findByAccountNumber(enquiryRequest.getAccountNumber());
        return String.format(FULL_NAME_FORMAT,
                user.getFirstName(),
                user.getLastName(),
                user.getOtherName()).trim();
    }

    private User validateAndGetUser(String accountNumber) {
        if (!Boolean.TRUE.equals(userRepository.existsByAccountNumber(accountNumber))) {
            return null;
        }
        return userRepository.findByAccountNumber(accountNumber);
    }

    private BankResponse buildBankResponse(User user, String responseCode, String responseMessage) {
        return BankResponse.builder()
                .responseCode(responseCode)
                .responseMessage(responseMessage)
                .accountInfo(AccountInfo.builder()
                        .accountBalance(user.getAccountBalance())
                        .accountNumber(user.getAccountNumber())
                        .accountName(String.format(FULL_NAME_FORMAT,
                                user.getFirstName(),
                                user.getLastName(),
                                user.getOtherName()).trim())
                        .build())
                .build();
    }

    @Override
    public BankResponse creditAccount(CreditDebitRequest creditDebitRequest) {
        User userToCredit = validateAndGetUser(creditDebitRequest.getAccountNumber());
        if (userToCredit == null) {
            return BankResponse.builder()
                    .responseCode(AccountUtils.ACCOUNT_NOT_EXIST_CODE)
                    .responseMessage(AccountUtils.ACCOUNT_NOT_EXIST_MESSAGE)
                    .accountInfo(null)
                    .build();
        }

        // Credit account
        userToCredit.setAccountBalance(userToCredit.getAccountBalance().add(creditDebitRequest.getAmount()));
        userRepository.save(userToCredit);

        return buildBankResponse(userToCredit, AccountUtils.ACCOUNT_CREDITED_SUCCESS, AccountUtils.ACCOUNT_CREDITED_SUCCESS_MESSAGE);
    }


    @Override
    public BankResponse debitAccount(CreditDebitRequest creditDebitRequest) {
        User userToDebit = validateAndGetUser(creditDebitRequest.getAccountNumber());
        if (userToDebit == null) {
            return BankResponse.builder()
                    .responseCode(AccountUtils.ACCOUNT_NOT_EXIST_CODE)
                    .responseMessage(AccountUtils.ACCOUNT_NOT_EXIST_MESSAGE)
                    .accountInfo(null)
                    .build();
        }

        // Check if sufficient balance exists
        if (userToDebit.getAccountBalance().compareTo(creditDebitRequest.getAmount()) < 0) {
            return BankResponse.builder()
                    .responseCode(AccountUtils.INSUFFICIENT_BALANCE_CODE)
                    .responseMessage(AccountUtils.INSUFFICIENT_BALANCE_MESSAGE)
                    .accountInfo(null)
                    .build();
        }

        // Debit account
        userToDebit.setAccountBalance(userToDebit.getAccountBalance().subtract(creditDebitRequest.getAmount()));
        userRepository.save(userToDebit);

        return buildBankResponse(userToDebit, AccountUtils.ACCOUNT_DEBITED_SUCCESS, AccountUtils.ACCOUNT_DEBITED_SUCCESS_MESSAGE);
    }

}
