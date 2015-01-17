package hamster.bonus;

import hamster.model.Partner;

public interface PartnerChooser {

    Partner get(BonusData data, String partner);

}
