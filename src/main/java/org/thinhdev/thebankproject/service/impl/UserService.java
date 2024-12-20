package org.thinhdev.thebankproject.service.impl;

import org.thinhdev.thebankproject.dto.request.UserRequest;
import org.thinhdev.thebankproject.dto.response.BankResponse;


public interface UserService
{
    BankResponse createAccount(UserRequest userRequest);
}
