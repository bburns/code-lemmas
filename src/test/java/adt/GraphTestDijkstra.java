
package adt;


import org.junit.Test;
import static org.junit.Assert.*;


import java.util.Collection;
import java.util.List;
import java.util.ArrayList;




public class GraphTestDijkstra {


    static class Simple implements Graph<Simple.Node, Simple.Edge> {

        static Node nodeNothing = new Node("Nothing");

        // subclasses
        public static class Node {
            public Object value;
            Node(Object o) { value = o; }
            @Override public String toString () { return value.toString(); }
            @Override public boolean equals(Object other) {
                if (other == null) {return false;}
                if (this.getClass() != other.getClass()) {return false;}
                if (!this.value.equals(((Node)other).value)) {return false;}
                return true;
            }

        }
        public static class Edge {
            public Node src;
            public Node dst;
            public double cost;
            Edge(Node src, Node dst, double cost) { this.src = src; this.dst = dst; this.cost = cost; }
            Edge(Node src, Node dst) { this.src = src; this.dst = dst; this.cost = 1; }
            public String toString () { return src + "-" + dst + "(" + cost + ")"; }
        }

        // data
        private List<Node> nodes = new ArrayList<>();
        private List<Edge> edges = new ArrayList<>();

        // operations
        public Node addNode(Object o) { Node n = new Node(o); nodes.add(n); return n; }
        public Node addNode(Node n) { nodes.add(n); return n; }
        
        public Node findNode(Object o) {
            Node n = new Node(o);
            int i = nodes.indexOf(n);
            if (i == -1) return nodeNothing;
            Node nfound = nodes.get(i);
            return nfound;
//            return nodes.get(i);
        }
        
        public void addEdge(Object src, Object dst) { addEdge(src, dst, 1); }
        public void addEdge(Object src, Object dst, double cost) {
            Node nsrc = findNode(src);
            Node ndst = findNode(dst);
            addEdge(nsrc, ndst, cost);
        }
        public void addEdge(Node src, Node dst) { addEdge(src, dst, 1); }
        public void addEdge(Node src, Node dst, double cost) {
            Edge edge = new Edge(src, dst, cost);
            edges.add(edge);
//            edges.add(new Edge(src, dst, cost));
        }
        
        public String toString() { return Graph.asString(this); }

//        @Override public Collection<Edge> getEdges(Object o) {
//            Node n = findNode(o);
//            return getEdges(n);
//        }

        // Graph interface
        @Override public Collection<Node> getNodes() { return nodes; }
        @Override public Collection<Edge> getEdges(Node node) {
            Collection<Edge> list = new ArrayList<>();
//            System.out.println(edges);
            for (Edge edge : edges) {
                if (edge.src.equals(node) || edge.dst.equals(node)) {
                    list.add(edge);
                }
//                System.out.println(node + " " + edge + " " + list);
            }
//            System.out.println(node + " " + list);
            return list;
        }
        @Override public Node getDestination(Edge edge) { return edge.dst; }
        @Override public double getCost(Edge edge) { return edge.cost; }

    }



    public void assertInfinity(double d) { assertEquals(Double.POSITIVE_INFINITY, d, 0); }


    private double dijkstra(Simple g, Object start, Object target) {
        Simple.Node nstart = g.findNode(start);
        Simple.Node ntarget = g.findNode(target);
        return Graph.dijkstra(g, nstart, ntarget);
    }

    @Test
    public void testDijkstra() throws Exception {

        double inf = Double.POSITIVE_INFINITY;

        Simple g = new Simple();
        double d;

//        d = dijkstra(g,0,0);
        d = dijkstra(g,'a','a');
//        assertEquals(inf,d,0);
        assertInfinity(d);

//        g.addNode(0);
        g.addNode('a');
//        d = dijkstra(g,0,0);
        d = dijkstra(g,'a','a');
        assertEquals(0,d,0);

//        g.addNode(1);
//        g.addEdge(0,1,1);
//        d = dijkstra(g,0,1);
        g.addNode('b');
        g.addEdge('a','b',1);
        d = dijkstra(g,'a','b');
        assertEquals(1,d,0);

//        g.addNode(2);
//        d = dijkstra(g,0,2);
        g.addNode('c');
        d = dijkstra(g,'a','c');
//        assertEquals(inf,d,0); // ie can't reach it
        assertInfinity(d);

//        g.addEdge(0,2,2);
//        System.out.println(g);
//        d = dijkstra(g,0,2);
        g.addEdge('a','c',2);
        System.out.println(g);
        d = dijkstra(g,'a','c');
        assertEquals(2,d,0);

//        g.addEdge(1,2,3);
//        System.out.println(g);
//        d = dijkstra(g,0,2);
//        assertEquals(2,d,0);

        // test a cycle

    }


}
