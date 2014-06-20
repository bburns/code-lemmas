
// House
// test subject for Graph interface and dfs
// ---------------------------------------------------------------------

package house;


import adt.Graph;
import ds.UndirectedGraph;

import java.util.*;

import static test.Test.*;
import static debug.Debug.*;


/** House
 * A map of locations - rooms and connections between them.
 * A room has a set of exits, which is a list of other rooms.
 */
public class House {

    private UndirectedGraph<Room> g = new UndirectedGraph<>();

    public Room addRoom(String name) {
        Room room = new Room(name);
        g.addNode(room);
        return room;
    }
    
//    public Room getRoom(String name) {
//       // for (Room r : rooms) if (name.equals(r.name)) return r;
//       // return null; //. use nullobject
//       // TODO add getNode method to Graph
//        return g.getNode(name);
//    }

    public boolean addExit(Room source, Room destination, int cost) {
        return g.addEdge(source, destination, cost);
    }

    public boolean addExit(Room source, Room destination) { return addExit(source, destination, 0); }



    public static class Room {
//        UndirectedGraph<Room> g;
        private String name;
        public Room(String name) { this.name = name; }
        public String toString() { return this.name; }
//        public List<E> getExits() { return g.getExits(this); }
    }

    public static class Exit {
        private Room destination;
        private int cost;
        public Exit(Room destination, int cost) { this.destination = destination; this.cost = cost; }
        public String toString() { return destination.toString(); }
    }

    public String toString() { return g.toString(); }
    public String toGraphviz() { return g.toGraphviz(); }

}


