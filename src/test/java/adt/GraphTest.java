
// Test of Graph interface

package adt;


import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


//import static test.adt.Zork.*;
//import Zork.Room;
import adt.Zork.Room;



public class GraphTest {

    Zork z;

    @Before
    public void setUp() throws Exception {

        z = new Zork();

        Room foyer = z.addRoom("foyer");
        Room hallway = z.addRoom("hallway");
        Room upstairshall = z.addRoom("upstairs hall");
        Room livingroom = z.addRoom("livingroom");
        Room kitchen = z.addRoom("kitchen");
        Room stairs = z.addRoom("stairs");
        Room sarahsoffice = z.addRoom("sarah's office");
        Room willsoffice = z.addRoom("will's office");
        Room bedroom = z.addRoom("bedroom");
        Room backporch = z.addRoom("backporch");
        Room backyard = z.addRoom("backyard");
        Room shed = z.addRoom("shed");
        Room garage = z.addRoom("garage");
        Room roof = z.addRoom("roof"); // no connection

        z.addExit(foyer,hallway);
        z.addExit(hallway,foyer);
        z.addExit(hallway,livingroom);
        z.addExit(hallway,kitchen);
        z.addExit(hallway,stairs);
        z.addExit(stairs,upstairshall);
        z.addExit(upstairshall,sarahsoffice);
        z.addExit(upstairshall,willsoffice);
        z.addExit(upstairshall,bedroom);
        z.addExit(kitchen,backporch);
        z.addExit(backporch,backyard);
        z.addExit(backyard,shed);
        z.addExit(backyard,garage);

        System.out.println(z);
    }


    @Test
    public void testDFS() throws Exception {

//        assertEquals(1,1);

        Room foyer = z.getRoom("foyer");
        Room willsoffice = z.getRoom("willsoffice");
        Room roof = z.getRoom("roof");

        System.out.println("running dfs...");
//        lib.Test.test(Graph.dfs(z, foyer, willsoffice), willsoffice);
        assertEquals(Graph.dfs(z, foyer, willsoffice), willsoffice);

//        lib.Test.test(Graph.dfs(z, foyer, roof), null);
        assertEquals(Graph.dfs(z, foyer, roof), null);
        // could pass a nullobject?
//        Room notfound = new Room("not found");
//        Test.test(Graph.dfs(z, foyer, roof, notfound), null);
    }


    @Test
    public void testBFS() throws Exception {

        Room foyer = z.getRoom("foyer");
        Room willsoffice = z.getRoom("willsoffice");
        Room roof = z.getRoom("roof");

        System.out.println("running bfs...");
        assertEquals(Graph.bfs(z, foyer, willsoffice), willsoffice);
        assertEquals(Graph.bfs(z, foyer, roof), null);

    }



//    @Test
//    public void testDijkstra() throws Exception { }


//    @Test public void testA() throws Exception { assertEquals(1,1); }
//    @Test public void testB() throws Exception { assertEquals(1,0); }
//    @Test public void testC() throws Exception { assertEquals(1,0); }

}
