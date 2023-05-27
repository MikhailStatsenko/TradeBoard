package com.app.tradeboard.utils.Exceptions;

import lombok.Getter;

@Getter
public class UserNotFoundException extends RuntimeException {
    private final long userId;

    public UserNotFoundException(long userId) {
        super("User not found with ID: " + userId);
        this.userId = userId;
    }
}

