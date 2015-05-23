package JDBC;

public class GameState {
	
	private String username;
	private int playerScore;
	private int enemyScore;
	private int fullScore;
	
	public GameState(String username, int playerScore, int enemyScore, int fullScore) {
		super();
		this.username = username;
		this.playerScore = playerScore;
		this.enemyScore = enemyScore;
		this.fullScore = fullScore;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public int getPlayerScore() {
		return playerScore;
	}
	
	public void setPlayerScore(int playerScore) {
		this.playerScore = playerScore;
	}
	
	public int getEnemyScore() {
		return enemyScore;
	}
	
	public void setEnemyScore(int enemyScore) {
		this.enemyScore = enemyScore;
	}

	public int getFullScore() {
		return fullScore;
	}

	public void setFullScore(int fullScore) {
		this.fullScore = fullScore;
	}
	
}
