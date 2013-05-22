package StockExchange.test;

import StockExchange.Client;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.junit.Test;
import org.junit.BeforeClass;
import org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class StartClient {
    @Test(expected = Exception.class)
    public void test_quote_by_name() {
        String args[] = {"ErNGlI"};
        Client cli = new Client();
        cli.main(args);
    }
}
