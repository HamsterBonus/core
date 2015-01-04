package hamster.model;

import org.apache.commons.lang3.StringUtils;

import com.google.common.base.Objects;

public abstract class AEntity implements Entity<String>{
	
	private static final long serialVersionUID = 1L;
	
	private final String id;
	
	public AEntity(String id) {
		this.id = id;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public boolean isNew() {
		return StringUtils.isEmpty(id);
	}

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

	@Override
	public boolean equals(Object obj) {
		if (this == obj){
			return true;
		}
		if (obj == null){
			return false;
		}
		if (getClass() != obj.getClass()){
			return false;
		}
		AEntity other = (AEntity) obj;
		return Objects.equal(id, other.id);
	}


}
