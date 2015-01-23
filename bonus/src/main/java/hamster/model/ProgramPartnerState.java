package hamster.model;

import hamster.state.State;
import org.springframework.data.domain.Persistable;

public enum ProgramPartnerState implements Persistable<String>, State {
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
