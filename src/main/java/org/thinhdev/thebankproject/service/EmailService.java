package org.thinhdev.thebankproject.service;

import org.thinhdev.thebankproject.dto.EmailDetails;

public interface EmailService
{
    void sendEmailAlert(EmailDetails emailDetails);
}
