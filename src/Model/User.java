package Model;

import java.sql.Connection;

public class User {
	
	private static Connection connection = null;//connection
	
	private String email;
	private String fname;
	private String lname;
	private String image;
	public User( ) {
		
	}
	
	void save() {
		// insert into database
	}
	
}
