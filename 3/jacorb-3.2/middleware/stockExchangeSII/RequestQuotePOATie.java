package middleware.stockExchangeSII;

import org.omg.PortableServer.POA;

/**
 * Generated from IDL interface "RequestQuote".
 *
 * @author JacORB IDL compiler V 2.3.1, 27-May-2009
 * @version generated at May 20, 2013 8:51:05 PM
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
	public middleware.stockExchangeSII.RequestQuote _this()
	{
		return middleware.stockExchangeSII.RequestQuoteHelper.narrow(_this_object());
	}
	public middleware.stockExchangeSII.RequestQuote _this(org.omg.CORBA.ORB orb)
	{
		return middleware.stockExchangeSII.RequestQuoteHelper.narrow(_this_object(orb));
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
	public double reqQuote(java.lang.String tab)
	{
		return _delegate.reqQuote(tab);
	}

}
