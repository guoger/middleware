package middleware.stockExchangeAMI;


/**
 * Generated from IDL interface "RequestQuote".
 *
 * @author JacORB IDL compiler V 3.2, 07-Dec-2012
 * @version generated at May 21, 2013 5:55:54 PM
 */

public class _RequestQuoteStub
	extends org.omg.CORBA.portable.ObjectImpl
	implements middleware.stockExchangeAMI.RequestQuote
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	private String[] ids = {"IDL:middleware/stockExchangeAMI/RequestQuote:1.0"};
	public String[] _ids()
	{
		return ids;
	}

	public final static java.lang.Class _opsClass = middleware.stockExchangeAMI.RequestQuoteOperations.class;
	public double getQuote(java.lang.String tab) throws middleware.stockExchangeAMI.InvalidIndex
	{
		while(true)
		{
			if(! this._is_local())
			{
				org.omg.CORBA.portable.InputStream _is = null;
				org.omg.CORBA.portable.OutputStream _os = null;
				try
				{
					_os = _request( "getQuote", true);
					java.lang.String tmpResult0 = tab;
_os.write_string( tmpResult0 );
					_is = _invoke(_os);
					double _result = _is.read_double();
					return _result;
				}
				catch( org.omg.CORBA.portable.RemarshalException _rx )
					{
						continue;
					}
				catch( org.omg.CORBA.portable.ApplicationException _ax )
				{
					String _id = _ax.getId();
					try
					{
						if( _id.equals("IDL:middleware/stockExchangeAMI/InvalidIndex:1.0"))
						{
							throw middleware.stockExchangeAMI.InvalidIndexHelper.read(_ax.getInputStream());
						}
						else 
						{
							throw new RuntimeException("Unexpected exception " + _id );
						}
					}
					finally
					{
						try
						{
							_ax.getInputStream().close();
						}
						catch (java.io.IOException e)
						{
							throw new RuntimeException("Unexpected exception " + e.toString() );
						}
					}
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
			org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke( "getQuote", _opsClass );
			if( _so == null )
				continue;
			RequestQuoteOperations _localServant = (RequestQuoteOperations)_so.servant;
			double _result;
			try
			{
				_result = _localServant.getQuote(tab);
				if ( _so instanceof org.omg.CORBA.portable.ServantObjectExt) 
					((org.omg.CORBA.portable.ServantObjectExt)_so).normalCompletion();
				return _result;
			}
			catch (middleware.stockExchangeAMI.InvalidIndex ex) 
			{
				if ( _so instanceof org.omg.CORBA.portable.ServantObjectExt) 
					((org.omg.CORBA.portable.ServantObjectExt)_so).exceptionalCompletion(ex);
				throw ex;
			}
			catch (RuntimeException re) 
			{
				if ( _so instanceof org.omg.CORBA.portable.ServantObjectExt) 
					((org.omg.CORBA.portable.ServantObjectExt)_so).exceptionalCompletion(re);
				throw re;
			}
			catch (java.lang.Error err) 
			{
				if ( _so instanceof org.omg.CORBA.portable.ServantObjectExt) 
					((org.omg.CORBA.portable.ServantObjectExt)_so).exceptionalCompletion(err);
				throw err;
			}
			finally
			{
				_servant_postinvoke(_so);
			}
		}

		}

	}

	public void sendc_getQuote(AMI_RequestQuoteHandler ami_handler, java.lang.String tab)
	{
		while(true)
		{
			try
			{
				org.omg.CORBA.portable.OutputStream _os = _request( "getQuote", true);
				java.lang.String tmpResult1 = tab;
_os.write_string( tmpResult1 );
				((org.jacorb.orb.Delegate)_get_delegate()).invoke(this, _os, ami_handler);
				return;
			}
			catch( org.omg.CORBA.portable.RemarshalException _rx )
			{
			}
			catch( org.omg.CORBA.portable.ApplicationException _ax )
			{
			}
		}

	}

}
