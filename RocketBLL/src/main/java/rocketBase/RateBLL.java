package rocketBase;

import java.util.ArrayList;

import org.apache.poi.ss.formula.functions.FinanceLib;

import exceptions.RateException;
import rocketDomain.RateDomainModel;

public class RateBLL {

	private static RateDAL _RateDAL = new RateDAL();
	
	static double getRate(int GivenCreditScore) throws RateException {
		
		ArrayList<RateDomainModel> rates = RateDAL.getAllRates();
		int rateInt = rates.size();
		double Irate = 0;
		for (int i = 0; i < rateInt; i++) {
			if (rates.get(i).getiMinCreditScore() == GivenCreditScore)
				Irate = rates.get(i).getdInterestRate();
		}
		return Irate;
	}
	
	public static double getPayment(double r, double n, double p, double f, boolean t) {
		
		return FinanceLib.pmt(r, n, p, f, t);
		/*
		 * return FinanceLib.pmt(getRate(MortgageController.getCreditScore()), 
				(12 * MortgageController.getTerm()), MortgageController.getHouseCost(),
				0, true);
		 */
	}
}
