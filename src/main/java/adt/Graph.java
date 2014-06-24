// ----------------------------------------------------------------------------
// Graph
// A graph interface with nodes and edges, and some static methods.
// ----------------------------------------------------------------------------
// The Node and Edge classes are left unspecified here, except
// indirectly through the methods, eg getDestination.
// ----------------------------------------------------------------------------


// TODO all comments to javadoc style. because standard, and work with intellij

// TODO improve edge interface - currently have code like this -
// List<UndirectedGraph.Edge<Character>> edges;


package adt;


import java.util.*;


// Graph
// N for node class, E for edge class
public interface Graph<N, E> {


    // Methods to be implemented
    // -----------------------------------------------

    boolean addNode(N node);
//    boolean addEdge(N src, N dst);
//    boolean addEdge(N src, N dst, double cost);
    boolean addEdge(E edge);
    
    List<N> getNodes();
    List<E> getEdges();
    List<E> getEdges(N node);
    
    N getSource(E edge);
    N getDestination(E edge);
    double getCost(E edge);



    // asString
    // -----------------------------------------------
    // Return a string representation of the given Graph object -
    // a list of nodes and where they exit to, eg
    // Graph.asString(g) => "a->[b,c]   b->[d,a]   c->[]   d->[b]"

    static <N, E> String asString(Graph<N, E> g) {
        StringBuilder sb = new StringBuilder();
        for (N n : g.getNodes()) {
            sb.append(n + "->" + g.getEdges(n) + "\n");
        }
        return sb.toString();
    }


    // asGraphviz
    // -----------------------------------------------
    // Return a representation of the graph as a Graphviz dot file, eg
    // Graph.asGraphviz(g, true) => "digraph g {a -> b; a -> c; b -> c;}"

    static <N, E> String asGraphviz(Graph<N, E> g, boolean directedGraph) {
        
        StringBuilder sb = new StringBuilder();
        String graphtype = directedGraph ? "digraph" : "graph";
        String delim = directedGraph ? "->" : "--";
        String graphname = "g";
        
        sb.append(graphtype + " " + graphname + " {\n");
        for (E e : g.getEdges()) {
            N src = g.getSource(e);
            N dst = g.getDestination(e);
            sb.append("    \"" +  src + "\"" + delim + "\"" + dst + "\";\n");
        }
        sb.append("}\n");
        
        return sb.toString();
    }

}


