package hamster.model;

import hamster.state.State;
import org.springframework.data.domain.Persistable;

public enum PartnerMerchantState implements Persistable<String>, State {
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
