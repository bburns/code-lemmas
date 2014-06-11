
package adt;


import org.junit.Test;
import static org.junit.Assert.*;


import java.util.Collection;
import java.util.List;
import java.util.ArrayList;




public class GraphTestDijkstra {


    public static void assertInfinity(double d) { assertEquals(Double.POSITIVE_INFINITY, d, 0); }
    public static void assertZero(double d) { assertEquals(0, d, 0); }
    public static void assertEquals0(double d0, double d1) { assertEquals(d0, d1, 0); }

    // a simple undirected graph class
    // nodes just store an Object value
    // edges are bidirectional
    static class Simple implements Graph<Simple.Node, Simple.Edge> {

        static Node nodeNothing = new Node("Nothing"); // nullobject

        // nodes just store Object values
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

        // edges are bidirectional, and have an associated cost
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

        // find first node containing given object.
        // returns the node or nodeNothing if not found.
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


    // Get shortest distance from start to target in given graph.
    // Just a wrapper around Graph.dijkstra.
    private static double dijkstra(Simple g, Object start, Object target) {
        Simple.Node nstart = g.findNode(start);
        Simple.Node ntarget = g.findNode(target);
        return Graph.dijkstra(g, nstart, ntarget);
    }


    @Test
    public void testDijkstra() throws Exception {

        Simple g = new Simple();
        double d;

        // no nodes - illegal
//        d = dijkstra(g,'a','a');
//        d = dijkstra(g,'a','b');

        // single node
        g.addNode('a');
        d = dijkstra(g,'a','a');
        assertZero(d);
//        d = dijkstra(g,'a','b'); // illegal

        // line
        g.addNode('b');
        g.addEdge('a','b',1);
        d = dijkstra(g,'a','b');
        assertEquals0(1,d);

        // line and node
        g.addNode('c');
        d = dijkstra(g,'a','c');
        assertInfinity(d); // ie can't reach it

        // v
        g.addEdge('a','c',2);
        d = dijkstra(g,'a','c');
        assertEquals0(2,d);

        // triangle
        g.addEdge('b','c',3);
        d = dijkstra(g,'a','c');
        assertEquals0(2,d);

        // diamond
        g.addNode('d');
        g.addEdge('b','d',7);
        g.addEdge('c','d',4);
        d = dijkstra(g,'a','d');
        assertEquals0(6,d);

        System.out.println(g);

    }


}
