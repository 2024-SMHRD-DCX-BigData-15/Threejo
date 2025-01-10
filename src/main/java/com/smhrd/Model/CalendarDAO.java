package com.smhrd.Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CalendarDAO {
	  private Connection getConnection() throws SQLException {
	        String url = "jdbc:mysql://localhost:3306/service_db";
	        String username = "ChangHwan";
	        String password = "1234";
	        return DriverManager.getConnection(url, username, password);
	    }

	    public boolean addEvent(CalendarVO calendarVO) {
	        String query = "INSERT INTO calendar_events (title, start_date, end_date) VALUES (?, ?, ?)";
	        try (Connection conn = getConnection();
	             PreparedStatement stmt = conn.prepareStatement(query)) {
	            stmt.setString(1, calendarVO.getTitle());
	            stmt.setString(2, calendarVO.getStartDate());
	            stmt.setString(3, calendarVO.getEndDate());
	            int result = stmt.executeUpdate();
	            return result > 0;  // 성공적으로 삽입된 경우 true
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return false;
	        }
	    }

}