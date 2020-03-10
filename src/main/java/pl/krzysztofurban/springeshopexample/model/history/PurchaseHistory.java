package pl.krzysztofurban.springeshopexample.model.history;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "PurchaseHistory")
public class PurchaseHistory implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private Long customerId;
  private Long productId;
  @CreationTimestamp
  private Date createdDate;
}
