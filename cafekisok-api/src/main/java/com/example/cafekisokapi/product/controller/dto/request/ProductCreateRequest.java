package com.example.cafekisokapi.product.controller.dto.request;

import com.example.storage.domain.product.Product;
import com.example.storage.domain.product.ProductSellingStatus;
import com.example.storage.domain.product.ProductType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@NoArgsConstructor
public class ProductCreateRequest {

    @NotNull(message = "상품 타입은 필수입니다.")
    private ProductType type;

    @NotNull(message = "상품 판매상태는 필수입니다.")
    private ProductSellingStatus sellingStatus;

    // String name -> 상품 이름은 20자 제한
    @NotBlank(message = "상품 이름은 필수입니다.") // "" "    " 둘다 거른다 => String은 무조건 @NotBlank로 적용하자
    //@NotNull => "" "  " => 통과
    //@NotEmpty => "" 거르고, "    "통과
    private String name;

    @Positive(message = "상품 가격은 양수여야 합니다.")
    private int price;

    @Builder
    public ProductCreateRequest(ProductType type, ProductSellingStatus sellingStatus, String name, int price) {
        this.type = type;
        this.sellingStatus = sellingStatus;
        this.name = name;
        this.price = price;
    }

    public Product toEntity(String nextProductNumber) {
        return Product.builder()
                .productNumber(nextProductNumber)
                .type(type)
                .sellingStatus(sellingStatus)
                .name(name)
                .price(price)
                .build();
    }
}
