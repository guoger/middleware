package StockExchange;


/**
 * Generated from IDL interface "Quoter".
 *
 * @author JacORB IDL compiler V 3.2, 07-Dec-2012
 * @version generated at May 19, 2013 8:40:53 PM
 */

public interface QuoterOperations
{
	/* constants */
	/* operations  */
	float getQuoteByName(java.lang.String stockName) throws StockExchange.QuoterPackage.InvalidStockName;
	float getQuoteByID(java.lang.String stockID) throws StockExchange.QuoterPackage.InvalidStockID;
}
