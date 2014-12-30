package hamster.bonus.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;

public class Amount implements Serializable, Comparable<Amount> {

    private static final long serialVersionUID = 1L;

    private final BigDecimal calculationValue;
    private final BigDecimal userValue;
    private final Currency currency;

    public Amount(final BigDecimal value, Currency currency) {
        Preconditions.checkNotNull(value);
        this.userValue = value.setScale(2, RoundingMode.HALF_EVEN);
        this.calculationValue = value.setScale(6, RoundingMode.HALF_EVEN);
        this.currency = Preconditions.checkNotNull(currency);
    }

    public Currency getCurrency() {
        return currency;
    }

    public String getCurrencyCode() {
        return currency.getCurrencyCode();
    }

    public BigDecimal getValue() {
        return calculationValue;
    }

    public BigDecimal getUserValue() {
        return userValue;
    }

    public double getDoubleValue() {
        return userValue.doubleValue();
    }

    public boolean isSign() {
        return calculationValue.compareTo(BigDecimal.ZERO) >= 0;
    }

    public boolean isEmpty() {
        return calculationValue.compareTo(BigDecimal.ZERO) == 0;
    }

    public int compareTo(Amount o) {
        if (o == null) {
            return 1;
        }
        if (!currency.equals(o.getCurrency())) {
            throw new IllegalArgumentException("It's impossible to compare amounts with different currencies.");
        }
        return userValue.compareTo(o.getUserValue());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(currency, userValue);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Amount)) {
            return false;
        }
        Amount other = (Amount) obj;
        return Objects.equal(currency, other.currency)
                && Objects.equal(userValue, other.userValue);
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                        .addValue(userValue)
                        .addValue(calculationValue)
                        .addValue(currency)
                            .toString();
    }

}
