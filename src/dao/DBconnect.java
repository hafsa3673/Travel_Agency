package dao;
import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconnect {
	

	private static Connection connection;
			static {
				String url= "jdbc:mysql://localhost:3306/agence?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
				String user="root";
				String password="agence";
				
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					 
					connection=DriverManager.getConnection(url, user, password);
					System.out.println("connexion �tablie !!! ");
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace(); 
				}
				
			}
			public static Connection getConnection() {
				return connection;
			}
			

	}


