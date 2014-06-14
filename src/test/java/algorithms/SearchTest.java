
// Test search algorithms


package algorithms;


//import adt.Graph;
//import ds.GraphUndirected;
//import ds.Zork;

//import static test.Test.*;

import ds.GraphUndirected;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static test.Test.assertEquals0;
import static test.Test.assertInfinity;
import static test.Test.assertZero;



public class SearchTest {


    // TODO put this code in a House class in test.algorithms

//    Zork z;
//
//    @Before
//    public void setUp() throws Exception {
//
//        z = new Zork();
//
//        Zork.Room foyer = z.addRoom("foyer");
//        Zork.Room hallway = z.addRoom("hallway");
//        Zork.Room upstairshall = z.addRoom("upstairs hall");
//        Zork.Room livingroom = z.addRoom("livingroom");
//        Zork.Room kitchen = z.addRoom("kitchen");
//        Zork.Room stairs = z.addRoom("stairs");
//        Zork.Room sarahsoffice = z.addRoom("sarah's office");
//        Zork.Room willsoffice = z.addRoom("will's office");
//        Zork.Room bedroom = z.addRoom("bedroom");
//        Zork.Room backporch = z.addRoom("backporch");
//        Zork.Room backyard = z.addRoom("backyard");
//        Zork.Room shed = z.addRoom("shed");
//        Zork.Room garage = z.addRoom("garage");
//        Zork.Room roof = z.addRoom("roof"); // no connection
//
//        z.addExit(foyer,hallway);
//        z.addExit(hallway,foyer);
//        z.addExit(hallway,livingroom);
//        z.addExit(hallway,kitchen);
//        z.addExit(hallway,stairs);
//        z.addExit(stairs,upstairshall);
//        z.addExit(upstairshall,sarahsoffice);
//        z.addExit(upstairshall,willsoffice);
//        z.addExit(upstairshall,bedroom);
//        z.addExit(kitchen,backporch);
//        z.addExit(backporch,backyard);
//        z.addExit(backyard,shed);
//        z.addExit(backyard,garage);
//
//        System.out.println(z);
//    }
//
//
//
//    @Test public void testDFS() throws Exception {
//        Zork.Room foyer = z.getRoom("foyer");
//        Zork.Room willsoffice = z.getRoom("willsoffice");
//        Zork.Room roof = z.getRoom("roof");
//
//        System.out.println("running dfs...");
//        assertEquals(Search.dfs(z, foyer, willsoffice), willsoffice);
//        assertEquals(Search.dfs(z, foyer, roof), null);
//    }
//
//
//    @Test public void testBFS() throws Exception {
//        Zork.Room foyer = z.getRoom("foyer");
//        Zork.Room willsoffice = z.getRoom("willsoffice");
//        Zork.Room roof = z.getRoom("roof");
//
//        System.out.println("running bfs...");
//        assertEquals(Search.bfs(z, foyer, willsoffice), willsoffice);
//        assertEquals(Search.bfs(z, foyer, roof), null);
//    }


//    // Get shortest distance from start to target in given graph.
//    // Just a wrapper around Search.dijkstra.
//    // Search.dijkstra(g, g.findNode(start), g.findNode(target));
//    private static double dijkstra(GraphUndirected g, Object start, Object target) {
//        GraphUndirected.Node nstart = g.findNode(start);
//        GraphUndirected.Node ntarget = g.findNode(target);
//        return Search.dijkstra(g, nstart, ntarget);
//    }
//
//
//
//    @Test public void testDijkstra() throws Exception {
//
//        GraphUndirected g = new GraphUndirected();
//        double d;
//
//        // no nodes - illegal
////        d = dijkstra(g,'a','a');
////        d = dijkstra(g,'a','b');
//
//        // single node
//        g.addNode('a');
//        d = dijkstra(g,'a','a');
//        assertZero(d);
////        d = dijkstra(g,'a','b'); // illegal
//
//        // line
//        g.addNode('b');
//        g.addEdge('a','b',1);
//        d = dijkstra(g,'a','b');
//        assertEquals0(1,d);
//
//        // line and node
//        g.addNode('c');
//        d = dijkstra(g,'a','c');
//        assertInfinity(d); // ie can't reach it
//
//        // v
//        g.addEdge('a','c',2);
//        d = dijkstra(g,'a','c');
//        assertEquals0(2,d);
//
//        // triangle
//        g.addEdge('b','c',3);
//        d = dijkstra(g,'a','c');
//        assertEquals0(2,d);
//
//        // diamond
//        g.addNode('d');
//        g.addEdge('b','d',7);
//        g.addEdge('c','d',4);
//        d = dijkstra(g,'a','d');
//        assertEquals0(6,d);
//
//        System.out.println(g);
//
//    }


}