package com.example.cafekisokapi.api.service.order;

import com.example.cafekisokapi.mail.service.MailSendClient;
import com.example.cafekisokapi.order.service.OrderStatisticsService;
import com.example.storage.domain.history.mail.MailSendHistory;
import com.example.storage.domain.history.mail.MailSendHistoryRepository;
import com.example.storage.domain.order.Order;
import com.example.storage.domain.order.OrderRepository;
import com.example.storage.domain.orderProduct.OrderProductRepository;
import com.example.storage.domain.product.Product;
import com.example.storage.domain.product.ProductRepository;
import com.example.storage.domain.product.ProductType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static com.example.storage.domain.order.OrderStatus.PAYMENT_COMPLETED;
import static com.example.storage.domain.product.ProductSellingStatus.SELLING;
import static com.example.storage.domain.product.ProductType.HANDMADE;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
class OrderStatisticsServiceTest {

    @Autowired private OrderStatisticsService orderStatisticsService;
    @Autowired private OrderRepository orderRepository;
    @Autowired private ProductRepository productRepository;
    @Autowired private OrderProductRepository orderProductRepository;
    @Autowired private MailSendHistoryRepository mailSendHistoryRepository;
    @MockBean private MailSendClient mailSendClient; // Spring 띄울 때 Bean 붙임


    @AfterEach
    void tearDown() {
        orderProductRepository.deleteAllInBatch();
        orderRepository.deleteAllInBatch();
        productRepository.deleteAllInBatch();
        mailSendHistoryRepository.deleteAllInBatch();
    }

    @DisplayName("결제완료 주문들을 조회하여 매출 통계 메일을 전송한다.")
    @Test
    void sendOrderStatisticsMail() {
        // given
        LocalDateTime now = LocalDateTime.of(2023, 3, 5, 0, 0);
        Product product1 = createProduct(HANDMADE, "001", 1000);
        Product product2 = createProduct(HANDMADE, "002", 3000);
        Product product3 = createProduct(HANDMADE, "003", 5000);
        List<Product> products = List.of(product1, product2, product3);
        productRepository.saveAll(products);

        createPaymentCompletedOrder(LocalDateTime.of(2023, 3, 4, 23, 59, 59), products);
        createPaymentCompletedOrder(now, products);
        createPaymentCompletedOrder(LocalDateTime.of(2023, 3, 5, 23, 59, 59), products);
        createPaymentCompletedOrder(LocalDateTime.of(2023, 3, 6, 0, 0, 0), products);

        Mockito.when(mailSendClient.sendMail(any(String.class), any(String.class), any(String.class), any(String.class))).thenReturn(true);

        boolean result = orderStatisticsService.sendOrderStatisticsMail(LocalDate.of(2023, 3, 5), "mygoaway2@gmail.com");

        // then
        Assertions.assertThat(result).isTrue();
        List<MailSendHistory> mailSendHistory = mailSendHistoryRepository.findAllByToEmail("mygoaway2@gmail.com");
        Assertions.assertThat(mailSendHistory).hasSize(1)
                .extracting("contents")
                .contains("총 매출 합계는 18000원입니다.");
    }


    private Product createProduct(ProductType type, String productNumber, int price) {
        return Product.builder()
                .productNumber(productNumber)
                .type(type)
                .price(price)
                .sellingStatus(SELLING)
                .name("메뉴 이름")
                .build();
    }

    private Order createPaymentCompletedOrder(LocalDateTime now, List<Product> products) {
        Order order = Order.builder()
                .products(products)
                .orderStatus(PAYMENT_COMPLETED)
                .registeredDateTime(now)
                .build();

        return orderRepository.save(order);
    }
}