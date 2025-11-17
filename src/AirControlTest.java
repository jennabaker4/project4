import java.util.Random;
import student.TestCase;

/**
 * @author Cailin Flatla and Jenna Baker
 * @version Fall 2025 Project 4
 */
public class AirControlTest extends TestCase {

    /**
     * Sets up the tests that follow. In general, used for initialization
     */
    public void setUp() {
        // Nothing here
    }


    /**
     * Get code coverage of the class declaration.
     *
     * @throws Exception
     */
    public void testRInit() throws Exception {
        AirControl recstore = new AirControl();
        assertNotNull(recstore);
    }


    // ----------------------------------------------------------
    /**
     * Test syntax: Sample Input/Output
     *
     * @throws Exception
     */
    /*
    public void testSampleInput() throws Exception {
        Random rnd = new Random();
        rnd.setSeed(0xCAFEBEEF);
        WorldDB w = new WorldDB(rnd);

        assertTrue(w.add(new Balloon("B1",
            10, 11, 11, 21, 12, 31, "hot_air", 15)));
        assertTrue(w.add(new AirPlane("Air1",
            0, 10, 1, 20, 2, 30, "USAir", 717, 4)));
        assertTrue(w.add(new Drone("Air2",
            100, 1010, 101, 924, 2, 900, "Droners", 3)));
        assertTrue(w.add(new Bird("pterodactyl",
            0, 100, 20, 10, 50, 50, "Dinosaur", 1)));
        assertFalse(w.add(new Bird("pterodactyl",
            0, 100, 20, 10, 50, 50, "Dinosaur", 1)));
        assertTrue(w.add(new Rocket("Enterprise",
            0, 100, 20, 10, 50, 50, 5000, 99.29)));

        assertFuzzyEquals(
            "Rocket Enterprise 0 100 20 10 50 50 5000 99.29",
            w.delete("Enterprise"));

        assertFuzzyEquals("Airplane Air1 0 10 1 20 2 30 USAir 717 4",
            w.print("Air1"));
        assertNull(w.print("air1"));

        assertFuzzyEquals(
            "I (0, 0, 0, 1024, 1024, 1024) 0\r\n"
                + "  I (0, 0, 0, 512, 1024, 1024) 1\r\n"
                + "    Leaf with 3 objects (0, 0, 0, 512, 512, 1024) 2\r\n"
                + "    (Airplane Air1 0 10 1 20 2 30 USAir 717 4)\r\n"
                + "    (Balloon B1 10 11 11 21 12 31 hot_air 15)\r\n"
                + "    (Bird pterodactyl 0 100 20 10 50 50 Dinosaur 1)\r\n"
                + "    Leaf with 1 objects (0, 512, 0, 512, 512, 1024) 2\r\n"
                + "    (Drone Air2 100 1010 101 924 2 900 Droners 3)\r\n"
                + "  Leaf with 1 objects (512, 0, 0, 512, 1024, 1024) 1\r\n"
                + "  (Drone Air2 100 1010 101 924 2 900 Droners 3)\r\n"
                + "5 Bintree nodes printed\r\n",
                w.printbintree());

        assertFuzzyEquals(
            "Node has depth 3, Value (null)\r\n"
                + "Node has depth 3, "
                + "Value (Airplane Air1 0 10 1 20 2 30 USAir 717 4)\r\n"
                + "Node has depth 1, "
                + "Value (Drone Air2 100 1010 101 924 2 900 Droners 3)\r\n"
                + "Node has depth 2, "
                + "Value (Balloon B1 10 11 11 21 12 31 hot_air 15)\r\n"
                + "Node has depth 2, "
                + "Value (Bird pterodactyl 0 100 20 10 50 50 Dinosaur 1)\r\n"
                + "4 skiplist nodes printed\r\n",
                w.printskiplist());

        assertFuzzyEquals(
            "Found these records in the range a to z\r\n"
                + "Bird pterodactyl 0 100 20 10 50 50 Dinosaur 1\r\n",
                w.rangeprint("a", "z"));
        assertFuzzyEquals(
            "Found these records in the range a to l\r\n",
            w.rangeprint("a", "l"));
        assertNull(w.rangeprint("z", "a"));

        assertFuzzyEquals(
            "The following collisions exist in the database:\r\n"
                + "In leaf node (0, 0, 0, 512, 512, 1024) 2\r\n"
                + "(Airplane Air1 0 10 1 20 2 30 USAir 717 4) "
                + "and (Balloon B1 10 11 11 21 12 31 hot_air 15)\r\n"
                + "In leaf node (0, 512, 0, 512, 512, 1024) 2\r\n"
                + "In leaf node (512, 0, 0, 512, 1024, 1024) 1\r\n",
                w.collisions());

        assertFuzzyEquals(
            "The following objects intersect (0 0 0 1024 1024 1024):\r\n"
                + "In Internal node (0, 0, 0, 1024, 1024, 1024) 0\r\n"
                + "In Internal node (0, 0, 0, 512, 1024, 1024) 1\r\n"
                + "In leaf node (0, 0, 0, 512, 512, 1024) 2\r\n"
                + "Airplane Air1 0 10 1 20 2 30 USAir 717 4\r\n"
                + "Balloon B1 10 11 11 21 12 31 hot_air 15\r\n"
                + "Bird pterodactyl 0 100 20 10 50 50 Dinosaur 1\r\n"
                + "In leaf node (0, 512, 0, 512, 512, 1024) 2\r\n"
                + "Drone Air2 100 1010 101 924 2 900 Droners 3\r\n"
                + "In leaf node (512, 0, 0, 512, 1024, 1024) 1\r\n"
                + "5 nodes were visited in the bintree\r\n",
                w.intersect(0, 0, 0, 1024, 1024, 1024));
    }*/


    // ----------------------------------------------------------
    /**
     * Test syntax: Check various forms of bad input parameters
     *
     * @throws Exception
     */
    public void testBadInput() throws Exception {
        Random rnd = new Random();
        rnd.setSeed(0xCAFEBEEF);
        WorldDB w = new WorldDB(rnd);
        assertFalse(w.add(new AirPlane("a", 1, 1, 1, 1, 1, 1, null, 1, 1)));
        assertFalse(w.add(new AirPlane("a", 1, 1, 1, 1, 1, 1, "Alaska", 0, 1)));
        assertFalse(w.add(new AirPlane("a", 1, 1, 1, 1, 1, 1, "Alaska", 1, 0)));
        assertFalse(w.add(new Balloon(null, 1, 1, 1, 1, 1, 1, "hot", 5)));
        assertFalse(w.add(new Balloon("b", -1, 1, 1, 1, 1, 1, "hot", 5)));
        assertFalse(w.add(new Balloon("b", 1, -1, 1, 1, 1, 1, "hot", 5)));
        assertFalse(w.add(new Balloon("b", 1, 1, -1, 1, 1, 1, "hot", 5)));
        assertFalse(w.add(new Balloon("b", 1, 1, 1, 0, 1, 1, "hot", 5)));
        assertFalse(w.add(new Balloon("b", 1, 1, 1, 1, 0, 1, "hot", 5)));
        assertFalse(w.add(new Balloon("b", 1, 1, 1, 1, 1, 0, "hot", 5)));
        assertFalse(w.add(new Balloon("b", 1, 1, 1, 1, 1, 1, null, 5)));
        assertFalse(w.add(new Balloon("b", 1, 1, 1, 1, 1, 1, "hot", -1)));
        assertFalse(w.add(new Bird("b", 1, 1, 1, 1, 1, 1, null, 5)));
        assertFalse(w.add(new Bird("b", 1, 1, 1, 1, 1, 1, "Ostrich", 0)));
        assertFalse(w.add(new Drone("d", 1, 1, 1, 1, 1, 1, null, 5)));
        assertFalse(w.add(new Drone("d", 1, 1, 1, 1, 1, 1, "Droner", 0)));
        assertFalse(w.add(new Rocket("r", 1, 1, 1, 1, 1, 1, -1, 1.1)));
        assertFalse(w.add(new Rocket("r", 1, 1, 1, 1, 1, 1, 1, -1.1)));
        assertFalse(w.add(
            new AirPlane("a", 2000, 1, 1, 1, 1, 1, "Alaska", 1, 1)));
        assertFalse(w.add(
            new AirPlane("a", 1, 2000, 1, 1, 1, 1, "Alaska", 1, 1)));
        assertFalse(w.add(
            new AirPlane("a", 1, 1, 2000, 1, 1, 1, "Alaska", 1, 1)));
        assertFalse(w.add(
            new AirPlane("a", 1, 1, 1, 2000, 1, 1, "Alaska", 1, 1)));
        assertFalse(w.add(
            new AirPlane("a", 1, 1, 1, 1, 2000, 1, "Alaska", 1, 1)));
        assertFalse(w.add(
            new AirPlane("a", 1, 1, 1, 1, 1, 2000, "Alaska", 1, 1)));
        assertFalse(w.add(
            new AirPlane("a", 1000, 1, 1, 1000, 1, 1, "Alaska", 1, 1)));
        assertFalse(w.add(
            new AirPlane("a", 1, 1000, 1, 1, 1000, 1, "Alaska", 1, 1)));
        assertFalse(w.add(
            new AirPlane("a", 1, 1, 1000, 1, 1, 1000, "Alaska", 1, 1)));
        assertNull(w.delete(null));
        assertNull(w.print(null));
        assertNull(w.rangeprint(null, "a"));
        assertNull(w.rangeprint("a", null));
        assertNull(w.intersect(-1, 1, 1, 1, 1, 1));
        assertNull(w.intersect(1, -1, 1, 1, 1, 1));
        assertNull(w.intersect(1, 1, -1, 1, 1, 1));
        assertNull(w.intersect(1, 1, 1, -1, 1, 1));
        assertNull(w.intersect(1, 1, 1, 1, -1, 1));
        assertNull(w.intersect(1, 1, 1, 1, 1, -1));
        assertNull(w.intersect(2000, 1, 1, 1, 1, 1));
        assertNull(w.intersect(1, 2000, 1, 1, 1, 1));
        assertNull(w.intersect(1, 1, 2000, 1, 1, 1));
        assertNull(w.intersect(1, 1, 1, 2000, 1, 1));
        assertNull(w.intersect(1, 1, 1, 1, 2000, 1));
        assertNull(w.intersect(1, 1, 1, 1, 1, 2000));
        assertNull(w.intersect(1000, 1, 1, 1000, 1, 1));
        assertNull(w.intersect(1, 1000, 1, 1, 1000, 1));
        assertNull(w.intersect(1, 1, 1000, 1, 1, 1000));
    }


    // ----------------------------------------------------------
    /**
     * Test empty: Check various returns from commands on empty database
     *
     * @throws Exception
     */
    public void testEmpty() throws Exception {
        WorldDB w = new WorldDB(null);
        assertNull(w.delete("hello"));
        assertFuzzyEquals("SkipList is empty", w.printskiplist());
        assertFuzzyEquals(
            "E (0, 0, 0, 1024, 1024, 1024) 0\r\n"
                + "1 Bintree nodes printed\r\n",
                w.printbintree());
        assertNull(w.print("hello"));
        assertFuzzyEquals("Found these records in the range begin to end\n",
            w.rangeprint("begin", "end"));
        assertFuzzyEquals("The following collisions exist in the database:\n",
            w.collisions());
        assertFuzzyEquals(
            "The following objects intersect (1, 1, 1, 1, 1, 1)\n" +
                "1 nodes were visited in the bintree\n",
                w.intersect(1, 1, 1, 1, 1, 1));
    }
    
    // ----------------------------------------------------------
    /**
     * Test basic add/print/delete behavior with valid objects.
     *
     * @throws Exception
     */
    public void testAddPrintDeleteSimple() throws Exception {
        WorldDB w = new WorldDB(new Random(123));

        // A valid Balloon
        assertTrue(w.add(new Balloon("B1",
            1, 1, 1, 10, 10, 10, "hot", 5)));

        // A valid AirPlane
        assertTrue(w.add(new AirPlane("A1",
            2, 2, 2, 5, 5, 5, "Alaska", 7, 2)));

        // print should find an existing record
        String b1 = w.print("B1");
        assertNotNull(b1);
        assertTrue(b1.startsWith("Balloon B1"));

        // delete should remove it
        String deleted = w.delete("B1");
        assertNotNull(deleted);
        assertTrue(deleted.startsWith("Balloon B1"));

        // now print should fail to find it
        assertNull(w.print("B1"));
    }

    // ----------------------------------------------------------
    /**
     * Test that printskiplist reports a non-empty structure correctly.
     *
     * @throws Exception
     */
    public void testSkipListNonEmpty() throws Exception {
        WorldDB w = new WorldDB(new Random(5));
        assertTrue(w.add(new Balloon("B1",
            1, 1, 1, 10, 10, 10, "hot", 5)));

        String s = w.printskiplist();
        assertNotNull(s);
        // For non-empty lists, the output should not be the empty message
        assertFalse("SkipList is empty".equals(s));
    }
    
    // ----------------------------------------------------------
    /**
     * Test rangeprint on a non-empty database.
     *
     * @throws Exception
     */
    public void testRangePrintNonEmpty() throws Exception {
        WorldDB w = new WorldDB(null);
        assertTrue(w.add(new Balloon("B1",
            1, 1, 1, 10, 10, 10, "hot", 5)));

        String s = w.rangeprint("A", "Z");
        assertNotNull(s);
        // It should at least mention the record name somewhere
        assertTrue(s.contains("B1"));
    }
    
    // ----------------------------------------------------------
    /**
     * Additional test: insert one valid instance of each AirObject subtype
     * and make sure add returns true and print finds at least one.
     *
     * @throws Exception
     */
    public void testAddAllObjectTypes() throws Exception {
        WorldDB w = new WorldDB(new Random(42));

        // Valid AirPlane
        assertTrue(w.add(new AirPlane("Plane1",
            10, 10, 10, 5, 5, 5, "Alaska", 7, 2)));

        // Valid Balloon
        assertTrue(w.add(new Balloon("Ball1",
            20, 20, 20, 5, 5, 5, "hot", 3)));

        // Valid Bird
        assertTrue(w.add(new Bird("Bird1",
            30, 30, 30, 5, 5, 5, "Dino", 1)));

        // Valid Drone
        assertTrue(w.add(new Drone("Drone1",
            40, 40, 40, 5, 5, 5, "DJI", 4)));

        // Valid Rocket
        assertTrue(w.add(new Rocket("Rocket1",
            50, 50, 50, 5, 5, 5, 100, 1.0)));

        // Make sure at least one of them can be printed
        String s = w.print("Rocket1");
        assertNotNull(s);
        // We only check a prefix so we don't depend on exact formatting.
        assertTrue(s.startsWith("Rocket Rocket1"));
    }

    // ----------------------------------------------------------
    /**
     * Additional test: intersect on a non-empty database with
     * valid parameters.
     *
     * @throws Exception
     */
    public void testIntersectNonEmpty() throws Exception {
        WorldDB w = new WorldDB(null);

        // Add one valid object
        assertTrue(w.add(new Balloon("B1",
            1, 1, 1, 10, 10, 10, "hot", 5)));

        // Use a box that clearly covers the whole world
        String result = w.intersect(0, 0, 0, 1024, 1024, 1024);
        assertNotNull(result);
        // Do not depend on exact formatting – just look for the header text.
        assertTrue(result.contains("The following objects intersect"));
    }

    // ----------------------------------------------------------
    /**
     * Additional test: delete a non-head element from a non-empty database.
     *
     * @throws Exception
     */
    public void testDeleteNonHead() throws Exception {
        WorldDB w = new WorldDB(new Random(99));

        // Three valid balloons with distinct names
        assertTrue(w.add(new Balloon("A",
            1, 1, 1, 5, 5, 5, "hot", 3)));
        assertTrue(w.add(new Balloon("B",
            2, 2, 2, 5, 5, 5, "hot", 3)));
        assertTrue(w.add(new Balloon("C",
            3, 3, 3, 5, 5, 5, "hot", 3)));

        // Delete the middle one by name
        String deleted = w.delete("B");
        assertNotNull(deleted);
        // Don't depend on full exact formatting, just the prefix
        assertTrue(deleted.startsWith("Balloon B"));

        // It should really be gone now
        assertNull(w.print("B"));

        // The others should still be present
        assertNotNull(w.print("A"));
        assertNotNull(w.print("C"));
    }


}