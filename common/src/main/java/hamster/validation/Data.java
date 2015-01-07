package hamster.validation;

import java.io.Serializable;

public interface Data<T extends Data> extends Serializable {

    T validate();

}
