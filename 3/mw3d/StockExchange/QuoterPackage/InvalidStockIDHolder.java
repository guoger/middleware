package StockExchange.QuoterPackage;

/**
 * Generated from IDL exception "InvalidStockID".
 *
 * @author JacORB IDL compiler V 3.2, 07-Dec-2012
 * @version generated at May 22, 2013 4:07:03 AM
 */

public final class InvalidStockIDHolder
	implements org.omg.CORBA.portable.Streamable
{
	public StockExchange.QuoterPackage.InvalidStockID value;

	public InvalidStockIDHolder ()
	{
	}
	public InvalidStockIDHolder(final StockExchange.QuoterPackage.InvalidStockID initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return StockExchange.QuoterPackage.InvalidStockIDHelper.type ();
	}
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = StockExchange.QuoterPackage.InvalidStockIDHelper.read(_in);
	}
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		StockExchange.QuoterPackage.InvalidStockIDHelper.write(_out, value);
	}
}
