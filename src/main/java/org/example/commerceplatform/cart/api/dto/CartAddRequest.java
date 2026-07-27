package org.example.commerceplatform.cart.api.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record CartAddRequest(
        @NotNull Long productId,
        @NotNull @Min(1) Integer quantity
) {}
