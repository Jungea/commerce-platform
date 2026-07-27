package org.example.commerceplatform.order.api.dto;

import org.example.commerceplatform.order.domain.Order;
import org.example.commerceplatform.order.domain.OrderItem;

import java.time.LocalDateTime;
import java.util.List;

public record OrderResponse(
        Long orderId,
        List<OrderItemResponse> items,
        Long totalPrice,
        LocalDateTime orderedAt
) {
    public record OrderItemResponse(
            Long orderItemId,
            Long productId,
            Long price,
            Integer quantity
    ) {
        public static OrderItemResponse from(OrderItem item) {
            return new OrderItemResponse(item.getId(), item.getProductId(), item.getPrice(), item.getQuantity());
        }
    }

    public static OrderResponse from(Order order) {
        List<OrderItemResponse> items = order.getItems().stream()
                .map(OrderItemResponse::from)
                .toList();
        return new OrderResponse(order.getId(), items, order.getTotalPrice(), order.getOrderedAt());
    }
}
