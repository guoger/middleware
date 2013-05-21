package middleware.stockExchangeOnewayCall;


/**
 * Generated from IDL interface "Request".
 *
 * @author JacORB IDL compiler V 3.2, 07-Dec-2012
 * @version generated at May 21, 2013 2:39:54 PM
 */

public abstract class RequestPOA
	extends org.omg.PortableServer.Servant
	implements org.omg.CORBA.portable.InvokeHandler, middleware.stockExchangeOnewayCall.RequestOperations
{
	static private final java.util.HashMap<String,Integer> m_opsHash = new java.util.HashMap<String,Integer>();
	static
	{
		m_opsHash.put ( "requestByTab", Integer.valueOf(0));
	}
	private String[] ids = {"IDL:middleware/stockExchangeOnewayCall/Request:1.0"};
	public middleware.stockExchangeOnewayCall.Request _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		middleware.stockExchangeOnewayCall.Request __r = middleware.stockExchangeOnewayCall.RequestHelper.narrow(__o);
		return __r;
	}
	public middleware.stockExchangeOnewayCall.Request _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		middleware.stockExchangeOnewayCall.Request __r = middleware.stockExchangeOnewayCall.RequestHelper.narrow(__o);
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
			case 0: // requestByTab
			{
				java.lang.String _arg0=_input.read_string();
				_out = handler.createReply();
				requestByTab(_arg0);
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
