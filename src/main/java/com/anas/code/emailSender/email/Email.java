package com.anas.code.emailSender.email;

import com.anas.code.emailSender.message.MessageBody;

import java.io.File;

/**
 * @author <a href="https://github.com/Anas-Elgarhy">Anas-Elgarhy</a>
 * at 2022-02-18
 */
public class Email {
    protected String host;
    private String from;
    private String to;
    private String subject;
    private MessageBody body;
    private File[] attachments;

    /**
     * Create a new Email object
     * @param emailDetails - email details
     */
    public Email(EmailDetails emailDetails) {
        setFrom(emailDetails.getFrom());
        setTo(emailDetails.getTo());
        setHost(emailDetails.getHost());
        setSubject(emailDetails.getSubject());
        setBody(emailDetails.getBody());
        setAttachments(emailDetails.getAttachments());
    }

    // Getters and Setters
    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public MessageBody getBody() {
        return body;
    }

    public void setBody(MessageBody body) {
        this.body = body;
    }

    public File[] getAttachments() {
        return attachments;
    }

    public void setAttachments(File[] attachments) {
        this.attachments = attachments;
    }
}
