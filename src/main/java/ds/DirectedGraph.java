// -------------------------------------------------------------------------------------------
// DirectedGraph
// A simple directed graph class, implementing the Graph interface.
// Edges have a cost associated with them.
//
// eg
// DirectedGraph<Integer> g = new DirectedGraph<>();
// g.addNode(0);
// g.addNode(1);
// g.addEdge(0,1,2.0); // cost 2.0
// -------------------------------------------------------------------------------------------


// TODO add index for edges for faster access, for larger graphs


package ds;

import adt.Graph;

import java.util.List;
import java.util.ArrayList;


public class DirectedGraph<N> implements Graph<N, DirectedGraph.Edge<N>> {

    // set of nodes and edges
    List<N> nodes = new ArrayList<>();
    List<Edge<N>> edges = new ArrayList<>();

    // Graph interface

    @Override public boolean addNode(N n) { return nodes.add(n); }
    @Override public boolean addEdge(N src, N dst) { return addEdge(src, dst, 1); }
    @Override public boolean addEdge(N src, N dst, double cost) { return edges.add(new Edge<N>(src, dst, cost)); }
    
    @Override public List<N> getNodes() { return nodes; }
    @Override public List<Edge<N>> getEdges() {return edges; } 
    @Override public List<Edge<N>> getEdges(N node) {
        // TODO try a lambda
        // TODO use index or multimap for speed
        List<Edge<N>> list = new ArrayList<>();
        for (Edge<N> edge : edges) { if (edge.src.equals(node)) { list.add(edge); } }
        return list;
    }
    
    @Override public N getSource(Edge<N> edge) { return edge.src; }
    @Override public N getDestination(Edge<N> edge) { return edge.dst; }
    @Override public double getCost(Edge<N> edge) { return edge.cost; }

    
    
    // representations
    public String toString() { return Graph.asString(this); }
    public String toGraphviz() { return Graph.asGraphviz(this, true); }

    
    // Edges have an optional associated cost
    static class Edge<N> {
        N src;
        N dst;
        double cost;
        Edge(N src, N dst) { this.src = src; this.dst = dst; this.cost = 1; } // constructor
        Edge(N src, N dst, double cost) { this.src = src; this.dst = dst; this.cost = cost; } // constructor
        @Override public String toString () { return dst.toString(); } // used by Graph.asString
        
        // @Override @SuppressWarnings("unchecked") public boolean equals (Object other) {
        @Override public boolean equals (Object other) {
            Edge<?> e = (Edge<?>) other; // warning if use Edge<N> - unchecked method invocation
            if (other == null) {return false;}
            if (this.getClass() != other.getClass()) {return false;}
            if (!this.src.equals(e.src)) {return false;}
            if (!this.dst.equals(e.dst)) {return false;}
            if (this.cost != e.cost) {return false;}
            return true;
        }
        
        @Override public int hashCode(){
            int result = 1;
            result = 31*result + (src !=null ? src.hashCode() : 0);
            result = 31*result + (dst !=null ? dst.hashCode() : 0);
            result = 31*result + (int) cost*1000;
            return result;
        }
    }
    
    
}



