package Escape.Model;

/**
 * Extends ArenaObject class, represents an enemy object.
 * This object must take a step by an algorithm.
 */
public class Enemy extends ArenaObject {
	/**
	 * The constructor calls the super().
	 * 
	 * @param x coordinate of this object
	 * @param y coordinate of this object
	 */
	public Enemy(int x, int y) {
		super(x, y);
	}
	
	/**
	 * The object takes a step by an algorithm.
	 * If the x distance from the player is bigger than the y distance,
	 * the enemy object reduces the x distance between them.
	 * In other case the enemy object reduces the y distance.
	 * @param player is the player's object
	 * @param step is the distance what the enemy can step at once
	 */
	public void setXY(Player player, int step){
		if( Math.abs(player.getX()-x) > Math.abs(player.getY()-y)){
			if (player.getX() > x){
				this.x += step;
			}
			else if (player.getX() < x) {
				this.x -= step;
			}
		}
		else {
			if (player.getY() > y){
				this.y += step;
			}
			else if (player.getY() < y){
				this.y -= step;
			}
		}
	}
}
