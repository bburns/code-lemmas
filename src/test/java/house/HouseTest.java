
// Test of House


package house;



import algorithms.*; // Sort, Search, ..


import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import static debug.Debug.*;



public class HouseTest {

    @Before public void setUp() throws Exception {

        House h = new House();

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

//        List<Exit> foyer.getExits();

//        p(h);
//        p(h.toGraphviz());

//        Search.dfs(g, )

    }


    @Test public void test() throws Exception {

    }
}