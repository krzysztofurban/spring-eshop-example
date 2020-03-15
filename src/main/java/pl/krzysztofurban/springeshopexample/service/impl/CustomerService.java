package pl.krzysztofurban.springeshopexample.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pl.krzysztofurban.springeshopexample.model.orders.Customer;
import pl.krzysztofurban.springeshopexample.model.type.CustomerAddress;
import pl.krzysztofurban.springeshopexample.repository.orders.CustomerRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CustomerService {
  private final CustomerRepository customerRepository;

  @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
  public void registerNewCustomers() {
    CustomerAddress customerAddress = new CustomerAddress();
    customerAddress.setCity("WWashington");
    customerAddress.setCountry("USA");
    customerAddress.setStreetAddress("IDontKnowStreet 123");

    Customer customer = new Customer();
    customer.setName("Donald Trump");
    customer.setEmail("donald.trump@whitehouse.gov");
    customer.setPassword("password");
    customer.setCustomerAddress(customerAddress);
    customer.setDateAdded(LocalDateTime.now());
    customerRepository.saveAndFlush(customer);
    log.info("All registered customers: " + customerRepository.findAll());
  }

  public List<Customer> findAll() {
    return customerRepository.findAll();
  }
}
