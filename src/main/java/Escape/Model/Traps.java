package Escape.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

/**
 * Extends ArenaObject class, represents a list of a traps.
 */
public class Traps {

	/**
	 * Creates logs.
	 */
	protected static Logger	logger = Logger.getLogger(Traps.class.getName());
	
	/**
	 * List, which contains the traps.
	 */
	private List<ArenaObject> list;
	
	/**
	 * Constructor for initialize the list size and add invalid traps.
	 * After initialization the constructor calls the <code>trapsInit</code>,
	 * which gives valid locations for traps.
	 * 
	 * @param listSize size of the list
	 * @param size size of the game arena
	 * @param step the distance of steps
	 * @param player the player's object
	 * @param enemy1 one of the two enemy object
	 * @param enemy2 another one of the two enemy object
	 */
	public Traps(int listSize, int size, int step, Player player, Enemy enemy1, Enemy enemy2){
		list = new ArrayList<ArenaObject>();
		for (int i=0; i<listSize; i++){
			list.add(new ArenaObject(-1000, -1000));
		}
		trapsInit(size, step, player, enemy1, enemy2);
	}
	
	/**
	 * Initializes the random location of traps.
	 * The location of player, enemies and other traps are invalid locations.
	 * Calculates until the coordinates are valid and the traps are set.
	 *
	 * @param size size of the game arena
	 * @param step the distance of steps
	 * @param player the player's object
	 * @param enemy1 one of the two enemy object
	 * @param enemy2 another one of the two enemy object
	 */
	public void trapsInit(int size, int step, Player player, Enemy enemy1, Enemy enemy2) {
		Random rand = new Random();
		for (int i = 0; i < list.size(); i++){
			list.get(i).setX(-1000);
			list.get(i).setY(-1000);
		}
		logger.info("Start search of traps");
		for (int i = 0; i < list.size(); i++){
			int x = 0;
			int y = 0;
			boolean goodCoords = false;
			while(!goodCoords) {
				x = (rand.nextInt(size-2)+1)*step;
				y = (rand.nextInt(size-2)+1)*step;
				goodCoords = true;
				for (ArenaObject trap : list) {
					if (Math.abs(x-trap.getX())<2*step || Math.abs(y-trap.getY())<2*step){
						goodCoords = false;
						break;
					}
				}
			}
			
			list.get(i).setX(x);
			list.get(i).setY(y);
			list.get(i).setActive(true);
		}
		logger.info("Traps search done");
	}

	/**
	 * Returns the list of traps.
	 * 
	 * @return list of traps
	 */
	public List<ArenaObject> getList() {
		return list;
	}

	/**
	 * Sets the list of traps.
	 * 
	 * @param list of traps
	 */
	public void setList(List<ArenaObject> list) {
		this.list = list;
	}
}
