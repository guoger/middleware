package middleware.stockExchangeOnewayCall;


/**
 * Generated from IDL interface "Reply".
 *
 * @author JacORB IDL compiler V 3.2, 07-Dec-2012
 * @version generated at May 21, 2013 2:39:54 PM
 */

public abstract class ReplyHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(ReplyHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_interface_tc("IDL:middleware/stockExchangeOnewayCall/Reply:1.0", "Reply");
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final middleware.stockExchangeOnewayCall.Reply s)
	{
			any.insert_Object(s);
	}
	public static middleware.stockExchangeOnewayCall.Reply extract(final org.omg.CORBA.Any any)
	{
		return narrow(any.extract_Object()) ;
	}
	public static String id()
	{
		return "IDL:middleware/stockExchangeOnewayCall/Reply:1.0";
	}
	public static Reply read(final org.omg.CORBA.portable.InputStream in)
	{
		return narrow(in.read_Object(middleware.stockExchangeOnewayCall._ReplyStub.class));
	}
	public static void write(final org.omg.CORBA.portable.OutputStream _out, final middleware.stockExchangeOnewayCall.Reply s)
	{
		_out.write_Object(s);
	}
	public static middleware.stockExchangeOnewayCall.Reply narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof middleware.stockExchangeOnewayCall.Reply)
		{
			return (middleware.stockExchangeOnewayCall.Reply)obj;
		}
		else if (obj._is_a("IDL:middleware/stockExchangeOnewayCall/Reply:1.0"))
		{
			middleware.stockExchangeOnewayCall._ReplyStub stub;
			stub = new middleware.stockExchangeOnewayCall._ReplyStub();
			stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
			return stub;
		}
		else
		{
			throw new org.omg.CORBA.BAD_PARAM("Narrow failed");
		}
	}
	public static middleware.stockExchangeOnewayCall.Reply unchecked_narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof middleware.stockExchangeOnewayCall.Reply)
		{
			return (middleware.stockExchangeOnewayCall.Reply)obj;
		}
		else
		{
			middleware.stockExchangeOnewayCall._ReplyStub stub;
			stub = new middleware.stockExchangeOnewayCall._ReplyStub();
			stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
			return stub;
		}
	}
}
