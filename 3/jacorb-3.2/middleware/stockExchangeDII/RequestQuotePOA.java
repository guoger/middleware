package middleware.stockExchangeDII;


/**
 * Generated from IDL interface "RequestQuote".
 *
 * @author JacORB IDL compiler V 3.2, 07-Dec-2012
 * @version generated at May 23, 2013 7:28:25 PM
 */

public abstract class RequestQuotePOA
	extends org.omg.PortableServer.Servant
	implements org.omg.CORBA.portable.InvokeHandler, middleware.stockExchangeDII.RequestQuoteOperations
{
	static private final java.util.HashMap<String,Integer> m_opsHash = new java.util.HashMap<String,Integer>();
	static
	{
		m_opsHash.put ( "reqQuote", Integer.valueOf(0));
	}
	private String[] ids = {"IDL:middleware/stockExchangeDII/RequestQuote:1.0"};
	public middleware.stockExchangeDII.RequestQuote _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		middleware.stockExchangeDII.RequestQuote __r = middleware.stockExchangeDII.RequestQuoteHelper.narrow(__o);
		return __r;
	}
	public middleware.stockExchangeDII.RequestQuote _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		middleware.stockExchangeDII.RequestQuote __r = middleware.stockExchangeDII.RequestQuoteHelper.narrow(__o);
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
