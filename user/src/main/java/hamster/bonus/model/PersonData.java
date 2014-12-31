package hamster.bonus.model;

import com.google.common.base.Objects;

public class PersonData extends AEntity {

	private static final long serialVersionUID = 1L;

	public PersonData(String id) {
		super(id);
	}

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                        .add("id", getId())
                            .toString();
    }

}
