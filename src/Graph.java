
// A Graph interface
// getEdges and getDestination are sufficient to run a depth-first-search.


import java.util.Collection;


// N for node class, E for edge class
interface Graph<N, E> {
    Collection<N> getNodes();
    Collection<E> getEdges(N node);
    N getDestination(E edge);
}




