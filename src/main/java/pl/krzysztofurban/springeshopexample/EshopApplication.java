package pl.krzysztofurban.springeshopexample;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.krzysztofurban.springeshopexample.model.orders.Product;
import pl.krzysztofurban.springeshopexample.repository.history.PurchaseHistoryRepository;
import pl.krzysztofurban.springeshopexample.repository.orders.CustomerRepository;
import pl.krzysztofurban.springeshopexample.repository.orders.OrderRepository;
import pl.krzysztofurban.springeshopexample.service.impl.CustomerService;
import pl.krzysztofurban.springeshopexample.service.impl.ProductService;

@SpringBootApplication
@Slf4j
public class EshopApplication {
  public static void main(String[] args) {
    SpringApplication.run(EshopApplication.class, args);
  }
}
