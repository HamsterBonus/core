package hamster.dao;

import hamster.model.Merchant;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface MerchantDao extends PagingAndSortingRepository<Merchant, String> {

}
