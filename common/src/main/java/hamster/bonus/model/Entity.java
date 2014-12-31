package hamster.bonus.model;

import java.io.Serializable;

import org.springframework.data.domain.Persistable;

public interface Entity<T extends Serializable> extends Persistable<T>{

}
