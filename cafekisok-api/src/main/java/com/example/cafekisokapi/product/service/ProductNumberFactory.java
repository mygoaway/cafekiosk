package com.example.cafekisokapi.product.service;

import com.example.storage.domain.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductNumberFactory {

    private final ProductRepository productRepository;

    // private 메서드를 테스트 꼭 해야하나? 아니다. 근데 해당 메서드를 분리할 필요가 있는지 고민해보자.
    public String createNextProductNumber() {
        String latestProductNumber = productRepository.findLatestProduct();
        if(latestProductNumber == null) {
            return "001";
        }

        Integer latestProductNumberInt = Integer.valueOf(latestProductNumber);
        int nextProductNumberInt = latestProductNumberInt + 1;

        return String.format("%03d", nextProductNumberInt);
    }
}
