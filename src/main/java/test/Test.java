// ----------------------------------------------------------------------------
// Test
// Simple test function - compares actual and expected values as strings.
// Uses string-based comparison which works for most things.
//
// @author Brian Burns <bburns.km@gmail.com>
// ----------------------------------------------------------------------------
// This is something in between Java assert and JUnit - gives more information
// than assert, but less verbose than JUnit.
// Also won't halt execution on failure, and can't be ignored accidentally if
// forget -ea flag like assert.
//
// Usage:
// import static test.Test.*;
// test(1+1, 2, "1+1"); // [OK] 1+1 => 2
// test(1+1, 3, "1+1"); // [FAILED] 1+1 => 2 [expected 3]
// test(1+1, 2); // [OK] unnamed => 2
// test(3==3); // [OK] boolean => true
// ----------------------------------------------------------------------------


// TODO make argument order match JUNIT - expect, actual and msg, expect, actual.

// TODO handle exceptions - pass a lambda with code to execute, exceptions to catch
// eg fails(lambda() 1/0); // defaults to catch Exception.


package test;


import java.util.*;

import static org.junit.Assert.assertEquals;


public class Test {

    // assertions to extend JUnit assertions

    public static void assertInfinity(double d) { assertEquals(Double.POSITIVE_INFINITY, d, 0); }
    public static void assertZero(double d) { assertEquals(0, d, 0); } // numbers need epsilon (last param)
    public static void assertEquals0(double d0, double d1) { assertEquals(d0, d1, 0); }


    // assertEquals wrapper - calls toString on the actual argument
    // eg assertString("[red, robot, room]", t.getWords("r"));
    public static void assertString(String expected, Object actual) {
        assertEquals(expected, actual.toString());
    }
    public static void assertString(String message, String expected, Object actual) {
        assertEquals(message, expected, actual.toString());
    }


    // compare actual and expected values as strings, and print test name and result, eg
    // test(3, 1+2, "1+2"); => "[OK] 1+2 => 3"
    public static void test(Object actual, Object expected, String name) {

        if (actual == null) actual = "null";
        if (expected == null) expected = "null";

        String actualStr = actual.toString();
        String expectedStr = expected.toString();
        boolean equal = actualStr.equals(expectedStr);

        if (equal)
            System.out.println("[OK] " + name + " => " + actualStr);
        else
            System.out.println("[FAILED] " + name + " => " + actualStr + " [expected " + expectedStr + "]");
    }

 
    // test without supplying a name, eg
    // test(3, 1+2); => "[OK] unnamed => 3"
    public static void test(Object actual, Object expected) { test(actual, expected, "unnamed"); }

    // test a boolean, eg
    // test(3==3); => "[OK] boolean => true"
    public static void test(Object actual) { test(actual, true, "boolean"); }



    // test the functions
    public static void main(String[] args) {

        System.out.println("test");
        test(1 + 1, 2, "1+1");
        test(1+1, 3, "should fail");
        test(1+1, 2);
        test(null, null);
        test(3==3);
        test(1==2);
        System.out.println("done");

    }

}


