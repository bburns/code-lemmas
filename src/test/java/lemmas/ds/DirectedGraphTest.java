
// Test of DirectedGraph


package lemmas.ds;


import java.util.*;
import static java.util.Arrays.asList;
import org.junit.Test;
import static org.junit.Assert.*;



public class DirectedGraphTest {
    
    @Test public void test() throws Exception {

        DirectedGraph<Integer> g = new DirectedGraph<>();

        // a triangle
        g.addNode(0);
        g.addNode(1);
        g.addNode(2);

        g.addEdge(0,1);
        g.addEdge(1,2);
        g.addEdge(2,0,2);

        DirectedGraph.Edge<Integer> e = new DirectedGraph.Edge<>(0,1);

        
//        log(g);
//        log(g.toGraphviz());

        List<Integer> nodes;
        List<DirectedGraph.Edge<Integer>> edges;
        DirectedGraph.Edge<Integer> edge;

        // all nodes
        nodes = g.getNodes();
        assertEquals(asList(0,1,2), nodes);

        // all edges
        edges = g.getEdges();
        assertEquals(3, edges.size());

        // first edge in list
        edge = new DirectedGraph.Edge<>(0,1);
        assertTrue(edges.contains(edge));
        assertEquals(edge,edges.get(0));

        
        // edges from node 2
        edges = g.getEdges(2);
        assertEquals(1,edges.size());
        edge = edges.get(0);
        assertEquals(new Integer(0), g.getDestination(edge));
        assertEquals(2.0, g.getCost(edge),0);
        
        edge = new DirectedGraph.Edge<>(1,2);
        assertFalse(edges.contains(edge));
        
    }

}
