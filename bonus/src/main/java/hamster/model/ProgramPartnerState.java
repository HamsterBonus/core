package hamster.model;

import hamster.state.State;

public enum ProgramPartnerState implements State {
    ACTIVE,
    CANCELLED,
    WAITED,
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
