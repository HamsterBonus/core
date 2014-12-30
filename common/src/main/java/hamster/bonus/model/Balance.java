package hamster.bonus.model;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Persistable;

import com.google.common.base.Objects;

public class Balance implements Persistable<String>{

	private static final long serialVersionUID = 1L;

	private final String id;
	private final Amount activeValue;
	private final Amount holdValue;
	
	public Balance(String id, Amount activeValue, Amount holdValue) {
		this.id = id;
		this.activeValue = activeValue;
		this.holdValue = holdValue;
	}

	@Override
	public String getId() {
		return id;
	}
	
	@Override
	public boolean isNew() {
		return StringUtils.isEmpty(id);
	}

	public Amount getActiveValue() {
		return activeValue;
	}

	public Amount getHoldValue() {
		return holdValue;
	}
	
    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Balance)) {
            return false;
        }
        Balance other = (Balance) obj;
        return Objects.equal(id, other.id);
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                        .add("id", id)
                        .add("activeValue", activeValue)
                        .add("holdValue", holdValue)
                            .toString();
    }
	
	
}
