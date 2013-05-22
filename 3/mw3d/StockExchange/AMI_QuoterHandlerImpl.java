package StockExchange;

import org.omg.Messaging.ExceptionHolder;

public class AMI_QuoterHandlerImpl 
	extends AMI_QuoterHandlerPOA 
{
    public AMI_QuoterHandlerImpl() {
	}
	
    //@Override
	public void getQuoteByID(float ami_return_val) {
		System.out.println("getQuoteByID: " + ami_return_val);
	}
	
	//@Override
	public void getQuoteByName(float ami_return_val) {
		System.out.println("getQuoteByName: " + ami_return_val);
	}
		
    //@Override
	public void getQuoteByID_excep(ExceptionHolder excep_holder) {
		try {
			excep_holder.raise_exception();
		} catch(Exception ex) {
			System.out.println("getQuoteByID: "+ex);
		}
	}
	
	//@Override
	public void getQuoteByName_excep(ExceptionHolder excep_holder) {
		try {
			excep_holder.raise_exception();
		} catch(Exception ex) {
			System.out.println("getQuoteByName: "+ex);
		}
	}
}
