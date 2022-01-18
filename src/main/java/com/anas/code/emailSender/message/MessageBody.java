package com.anas.code.emailSender.message;

/**
 * @author <a href="https://github.com/Anas-Elgarhy">Anas-Elgarhy</a>
 * at 2022-02-18
 */
public class MessageBody {
    private String message;
    private Type type;

    /**
     *  Create a new MessageBody object
     * @param message - the message to be sent
     * @param type - the type of message e.g. TEXT, HTML
     */
    public MessageBody(String message, Type type) {
        this.message = message;
        this.type = type;
    }

    /**
     *  Get the message text
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     *  Get the message type
     * @return the message type
     */
    public Type getType() {
        return type;
    }
}
