package com.anas.code.emailSender.user;

/**
 * @author <a href="https://github.com/Anas-Elgarhy">Anas-Elgarhy</a>
 * at 2022-02-18
 */
public interface User {
    /**
     * Get the email address of the user.
     * @return - the email address of the user.
     */
    public String getEmail();

    /**
     * Get the password of the user.
     * @return - the password of the user.
     */
    public String getPassword();
}
