package middleware.stockExchangeAMI;

import org.omg.PortableServer.POA;

/**
 * Generated from IDL interface "AMI_RequestQuoteHandler".
 *
 * @author JacORB IDL compiler V 3.2, 07-Dec-2012
 * @version generated at May 21, 2013 5:55:54 PM
 */

public class AMI_RequestQuoteHandlerPOATie
	extends AMI_RequestQuoteHandlerPOA
{
	private AMI_RequestQuoteHandlerOperations _delegate;

	private POA _poa;
	public AMI_RequestQuoteHandlerPOATie(AMI_RequestQuoteHandlerOperations delegate)
	{
		_delegate = delegate;
	}
	public AMI_RequestQuoteHandlerPOATie(AMI_RequestQuoteHandlerOperations delegate, POA poa)
	{
		_delegate = delegate;
		_poa = poa;
	}
	public middleware.stockExchangeAMI.AMI_RequestQuoteHandler _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		middleware.stockExchangeAMI.AMI_RequestQuoteHandler __r = middleware.stockExchangeAMI.AMI_RequestQuoteHandlerHelper.narrow(__o);
		return __r;
	}
	public middleware.stockExchangeAMI.AMI_RequestQuoteHandler _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		middleware.stockExchangeAMI.AMI_RequestQuoteHandler __r = middleware.stockExchangeAMI.AMI_RequestQuoteHandlerHelper.narrow(__o);
		return __r;
	}
	public AMI_RequestQuoteHandlerOperations _delegate()
	{
		return _delegate;
	}
	public void _delegate(AMI_RequestQuoteHandlerOperations delegate)
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
	public void getQuote(double ami_return_val)
	{
_delegate.getQuote(ami_return_val);
	}

	public void getQuote_excep(org.omg.Messaging.ExceptionHolder excep_holder)
	{
_delegate.getQuote_excep(excep_holder);
	}

}
