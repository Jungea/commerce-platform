package org.example.commerceplatform.member.api.dto;

public record LoginResponse(
        Long id,
        String email,
        String name
) {}
