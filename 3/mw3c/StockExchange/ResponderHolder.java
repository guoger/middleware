package StockExchange;

/**
 * Generated from IDL interface "Responder".
 *
 * @author JacORB IDL compiler V 3.2, 07-Dec-2012
 * @version generated at May 22, 2013 4:05:51 AM
 */

public final class ResponderHolder	implements org.omg.CORBA.portable.Streamable{
	 public Responder value;
	public ResponderHolder()
	{
	}
	public ResponderHolder (final Responder initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return ResponderHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = ResponderHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		ResponderHelper.write (_out,value);
	}
}
