

import java.util.ArrayList;
import java.util.List;


// implements Graph
// implements Graph<Place, Road> ?
// so would add getNodes, getEdges
public class Tate {


//    List<Place> getNodes() { return places; }


    static class Place {
        String name;
        Place(String name) { this.name = name; }
//        void addRoad(String direction, Place destination, );
        @Override public String toString() { return name; }
//        List<Place> getEdges() {
//        }
    }


    static class Road {
        Place source;
        Place destination;
//        String direction;
        int distance;
        Road(Place source, Place destination, int distance) {
            this.source = source;
            this.destination = destination;
            this.distance = distance;
        }
    }


    ArrayList<Place> places = new ArrayList<Place>();
    ArrayList<Road> roads = new ArrayList<Road>();


    // represent tate as a string, like a->[b,c] b->[d], etc
//    public String toString() {
//        StringBuilder sb = new StringBuilder();
//        for (Place place : places) {
//            sb.append(place + "->" + place.roads + "\n");
//        }
//        return sb.toString();
//    }




    public static void main(String[] args) {

        System.out.println("Tate");

        Tate tate = new Tate();

        Place london = new Place("London");
        Place dover = new Place("Dover");

        tate.places.add(london);
        tate.places.add(dover);

        Road r1 = new Road(london, dover, 76);
        tate.roads.add(r1);

        System.out.println(london);
        System.out.println(r1);
        System.out.println(tate);

//        london.addDest(dover, 76);


    }
}



