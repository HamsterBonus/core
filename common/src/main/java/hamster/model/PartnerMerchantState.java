package hamster.model;

import hamster.state.State;

public enum PartnerMerchantState implements State {
    WAITED_FOR_PARENT,
    WAITED_FOR_MERCHANT,
    CONFIRMED,
    ;

    @Override
    public String getId() {
        return this.name();
    }

    @Override
    public boolean isNew() {
        return false;
    }
}
