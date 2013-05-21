package middleware.stockExchangeAMI;

import org.omg.Messaging.ExceptionHolder;

public class AMI_RequestQuoteHandlerImpl 
	extends AMI_RequestQuoteHandlerPOA {
	@Override
	public void getQuote(double ami_return_val) {
		System.out.println(" [AMI_RQHImpl]\trequest reply: "+ami_return_val);
	}

	@Override
	public void getQuote_excep(ExceptionHolder excep_holder) {
		try {
			excep_holder.raise_exception();
		} catch(Exception ex) {
			System.out.println(" [AMI_RQHImpl]\trequest exception: "+ex);
		}
	}

}
