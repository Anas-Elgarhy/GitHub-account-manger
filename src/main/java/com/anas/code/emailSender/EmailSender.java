package com.anas.code.emailSender;

import com.anas.code.emailSender.email.Email;
import com.anas.code.emailSender.user.User;

import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailSender {
    private final Email email;
    private final User user;
    private final Properties systemProperties;

    public EmailSender(Email email, User user) {
        this.email = email;
        this.user = user;

        // Get system properties
        systemProperties = System.getProperties();
    }

    public boolean send() {
        // Setup mail server
        Utilts.setUpServer(systemProperties, email.getHost());

        // Create a mail session
        Session session = Utilts.getSession(systemProperties, user);

        // Create a mail message
        MimeMessage message = Utilts.createMessage(session, email);

        // Send the message
        return Utilts.sendMessage(message);
    }
}
