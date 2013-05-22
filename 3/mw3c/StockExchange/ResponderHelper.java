package StockExchange;


/**
 * Generated from IDL interface "Responder".
 *
 * @author JacORB IDL compiler V 3.2, 07-Dec-2012
 * @version generated at May 22, 2013 4:05:51 AM
 */

public abstract class ResponderHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(ResponderHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_interface_tc("IDL:StockExchange/Responder:1.0", "Responder");
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final StockExchange.Responder s)
	{
			any.insert_Object(s);
	}
	public static StockExchange.Responder extract(final org.omg.CORBA.Any any)
	{
		return narrow(any.extract_Object()) ;
	}
	public static String id()
	{
		return "IDL:StockExchange/Responder:1.0";
	}
	public static Responder read(final org.omg.CORBA.portable.InputStream in)
	{
		return narrow(in.read_Object(StockExchange._ResponderStub.class));
	}
	public static void write(final org.omg.CORBA.portable.OutputStream _out, final StockExchange.Responder s)
	{
		_out.write_Object(s);
	}
	public static StockExchange.Responder narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof StockExchange.Responder)
		{
			return (StockExchange.Responder)obj;
		}
		else if (obj._is_a("IDL:StockExchange/Responder:1.0"))
		{
			StockExchange._ResponderStub stub;
			stub = new StockExchange._ResponderStub();
			stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
			return stub;
		}
		else
		{
			throw new org.omg.CORBA.BAD_PARAM("Narrow failed");
		}
	}
	public static StockExchange.Responder unchecked_narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof StockExchange.Responder)
		{
			return (StockExchange.Responder)obj;
		}
		else
		{
			StockExchange._ResponderStub stub;
			stub = new StockExchange._ResponderStub();
			stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
			return stub;
		}
	}
}
