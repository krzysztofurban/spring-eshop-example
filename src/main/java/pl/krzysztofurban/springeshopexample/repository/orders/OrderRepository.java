package pl.krzysztofurban.springeshopexample.repository.orders;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.krzysztofurban.springeshopexample.model.orders.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
