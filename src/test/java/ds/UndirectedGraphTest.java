
// Test of UndirectedGraph


// TODO merge with DirectedGraphTest


package ds;


import java.util.*;
import static java.util.Arrays.asList;

import org.junit.Test;
import static org.junit.Assert.*;

import static debug.Debug.*;


public class UndirectedGraphTest {

    @Test public void test() throws Exception {

        UndirectedGraph<Character> g = new UndirectedGraph<>();

        g.addNode('a');
        g.addNode('b');
        g.addNode('c');

        g.addEdge('a','b');
        g.addEdge('b','c', 2);
        g.addEdge('c','a');

        poff();
        p(g);
        p(g.toGraphviz());
        
        
        List<Character> nodes;
        UndirectedGraph.Edge<Character> edge;
        List<UndirectedGraph.Edge<Character>> edges;

        // all nodes
        nodes = g.getNodes();
        assertEquals(asList('a','b','c'), nodes);

        // all edges
        edges = g.getEdges();
        assertEquals(3, edges.size());

        // first edge in list
        edge = new UndirectedGraph.Edge<>('a','b');
        assertTrue(edges.contains(edge));
        assertEquals(edge,edges.get(0));


        // edges from node c

        edges = g.getEdges('c');
        assertEquals(2,edges.size());
        edge = new UndirectedGraph.Edge<>('b','c');
        assertFalse(edges.contains(edge));
        edge = edges.get(0);

        p(edge);
        p(g.getSource(edge));
        p(g.getDestination(edge));
        p(g.getCost(edge));

        assertEquals(new Character('b'), g.getDestination(edge));
        assertEquals(2.0, g.getCost(edge), 0);

    }
}

