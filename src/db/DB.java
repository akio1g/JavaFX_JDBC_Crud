package db;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DB {

	private static Connection conn = null;
	
	public static Connection getConnection() { // conectar com banco de dados
		if(conn==null) {
			try {
			Properties prop = loadProperties(); // carregar propriedades do db properties
			String url = prop.getProperty("dburl"); // pega url
			conn = DriverManager.getConnection(url, prop); // conecta url com as propriedades
			} catch (SQLException e){
				throw new DBException(e.getMessage());
			}
		}
		return conn;
	}
	
	public static void closeConnection() {
		if(conn!=null) {
			try {
				conn.close();
			} catch (SQLException e) {
				throw new DBException(e.getMessage());
			}
		}
	}
	
	private static Properties loadProperties() { // carregar propriedades do db properties
		try (FileInputStream fs = new FileInputStream("db.properties")) {
			Properties prop = new Properties();
			prop.load(fs);
			return prop;
		} catch (FileNotFoundException e) {
			throw new DBException(e.getMessage());
		} catch (IOException e) {
			throw new DBException(e.getMessage());
		}
	}
	
	public static void closeStatement(Statement st) {
		if(st!=null) {
			try {
				st.close();
			} catch (SQLException e) {
				throw new db.DBException(e.getMessage());
			}
		}
	}
	
	public static void closeResultSet(ResultSet rs) {
		if(rs!=null) {
			try {
				rs.close();
			} catch (SQLException e) {
				throw new db.DBException(e.getMessage());
			}
		}
	}
}
