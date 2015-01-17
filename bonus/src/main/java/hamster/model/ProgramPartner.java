package hamster.model;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;

public class ProgramPartner extends AEntity {

    private static final long serialVersionUID = 1L;

    private final String program;
    private final String partner;
    private boolean byDefault;

    public ProgramPartner(String id, String program, String partner, boolean byDefault) {
        super(id);
        this.program = Preconditions.checkNotNull(program);
        this.partner = Preconditions.checkNotNull(partner);
        this.byDefault = byDefault;
    }

    public String getProgram() {
        return program;
    }

    public String getPartner() {
        return partner;
    }

    public boolean isByDefault() {
        return byDefault;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("id", getId())
                .add("program", program)
                .add("partner", partner)
                .add("default", byDefault)
                .toString();
    }

}
