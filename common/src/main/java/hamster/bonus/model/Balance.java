package hamster.bonus.model;

import com.google.common.base.Objects;

public class Balance extends AEntity {

	private static final long serialVersionUID = 1L;

	private final Amount activeValue;
	private final Amount holdValue;
	
	public Balance(String id, Amount activeValue, Amount holdValue) {
		super(id);
		this.activeValue = activeValue;
		this.holdValue = holdValue;
	}

	public Amount getActiveValue() {
		return activeValue;
	}

	public Amount getHoldValue() {
		return holdValue;
	}
	
    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                        .add("id", getId())
                        .add("activeValue", activeValue)
                        .add("holdValue", holdValue)
                            .toString();
    }
	
	
}
