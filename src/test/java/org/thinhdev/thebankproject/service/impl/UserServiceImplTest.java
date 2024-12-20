package org.thinhdev.thebankproject.service.impl;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.thinhdev.thebankproject.dto.request.UserRequest;
import org.thinhdev.thebankproject.dto.response.BankResponse;
import org.thinhdev.thebankproject.entity.User;
import org.thinhdev.thebankproject.repository.UserRepository;
import org.thinhdev.thebankproject.utils.AccountUtils;

import java.math.BigDecimal;

class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateAccount_Success() {
        UserRequest userRequest = new UserRequest();
        userRequest.setEmail("test@example.com");
        userRequest.setFirstName("John");
        userRequest.setLastName("Doe");
        userRequest.setOtherName("Smith");

        when(userRepository.existsByEmail(userRequest.getEmail())).thenReturn(false);

        User savedUser = User.builder()
                .email(userRequest.getEmail())
                .accountBalance(BigDecimal.ZERO)
                .accountNumber("202400123456")
                .firstName("John")
                .lastName("Doe")
                .otherName("Smith")
                .build();

        when(userRepository.save(any(User.class))).thenReturn(savedUser);

        BankResponse response = userService.createAccount(userRequest);

        assertNotNull(response);
        assertEquals(AccountUtils.ACCOUNT_CREATION_SUCCESS, response.getResponseCode());
        assertNotNull(response.getAccountInfo());
        assertEquals(savedUser.getAccountNumber(), response.getAccountInfo().getAccountNumber());
    }

    @Test
    void testCreateAccount_EmailExists() {
        UserRequest userRequest = new UserRequest();
        userRequest.setEmail("existing@example.com");

        when(userRepository.existsByEmail(userRequest.getEmail())).thenReturn(true);

        BankResponse response = userService.createAccount(userRequest);

        assertNotNull(response);
        assertEquals(AccountUtils.ACCOUNT_EXISTS_CODE, response.getResponseCode());
        assertNull(response.getAccountInfo());
    }
}
