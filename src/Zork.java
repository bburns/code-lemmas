
// Zork
// test subject for Graph interface and dfs
// ---------------------------------------------------------------------


import java.util.Collection;
import java.util.ArrayList;



class Room {
    // data
    public String name;
    Collection<Exit> exits = new ArrayList<Exit>();
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
    public int cost;
    public Room destination;
    // constructor
    public Exit(Room destination, int cost) { this.destination = destination; this.cost = cost; }
    // methods
    public Room getDestination() { return destination; }
    public String toString() { return destination.toString(); }
}



public class Zork implements Graph<Room, Exit> {

    // data
    Collection<Room> rooms = new ArrayList<Room>();


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

    public String toString() { return Graphs.asString(this); }


    // Graph interface
    // convert Rooms to Nodes and Exits to Edges
    @Override public Collection<Room> getNodes() { return getRooms(); }
    @Override public Collection<Exit> getEdges(Room room) { return getExits(room); }
    @Override public Room getDestination(Exit exit) { return exit.getDestination(); }


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

        System.out.println(z);

        System.out.println("running dfs...");

        Test.test(Graphs.dfs(z, foyer, willsoffice), willsoffice);
        Test.test(Graphs.dfs(z, foyer, roof), null);

        // could pass a nullobject
//        Room notfound = new Room("not found");
//        Test.test(Graphs.dfs(z, foyer, roof, notfound), null);

        System.out.println("done");
    }



    public static void main(String[] args) {
        Zork.test();
    }
}


