package middleware.stockExchangeOnewayCall;

import org.omg.PortableServer.POA;

/**
 * Generated from IDL interface "Reply".
 *
 * @author JacORB IDL compiler V 3.2, 07-Dec-2012
 * @version generated at May 21, 2013 2:39:54 PM
 */

public class ReplyPOATie
	extends ReplyPOA
{
	private ReplyOperations _delegate;

	private POA _poa;
	public ReplyPOATie(ReplyOperations delegate)
	{
		_delegate = delegate;
	}
	public ReplyPOATie(ReplyOperations delegate, POA poa)
	{
		_delegate = delegate;
		_poa = poa;
	}
	public middleware.stockExchangeOnewayCall.Reply _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		middleware.stockExchangeOnewayCall.Reply __r = middleware.stockExchangeOnewayCall.ReplyHelper.narrow(__o);
		return __r;
	}
	public middleware.stockExchangeOnewayCall.Reply _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		middleware.stockExchangeOnewayCall.Reply __r = middleware.stockExchangeOnewayCall.ReplyHelper.narrow(__o);
		return __r;
	}
	public ReplyOperations _delegate()
	{
		return _delegate;
	}
	public void _delegate(ReplyOperations delegate)
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
	public void replyWithQuote(double quote)
	{
_delegate.replyWithQuote(quote);
	}

}
