

package lemmas.ds;


import org.junit.Test;
import static org.junit.Assert.*;


public class DynamicArrayTest {

    @Test public void test() {

        DynamicArray<Object> da = new DynamicArray<>();

        da.set(10,"ham");
        da.add(15);
        assertEquals(da.get(10), "ham");
        assertEquals(da.get(11),15);

        DynamicArray<String> das = new DynamicArray<>();
        das.add("hello");
        assertEquals(das.get(0), "hello");
        das.set(3, "world");
        assertEquals(das.get(3),"world");
        assertEquals(das.get(9),null);

        DynamicArray<Integer> dai = new DynamicArray<>(5);
        dai.set(10,42);
        assertEquals((long)dai.get(10),42);

        DynamicArray<Integer> ali1 = new DynamicArray<>();
        ali1.set(5,312);
        ali1.set(15,492);
        DynamicArray<Integer> ali2 = new DynamicArray<>(ali1);
        assertEquals((long)ali1.get(15),492);

    }

}