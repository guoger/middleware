package middleware.test;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import java.io.PrintStream;
import java.io.OutputStream;

public class Run {
    public static void main(String[] args) {
        System.out.println("start tests");
        // silence
        //PrintStream originalStream = System.out;
        //PrintStream dummyStream    = new PrintStream(new OutputStream(){
        //    public void write(int b) {
        //        //NO-OP
        //    }
        //});
        //System.setOut(dummyStream);
        // call function
        Result result = JUnitCore.runClasses(BareFunctionality.class);
        Result result2 = JUnitCore.runClasses(StartClient.class);
        // regain voice
        //System.setOut(originalStream);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        System.out.println(String.format("Test 1: %d of %d succeeded",result.getRunCount() - result.getFailureCount(), result.getRunCount()));
        for (Failure failure : result2.getFailures()) {
            System.out.println(failure.toString());
        }
        System.out.println(String.format("Test 2: %d of %d succeeded",result2.getRunCount() - result2.getFailureCount(), result2.getRunCount()));
        System.out.println("tests ended");
    }
} 
