package EscapeTest;

import static org.junit.Assert.*;

import org.junit.Test;

import Escape.Model.Arena;
import Escape.Model.ArenaObject;

public class ArenaTest {
	
	private void AssertObject(int expectedX, int expectedY, boolean expectedActive, ArenaObject actual){
		assertEquals(expectedX, actual.getX());
		assertEquals(expectedY, actual.getY());
		assertEquals(expectedActive, actual.isActive());
	}

	@Test
	public void ArenaObjectTest() {
		AssertObject(200, 200, true, new ArenaObject(200, 200));
	}
	
	@Test
	public void ArenaTests(){
		Arena arena = new Arena(6, 600);
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
		assertFalse(arena.enemiesIsActive());
	}

}
