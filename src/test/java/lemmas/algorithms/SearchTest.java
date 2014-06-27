
// Test of search algorithms


package lemmas.algorithms;

//import lemmas.adt.Graph;
import lemmas.ds.UndirectedGraph;

import house.House;


import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import static debug.Test.*;



public class SearchTest {

    House h;

    @Before public void setUp() throws Exception {

        h = new House();

        House.Room foyer = h.addRoom("foyer");
        House.Room hallway = h.addRoom("hallway");
        House.Room upstairshall = h.addRoom("upstairs hall");
        House.Room livingroom = h.addRoom("livingroom");
        House.Room kitchen = h.addRoom("kitchen");
        House.Room stairs = h.addRoom("stairs");
        House.Room sarahsoffice = h.addRoom("sarah's office");
        House.Room willsoffice = h.addRoom("will's office");
        House.Room bedroom = h.addRoom("bedroom");
        House.Room backporch = h.addRoom("backporch");
        House.Room backyard = h.addRoom("backyard");
        House.Room shed = h.addRoom("shed");
        House.Room garage = h.addRoom("garage");
        House.Room roof = h.addRoom("roof"); // no connection

        h.addExit(foyer, hallway);
        h.addExit(hallway, livingroom);
        h.addExit(hallway, kitchen);
        h.addExit(hallway, stairs);
        h.addExit(stairs, upstairshall);
        h.addExit(upstairshall, sarahsoffice);
        h.addExit(upstairshall, willsoffice);
        h.addExit(upstairshall, bedroom);
        h.addExit(kitchen, backporch);
        h.addExit(backporch, backyard);
        h.addExit(backyard, shed);
        h.addExit(backyard, garage);

    }



    @Test public void testDFS() throws Exception {
        House.Room foyer = h.getRoom("foyer");
        House.Room willsoffice = h.getRoom("will's office");
        House.Room roof = h.getRoom("roof");

        assertNotNull(foyer);
        assertNotNull(willsoffice);
        assertNotNull(roof);

        assertEquals(Search.dfs(h.g, foyer, willsoffice), willsoffice);
        assertEquals(Search.dfs(h.g, foyer, roof), null);
    }


    @Test public void testBFS() throws Exception {
        House.Room foyer = h.getRoom("foyer");
        House.Room willsoffice = h.getRoom("will's office");
        House.Room roof = h.getRoom("roof");

        assertNotNull(foyer);
        assertNotNull(willsoffice);
        assertNotNull(roof);

        assertEquals(Search.bfs(h.g, foyer, willsoffice), willsoffice);
        assertEquals(Search.bfs(h.g, foyer, roof), null);
    }



    @Test public void testDijkstra() throws Exception {

        UndirectedGraph<Character> g = new UndirectedGraph();
        double d;

        // no nodes - illegal
//        d = Search.dijkstra(g,'a','a');
//        d = Search.dijkstra(g,'a','b');

        // single node
        g.addNode('a');
        d = Search.dijkstra(g,'a','a');
        assertZero(d);
//        d = Search.dijkstra(g,'a','b'); // illegal

        // line
        g.addNode('b');
        g.addEdge('a','b',1);
        d = Search.dijkstra(g,'a','b');
        assertEquals0(1,d);

        // line and node
        g.addNode('c');
        d = Search.dijkstra(g,'a','c');
        assertInfinity(d); // ie can't reach it

        // v
        g.addEdge('a','c',2);
        d = Search.dijkstra(g,'a','c');
        assertEquals0(2,d);

        // triangle
        g.addEdge('b','c',3);
        d = Search.dijkstra(g,'a','c');
        assertEquals0(2,d);

        // diamond
        g.addNode('d');
        g.addEdge('b','d',7);
        g.addEdge('c','d',4);
        d = Search.dijkstra(g,'a','d');
        assertEquals0(6,d);

        System.out.println(g);

    }


}