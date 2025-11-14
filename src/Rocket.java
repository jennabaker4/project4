/**
 * Rocket class
 */
public class Rocket extends AirObject {
	private int ascentRate;
	private float trajectory;
	
	/**
	 * Constructor
	 * @param x coord
	 * @param y coord
	 * @param z coord
	 * @param xWidth x face width
	 * @param yWidth y face width
	 * @param zWidth z face width
	 * @param name of rocket
	 * @param ascentRate of rocket
	 * @param trajectory of rocket
	 */
	Rocket(int x, int y, int z, int xWidth, int yWidth, int zWidth, String name, int ascentRate, float trajectory) {
		super(x, y, z, xWidth, yWidth, zWidth, name);
		this.ascentRate = ascentRate;
		this.trajectory = trajectory;
	}
	
	/**
	 * Return ascent rate
	 * @return ascentRate
	 */
	public int getAscentRate() {
		return ascentRate;
	}
	
	/**
	 * Return trajectory
	 * @return trajectory
	 */
	public float getTrajectory() {
		return trajectory;
	}
}
