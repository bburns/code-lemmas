

package lemmas.adt;


import lemmas.ds.BinaryHeap;
import org.junit.Test;
import static org.junit.Assert.*;


public class PriorityQueueTest {

    @Test public void test() throws Exception {

        PriorityQueue pq = new BinaryHeap();

        pq.push("naan", 7);
        assertEquals("naan",pq.peek());

        pq.push("rice", 8);
        assertEquals("naan",pq.peek());

        pq.push("saag", 4);
        assertEquals("saag",pq.peek());

    }


}