package middleware.stockExchangeSII;


/**
 * Generated from IDL interface "RequestQuote".
 *
 * @author JacORB IDL compiler V 2.3.1, 27-May-2009
 * @version generated at May 20, 2013 8:51:05 PM
 */

public final class RequestQuoteHelper
{
	public static void insert (final org.omg.CORBA.Any any, final middleware.stockExchangeSII.RequestQuote s)
	{
			any.insert_Object(s);
	}
	public static middleware.stockExchangeSII.RequestQuote extract(final org.omg.CORBA.Any any)
	{
		return narrow(any.extract_Object()) ;
	}
	public static org.omg.CORBA.TypeCode type()
	{
		return org.omg.CORBA.ORB.init().create_interface_tc("IDL:middleware/stockExchangeSII/RequestQuote:1.0", "RequestQuote");
	}
	public static String id()
	{
		return "IDL:middleware/stockExchangeSII/RequestQuote:1.0";
	}
	public static RequestQuote read(final org.omg.CORBA.portable.InputStream in)
	{
		return narrow(in.read_Object(middleware.stockExchangeSII._RequestQuoteStub.class));
	}
	public static void write(final org.omg.CORBA.portable.OutputStream _out, final middleware.stockExchangeSII.RequestQuote s)
	{
		_out.write_Object(s);
	}
	public static middleware.stockExchangeSII.RequestQuote narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof middleware.stockExchangeSII.RequestQuote)
		{
			return (middleware.stockExchangeSII.RequestQuote)obj;
		}
		else if (obj._is_a("IDL:middleware/stockExchangeSII/RequestQuote:1.0"))
		{
			middleware.stockExchangeSII._RequestQuoteStub stub;
			stub = new middleware.stockExchangeSII._RequestQuoteStub();
			stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
			return stub;
		}
		else
		{
			throw new org.omg.CORBA.BAD_PARAM("Narrow failed");
		}
	}
	public static middleware.stockExchangeSII.RequestQuote unchecked_narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof middleware.stockExchangeSII.RequestQuote)
		{
			return (middleware.stockExchangeSII.RequestQuote)obj;
		}
		else
		{
			middleware.stockExchangeSII._RequestQuoteStub stub;
			stub = new middleware.stockExchangeSII._RequestQuoteStub();
			stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
			return stub;
		}
	}
}
