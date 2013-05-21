package middleware.stockExchangeOnewayCall;


/**
 * Generated from IDL interface "Request".
 *
 * @author JacORB IDL compiler V 3.2, 07-Dec-2012
 * @version generated at May 21, 2013 2:39:54 PM
 */

public abstract class RequestHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(RequestHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_interface_tc("IDL:middleware/stockExchangeOnewayCall/Request:1.0", "Request");
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final middleware.stockExchangeOnewayCall.Request s)
	{
			any.insert_Object(s);
	}
	public static middleware.stockExchangeOnewayCall.Request extract(final org.omg.CORBA.Any any)
	{
		return narrow(any.extract_Object()) ;
	}
	public static String id()
	{
		return "IDL:middleware/stockExchangeOnewayCall/Request:1.0";
	}
	public static Request read(final org.omg.CORBA.portable.InputStream in)
	{
		return narrow(in.read_Object(middleware.stockExchangeOnewayCall._RequestStub.class));
	}
	public static void write(final org.omg.CORBA.portable.OutputStream _out, final middleware.stockExchangeOnewayCall.Request s)
	{
		_out.write_Object(s);
	}
	public static middleware.stockExchangeOnewayCall.Request narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof middleware.stockExchangeOnewayCall.Request)
		{
			return (middleware.stockExchangeOnewayCall.Request)obj;
		}
		else if (obj._is_a("IDL:middleware/stockExchangeOnewayCall/Request:1.0"))
		{
			middleware.stockExchangeOnewayCall._RequestStub stub;
			stub = new middleware.stockExchangeOnewayCall._RequestStub();
			stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
			return stub;
		}
		else
		{
			throw new org.omg.CORBA.BAD_PARAM("Narrow failed");
		}
	}
	public static middleware.stockExchangeOnewayCall.Request unchecked_narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof middleware.stockExchangeOnewayCall.Request)
		{
			return (middleware.stockExchangeOnewayCall.Request)obj;
		}
		else
		{
			middleware.stockExchangeOnewayCall._RequestStub stub;
			stub = new middleware.stockExchangeOnewayCall._RequestStub();
			stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
			return stub;
		}
	}
}
