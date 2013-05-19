package StockExchange.QuoterPackage;

/**
 * Generated from IDL exception "InvalidStockName".
 *
 * @author JacORB IDL compiler V 3.2, 07-Dec-2012
 * @version generated at May 19, 2013 8:40:53 PM
 */

public final class InvalidStockNameHolder
	implements org.omg.CORBA.portable.Streamable
{
	public StockExchange.QuoterPackage.InvalidStockName value;

	public InvalidStockNameHolder ()
	{
	}
	public InvalidStockNameHolder(final StockExchange.QuoterPackage.InvalidStockName initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return StockExchange.QuoterPackage.InvalidStockNameHelper.type ();
	}
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = StockExchange.QuoterPackage.InvalidStockNameHelper.read(_in);
	}
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		StockExchange.QuoterPackage.InvalidStockNameHelper.write(_out, value);
	}
}
