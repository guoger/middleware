package middleware.stockExchangeOnewayCall;


/**
 * Generated from IDL interface "Reply".
 *
 * @author JacORB IDL compiler V 3.2, 07-Dec-2012
 * @version generated at May 21, 2013 2:39:54 PM
 */

public abstract class ReplyPOA
	extends org.omg.PortableServer.Servant
	implements org.omg.CORBA.portable.InvokeHandler, middleware.stockExchangeOnewayCall.ReplyOperations
{
	static private final java.util.HashMap<String,Integer> m_opsHash = new java.util.HashMap<String,Integer>();
	static
	{
		m_opsHash.put ( "replyWithQuote", Integer.valueOf(0));
	}
	private String[] ids = {"IDL:middleware/stockExchangeOnewayCall/Reply:1.0"};
	public middleware.stockExchangeOnewayCall.Reply _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		middleware.stockExchangeOnewayCall.Reply __r = middleware.stockExchangeOnewayCall.ReplyHelper.narrow(__o);
		return __r;
	}
	public middleware.stockExchangeOnewayCall.Reply _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		middleware.stockExchangeOnewayCall.Reply __r = middleware.stockExchangeOnewayCall.ReplyHelper.narrow(__o);
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
			case 0: // replyWithQuote
			{
				double _arg0=_input.read_double();
				_out = handler.createReply();
				replyWithQuote(_arg0);
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
