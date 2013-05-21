package middleware.stockExchangeAMI;


/**
 * Generated from IDL interface "AMI_RequestQuoteHandler".
 *
 * @author JacORB IDL compiler V 3.2, 07-Dec-2012
 * @version generated at May 21, 2013 5:55:54 PM
 */

public interface AMI_RequestQuoteHandlerOperations
	extends org.omg.Messaging.ReplyHandlerOperations
{
	/* constants */
	/* operations  */
	void getQuote(double ami_return_val);
	void getQuote_excep(org.omg.Messaging.ExceptionHolder excep_holder);
}
