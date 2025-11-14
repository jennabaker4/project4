/**
 * Bird class
 */
public class Bird extends AirObject {
	private int type;
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
	Bird(int x, int y, int z, int xWidth, int yWidth, int zWidth, String name, int type, int number) {
		super(x, y, z, xWidth, yWidth, zWidth, name);
		this.type = type;
		this.number = number;
	}
	
	/**
	 * Return bird type
	 * @return type
	 */
	public int getType() {
		return type;
	}
	
	/**
	 * Return number
	 * @return number
	 */
	public int getNumber() {
		return number;
	}
}
