
// Test sort algorithms


package sort;


import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


import java.util.List;
import java.util.ArrayList;
import static java.util.Arrays.asList;



public class SortTest {

    List<Integer> unsorted = asList(2,1,5,3,0);


    // test if a list is sorted
    public boolean isSorted(List<Integer> list) {
        for (int i=0; i < list.size()-1; i++) {
            if (list.get(i) > list.get(i+1)) return false;
        }
        return true;
    }


    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testBubbleSort() throws Exception {
        // test with increasing sequences of numbers - [], [2], [2,1], ...
        int n = unsorted.size();
        for (int i = 0; i < n+1; i++) {
            List<Integer> in = unsorted.subList(0, i); // end index is exclusive
            List<Integer> out = Sort.bubble(in);
            assertTrue(isSorted(out));
        }
    }

}


