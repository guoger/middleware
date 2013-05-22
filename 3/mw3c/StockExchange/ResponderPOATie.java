package StockExchange;

import org.omg.PortableServer.POA;

/**
 * Generated from IDL interface "Responder".
 *
 * @author JacORB IDL compiler V 3.2, 07-Dec-2012
 * @version generated at May 22, 2013 4:05:51 AM
 */

public class ResponderPOATie
	extends ResponderPOA
{
	private ResponderOperations _delegate;

	private POA _poa;
	public ResponderPOATie(ResponderOperations delegate)
	{
		_delegate = delegate;
	}
	public ResponderPOATie(ResponderOperations delegate, POA poa)
	{
		_delegate = delegate;
		_poa = poa;
	}
	public StockExchange.Responder _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		StockExchange.Responder __r = StockExchange.ResponderHelper.narrow(__o);
		return __r;
	}
	public StockExchange.Responder _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		StockExchange.Responder __r = StockExchange.ResponderHelper.narrow(__o);
		return __r;
	}
	public ResponderOperations _delegate()
	{
		return _delegate;
	}
	public void _delegate(ResponderOperations delegate)
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
	public void respondQuote(float quote)
	{
_delegate.respondQuote(quote);
	}

}
