package hamster.model;

import com.google.common.base.Objects;
import com.google.common.collect.ImmutableList;

import java.util.Collection;

public class BonusProgram extends AEntity {

    private static final long serialVersionUID = 1L;

    private final Collection<String> merchants;

    public BonusProgram(String id, Collection<String> merchants) {
        super(id);
        if(merchants !=null){
            this.merchants = ImmutableList.copyOf(merchants);
        } else {
            this.merchants = null;
        }
    }

    public Collection<String> getMerchants() {
        return merchants;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("id", getId())
                .add("merchants", merchants)
                .toString();
    }

}
