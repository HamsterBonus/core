package hamster.dao;

import hamster.model.BonusProgramMerchant;
import org.springframework.data.repository.PagingAndSortingRepository;
import java.util.Collection;

public interface BonusProgramMerchantDao extends PagingAndSortingRepository<BonusProgramMerchant, String> {

    Collection<BonusProgramMerchant> findByMerchant(String merchant);

}
