package Escape.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Extends ArenaObject class, represents a list of a traps.
 */
public class Traps {

	/**
	 * List, which contains the traps.
	 */
	private List<ArenaObject> list;
	
	/**
	 * Constructor for initialize the list size and add invalid traps.
	 * After initialize the constructor calls the <code>trapsInit</code>,
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
	 * Invalid locations are the location of player, enemies and other traps.
	 * Calculates till the coordinates are not valid and then add to one trap.
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
			int x = 0;
			int y = 0;
			boolean goodCoords = false;
			while(!goodCoords) {
				x = (rand.nextInt(size-2)+1)*step;
				y = (rand.nextInt(size-2)+1)*step;
				if ((x!=player.getX() || y!=player.getY())){
					goodCoords = true;
					if (x==enemy1.getX() && y==enemy1.getY()){
						goodCoords = false;
						break;
					}
					if (x==enemy2.getX() && y==enemy2.getY()){
						goodCoords = false;
						break;
					}
				}
				if (goodCoords && list.size()>0)
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
