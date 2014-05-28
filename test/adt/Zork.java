
// Zork
// test subject for Graph interface and dfs
// ---------------------------------------------------------------------



package adt;


import lib.Test;

import java.util.Collection;
import java.util.ArrayList;


// A map of locations - rooms and connections between them.
// A room has a set of exits, which is just a list of other rooms.
public class Zork implements Graph<Zork.Room, Zork.Exit> {

    // data
    private Collection<Room> rooms = new ArrayList<>();

    // methods
    public Collection<Room> getRooms() { return rooms; }
    public Collection<Exit> getExits(Room room) { return room.exits; }

    public Room addRoom(String name) {
        Room room = new Room(name);
        rooms.add(room);
        return room;
    }

    public Exit addExit(Room source, Room destination, int cost) {
        Exit exit = source.addExit(destination, cost);
        return exit;
    }
    public Exit addExit(Room source, Room destination) { return addExit(source, destination, 0); }

//    public String toString() { return Graph.asString(this); }
    public String toString() { return Graph.asGraphviz(this); }


    // Graph interface
    // convert Rooms to Nodes and Exits to Edges
    @Override public Collection<Room> getNodes() { return getRooms(); }
    @Override public Collection<Exit> getEdges(Room room) { return getExits(room); }
    @Override public Room getDestination(Exit exit) { return exit.getDestination(); }




    class Room {
        // data
        private String name;
        private Collection<Exit> exits = new ArrayList<>();
        // constructor
        public Room(String name) { this.name = name; }
        // methods
        public Exit addExit(Room destination, int cost) {
            Exit exit = new Exit(destination, cost);
            exits.add(exit);
            return exit;
        }
        //    public Exit addExit(Room destination) { return addExit(destination, 0); }
        public String toString() { return this.name; }
    }



    class Exit {
        // data
        private Room destination;
        private int cost;
        // constructor
        public Exit(Room destination, int cost) { this.destination = destination; this.cost = cost; }
        // methods
        public Room getDestination() { return destination; }
        public String toString() { return destination.toString(); }
    }



    // testing
    public static void test() {

        Zork z = new Zork();

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


        System.out.println("running dfs...");
        Test.test(Graph.dfs(z, foyer, willsoffice), willsoffice);
        Test.test(Graph.dfs(z, foyer, roof), null);
        // could pass a nullobject?
//        Room notfound = new Room("not found");
//        Test.test(Graph.dfs(z, foyer, roof, notfound), null);

        System.out.println("running bfs...");
        Test.test(Graph.bfs(z, foyer, willsoffice), willsoffice);
        Test.test(Graph.bfs(z, foyer, roof), null);


        System.out.println("done");
    }



    public static void main(String[] args) {
        Zork.test();
    }
}


