package com.apiece.springboot_sns_sample.domain.user;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class UserException extends RuntimeException {

    private final HttpStatus status;

    private UserException(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }

    public static UserException notFound(Long id) {
        return new UserException(HttpStatus.NOT_FOUND, "User not found: id=" + id);
    }

    public static UserException notFound(String username) {
        return new UserException(HttpStatus.NOT_FOUND, "User not found: username=" + username);
    }

    public static UserException passwordTooLong(int maxBytes) {
        return new UserException(HttpStatus.BAD_REQUEST, "password: must be at most " + maxBytes + " bytes in UTF-8");
    }

    public static UserException duplicateUsername(String username) {
        return new UserException(HttpStatus.CONFLICT, "Username already exists: " + username);
    }
}
