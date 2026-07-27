package org.example.commerceplatform.order.api;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.example.commerceplatform.common.exception.AuthenticationFailedException;
import org.example.commerceplatform.order.api.dto.OrderResponse;
import org.example.commerceplatform.order.application.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderResponse> create(HttpSession session) {
        Long memberId = getMemberId(session);
        OrderResponse response = OrderResponse.from(orderService.create(memberId));
        return ResponseEntity
                .created(URI.create("/orders/" + response.orderId()))
                .body(response);
    }

    @GetMapping
    public ResponseEntity<List<OrderResponse>> getList(HttpSession session) {
        Long memberId = getMemberId(session);
        List<OrderResponse> orders = orderService.getList(memberId).stream()
                .map(OrderResponse::from)
                .toList();
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> getOne(@PathVariable Long id, HttpSession session) {
        Long memberId = getMemberId(session);
        return ResponseEntity.ok(OrderResponse.from(orderService.getOne(id, memberId)));
    }

    private Long getMemberId(HttpSession session) {
        Long memberId = (Long) session.getAttribute("memberId");
        if (memberId == null) {
            throw new AuthenticationFailedException("로그인이 필요합니다.");
        }
        return memberId;
    }
}
