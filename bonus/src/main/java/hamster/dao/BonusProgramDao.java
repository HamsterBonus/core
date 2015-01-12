package hamster.dao;

import hamster.model.BonusProgram;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BonusProgramDao extends PagingAndSortingRepository<BonusProgram, String> {

}
