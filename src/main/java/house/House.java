
// House
// test subject for Graph interface and dfs
// ---------------------------------------------------------------------

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

//    private UndirectedGraph<Room> g = new UndirectedGraph<>();
    public UndirectedGraph<Room> g = new UndirectedGraph<>();
//    public Graph<Room> g = new UndirectedGraph<>();

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
//        g.getEdges(room);
//    }
//    @Override public List<Edge<N>> getEdges(N node) {
        // each node stores a list of destination nodes, while an Edge class
        // stores a source node, a target node, and a cost, so need to create a new Edge
        // for each target
        // inefficient, but okay for small graphs
        // TODO use index or multimap for speed
//        List<N> nlist = getDestinations(node);
//        List<Edge<N>> elist = new ArrayList<>();
//        for (N n : nlist) { Edge<N> e = new Edge<>(node, n); elist.add(e); }
//        return elist;
        List<Exit> exits = new ArrayList<>();
        List<DirectedGraph.Edge<Room>> edges = g.getEdges(room);
        for (DirectedGraph.Edge<Room> edge : edges) {
            Exit exit = new Exit(g.getDestination(edge), g.getCost(edge));
        }
//        for (N n : nlist) { Edge<N> e = new Edge<>(node, n); elist.add(e); }
        return exits;
    }



    public static class Room {
//        UndirectedGraph<Room> g;
        private String name;
        public Room(String name) { this.name = name; }
        public String toString() { return this.name; }
//        public List<E> getExits() { return g.getExits(this); }
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


