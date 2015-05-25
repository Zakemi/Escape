package Escape.Controller;

import java.util.logging.Logger;

import Escape.Model.Arena;
import Escape.Model.ArenaObject;
import Escape.Service.Service;
import Escape.View.View;

/**
 * Control the gameplay.
 */
public class Controller{
	/**
	 * Creates logs.
	 */
	protected static Logger	logger = Logger.getLogger(Controller.class.getName());
	/**
	 * The game's arena.
	 */
	private Arena arena;
	
	/**
	 * The game's representation.
	 */
	private View view;
	
	/**
	 * The score of player.
	 */
	private int playerScore = 0;
	
	/**
	 * The score of enemies.
	 */
	private int enemyScore = 0;
	
	/**
	 * Constructor for create a controller.
	 * 
	 * @param arena the game's arena
	 * @param view the game's representation
	 */
	public Controller(Arena arena, View view){
		this.arena = arena;
		this.view = view;
	}
	
	/**
	 * After handle a player step, checks the state of game.
	 * If the player is not active, start a new round, 
	 * increase the <code>enemyScore</code> and update view.
	 * (Else) If there isn't active enemy, start a new round, 
	 * increase the <code>playerScore</code> and update view.
	 * (Else) If there was a step, calculate the steps of enemies,
	 * checks the active state of enemies and traps.
	 * 
	 * @param didStep did the player a step or not
	 */
	public void keyEvent(boolean didStep){
		setPlayerActive();
		if ( ! arena.getPlayer().isActive()){
			logger.info("Player is not active");
			Service.setDefault(arena);
			enemyScore++;
			view.updateView();
		}
		else if ( ! arena.enemiesIsActive()){
			logger.info("Noone enemy is acive");
			Service.setDefault(arena);
			playerScore++;
			view.updateView();
		}
		else if(didStep){
			logger.info("Player and at least one enemy active");
			enemyStep();
			setEnemyAndTrapActive();
		}
		view.updateView();
	}
	
	/**
	 * Handle when the player steps left.
	 * Calls <code>keyEvent</code>
	 */
	public void keyVK_LEFT(){
		boolean didStep = setPlayerX(arena.getPlayer().getX()-arena.getStep());
		logger.info("Did step:"+didStep);
		keyEvent(didStep);
	}
	
	/**
	 * Handle when the player steps right.
	 * Calls <code>keyEvent</code>
	 */
	public void keyVK_RIGHT(){
		boolean didStep = setPlayerX(arena.getPlayer().getX()+arena.getStep());
		logger.info("Did step:"+didStep);
		keyEvent(didStep);
	}
	
	/**
	 * Handle when the player steps down.
	 * Calls <code>keyEvent</code>
	 */
	public void keyVK_DOWN(){
		boolean didStep = setPlayerY(arena.getPlayer().getY()+arena.getStep());
		logger.info("Did step:"+didStep);
		keyEvent(didStep);
	}
	
	/**
	 * Handle when the player steps up.
	 * Calls <code>keyEvent</code>
	 */
	public void keyVK_UP(){
		boolean didStep = setPlayerY(arena.getPlayer().getY()-arena.getStep());
		logger.info("Did step:"+didStep);
		keyEvent(didStep);
	}
	
	/**
	 * Sets the player's active.
	 * If the player in a trap or an enemy, the activation is false.
	 */
	public void setPlayerActive(){
		if (arena.getEnemy1().isActive() && (arena.getEnemy1().getX() == arena.getPlayer().getX()) && 
				(arena.getEnemy1().getY() == arena.getPlayer().getY())){
			arena.getPlayer().setActive(false);
			logger.info("Player catched by enemy1");
		}
		if (arena.getEnemy2().isActive() && (arena.getEnemy2().getX() == arena.getPlayer().getX()) && 
				(arena.getEnemy2().getY() == arena.getPlayer().getY())){
			arena.getPlayer().setActive(false);
			logger.info("Player catched by enemy2");
		}
		for (ArenaObject trap : arena.getTraps().getList()) {
			if (trap.isActive() && (trap.getX() == arena.getPlayer().getX()) && 
					(trap.getY() == arena.getPlayer().getY())){
				arena.getPlayer().setActive(false);
				logger.info("Player catched by a trap");
				break;
			}
		}
		logger.info("Player isActive:"+arena.getPlayer().isActive());
	}
	
	/**
	 * Sets the activation of enemies and traps.
	 * If an enemy step in a trap, both will be inactive.
	 */
	public void setEnemyAndTrapActive(){
		for (ArenaObject trap : arena.getTraps().getList()) {
			if (trap.isActive() && (arena.getEnemy1().getX() == trap.getX()) && (arena.getEnemy1().getY() == trap.getY())){
				arena.getEnemy1().setActive(false);
				trap.setActive(false);
				logger.info("Enemy1 in a trap");
			}
			if (trap.isActive() && (arena.getEnemy2().getX() == trap.getX()) && (arena.getEnemy2().getY() == trap.getY())){
				arena.getEnemy2().setActive(false);
				trap.setActive(false);
				logger.info("Enemy2 in a trap");
			}
		}
	}
	
	/**
	 * Tries to step with sets x coordinate.
	 * The step was successful if x is inside the arena.
	 * 
	 * @param x new x coordinate of the player
	 * @return was the step successful
	 */
	public boolean setPlayerX(int x) {
		if (arena.getPlayer().isActive() && x >= 0 && x < arena.getWinSize()){
			arena.getPlayer().setX(x);
			return true;
		}
		return false;
	}
	
	/**
	 * Tries to step with sets y coordinate.
	 * The step was successful if y is inside the area.
	 * 
	 * @param y new y coordinate of the player
	 * @return was the step successful
	 */
	public boolean setPlayerY(int y) {
		if (arena.getPlayer().isActive() && y >= 0 && y < arena.getWinSize()){
			arena.getPlayer().setY(y);
			return true;
		}
		return false;
	}

	/**
	 * Steps with enemies automatically and check the player's activation.
	 * Uses the enemy step algorithm.
	 */
	public void enemyStep(){
		if (arena.getPlayer().isActive()){
			if (arena.getEnemy1().isActive()){
				arena.getEnemy1().setXY(arena.getPlayer(), arena.getStep());
				logger.info("Enemy1 stepped:"+arena.getEnemy1().getX()+", "+arena.getEnemy1().getY());
			}
			if (arena.getEnemy2().isActive()){
				arena.getEnemy2().setXY(arena.getPlayer(), arena.getStep());
				logger.info("Enemy2 stepped:"+arena.getEnemy2().getX()+", "+arena.getEnemy2().getY());
			}
		}
		setPlayerActive();
	}

	/**
	 * Returns the score of player.
	 * 
	 * @return the score of player
	 */
	public int getPlayerScore() {
		return playerScore;
	}

	/**
	 * Returns the score of enemies.
	 * 
	 * @return the score of enemies
	 */
	public int getEnemyScore() {
		return enemyScore;
	}

	/**
	 * Sets the score of player.
	 * 
	 * @param playerScore the score of player
	 */
	public void setPlayerScore(int playerScore) {
		this.playerScore = playerScore;
	}

	/**
	 * Sets the score of enemies.
	 * 
	 * @param enemyScore the score of enemies
	 */
	public void setEnemyScore(int enemyScore) {
		this.enemyScore = enemyScore;
	}
	
}
