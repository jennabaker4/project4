/**
 * Balloon class
 */
public class Balloon extends AirObject {
	private String type;
	private int ascentRate;
	
	/**
	 * Constructor
	 * @param x coord
	 * @param y coord
	 * @param z coord
	 * @param xWidth x face width
	 * @param yWidth y face width
	 * @param zWidth z face width
	 * @param name of object
	 * @param type identifier number
	 * @param ascentRate rate of ascent
	 */
	Balloon(String name, int x, int y, int z, int xWidth, int yWidth, int zWidth, String type, int ascentRate) {
		super(x, y, z, xWidth, yWidth, zWidth, name);
		this.type = type;
		this.ascentRate = ascentRate;
	}
	
	/**
	 * Return type number
	 * @return type
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * Return ascent rate
	 * @return ascentRate
	 */
	public int getAscentRate() {
		return ascentRate;
	}
	
    /**
     * Return a string representation of the Balloon.
     * Format: "Balloon name x y z xWidth yWidth zWidth type ascentRate"
     * @return formatted string
     */
    @Override
    public String toString() {
        return "Balloon " + getName() + " "
            + getXorig() + " " + getYorig() + " " + getZorig() + " "
            + getXwidth() + " " + getYwidth() + " " + getZwidth() + " "
            + type + " " + ascentRate;
    }
}
