package StockExchange;


/**
 * Generated from IDL interface "Quoter".
 *
 * @author JacORB IDL compiler V 3.2, 07-Dec-2012
 * @version generated at May 19, 2013 8:40:53 PM
 */

public class _QuoterStub
	extends org.omg.CORBA.portable.ObjectImpl
	implements StockExchange.Quoter
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	private String[] ids = {"IDL:StockExchange/Quoter:1.0"};
	public String[] _ids()
	{
		return ids;
	}

	public final static java.lang.Class _opsClass = StockExchange.QuoterOperations.class;
	public float getQuoteByID(java.lang.String stockID) throws StockExchange.QuoterPackage.InvalidStockID
	{
		org.omg.CORBA.Request _request = _request( "getQuoteByID" );

		_request.set_return_type( org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(6)) );

		org.omg.CORBA.Any $stockID = _request.add_in_arg();
		$stockID.insert_string(stockID);

		_request.exceptions().add(StockExchange.QuoterPackage.InvalidStockIDHelper.type());

		_request.invoke();

		java.lang.Exception _exception = _request.env().exception();
		if (_exception != null)
		{
			if(_exception instanceof org.omg.CORBA.UnknownUserException)
			{
				org.omg.CORBA.UnknownUserException _userException = (org.omg.CORBA.UnknownUserException) _exception;
				if (_userException.except.type().equals(StockExchange.QuoterPackage.InvalidStockIDHelper.type()))
				{
					throw StockExchange.QuoterPackage.InvalidStockIDHelper.extract(_userException.except);
				}
				else
				{
					throw new org.omg.CORBA.UNKNOWN();
				}
			}
			throw (org.omg.CORBA.SystemException) _exception;
		}

		float _result;
		_result = _request.return_value().extract_float();
		return _result;
	}

	public float getQuoteByName(java.lang.String stockName) throws StockExchange.QuoterPackage.InvalidStockName
	{
		org.omg.CORBA.Request _request = _request( "getQuoteByName" );

		_request.set_return_type( org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(6)) );

		org.omg.CORBA.Any $stockName = _request.add_in_arg();
		$stockName.insert_string(stockName);

		_request.exceptions().add(StockExchange.QuoterPackage.InvalidStockNameHelper.type());

		_request.invoke();

		java.lang.Exception _exception = _request.env().exception();
		if (_exception != null)
		{
			if(_exception instanceof org.omg.CORBA.UnknownUserException)
			{
				org.omg.CORBA.UnknownUserException _userException = (org.omg.CORBA.UnknownUserException) _exception;
				if (_userException.except.type().equals(StockExchange.QuoterPackage.InvalidStockNameHelper.type()))
				{
					throw StockExchange.QuoterPackage.InvalidStockNameHelper.extract(_userException.except);
				}
				else
				{
					throw new org.omg.CORBA.UNKNOWN();
				}
			}
			throw (org.omg.CORBA.SystemException) _exception;
		}

		float _result;
		_result = _request.return_value().extract_float();
		return _result;
	}

}
