/**
 * Drone class
 */
public class Drone extends AirObject {
	private String brand;
	private int numEngines;
	
	/**
	 * Constructor
	 * @param x coord
	 * @param y coord
	 * @param z coord
	 * @param xWidth x face width
	 * @param yWidth y face width
	 * @param zWidth z face width
	 * @param name of drone
	 * @param brand of drone
	 * @param numEngines in drone
	 */
	Drone(String name, int x, int y, int z, int xWidth, int yWidth, int zWidth, String brand, int numEngines) {
		super(x, y, z, xWidth, yWidth, zWidth, name);
		this.brand = brand;
		this.numEngines = numEngines;
	}
	
	/**
	 * Return brand
	 * @return brand
	 */
	public String getBrand() {
		return brand;
	}
	
	/**
	 * Return number of engines
	 * @return numEngines
	 */
	public int getNumEngines() {
		return numEngines;
	}
	
    /**
     * Return a string representation of the Drone.
     * Format: "Drone name x y z xWidth yWidth zWidth brand numEngines"
     * @return formatted string
     */
    @Override
    public String toString() {
        return "Drone " + getName() + " "
            + getXorig() + " " + getYorig() + " " + getZorig() + " "
            + getXwidth() + " " + getYwidth() + " " + getZwidth() + " "
            + brand + " " + numEngines;
    }

}
