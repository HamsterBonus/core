package hamster.bonus;

import com.google.common.base.Preconditions;
import hamster.dao.PartnerDao;
import hamster.dao.PartnerMerchantDao;
import hamster.model.Partner;
import hamster.model.PartnerMerchant;
import hamster.model.PartnerMerchantState;
import hamster.model.PartnerState;
import hamster.validation.ValidationException;
import org.apache.commons.lang3.StringUtils;

public class SimplePartnerChooser implements PartnerChooser {

    private PartnerDao partnerDao;
    private PartnerMerchantDao partnerMerchantDao;

    public SimplePartnerChooser(PartnerDao partnerDao, PartnerMerchantDao partnerMerchantDao) {
        this.partnerDao = Preconditions.checkNotNull(partnerDao);
        this.partnerMerchantDao = Preconditions.checkNotNull(partnerMerchantDao);
    }

    @Override
    public Partner get(BonusData data, String partnerId) {
        Partner partner = getAndCheckPartner(partnerId);
        if(StringUtils.isEmpty(data.getPayment().getMerchant())){
            return partner;
        }
        PartnerMerchant pm = partnerMerchantDao.findByParentAndMerchant(partner.getId(), data.getPayment().getMerchant());
        if(pm == null
                || !pm.isUseMerchantBalance()
                || !PartnerMerchantState.CONFIRMED.equals(pm.getState())){
            return partner;
        }
        return getAndCheckPartner(pm.getPartner());
    }

    private Partner getAndCheckPartner(String partner){
        return check(partnerDao.findOne(partner));
    }

    private Partner check(Partner partner){
        if(partner == null
                || !PartnerState.ACTIVE.equals(partner.getState())){
            throw new ValidationException("Partner id is incorrect");
        }
        return partner;
    }
}
