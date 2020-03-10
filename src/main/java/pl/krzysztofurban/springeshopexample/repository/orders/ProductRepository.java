package pl.krzysztofurban.springeshopexample.repository.orders;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.krzysztofurban.springeshopexample.model.orders.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
