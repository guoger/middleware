package middleware.stockExchangeOnewayCall;

/**
 * Generated from IDL interface "Reply".
 *
 * @author JacORB IDL compiler V 3.2, 07-Dec-2012
 * @version generated at May 21, 2013 2:39:54 PM
 */

public final class ReplyHolder	implements org.omg.CORBA.portable.Streamable{
	 public Reply value;
	public ReplyHolder()
	{
	}
	public ReplyHolder (final Reply initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return ReplyHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = ReplyHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		ReplyHelper.write (_out,value);
	}
}
