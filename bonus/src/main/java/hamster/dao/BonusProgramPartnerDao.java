package hamster.dao;

import hamster.model.BonusProgramPartner;
import org.springframework.data.repository.PagingAndSortingRepository;
import java.util.Collection;

public interface BonusProgramPartnerDao extends PagingAndSortingRepository<BonusProgramPartner, String> {

    Collection<BonusProgramPartner> findByPartner(String merchant);

}
