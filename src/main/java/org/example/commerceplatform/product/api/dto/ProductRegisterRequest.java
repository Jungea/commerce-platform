package org.example.commerceplatform.product.api.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductRegisterRequest(
        @NotBlank String name,
        @NotNull @Min(0) Long price,
        @NotNull @Min(0) Integer stockQuantity
) {}
