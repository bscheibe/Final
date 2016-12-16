package rocketBase;

import static org.junit.Assert.*;

import org.junit.Test;

import exceptions.RateException;

public class rate_test {

	@Test
	public void testGetRate() throws RateException {
		assert(RateBLL.getRate(1600) == 5.00);
	}
	
	@Test(expected = RateException.class)
	public void testRateException() throws RateException {
		RateBLL.getRate(1700);
	}

}
