package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

import Escape.Controller.Controller;

/**
 * Creates a factory that returns a database connection.
 */
public class ConnectionFactory {

	/**
	 * Creates logs.
	 */
	protected static Logger	logger = Logger.getLogger(Controller.class.getName());
	
	/**
	 * A factory, only this can make connections to database.
	 */
	private static ConnectionFactory factory = new ConnectionFactory();
	
	/**
	 * The URL of database.
	 */
	static final String DB_URL = "jdbc:oracle:thin:@db.inf.unideb.hu:1521:ora11g";
	
	/**
	 * The username for connection.
	 */
	private String username;
	
	/**
	 * The password for connection.
	 */
	private String password;
	
	/**
	 * Register the <code>oracle.jdbc.OracleDriver()</code> driver.
	 */
	private ConnectionFactory(){
		try {
			DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
			logger.info("Oracle Driver register done");
		} catch (SQLException e) {
			//e.printStackTrace();
			logger.throwing("ConnectionFactory", "registerDriverFault", e);
			//System.out.println("ERROR: Can not register oracle driver.");
		}
	}
	
	/**
	 * Creates a new connection to database and returns with that.
	 * 
	 * @return a new connection to database
	 */
	private Connection createConnection(){
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(DB_URL, username, password);
			logger.info("Connection created");
		} catch (SQLException e) {
			/*e.printStackTrace();
			System.out.println("ERROR: Can not create connection: "+username+" "+password);*/
			logger.throwing("ConnectionFactory", "connectionFault_"+username+"_"+password, e);
		}
		return connection;
	}
	
	/**
	 * Others ask <code>factory</code> to give a new connection.
	 * Calls the <code>createConnection</code> method.
	 * 
	 * @param _username for connection to database
	 * @param _password for connection to database
	 * @return a new connection to database
	 */
	public static Connection getConnection(String _username, String _password){
		logger.info("player name:"+_username+" DB password:"+_password);
		factory.username = _username;
		factory.password = _password;
		return factory.createConnection();
	}
}
