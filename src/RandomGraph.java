

//
//import java.util.List;
//import java.util.ArrayList;
//
//
//
//public class RandomGraph implements Graph<RandomGraph.Room, RandomGraph.Exit> {
////public class Zork  {
//
//    public static class Room {}
//    public static class Exit {
//        public Room getDestination() { return new Room(); }
//    }
//
//    List<Room> rooms = new ArrayList<Room>();
//
//    public List<Room> getRooms() { return rooms; }
//    public List<Exit> getExits(Room room) { return new ArrayList<Exit>(); }
//    public void addRoom (Room room) { rooms.add(room); }
//    public Room getDestination(Exit exit) { return exit.getDestination(); }
//
//    // graph interface
////    public List<Room> getNodes() { return getRooms(); }
//    public List<Exit> getEdges(Room room) { return getExits(room); }
//
//
//
//    public static void main(String[] args) {
//        Zork z = new Zork();
//        Room r = new Room();
//        Exit e = new Exit();
//        z.addRoom(r);
//        List<Exit> exits = z.getEdges(r);
//    }
//
//}
