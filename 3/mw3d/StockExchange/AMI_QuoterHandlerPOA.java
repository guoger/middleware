package StockExchange;


/**
 * Generated from IDL interface "AMI_QuoterHandler".
 *
 * @author JacORB IDL compiler V 3.2, 07-Dec-2012
 * @version generated at May 22, 2013 4:07:03 AM
 */

public abstract class AMI_QuoterHandlerPOA
	extends org.omg.PortableServer.Servant
	implements org.omg.CORBA.portable.InvokeHandler, StockExchange.AMI_QuoterHandlerOperations
{
	static private final java.util.HashMap<String,Integer> m_opsHash = new java.util.HashMap<String,Integer>();
	static
	{
		m_opsHash.put ( "getQuoteByID_excep", Integer.valueOf(0));
		m_opsHash.put ( "getQuoteByID", Integer.valueOf(1));
		m_opsHash.put ( "getQuoteByName", Integer.valueOf(2));
		m_opsHash.put ( "getQuoteByName_excep", Integer.valueOf(3));
	}
	private String[] ids = {"IDL:StockExchange/AMI_QuoterHandler:1.0","IDL:omg.org/Messaging/ReplyHandler:1.0"};
	public StockExchange.AMI_QuoterHandler _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		StockExchange.AMI_QuoterHandler __r = StockExchange.AMI_QuoterHandlerHelper.narrow(__o);
		return __r;
	}
	public StockExchange.AMI_QuoterHandler _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		StockExchange.AMI_QuoterHandler __r = StockExchange.AMI_QuoterHandlerHelper.narrow(__o);
		return __r;
	}
	public org.omg.CORBA.portable.OutputStream _invoke(String method, org.omg.CORBA.portable.InputStream _input, org.omg.CORBA.portable.ResponseHandler handler)
		throws org.omg.CORBA.SystemException
	{
		org.omg.CORBA.portable.OutputStream _out = null;
		// do something
		// quick lookup of operation
		java.lang.Integer opsIndex = (java.lang.Integer)m_opsHash.get ( method );
		if ( null == opsIndex )
			throw new org.omg.CORBA.BAD_OPERATION(method + " not found");
		switch ( opsIndex.intValue() )
		{
			case 0: // getQuoteByID_excep
			{
				org.omg.Messaging.ExceptionHolder _arg0=(org.omg.Messaging.ExceptionHolder)((org.omg.CORBA_2_3.portable.InputStream)_input).read_value ("IDL:omg.org/Messaging/ExceptionHolder:1.0");
				_out = handler.createReply();
				getQuoteByID_excep(_arg0);
				break;
			}
			case 1: // getQuoteByID
			{
				float _arg0=_input.read_float();
				_out = handler.createReply();
				getQuoteByID(_arg0);
				break;
			}
			case 2: // getQuoteByName
			{
				float _arg0=_input.read_float();
				_out = handler.createReply();
				getQuoteByName(_arg0);
				break;
			}
			case 3: // getQuoteByName_excep
			{
				org.omg.Messaging.ExceptionHolder _arg0=(org.omg.Messaging.ExceptionHolder)((org.omg.CORBA_2_3.portable.InputStream)_input).read_value ("IDL:omg.org/Messaging/ExceptionHolder:1.0");
				_out = handler.createReply();
				getQuoteByName_excep(_arg0);
				break;
			}
		}
		return _out;
	}

	public String[] _all_interfaces(org.omg.PortableServer.POA poa, byte[] obj_id)
	{
		return ids;
	}
}
