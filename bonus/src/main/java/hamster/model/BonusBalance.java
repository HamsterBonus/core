package hamster.model;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;

public class BonusBalance extends AEntity {

    private static final long serialVersionUID = 1L;

    private final String programm;
    private final String balance;
    private final String user;

    public BonusBalance(String id, String programm, String balance, String user) {
        super(id);
        this.programm = Preconditions.checkNotNull(programm);
        this.balance = Preconditions.checkNotNull(balance);
        this.user = Preconditions.checkNotNull(user);
    }

    public String getProgramm() {
        return programm;
    }

    public String getBalance() {
        return balance;
    }

    public String getUser() {
        return user;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("id", getId())
                .add("programm", programm)
                .add("balance", balance)
                .add("user", user)
                .toString();
    }

}
