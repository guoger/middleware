package middleware.stockExchangeAMI;


/**
 * Generated from IDL exception "InvalidIndex".
 *
 * @author JacORB IDL compiler V 3.2, 07-Dec-2012
 * @version generated at May 21, 2013 5:55:54 PM
 */

public abstract class InvalidIndexHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(InvalidIndexHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_exception_tc(middleware.stockExchangeAMI.InvalidIndexHelper.id(),"InvalidIndex",new org.omg.CORBA.StructMember[0]);
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final middleware.stockExchangeAMI.InvalidIndex s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static middleware.stockExchangeAMI.InvalidIndex extract (final org.omg.CORBA.Any any)
	{
		org.omg.CORBA.portable.InputStream in = any.create_input_stream();
		try
		{
			return read (in);
		}
		finally
		{
			try
			{
				in.close();
			}
			catch (java.io.IOException e)
			{
			throw new RuntimeException("Unexpected exception " + e.toString() );
			}
		}
	}

	public static String id()
	{
		return "IDL:middleware/stockExchangeAMI/InvalidIndex:1.0";
	}
	public static middleware.stockExchangeAMI.InvalidIndex read (final org.omg.CORBA.portable.InputStream in)
	{
		String id = in.read_string();
		if (!id.equals(id())) throw new org.omg.CORBA.MARSHAL("wrong id: " + id);
		final middleware.stockExchangeAMI.InvalidIndex result = new middleware.stockExchangeAMI.InvalidIndex(id);
		return result;
	}
	public static void write (final org.omg.CORBA.portable.OutputStream out, final middleware.stockExchangeAMI.InvalidIndex s)
	{
		out.write_string(id());
	}
}
