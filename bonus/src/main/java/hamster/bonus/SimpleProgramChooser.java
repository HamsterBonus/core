package hamster.bonus;

import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import hamster.dao.BonusProgramDao;
import hamster.model.BonusProgram;
import hamster.model.ProgramPartner;
import hamster.validation.ValidationException;
import org.apache.commons.lang3.StringUtils;

import java.util.Collection;

public class SimpleProgramChooser implements ProgramChooser {

    private BonusProgramDao bonusProgramDao;

    public SimpleProgramChooser(BonusProgramDao bonusProgramDao) {
        this.bonusProgramDao = Preconditions.checkNotNull(bonusProgramDao);
    }

    @Override
    public BonusProgram get(Collection<ProgramPartner> programs, final BonusData data) {
        ProgramPartner pm = Iterables.find(
                programs,
                new Predicate<ProgramPartner>() {
                    @Override
                    public boolean apply(ProgramPartner input) {
                        return StringUtils.isEmpty(data.getProgram())
                                ? input.isByDefault()
                                : data.getProgram().equals(input.getProgram());
                    }
                }
        );
        if(pm == null){
            throw new ValidationException("Can't choose active program");
        }
        //todo: check status
        return bonusProgramDao.findOne(pm.getProgram());
    }
}
