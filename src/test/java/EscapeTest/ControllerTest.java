package EscapeTest;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import Escape.Controller.Controller;
import Escape.Model.Arena;
import Escape.Model.ArenaObject;
import Escape.Model.Player;
import Escape.View.View;

public class ControllerTest {

	@Test
	public void setPlayerX_Y() {
		Arena arena = new Arena(6, 600);
		View view = new View(arena);
		Controller control = new Controller(arena, view);
		view.setControl(control);
		
		assertFalse(control.setPlayerX(-100));
		assertFalse(control.setPlayerX(700));
		assertFalse(control.setPlayerX(600));
		assertFalse(control.setPlayerX(-1));
		assertFalse(control.setPlayerY(-100));
		assertFalse(control.setPlayerY(700));
		assertFalse(control.setPlayerY(600));
		assertFalse(control.setPlayerY(-1));
		
		assertTrue(control.setPlayerX(0));
		assertTrue(control.setPlayerX(200));
		assertTrue(control.setPlayerX(500));
		assertTrue(control.setPlayerY(0));
		assertTrue(control.setPlayerY(200));
		assertTrue(control.setPlayerY(500));
		
		arena.getPlayer().setActive(false);
		assertFalse(control.setPlayerX(200));
		assertFalse(control.setPlayerY(200));
	}

	@Test
	public void keyVK_STHTest() {
		Arena arena = new Arena(6, 600);
		View view = new View(arena);
		Controller control = new Controller(arena, view);
		view.setControl(control);
		
		control.keyVK_DOWN();
		control.keyVK_RIGHT();
		control.keyVK_UP();
		control.keyVK_LEFT();
		
		arena.setPlayer(new Player(0, 0));
		control.keyVK_UP();
		control.keyVK_LEFT();
		
		arena.setPlayer(new Player(500, 500));
		control.keyVK_DOWN();
		control.keyVK_RIGHT();
		
	}
	
	@Test
	public void keyEventTest(){
		Arena arena = new Arena(6, 600);
		View view = new View(arena);
		Controller control = new Controller(arena, view);
		view.setControl(control);
		
		int enemyscore = control.getEnemyScore();
		arena.getPlayer().setActive(false);
		control.keyEvent(false);
		assertEquals(enemyscore+1, control.getEnemyScore());
		
		enemyscore = control.getEnemyScore();
		arena.getEnemy1().setActive(false);
		control.keyEvent(false);
		assertEquals(enemyscore, control.getEnemyScore());
		
		enemyscore = control.getEnemyScore();
		arena.getEnemy1().setActive(true);
		arena.getEnemy2().setActive(false);
		control.keyEvent(false);
		assertEquals(enemyscore, control.getEnemyScore());
		
		enemyscore = control.getEnemyScore();
		arena.getEnemy1().setActive(false);
		control.keyEvent(false);
		assertEquals(enemyscore, control.getEnemyScore());
		
		enemyscore = control.getEnemyScore();
		int playerscore = control.getPlayerScore();
		arena.getPlayer().setActive(true);
		arena.getEnemy1().setActive(false);
		arena.getEnemy2().setActive(false);
		control.keyEvent(false);
		assertEquals(enemyscore, control.getEnemyScore());
		assertEquals(playerscore+1, control.getPlayerScore());
	}
	
	@Test
	public void KeyEventTest2(){
		Arena arena = new Arena(6, 600);
		View view = new View(arena);
		Controller control = new Controller(arena, view);
		view.setControl(control);
		
		arena.getPlayer().setActive(true);
		arena.getEnemy1().setActive(true);
		arena.getEnemy2().setActive(true);
		arena.setPlayer(new Player(300, 100));
		arena.getTraps().setList(new ArrayList<ArenaObject>());
		arena.getTraps().getList().add(new ArenaObject(300, 100));
		arena.getTraps().getList().add(new ArenaObject(500, 400));
		control.setPlayerActive();
		assertFalse(arena.getPlayer().isActive());
		control.keyEvent(true);
		assertTrue(arena.getPlayer().isActive());
	}
	
	@Test
	public void KeyEventTest3(){
		Arena arena = new Arena(6, 600);
		View view = new View(arena);
		Controller control = new Controller(arena, view);
		view.setControl(control);
		
		arena.getPlayer().setActive(true);
		arena.getEnemy1().setActive(true);
		arena.getEnemy2().setActive(true);
		arena.setPlayer(new Player(300, 100));
		arena.getTraps().setList(new ArrayList<ArenaObject>());
		arena.getTraps().getList().add(new ArenaObject(500, 500));
		arena.getTraps().getList().add(new ArenaObject(000, 500));
		control.setPlayerActive();
		assertTrue(arena.getPlayer().isActive());
		control.setEnemyAndTrapActive();
		assertFalse(arena.getEnemy1().isActive());
		assertFalse(arena.getEnemy2().isActive());
		assertFalse(arena.getTraps().getList().get(0).isActive());
		assertFalse(arena.getTraps().getList().get(1).isActive());
	}
	
	@Test
	public void setScoreTest(){
		Arena arena = new Arena(6, 600);
		View view = new View(arena);
		Controller control = new Controller(arena, view);
		view.setControl(control);
		
		control.setPlayerScore(5);
		assertEquals(5, control.getPlayerScore());
		
		control.setEnemyScore(6);
		assertEquals(6, control.getEnemyScore());
	}
	
	@Test
	public void setPlayerActiveTest(){
		Arena arena = new Arena(6, 600);
		View view = new View(arena);
		Controller control = new Controller(arena, view);
		view.setControl(control);
		
		List<ArenaObject> traps = new ArrayList<ArenaObject>();
		traps.add(new ArenaObject(600, 600));
		traps.add(new ArenaObject(700, 700));
		arena.getTraps().setList(traps);
		
		arena.getPlayer().setX(300);
		arena.getPlayer().setY(300);
		arena.getEnemy1().setX(300);
		arena.getEnemy1().setY(300);
		control.setPlayerActive();
		assertFalse(arena.getPlayer().isActive());
		
		arena.getPlayer().setActive(true);
		arena.getPlayer().setX(300);
		arena.getPlayer().setY(300);
		arena.getEnemy1().setX(500);
		arena.getEnemy1().setY(500);
		arena.getEnemy2().setX(300);
		arena.getEnemy2().setY(300);
		control.setPlayerActive();
		assertFalse(arena.getPlayer().isActive());
		
		arena.getPlayer().setActive(true);
		arena.getPlayer().setX(300);
		arena.getPlayer().setY(300);
		arena.getEnemy1().setX(0);
		arena.getEnemy1().setY(0);
		arena.getEnemy2().setX(300);
		arena.getEnemy2().setY(400);
		control.setPlayerActive();
		assertTrue(arena.getPlayer().isActive());
		
		arena.getPlayer().setActive(true);
		arena.getPlayer().setX(300);
		arena.getPlayer().setY(300);
		arena.getEnemy1().setX(500);
		arena.getEnemy1().setY(500);
		arena.getEnemy2().setX(400);
		arena.getEnemy2().setY(400);
		traps = new ArrayList<ArenaObject>();
		traps.add(new ArenaObject(300, 300));
		traps.add(new ArenaObject(200, 200));
		arena.getTraps().setList(traps);
		control.setPlayerActive();
		assertFalse(arena.getPlayer().isActive());
		
		arena.getPlayer().setActive(true);
		traps = new ArrayList<ArenaObject>();
		traps.add(new ArenaObject(200, 200));
		traps.add(new ArenaObject(300, 300));
		arena.getTraps().setList(traps);
		control.setPlayerActive();
		assertFalse(arena.getPlayer().isActive());
		
		arena.getPlayer().setActive(true);
		arena.getPlayer().setX(300);
		arena.getPlayer().setY(300);
		traps = new ArrayList<ArenaObject>();
		traps.add(new ArenaObject(200, 200));
		traps.add(new ArenaObject(300, 300));
		traps.get(1).setActive(false);
		arena.getTraps().setList(traps);
		control.setPlayerActive();
		assertTrue(arena.getPlayer().isActive());
	}
	
	@Test
	public void setEnemyAndTrapActive1(){
		Arena arena = new Arena(6, 600);
		View view = new View(arena);
		Controller control = new Controller(arena, view);
		view.setControl(control);
		
		arena.getEnemy1().setX(500);
		arena.getEnemy1().setY(500);
		arena.getEnemy2().setX(400);
		arena.getEnemy2().setY(400);
		List<ArenaObject> traps = new ArrayList<ArenaObject>();
		traps.add(new ArenaObject(500, 500));
		traps.add(new ArenaObject(400, 400));
		arena.getTraps().setList(traps);
		control.setEnemyAndTrapActive();
		assertFalse(arena.getEnemy1().isActive());
		assertFalse(arena.getEnemy2().isActive());
		assertFalse(arena.getTraps().getList().get(0).isActive());
		assertFalse(arena.getTraps().getList().get(1).isActive());
	}
	
	@Test
	public void setEnemyAndTrapActive2(){
		Arena arena = new Arena(6, 600);
		View view = new View(arena);
		Controller control = new Controller(arena, view);
		view.setControl(control);
		
		arena.getEnemy1().setX(500);
		arena.getEnemy1().setY(500);
		arena.getEnemy2().setX(400);
		arena.getEnemy2().setY(400);
		List<ArenaObject> traps = new ArrayList<ArenaObject>();
		traps.add(new ArenaObject(400, 400));
		traps.add(new ArenaObject(100, 100));
		arena.getTraps().setList(traps);
		control.setEnemyAndTrapActive();
		assertTrue(arena.getEnemy1().isActive());
		assertFalse(arena.getEnemy2().isActive());
		assertFalse(arena.getTraps().getList().get(0).isActive());
		assertTrue(arena.getTraps().getList().get(1).isActive());
	}
	
	@Test
	public void setEnemyAndTrapActive3(){
		Arena arena = new Arena(6, 600);
		View view = new View(arena);
		Controller control = new Controller(arena, view);
		view.setControl(control);
		
		arena.getEnemy1().setX(500);
		arena.getEnemy1().setY(500);
		arena.getEnemy2().setX(400);
		arena.getEnemy2().setY(400);
		List<ArenaObject> traps = new ArrayList<ArenaObject>();
		traps.add(new ArenaObject(300, 300));
		traps.add(new ArenaObject(500, 500));
		arena.getTraps().setList(traps);
		control.setEnemyAndTrapActive();
		assertFalse(arena.getEnemy1().isActive());
		assertTrue(arena.getEnemy2().isActive());
		assertTrue(arena.getTraps().getList().get(0).isActive());
		assertFalse(arena.getTraps().getList().get(1).isActive());
	}
	
	@Test
	public void setEnemyAndTrapActive4(){
		Arena arena = new Arena(6, 600);
		View view = new View(arena);
		Controller control = new Controller(arena, view);
		view.setControl(control);
		
		arena.getEnemy1().setX(500);
		arena.getEnemy1().setY(500);
		arena.getEnemy2().setX(400);
		arena.getEnemy2().setY(400);
		List<ArenaObject> traps = new ArrayList<ArenaObject>();
		traps.add(new ArenaObject(500, 300));
		traps.add(new ArenaObject(500, 400));
		arena.getTraps().setList(traps);
		control.setEnemyAndTrapActive();
		assertTrue(arena.getEnemy1().isActive());
		assertTrue(arena.getEnemy2().isActive());
		assertTrue(arena.getTraps().getList().get(0).isActive());
		assertTrue(arena.getTraps().getList().get(1).isActive());
	}
	
	@Test
	public void setEnemyAndTrapActive5(){
		Arena arena = new Arena(6, 600);
		View view = new View(arena);
		Controller control = new Controller(arena, view);
		view.setControl(control);
		
		arena.getEnemy1().setX(500);
		arena.getEnemy1().setY(500);
		arena.getEnemy2().setX(400);
		arena.getEnemy2().setY(400);
		List<ArenaObject> traps = new ArrayList<ArenaObject>();
		traps.add(new ArenaObject(500, 500));
		traps.add(new ArenaObject(500, 400));
		traps.get(0).setActive(false);
		arena.getTraps().setList(traps);
		control.setEnemyAndTrapActive();
		assertTrue(arena.getEnemy1().isActive());
		assertTrue(arena.getEnemy2().isActive());
		assertFalse(arena.getTraps().getList().get(0).isActive());
		assertTrue(arena.getTraps().getList().get(1).isActive());
	}
	
	@Test
	public void setEnemyAndTrapActive6(){
		Arena arena = new Arena(6, 600);
		View view = new View(arena);
		Controller control = new Controller(arena, view);
		view.setControl(control);
		
		arena.getEnemy1().setX(500);
		arena.getEnemy1().setY(500);
		arena.getEnemy2().setX(400);
		arena.getEnemy2().setY(400);
		List<ArenaObject> traps = new ArrayList<ArenaObject>();
		traps.add(new ArenaObject(400, 500));
		traps.add(new ArenaObject(200, 400));
		arena.getTraps().setList(traps);
		control.setEnemyAndTrapActive();
		assertTrue(arena.getEnemy1().isActive());
		assertTrue(arena.getEnemy2().isActive());
		assertTrue(arena.getTraps().getList().get(0).isActive());
		assertTrue(arena.getTraps().getList().get(1).isActive());
	}
	
	@Test
	public void enemyStepTest1(){
		Arena arena = new Arena(6, 600);
		View view = new View(arena);
		Controller control = new Controller(arena, view);
		view.setControl(control);
		
		int enemy1y = arena.getEnemy1().getY();
		int enemy2y = arena.getEnemy2().getY();
		arena.getPlayer().setActive(true);
		control.enemyStep();
		assertNotEquals(enemy1y, arena.getEnemy1().getY());
		assertNotEquals(enemy2y, arena.getEnemy2().getY());
	}
	
	@Test
	public void enemyStepTest2(){
		Arena arena = new Arena(6, 600);
		View view = new View(arena);
		Controller control = new Controller(arena, view);
		view.setControl(control);
		
		int enemy1y = arena.getEnemy1().getY();
		int enemy2y = arena.getEnemy2().getY();
		arena.getPlayer().setActive(false);
		control.enemyStep();
		assertEquals(enemy1y, arena.getEnemy1().getY());
		assertEquals(enemy2y, arena.getEnemy2().getY());
	}
	
	@Test
	public void enemyStepTest3(){
		Arena arena = new Arena(6, 600);
		View view = new View(arena);
		Controller control = new Controller(arena, view);
		view.setControl(control);
		
		int enemy1y = arena.getEnemy1().getY();
		int enemy2y = arena.getEnemy2().getY();
		arena.getEnemy1().setActive(false);
		control.enemyStep();
		assertEquals(enemy1y, arena.getEnemy1().getY());
		assertNotEquals(enemy2y, arena.getEnemy2().getY());
	}
	
	@Test
	public void enemyStepTest4(){
		Arena arena = new Arena(6, 600);
		View view = new View(arena);
		Controller control = new Controller(arena, view);
		view.setControl(control);
		
		int enemy1y = arena.getEnemy1().getY();
		int enemy2y = arena.getEnemy2().getY();
		arena.getEnemy2().setActive(false);
		control.enemyStep();
		assertNotEquals(enemy1y, arena.getEnemy1().getY());
		assertEquals(enemy2y, arena.getEnemy2().getY());
	}
}
