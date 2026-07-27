package org.example.commerceplatform.order.application;

import lombok.RequiredArgsConstructor;
import org.example.commerceplatform.cart.domain.Cart;
import org.example.commerceplatform.cart.domain.CartRepository;
import org.example.commerceplatform.common.exception.InvalidRequestException;
import org.example.commerceplatform.common.exception.NotFoundException;
import org.example.commerceplatform.order.domain.Order;
import org.example.commerceplatform.order.domain.OrderItem;
import org.example.commerceplatform.order.domain.OrderRepository;
import org.example.commerceplatform.product.domain.Product;
import org.example.commerceplatform.product.domain.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    @Transactional
    public Order create(Long memberId) {
        Cart cart = cartRepository.findByMemberId(memberId)
                .orElseThrow(() -> new InvalidRequestException("장바구니가 비어있습니다."));

        if (cart.getItems().isEmpty()) {
            throw new InvalidRequestException("장바구니가 비어있습니다.");
        }

        Order order = orderRepository.save(new Order(memberId));

        cart.getItems().forEach(cartItem -> {
            Product product = productRepository.findById(cartItem.getProductId())
                    .orElseThrow(() -> new NotFoundException("상품을 찾을 수 없습니다. id=" + cartItem.getProductId()));
            order.addItem(new OrderItem(order, product.getId(), product.getPrice(), cartItem.getQuantity()));
        });

        cart.getItems().clear();

        return order;
    }

    public List<Order> getList(Long memberId) {
        return orderRepository.findByMemberId(memberId);
    }

    public Order getOne(Long orderId, Long memberId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new NotFoundException("주문을 찾을 수 없습니다. id=" + orderId));
        if (!order.getMemberId().equals(memberId)) {
            throw new NotFoundException("주문을 찾을 수 없습니다. id=" + orderId);
        }
        return order;
    }
}
