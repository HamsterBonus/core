package hamster.bonus.balance;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collection;
import java.util.Currency;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;

import com.google.common.base.Objects;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.collect.Sets;

import hamster.bonus.model.Amount;

public class AmountBuilder<T extends AmountBuilder<T>> {
	
	protected Optional<Currency> currency = Optional.absent();
	protected Optional<BigDecimal> value = Optional.absent();
	private Collection<Calculation<?>> calcs;
	
	public static SimpleAmountBuilder create(){
		return new SimpleAmountBuilder();
	}

	public static SimpleAmountBuilder create(Amount amount){
		if(amount == null){
			return create();
		}
		return create().currency(amount.getCurrency()).value(amount.getValue());
	}
	
	private AmountBuilder(){
		calcs = Sets.newHashSet();
	}
	
	public Amount build(){
		BigDecimal value = getValue();
		return new Amount(
				value.setScale(6, RoundingMode.HALF_EVEN),
				value.setScale(2, RoundingMode.HALF_EVEN),
				getCurrency()
		);
	}
	
	public T value(long integer, long fraction){
		return value(integer, fraction, null);
	}
	
	public T value(long integer, long fraction, Currency currency){
		return value(Long.toString(integer), Long.toString(fraction), Optional.fromNullable(currency));
	}
	
	public T value(String integer, String fraction){
		return value(integer, fraction, Optional.<Currency>absent());
	}
	
	public T value(String integer, String fraction, Currency currency){
		return value(integer, fraction, Optional.fromNullable(currency));
	}
	
	private T value(String integer, String fraction, Optional<Currency> currency){
		Currency c = getCurrency();
		if(c.getDefaultFractionDigits() <= 0){
			return value(Objects.firstNonNull(integer, "0"));
		}
		return currency(c).value(
				Objects.firstNonNull(integer, "0") 
				+ "."
				+ StringUtils.leftPad(Objects.firstNonNull(fraction, "0"), c.getDefaultFractionDigits(), '0')
		);
	}
	
	protected Currency getCurrency(){
		return currency.or(Currency.getInstance(Locale.getDefault()));
	}
	
	protected BigDecimal getValue(){
		BigDecimal current = value.or(BigDecimal.ZERO);
		if(calcs.size() == 0){
			return current;
		}
		for(Calculation<?> calc : calcs){
			current = calc.calc(current, getCurrency());
		}
		return current;
	}
	
	public T value(String value){
		return value(Double.parseDouble(value));
	}
	
	public T value(double value){
		return value(BigDecimal.valueOf(value));
	}

	public T value(BigDecimal value){
		this.value = Optional.fromNullable(value);
		return (T) this;
	}
	
	public T rub(){
		return currency("RUB");
	}

	public T usd(){
		return currency(Currency.getInstance(Locale.US));
	}
	
	public T currency(String currencyCode){
		return currency(Currency.getInstance(currencyCode));
	}
	
	public T currency(Currency currency){
		this.currency = Optional.fromNullable(currency);
		return (T) this;
	}

	public Add add(){
		return addCalc(new Add(this));
	}

	public Add add(Amount amount){
		return amount(add(), amount);		
	}
	
	public Reduce reduce(){
		return addCalc(new Reduce(this));
	}

	public Reduce reduce(Amount amount){
		return amount(reduce(), amount);		
	}

	public Multiply multiply(){
		return addCalc(new Multiply(this));
	}

	public Multiply multiply(Amount amount){
		return amount(multiply(), amount);
	}

	private <C extends Calculation<C>> C addCalc(C calc){
		calcs.add(calc);
		return calc;
	}
	
	private <C extends Calculation<C>> C amount(C builder, Amount amount){
		if(amount == null){
			return builder;
		}
		return builder.currency(amount.getCurrency()).value(amount.getValue());
		
	}
	
	public static class SimpleAmountBuilder extends AmountBuilder<SimpleAmountBuilder>{
		
	}
	
	public static abstract class Calculation<C extends Calculation<C>> extends AmountBuilder<C> {
		
		private final AmountBuilder<?> parent;

		public Calculation(AmountBuilder<?> parent) {
			this.parent = Preconditions.checkNotNull(parent);
		}

		@Override
		public final Amount build() {
			return parent.build();
		}
		
		public BigDecimal calc(BigDecimal current, Currency currency){
			if(!this.currency.or(currency).equals(currency)){
				throw new IllegalStateException("Currencies are different");
			}
			return calc(current, getValue());
		}
		
		public AmountBuilder<?> and(){
			return parent;
		}
		
		protected abstract BigDecimal calc(BigDecimal current, BigDecimal value);

	}

	public static class Add extends Calculation<Add> {

		public Add(AmountBuilder<?> parent) {
			super(parent);
		}

		@Override
		protected BigDecimal calc(BigDecimal current, BigDecimal value) {
			return current.add(value);
		}
		
	}
	
	public static class Multiply extends Calculation<Multiply> {

		public Multiply(AmountBuilder<?> parent) {
			super(parent);
		}

		@Override
		protected BigDecimal calc(BigDecimal current, BigDecimal value) {
			return current.multiply(value);
		}
		
	}

	public static class Reduce extends Calculation<Reduce> {

		public Reduce(AmountBuilder<?> parent) {
			super(parent);
		}

		@Override
		protected BigDecimal calc(BigDecimal current, BigDecimal value) {
			return current.multiply(value.negate());
		}
		
	}

}
