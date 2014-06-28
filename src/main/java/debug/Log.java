/** ----------------------------------------------------------------------------
 * Log
 * Simple logging functions - print to System.out
 * ----------------------------------------------------------------------------
 * Lighter weight than slf4j, eg
 *   log.info(g.toString()); // slf4j
 * vs
 *   log(g);
 * ----------------------------------------------------------------------------
 * Usage:
 *   import static debug.Log.*;
 *   logon();
 *   log("hello");
 *   log("room %d", 32);
 *   logoff();
 *   log("goodbye");
 * ----------------------------------------------------------------------------
 */

package debug;


public class Log {

    // turn printing on and off
    public static void logon() { loggingOn = true; }
    public static void logoff() { loggingOn = false; }

    // print methods
    public static void log(Object o) { if (loggingOn) System.out.println(o); }
    public static void log(String format, Object... args) { if (loggingOn) System.out.format(format + "%n", args); }
    public static void log() { if (loggingOn) System.out.println(); }

    // printing status
    private static boolean loggingOn = true;

}


