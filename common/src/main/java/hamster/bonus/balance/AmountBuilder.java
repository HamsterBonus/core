package hamster.bonus.balance;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;

import com.google.common.base.Objects;
import com.google.common.base.Optional;

import hamster.bonus.model.Amount;

public class AmountBuilder {
	
	private Optional<Currency> currency;
	private Optional<BigDecimal> value;
	
	public static AmountBuilder create(){
		return new AmountBuilder();
	}
	
	public Amount build(){
		BigDecimal value = this.value.or(BigDecimal.ZERO);
		return new Amount(
				value.setScale(6, RoundingMode.HALF_EVEN),
				value.setScale(2, RoundingMode.HALF_EVEN),
				this.currency.or(getDefaultCurrency())
		);
	}
	
	public AmountBuilder value(long integer, long fraction){
		return value(integer, fraction, null);
	}
	
	public AmountBuilder value(long integer, long fraction, Currency currency){
		return value(Long.toString(integer), Long.toString(fraction), Optional.fromNullable(currency));
	}
	
	public AmountBuilder value(String integer, String fraction){
		return value(integer, fraction, Optional.<Currency>absent());
	}
	
	public AmountBuilder value(String integer, String fraction, Currency currency){
		return value(integer, fraction, Optional.fromNullable(currency));
	}
	
	private AmountBuilder value(String integer, String fraction, Optional<Currency> currency){
		Currency c = currency.or(getDefaultCurrency());
		if(c.getDefaultFractionDigits() <= 0){
			return value(Objects.firstNonNull(integer, "0"));
		}
		return currency(c).value(
				Objects.firstNonNull(integer, "0") 
				+ "."
				+ StringUtils.leftPad(Objects.firstNonNull(fraction, "0"), c.getDefaultFractionDigits(), '0')
		);
	}
	
	private Currency getDefaultCurrency(){
		return Currency.getInstance(Locale.getDefault());
	}
	
	public AmountBuilder value(String value){
		return value(Double.parseDouble(value));
	}
	
	public AmountBuilder value(double value){
		return value(BigDecimal.valueOf(value));
	}

	public AmountBuilder value(BigDecimal value){
		this.value = Optional.fromNullable(value);
		return this;
	}
	
	public AmountBuilder rub(){
		return currency("RUB");
	}

	public AmountBuilder usd(){
		return currency(Currency.getInstance(Locale.US));
	}
	
	public AmountBuilder currency(String currencyCode){
		return currency(Currency.getInstance(currencyCode));
	}
	
	public AmountBuilder currency(Currency currency){
		this.currency = Optional.fromNullable(currency);
		return null;
	}
}
