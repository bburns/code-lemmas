
// Test of UndirectedGraph


package ds;


import java.util.*;
import static java.util.Arrays.asList;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;


public class UndirectedGraphTest {

    @Test public void test() throws Exception {

        UndirectedGraph<Character> g = new UndirectedGraph<>();

        g.addNode('a');
        g.addNode('b');
        g.addNode('c');

        g.addEdge('a','b');
        g.addEdge('b','c', 2);
        g.addEdge('c','a');

//        System.out.println(g.i);
        
//        List<UndirectedGraph.Edge<Character>> edges = g.getEdges('a');
//        for (UndirectedGraph.Edge<Character> edge : edges) {
//            System.out.println(edge);
//        }

        System.out.println(g);
        System.out.println(g.toGraphviz());

        List<Character> nodes;
        List<UndirectedGraph.Edge<Character>> edges; //.
        UndirectedGraph.Edge<Character> edge; //.


        // all nodes
        nodes = g.getNodes();
        System.out.println(nodes);
        assertEquals(asList('a','b','c'), nodes);

        // all edges
        edges = g.getEdges();
        System.out.println(edges);
        assertEquals(3, edges.size());

        // first edge in list
        edge = new UndirectedGraph.Edge<>('a','b'); //.
        System.out.println(edge);
        assertTrue(edges.contains(edge));
        assertEquals(edge,edges.get(0));


        // edges from node 2
        edges = g.getEdges('c');
        System.out.println(edges);
        assertEquals(2,edges.size());
        edge = new UndirectedGraph.Edge<>('b','c'); //.
        assertFalse(edges.contains(edge));

        edge = edges.get(0);
        System.out.println(edge);
        System.out.println(g.getSource(edge));
        System.out.println(g.getDestination(edge));
        System.out.println(g.getCost(edge));
        assertEquals(new Character('b'), g.getDestination(edge));
        assertEquals(2.0, g.getCost(edge), 0);

    }
}

