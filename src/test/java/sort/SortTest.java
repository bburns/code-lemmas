
// Test sort algorithms


package sort;


import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import static java.util.Arrays.asList;



public class SortTest {

    // test if a list is sorted
//    public <T extends Comparable<? super T>> boolean isSorted(List<T> list) {
//        for (int i=0; i < list.size()-1; i++) {
//            if (list.get(i).compareTo(list.get(i+1)) > 0) return false; // list[i] > list[i+1]
//        }
//        return true;
//    }


//    @Before public void setUp() throws Exception {}


    @Test
    public void testBubbleInts() throws Exception {
        List<Integer> in = asList(2, 1, 5, 3, 0, 5);
        List<Integer> out = Sort.bubble(in);
//        assertEquals(asList(0,1,2,3,5,5), out);
        Collections.sort(in);
        assertEquals(in, out);
    }

    @Test
    public void testBubbleStrings() throws Exception {
        List<String> in = asList("marsh", "lake", "river", "pond", "creek", "lake");
        List<String> out = Sort.bubble(in);
//        assertTrue(isSorted(out));
//        assertString("[creek, lake, marsh, pond, river]", out);
        Collections.sort(in);
        assertEquals(in, out);
    }


    @Test
    public void testCounting() throws Exception {
        List<Integer> in = asList(2, 1, 5, 3, 0, 2, 0);
        System.out.println(in);
        List<Integer> out = Sort.counting(in);
        System.out.println(out);
//        List<Integer> sorted = asList(0,1,2,3,5);
//        assertEquals(sorted, out);
//        assertEquals(asList(0,1,2,3,5), out);
//        assertEquals(asList(0,0,1,2,2,3,5), out);
        Collections.sort(in);
        assertEquals(in, out);
    }



}


