// ----------------------------------------------------------------------------
// Debug
// simple debug print functions
// ----------------------------------------------------------------------------
//
// Logging with slf4j is too heavy in some cases, eg
// log.info(g.toString());
// vs
// p(g);
//
// Usage:
// import static debug.Debug.*;
// pon();
// p("hello");
// p("room %d", 32);
// poff();
// p("goodbye");
// ----------------------------------------------------------------------------


// TODO merge with Test class


package debug;


public class Debug {

    // turn printing on and off
    public static void pon() { printingOn = true; }
    public static void poff() { printingOn = false; }

    // print methods
    public static void p(Object o) { if (printingOn) System.out.println(o); }
    public static void p(String format, Object... args) { if (printingOn) System.out.format(format + "%n", args); }
    public static void p() { if (printingOn) System.out.println(); }

    // printing status
    private static boolean printingOn = true;

}




