package hamster.dao;

import hamster.model.Partner;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PartnerDao extends PagingAndSortingRepository<Partner, String>{
}
