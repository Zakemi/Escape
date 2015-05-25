package EscapeTest;

import static org.junit.Assert.*;


import org.junit.Test;

import JDBC.ConnectionFactory;

public class DatabaseTest {

	@Test
	public void test() {
		assertNull(ConnectionFactory.getConnection("asduser", "asdpass"));
	}

	/*@Test(expected= NullPointerException.class)
	public void createTableTest(){
		DAO dao = new DAO();
		dao.createGameStateTable("password");
	}
	
	@Test(expected= NullPointerException.class)
	public void getTOP5Test(){
		DAO dao = new DAO();
		dao.getTop5("password");
	}
	
	@Test(expected= NullPointerException.class)
	public void addGameStateTest(){
		DAO dao = new DAO();
		dao.addGameState(new GameState("user", 4, 4, 0), "password");
	}*/
}
