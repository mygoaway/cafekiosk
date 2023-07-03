package sample.cafekiosk.spring;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import sample.cafekiosk.spring.domain.product.Product;
import sample.cafekiosk.spring.domain.product.ProductSellingStatus;
import sample.cafekiosk.spring.domain.product.ProductType;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

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
                    .type(ProductType.HANDMADE)
                    .sellingStatus(ProductSellingStatus.SELLING)
                    .name("아메리카노")
                    .price(4000)
                    .build();
            em.persist(product1);

            Product product2 = Product.builder()
                    .productNumber("002")
                    .type(ProductType.HANDMADE)
                    .sellingStatus(ProductSellingStatus.HOLD)
                    .name("카페라떼")
                    .price(4500)
                    .build();
            em.persist(product2);

            Product product3 = Product.builder()
                    .productNumber("003")
                    .type(ProductType.BAKERY)
                    .sellingStatus(ProductSellingStatus.STOP_SELLING)
                    .name("크루아상")
                    .price(3500)
                    .build();
            em.persist(product3);
        }
    }
}


