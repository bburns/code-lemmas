
// test subject for Graph interface and dfs


import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.Stack;
import java.util.HashSet;


interface Graph<N, E> {
    Collection<N> getNodes();
    Collection<E> getEdges(N node);
    N getDestination(E edge);
}

class Graphs {
    static void nothing() {}
    static <N> N identity(N node) { return node; }
    static <N> N dfs(N node) { return node; }
//    static <N, E> N dfs(Graph<N, E> g, N start, N find) { return start; }

    static <N, E> N dfs(Graph<N, E> g, N start, N find) {
        Stack<N> tosee = new Stack<N>();
        Set<N> seen = new HashSet<N>();
        tosee.push(start);
        while (!tosee.empty()) {
            N n = tosee.pop();
            System.out.println("visiting " + n);
            if (n.equals(find)) return n;
            seen.add(n);
            // adding from left to right, so will be searched right to left - change if that's an issue
            for (E e : g.getEdges(n)) {
                N dest = g.getDestination(e);
                if (!seen.contains(dest)) {
                    tosee.push(dest);
                }
            }
        }
        // can't do due to type erasure...?
//        return new N("NullObject");
        return null;
    }
    static <N, E> String asString(Graph<N, E> g) {
        StringBuilder sb = new StringBuilder();
        for (N n : g.getNodes()) {
            sb.append(n + "->" + g.getEdges(n) + "\n");
        }
        return sb.toString();
    }
}


  class Room {
    public String name;
    List<Exit> exits = new ArrayList<Exit>();
    //        public Room() {};
    public Room(String name) { this.name = name; }
    public String toString() { return this.name; }
    //        public Exit addExit(Room destination) { return addExit(destination, 0); }
    public Exit addExit(Room destination, int cost) {
        Exit exit = new Exit(destination, cost);
        exits.add(exit);
        return exit;
    }
}

  class Exit {
    public int cost;
    public Room destination;
    //        public Exit() {};
    public Exit(Room destination, int cost) { this.destination = destination; this.cost = cost; }
    public Room getDestination() { return destination; }
    public String toString() { return destination.toString(); }
}


public class Zork implements Graph<Room, Exit> {

    List<Room> rooms = new ArrayList<Room>();

    // methods
    public List<Room> getRooms() { return rooms; }
    public List<Exit> getExits(Room room) { return room.exits; }

//    public void addRoom(Room room) { rooms.add(room); }
    public Room addRoom(String name) { Room room = new Room(name); rooms.add(room); return room; }

    public Exit addExit(Room source, Room destination, int cost) {
        Exit exit = source.addExit(destination, cost);
        return exit;
    }
    public Exit addExit(Room source, Room destination) { return addExit(source, destination, 0); }

    public String toString() {
        return Graphs.asString(this);
//        StringBuilder sb = new StringBuilder();
//        for (Room room : getRooms()) {
//            sb.append(room + "->" + getExits(room) + "\n");
//        }
//        return sb.toString();
    }

    // Graph interface
    @Override public List<Room> getNodes() { return getRooms(); }
//    @Override public List<Exit> getEdges(Room room) { return getExits(room); }
    @Override public List<Exit> getEdges(Room room) { return room.exits; }
    @Override public Room getDestination(Exit exit) { return exit.getDestination(); }






    public static void main(String[] args) {

//        Graphs.nothing();
//        Graphs.identity(new Room("patio"));

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

        z.addExit(foyer,hallway);
        z.addExit(hallway,livingroom);
        z.addExit(hallway,kitchen);
        z.addExit(hallway,stairs);
        z.addExit(stairs,upstairshall);
        z.addExit(upstairshall,sarahsoffice);
        z.addExit(upstairshall,willsoffice);
        z.addExit(upstairshall,bedroom);

        System.out.println(z);


        System.out.println("running dfs...");
        {
            Room found = Graphs.dfs(z, foyer, willsoffice);
            assert (found==willsoffice);
        }
        {
            Room attic = z.addRoom("attic"); // no connection
            Room found = Graphs.dfs(z, foyer, attic); // nullobject
            assert (found == null);
        }

        System.out.println("done");
    }

}



// works - hardcoded types
//    static Room dfs(Zork g, Room start, Room find) {
//        Stack<Room> tosee = new Stack<Room>();
//        Set<Room> seen = new HashSet<Room>();
//        tosee.push(start);
//        while (!tosee.empty()) {
//            Room n = tosee.pop();
//            System.out.println("visiting " + n);
//            if (n.equals(find)) return n;
//            seen.add(n);
//            // adding from left to right, so will be searched right to left - change if that's an issue
//            for (Exit e : g.getExits(n)) {
//                Room dest = g.getDestination(e);
//                if (!seen.contains(dest)) {
//                    tosee.push(dest);
//                }
//            }
//        }
//        return new Room("NullObject");
//    }

