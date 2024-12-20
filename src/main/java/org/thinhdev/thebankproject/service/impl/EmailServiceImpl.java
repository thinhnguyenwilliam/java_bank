package org.thinhdev.thebankproject.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.thinhdev.thebankproject.config.MailConfig;
import org.thinhdev.thebankproject.dto.EmailDetails;
import org.thinhdev.thebankproject.exception.EmailSendException;
import org.thinhdev.thebankproject.service.EmailService;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EmailServiceImpl implements EmailService {

    static final Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);

    JavaMailSender javaMailSender;
    MailConfig mailConfig;

    @Override
    public void sendEmailAlert(EmailDetails emailDetails) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(mailConfig.getUsername());
            message.setTo(emailDetails.getRecipient());
            message.setSubject(emailDetails.getSubject());
            message.setText(emailDetails.getMessageBody());

            javaMailSender.send(message);
            logger.info("Email sent successfully to {}", emailDetails.getRecipient());
        } catch (MailException e) {
            throw new EmailSendException("Failed to send email", e);
        }
    }
}
