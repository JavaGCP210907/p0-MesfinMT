package com.revature.models;


import java.util.List;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.dao.AccountDao;
import com.revature.dao.AccountTypeDao;
import com.revature.dao.CustomerDao;
import com.revature.dao.TransactionDao;

//This Menu Class will have a method that displays a menu to the user that they can interact with
//Through this menu, the user can give inputs that will interact with the database
public class Menu {

	CustomerDao cDao = new CustomerDao(); //so we can use the CustomerDao methods
	AccountTypeDao atDao = new AccountTypeDao(); //so we can use the AccountTypeDao methods
	AccountDao aDao = new AccountDao(); //so we can use the AccountDao methods
	TransactionDao tDao = new TransactionDao(); //so we can use the TransactionDao methods
	Logger log = LogManager.getLogger(Menu.class); //Logger object so that we can implement Logging
	
	//All of the manu display options and control flow are contained within this method
	public void displayMenu() {
		
		boolean displayMenu = true; //we're going to use this to toggle whether the menu continues after user input
		Scanner scan = new Scanner(System.in); //Scanner object to parse user input
		
		//pretty greeting :)
		System.out.println("*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*");
		System.out.println("*  WELCOME TO BANKING OPERATIONS MANAGEMENT SYSTEM!  *");
		System.out.println("*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*");
		
		
		//display the menu as long as the displayMenu boolean is true
		while(displayMenu) {
			
			System.out.println("---------------------------");
			System.out.println("----MAIN MENU OPTIONS------");
			System.out.println("---------------------------");
			
			//menu options
			System.out.println("01 -> ACCOUNT TYPES RELATED OPERATIONS");
			System.out.println("02 -> CUSTOMER RELATED OPERATIONS");
			System.out.println("03 -> ACCOUNT RELATED OPERATIONS");
			System.out.println("04 -> TRANSACTION RELATED OPERATIONS");
			System.out.println("0 -> EXIT THE APPLICATION");
			
			//parse user input after they choose a main menu option, and put it in a String variable
			String input = scan.nextLine();
			
			switch(input) {
			
			case "01": {
				boolean displayMenu2 = true; 
				while(displayMenu2) {
					System.out.println("----------------------------------");
					System.out.println("----ACCOUNT TYPE MENU OPTIONS------");
					System.out.println("----------------------------------");
					System.out.println("05 -> LIST ALL ACCOUNT TYPES");
					System.out.println("06 -> ADD ACCOUNT TYPES");
					System.out.println("07 -> UPDATE INTEREST RATE");
					System.out.println("08 -> DELETE ACCOUNT TYPES");
					System.out.println("1 -> EXIT TO MAIN MENU");

				String inputc = scan.nextLine();
				
				switch(inputc) {
				
				case "05": {
					//get the List of AccountType from the DAO layer
					List<AccountType> accounttype = atDao.getAccountTypes();
					
					//enhanced for loop to print out the AccountType one by one
					for(AccountType actyp : accounttype) {
						System.out.println(actyp);
					}
					
	//				log.info("USER RETRIEVED LIST OF ALL EMPLOYEES");
					break; 
				}
				
				case "06": {
					System.out.println("Enter Account type");
					String type = scan.nextLine();
					
					System.out.println("Enter Interest Rate");
					double rate = scan.nextDouble();
					
					scan.nextLine();
					
					//create a new Employee based on these inputs
					AccountType actyp = new AccountType(type, rate);
					
					atDao.addAccountType(actyp);
					break;
				}
				
				case "07": {
					System.out.println("Enter Account Type ID to change");
					int id = scan.nextInt();
					
					System.out.println("Enter a new Rate for this Account Type");
					Double rateInput = scan.nextDouble();
					scan.nextLine();
					
					atDao.updateInterestRate(id, rateInput);
					break;
				}

				case "08": {
					System.out.println("Enter the id of the Account Type to delete");
					
					int id = scan.nextInt();
					scan.nextLine();
					
					atDao.removeAccountType(id);
					
					log.warn("USER DELETED ACCOUNT TYPE " + id);
					
					break;
				}

				case "0": {
					displayMenu2 = false; //this is how we break out of the while loop, ending the menu display
					System.out.println("EXITED TO MAIN MENU.");
					break;
				}
				
				//this default block will catch anything that doesn't match a menu option
				default: {
					System.out.println("THE INPUT SHOULD BE ONE OF THE FOLLOWING 05, 06, 07, 08 OR 0. TRY AGAIN.");
					break;
				}
				
				} //switch statement ends here
				}//2nd While

				break; //we need a break in each case, or else all the other cases will still run
			}
			
			case "02": {
				boolean displayMenu3 = true; 
				while(displayMenu3) {
					System.out.println("----------------------------------");
					System.out.println("-------CUSTOMER MENU OPTIONS------");
					System.out.println("----------------------------------");
					System.out.println("09 -> LIST ALL CUSTOMERS");
					System.out.println("10 -> LIST CUSTOMERS BY ID");
					System.out.println("11 -> LIST CUSTOMERS BY NAME");
					System.out.println("12 -> ADD CUSTOMER");
					System.out.println("13 -> UPDATE CUSTOMER  ADDRESS");
					System.out.println("14 -> DELETE CUSTOMER BY ID");
					System.out.println("1 -> EXIT TO MAIN MENU");

				String input3 = scan.nextLine();
				
				switch(input3) {
				
				case "09": {
					List<Customer> customer = cDao.getCustomer();
					
					for(Customer cus : customer) {
						System.out.println(cus);
					}
					
	//				log.info("USER RETRIEVED LIST OF ALL EMPLOYEES");
					break; 
				}
				
			
				case "10": {
					System.out.println("Enter customer id to search for?");
					int idInput = scan.nextInt(); 
					scan.nextLine(); 
					List<Customer> customers = cDao.getCustomerById(idInput);
					
					for(Customer cus : customers) {
						System.out.println(cus);
					}
					
					break;
				}
						
				case "11": {
					System.out.println("Enter customer name to search for?");
					String name = scan.nextLine(); 
					List<Customer> customers = cDao.getCustomerByName(name);
					
					for(Customer cus : customers) {
						System.out.println(cus);
					}
					
					break;
				}
				case "12": {
					System.out.println("Enter Customer Name");
					String name = scan.nextLine();
					
					System.out.println("Enter Address");
					String address = scan.nextLine();
					System.out.println("Enter City");
					String city = scan.nextLine();
					System.out.println("Enter State");
					String state = scan.nextLine();
					System.out.println("Enter Zipcode");
					int zipcode = scan.nextInt();
					
					scan.nextLine();
					
					//create a new Employee based on these inputs
					Customer cus = new Customer(name, address,city,state,zipcode);
					
					cDao.addCustomer(cus);
					break;
				}
				case "13": {
					System.out.println("Enter Customer ID to change");
					int id = scan.nextInt();
					scan.nextLine();
					
					System.out.println("Enter the new customer Address");
					String address = scan.nextLine();
					
					cDao.updateCustomer(id,address);
					break;
				}
				case "14": {
					System.out.println("Enter the id of the Customer to delete");
					
					int id = scan.nextInt();
					scan.nextLine();
					
					cDao.removeCustomer(id);
					
					log.warn("USER DELETED CUSTOMER ID: " + id);
					
					break;
				}

				case "1": {
					displayMenu3 = false; //this is how we break out of the while loop, ending the menu display
					System.out.println("EXITED TO MAIN MENU.");
					break;
				}
				
				//this default block will catch anything that doesn't match a menu option
				default: {
					System.out.println("THE INPUT SHOULD BE ONE OF THE FOLLOWING 09, 10, 11, 12, 13, 14 OR 1. TRY AGAIN.");
					break;
				}
				
				} //switch statement ends here
				}//2nd While

				break; //we need a break in each case, or else all the other cases will still run
			}
						
			case "03": {
				boolean displayMenu3 = true; 
				while(displayMenu3) {
					System.out.println("----------------------------------");
					System.out.println("-------ACCOUNTS MENU OPTIONS------");
					System.out.println("----------------------------------");
					System.out.println("15 -> LIST ALL ACCOUNTS");
					System.out.println("16 -> LIST ACCOUNT BY ID");
					System.out.println("17 -> LIST ACCOUNTS BY NAME");
					System.out.println("18 -> ADD ACCOUNT");
					System.out.println("19 -> UPDATE ACCOUNT OWNER'S NAME");
					System.out.println("20 -> DELETE ACCOUNT BY ID");
					System.out.println("1 -> EXIT TO MAIN MENU");

				String input3 = scan.nextLine();
				
				switch(input3) {
				
				case "15": {
					System.out.println("08");
					break; //we need a break in each case, or else all the other cases will still run
				}
				
				case "16": {
					System.out.println("09");
					break;
				}
				
				case "17": {
					System.out.println("10");
					break;
				}
				case "18": {
					System.out.println("11");
					break;
				}
				case "19": {
					System.out.println("12");
					break;
				}
				case "20": {
					System.out.println("13");
					break;
				}

				case "1": {
					displayMenu3 = false; //this is how we break out of the while loop, ending the menu display
					System.out.println("EXITED TO MAIN MENU.");
					break;
				}
				
				//this default block will catch anything that doesn't match a menu option
				default: {
					System.out.println("THE INPUT SHOULD BE ONE OF THE FOLLOWING 15, 16, 17, 18, 19, 20 OR 1. TRY AGAIN.");
					break;
				}
				
				} //switch statement ends here
				}//2nd While

				break; //we need a break in each case, or else all the other cases will still run
			}
			case "04": {
				boolean displayMenu3 = true; 
				while(displayMenu3) {
					System.out.println("----------------------------------");
					System.out.println("-------TRANSACTION MENU OPTIONS------");
					System.out.println("----------------------------------");
					System.out.println("21 -> LIST ALL TRANSACTION");
					System.out.println("22 -> LIST TRANSACTIONS BY TRANSACTIONID");
					System.out.println("23 -> LIST TRANSACTION BY ACCOUNTID");
					System.out.println("24 -> POST TRANSACTION");
					System.out.println("25 -> UPDATE TRANSACTION REFERENCE");
					System.out.println("26 -> DELETE TRANSACTION");
					System.out.println("1 -> EXIT TO MAIN MENU");

				String input3 = scan.nextLine();
				
				switch(input3) {
				
				case "21": {
					System.out.println("08");
					break; //we need a break in each case, or else all the other cases will still run
				}
				
				case "22": {
					System.out.println("09");
					break;
				}
				
				case "23": {
					System.out.println("10");
					break;
				}
				case "24": {
					System.out.println("11");
					break;
				}
				case "25": {
					System.out.println("12");
					break;
				}
				case "26": {
					System.out.println("13");
					break;
				}

				case "1": {
					displayMenu3 = false; //this is how we break out of the while loop, ending the menu display
					System.out.println("EXITED TO MAIN MENU.");
					break;
				}
				
				//this default block will catch anything that doesn't match a menu option
				default: {
					System.out.println("THE INPUT SHOULD BE ONE OF THE FOLLOWING 21, 22, 23, 24, 25, 26 OR 1. TRY AGAIN.");
					break;
				}
				
				} //switch statement ends here
				}//2nd While

				break; //we need a break in each case, or else all the other cases will still run
			}

			case "0": {
				displayMenu = false; //this is how we break out of the while loop, ending the menu display
				System.out.println("see ya! come again soon.");
				break;
			}
			
			//this default block will catch anything that doesn't match a menu option
			default: {
				System.out.println("THE INPUT SHOULD BE ONE OF THE FOLLOWING 01, 02, 03, 04 OR 0. TRY AGAIN.");
				break;
			}
			
			} //switch statement ends here
			
		} //while loop ends here
		
		System.out.println("Thank you for using the Banking Application System");
		scan.close(); //closes the Scanner, good for memory saving (imagine we have 50000000 open Scanner objects)
		
	}
	
}
