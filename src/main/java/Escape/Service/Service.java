package Escape.Service;

import java.util.List;
import java.util.logging.Logger;

import Escape.Controller.Controller;
import Escape.Model.Arena;
import Escape.View.View;
import JDBC.DAO;
import JDBC.GameState;

/**
 * Manages the UI's functions.
 * Sets the game area to default at the end of a round. Creates a new game
 * and resets scores. 
 */
public class Service {

	/**
	 * Creates logs.
	 */
	protected static Logger	logger = Logger.getLogger(Service.class.getName());
	
	/**
	 * Sets the game area to default at the end of a round.
	 * 
	 * @param arena sets to default
	 */
	public static void setDefault(Arena arena){
		arena.getPlayer().setX((arena.getSize()/2)*arena.getStep());
		arena.getPlayer().setY(0*arena.getSize());
		arena.getPlayer().setActive(true);
		
		arena.getEnemy1().setX((arena.getSize()-1)*arena.getStep());
		arena.getEnemy1().setY((arena.getSize()-1)*arena.getStep());
		arena.getEnemy1().setActive(true);
		
		arena.getEnemy2().setX(0*arena.getStep());
		arena.getEnemy2().setY((arena.getSize()-1)*arena.getStep());
		arena.getEnemy2().setActive(true);
		
		arena.getTraps().trapsInit(arena.getSize(), arena.getStep(), 
				arena.getPlayer(), arena.getEnemy1(), arena.getEnemy2());
		
		logger.info("Arena is in default state");
	}
	
	/**
	 * Creates a new game and resets scores.
	 * 
	 * @param arena the game arena
	 * @param control the controller of the game
	 * @param view the Game tab of JFrame
	 */
	public static void newGame(Arena arena, Controller control, View view){
		setDefault(arena);
		control.setPlayerScore(0);
		control.setEnemyScore(0);
		view.updateView();
		logger.info("Started a new game");
	}
	
	/**
	 * Saves the game to database and creates a new game.
	 * Creates a GameState about the game. 
	 * Makes a DAO and calls <code>addGameState</code> to save game to database.
	 * 
	 * @param control the controller of the game
	 * @param username the player's name
	 * @param DAOpassword for database connection
	 */
	public static void saveGame(Controller control, String username, String DAOpassword){
		GameState newGameState = new GameState(username, control.getPlayerScore(), control.getEnemyScore(),
				control.getPlayerScore()-control.getEnemyScore());
		System.out.println(control.getPlayerScore()+control.getEnemyScore());
		DAO dao = new DAO();
		dao.addGameState(newGameState, DAOpassword);
		logger.info("Game saved");
	}
	
	/**
	 * Returns the TOP 5 score of the game.
	 * Makes a DAO and calls <code>getTop5</code> to returns scores.
	 * 
	 * @param DAOpassword for database connection
	 * @return list of TOP 5 scores
	 */
	public static List<GameState> getTop5(String DAOpassword){
		DAO dao = new DAO();
		return dao.getTop5(DAOpassword);
	}
	
}
