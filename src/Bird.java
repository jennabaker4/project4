/**
 * Bird class
 */
public class Bird extends AirObject {
	private String type;
	private int number;
	
	/**
	 * Constructor
	 * @param x coord
	 * @param y coord
	 * @param z coord
	 * @param xWidth x face width
	 * @param yWidth y face width
	 * @param zWidth z face width
	 * @param name of bird
	 * @param type of bird
	 * @param number for identification
	 */
	Bird(String name, int x, int y, int z, int xWidth, int yWidth, int zWidth, String type, int number) {
		super(x, y, z, xWidth, yWidth, zWidth, name);
		this.type = type;
		this.number = number;
	}
	
	/**
	 * Return bird type
	 * @return type
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * Return number
	 * @return number
	 */
	public int getNumber() {
		return number;
	}
	
    /**
     * Return a string representation of the Bird.
     * Format: "Bird name x y z xWidth yWidth zWidth type number"
     * @return formatted string
     */
    @Override
    public String toString() {
        return "Bird " + getName() + " "
            + getXorig() + " " + getYorig() + " " + getZorig() + " "
            + getXwidth() + " " + getYwidth() + " " + getZwidth() + " "
            + type + " " + number;
    }

}
