package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionFactory {

	private static ConnectionFactory factory = new ConnectionFactory();
	static final String DB_URL = "jdbc:oracle:thin:@db.inf.unideb.hu:1521:ora11g";
	private static String username;
	private static String password;
	
	private ConnectionFactory(){
		try {
			DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("ERROR: Can not register oracle driver.");
		}
	}
	
	private Connection createConnection(){
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(DB_URL, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("ERROR: Can not create connection: "+username+" "+password);
		}
		return connection;
	}
	
	public static Connection getConnection(String _username, String _password){
		username = _username;
		password = _password;
		return factory.createConnection();
	}
}
