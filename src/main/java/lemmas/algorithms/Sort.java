/** ----------------------------------------------------------------------------
 * Sort algorithms
 * ----------------------------------------------------------------------------
 */

package lemmas.algorithms;


import java.util.*;
import static org.junit.Assert.*;


public class Sort {

    /**
     * Bubble sort
     * Returns new list (with same object references)
     */
    public static <T extends Comparable<? super T>> List<T> bubble(List<T> in) {
        List<T> a = new ArrayList<>(in); // get shallow copy
        int n = a.size();
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < n-i; j++) {
                if (a.get(j).compareTo(a.get(j+1)) > 0) {
                    T t = a.get(j);
                    a.set(j, a.get(j+1));
                    a.set(j+1, t);
                }
            }
        }
        return a;
    }



    /**
     * Counting sort
     * Builds a histogram of all the values, then walks it to build a sorted list.
     * Requires an array with number of elements = max of values.  
     */
    public static List<Integer> counting(List<Integer> in) {

        // get min/max values
        int min = Collections.min(in);
        int max = Collections.max(in);

        // preconditions
        assertTrue(min >= 0);
        
        // build histogram of observed values
        int nplaces = max + 1;
        int[] histogram = new int[nplaces];
        for (Integer value : in) {
            histogram[value] += 1;
        }

        // walk through histogram, adding values to sorted list
        List<Integer> out = new ArrayList<>();
        int value = 0;
        while (value < nplaces) {
            while (histogram[value] > 0) {
                out.add(value);
                histogram[value]--;
            }
            value++;
        }
        return out;
    }

}
