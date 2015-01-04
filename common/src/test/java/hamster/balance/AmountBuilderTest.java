package hamster.balance;

import java.util.Currency;
import java.util.Locale;

import hamster.model.Amount;

import org.junit.Test;

import static org.junit.Assert.*;

public class AmountBuilderTest {

	@Test
	public void test1(){
		Amount amount = AmountBuilder.create()
						.value("1").currency(getCurrency())
						.add()
							.value("1")
							.multiply()
								.value("3")
								.build();
		assertEquals(4, amount.getValue().intValue());
		assertEquals(getCurrency(), amount.getCurrency());
	}

	@Test
	public void test2(){
		Amount amount = AmountBuilder.create()
						.value("1").currency(getCurrency())
						.add()
							.value("1")
							.and()
						.multiply()
							.value("3")
							.build();
		assertEquals(6, amount.getValue().intValue());
		assertEquals(getCurrency(), amount.getCurrency());
	}

	private Currency getCurrency(){
		return Currency.getInstance(Locale.US);
	}
}
