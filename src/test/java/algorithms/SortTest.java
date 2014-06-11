
// Test sort algorithms


package algorithms;


import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;



public class SortTest {

    @Test public void testBubbleInts() throws Exception {
        List<Integer> in = asList(2,1,5,3,0,5);
        List<Integer> out = Sort.bubble(in);
        Collections.sort(in); // correct sort
        assertEquals(in, out);
    }

    @Test public void testBubbleStrings() throws Exception {
        List<String> in = asList("marsh", "lake", "river", "pond", "creek", "lake");
        List<String> out = Sort.bubble(in);
        Collections.sort(in); // correct sort
        assertEquals(in, out);
    }

    @Test public void testCounting() throws Exception {
        List<Integer> in = asList(2,1,5,3,0,2,0);
        System.out.println(in);
        List<Integer> out = Sort.counting(in);
        System.out.println(out);
        Collections.sort(in);
        assertEquals(in, out);
    }

}


