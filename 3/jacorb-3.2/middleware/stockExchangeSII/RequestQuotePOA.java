package middleware.stockExchangeSII;


/**
 * Generated from IDL interface "RequestQuote".
 *
 * @author JacORB IDL compiler V 2.3.1, 27-May-2009
 * @version generated at May 20, 2013 8:51:05 PM
 */

public abstract class RequestQuotePOA
	extends org.omg.PortableServer.Servant
	implements org.omg.CORBA.portable.InvokeHandler, middleware.stockExchangeSII.RequestQuoteOperations
{
	static private final java.util.Hashtable m_opsHash = new java.util.Hashtable();
	static
	{
		m_opsHash.put ( "reqQuote", new java.lang.Integer(0));
	}
	private String[] ids = {"IDL:middleware/stockExchangeSII/RequestQuote:1.0"};
	public middleware.stockExchangeSII.RequestQuote _this()
	{
		return middleware.stockExchangeSII.RequestQuoteHelper.narrow(_this_object());
	}
	public middleware.stockExchangeSII.RequestQuote _this(org.omg.CORBA.ORB orb)
	{
		return middleware.stockExchangeSII.RequestQuoteHelper.narrow(_this_object(orb));
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
			case 0: // reqQuote
			{
				java.lang.String _arg0=_input.read_string();
				_out = handler.createReply();
				_out.write_double(reqQuote(_arg0));
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
