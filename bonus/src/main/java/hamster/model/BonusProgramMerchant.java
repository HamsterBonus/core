package hamster.model;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;

public class BonusProgramMerchant extends AEntity {

    private static final long serialVersionUID = 1L;

    private final String program;
    private final String merchant;
    private boolean byDefault;

    public BonusProgramMerchant(String id, String program, String merchant, boolean byDefault) {
        super(id);
        this.program = Preconditions.checkNotNull(program);
        this.merchant = Preconditions.checkNotNull(merchant);
        this.byDefault = byDefault;
    }

    public String getProgram() {
        return program;
    }

    public String getMerchant() {
        return merchant;
    }

    public boolean isByDefault() {
        return byDefault;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("id", getId())
                .add("program", program)
                .add("merchant", merchant)
                .add("default", byDefault)
                .toString();
    }

}
