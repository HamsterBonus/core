package hamster.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Currency;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;

public class Amount implements Serializable, Comparable<Amount> {

    private static final long serialVersionUID = 1L;

    private final BigDecimal value;
    private final BigDecimal displayValue;
    private final Currency currency;

    public Amount(BigDecimal value, Currency currency) {
    	this(value, value, currency);
    }

    public Amount(BigDecimal value, BigDecimal displayValue, Currency currency) {
        this.displayValue = Preconditions.checkNotNull(displayValue);
        this.value = Preconditions.checkNotNull(value);
        this.currency = Preconditions.checkNotNull(currency);
    }

    public Currency getCurrency() {
        return currency;
    }

    public String getCurrencyCode() {
        return currency.getCurrencyCode();
    }

    public BigDecimal getValue() {
        return value;
    }

    public BigDecimal getDisplayValue() {
        return displayValue;
    }

    public double getDoubleValue() {
        return displayValue.doubleValue();
    }

    public boolean isSign() {
        return value.compareTo(BigDecimal.ZERO) >= 0;
    }

    public boolean isEmpty() {
        return value.compareTo(BigDecimal.ZERO) == 0;
    }

    public int compareTo(Amount o) {
        if (o == null) {
            return 1;
        }
        if (!currency.equals(o.getCurrency())) {
            throw new IllegalArgumentException("It's impossible to compare amounts with different currencies.");
        }
        return value.compareTo(o.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(currency, value);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Amount)) {
            return false;
        }
        Amount other = (Amount) obj;
        return Objects.equal(currency, other.currency)
                && Objects.equal(value, other.value);
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                        .add("displayValue", displayValue)
                        .add("value", value)
                        .add("currency", currency)
                            .toString();
    }

}
