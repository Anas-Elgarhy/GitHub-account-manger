package com.anas.code.emailSender.email;

import com.anas.code.emailSender.message.MessageBody;

import java.io.File;

/**
 * @author <a href="https://github.com/Anas-Elgarhy">Anas-Elgarhy</a>
 * at 2022-02-18
 */
public interface EmailDetails {
    /**
     * @return the sender email address
     */
    public String getFrom();

    /**
     * @return the recipient email address
     */
    public String getTo();

    /**
     * @return the server host address, e.g. smtp.gmail.com
     */
    public String getHost();

    /**
     * @return the email subject
     */
    public String getSubject();

    /**
     * @return the email body
     */
    public MessageBody getBody();

    /**
     * @return the email attachments
     */
    public File[] getAttachments();
}
