package com.bostongene.exception;

/**
 * Created by Andry on 29.05.17.
 */
public class UserExistsException extends JpaException {
    private static final String MESSAGE = "User already exists";

    @SuppressWarnings("unused")
    public UserExistsException() {
        super(MESSAGE);
    }

    public UserExistsException(Throwable cause) {
        super(MESSAGE, cause);
    }
}
