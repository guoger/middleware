package middleware.stockExchangeAMI;


/**
 * Generated from IDL interface "RequestQuote".
 *
 * @author JacORB IDL compiler V 3.2, 07-Dec-2012
 * @version generated at May 21, 2013 5:55:54 PM
 */

public abstract class RequestQuotePOA
	extends org.omg.PortableServer.Servant
	implements org.omg.CORBA.portable.InvokeHandler, middleware.stockExchangeAMI.RequestQuoteOperations
{
	static private final java.util.HashMap<String,Integer> m_opsHash = new java.util.HashMap<String,Integer>();
	static
	{
		m_opsHash.put ( "getQuote", Integer.valueOf(0));
	}
	private String[] ids = {"IDL:middleware/stockExchangeAMI/RequestQuote:1.0"};
	public middleware.stockExchangeAMI.RequestQuote _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		middleware.stockExchangeAMI.RequestQuote __r = middleware.stockExchangeAMI.RequestQuoteHelper.narrow(__o);
		return __r;
	}
	public middleware.stockExchangeAMI.RequestQuote _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		middleware.stockExchangeAMI.RequestQuote __r = middleware.stockExchangeAMI.RequestQuoteHelper.narrow(__o);
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
			try
			{
				java.lang.String _arg0=_input.read_string();
				_out = handler.createReply();
				_out.write_double(getQuote(_arg0));
			}
			catch(middleware.stockExchangeAMI.InvalidIndex _ex0)
			{
				_out = handler.createExceptionReply();
				middleware.stockExchangeAMI.InvalidIndexHelper.write(_out, _ex0);
			}
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
