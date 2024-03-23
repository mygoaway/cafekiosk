package com.example.storage.domain;

import com.example.storage.domain.product.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

import static com.example.storage.domain.product.ProductSellingStatus.*;
import static com.example.storage.domain.product.ProductType.BAKERY;
import static com.example.storage.domain.product.ProductType.HANDMADE;

//@Component
@RequiredArgsConstructor
public class InitDb {
    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.dbInit1();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {
        private final EntityManager em;

        public void dbInit1() {

            Product product1 = Product.builder()
                    .productNumber("001")
                    .type(HANDMADE)
                    .sellingStatus(SELLING)
                    .name("아메리카노")
                    .price(4000)
                    .build();
            em.persist(product1);

            Product product2 = Product.builder()
                    .productNumber("002")
                    .type(HANDMADE)
                    .sellingStatus(HOLD)
                    .name("카페라떼")
                    .price(4500)
                    .build();
            em.persist(product2);

            Product product3 = Product.builder()
                    .productNumber("003")
                    .type(BAKERY)
                    .sellingStatus(STOP_SELLING)
                    .name("크루아상")
                    .price(3500)
                    .build();
            em.persist(product3);
        }
    }
}


