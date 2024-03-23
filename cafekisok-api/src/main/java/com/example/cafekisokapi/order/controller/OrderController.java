package com.example.cafekisokapi.order.controller;


import com.example.cafekisokapi.order.controller.request.OrderCreateRequest;
import com.example.cafekisokapi.order.service.OrderService;
import com.example.cafekisokapi.order.service.response.OrderResponse;
import com.example.common.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/api/v1/orders/new")
    public ApiResponse<OrderResponse> createOrder(@Valid @RequestBody OrderCreateRequest request) {
        return ApiResponse.ok(orderService.createOrder(request.toServiceRequest(), LocalDateTime.now()));
    }

}
