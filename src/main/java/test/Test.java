/*
Test
Simple test function - compares actual and expected values as strings. 
String-based comparisons work for most things.

@author Brian Burns <bburns.km@gmail.com>
-------------------------------------------------------------------------------
 
Something between Java assert and JUnit - can't be ignored accidentally if
forget -ea flag, and won't halt execution like assert.

Usage

import static test.Test.*;
test(1+1, 2, "1+1"); // [OK] 1+1 => 2
test(1+1, 3, "1+1"); // [FAILED] 1+1 => 2 [expected 3]

The string is optional -
test(1+1, 2); // [OK] unnamed => 2

Test booleans -
test(someflag); // [OK] boolean => true

-------------------------------------------------------------------------------
*/

// TODO make argument order match JUNIT - expect, actual and msg, expect, actual.

// TODO handle exceptions - pass a lambda with code to execute, exceptions to catch
// eg fails(lambda() 1/0); // defaults to catch Exception.


package test;


import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

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


    // compare actual and expected values as strings, and print test name and result
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

 
    // test without supplying a name
    public static void test(Object actual, Object expected) { test(actual, expected, "unnamed"); }

    // test boolean
    public static void test(Object actual) { test(actual, true, "boolean"); }



    // test the functions
    public static void main(String[] args) {

        System.out.println("test");
        test(1 + 1, 2, "1+1");
        test(1+1, 3, "should fail");
        test(1+1, 2);
        test(null, null);
        test(true); // boolean
        System.out.println("done");

    }

}


