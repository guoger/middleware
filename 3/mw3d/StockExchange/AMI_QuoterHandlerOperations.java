package StockExchange;


/**
 * Generated from IDL interface "AMI_QuoterHandler".
 *
 * @author JacORB IDL compiler V 3.2, 07-Dec-2012
 * @version generated at May 22, 2013 4:07:03 AM
 */

public interface AMI_QuoterHandlerOperations
	extends org.omg.Messaging.ReplyHandlerOperations
{
	/* constants */
	/* operations  */
	void getQuoteByName(float ami_return_val);
	void getQuoteByName_excep(org.omg.Messaging.ExceptionHolder excep_holder);
	void getQuoteByID(float ami_return_val);
	void getQuoteByID_excep(org.omg.Messaging.ExceptionHolder excep_holder);
}
