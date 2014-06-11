
// A simple undirected graph class.


package ds;

import adt.Graph;

import java.util.Collection;
import java.util.List;
import java.util.ArrayList;


// A simple undirected graph class, implementing the adt.Graph interface.
// Nodes store an Object value.
// Edges have a cost associated with them.
public class GraphUndirected implements Graph<GraphUndirected.Node, GraphUndirected.Edge> {

    static Node nodeNothing = new Node("Nothing"); // nullobject

    // Nodes just store Object values
    // equality is based on equality of values
    public static class Node {
        public Object value;
        Node(Object o) { value = o; } // constructor
        @Override public String toString () { return value.toString(); }
        @Override public boolean equals(Object other) {
            if (other == null) {return false;}
            if (this.getClass() != other.getClass()) {return false;}
            if (!this.value.equals(((Node)other).value)) {return false;}
            return true;
        }
    }

    // Edges are bidirectional, and have an associated cost
    public static class Edge {
        public Node src;
        public Node dst;
        public double cost;
        Edge(Node src, Node dst) { this.src = src; this.dst = dst; this.cost = 1; } // constructor
        Edge(Node src, Node dst, double cost) { this.src = src; this.dst = dst; this.cost = cost; } // constructor
        public String toString () { return src + "-" + dst + "(" + cost + ")"; }
    }

    // set of nodes and edges
    private List<Node> nodes = new ArrayList<>();
    private List<Edge> edges = new ArrayList<>();

    // operations
    public Node addNode(Node n) { nodes.add(n); return n; }
    public Node addNode(Object o) { Node n = new Node(o); nodes.add(n); return n; }

    // Find first node containing given object.
    // Returns the node or nodeNothing if not found.
    public Node findNode(Object o) {
        Node n = new Node(o);
        int i = nodes.indexOf(n);
        if (i == -1) return nodeNothing; // nullobject
        Node nodeFound = nodes.get(i);
        return nodeFound;
    }

    public void addEdge(Node src, Node dst) { addEdge(src, dst, 1); }
    public void addEdge(Node src, Node dst, double cost) { edges.add(new Edge(src, dst, cost)); }
    public void addEdge(Object src, Object dst) { addEdge(src, dst, 1); }
    public void addEdge(Object src, Object dst, double cost) { addEdge(findNode(src), findNode(dst), cost); }

    public String toString() { return Graph.asString(this); }


    // Graph interface

    @Override public Collection<Node> getNodes() { return nodes; }
    @Override public Collection<Edge> getEdges(Node node) {
        Collection<Edge> list = new ArrayList<>();
        for (Edge edge : edges) {
            if (edge.src.equals(node) || edge.dst.equals(node)) { list.add(edge); }
        }
        return list;
    }
    @Override public Node getDestination(Edge edge) { return edge.dst; }
    @Override public double getCost(Edge edge) { return edge.cost; }

}



