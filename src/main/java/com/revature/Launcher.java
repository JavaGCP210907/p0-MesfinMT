package com.revature;

import java.sql.Connection;
import java.sql.SQLException;
import com.revature.models.Menu;
import com.revature.utils.ConnectionUtil;

//The launcher class of the Banking application system. It starts the JDBC connection and called the displayMenu class
public class Launcher {

	public static void main(String[] args) throws Exception {
		
		try(Connection conn = ConnectionUtil.getConnection()) {
		} catch (SQLException e) {
			System.out.println("Database connection failed :(");
			e.printStackTrace();
		}

		Menu menu = new Menu();
		
		menu.displayMenu();
	}

}
