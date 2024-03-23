package com.example.storage.domain.orderProduct;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import com.example.storage.domain.BaseEntity;
import com.example.storage.domain.order.Order;
import com.example.storage.domain.product.Product;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class OrderProduct extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    public OrderProduct(Order order, Product product) {
        this.order = order;
        this.product = product;
    }
}
