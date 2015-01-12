package hamster.model;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;

import java.math.BigDecimal;

public class BonusProgram extends AEntity {

    private static final long serialVersionUID = 1L;

    private final String description;
    private final BigDecimal percent;
    private final boolean canBeChanged;

    public BonusProgram(String id, String description, BigDecimal percent, boolean canBeChanged) {
        super(id);
        this.description = Preconditions.checkNotNull(description);
        this.percent = Preconditions.checkNotNull(percent);
        this.canBeChanged = canBeChanged;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPercent() {
        return percent;
    }

    public boolean isCanBeChanged() {
        return canBeChanged;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("id", getId())
                .add("description", description)
                .add("percent", percent)
                .add("canBeChanged", canBeChanged)
                .toString();
    }

}
