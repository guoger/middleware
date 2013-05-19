package StockExchange;


/**
 * Generated from IDL interface "Quoter".
 *
 * @author JacORB IDL compiler V 3.2, 07-Dec-2012
 * @version generated at May 19, 2013 8:40:53 PM
 */

public abstract class QuoterHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(QuoterHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_interface_tc("IDL:StockExchange/Quoter:1.0", "Quoter");
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final StockExchange.Quoter s)
	{
			any.insert_Object(s);
	}
	public static StockExchange.Quoter extract(final org.omg.CORBA.Any any)
	{
		return narrow(any.extract_Object()) ;
	}
	public static String id()
	{
		return "IDL:StockExchange/Quoter:1.0";
	}
	public static Quoter read(final org.omg.CORBA.portable.InputStream in)
	{
		return narrow(in.read_Object(StockExchange._QuoterStub.class));
	}
	public static void write(final org.omg.CORBA.portable.OutputStream _out, final StockExchange.Quoter s)
	{
		_out.write_Object(s);
	}
	public static StockExchange.Quoter narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof StockExchange.Quoter)
		{
			return (StockExchange.Quoter)obj;
		}
		else if (obj._is_a("IDL:StockExchange/Quoter:1.0"))
		{
			StockExchange._QuoterStub stub;
			stub = new StockExchange._QuoterStub();
			stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
			return stub;
		}
		else
		{
			throw new org.omg.CORBA.BAD_PARAM("Narrow failed");
		}
	}
	public static StockExchange.Quoter unchecked_narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof StockExchange.Quoter)
		{
			return (StockExchange.Quoter)obj;
		}
		else
		{
			StockExchange._QuoterStub stub;
			stub = new StockExchange._QuoterStub();
			stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
			return stub;
		}
	}
}
