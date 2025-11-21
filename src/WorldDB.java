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

    // Skip list storing AirObjects keyed by their name
    private SkipList<String, AirObject> skip;
    
    

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
        // Reinitialize an empty skip list
        skip = new SkipList<String, AirObject>(rnd);
        // Bintree will be added in later milestones
    }


    /**
     * Helper to check that a coordinate/width pair is within the world.
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
            // carrier must not be null, but may be empty
            if (p.getCarrier() == null) {
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
            // type must not be null, but may be empty
            if (b.getType() == null) {
                return false;
            }
            if (b.getAscentRate() <= 0) {
                return false;
            }
        }
        else if (a instanceof Bird) {
            Bird b = (Bird)a;
            // type must not be null, but may be empty
            if (b.getType() == null) {
                return false;
            }
            if (b.getNumber() <= 0) {
                return false;
            }
        }
        else if (a instanceof Drone) {
            Drone d = (Drone)a;
            // brand must not be null, but may be empty
            if (d.getBrand() == null) {
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
     * (Try to) insert an AirObject into the database
     * @param a An AirObject.
     * @return True iff the AirObject is successfully entered into the database
     */
    public boolean add(AirObject a) {
        // Parameter and bounds checking
        if (!baseValid(a)) {
            return false;
        }
        if (!subclassValid(a)) {
            return false;
        }

        String name = a.getName();

        // No duplicate names allowed
        if (skip.find(name) != null) {
            return false;
        }

        // Insert into skip list, keyed by name
        skip.insert(name, a);
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

        AirObject removed = skip.delete(name);
        if (removed == null) {
            return null;
        }
        return removed.toString();
    }


    // ----------------------------------------------------------
    /**
     * Return a listing of the Skiplist in alphabetical order on the names.
     * See the sample test cases for details on format.
     * @return String listing the AirObjects in the Skiplist as specified.
     */
    public String printskiplist() {
        if (skip == null || skip.isEmpty()) {
            return "SkipList is empty";
        }
        return skip.print();
    }


    // ----------------------------------------------------------
    /**
     * Return a listing of the Bintree nodes in preorder.
     * See the sample test cases for details on format.
     * @return String listing the Bintree nodes as specified.
     */
    public String printbintree() {
        // Milestone 2: still treat the entire world as a single empty node
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
        AirObject a = skip.find(name);
        if (a == null) {
            return null;
        }
        return a.toString();
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

        SkipList.SkipNode<String, AirObject> node =
            skip.getHead().forward[0];

        while (node != null) {
            String name = node.key;
            if (name.compareTo(start) >= 0 && name.compareTo(end) <= 0) {
                sb.append(node.value.toString());
                sb.append("\n");
            }
            node = node.forward[0];
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
        // For Milestone 2 there are still no collisions to report.
        return "The following collisions exist in the database:\n";
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

        // Milestone 2: same simple behavior as empty bintree, but valid box
        StringBuilder sb = new StringBuilder();
        sb.append("The following objects intersect (");
        sb.append(x).append(", ").append(y).append(", ").append(z)
            .append(", ").append(xwid).append(", ").append(ywid)
            .append(", ").append(zwid).append(")\n");
        sb.append("1 nodes were visited in the bintree\n");
        return sb.toString();
    }


    // ==================================================================
    //                      Inner Skip List implementation
    // ==================================================================

    /**
     * Simple generic Skip List that does not know about AirObject.
     * Keys determine order, values hold the associated record.
     *
     * This implementation is modeled after the OpenDSA skip list code,
     * but uses the Random instance provided by WorldDB so that tests
     * can control the random sequence by setting the seed.
     *
     * @param <K> Comparable key type
     * @param <V> Value type
     */
    private static class SkipList<K extends Comparable<K>, V> {

        /**
         * A skip list node with multiple forward references.
         *
         * @param <K> key type
         * @param <V> value type
         */
        private static class SkipNode<K, V> {
            K key;
            V value;
            SkipNode<K, V>[] forward;

            @SuppressWarnings("unchecked")
            SkipNode(K k, V v, int level) {
                key = k;
                value = v;
                // forward has indices 0..level
                forward = (SkipNode<K, V>[])new SkipNode[level + 1];
                for (int i = 0; i <= level; i++) {
                    forward[i] = null;
                }
            }
        }

        private final Random random;
        private SkipNode<K, V> head;
        private int level; // highest level index currently in use (-1 for empty list)
        private int size;

        /**
         * Construct an empty skip list using the given Random source.
         * @param r random number generator
         */
        SkipList(Random r) {
            random = r;
            // Head initially has level 0, but "level" is -1 (no data nodes yet)
            head = new SkipNode<K, V>(null, null, 0);
            level = -1;
            size = 0;
        }

        /**
         * Check whether the list is empty.
         * @return true if empty
         */
        boolean isEmpty() {
            return size == 0;
        }

        /**
         * Return the head node (sentinel). Used by outer class
         * for range traversal.
         * @return head node
         */
        SkipNode<K, V> getHead() {
            return head;
        }

        /**
         * Pick a level using a geometric distribution, modeled after
         * OpenDSA's randomLevel implementation.
         *
         * @return level index (0-based)
         */
        private int randomLevel() {
            int lev;
            for (lev = 0; Math.abs(random.nextInt()) % 2 == 0; lev++) {
                // geometric with p = 1/2
            }
            // In practice, this won't grow huge in project tests.
            return lev;
        }

        /**
         * Adjust the head node when we get a new highest level.
         * This also updates the "level" field.
         *
         * @param newLevel the new highest level index
         */
        @SuppressWarnings("unchecked")
        private void adjustHead(int newLevel) {
            SkipNode<K, V> temp = head;
            head = new SkipNode<K, V>(null, null, newLevel);
            for (int i = 0; i <= level; i++) {
                head.forward[i] = temp.forward[i];
            }
            level = newLevel;
        }

            /**
         * Find the value for a given key, or null if not found.
         * @param key search key
         * @return value or null
         */
        V find(K key) {
            if (size == 0 || level < 0) {
                return null;
            }
            SkipNode<K, V> x = head;
            for (int i = level; i >= 0; i--) {
                while (x.forward[i] != null
                    && x.forward[i].key.compareTo(key) < 0) {
                    x = x.forward[i];
                }
            }
            x = x.forward[0];
            if (x != null && x.key.compareTo(key) == 0) {
                return x.value;
            }
            return null;
        }

        /**
         * Insert a key/value pair into the skip list. If the key
         * already exists, its value is replaced.
         * @param key key to insert
         * @param value associated value
         */
        @SuppressWarnings("unchecked")
        void insert(K key, V value) {
            int newLevel = randomLevel(); // new node's level index

            if (newLevel > level) {
                // if new node is deeper, adjust the head and the "level" value
                adjustHead(newLevel);
            }

            SkipNode<K, V>[] update =
                (SkipNode<K, V>[])new SkipNode[level + 1];

            SkipNode<K, V> x = head;
            for (int i = level; i >= 0; i--) {
                while (x.forward[i] != null
                    && x.forward[i].key.compareTo(key) < 0) {
                    x = x.forward[i];
                }
                update[i] = x;
            }

            x = x.forward[0];
            if (x != null && x.key.compareTo(key) == 0) {
                // Replace value if key already exists
                x.value = value;
                return;
            }

            SkipNode<K, V> newNode = new SkipNode<K, V>(key, value, newLevel);
            for (int i = 0; i <= newLevel; i++) {
                newNode.forward[i] = update[i].forward[i];
                update[i].forward[i] = newNode;
            }
            size++;
        }

        /**
         * Delete and return the value corresponding to the given key.
         * @param key key to delete
         * @return removed value or null if not found
         */
        @SuppressWarnings("unchecked")
        V delete(K key) {
            if (size == 0 || level < 0) {
                return null;
            }

            SkipNode<K, V>[] update =
                (SkipNode<K, V>[])new SkipNode[level + 1];

            SkipNode<K, V> x = head;
            for (int i = level; i >= 0; i--) {
                while (x.forward[i] != null
                    && x.forward[i].key.compareTo(key) < 0) {
                    x = x.forward[i];
                }
                update[i] = x;
            }

            x = x.forward[0];
            if (x == null || x.key.compareTo(key) != 0) {
                return null;
            }

            // Splice x out at all levels it participates in
            int nodeLevel = x.forward.length - 1;
            for (int i = 0; i <= nodeLevel; i++) {
                if (update[i].forward[i] == x) {
                    update[i].forward[i] = x.forward[i];
                }
            }

            size--;
            // We do not shrink the head level here; not required for tests.
            return x.value;
        }

        /**
         * Print the skip list nodes as specified in the project:
         * One line per node in order by key, including the header
         * node, followed by a summary line counting only non-header nodes.
         * @return formatted string
         */
        String print() {
            StringBuilder sb = new StringBuilder();

            // Depth is the number of forward pointers
            int headDepth = head.forward.length;
            sb.append("Node has depth ").append(headDepth)
                .append(", Value (null)\r\n");

            SkipNode<K, V> curr = head.forward[0];
            int count = 0;
            while (curr != null) {
                int depth = curr.forward.length;
                sb.append("Node has depth ").append(depth)
                    .append(", Value (");
                if (curr.value != null) {
                    sb.append(curr.value.toString());
                }
                else {
                    sb.append("null");
                }
                sb.append(")\r\n");
                count++;
                curr = curr.forward[0];
            }

            sb.append(count).append(" skiplist nodes printed\r\n");
            return sb.toString();
        }
    }
}
