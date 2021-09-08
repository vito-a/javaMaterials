/*
 * Java Autumn 2021 - 08. Exceptions  Basic IO - NoteBook
 *
 * LoginExistsException
 *
 */
package ua.training.model;

public class LoginExistsException extends Exception {
    private String loginData;

    public String getLoginData() {
        return loginData;
    }

    public LoginExistsException(String message, String loginData) {
        super(message);
        this.loginData = loginData;
    }
}
