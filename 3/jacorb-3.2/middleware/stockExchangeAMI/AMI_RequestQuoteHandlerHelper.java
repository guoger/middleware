package middleware.stockExchangeAMI;


/**
 * Generated from IDL interface "AMI_RequestQuoteHandler".
 *
 * @author JacORB IDL compiler V 3.2, 07-Dec-2012
 * @version generated at May 21, 2013 5:55:54 PM
 */

public abstract class AMI_RequestQuoteHandlerHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(AMI_RequestQuoteHandlerHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_interface_tc("IDL:middleware/stockExchangeAMI/AMI_RequestQuoteHandler:1.0", "AMI_RequestQuoteHandler");
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final middleware.stockExchangeAMI.AMI_RequestQuoteHandler s)
	{
			any.insert_Object(s);
	}
	public static middleware.stockExchangeAMI.AMI_RequestQuoteHandler extract(final org.omg.CORBA.Any any)
	{
		return narrow(any.extract_Object()) ;
	}
	public static String id()
	{
		return "IDL:middleware/stockExchangeAMI/AMI_RequestQuoteHandler:1.0";
	}
	public static AMI_RequestQuoteHandler read(final org.omg.CORBA.portable.InputStream in)
	{
		return narrow(in.read_Object(middleware.stockExchangeAMI._AMI_RequestQuoteHandlerStub.class));
	}
	public static void write(final org.omg.CORBA.portable.OutputStream _out, final middleware.stockExchangeAMI.AMI_RequestQuoteHandler s)
	{
		_out.write_Object(s);
	}
	public static middleware.stockExchangeAMI.AMI_RequestQuoteHandler narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof middleware.stockExchangeAMI.AMI_RequestQuoteHandler)
		{
			return (middleware.stockExchangeAMI.AMI_RequestQuoteHandler)obj;
		}
		else if (obj._is_a("IDL:middleware/stockExchangeAMI/AMI_RequestQuoteHandler:1.0"))
		{
			middleware.stockExchangeAMI._AMI_RequestQuoteHandlerStub stub;
			stub = new middleware.stockExchangeAMI._AMI_RequestQuoteHandlerStub();
			stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
			return stub;
		}
		else
		{
			throw new org.omg.CORBA.BAD_PARAM("Narrow failed");
		}
	}
	public static middleware.stockExchangeAMI.AMI_RequestQuoteHandler unchecked_narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof middleware.stockExchangeAMI.AMI_RequestQuoteHandler)
		{
			return (middleware.stockExchangeAMI.AMI_RequestQuoteHandler)obj;
		}
		else
		{
			middleware.stockExchangeAMI._AMI_RequestQuoteHandlerStub stub;
			stub = new middleware.stockExchangeAMI._AMI_RequestQuoteHandlerStub();
			stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
			return stub;
		}
	}
}
