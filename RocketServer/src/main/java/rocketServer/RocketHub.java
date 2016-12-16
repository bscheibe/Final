package rocketServer;

import java.io.IOException;

import exceptions.RateException;
import netgame.common.Hub;
import rocketBase.RateBLL;
import rocketData.LoanRequest;


public class RocketHub extends Hub {

	private RateBLL _RateBLL = new RateBLL();
	
	public RocketHub(int port) throws IOException {
		super(port);
	}

	@Override
	protected void messageReceived(int ClientID, Object message) {
		System.out.println("Message Received by Hub");
		
		if (message instanceof LoanRequest) {
			resetOutput();
			double payment = 0.0;
			LoanRequest lq = (LoanRequest) message;
			try {
				payment = RateBLL.getPayment(RateBLL.getRate(lq.getiCreditScore()),
						(12 * lq.getiTerm()), lq.getdAmount(),
						0, true);
			} catch (RateException e) {
				e.printStackTrace();
			}
			lq.setdPayment(payment);
			sendToAll(lq);
		}
	}
}
