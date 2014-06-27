
Lemmas
====================

Implementing some data structures and algorithms in Java. In a way these correspond to lemmas in mathematics - a step up from axioms, and used to construct larger theorems - with the built in data types and operations as axioms, and programs as theorems. They are implemented better elsewhere, of course - these are just sketches. 

And for those who like definitions...

**lem&#183;ma** (plural lem&#183;mas or lem&#183;ma&#183;ta), noun. a proposition proved or accepted for immediate use in the proof of some other proposition; a helping theorem. 1560-70;  < Latin:  theme, title, epigram < Ancient Greek &#955;&#951;&#956;&#956;&#945;, something received, premise. [1]


Contents
--------------------

* Abstract Data Types
    * [Graph](https://github.com/bburns/code-lemmas/blob/master/src/adt/Graph.java)
* Data Structures
    * Trie
* Sort
    * Bubble Sort
    * Counting Sort
* Search
    * Depth First Search
    * Breadth First Search

Use
--------------------

**Graph**

A given class can be converted to act as a graph by implementing the Graph interface. Nodes and edges can be stored in any format, as long as the Graph methods are implemented. For example, 

```java
public class Zork **implements Graph<Zork.Room, Zork.Exit>** {

    // zork internal classes
    class Room { private String name; ... }
    class Exit { private Room destination; ... }

    // zork data
    private Collection<Room> rooms = new ArrayList<>();

    // zork methods
    public Collection<Room> getRooms() { return rooms; }
    public Collection<Exit> getExits(Room room) { return room.exits; }
    
    // implement Graph interface
    **@Override public Collection<Room> getNodes() { return getRooms(); }**
    **@Override public Collection<Exit> getEdges(Room room) { return getExits(room); }**
    **@Override public Room getDestination(Exit exit) { return exit.getDestination(); }**
}
```


Testing
--------------------




Tools used
--------------------

Java Collections Framework, JUnit, slf4j, Gradle, git, IntelliJ IDEA. 



[1] http://dictionary.reference.com/browse/lemma?s=t



