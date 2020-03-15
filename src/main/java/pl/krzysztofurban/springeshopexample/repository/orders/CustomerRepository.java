package pl.krzysztofurban.springeshopexample.repository.orders;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.krzysztofurban.springeshopexample.model.dto.CustomerDto;
import pl.krzysztofurban.springeshopexample.model.orders.Customer;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
  @Query("Select c from Customer c left join fetch c.orders")
  List<Customer> findCustomersWithOrderDetails();

  //QueryExample with constructor mapping
  @Query("Select new pl.krzysztofurban.springeshopexample.model.dto.CustomerDto(c.customerId, c.name) from Customer c")
  List<CustomerDto> findCustomersDto();
}
