package sample.cafekiosk.spring.domain.product;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ProductTypeTest {

    @DisplayName("상품 타입이 재고 관련 타입인지를 체크한다.")
    @Test
    void containsStockType1() {
        //given
        ProductType productType = ProductType.HANDMADE;

        //when
        boolean result = ProductType.containsStockType(productType);

        //then
        Assertions.assertThat(result).isFalse();
    }

    @DisplayName("상품 타입이 재고 관련 타입인지를 체크한다.")
    @Test
    void containsStockType2() {
        //given
        ProductType productType = ProductType.BAKERY;

        //when
        boolean result = ProductType.containsStockType(productType);

        //then
        Assertions.assertThat(result).isTrue();
    }

    @DisplayName("상품 타입이 재고 관련 타입인지를 체크한다. 단, @ParameterizedTest")
    @CsvSource({"HANDMADE,false", "BOTTLE,true", "BAKERY,true"})
    @ParameterizedTest
    void containsStockTypeWithParameterizedTest(ProductType type, boolean expected) {
        // when
        boolean result = ProductType.containsStockType(type);

        // then
        Assertions.assertThat(result).isEqualTo(expected);
    }
}