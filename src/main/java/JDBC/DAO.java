package JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DAO {
	
	public void createGameStateTable(){
		try (Connection connection = ConnectionFactory.getConnection("H_ECSSN7", "kassai")){
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
	
	public List<GameState> getTop5(){
		List<GameState> result = new ArrayList<GameState>();
		try (Connection connection = ConnectionFactory.getConnection("H_ECSSN7", "kassai")){
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM (SELECT * FROM gamestate ORDER BY full_score DESC) WHERE ROWNUM<6");
			while(resultSet.next()){
				String username = resultSet.getString(1);
				int playerScore = resultSet.getInt(2);
				int enemyScore = resultSet.getInt(3);
				int fullScore = resultSet.getInt(4);
				//System.out.println(username + playerScore + enemyScore + fullScore);
				result.add(new GameState(username, playerScore, enemyScore, fullScore));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		/*for (GameState gameState : result) {
			System.out.println(gameState.getUsername());
		}*/
		return result;
	}

	public void addGameState(GameState gameState){
		try(Connection connection = ConnectionFactory.getConnection("H_ECSSN7", "kassai")){
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
