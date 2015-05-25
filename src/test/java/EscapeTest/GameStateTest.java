package EscapeTest;

import static org.junit.Assert.*;

import org.junit.Test;

import JDBC.GameState;

public class GameStateTest {

	@Test
	public void constructorTest() {
		GameState gs = new GameState("asd", 3, 1, 2);
		assertEquals("asd", gs.getUsername());
		assertEquals(3, gs.getPlayerScore());
		assertEquals(1, gs.getEnemyScore());
		assertEquals(2, gs.getFullScore());
	}
	
	@Test
	public void getSetTest(){
		GameState gs = new GameState("bla", 0, 0, 0);
		gs.setUsername("asd");
		gs.setEnemyScore(1);
		gs.setPlayerScore(3);
		gs.setFullScore(2);
		assertEquals("asd", gs.getUsername());
		assertEquals(3, gs.getPlayerScore());
		assertEquals(1, gs.getEnemyScore());
		assertEquals(2, gs.getFullScore());
	}

}
