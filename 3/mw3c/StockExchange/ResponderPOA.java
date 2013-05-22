package StockExchange;


/**
 * Generated from IDL interface "Responder".
 *
 * @author JacORB IDL compiler V 3.2, 07-Dec-2012
 * @version generated at May 22, 2013 4:05:51 AM
 */

public abstract class ResponderPOA
	extends org.omg.PortableServer.Servant
	implements org.omg.CORBA.portable.InvokeHandler, StockExchange.ResponderOperations
{
	static private final java.util.HashMap<String,Integer> m_opsHash = new java.util.HashMap<String,Integer>();
	static
	{
		m_opsHash.put ( "respondQuote", Integer.valueOf(0));
	}
	private String[] ids = {"IDL:StockExchange/Responder:1.0"};
	public StockExchange.Responder _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		StockExchange.Responder __r = StockExchange.ResponderHelper.narrow(__o);
		return __r;
	}
	public StockExchange.Responder _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		StockExchange.Responder __r = StockExchange.ResponderHelper.narrow(__o);
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
			case 0: // respondQuote
			{
				float _arg0=_input.read_float();
				_out = handler.createReply();
				respondQuote(_arg0);
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
