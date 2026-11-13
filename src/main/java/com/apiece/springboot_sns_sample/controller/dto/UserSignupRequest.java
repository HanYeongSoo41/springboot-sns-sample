package com.apiece.springboot_sns_sample.controller.dto;

import com.apiece.springboot_sns_sample.domain.user.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserSignupRequest(
        @NotBlank @Size(min = 3, max = 50) String username,
        @NotBlank @Size(min = 8, max = 72) String password) {

    public User toEntity() {
        return new User(username, password);
    }
}
