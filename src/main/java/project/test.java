package project;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class test {

	public static void main(String[] args) {

		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://172.30.1.76:3306", "ChangHwan","1234");
			System.out.println("success");
			Statement stmt = conn.createStatement();
		
		} catch(SQLException e) {
			System.out.println("SQLException" + e);
		}
		
		
	}

}
