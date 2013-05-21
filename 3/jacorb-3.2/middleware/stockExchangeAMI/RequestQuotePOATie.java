package middleware.stockExchangeAMI;

import org.omg.PortableServer.POA;

/**
 * Generated from IDL interface "RequestQuote".
 *
 * @author JacORB IDL compiler V 3.2, 07-Dec-2012
 * @version generated at May 21, 2013 5:55:54 PM
 */

public class RequestQuotePOATie
	extends RequestQuotePOA
{
	private RequestQuoteOperations _delegate;

	private POA _poa;
	public RequestQuotePOATie(RequestQuoteOperations delegate)
	{
		_delegate = delegate;
	}
	public RequestQuotePOATie(RequestQuoteOperations delegate, POA poa)
	{
		_delegate = delegate;
		_poa = poa;
	}
	public middleware.stockExchangeAMI.RequestQuote _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		middleware.stockExchangeAMI.RequestQuote __r = middleware.stockExchangeAMI.RequestQuoteHelper.narrow(__o);
		return __r;
	}
	public middleware.stockExchangeAMI.RequestQuote _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		middleware.stockExchangeAMI.RequestQuote __r = middleware.stockExchangeAMI.RequestQuoteHelper.narrow(__o);
		return __r;
	}
	public RequestQuoteOperations _delegate()
	{
		return _delegate;
	}
	public void _delegate(RequestQuoteOperations delegate)
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
	public double getQuote(java.lang.String tab) throws middleware.stockExchangeAMI.InvalidIndex
	{
		return _delegate.getQuote(tab);
	}

}
