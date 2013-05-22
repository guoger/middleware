package StockExchange;


/**
 * Generated from IDL interface "AMI_QuoterHandler".
 *
 * @author JacORB IDL compiler V 3.2, 07-Dec-2012
 * @version generated at May 22, 2013 4:07:03 AM
 */

public abstract class AMI_QuoterHandlerHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(AMI_QuoterHandlerHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_interface_tc("IDL:StockExchange/AMI_QuoterHandler:1.0", "AMI_QuoterHandler");
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final StockExchange.AMI_QuoterHandler s)
	{
			any.insert_Object(s);
	}
	public static StockExchange.AMI_QuoterHandler extract(final org.omg.CORBA.Any any)
	{
		return narrow(any.extract_Object()) ;
	}
	public static String id()
	{
		return "IDL:StockExchange/AMI_QuoterHandler:1.0";
	}
	public static AMI_QuoterHandler read(final org.omg.CORBA.portable.InputStream in)
	{
		return narrow(in.read_Object(StockExchange._AMI_QuoterHandlerStub.class));
	}
	public static void write(final org.omg.CORBA.portable.OutputStream _out, final StockExchange.AMI_QuoterHandler s)
	{
		_out.write_Object(s);
	}
	public static StockExchange.AMI_QuoterHandler narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof StockExchange.AMI_QuoterHandler)
		{
			return (StockExchange.AMI_QuoterHandler)obj;
		}
		else if (obj._is_a("IDL:StockExchange/AMI_QuoterHandler:1.0"))
		{
			StockExchange._AMI_QuoterHandlerStub stub;
			stub = new StockExchange._AMI_QuoterHandlerStub();
			stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
			return stub;
		}
		else
		{
			throw new org.omg.CORBA.BAD_PARAM("Narrow failed");
		}
	}
	public static StockExchange.AMI_QuoterHandler unchecked_narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof StockExchange.AMI_QuoterHandler)
		{
			return (StockExchange.AMI_QuoterHandler)obj;
		}
		else
		{
			StockExchange._AMI_QuoterHandlerStub stub;
			stub = new StockExchange._AMI_QuoterHandlerStub();
			stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
			return stub;
		}
	}
}
