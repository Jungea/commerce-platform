package org.example.commerceplatform.cart.api;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.commerceplatform.cart.api.dto.CartAddRequest;
import org.example.commerceplatform.cart.api.dto.CartResponse;
import org.example.commerceplatform.cart.application.CartService;
import org.example.commerceplatform.cart.domain.Cart;
import org.example.commerceplatform.common.exception.AuthenticationFailedException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping("/items")
    public ResponseEntity<CartResponse> addItem(@Valid @RequestBody CartAddRequest request, HttpSession session) {
        Long memberId = getMemberId(session);
        Cart cart = cartService.addItem(memberId, request.productId(), request.quantity());
        return ResponseEntity.ok(CartResponse.from(cart));
    }

    @GetMapping
    public ResponseEntity<CartResponse> getCart(HttpSession session) {
        Long memberId = getMemberId(session);
        Cart cart = cartService.getCart(memberId);
        return ResponseEntity.ok(CartResponse.from(cart));
    }

    private Long getMemberId(HttpSession session) {
        Long memberId = (Long) session.getAttribute("memberId");
        if (memberId == null) {
            throw new AuthenticationFailedException("로그인이 필요합니다.");
        }
        return memberId;
    }
}
