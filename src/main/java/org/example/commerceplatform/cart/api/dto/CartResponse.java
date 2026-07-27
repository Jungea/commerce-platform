package org.example.commerceplatform.cart.api.dto;

import org.example.commerceplatform.cart.domain.Cart;
import org.example.commerceplatform.cart.domain.CartItem;

import java.util.List;

public record CartResponse(
        Long cartId,
        List<CartItemResponse> items
) {
    public record CartItemResponse(
            Long cartItemId,
            Long productId,
            Integer quantity
    ) {
        public static CartItemResponse from(CartItem item) {
            return new CartItemResponse(item.getId(), item.getProductId(), item.getQuantity());
        }
    }

    public static CartResponse from(Cart cart) {
        List<CartItemResponse> items = cart.getItems().stream()
                .map(CartItemResponse::from)
                .toList();
        return new CartResponse(cart.getId(), items);
    }
}
