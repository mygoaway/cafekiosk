package com.example.cafekisokapi.product.service;

import com.example.cafekisokapi.product.controller.dto.request.ProductCreateRequest;
import com.example.cafekisokapi.product.service.response.ProductResponse;
import com.example.storage.domain.product.Product;
import com.example.storage.domain.product.ProductRepository;
import com.example.storage.domain.product.ProductSellingStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


/*
*  readOnly = true : 읽기전용
*  CRUD 에서 CUD 동작 X / only Read
*  JPA : CUD 스냅샷 저장, 변경감지 X (성능 향상)
*
*  CQRS - Command(CUD) / Read => Read 빈도수가 압도적으로 높음
* */

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductNumberFactory productNumberFactory;

    // 동시성 이슈
    // product_number 필드 unique Key 설정 -> 중복이면 시스템적으로 재시도 처리
    @Transactional
    public ProductResponse createProduct(ProductCreateRequest request) {
        String nextProductNumber = productNumberFactory.createNextProductNumber();

        Product product = request.toEntity(nextProductNumber);
        Product savedProduct = productRepository.save(product);

        return ProductResponse.of(savedProduct);
    }

    public List<ProductResponse> getSellingProducts() {
        List<Product> products = productRepository.findAllBySellingStatusIn(ProductSellingStatus.forDisplay());

        return products.stream()
                .map(product -> ProductResponse.of(product))
                .collect(Collectors.toList());
    }
}
