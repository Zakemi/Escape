package Escape.Model;

public class ArenaObject {
	protected int x;
	protected int y;
	protected boolean active;
	public ArenaObject(int x, int y) {
		this.x = x;
		this.y = y;
		this.active = true;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	@Override
	public String toString() {
		return "ArenaObject [x=" + x + ", y=" + y + ", active=" + active + "]";
	}
}
