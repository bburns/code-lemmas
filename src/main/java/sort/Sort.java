
// Sort algorithms


package sort;


import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import static java.util.Arrays.asList;



public class Sort {

    // Bubble sort
    // Returns new list (with same object references)
    public static <T extends Comparable> List<T> bubble(List<T> in) {
        List<T> a = new ArrayList<>(in); // shallow copy
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



    // Counting sort
    public static List<Integer> counting(List<Integer> in) {

        List<Integer> out = new ArrayList<>();

        // get min/max values
        int min = Collections.min(in);
        int max = Collections.max(in);
//        int min = Integer.MAX_VALUE;
//        int max = Integer.MIN_VALUE;
//        for (Integer v : in) { }


        // tally observed numbers
        int nplaces = max + 1;
        int[] counts = new int[nplaces];
        for (Integer v : in) {
            counts[v] += 1;
        }

        // walk through tally array, outputting numbers
        int i = 0;
        while (i < nplaces) {
            while (counts[i] > 0) {
                out.add(i);
                counts[i]--;
            }
            i++;
        }

        return out;
    }


}
