package org.example.commerceplatform.member.api.dto;

public record MemberSignupResponse(
        Long id,
        String email,
        String name
) {}
