
// Test search algorithms


package algorithms;


import adt.Graph;


import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;




public class SearchTest {


    Zork z;

    @Before
    public void setUp() throws Exception {

        z = new Zork();

        Zork.Room foyer = z.addRoom("foyer");
        Zork.Room hallway = z.addRoom("hallway");
        Zork.Room upstairshall = z.addRoom("upstairs hall");
        Zork.Room livingroom = z.addRoom("livingroom");
        Zork.Room kitchen = z.addRoom("kitchen");
        Zork.Room stairs = z.addRoom("stairs");
        Zork.Room sarahsoffice = z.addRoom("sarah's office");
        Zork.Room willsoffice = z.addRoom("will's office");
        Zork.Room bedroom = z.addRoom("bedroom");
        Zork.Room backporch = z.addRoom("backporch");
        Zork.Room backyard = z.addRoom("backyard");
        Zork.Room shed = z.addRoom("shed");
        Zork.Room garage = z.addRoom("garage");
        Zork.Room roof = z.addRoom("roof"); // no connection

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



    @Test public void testDFS() throws Exception {
        Zork.Room foyer = z.getRoom("foyer");
        Zork.Room willsoffice = z.getRoom("willsoffice");
        Zork.Room roof = z.getRoom("roof");

        System.out.println("running dfs...");
        assertEquals(Search.dfs(z, foyer, willsoffice), willsoffice);
        assertEquals(Search.dfs(z, foyer, roof), null);
    }


    @Test public void testBFS() throws Exception {
        Zork.Room foyer = z.getRoom("foyer");
        Zork.Room willsoffice = z.getRoom("willsoffice");
        Zork.Room roof = z.getRoom("roof");

        System.out.println("running bfs...");
        assertEquals(Search.bfs(z, foyer, willsoffice), willsoffice);
        assertEquals(Search.bfs(z, foyer, roof), null);
    }

}