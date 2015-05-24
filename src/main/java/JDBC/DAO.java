package JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * A DAO for the JDBC connection.
 */
public class DAO {
	
	/**
	 * Creates the GAMESTATE table in database.
	 * Uses this SQL queue:
	 * <pre>
	 * CREATE TABLE gamestate(
	 * 		username varchar(64) not null,
	 * 		player_score number(10) not null,
	 * 		enemy_score number(10) not null,
	 * 		full_score number(10) not null
	 * );
	 * </pre>
	 * 
	 * @param password for connect to database
	 */
	public void createGameStateTable(String password){
		try (Connection connection = ConnectionFactory.getConnection("H_ECSSN7", password)){
			Statement statement = connection.createStatement();
			String create_table = "CREATE TABLE gamestate"
						+"("
						+"username varchar(64) not null,"
						+"player_score number(10) not null,"
						+"enemy_score number(10) not null,"
						+"full_score number(10) not null"
						+")";
			statement.executeUpdate(create_table);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Returns the TOP5 player score.
	 * Uses this SQL queue:
	 * <pre>
	 * SELECT * FROM 
	 * 		(SELECT * FROM gamestate 
	 * 		ORDER BY full_score DESC)
	 * WHERE ROWNUM{@literal <}6;
	 * </pre>
	 * 
	 * @param password for connect to database
	 * @return the list of score of players
	 */
	public List<GameState> getTop5(String password){
		List<GameState> result = new ArrayList<GameState>();
		try (Connection connection = ConnectionFactory.getConnection("H_ECSSN7", password)){
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM (SELECT * FROM gamestate ORDER BY full_score DESC) WHERE ROWNUM<6");
			while(resultSet.next()){
				String username = resultSet.getString(1);
				int playerScore = resultSet.getInt(2);
				int enemyScore = resultSet.getInt(3);
				int fullScore = resultSet.getInt(4);
				result.add(new GameState(username, playerScore, enemyScore, fullScore));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * Add a new player score to database.
	 * Uses this SQL queue:
	 * <pre>
	 * INSERT INTO gamestate(username, player_score, enemy_score, full_score) 
	 * VALUES (?,?,?,?);
	 * </pre>
	 * Before execute, add parameters to ? places.
	 * 
	 * @param gameState what will be added to database
	 * @param password for connect to database
	 */
	public void addGameState(GameState gameState, String password){
		try(Connection connection = ConnectionFactory.getConnection("H_ECSSN7", password)){
			System.out.println(gameState.getPlayerScore()+gameState.getEnemyScore());
			PreparedStatement prepstate = connection.prepareStatement("INSERT INTO gamestate(username, player_score, enemy_score, full_score) VALUES (?,?,?,?)");
			prepstate.setString(1, gameState.getUsername());
			prepstate.setInt(2, gameState.getPlayerScore());
			prepstate.setInt(3, gameState.getEnemyScore());
			prepstate.setInt(4, gameState.getFullScore());
			prepstate.executeQuery();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
