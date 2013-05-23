package middleware.test;

import middleware.stockExchangeSII.Client;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.junit.Test;
import org.junit.BeforeClass;
import org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class StartClient {
    @Test(expected = Exception.class)
    public void test_quote_by_name() throws Exception {
        String args[] = {"ServerIOR", "ErNGlI"};
        Client.start(args);
    }
}
