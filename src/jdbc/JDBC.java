package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class JDBC {
	public void saveUser(String email, String fname, String lname, String image) {
		System.out.println(email + " " + fname + " " + lname + " " + image);
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/calendarUser?user=root&password=12345678Abc&useSSL=false");
			ps = conn.prepareStatement("INSERT INTO user (email, fname, lname, image) Values (?,?,?,?)" );
			ps.setString(1, email);
			ps.setString(2, fname);
			ps.setString(3, lname);
			ps.setString(4, image);
			System.out.println(ps);
			ps.executeUpdate();
		} catch (SQLException sqle) {
		System.out.println (sqle.getMessage());
		} catch (ClassNotFoundException cnfe) {
		System.out.println (cnfe.getMessage());
		} 
	}
	
	public void saveEvent(String title,String startDate,String startTime,String endDate,String endTime,String email,String id) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/calendarUser?user=root&password=12345678Abc&useSSL=false");
			ps = conn.prepareStatement("INSERT INTO event (title,startDate,startTime,endDate,endTime,email,eventID) Values (?,?,?,?,?,?,?)");
			ps.setString(1, title);
			ps.setString(2, startDate);
			ps.setString(3, startTime);
			ps.setString(4, endDate);
			ps.setString(5, endTime);
			ps.setString(6, email);
			ps.setString(7, id);
			System.out.println(ps);
			ps.executeUpdate();
			
		} catch (SQLException sqle) {
		System.out.println (sqle.getMessage());
		} catch (ClassNotFoundException cnfe) {
		System.out.println (cnfe.getMessage());
		} 
	}
	
	public ArrayList<String[]> getSearchResult(String search){
		ArrayList<String[]> searchResult = new ArrayList<String[]>();
		String[] words = search.split(" ");
		int count = words.length;
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/calendarUser?user=root&password=12345678Abc&useSSL=false");
			String sql = "SELECT * FROM user WHERE ";
			for(String word : words) {
				sql += "Lower(fname) LIKE '%" + word.toLowerCase() + "%' "; 
				sql += "OR Lower(lname) LIKE '%" + word.toLowerCase() + "%' OR ";
			}
			sql = sql.substring(0, sql.length() - 3);
			
			System.out.println(sql);
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				String[] resultRow = new String[4];
//				for (int i = 0; i < 4; i++) {
//					resultRow[i] = rs.getString(i+1);
//					System.out.print(resultRow[i] + " ");
//				}
				resultRow[0] = rs.getString("email");
				resultRow[1] = rs.getString("fname");
				resultRow[2] = rs.getString("lname");
				resultRow[3] = rs.getString("image");
				System.out.println();
				searchResult.add(resultRow);
			}
		}catch (SQLException sqle) {
		System.out.println (sqle.getMessage());
		} catch (ClassNotFoundException cnfe) {
		System.out.println (cnfe.getMessage());
		} 
		
		return searchResult;
		
	}
	
	public ArrayList<String[]> getEvent(String email){
		//email = "xubo.zhu888@gmail.com";
		ArrayList<String[]> eventOfThisUser = new ArrayList<String[]>();
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/calendarUser?user=root&password=12345678Abc&useSSL=false");
			String sql = "SELECT * FROM event WHERE email = '" + email + "'; ";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				String[] resultRow = new String[5];
				resultRow[0] = rs.getString("title");
				resultRow[1] = rs.getString("startDate");
				resultRow[2] = rs.getString("endDate");
				resultRow[3] = rs.getString("startTime");
				resultRow[4] = rs.getString("endTime");
				
				eventOfThisUser.add(resultRow);
			}
		
		}catch (SQLException sqle) {
		System.out.println (sqle.getMessage());
		} catch (ClassNotFoundException cnfe) {
		System.out.println (cnfe.getMessage());
		} 
		
		return eventOfThisUser;
			
	}
	
	public ArrayList<String[]> getProfile(String email){
		ArrayList<String[]> profileOfThisUser = new ArrayList<String[]>();
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/calendarUser?user=root&password=12345678Abc&useSSL=false");
			String sql = "SELECT * FROM user WHERE email = '" + email + "' ";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				String[] resultRow = new String[4];
				resultRow[0] = rs.getString("image");
				resultRow[1] = rs.getString("fname");
				resultRow[2] = rs.getString("lname");
				resultRow[3] = rs.getString("email");
				
				profileOfThisUser.add(resultRow);
			}
		
		}catch (SQLException sqle) {
		System.out.println (sqle.getMessage());
		} catch (ClassNotFoundException cnfe) {
		System.out.println (cnfe.getMessage());
		} 
		
		return profileOfThisUser;
			
	}
	
	public boolean checkIfFollow(String currentUserEmail, String otherEmail) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/calendarUser?user=root&password=12345678Abc&useSSL=false");
			ps = conn.prepareStatement("SELECT * FROM userFollow WHERE email='" +currentUserEmail + "' AND followingUserEmail = '" + otherEmail + "'");
			rs = ps.executeQuery();
			if(rs.next()){
				return true;
			} else {
				return false;
			}
		}catch (SQLException sqle) {
		System.out.println (sqle.getMessage());
		} catch (ClassNotFoundException cnfe) {
		System.out.println (cnfe.getMessage());
		} 
		
		return false;
	}
	
	public void changeFollow(String currentUserEmail, String otherEmail) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/calendarUser?user=root&password=12345678Abc&useSSL=false");
			ps = conn.prepareStatement("SELECT * FROM userFollow WHERE email='" +currentUserEmail + "' AND followingUserEmail = '" + otherEmail + "'");
			rs = ps.executeQuery();
			if (rs.next()) {
				ps = conn.prepareStatement("DELETE FROM userFollow WHERE email='" +currentUserEmail + "' AND followingUserEmail = '" + otherEmail + "'");
				rs = ps.executeQuery();
			}
			else {
				ps = conn.prepareStatement("INSERT INTO userFollow (email, followingUserEmail) VALUES (?,?)");
				ps.setString(1, currentUserEmail);
				ps.setString(2, otherEmail);
				ps.executeUpdate();
			}
		} catch (SQLException sqle) {
		System.out.println (sqle.getMessage());
		} catch (ClassNotFoundException cnfe) {
		System.out.println (cnfe.getMessage());
		} 
	}
	
	/*public void getUserInfo(String f_name, String l_name) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String email;
		String fname;
		String lname;
		String image;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/calendarUser?user=root&password=12345678Abc&useSSL=false");
			ps = conn.prepareStatement("SELECT * FROM user WHERE fname = '" + f_name +"' AND lname = '" + l_name);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				email = rs.getString("email");
				fname = rs.getString("fname");
				lname = rs.getString("lname");
				image = rs.getString("image");
			}
			
		}catch (SQLException sqle) {
		System.out.println (sqle.getMessage());
		} catch (ClassNotFoundException cnfe) {
		System.out.println (cnfe.getMessage());
		} 
	
	}*/
}
