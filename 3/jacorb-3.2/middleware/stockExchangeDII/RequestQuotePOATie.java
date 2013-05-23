package middleware.stockExchangeDII;

import org.omg.PortableServer.POA;

/**
 * Generated from IDL interface "RequestQuote".
 *
 * @author JacORB IDL compiler V 3.2, 07-Dec-2012
 * @version generated at May 23, 2013 7:28:25 PM
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
	public middleware.stockExchangeDII.RequestQuote _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		middleware.stockExchangeDII.RequestQuote __r = middleware.stockExchangeDII.RequestQuoteHelper.narrow(__o);
		return __r;
	}
	public middleware.stockExchangeDII.RequestQuote _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		middleware.stockExchangeDII.RequestQuote __r = middleware.stockExchangeDII.RequestQuoteHelper.narrow(__o);
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
	public double reqQuote(java.lang.String tab)
	{
		return _delegate.reqQuote(tab);
	}

}
