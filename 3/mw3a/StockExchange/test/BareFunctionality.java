package StockExchange.test;

import StockExchange.QuoterImpl;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.junit.Test;
import org.junit.BeforeClass;
import org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import java.io.PrintStream;
import java.io.OutputStream;

public class BareFunctionality {
    private static QuoterImpl quote;

    @BeforeClass
    public static void testSetup() {
        PrintStream originalStream = System.out;
        PrintStream dummyStream    = new PrintStream(new OutputStream(){
            public void write(int b) {
                //NO-OP
            }
        });
        System.setOut(dummyStream);
        quote = new QuoterImpl();
        System.setOut(originalStream);
    }
    @Test
    public void test_quote_by_name() {
        float ret = quote.getQuoteByName("adidas AG");
        assertEquals("could not get quote by name", 85.52, ret, 0.01);
        ret = quote.getQuoteByName("ernegli");
        assertEquals("quote for invalid name gave wrong response", -1, ret, 0.01);
    } 
    @Test
    public void test_quote_by_id() {
        float ret = quote.getQuoteByID("DE000A1EWWW0");
        assertEquals("could not get quote by ID", 85.52, ret, 0.01);
        ret = quote.getQuoteByID("ernegli");
        assertEquals("quote for invalid ID gave wrong response", -1, ret, 0.01);
    }
}
