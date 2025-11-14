/**
 * AirObject class
 */
public class AirObject {
	private int x;
	private int y;
	private int z;
	private int xWidth;
	private int yWidth;
	private int zWidth;
	private String name;
	
	/**
	 * Constructor
	 * @param x coord
	 * @param y coord
	 * @param z coord
	 * @param xWidth x face width
	 * @param yWidth y face width
	 * @param zWidth z face width
	 * @param name of object
	 */
	AirObject(int x, int y, int z, int xWidth, int yWidth, int zWidth, String name) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.xWidth = xWidth;
		this.yWidth = yWidth;
		this.zWidth = zWidth;
		this.name = name;
	}
	
	/**
	 * Returns x value
	 * @return x
	 */
	public int getXorig() {
		return x;
	}
	
	/**
	 * Returns y value
	 * @return y
	 */
	public int getYorig() {
		return y;
	}
	
	/**
	 * Returns z value
	 * @return z
	 */
	public int getZorig() {
		return z;
	}
	
	/**
	 * Returns x width
	 * @return xWidth
	 */
	public int getXwidth() {
		return xWidth;
	}
	
	/**
	 * Returns y width
	 * @return yWidth
	 */
	public int getYwidth() {
		return yWidth;
	}
	
	/**
	 * Returns z width
	 * @return zWidth
	 */
	public int getZwidth() {
		return zWidth;
	}
	
	/**
	 * Returns name
	 * @return name
	 */
	public String getName() {
		return name;
	}
}
