package org.thinhdev.thebankproject.exception;

public class EmailSendException extends RuntimeException {
    public EmailSendException(String message, Throwable cause) {
        super(message, cause);
    }
}
