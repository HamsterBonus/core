package hamster.dao;

import hamster.model.PaymentBonus;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PaymentBonusDao extends PagingAndSortingRepository<PaymentBonus, String>{
}
