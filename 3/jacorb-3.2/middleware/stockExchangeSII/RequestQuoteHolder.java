package middleware.stockExchangeSII;

/**
 * Generated from IDL interface "RequestQuote".
 *
 * @author JacORB IDL compiler V 2.3.1, 27-May-2009
 * @version generated at May 20, 2013 8:51:05 PM
 */

public final class RequestQuoteHolder	implements org.omg.CORBA.portable.Streamable{
	 public RequestQuote value;
	public RequestQuoteHolder()
	{
	}
	public RequestQuoteHolder (final RequestQuote initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return RequestQuoteHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = RequestQuoteHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		RequestQuoteHelper.write (_out,value);
	}
}
