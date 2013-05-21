package middleware.stockExchangeAMI;

/**
 * Generated from IDL exception "InvalidIndex".
 *
 * @author JacORB IDL compiler V 3.2, 07-Dec-2012
 * @version generated at May 21, 2013 5:55:54 PM
 */

public final class InvalidIndexHolder
	implements org.omg.CORBA.portable.Streamable
{
	public middleware.stockExchangeAMI.InvalidIndex value;

	public InvalidIndexHolder ()
	{
	}
	public InvalidIndexHolder(final middleware.stockExchangeAMI.InvalidIndex initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return middleware.stockExchangeAMI.InvalidIndexHelper.type ();
	}
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = middleware.stockExchangeAMI.InvalidIndexHelper.read(_in);
	}
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		middleware.stockExchangeAMI.InvalidIndexHelper.write(_out, value);
	}
}
