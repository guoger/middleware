package middleware.stockExchangeSII;

import org.omg.CORBA.*;

import middleware.getQuote.*;

public class RequestQuoteImpl extends RequestQuotePOA {
	GetQuote getQuote;
	public RequestQuoteImpl() {
		getQuote = new GetQuote();
	}

	public static void main(String[] args) {

	}

	@Override
	public double reqQuote(String tab) {
		if (tab.startsWith("DE")) {
      System.out.println(" [SERVER]\tGet quote by ID");
			return getQuote.getValueByID(tab);
		} else {
      System.out.println(" [SERVER]\tGet quote by Name");
			return getQuote.getValueByName(tab);
		}
	}

}
