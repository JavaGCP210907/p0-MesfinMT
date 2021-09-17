package com.revature;


import java.sql.Connection;
import java.sql.SQLException;

import com.revature.models.Menu;
import com.revature.utils.ConnectionUtil;

//We're going to create a CLI (Command Line Interface) application that uses data from a PostgreSQL database
//The user will be able to enter inputs into a menu on the console, and interact with our data
public class Launcher {

	public static void main(String[] args) {
		
		//Here, we're just testing whether our Connection (from the ConnectionUtil Class) is successful
		//remember - the getConnection() method will return a Connection object if you reach the database successfully
		try(Connection conn = ConnectionUtil.getConnection()) {
//			System.out.println("Hello, connection was successful!!");
		} catch (SQLException e) {
			System.out.println("HEY connection failed :(");
			e.printStackTrace();
		}
		//THIS IS CALLED A "TRY WITH RESOURCES BLOCK"
		
		
		//Here is the actual functionality of our application----------------
		
		
		//Create our menu object
		Menu menu = new Menu();
		
		//Use the Menu's displayMenu() method to use the menu
		menu.displayMenu();
		
		//really clean main method, right? Power of abstraction ;)
		
		//all the complicated menu display logic is hidden in the Menu Class
		
	}

}
