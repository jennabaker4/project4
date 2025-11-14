/**
 * AirPlane class
 */
public class AirPlane extends AirObject {
	private String carrier;
	private int flightNum;
	private int numEngines;
	
	/**
	 * Constructor
	 * @param name of object
	 * @param x coord
	 * @param y coord
	 * @param z corrd
	 * @param xWidth x face width
	 * @param yWidth y face width
	 * @param zWidth z face width
	 * @param carrier carrier number
	 * @param flightNum identifier
	 * @param numEngines number of engines on plane
	 */
	AirPlane(String name, int x, int y, int z, int xWidth, int yWidth, int zWidth, String carrier, int flightNum, int numEngines) {
		super(x, y, z, xWidth, yWidth, zWidth, name);
		this.carrier = carrier;
		this.flightNum = flightNum;
		this.numEngines = numEngines;
	}
	
	/**
	 * Return carrier number
	 * @return carrier
	 */
	public String getCarrier() {
		return carrier;
	}
	
	/**
	 * Return flight number
	 * @return flightNum
	 */
	public int getFlightNum() {
		return flightNum;
	}
	
	/**
	 * Return number of engines
	 * @return numEngines
	 */
	public int numEngines() {
		return numEngines;
	}
}
