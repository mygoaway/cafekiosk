package sample.cafekiosk.spring.domain.orderProduct;

import org.springframework.data.jpa.repository.JpaRepository;
import sample.cafekiosk.spring.domain.order.Order;

public interface OrderProductRepository extends JpaRepository<OrderProduct, Long> {
}
