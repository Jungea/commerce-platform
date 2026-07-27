package org.example.commerceplatform.member.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record MemberSignupRequest(
        @NotBlank @Email String email,
        @NotBlank String password,
        @NotBlank String name
) {}
