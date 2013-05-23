package middleware.stockExchangeDII;


/**
 * Generated from IDL interface "RequestQuote".
 *
 * @author JacORB IDL compiler V 3.2, 07-Dec-2012
 * @version generated at May 23, 2013 7:28:25 PM
 */

public class _RequestQuoteStub
	extends org.omg.CORBA.portable.ObjectImpl
	implements middleware.stockExchangeDII.RequestQuote
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	private String[] ids = {"IDL:middleware/stockExchangeDII/RequestQuote:1.0"};
	public String[] _ids()
	{
		return ids;
	}

	public final static java.lang.Class _opsClass = middleware.stockExchangeDII.RequestQuoteOperations.class;
	public double reqQuote(java.lang.String tab)
	{
		org.omg.CORBA.Request _request = _request( "reqQuote" );

		_request.set_return_type( org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(7)) );

		org.omg.CORBA.Any $tab = _request.add_in_arg();
		$tab.insert_string(tab);

		_request.invoke();

		java.lang.Exception _exception = _request.env().exception();
		if (_exception != null)
		{
			throw (org.omg.CORBA.SystemException) _exception;
		}

		double _result;
		_result = _request.return_value().extract_double();
		return _result;
	}

}
