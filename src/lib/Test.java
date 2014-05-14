/*
Test
Simple test function - compares actual and expected values as strings. 
String-based comparisons work for most things. 
-------------------------------------------------------------------------------
@author Brian Burns <bburns.km@gmail.com>
@since 2008-10
-------------------------------------------------------------------------------
 
something in between assert and junit - 
more informative than assert
can't be left off accidentally if forget -ea flag
won't halt execution like assert

cf
assert (n.key=='i');
test(n.key=='i');
test(n.key,'i');

 
 
With this file in the same folder as your Java project,
   Test.test(1+1, 2, "1+1");
   Test.test(1+1, 3, "1+1");
prints
   [OK] 1+1 => 2
   [FAILED] 1+1 => 2 [expected 3]

The string is optional, eg
   Test.test(1+1, 2);
   [OK] unnamed => 2

Static import
  import static Test.test;
  test(1+1,2,"1+1");

Test booleans
  test(someflag);
-------------------------------------------------------------------------------
*/


//. pass an exception class to catch errors


package lib;



import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;


public class Test {

    // Compare actual and expected values as strings, and print result.
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

 
    // Test without supplying a name
    public static void test(Object actual, Object expected) {
        test(actual, expected, "unnamed");
    }

    // Test boolean
    public static void test(Object actual) {
        test(actual, true, "boolean");
    }


    public static <T> void testPermutations(List<T> list1, List<T> list2, boolean expected, String name) {
        test(permutations(list1, list2), expected, name);
    }
    public static <T> void testPermutations(List<T> list1, List<T> list2, boolean expected) {
        test(permutations(list1, list2), expected, "permutation");
    }
    public static <T> void testPermutations(List<T> list1, List<T> list2) {
        test(permutations(list1, list2), true, "permutation");
    }
    
    // check if the two lists are permutations of each other, by converting them to sets
    public static <T> boolean permutations(List<T> list1, List<T> list2) {
        assert list1!=null;
        assert list2!=null;
        if (list1.size() == list2.size()) {
            Set<T> set1 = new HashSet<T>(list1);
            Set<T> set2 = new HashSet<T>(list2);
            return set1.equals(set2);
        }
        return false;
    }
    
    
    
    public static void main(String[] args) {

        // example usage

        System.out.println("test");
        
        test(1+1, 2, "1+1");
        test(1+1, 2);
        // test(1+1, 3, "1+1"); // to show failure
        // test(1+1, 3); // to show failure
        test(true); // boolean
        test(null, null);


        
        // permutations
        
        System.out.println("permutations");
        
        testPermutations(Arrays.asList(1,2,3), Arrays.asList(1,2,3), true);
        testPermutations(Arrays.asList(1,2,3), Arrays.asList(1,3,2), true);
        testPermutations(Arrays.asList(1,2,3), Arrays.asList(2,1,3), true);
        testPermutations(Arrays.asList(1,2,3), Arrays.asList(2,3,1), true);
        testPermutations(Arrays.asList(1,2,3), Arrays.asList(3,1,2), true);
        testPermutations(Arrays.asList(1,2,3), Arrays.asList(3,2,1), true);
        
        testPermutations(Arrays.asList(1,2,3), Arrays.asList(), false);
        testPermutations(Arrays.asList(1,2,3), Arrays.asList(3), false);
        testPermutations(Arrays.asList(1,2,3), Arrays.asList(1,1,1), false);
        testPermutations(Arrays.asList(1,2,3), Arrays.asList(1,2,3,4), false);
        testPermutations(Arrays.asList(1,2,3), Arrays.asList(3,2,1,3), false);
        
        
        // handle exceptions

        // System.out.println("exceptions");
        
        // test(1/0, 0, "1/0"); // compiler catches 1/0 with warning

        // int d = 0;
        // test(1/d, 0 "1/0"); // throws exception

        // try {
        //     int d = 0;
        //     test(1/d, 0, "1/0"); // throws exception
        // }
        // catch (ArithmeticException e) {
        //     System.out.println("lij");
        // }

        // nowork
        // int d = 0;
        // test(1/d, ArithmeticException, "1/0");

    }

}


