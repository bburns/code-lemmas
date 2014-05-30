
// Sort algorithms


package sort;


import java.util.List;
import java.util.ArrayList;


public class Sort {

    // bubble sort
    // returns new list (with same object references)
    public static <T extends Comparable> List<T> bubble(List<T> a) {
        List<T> b = new ArrayList<>(a); // shallow copy
        int n = b.size();
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < n-i; j++) {
                if (b.get(j).compareTo(b.get(j+1)) > 0) {
                    T t = b.get(j);
                    b.set(j, b.get(j+1));
                    b.set(j+1, t);
                }
            }
        }
        return b;
    }

}
