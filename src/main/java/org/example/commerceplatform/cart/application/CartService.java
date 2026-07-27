package org.example.commerceplatform.cart.application;

import lombok.RequiredArgsConstructor;
import org.example.commerceplatform.cart.domain.Cart;
import org.example.commerceplatform.cart.domain.CartItem;
import org.example.commerceplatform.cart.domain.CartRepository;
import org.example.commerceplatform.common.exception.NotFoundException;
import org.example.commerceplatform.product.domain.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    @Transactional
    public Cart addItem(Long memberId, Long productId, Integer quantity) {
        if (!productRepository.existsById(productId)) {
            throw new NotFoundException("상품을 찾을 수 없습니다. id=" + productId);
        }

        Cart cart = cartRepository.findByMemberId(memberId)
                .orElseGet(() -> cartRepository.save(new Cart(memberId)));

        cart.getItems().stream()
                .filter(item -> item.getProductId().equals(productId))
                .findFirst()
                .ifPresentOrElse(
                        item -> item.increaseQuantity(quantity),
                        () -> cart.addItem(new CartItem(cart, productId, quantity))
                );

        return cart;
    }

    public Cart getCart(Long memberId) {
        return cartRepository.findByMemberId(memberId)
                .orElseGet(() -> new Cart(memberId));
    }
}
