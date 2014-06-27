// ----------------------------------------------------------------------------
// UndirectedGraph
// A simple undirected graph class, implementing the Graph interface.
// Edges have a cost associated with them. Extends DirectedGraph.
// ----------------------------------------------------------------------------
// eg
// UndirectedGraph<Integer> g = new UndirectedGraph<>();
// g.addNode(0);
// g.addNode(1);
// g.addEdge(0,1,2.0); // cost 2.0
// ----------------------------------------------------------------------------


package lemmas.ds;


import lemmas.adt.Graph; // interface

import java.util.*;



public class UndirectedGraph<N> extends DirectedGraph<N> {

//    public static class Edge<N> extends DirectedGraph.Edge<N> {}

    @Override public List<Edge<N>> getEdges(N node) {
//    @Override public List<? extends Edge<N>> getEdges(N node) {
        List<Edge<N>> list = new ArrayList<>();
        for (Edge<N> edge : edges) {
            if (getSource(edge).equals(node)) { list.add(edge); }
            // edges are bidirectional, so add if node matches destination also
            if (getDestination(edge).equals(node)) {
                // TODO not very efficient, but ok for small datasets with a few edges per node
                Edge<N> reversed = new Edge<N>(edge.dst, edge.src, edge.cost);
                list.add(reversed);
            }
        }
        return list;
    }

    public String toGraphviz() { return Graph.asGraphviz(this, false); }

}



