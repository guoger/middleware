package middleware.stockExchangeSII;


/**
 * Generated from IDL interface "RequestQuote".
 *
 * @author JacORB IDL compiler V 2.3.1, 27-May-2009
 * @version generated at May 20, 2013 8:51:05 PM
 */

public class _RequestQuoteStub
	extends org.omg.CORBA.portable.ObjectImpl
	implements middleware.stockExchangeSII.RequestQuote
{
	private String[] ids = {"IDL:middleware/stockExchangeSII/RequestQuote:1.0"};
	public String[] _ids()
	{
		return ids;
	}

	public final static java.lang.Class _opsClass = middleware.stockExchangeSII.RequestQuoteOperations.class;
	public double reqQuote(java.lang.String tab)
	{
		while(true)
		{
		if(! this._is_local())
		{
			org.omg.CORBA.portable.InputStream _is = null;
			org.omg.CORBA.portable.OutputStream _os = null;
			try
			{
				_os = _request( "reqQuote", true);
				_os.write_string(tab);
				_is = _invoke(_os);
				double _result = _is.read_double();
				return _result;
			}
			catch( org.omg.CORBA.portable.RemarshalException _rx ){}
			catch( org.omg.CORBA.portable.ApplicationException _ax )
			{
				String _id = _ax.getId();
					try
					{
							_ax.getInputStream().close();
					}
					catch (java.io.IOException e)
					{
					throw new RuntimeException("Unexpected exception " + e.toString() );
					}
				throw new RuntimeException("Unexpected exception " + _id );
			}
			finally
			{
				if (_os != null)
				{
					try
					{
						_os.close();
					}
					catch (java.io.IOException e)
					{
					throw new RuntimeException("Unexpected exception " + e.toString() );
					}
				}
				this._releaseReply(_is);
			}
		}
		else
		{
			org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke( "reqQuote", _opsClass );
			if( _so == null )
				throw new org.omg.CORBA.UNKNOWN("local invocations not supported!");
			RequestQuoteOperations _localServant = (RequestQuoteOperations)_so.servant;
			double _result;
			try
			{
				_result = _localServant.reqQuote(tab);
			}
			finally
			{
				_servant_postinvoke(_so);
			}
			return _result;
		}

		}

	}

}
