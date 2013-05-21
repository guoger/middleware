package middleware.stockExchangeAMI;


/**
 * Generated from IDL interface "RequestQuote".
 *
 * @author JacORB IDL compiler V 3.2, 07-Dec-2012
 * @version generated at May 21, 2013 5:55:54 PM
 */

public abstract class RequestQuoteHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(RequestQuoteHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_interface_tc("IDL:middleware/stockExchangeAMI/RequestQuote:1.0", "RequestQuote");
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final middleware.stockExchangeAMI.RequestQuote s)
	{
			any.insert_Object(s);
	}
	public static middleware.stockExchangeAMI.RequestQuote extract(final org.omg.CORBA.Any any)
	{
		return narrow(any.extract_Object()) ;
	}
	public static String id()
	{
		return "IDL:middleware/stockExchangeAMI/RequestQuote:1.0";
	}
	public static RequestQuote read(final org.omg.CORBA.portable.InputStream in)
	{
		return narrow(in.read_Object(middleware.stockExchangeAMI._RequestQuoteStub.class));
	}
	public static void write(final org.omg.CORBA.portable.OutputStream _out, final middleware.stockExchangeAMI.RequestQuote s)
	{
		_out.write_Object(s);
	}
	public static middleware.stockExchangeAMI.RequestQuote narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof middleware.stockExchangeAMI.RequestQuote)
		{
			return (middleware.stockExchangeAMI.RequestQuote)obj;
		}
		else if (obj._is_a("IDL:middleware/stockExchangeAMI/RequestQuote:1.0"))
		{
			middleware.stockExchangeAMI._RequestQuoteStub stub;
			stub = new middleware.stockExchangeAMI._RequestQuoteStub();
			stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
			return stub;
		}
		else
		{
			throw new org.omg.CORBA.BAD_PARAM("Narrow failed");
		}
	}
	public static middleware.stockExchangeAMI.RequestQuote unchecked_narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof middleware.stockExchangeAMI.RequestQuote)
		{
			return (middleware.stockExchangeAMI.RequestQuote)obj;
		}
		else
		{
			middleware.stockExchangeAMI._RequestQuoteStub stub;
			stub = new middleware.stockExchangeAMI._RequestQuoteStub();
			stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
			return stub;
		}
	}
}
