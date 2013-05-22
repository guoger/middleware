package StockExchange.QuoterPackage;


/**
 * Generated from IDL exception "InvalidStockName".
 *
 * @author JacORB IDL compiler V 3.2, 07-Dec-2012
 * @version generated at May 22, 2013 4:07:03 AM
 */

public abstract class InvalidStockNameHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(InvalidStockNameHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_exception_tc(StockExchange.QuoterPackage.InvalidStockNameHelper.id(),"InvalidStockName",new org.omg.CORBA.StructMember[0]);
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final StockExchange.QuoterPackage.InvalidStockName s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static StockExchange.QuoterPackage.InvalidStockName extract (final org.omg.CORBA.Any any)
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
		return "IDL:StockExchange/Quoter/InvalidStockName:1.0";
	}
	public static StockExchange.QuoterPackage.InvalidStockName read (final org.omg.CORBA.portable.InputStream in)
	{
		String id = in.read_string();
		if (!id.equals(id())) throw new org.omg.CORBA.MARSHAL("wrong id: " + id);
		final StockExchange.QuoterPackage.InvalidStockName result = new StockExchange.QuoterPackage.InvalidStockName(id);
		return result;
	}
	public static void write (final org.omg.CORBA.portable.OutputStream out, final StockExchange.QuoterPackage.InvalidStockName s)
	{
		out.write_string(id());
	}
}
