package pl.krzysztofurban.springeshopexample.model.orders;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@Entity
@ToString(exclude = {"orders"})
@EqualsAndHashCode(exclude = {"orders"})
public class Customer implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long customerId;
  private String name, email, password;
  private LocalDateTime dateAdded;

  @JsonIgnore
  @OneToMany(fetch = FetchType.EAGER)
  @JoinColumn(name = "customerId")
  Set<Order> orders;
}
