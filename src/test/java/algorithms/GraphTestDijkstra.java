
package algorithms;


import adt.Graph;
import ds.GraphUndirected;

import org.junit.Test;
import static org.junit.Assert.*;



public class GraphTestDijkstra {

//    static class GraphUndirected {}

    public static void assertInfinity(double d) { assertEquals(Double.POSITIVE_INFINITY, d, 0); }
    public static void assertZero(double d) { assertEquals(0, d, 0); }
    public static void assertEquals0(double d0, double d1) { assertEquals(d0, d1, 0); }


    // Get shortest distance from start to target in given graph.
    // Just a wrapper around Graph.dijkstra.
    private static double dijkstra(GraphUndirected g, Object start, Object target) {
        GraphUndirected.Node nstart = g.findNode(start);
        GraphUndirected.Node ntarget = g.findNode(target);
        return Search.dijkstra(g, nstart, ntarget);
    }


    @Test public void testDijkstra() throws Exception {

        GraphUndirected g = new GraphUndirected();
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
