package middleware.stockExchangeOnewayCall;

/**
 * Generated from IDL interface "Request".
 *
 * @author JacORB IDL compiler V 3.2, 07-Dec-2012
 * @version generated at May 21, 2013 2:39:54 PM
 */

public final class RequestHolder	implements org.omg.CORBA.portable.Streamable{
	 public Request value;
	public RequestHolder()
	{
	}
	public RequestHolder (final Request initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return RequestHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = RequestHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		RequestHelper.write (_out,value);
	}
}
