package hamster.model;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;

public class Partner extends AEntity {

    private static final long serialVersionUID = 1L;

    private final PartnerState state;

    public Partner(String id, PartnerState state) {
        super(id);
        this.state = Preconditions.checkNotNull(state);
    }

    public PartnerState getState() {
        return state;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("id", getId())
                .add("state", state)
                .toString();
    }

}
