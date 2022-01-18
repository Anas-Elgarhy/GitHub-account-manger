package com.anas.code.emailSender.message;

/**
 * @author <a href="https://github.com/Anas-Elgarhy">Anas-Elgarhy</a>
 * at 2022-02-18
 */
public class MessageBody {
    private String message;

    /**
     *  Create a new MessageBody object
     * @param message - the message to be sent
     */
    public MessageBody(String message) {
        this.message = message;
    }

    /**
     *  Get the message text
     * @return the message
     */
    public String getMessage() {
        return "<div class=\"wrapper-class\">" +
                message +
                "</div>";
    }
}
