package EscapeTest;

import static org.junit.Assert.*;

import org.junit.Test;

import Escape.Controller.Controller;
import Escape.Model.Arena;
import Escape.Model.ArenaObject;
import Escape.Service.Service;
import Escape.View.View;

public class ServiceTest {
	
	private void AssertObject(int expectedX, int expectedY, boolean expectedActive, ArenaObject actual){
		assertEquals(expectedX, actual.getX());
		assertEquals(expectedY, actual.getY());
		assertEquals(expectedActive, actual.isActive());
	}

	@Test
	public void setDefaultTest() {
		Arena arena = new Arena(6, 600);
		
		Service.setDefault(arena);
		AssertObject(300, 0, true, arena.getPlayer());
		AssertObject(0, 500, true, arena.getEnemy2());
		AssertObject(500, 500, true, arena.getEnemy1());
	}
	
	@Test
	public void newGameTest(){
		Arena arena = new Arena(6, 600);
		View view = new View(arena);
		Controller control = new Controller(arena, view);
		view.setControl(control);
		
		Service.newGame(arena, control, view);
		AssertObject(300, 0, true, arena.getPlayer());
		AssertObject(0, 500, true, arena.getEnemy2());
		AssertObject(500, 500, true, arena.getEnemy1());
		
		assertEquals(0, control.getPlayerScore());
		assertEquals(0, control.getEnemyScore());
	}

}
