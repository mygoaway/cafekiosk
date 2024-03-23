package com.example.storage.domain.order;

import com.example.storage.domain.product.Product;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static com.example.storage.domain.product.ProductSellingStatus.SELLING;
import static com.example.storage.domain.product.ProductType.HANDMADE;

@SpringBootTest
class OrderTest {

    @DisplayName("주문 생성 시 주문 상태는 INIT 이다.")
    @Test
    void init() {
        // given
        List<Product> products = List.of(
                createProduct("001", 1000),
                createProduct("002", 3000)
        );

        // when
        Order order = Order.create(products, LocalDateTime.now());

        // then
        Assertions.assertThat(order.getOrderStatus()).isEqualTo(OrderStatus.INIT);
    }

    @DisplayName("주문 생성 시 상품 리스트에서 주문의 총 금액을 계산한다.")
    @Test
    void calculateTotalPrice() {
        // given
        List<Product> products = List.of(
                createProduct("001", 1000),
                createProduct("002", 3000)
        );

        // when
        Order order = Order.create(products, LocalDateTime.now());

        // then
        Assertions.assertThat(order.getTotalPrice()).isEqualTo(4000);
    }



    @DisplayName("주문 생성 시 주문 등록 시간을 기록한다.")
    @Test
    void registeredDateTime() {
        // given
        LocalDateTime registeredTime = LocalDateTime.now();
        List<Product> products = List.of(
                createProduct("001", 1000),
                createProduct("002", 3000)
        );

        // when
        Order order = Order.create(products, registeredTime);

        // then
        Assertions.assertThat(order.getRegisteredDateTime()).isEqualTo(registeredTime);
    }

    private Product createProduct(String productNumber, int price) {
        return Product.builder()
                .productNumber(productNumber)
                .type(HANDMADE)
                .price(price)
                .sellingStatus(SELLING)
                .name("메뉴 이름")
                .build();

    }
}