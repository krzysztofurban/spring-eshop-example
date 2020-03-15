package pl.krzysztofurban.springeshopexample.repository.orders;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.krzysztofurban.springeshopexample.model.orders.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
