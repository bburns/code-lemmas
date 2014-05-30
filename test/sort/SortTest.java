
// Test sort algorithms


package sort;


import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


import java.util.List;
import java.util.ArrayList;
import static java.util.Arrays.asList;



public class SortTest {

    // test if a list is sorted
    public <T extends Comparable<? super T>> boolean isSorted(List<T> list) {
        for (int i=0; i < list.size()-1; i++) {
            if (list.get(i).compareTo(list.get(i+1)) > 0) return false; // list[i] > list[i+1]
        }
        return true;
    }


    @Before public void setUp() throws Exception {}


    @Test
    public void testBubbleInts() throws Exception {
        List<Integer> in = asList(2, 1, 5, 3, 0);
        List<Integer> out = Sort.bubble(in);
        assertTrue(isSorted(out));
    }

    @Test
    public void testBubbleStrings() throws Exception {
        List<String> in = asList("marsh", "lake", "river", "pond", "creek");
        List<String> out = Sort.bubble(in);
        assertTrue(isSorted(out));
    }


}


