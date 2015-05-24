package Escape.Model;
/**
 * This class is the base class for an object which appears in the game arena.
 */
public class ArenaObject {
	/**
	 * The x coordinate of this object.
	 */
	protected int x;
	
	/**
	 * The y coordinate of this object.
	 */
	protected int y;
	
	/**
	 * Shows is the object active or not.
	 */
	protected boolean active;
	
	/**
	 * A constructor for an ArenaObject object. This object is always active when created.
	 * @param x the ArenaObject x coordinate
	 * @param y the ArenaObject y coordinate
	 */
	public ArenaObject(int x, int y) {
		this.x = x;
		this.y = y;
		this.active = true;
	}
	
	/**
	 * Returns the x coordinate of this object.
	 * @return x coordinate of this object
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * Sets the x coordinate of this object.
	 * @param x coordinate of this object
	 */
	public void setX(int x) {
		this.x = x;
	}
	
	/**
	 * Returns the object's status, active or not.
	 * @return the object's status
	 */
	public boolean isActive() {
		return active;
	}
	
	/**
	 * Sets the object's status, active or not.
	 * @param active the object's status
	 */
	public void setActive(boolean active) {
		this.active = active;
	}
	
	/**
	 * Returns the y coordinate of this object.
	 * @return y coordinate of this object
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * Sets the y coordinate of this object.
	 * @param y coordinate of this object
	 */
	public void setY(int y) {
		this.y = y;
	}
	
	/**
	 * ToStrings method for this object.
	 */
	@Override
	public String toString() {
		return "ArenaObject [x=" + x + ", y=" + y + ", active=" + active + "]";
	}
}
