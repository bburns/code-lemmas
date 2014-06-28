/**
 * House
 * test subject for Graph interface and dfs
 * ---------------------------------------------------------------------
 */

package house;


import lemmas.adt.Graph;
import lemmas.ds.DirectedGraph;
import lemmas.ds.UndirectedGraph;

import java.util.*;


/**
 * House
 * A map of locations - rooms and connections between them.
 * A room has a set of exits, which is a list of other rooms.
 */
public class House {

    // TODO should be able to use Graph type for g
//    public Graph<Room> g = new UndirectedGraph<>();
    public UndirectedGraph<Room> g = new UndirectedGraph<>();

    public Room addRoom(String name) {
        Room room = new Room(name);
        g.addNode(room);
        return room;
    }

    public Room getRoom(String name) {
        List<Room> rooms = g.getNodes();
        for (Room r : rooms) { if (name.equals(r.name)) return r; }
        return null; // TODO use nullobject
    }

    public boolean addExit(Room source, Room destination, int cost) {
        return g.addEdge(source, destination, cost);
    }

    public boolean addExit(Room source, Room destination) { return addExit(source, destination, 0); }

    public List<Exit> getExits(Room room) {
        // get Edges from graph, convert them to Exits. 
        // inefficient, but okay for small graphs.
        List<DirectedGraph.Edge<Room>> edges = g.getEdges(room);
        List<Exit> exits = new ArrayList<>();
        for (DirectedGraph.Edge<Room> edge : edges) {
            Exit exit = new Exit(g.getDestination(edge), g.getCost(edge));
        }
        return exits;
    }


    public static class Room {
        private String name;
        public Room(String name) { this.name = name; }
        public String toString() { return this.name; }
        // TODO need getExits fn
        // UndirectedGraph<Room> g;
        // public List<E> getExits() { return g.getExits(this); }
    }

    public static class Exit {
        private Room destination;
        private double cost;
        public Exit(Room destination, double cost) { this.destination = destination; this.cost = cost; }
        public String toString() { return destination.toString(); }
    }

    public String toString() { return g.toString(); }
    public String toGraphviz() { return g.toGraphviz(); }

}


