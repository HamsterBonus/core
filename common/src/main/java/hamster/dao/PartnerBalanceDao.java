package hamster.dao;

import hamster.model.PartnerBalance;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Collection;

public interface PartnerBalanceDao extends PagingAndSortingRepository<PartnerBalance, String> {

    Collection<PartnerBalance> findByPartner(String partner);

}
