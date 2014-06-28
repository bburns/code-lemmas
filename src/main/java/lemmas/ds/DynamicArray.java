/** ----------------------------------------------------------------------------
 * DynamicArray
 * Extend ArrayList to work with arbitrary indexes -
 * automatically resizes if get or set are out of bounds. 
 * ----------------------------------------------------------------------------
 */

package lemmas.ds;


import java.util.*;


public class DynamicArray<E> extends ArrayList<E> {

    // recreate ArrayList constructors
    public DynamicArray() { super(); }
    public DynamicArray(int initialCapacity) { super(initialCapacity); }
    public DynamicArray(Collection<? extends E> c) { super(c); }


    /**
     * Extend capacity of this array to include the given index.
     */
    void extendArray(int index) {
        int nelements = index + 1; // need this many elements in array
        ensureCapacity(nelements); // allocate space all at once
        int padding = nelements - size();
        for (int i = 0; i < padding; i++) { add(null); }
    }
    
    
    @Override public E get(int index) {
        extendArray(index);
        return super.get(index);
    }
    
    @Override public E set(int index, E element) {
        extendArray(index);
        return super.set(index, element);
    }

}


