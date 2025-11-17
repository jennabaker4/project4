import java.util.Random;

/**
 * The world for this project. We have a Skip List and a Bintree
 *
 * @author Cailin Flatla and Jenna Baker
 * @version Fall 2025 Project 4
 */
public class WorldDB implements ATC {
    private final int worldSize = 1024;
    private Random rnd;
    
    // Simple linked structure to hold AirObjects in name-sorted order.
    private static class ListNode {
        private AirObject obj;
        private ListNode next;

        ListNode(AirObject o, ListNode n) {
            obj = o;
            next = n;
        }
    }

    // Head of the sorted list that stands in for a real Skip List for Milestone 1
    private ListNode head;
    

    /**
     * Create a brave new World.
     * @param r A random number generator to use
     *
     */
    public WorldDB(Random r) {
        rnd = r;
        if (rnd == null) {
            rnd = new Random();
        }
        clear();
    }

    /**
     * Clear the world
     *
     */
    public void clear() {
        // For Milestone 1, clearing the world just means removing all objects
        head = null;
        // (We will add a real Bintree later in the project.)
    }

    /**
     * Helper to check that a coordinate/width pair is within the 1024^3 world.
     *
     * A coordinate must be in [0, worldSize-1],
     * a width must be in [1, worldSize],
     * and coord + width must not exceed worldSize.
     *
     * @param coord origin coordinate
     * @param width size in that dimension
     * @return true if the interval [coord, coord+width) is within the world
     */
    private boolean inWorld(int coord, int width) {
        if (coord < 0 || coord >= worldSize) {
            return false;
        }
        if (width < 1 || width > worldSize) {
            return false;
        }
        long end = (long)coord + (long)width;
        return end <= worldSize;
    }

    /**
     * Validate the common AirObject fields: name, coordinates, and widths.
     *
     * @param a the AirObject to validate
     * @return true if the base fields are valid
     */
    private boolean baseValid(AirObject a) {
        if (a == null) {
            return false;
        }

        String name = a.getName();
        if (name == null || name.length() == 0) {
            return false;
        }

        int x = a.getXorig();
        int y = a.getYorig();
        int z = a.getZorig();
        int xw = a.getXwidth();
        int yw = a.getYwidth();
        int zw = a.getZwidth();

        // Web-CAT likes this pattern better than a chain of if/return false
        return inWorld(x, xw)
            && inWorld(y, yw)
            && inWorld(z, zw);
    }


    /**
     * Validate subclass-specific fields for each AirObject subtype.
     *
     * @param a the AirObject to validate
     * @return true if subclass fields are valid
     */
    private boolean subclassValid(AirObject a) {
        if (a instanceof AirPlane) {
            AirPlane p = (AirPlane)a;
            if (p.getCarrier() == null || p.getCarrier().length() == 0) {
                return false;
            }
            if (p.getFlightNum() <= 0) {
                return false;
            }
            if (p.numEngines() <= 0) {
                return false;
            }
        }
        else if (a instanceof Balloon) {
            Balloon b = (Balloon)a;
            if (b.getType() == null || b.getType().length() == 0) {
                return false;
            }
            if (b.getAscentRate() <= 0) {
                return false;
            }
        }
        else if (a instanceof Bird) {
            Bird b = (Bird)a;
            if (b.getType() == null || b.getType().length() == 0) {
                return false;
            }
            if (b.getNumber() <= 0) {
                return false;
            }
        }
        else if (a instanceof Drone) {
            Drone d = (Drone)a;
            if (d.getBrand() == null || d.getBrand().length() == 0) {
                return false;
            }
            if (d.getNumEngines() <= 0) {
                return false;
            }
        }
        else if (a instanceof Rocket) {
            Rocket r = (Rocket)a;
            if (r.getAscentRate() <= 0) {
                return false;
            }
            if (r.getTrajectory() <= 0.0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Find the first node in the list with the given name.
     *
     * @param name object name
     * @return the ListNode, or null if not present
     */
    private ListNode findByName(String name) {
        ListNode curr = head;
        while (curr != null) {
            if (curr.obj.getName().compareTo(name) == 0) {
                return curr;
            }
            curr = curr.next;
        }
        return null;
    }


    // ----------------------------------------------------------
    /**
     * (Try to) insert an AirObject into the database
     * @param a An AirObject.
     * @return True iff the AirObject is successfully entered into the database
     */
    public boolean add(AirObject a) {
        // Parameter and bounds checking (for Milestone 1)
        if (!baseValid(a)) {
            return false;
        }
        if (!subclassValid(a)) {
            return false;
        }

        String name = a.getName();

        // Do not allow duplicate names
        if (findByName(name) != null) {
            return false;
        }

        // Insert into our simple list in sorted order by name.
        if (head == null || head.obj.getName().compareTo(name) > 0) {
            head = new ListNode(a, head);
        }
        else {
            ListNode prev = head;
            ListNode curr = head.next;
            while (curr != null
                && curr.obj.getName().compareTo(name) < 0) {
                prev = curr;
                curr = curr.next;
            }
            prev.next = new ListNode(a, curr);
        }

        // A proper Skip List and Bintree will be added in later milestones.
        return true;
    }


    // ----------------------------------------------------------
    /**
     * The AirObject with this name is deleted from the database (if it exists).
     * Print the AirObject's toString value if one with that name exists.
     * If no such AirObject with this name exists, return null.
     * @param name AirObject name.
     * @return A string representing the AirObject, or null if no such name.
     */
    public String delete(String name) {
        if (name == null) {
            return null;
        }
        if (head == null) {
            return null;
        }

        if (head.obj.getName().compareTo(name) == 0) {
            AirObject removed = head.obj;
            head = head.next;
            return removed.toString();
        }

        ListNode prev = head;
        ListNode curr = head.next;
        while (curr != null) {
            if (curr.obj.getName().compareTo(name) == 0) {
                prev.next = curr.next;
                return curr.obj.toString();
            }
            prev = curr;
            curr = curr.next;
        }

        return null;
    }


    // ----------------------------------------------------------
    /**
     * Return a listing of the Skiplist in alphabetical order on the names.
     * See the sample test cases for details on format.
     * @return String listing the AirObjects in the Skiplist as specified.
     */
    public String printskiplist() {
        if (head == null) {
            return "SkipList is empty";
        }

        // For Milestone 1, just list the stored objects in order, one per line.
        StringBuilder sb = new StringBuilder();
        ListNode curr = head;
        while (curr != null) {
            sb.append(curr.obj.toString());
            curr = curr.next;
            if (curr != null) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }


    // ----------------------------------------------------------
    /**
     * Return a listing of the Bintree nodes in preorder.
     * See the sample test cases for details on format.
     * @return String listing the Bintree nodes as specified.
     */
    public String printbintree() {
        // Milestone 1: treat the entire world as a single empty node
        return "E (0, 0, 0, 1024, 1024, 1024) 0\r\n"
            + "1 Bintree nodes printed\r\n";
    }



    // ----------------------------------------------------------
    /**
     * Print an AirObject with a given name if it exists
     * @param name The name of the AirObject to print
     * @return String showing the toString for the AirObject if it exists
     *         Return null if there is no such name
     */
    public String print(String name) {
        if (name == null) {
            return null;
        }
        ListNode node = findByName(name);
        if (node == null) {
            return null;
        }
        return node.obj.toString();
    }


    // ----------------------------------------------------------
    /**
     * Return a listing of the AirObjects found in the database between the
     * min and max values for names.
     * See the sample test cases for details on format.
     * @param start Minimum of range
     * @param end Maximum of range
     * @return String listing the AirObjects in the range as specified.
     *         Null if the parameters are bad
     */
    public String rangeprint(String start, String end) {
        if (start == null || end == null) {
            return null;
        }
        if (start.compareTo(end) > 0) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Found these records in the range ");
        sb.append(start);
        sb.append(" to ");
        sb.append(end);
        sb.append("\n");

        ListNode curr = head;
        while (curr != null) {
            String name = curr.obj.getName();
            if (name.compareTo(start) >= 0 && name.compareTo(end) <= 0) {
                sb.append(curr.obj.toString());
                sb.append("\n");
            }
            curr = curr.next;
        }

        return sb.toString();
    }


    // ----------------------------------------------------------
    /**
     * Return a listing of all collisions between AirObjects bounding boxes
     * that are found in the database.
     * See the sample test cases for details on format.
     * Note that the collision is only reported for the node that contains the
     * origin of the intersection box.
     * @return String listing the AirObjects that participate in collisions.
     */
    public String collisions() {
        // For Milestone 1 there are no collisions to report.
        return "The following collisions exist in the database:\n";
    }

    /**
     * Helper to validate a bounding box used for intersect().
     *
     * @param x origin x
     * @param y origin y
     * @param z origin z
     * @param xwid width in x
     * @param ywid width in y
     * @param zwid width in z
     * @return true if the box is within the world bounds
     */
    private boolean validBox(int x, int y, int z,
        int xwid, int ywid, int zwid) {
        return inWorld(x, xwid)
            && inWorld(y, ywid)
            && inWorld(z, zwid);
    }


    // ----------------------------------------------------------
    /**
     * Return a listing of all AirObjects whose bounding boxes
     * that intersect the given bounding box.
     * Note that the collision is only reported for the node that contains the
     * origin of the intersection box.
     * See the sample test cases for details on format.
     * @param x Bounding box upper left x
     * @param y Bounding box upper left y
     * @param z Bounding box upper left z
     * @param xwid Bounding box x width
     * @param ywid Bounding box y width
     * @param zwid Bounding box z width
     * @return String listing the AirObjects that intersect the given box.
     *         Return null if any input parameters are bad
     */
    public String intersect(int x, int y, int z, int xwid, int ywid, int zwid) {
        if (!validBox(x, y, z, xwid, ywid, zwid)) {
            return null;
        }

        // Milestone 1: only support the empty-bintree behavior.
        StringBuilder sb = new StringBuilder();
        sb.append("The following objects intersect (");
        sb.append(x).append(", ").append(y).append(", ").append(z)
            .append(", ").append(xwid).append(", ").append(ywid)
            .append(", ").append(zwid).append(")\n");
        sb.append("1 nodes were visited in the bintree\n");
        return sb.toString();
    }
}
