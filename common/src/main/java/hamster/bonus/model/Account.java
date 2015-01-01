package hamster.bonus.model;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;

public class Account extends AEntity {

	private static final long serialVersionUID = 1L;

	private final String user;
	
	public Account(String id, String user) {
		super(id);
		this.user = Preconditions.checkNotNull(user);
	}

	public String getUser() {
		return user;
	}
	
    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                        .add("id", getId())
                        .add("user", user)
                            .toString();
    }


}
