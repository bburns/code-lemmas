
// A Graph interface with nodes and edges, and some
// static methods - dfs, bfs, asString, asGraphviz.
//
// For example,
// static class Places implements Graph<String, Places.Road> {
//    @Override public Collection<Room> getNodes() { return getRooms(); }



// ---------------------------------------------------

package adt;


import java.util.Collection;


// ------------------------------------------------------------
// Graph interface with some static methods
// ------------------------------------------------------------
// N for node class, E for edge class
public interface Graph<N, E> {

    // ------------------------------------------------------------
    // Methods to be implemented
    // ------------------------------------------------------------
    Collection<N> getNodes();
    Collection<E> getEdges(N node);
    N getDestination(E edge);
    double getCost(E edge);


    // ------------------------------------------------------------
    // asString
    // ------------------------------------------------------------
    // Return a string representation of the given Graph object -
    // a list of nodes and where they exit to.
    // eg
    // Graph.asString(g) => "a->[b,c]   b->[d,a]   c->[]   d->[b]"
    static <N, E> String asString(Graph<N, E> g) {
        StringBuilder sb = new StringBuilder();
        for (N n : g.getNodes()) {
            sb.append(n + "->" + g.getEdges(n) + "\n");
        }
        return sb.toString();
    }


    // ------------------------------------------------------------
    // asGraphviz
    // ------------------------------------------------------------
    // Return a representation of the graph as a Graphviz dot file.
    // eg
    // Graph.asGraphviz(g) => "digraph g {a -> b; a -> c; b -> c;}"
    static <N, E> String asGraphviz(Graph<N, E> g) {
        StringBuilder sb = new StringBuilder();
        sb.append("digraph g {\n");
        for (N n : g.getNodes()) {
            for (E e : g.getEdges(n)) {
                N dest = g.getDestination(e);
                sb.append("    \"" + n + "\" -> \"" + dest + "\";\n");
            }
        }
        sb.append("}\n");
        return sb.toString();
    }

}


