package pl.krzysztofurban.springeshopexample.repository.orders;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.krzysztofurban.springeshopexample.model.orders.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
