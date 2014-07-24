


package lemmas.ds;

import lemmas.adt.PriorityQueue;
import java.util.*;


public class BinaryHeap implements PriorityQueue {

    DynamicArray<String> data = new DynamicArray<>();
    DynamicArray<Integer> priorities = new DynamicArray<>();

    Random rnd = new Random();


    @Override
    public void push(String s, int priority) {
        push(s, priority, 0);
    }

    public void push(String s, int priority, int i) {

        // if slot doesn't exist yet, put it here and exit
        if (i>=data.size()) {
            priorities.set(i,priority);
            data.set(i,s);
            return;
        }

        // if it belongs here, put it here and shift existing item further down
        if (priority < priorities.get(i)) {
            int pold = priorities.get(i);
            String sold = data.get(i);
            priorities.set(i, priority);
            data.set(i, s);
            priority = pold;
            s = sold;
        }

        // push item to left or right subtree
        int ileft = i*2+1;
        int iright = i*2+2;
        int ichild = rnd.nextBoolean() ? ileft : iright;
        push(s, priority, ichild);

    }

    @Override
    public String peek() {
        return data.get(0);
    }




}
