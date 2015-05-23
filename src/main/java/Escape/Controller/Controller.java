package Escape.Controller;

import Escape.Model.Arena;
import Escape.Model.ArenaObject;
import Escape.Service.Service;
import Escape.View.View;

public class Controller{
	private Arena arena;
	private View view;
	private int playerScore = 0;
	private int enemyScore = 0;
	
	public Controller(Arena arena, View view){
		this.arena = arena;
		this.view = view;
	}
	
	public void keyEvent(boolean didStep){
		setPlayerActive();
		if ( ! arena.getPlayer().isActive()){
			Service.setDefault(arena);
			enemyScore++;
			view.updateView();
		}
		else if ( ! arena.enemiesIsActive()){
			Service.setDefault(arena);
			playerScore++;
			view.updateView();
		}
		else if(didStep){
			enemyStep();
			setEnemyAndTrapActive();
		}
		view.updateView();
	}
	
	public void keyVK_LEFT(){
		boolean didStep = setPlayerX(arena.getPlayer().getX()-arena.getStep());
		keyEvent(didStep);
	}
	
	public void keyVK_RIGHT(){
		boolean didStep = setPlayerX(arena.getPlayer().getX()+arena.getStep());
		keyEvent(didStep);
	}
	
	public void keyVK_DOWN(){
		boolean didStep = setPlayerY(arena.getPlayer().getY()+arena.getStep());
		keyEvent(didStep);
	}
	
	public void keyVK_UP(){
		boolean didStep = setPlayerY(arena.getPlayer().getY()-arena.getStep());
		keyEvent(didStep);
	}
	
	public void setPlayerActive(){
		if (arena.getEnemy1().isActive() && arena.getEnemy1().getX() == arena.getPlayer().getX() && 
				arena.getEnemy1().getY() == arena.getPlayer().getY())
			arena.getPlayer().setActive(false);
		if (arena.getEnemy2().isActive() && arena.getEnemy2().getX() == arena.getPlayer().getX() && 
				arena.getEnemy2().getY() == arena.getPlayer().getY())
			arena.getPlayer().setActive(false);
		for (ArenaObject trap : arena.getTraps().getList()) {
			if (trap.isActive() && trap.getX() == arena.getPlayer().getX() && 
					trap.getY() == arena.getPlayer().getY()){
				arena.getPlayer().setActive(false);
			}
		}
	}
	
	public void setEnemyAndTrapActive(){
		for (ArenaObject trap : arena.getTraps().getList()) {
			if (trap.isActive() && arena.getEnemy1().getX() == trap.getX() && arena.getEnemy1().getY() == trap.getY()){
				arena.getEnemy1().setActive(false);
				trap.setActive(false);
			}
			if (trap.isActive() && arena.getEnemy2().getX() == trap.getX() && arena.getEnemy2().getY() == trap.getY()){
				arena.getEnemy2().setActive(false);
				trap.setActive(false);
			}
		}
	}
	
	public boolean setPlayerX(int x) {
		if (arena.getPlayer().isActive() && x >= 0 && x < arena.getWinSize()){
			arena.getPlayer().setX(x);
			return true;
		}
		return false;
	}
	
	public boolean setPlayerY(int y) {
		if (arena.getPlayer().isActive() && y >= 0 && y < arena.getWinSize()){
			arena.getPlayer().setY(y);
			return true;
		}
		return false;
	}

	public void enemyStep(){
		if (arena.getPlayer().isActive()){
			if (arena.getEnemy1().isActive())
				arena.getEnemy1().setXY(arena.getPlayer(), arena.getStep());
			if (arena.getEnemy2().isActive())
				arena.getEnemy2().setXY(arena.getPlayer(), arena.getStep());
		}
		setPlayerActive();
	}

	public int getPlayerScore() {
		return playerScore;
	}

	public int getEnemyScore() {
		return enemyScore;
	}

	public void setPlayerScore(int playerScore) {
		this.playerScore = playerScore;
	}

	public void setEnemyScore(int enemyScore) {
		this.enemyScore = enemyScore;
	}
	
	
	
}
