package middleware.stockExchangeAMI;

import middleware.getQuote.*;

public class RequestQuoteImpl extends RequestQuotePOA {
	GetQuote getQuote;
	public RequestQuoteImpl() {
		getQuote = new GetQuote();
	}

	@Override
	public double getQuote(String tab) {
		if (tab.startsWith("DE")) {
      System.out.println(" [SERVER]\tGet quote by ID");
			return getQuote.getValueByID(tab);
		} else {
      System.out.println(" [SERVER]\tGet quote by Name");
			return getQuote.getValueByName(tab);
		}
	}
}
