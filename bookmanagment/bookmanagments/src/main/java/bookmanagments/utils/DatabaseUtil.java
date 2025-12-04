package bookmanagments.utils;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtil {

	private static final String URL = "jdbc:mysql://localhost:3306/bookstore?allowPublicKeyRetrieval=true&useSSL=false";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "root";

	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("MySQL JDBC Driver Registered!");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("MySQL JDBC Driver not found!", e);
		}
	}

	public static Connection getConnection() throws SQLException {
		Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		System.out.println("Database connection established!");
		return connection;
	}

	// Test connection
	public static void main(String[] args) {
		try {
			Connection conn = getConnection();
			System.out.println("Connection test successful!");
			conn.close();
		} catch (SQLException e) {
			System.out.println("Connection test failed: " + e.getMessage());
		}
	}
}
