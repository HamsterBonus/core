package hamster.dao;

import hamster.model.ProgramPartner;
import org.springframework.data.repository.PagingAndSortingRepository;
import java.util.Collection;

public interface ProgramPartnerDao extends PagingAndSortingRepository<ProgramPartner, String> {

    Collection<ProgramPartner> findByPartner(String merchant);

}
