package StockExchange;

import org.omg.PortableServer.POA;

/**
 * Generated from IDL interface "AMI_QuoterHandler".
 *
 * @author JacORB IDL compiler V 3.2, 07-Dec-2012
 * @version generated at May 22, 2013 4:07:03 AM
 */

public class AMI_QuoterHandlerPOATie
	extends AMI_QuoterHandlerPOA
{
	private AMI_QuoterHandlerOperations _delegate;

	private POA _poa;
	public AMI_QuoterHandlerPOATie(AMI_QuoterHandlerOperations delegate)
	{
		_delegate = delegate;
	}
	public AMI_QuoterHandlerPOATie(AMI_QuoterHandlerOperations delegate, POA poa)
	{
		_delegate = delegate;
		_poa = poa;
	}
	public StockExchange.AMI_QuoterHandler _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		StockExchange.AMI_QuoterHandler __r = StockExchange.AMI_QuoterHandlerHelper.narrow(__o);
		return __r;
	}
	public StockExchange.AMI_QuoterHandler _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		StockExchange.AMI_QuoterHandler __r = StockExchange.AMI_QuoterHandlerHelper.narrow(__o);
		return __r;
	}
	public AMI_QuoterHandlerOperations _delegate()
	{
		return _delegate;
	}
	public void _delegate(AMI_QuoterHandlerOperations delegate)
	{
		_delegate = delegate;
	}
	public POA _default_POA()
	{
		if (_poa != null)
		{
			return _poa;
		}
		return super._default_POA();
	}
	public void getQuoteByID_excep(org.omg.Messaging.ExceptionHolder excep_holder)
	{
_delegate.getQuoteByID_excep(excep_holder);
	}

	public void getQuoteByID(float ami_return_val)
	{
_delegate.getQuoteByID(ami_return_val);
	}

	public void getQuoteByName(float ami_return_val)
	{
_delegate.getQuoteByName(ami_return_val);
	}

	public void getQuoteByName_excep(org.omg.Messaging.ExceptionHolder excep_holder)
	{
_delegate.getQuoteByName_excep(excep_holder);
	}

}
