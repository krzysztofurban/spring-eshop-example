package pl.krzysztofurban.springeshopexample.bootstrap;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.krzysztofurban.springeshopexample.model.dto.CustomerDto;
import pl.krzysztofurban.springeshopexample.model.orders.Customer;
import pl.krzysztofurban.springeshopexample.repository.history.PurchaseHistoryRepository;
import pl.krzysztofurban.springeshopexample.repository.orders.CustomerRepository;
import pl.krzysztofurban.springeshopexample.repository.orders.OrderRepository;
import pl.krzysztofurban.springeshopexample.service.impl.CustomerService;
import pl.krzysztofurban.springeshopexample.service.impl.ProductService;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class TestDataLoader implements CommandLineRunner {
  private final ProductService productService;
  private final CustomerService customerService;
  private final OrderRepository orderRepository;
  private final PurchaseHistoryRepository purchaseHistoryRepository;
  private final CustomerRepository customerRepository;

  @Override
  public void run(String... args) throws Exception {
    customerService.registerNewCustomers();
    productService.registerNewProducts();
    productService.purchase(1L, 1L, 2D, 400D);
    productService.purchase(1L, 1L, 2D, 400D);
    productService.purchase(1L, 1L, 2D, 400D);
    productService.saveHistory(1L, 1L);
    log.info("Customers {}", customerService.findAll());
    log.info("Products {}", productService.findAll());
    log.info("Orders {}", orderRepository.findAll());
    log.info("PurchaseHistory {}", purchaseHistoryRepository.findAll());
    nPlusOneExample();
    printDashLine();
    fetchCustomerDtos();
  }

  private void printDashLine() {
    log.info("____________________________________________________________________________");
  }

  public void nPlusOneExample() {
    List<Customer> customerList = customerRepository.findCustomersWithOrderDetails();
    log.info("Customer List with Order Details: {}", customerList);
  }

  public void fetchCustomerDtos() {
    List<CustomerDto> customerDtos = customerRepository.findCustomersDto();
    log.info("CustomerDTOs: {}", customerDtos);
  }
}
