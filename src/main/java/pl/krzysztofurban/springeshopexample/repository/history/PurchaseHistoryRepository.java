package pl.krzysztofurban.springeshopexample.repository.history;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.krzysztofurban.springeshopexample.model.history.PurchaseHistory;

public interface PurchaseHistoryRepository extends JpaRepository<PurchaseHistory, Long> {
}
