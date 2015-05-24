package Escape.Model;

/**
 * This class contains everything that represents the game arena.
 */
public class Arena {
	/**
	 * Represent the player object.
	 */
	private Player player;
	
	/**
	 * Represent one of the two enemy object.
	 */
	private Enemy enemy1;
	
	/**
	 * Represent one of the two enemy object.
	 */
	private Enemy enemy2;
	
	/**
	 * Represent the list of traps.
	 */
	private Traps traps;
	
	/**
	 * The game arena size, how many object can be in one row.
	 */
	private int size;
	
	/**
	 * The game arena size in pixels.
	 */
	private int winSize;
	
	/**
	 * The distance that the objects can step at once.
	 */
	private int step;
	
	/**
	 * The list of traps size.
	 */
	private int trapSize = 2;

	/**
	 * Constructor for create the game arena.
	 * Places the player top center, the enemies bottom left
	 * and right corner and the traps random free slots.
	 * This code
	 * <pre>Arena arena = new Arena(6, 600);</pre>
	 * creates an 600x600 pixels arena and it allows to place
	 * 6 100x100 objects in one row.  
	 * 
	 * @param size how many object can be in row
	 * @param winSize the real size in pixels
	 */
	public Arena(int size, int winSize) {
		this.size = size;
		this.winSize = winSize;
		this.step = winSize/size;
		player = new Player((size/2)*step,0*step);
		enemy1 = new Enemy((size-1)*step, (size-1)*step);
		enemy2 = new Enemy(0*step, (size-1)*step);
		traps = new Traps(trapSize, size, step, player, enemy1, enemy2);
	}
	
	/**
	 * Check if there is at least one active enemy.
	 * 
	 * @return is there at least one active enemy
	 */
	public boolean enemiesIsActive(){
		if (enemy1.isActive() == true)
			return true;
		if (enemy2.isActive() == true)
			return true;
		return false;
	}

	/**
	 * Returns the player object.
	 * 
	 * @return player object
	 */
	public Player getPlayer() {
		return player;
	}

	/**
	 * Sets the player object.
	 * 
	 * @param player player object
	 */
	public void setPlayer(Player player) {
		this.player = player;
	}

	/**
	 * Returns one of the two enemies.
	 * 
	 * @return the one of the two enemy object
	 */
	public Enemy getEnemy1() {
		return enemy1;
	}

	/**
	 * Returns one of the two enemies.
	 * 
	 * @return the one of the two enemy object
	 */
	public Enemy getEnemy2() {
		return enemy2;
	}
	
	/**
	 * Sets the one of the two enemy object.
	 * 
	 * @param enemy1 an enemy object
	 */
	public void setEnemy1(Enemy enemy1) {
		this.enemy1 = enemy1;
	}

	/**
	 * Sets the one of the two enemy object.
	 * 
	 * @param enemy2 an enemy object
	 */
	public void setEnemy2(Enemy enemy2) {
		this.enemy2 = enemy2;
	}
	
	/**
	 * Returns the max number of objects in one row.
	 * 
	 * @return the max number of objects in one row
	 */
	public int getSize() {
		return size;
	}

	/**
	 * Sets the max number of objects in one row.
	 * 
	 * @param size the max number of objects in one row
	 */
	public void setSize(int size) {
		this.size = size;
	}

	/**
	 * Returns the arena size in pixels.
	 * 
	 * @return the arena size in pixels
	 */
	public int getWinSize() {
		return winSize;
	}

	/**
	 * Sets the arena size in pixels.
	 * 
	 * @param winSize the arena size in pixels
	 */
	public void setWinSize(int winSize) {
		this.winSize = winSize;
	}

	/**
	 * Returns the list of traps.
	 * 
	 * @return list of traps
	 */
	public Traps getTraps() {
		return traps;
	}
	
	/**
	 * Sets the list of traps.
	 * 
	 * @param traps list of traps
	 */
	public void setTraps(Traps traps) {
		this.traps = traps;
	}
	
	/**
	 * Returns the distance that objects can step.
	 * 
	 * @return the distance that objects can step
	 */
	public int getStep() {
		return step;
	}
	
	/**
	 * Sets the distance that objects can step.
	 * 
	 * @param step the distance that objects can step
	 */
	public void setStep(int step) {
		this.step = step;
	}

	/**
	 * Returns the size of list of traps.
	 * 
	 * @return the size of list of traps
	 */
	public int getTrapSize() {
		return trapSize;
	}

	/**
	 * Sets the size of list of traps.
	 * 
	 * @param trapSize the size of list of traps
	 */
	public void setTrapSize(int trapSize) {
		this.trapSize = trapSize;
	}

}
