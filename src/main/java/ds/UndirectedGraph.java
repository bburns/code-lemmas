// -------------------------------------------------------------------------------------------
// UndirectedGraph
// A simple undirected graph class, implementing the Graph interface.
// Edges have a cost associated with them.
//
// eg
// UndirectedGraph<Integer> g = new UndirectedGraph<>();
// g.addNode(0);
// g.addNode(1);
// g.addEdge(0,1,2.0); // cost 2.0
// -------------------------------------------------------------------------------------------


package ds;


import adt.Graph;

import java.util.List;
import java.util.ArrayList;


public class UndirectedGraph<N> extends DirectedGraph<N> {

    public String toGraphviz() { return Graph.asGraphviz(this, false); }
    
    @Override public List<Edge<N>> getEdges(N node) {
        List<Edge<N>> list = new ArrayList<>();
        for (Edge<N> edge : edges) {
            if (edge.src.equals(node)) { list.add(edge); }
            if (edge.dst.equals(node)) {
                // TODO this is not very efficient, but is fine for small datasets, which only have a few edges per node
                Edge<N> reversed = new Edge<N>(edge.dst, edge.src, edge.cost);
                list.add(reversed);
            }
        }
        return list;
    }

}
