package hamster.model;

import com.google.common.base.Objects;

public class User extends AEntity{

	private static final long serialVersionUID = 1L;

	public User(String id) {
		super(id);
	}

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                        .add("id", getId())
                            .toString();
    }

}
