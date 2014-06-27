
// House
// test subject for Graph interface and dfs
// ---------------------------------------------------------------------

package house;


import lemmas.ds.UndirectedGraph;

import java.util.*;


/** House
 * A map of locations - rooms and connections between them.
 * A room has a set of exits, which is a list of other rooms.
 */
public class House {

//    private UndirectedGraph<Room> g = new UndirectedGraph<>();
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

////    public Room getRoom(String name) {
////       // for (Room r : rooms) if (name.equals(r.name)) return r;
////       // return null; //. use nullobject
////       // TODO add getNode method to Graph
////        return g.getNode(name);
////    }

    public boolean addExit(Room source, Room destination) { return addExit(source, destination, 1); }
    public boolean addExit(Room source, Room destination, int cost) {
        Exit exit = new Exit(source, destination, cost);
        return g.addEdge(exit);
    }


    public static class Room {
        private String name;
        public Room(String name) { this.name = name; }
        public String toString() { return this.name; }
//        public List<UndirectedGraph.Edge<Room>> getExits() { return getEdges(this); }
//        public List<Exit> getExits() { return g.getEdges(this); }
    }


    public static class Exit extends DirectedGraph.Edge<Room> {
        public Exit(Room source, Room destination) { super(source, destination); }
        public Exit(Room source, Room destination, double cost) { super(source, destination, cost); }
//        public String toString() { return destination.toString(); }
    }


    public String toString() { return Graph.asString(g); }
    public String toGraphviz() { return Graph.asGraphviz(g, false); }

}


