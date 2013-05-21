package middleware.stockExchangeOnewayCall;

import org.omg.PortableServer.POA;

/**
 * Generated from IDL interface "Request".
 *
 * @author JacORB IDL compiler V 3.2, 07-Dec-2012
 * @version generated at May 21, 2013 2:39:54 PM
 */

public class RequestPOATie
	extends RequestPOA
{
	private RequestOperations _delegate;

	private POA _poa;
	public RequestPOATie(RequestOperations delegate)
	{
		_delegate = delegate;
	}
	public RequestPOATie(RequestOperations delegate, POA poa)
	{
		_delegate = delegate;
		_poa = poa;
	}
	public middleware.stockExchangeOnewayCall.Request _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		middleware.stockExchangeOnewayCall.Request __r = middleware.stockExchangeOnewayCall.RequestHelper.narrow(__o);
		return __r;
	}
	public middleware.stockExchangeOnewayCall.Request _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		middleware.stockExchangeOnewayCall.Request __r = middleware.stockExchangeOnewayCall.RequestHelper.narrow(__o);
		return __r;
	}
	public RequestOperations _delegate()
	{
		return _delegate;
	}
	public void _delegate(RequestOperations delegate)
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
	public void requestByTab(java.lang.String tab)
	{
_delegate.requestByTab(tab);
	}

}
