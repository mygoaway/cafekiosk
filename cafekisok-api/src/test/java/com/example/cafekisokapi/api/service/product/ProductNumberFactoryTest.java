package com.example.cafekisokapi.api.service.product;

import com.example.cafekisokapi.product.service.ProductNumberFactory;
import com.example.storage.domain.product.Product;
import com.example.storage.domain.product.ProductRepository;
import com.example.storage.domain.product.ProductSellingStatus;
import com.example.storage.domain.product.ProductType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.example.storage.domain.product.ProductSellingStatus.SELLING;
import static com.example.storage.domain.product.ProductType.HANDMADE;

@SpringBootTest
class ProductNumberFactoryTest {
    @Autowired
    private ProductNumberFactory productNumberFactory;

    @Autowired
    private ProductRepository productRepository;

    @AfterEach
    void tearDown() {
        productRepository.deleteAllInBatch();
    }

    @DisplayName("조회한게 1개인 경우, 상품번호는 002이다.")
    @Test
    void createNextProductNumber() {
        // given
        Product product = createProduct("001", HANDMADE, SELLING, "아메리카노", 4000);
        productRepository.save(product);

        // when
        String result = productNumberFactory.createNextProductNumber();

        // then
        Assertions.assertThat(result).isEqualTo("002");
    }

    @DisplayName("조회한게 없는 경우, 상품번호는 001이다.")
    @Test
    void createNextProductNumberWithProductIsEmpty() {
        // when
        String result = productNumberFactory.createNextProductNumber();

        // then
        Assertions.assertThat(result).isEqualTo("001");
    }

    private Product createProduct(String productNumber, ProductType type, ProductSellingStatus sellingStatus, String name, int price) {
        return Product.builder()
                .productNumber(productNumber)
                .type(type)
                .sellingStatus(sellingStatus)
                .name(name)
                .price(price)
                .build();
    }
}