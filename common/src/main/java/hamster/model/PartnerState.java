package hamster.model;

import hamster.state.State;

public enum PartnerState implements State {
    ACTIVE,
    DISABLED,
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
