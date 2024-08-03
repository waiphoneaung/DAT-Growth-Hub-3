package com.g3.elis.util;

public class EmailSendResult {
    private String recipient;
    private String status;

    public EmailSendResult(String recipient, String status) {
        this.recipient = recipient;
        this.status = status;
    }

    public String getRecipient() {
        return recipient;
    }

    public String getStatus() {
        return status;
    }
}
