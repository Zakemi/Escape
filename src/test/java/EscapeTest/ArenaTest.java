package EscapeTest;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import Escape.Model.Arena;
import Escape.Model.ArenaObject;
import Escape.Model.Enemy;
import Escape.Model.Player;
import Escape.Model.Traps;

public class ArenaTest {
	
	private void AssertObject(int expectedX, int expectedY, boolean expectedActive, ArenaObject actual){
		assertEquals(expectedX, actual.getX());
		assertEquals(expectedY, actual.getY());
		assertEquals(expectedActive, actual.isActive());
	}
	
	private void AssertObject(ArenaObject expected, ArenaObject actual){
		assertEquals(expected.getX(), actual.getX());
		assertEquals(expected.getY(), actual.getY());
		assertEquals(expected.isActive(), actual.isActive());
	}
	
	private boolean AssertNotEqualObject(ArenaObject a, ArenaObject b){
		if (a.getX() != b.getX() || a.getY() != b.getY())
			return true;
		return false;
	}
	
	private void AssertListEquals(List<ArenaObject> expected, List<ArenaObject> actual){
		assertEquals(expected.size(), actual.size());
		for(int i=0; i<actual.size(); i++)
			AssertObject(expected.get(i).getX(), expected.get(i).getY(), expected.get(i).isActive(), actual.get(i));
	}

	@Test
	public void ArenaObjectTest() {
		AssertObject(200, 200, true, new ArenaObject(200, 200));
	}
	
	@Test
	public void enemyNotNull(){
		assertNotNull(new Enemy(100, 200));
	}
	
	@Test
	public void ArenaTests(){
		Arena arena = new Arena(6, 600);
		assertNotNull(new Arena(6, 600));
		assertEquals(6, arena.getSize());
		assertEquals(600, arena.getWinSize());
		assertEquals(100, arena.getStep());
		AssertObject(300, 0, true, arena.getPlayer());
		AssertObject(0, 500, true, arena.getEnemy2());
		AssertObject(500, 500, true, arena.getEnemy1());
		
	}
	
	@Test
	public void ArenaEnemiesActiveTest(){
		Arena arena = new Arena(6, 600);
		assertTrue(arena.enemiesIsActive());
		
		arena.getEnemy1().setActive(false);
		assertTrue(arena.enemiesIsActive());
		
		arena.getEnemy1().setActive(true);
		arena.getEnemy2().setActive(false);
		assertTrue(arena.enemiesIsActive());
		
		arena.getEnemy1().setActive(false);
		boolean bool = arena.enemiesIsActive();
		assertFalse(bool);
	}

	@Test
	public void TrapsLocationTest(){
		for(int i=0; i<100; i++){
			Player player = new Player(300, 0);
			Enemy enemy1 = new Enemy(0, 500);
			Enemy enemy2 = new Enemy(500,500);
			Traps traps = new Traps(2, 6, 100, player, enemy1, enemy2);
			assertTrue(AssertNotEqualObject(traps.getList().get(0), player));
			assertTrue(AssertNotEqualObject(traps.getList().get(0), enemy1));
			assertTrue(AssertNotEqualObject(traps.getList().get(0), enemy2));
			assertTrue(AssertNotEqualObject(traps.getList().get(1), player));
			assertTrue(AssertNotEqualObject(traps.getList().get(1), enemy1));
			assertTrue(AssertNotEqualObject(traps.getList().get(1), enemy2));
			assertNotEquals(400, player.getX());
			assertNotEquals(300, player.getY());
			List<ArenaObject> newtraps = new ArrayList<ArenaObject>();
			newtraps.add(new ArenaObject(300, 300));
			traps.setList(newtraps);
			AssertListEquals(newtraps, traps.getList());
		}
	}
	
	@Test
	public void GettersAndSetters(){
		Arena arena = new Arena(6, 600);
		
		Player pl = arena.getPlayer();
		arena.setPlayer(pl);
		AssertObject(pl, arena.getPlayer());
		
		Enemy en1 = arena.getEnemy1();
		arena.setEnemy1(en1);
		AssertObject(en1, arena.getEnemy1());
		
		Enemy en2 = arena.getEnemy2();
		arena.setEnemy2(en2);
		AssertObject(en2, arena.getEnemy2());
		
		Traps trs = arena.getTraps();
		arena.setTraps(trs);
		AssertListEquals(trs.getList(), arena.getTraps().getList());
		
		arena.setSize(4);
		assertEquals(4, arena.getSize());
		
		arena.setWinSize(400);
		assertEquals(400, arena.getWinSize());
		
		arena.setStep(200);
		assertEquals(200, arena.getStep());
		
		arena.setTrapSize(1);
		assertEquals(1, arena.getTrapSize());
	}

	@Test
	public void ArenaObjectGetSetTest(){
		ArenaObject obj = new ArenaObject(100, 200);
		
		obj.setX(300);
		assertEquals(300, obj.getX());
		
		obj.setY(400);
		assertEquals(400, obj.getY());
		
		obj.setActive(false);
		assertFalse(obj.isActive());
		
		assertEquals("ArenaObject [x=300, y=400, active=false]", obj.toString());
	}
	
	@Test
	public void EnemyStepAlgorithm(){
		Player player = new Player(300,0);
		Enemy enemy = new Enemy(0, 500);
		
		enemy.setXY(player, 100);
		assertEquals(0, enemy.getX());
		assertEquals(400, enemy.getY());
		
		player = new Player(0, 400);
		enemy = new Enemy(300, 0);
		
		enemy.setXY(player, 100);
		assertEquals(300, enemy.getX());
		assertEquals(100, enemy.getY());
		
		player = new Player(0, 100);
		enemy = new Enemy(300, 0);
		
		enemy.setXY(player, 100);
		assertEquals(200, enemy.getX());
		assertEquals(0, enemy.getY());
		
		player = new Player(300, 0);
		enemy = new Enemy(500, 500);
		
		enemy.setXY(player, 100);
		assertEquals(500, enemy.getX());
		assertEquals(400, enemy.getY());
		
		player = new Player(500, 500);
		enemy = new Enemy(300, 0);
		
		enemy.setXY(player, 100);
		assertEquals(300, enemy.getX());
		assertEquals(100, enemy.getY());
	}
}
