package hamster.model;

import hamster.state.State;
import org.springframework.data.domain.Persistable;

public enum PartnerState implements Persistable<String>, State {
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
