package hamster.bonus;

import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import hamster.dao.BonusProgramDao;
import hamster.dao.ProgramPartnerDao;
import hamster.error.SystemException;
import hamster.model.BonusProgram;
import hamster.model.ProgramPartner;
import hamster.model.ProgramPartnerState;
import hamster.validation.ValidationException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.Collection;

public class SimpleProgramChooser implements ProgramChooser {

    private ProgramPartnerDao programPartnerDao;
    private BonusProgramDao bonusProgramDao;

    public SimpleProgramChooser(ProgramPartnerDao programPartnerDao, BonusProgramDao bonusProgramDao) {
        this.programPartnerDao = Preconditions.checkNotNull(programPartnerDao);
        this.bonusProgramDao = Preconditions.checkNotNull(bonusProgramDao);
    }

    @Override
    public BonusProgram get(BonusData data, String partner) {
        Collection<ProgramPartner> programs = programPartnerDao.findByPartner(partner);
        if(CollectionUtils.isEmpty(programs)){
            throw new SystemException("The list of bonus programs for partner " + partner + " is empty");
        }
        return get(programs, data);
    }

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
                },
                null
        );
        if(pm == null
                || !ProgramPartnerState.ACTIVE.equals(pm.getState())){
            throw new ValidationException("Can't choose active program");
        }
        //todo: check status
        return bonusProgramDao.findOne(pm.getProgram());
    }
}
