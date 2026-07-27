package org.example.commerceplatform.order.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
@Table(name = "order_items")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @Column(nullable = false)
    private Long productId;

    @Column(nullable = false)
    private Long price;       // 주문 시점 가격 스냅샷

    @Column(nullable = false)
    private Integer quantity;

    protected OrderItem() {}

    public OrderItem(Order order, Long productId, Long price, Integer quantity) {
        this.order = order;
        this.productId = productId;
        this.price = price;
        this.quantity = quantity;
    }
}
