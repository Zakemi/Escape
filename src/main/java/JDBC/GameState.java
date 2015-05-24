package JDBC;

/**
 * The database contains GameState objects. 
 */
public class GameState {
	
	/**
	 * The name of the player.
	 */
	private String username;
	
	/**
	 * The score of the <code>player</code>.
	 */
	private int playerScore;
	
	/**
	 * The score of the enemies.
	 */
	private int enemyScore;
	
	/**
	 * The total score of the game.
	 * Calculate this with reduce the <code>playerScore</code> with <code>enemyScore</code>. 
	 */
	private int fullScore;
	
	/**
	 * Create a GameState.
	 * 
	 * @param username the name of the player
	 * @param playerScore the score of the <code>player</code>
	 * @param enemyScore the score of the enemies
	 * @param fullScore the total score of the game
	 */
	public GameState(String username, int playerScore, int enemyScore, int fullScore) {
		super();
		this.username = username;
		this.playerScore = playerScore;
		this.enemyScore = enemyScore;
		this.fullScore = fullScore;
	}
	
	/**
	 * Returns the name of the player.
	 * 
	 * @return the name of the player
	 */
	public String getUsername() {
		return username;
	}
	
	/**
	 * Sets the name of the player.
	 * 
	 * @param username the name of the player
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	
	/**
	 * Returns the score of the <code>player</code>.
	 * 
	 * @return the score of the <code>player</code>
	 */
	public int getPlayerScore() {
		return playerScore;
	}
	
	/**
	 * Sets the score of the <code>player</code>.
	 * 
	 * @param playerScore the score of the <code>player</code>
	 */
	public void setPlayerScore(int playerScore) {
		this.playerScore = playerScore;
	}
	
	/**
	 * Returns the score of the enemies.
	 * 
	 * @return the score of the enemies
	 */
	public int getEnemyScore() {
		return enemyScore;
	}
	
	/**
	 * Sets the score of the enemies.
	 * 
	 * @param enemyScore the score of the enemies
	 */
	public void setEnemyScore(int enemyScore) {
		this.enemyScore = enemyScore;
	}

	/**
	 * Return the total score of the game.
	 * 
	 * @return the total score of the game
	 */
	public int getFullScore() {
		return fullScore;
	}

	/**
	 * Sets the total score of the game.
	 * 
	 * @param fullScore the total score of the game
	 */
	public void setFullScore(int fullScore) {
		this.fullScore = fullScore;
	}
	
}
