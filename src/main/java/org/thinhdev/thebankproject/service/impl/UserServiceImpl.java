package org.thinhdev.thebankproject.service.impl;

import org.springframework.stereotype.Service;
import org.thinhdev.thebankproject.dto.AccountInfo;

import org.thinhdev.thebankproject.dto.request.UserRequest;
import org.thinhdev.thebankproject.dto.response.BankResponse;
import org.thinhdev.thebankproject.entity.User;
import org.thinhdev.thebankproject.enums.UserStatus;
import org.thinhdev.thebankproject.repository.UserRepository;
import org.thinhdev.thebankproject.utils.AccountUtils;

import java.math.BigDecimal;

@Service
public class UserServiceImpl implements UserService
{
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    @Override
    public BankResponse createAccount(UserRequest userRequest) {
        if(Boolean.TRUE.equals(userRepository.existsByEmail(userRequest.getEmail()))) {
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
        return BankResponse.builder()
                .responseCode(AccountUtils.ACCOUNT_CREATION_SUCCESS)
                .responseMessage(AccountUtils.ACCOUNT_CREATION_SUCCESS_MESSAGE)
                .accountInfo(AccountInfo.builder()
                        .accountBalance(savedUser.getAccountBalance())
                        .accountNumber(savedUser.getAccountNumber())
                        .accountName(String.format("%s %s %s",
                                savedUser.getFirstName(),
                                savedUser.getLastName(),
                                savedUser.getOtherName() != null ? savedUser.getOtherName() : "").trim())
                        .build())
                .build();

    }
}