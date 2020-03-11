package pl.krzysztofurban.springeshopexample.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.krzysztofurban.springeshopexample.model.history.PurchaseHistory;
import pl.krzysztofurban.springeshopexample.model.orders.Order;
import pl.krzysztofurban.springeshopexample.model.orders.Product;
import pl.krzysztofurban.springeshopexample.repository.history.PurchaseHistoryRepository;
import pl.krzysztofurban.springeshopexample.repository.orders.OrderRepository;
import pl.krzysztofurban.springeshopexample.repository.orders.ProductRepository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {

  final OrderRepository orderRepository;
  final ProductRepository productRepository;
  final PurchaseHistoryRepository purchaseHistoryRepository;

  @Transactional
  public Boolean purchase(Long productId, Long customerId, Double quantity, Double price) {
    Boolean success = Boolean.TRUE;
    Order order = new Order();
    order.setCustomerId(customerId);
    order.setProductId(productId);
    order.setPrice(price);
    order.setQuantity(quantity);
    orderRepository.save(order);
    return success;
  }
  @Transactional
  public void saveHistory(Long customerId, Long productId) {
    PurchaseHistory ph = new PurchaseHistory();
    ph.setCustomerId(customerId);
    ph.setProductId(productId);
    ph.setCreatedDate(new Date());
  }

  public void registerNewProducts() {
    Product product = new Product();
    product.setName("Superb java");
    product.setPrice(400D);
    product.setQuantity(1D);
    productRepository.save(product);
  }

  public List<Product> findAll() {
    return productRepository.findAll();
  }
}
