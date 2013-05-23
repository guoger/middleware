package middleware.test;

import middleware.getQuote.GetQuote;

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
    private static GetQuote quote;

    @BeforeClass
    public static void testSetup() {
        quote = new GetQuote();
    }
    @Test
    public void test_quote_by_name() {
        double ret = quote.getValueByName("adidas AG");
        assertEquals("could not get quote by name", 85.52, ret, 0.01);
        ret = quote.getValueByName("ernegli");
        assertEquals("quote for invalid name gave wrong response", -1, ret, 0.01);
    } 
    @Test
    public void test_quote_by_id() {
        double ret = quote.getValueByID("DE000A1EWWW0");
        assertEquals("could not get quote by ID", 85.52, ret, 0.01);
        ret = quote.getValueByID("ernegli");
        assertEquals("quote for invalid ID gave wrong response", -1, ret, 0.01);
    }
}
