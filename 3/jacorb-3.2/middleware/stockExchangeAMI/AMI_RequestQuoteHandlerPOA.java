package middleware.stockExchangeAMI;


/**
 * Generated from IDL interface "AMI_RequestQuoteHandler".
 *
 * @author JacORB IDL compiler V 3.2, 07-Dec-2012
 * @version generated at May 21, 2013 5:55:54 PM
 */

public abstract class AMI_RequestQuoteHandlerPOA
	extends org.omg.PortableServer.Servant
	implements org.omg.CORBA.portable.InvokeHandler, middleware.stockExchangeAMI.AMI_RequestQuoteHandlerOperations
{
	static private final java.util.HashMap<String,Integer> m_opsHash = new java.util.HashMap<String,Integer>();
	static
	{
		m_opsHash.put ( "getQuote", Integer.valueOf(0));
		m_opsHash.put ( "getQuote_excep", Integer.valueOf(1));
	}
	private String[] ids = {"IDL:middleware/stockExchangeAMI/AMI_RequestQuoteHandler:1.0","IDL:omg.org/Messaging/ReplyHandler:1.0"};
	public middleware.stockExchangeAMI.AMI_RequestQuoteHandler _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		middleware.stockExchangeAMI.AMI_RequestQuoteHandler __r = middleware.stockExchangeAMI.AMI_RequestQuoteHandlerHelper.narrow(__o);
		return __r;
	}
	public middleware.stockExchangeAMI.AMI_RequestQuoteHandler _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		middleware.stockExchangeAMI.AMI_RequestQuoteHandler __r = middleware.stockExchangeAMI.AMI_RequestQuoteHandlerHelper.narrow(__o);
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
			case 0: // getQuote
			{
				double _arg0=_input.read_double();
				_out = handler.createReply();
				getQuote(_arg0);
				break;
			}
			case 1: // getQuote_excep
			{
				org.omg.Messaging.ExceptionHolder _arg0=(org.omg.Messaging.ExceptionHolder)((org.omg.CORBA_2_3.portable.InputStream)_input).read_value ("IDL:omg.org/Messaging/ExceptionHolder:1.0");
				_out = handler.createReply();
				getQuote_excep(_arg0);
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
