package hamster.dao;

import hamster.model.Balance;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BalanceDao extends PagingAndSortingRepository<Balance, String>{
}
