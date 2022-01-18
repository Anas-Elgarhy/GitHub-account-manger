package com.anas.code.emailSender;

import com.anas.code.emailSender.email.Email;
import com.anas.code.emailSender.message.Type;
import com.anas.code.emailSender.user.User;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author <a href="https://github.com/Anas-Elgarhy">Anas-Elgarhy</a>
 * at 2022-02-18
 */
public class Utilts {
    private static final Logger LOGGER = Logger.getLogger(Utilts.class.getName());

    /**
     * This method is used to set up the properties for the session
     * @param props - properties for the session
     * @param host - host, like smtp.gmail.com
     */
    public static void setUpServer(Properties props, String host) {
        LOGGER.log(Level.INFO,"Setting up server");

        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "456");
        props.put("mail.smtp.auth", "true"); // enable authentication
        props.put("mail.smtp.ssl.enable", "true"); // enable ssl
    }

    /**
     *  This method is used to set up the session for sending the email
     * @param props - properties for the session
     * @param user - user object
     * @return - session
     */
    public static Session getSession(Properties props , User user) {
        LOGGER.log(Level.INFO,"Getting session");

        return Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user.getEmail(), user.getPassword());
            }
        });
    }

    /**
     * This method is used to create the message for the email
     * @param session - session
     * @param email - email object
     * @return - mime message
     */
    public static MimeMessage createMessage(Session session, Email email) {
        LOGGER.log(Level.INFO,"Creating message");
        // Create a default MimeMessage object.
        MimeMessage message = new MimeMessage(session);
        try {
            // Set from
            message.setFrom(new InternetAddress(email.getFrom()));
            // Set to
            message.setRecipients(MimeMessage.RecipientType.TO, InternetAddress.parse(email.getTo()));
            // Set subject
            message.setSubject(email.getSubject());
            // Set body
            message.setContent(createMultipart(email));
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return message;
    }

    /**
     * This method is used to create the message parts for the email
     * @param email - email object
     * @return - multipart object
     */
    private static Multipart createMultipart(Email email) {
        Multipart multipart = new MimeMultipart();
        MimeBodyPart attachmentsPart = new MimeBodyPart();
        MimeBodyPart textPart = new MimeBodyPart();

        try {
            // Set attachments part
            if (email.getAttachments() != null) {
                for (File file : email.getAttachments()) {
                    attachmentsPart.attachFile(file);
                }
            }
            // Set text part
            if (email.getBody().getType() == Type.TEXT) {
                textPart.setText(email.getBody().getMessage());
            } else {
                textPart.setContent(email.getBody().getMessage(), email.getBody().getType().getMimeType());
            }
            // Add parts to multipart
            multipart.addBodyPart(textPart);
            multipart.addBodyPart(attachmentsPart);
        } catch (MessagingException | IOException e) {
            e.printStackTrace();
        }
        return multipart;
    }

    /**
     * This method is used to send the email
     * @param message - message object
     */
    public static boolean sendMessage(MimeMessage message) {
        LOGGER.log(Level.INFO,"Sending message");
        try {
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
        LOGGER.log(Level.INFO,"Message sent successfully");
        return true;
    }
}
