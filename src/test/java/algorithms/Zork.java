
// Zork
// test subject for Graph interface and dfs
// ---------------------------------------------------------------------

package algorithms;


import java.util.Collection;
import java.util.ArrayList;

import adt.Graph;



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
    public Room getRoom(String name) {
        for (Room r : rooms) if (name.equals(r.name)) return r;
        return null; //. use nullobject
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
    @Override public double getCost(Exit exit) { return 1.0; }


    public class Room {
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
        public boolean equals(Room other) {
            return (name.equals(other.name));
        }
        //    public Exit addExit(Room destination) { return addExit(destination, 0); }
        public String toString() { return this.name; }
    }


    public class Exit {
        // data
        private Room destination;
        private int cost;
        // constructor
        public Exit(Room destination, int cost) { this.destination = destination; this.cost = cost; }
        // methods
        public Room getDestination() { return destination; }
        public String toString() { return destination.toString(); }
    }

}


