package Escape.Service;

import java.util.List;

import Escape.Controller.Controller;
import Escape.Model.Arena;
import Escape.View.View;
import JDBC.DAO;
import JDBC.GameState;

public class Service {

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
	}
	
	public static void newGame(Arena arena, Controller control, View view){
		setDefault(arena);
		control.setPlayerScore(0);
		control.setEnemyScore(0);
		view.updateView();
	}
	
	public static void saveGame(Controller control, String username){
		GameState newGameState = new GameState(username, control.getPlayerScore(), control.getEnemyScore(),
				control.getPlayerScore()-control.getEnemyScore());
		System.out.println(control.getPlayerScore()+control.getEnemyScore());
		DAO dao = new DAO();
		dao.addGameState(newGameState);
	}
	
	public static List<GameState> getTop5(){
		DAO dao = new DAO();
		return dao.getTop5();
	}
	
}
