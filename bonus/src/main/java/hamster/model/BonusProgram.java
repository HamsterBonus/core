package hamster.model;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;

public class BonusProgram extends AEntity {

    private static final long serialVersionUID = 1L;

    private final String description;

    public BonusProgram(String id, String description) {
        super(id);
        this.description = Preconditions.checkNotNull(description);
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("id", getId())
                .add("description", description)
                .toString();
    }

}
