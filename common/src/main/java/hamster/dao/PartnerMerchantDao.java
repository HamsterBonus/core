package hamster.dao;

import hamster.model.PartnerMerchant;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PartnerMerchantDao extends PagingAndSortingRepository<PartnerMerchant, String>{

    PartnerMerchant findByParentAndMerchant(String parent, String merchant);

}
