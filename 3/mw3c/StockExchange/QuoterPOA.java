package StockExchange;


/**
 * Generated from IDL interface "Quoter".
 *
 * @author JacORB IDL compiler V 3.2, 07-Dec-2012
 * @version generated at May 22, 2013 4:05:51 AM
 */

public abstract class QuoterPOA
	extends org.omg.PortableServer.Servant
	implements org.omg.CORBA.portable.InvokeHandler, StockExchange.QuoterOperations
{
	static private final java.util.HashMap<String,Integer> m_opsHash = new java.util.HashMap<String,Integer>();
	static
	{
		m_opsHash.put ( "getQuoteByID", Integer.valueOf(0));
		m_opsHash.put ( "getQuoteByName", Integer.valueOf(1));
	}
	private String[] ids = {"IDL:StockExchange/Quoter:1.0"};
	public StockExchange.Quoter _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		StockExchange.Quoter __r = StockExchange.QuoterHelper.narrow(__o);
		return __r;
	}
	public StockExchange.Quoter _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		StockExchange.Quoter __r = StockExchange.QuoterHelper.narrow(__o);
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
			case 0: // getQuoteByID
			{
				java.lang.String _arg0=_input.read_string();
				_out = handler.createReply();
				getQuoteByID(_arg0);
				break;
			}
			case 1: // getQuoteByName
			{
				java.lang.String _arg0=_input.read_string();
				_out = handler.createReply();
				getQuoteByName(_arg0);
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
