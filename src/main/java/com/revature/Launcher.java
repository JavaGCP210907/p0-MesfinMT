package com.revature;


import java.sql.Connection;
import java.sql.SQLException;

import com.revature.models.Menu;
import com.revature.utils.ConnectionUtil;

public class Launcher {

	public static void main(String[] args) {
		
		try(Connection conn = ConnectionUtil.getConnection()) {
		} catch (SQLException e) {
			System.out.println("HEY connection failed :(");
			e.printStackTrace();
		}

		Menu menu = new Menu();
		
		menu.displayMenu();
	}

}
