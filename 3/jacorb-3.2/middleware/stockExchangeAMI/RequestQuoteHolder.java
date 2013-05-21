package middleware.stockExchangeAMI;

/**
 * Generated from IDL interface "RequestQuote".
 *
 * @author JacORB IDL compiler V 3.2, 07-Dec-2012
 * @version generated at May 21, 2013 5:55:54 PM
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
