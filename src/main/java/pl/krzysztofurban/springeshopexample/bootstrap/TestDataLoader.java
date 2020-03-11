package pl.krzysztofurban.springeshopexample.bootstrap;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import pl.krzysztofurban.springeshopexample.repository.history.PurchaseHistoryRepository;
import pl.krzysztofurban.springeshopexample.repository.orders.CustomerRepository;
import pl.krzysztofurban.springeshopexample.repository.orders.OrderRepository;
import pl.krzysztofurban.springeshopexample.service.impl.CustomerService;
import pl.krzysztofurban.springeshopexample.service.impl.ProductService;

@Slf4j
@RequiredArgsConstructor
public class TestDataLoader implements CommandLineRunner {
  private ProductService productService;
  private CustomerService customerService;
  private OrderRepository orderRepository;
  private CustomerRepository customerRepository;
  private PurchaseHistoryRepository purchaseHistoryRepository;

  @Override
  public void run(String... args) throws Exception {
    customerService.registerNewCustomers();
    productService.registerNewProducts();
    productService.purchase(1L, 1L, 2D, 400D);
    productService.saveHistory(1L, 1L);
    log.info("Customers {}", customerService.findAll());
    log.info("Products {}", productService.findAll());
    log.info("Orders {}", orderRepository.findAll());
    log.info("PurchaseHistory {}", purchaseHistoryRepository.findAll());
  }
}
