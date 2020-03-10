package pl.krzysztofurban.springeshopexample.repository.orders;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.krzysztofurban.springeshopexample.model.orders.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
