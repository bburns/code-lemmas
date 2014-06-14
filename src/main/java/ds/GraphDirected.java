
// GraphDirected
// A simple directed graph class, implementing the Graph interface.
// Nodes just store an Object value.
// Edges have a cost associated with them.
// -------------------------------------------------------------------------------------------


// TODO add index for edges for faster access, for larger graphs


package ds;

import adt.Graph;

import java.util.Collection;
import java.util.List;
import java.util.ArrayList;


// public class GraphDirected implements Graph<GraphDirected.Node, GraphDirected.Edge> {
// public class GraphDirected<N> implements Graph<N, GraphDirected.Edge> {
public class GraphDirected<N> implements Graph<N, GraphDirected.Edge<N>> {

    // can't do this
    // static Node nodeNothing = new Node("Nothing"); // nullobject
    // static N nodeNothing = new N("Nothing"); // nullobject

    // Nodes just store Object values
    // equality is based on equality of values
    // static class Node {
    //     public Object value;
    //     Node(Object o) { value = o; } // constructor
    //     @Override public String toString () { return value.toString(); }
    //     @Override public boolean equals(Object other) {
    //         if (other == null) {return false;}
    //         if (this.getClass() != other.getClass()) {return false;}
    //         if (!this.value.equals(((Node)other).value)) {return false;}
    //         return true;
    //     }
    // }

    // Edges have an associated cost
    // static class Edge {
    static class Edge<N> {
        public N src;
        public N dst;
        public double cost;
        Edge(N src, N dst) { this.src = src; this.dst = dst; this.cost = 1; } // constructor
        Edge(N src, N dst, double cost) { this.src = src; this.dst = dst; this.cost = cost; } // constructor
        public String toString () { return src + "-" + dst + "(" + cost + ")"; }
    }

    // set of nodes and edges
    private List<N> nodes = new ArrayList<>();
    private List<Edge> edges = new ArrayList<>();


    // Graph interface

    // java collections return boolean on add. i'd rather return the new object but...
    // @Override public Node addNode(Node n) { nodes.add(n); return n; }
    // @Override public Edge addEdge(Node src, Node dst) { return addEdge(src, dst, 1); }
    // // @Override public Edge addEdge(Node src, Node dst, double cost) { return edges.add(new Edge(src, dst, cost)); }
    // @Override public Edge addEdge(Node src, Node dst, double cost) { Edge e = new Edge(src, dst, cost); edges.add(n); return e; }

    @Override public boolean addNode(N n) { return nodes.add(n); }
    @Override public boolean addEdge(N src, N dst) { return addEdge(src, dst, 1); }
    @Override public boolean addEdge(N src, N dst, double cost) { return edges.add(new Edge(src, dst, cost)); }

    @Override public Collection<N> getNodes() { return nodes; }
    // @Override public Collection<Edge> getEdges(N node) {
    @Override public Collection<Edge<N>> getEdges(N node) {
        // Collection<Edge> list = new ArrayList<>();
        Collection<Edge<N>> list = new ArrayList<>();
        for (Edge edge : edges) {
            // if (edge.src.equals(node) || edge.dst.equals(node)) { list.add(edge); }
            if (edge.src.equals(node)) { list.add(edge); }
        }
        return list;
    }
    @Override public N getDestination(Edge<N> edge) { return edge.dst; }
    @Override public double getCost(Edge<N> edge) { return edge.cost; }



    // additional operations

    // public Node addNode(Object o) { Node n = new Node(o); return addNode(n); }
//    public Edge addEdge(Object src, Object dst) { return addEdge(src, dst, 1); }
//    public Edge addEdge(Object src, Object dst, double cost) { return addEdge(findNode(src), findNode(dst), cost); }

    // Find first node containing given object.
    // Returns the node or nodeNothing if not found.
    // private N findNode(Object o) {
    //     N n = new N(o);
    //     int i = nodes.indexOf(n);
    //     if (i == -1) return nodeNothing; // nullobject
    //     N nodeFound = nodes.get(i);
    //     return nodeFound;
    // }

    // represent graph
    public String toString() { return Graph.asString(this); }

}



