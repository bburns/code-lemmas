
// Test of directed graph


package ds;

import java.util.*;

import org.junit.Test;
import static org.junit.Assert.*;

import static java.util.Arrays.asList;


public class DirectedGraphTest {
    
    @Test public void test() throws Exception {

        DirectedGraph<Integer> g = new DirectedGraph<>(); // works

        // a triangle
        
        g.addNode(0);
        g.addNode(1);
        g.addNode(2);
        
        g.addEdge(0,1);
        g.addEdge(1,2);
        g.addEdge(2,0,2);
        
        // System.out.println(g);
        // System.out.println(g.toGraphviz());
        
        List<Integer> nodes;
        List<DirectedGraph.Edge<Integer>> edges;
        DirectedGraph.Edge<Integer> edge;
        
        
        // all nodes
        nodes = g.getNodes();
        // System.out.println(nodes);
        assertEquals(asList(0,1,2), nodes);

        // all edges
        edges = g.getEdges();
        assertEquals(3, edges.size());
        // System.out.println(edges);
        
        // first edge in list
        // need to override .equals
        // assertTrue(edges.contains(new GraphDirected.Edge<Integer>(1,2)));
        // GraphDirected.Edge<Integer> e1 = new GraphDirected.Edge<>(0,1);
        edge = new DirectedGraph.Edge<>(0,1);
        // System.out.println(edge);
        assertTrue(edges.contains(edge));
        assertEquals(edge,edges.get(0)); 
        
        
        // edges from node 2
        edges = g.getEdges(2);
        // System.out.println(edges);
        assertEquals(1,edges.size());
        edge = edges.get(0);
        // System.out.println(edge);
        // System.out.println(g.getDestination(edge));
        // System.out.println(g.getCost(edge));
        // assertEquals(1,g.getDestination(edge));
        assertEquals(new Integer(0), g.getDestination(edge));
        assertEquals(2.0, g.getCost(edge),0);
        
        edge = new DirectedGraph.Edge<>(1,2);
        assertFalse(edges.contains(edge));
        
    }

}
