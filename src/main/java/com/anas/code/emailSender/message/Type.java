package com.anas.code.emailSender.message;

/**
 * @author <a href="https://github.com/Anas-Elgarhy">Anas-Elgarhy</a>
 * at 2022-02-18
 */
public enum Type {
    TEXT("text/plan"),
    HTML("text/html");

    private final String mimetype;

    private Type(String mimetype) {
        this.mimetype = mimetype;
    }

    public String getMimeType() {
        return mimetype;
    }
}
