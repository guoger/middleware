package StockExchange.test;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class Run {
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(BareFunctionality.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        /*result = JUnitCore.runClasses(StartClient.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }*/
    }
} 
