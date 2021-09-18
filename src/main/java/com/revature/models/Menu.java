package com.revature.models;


import java.util.List;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.dao.AccountDao;
import com.revature.dao.AccountTypeDao;
import com.revature.dao.CustomerDao;
import com.revature.dao.TransactionDao;

public class Menu {

	CustomerDao cDao = new CustomerDao(); //so we can use the CustomerDao methods
	AccountTypeDao atDao = new AccountTypeDao(); //so we can use the AccountTypeDao methods
	AccountDao aDao = new AccountDao(); //so we can use the AccountDao methods
	TransactionDao tDao = new TransactionDao(); //so we can use the TransactionDao methods
	Logger log = LogManager.getLogger(Menu.class); //Logger object so that we can implement Logging
	
	public void displayMenu() {
		
		boolean displayMenu = true; 
		Scanner scan = new Scanner(System.in);
		
		System.out.println("*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*");
		System.out.println("*  WELCOME TO BANKING OPERATIONS MANAGEMENT SYSTEM!  *");
		System.out.println("*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*");
		
		
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
					System.out.println("0 -> EXIT TO MAIN MENU");

				String inputc = scan.nextLine();
				
				switch(inputc) {
				
				case "05": {

					List<AccountType> accounttype = atDao.getAccountTypes();

					for(AccountType actyp : accounttype) {
						System.out.println(actyp);
					}
					
					break; 
				}
				
				case "06": {
					System.out.println("Enter Account type");
					String type = scan.nextLine();
					
					System.out.println("Enter Interest Rate");
					double rate = scan.nextDouble();
					scan.nextLine();
					
					AccountType actyp = new AccountType(type, rate);
					
					atDao.addAccountType(actyp);
					log.info("USER ADDED NEW ACCOUNT TYPE: "+type);
					break;
				}
				
				case "07": {
					System.out.println("Enter Account Type ID to change");
					int id = scan.nextInt();
					
					System.out.println("Enter a new Rate for this Account Type");
					Double rateInput = scan.nextDouble();
					scan.nextLine();
					
					atDao.updateInterestRate(id, rateInput);
					log.info("USER UPDATED THE INTEREST RATE OF ACCOUNT TYPE: "+id+" TO "+rateInput);
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
					displayMenu2 = false; 
					System.out.println("EXITED TO MAIN MENU.");
					break;
				}
				
				default: {
					System.out.println("THE INPUT SHOULD BE ONE OF THE FOLLOWING 05, 06, 07, 08 OR 0. TRY AGAIN.");
					break;
				}
				
				} //End Nested switch statement
				} //End nested while

				break;
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
					System.out.println("13 -> UPDATE CUSTOMER ADDRESS");
					System.out.println("14 -> DELETE CUSTOMER BY ID");
					System.out.println("0 -> EXIT TO MAIN MENU");

				String input3 = scan.nextLine();
				
				switch(input3) {
				
				case "09": {
					List<Customer> customer = cDao.getCustomer();
					
					for(Customer cus : customer) {
						System.out.println(cus);
					}
					
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
					
					Customer cus = new Customer(name, address,city,state,zipcode);
					
					cDao.addCustomer(cus);
					log.warn("USER ADDED CUSTOMER WITH NAME: " + name);
					break;
				}
				case "13": {
					System.out.println("Enter Customer ID to change");
					int id = scan.nextInt();
					scan.nextLine();
					
					System.out.println("Enter the new customer Address");
					String address = scan.nextLine();
					
					cDao.updateCustomer(id,address);
					log.warn("USER UPDATED CUSTOMER ADDRESS TO : " + address+ " FOR CUSTOMER ID: "+id);
					break;
				}
				case "14": {
					System.out.println("Enter the id of the Customer to be delete");
					
					int id = scan.nextInt();
					scan.nextLine();
					
					cDao.removeCustomer(id);
					
					log.warn("USER DELETED CUSTOMER ID: " + id);
					
					break;
				}

				case "0": {
					displayMenu3 = false; 
					System.out.println("EXITED TO MAIN MENU.");
					break;
				}
				
				default: {
					System.out.println("THE INPUT SHOULD BE ONE OF THE FOLLOWING 09, 10, 11, 12, 13, 14 OR 1. TRY AGAIN.");
					break;
				}
				
				} //switch statement ends here
				}//2nd While

				break; 
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
					System.out.println("19 -> UPDATE ACCOUNT TYPE");
					System.out.println("20 -> DELETE ACCOUNT BY ID");
					System.out.println("0 -> EXIT TO MAIN MENU");

				String input3 = scan.nextLine();
				
				switch(input3) {
				
				case "15": {
					List<Account> account = aDao.getAccount();
					
					for(Account acc : account) {
						System.out.println(acc);
					}
					
					break; 
				}
				
				case "16": {
					System.out.println("Enter account id to search for?");
					int idInput = scan.nextInt(); 
					scan.nextLine(); 
					List<Account> accounts = aDao.getAccountById(idInput);
					
					for(Account acc : accounts) {
						System.out.println(acc);
					}
					
					break;
				}
				
				case "17": {
					System.out.println("Enter account name to search for?");
					String name = scan.nextLine(); 
					List<Account> accounts = aDao.getAccountByName(name);
					
					for(Account acc : accounts) {
						System.out.println(acc);
					}
					
					break;
				}
				case "18": {

					System.out.println("Enter Account Type ID");
					int accid = scan.nextInt();
					
					System.out.println("Enter Customer ID");
					int cusid = scan.nextInt();
					
					scan.nextLine();
					
					Account acc = new Account(accid,cusid);
					
					aDao.addAccount(acc);
					log.warn("USER ADDED ACCOUNT ID: " + accid);
					break;
				}
				case "19": {
					System.out.println("Enter Account ID to change");
					int accountid = scan.nextInt();
					scan.nextLine();
					
					System.out.println("Enter the new Account Type ID");
					int accounttypeid = scan.nextInt();
					scan.nextLine();
					
					aDao.updateAccount(accountid,accounttypeid);
					log.warn("USER UPDATED ACCOUNT TYPE ID TO : " + accounttypeid+ " FOR ACCOUNT ID: "+accountid);
					break;
				}
				case "20": {

					System.out.println("Enter the the account ID to be delete");
					
					int id = scan.nextInt();
					scan.nextLine();
					
					aDao.removeAccount(id);
					
					log.warn("USER DELETED ACCOUNT ID: " + id);
					
					break;
				}

				case "0": {
					displayMenu3 = false; 
					System.out.println("EXITED TO MAIN MENU.");
					break;
				}
				
				default: {
					System.out.println("THE INPUT SHOULD BE ONE OF THE FOLLOWING 15, 16, 17, 18, 19, 20 OR 1. TRY AGAIN.");
					break;
				}
				
				} //switch statement ends here
				}//2nd While

				break; 
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
					System.out.println("0 -> EXIT TO MAIN MENU");

				String input3 = scan.nextLine();
				
				switch(input3) {
				
				case "21": {

					List<Transaction> transaction = tDao.getTransaction();
					
					for(Transaction trn : transaction) {
						System.out.println(trn);
					}
					
					break; 
				}
				
				case "22": {

					System.out.println("Enter transaction id to search for?");
					int idInput = scan.nextInt(); 
					scan.nextLine(); 
					List<Transaction> transaction = tDao.getTransactionByTransactionId(idInput);
					
					for(Transaction trn : transaction) {
						System.out.println(trn);
					}
					
					break;
				}

					
				case "23": {

					System.out.println("Enter account id to search for transactions?");
					int idInput = scan.nextInt(); 
					scan.nextLine(); 
					List<Transaction> transaction = tDao.getTransactionByAccountId(idInput);
					
					for(Transaction trn : transaction) {
						System.out.println(trn);
					}
					
					break;
				}

				case "24": {

					System.out.println("Enter Account ID");
					int accid = scan.nextInt();
					
					System.out.println("Enter the amount");
					double amount = scan.nextDouble();
					
					System.out.println("Enter the Description/Reference");
					String desc = scan.nextLine();
					scan.nextLine();
					
					Transaction trn = new Transaction(amount,desc,accid);
					
					tDao.addTransaction(trn);
					log.warn("USER POSTED A TRANSACTION ON ACCOUNT ID: " + accid);
					break;
				}

				case "25": {

					System.out.println("Enter Transaction ID to change");
					int id = scan.nextInt();
					scan.nextLine();
					
					System.out.println("Enter the new Transaction Reference");
					String desc = scan.nextLine();
					
					tDao.updateTransaction(id,desc);
					log.warn("USER UPDATED TRANSACTION REFERENCE TO : " + desc+ " FOR transaction ID: "+id);
					break;
				}

				case "26": {

					System.out.println("Enter the the transaction ID to be delete");
					
					int id = scan.nextInt();
					scan.nextLine();
					
					tDao.removeTransaction(id);
					
					log.warn("USER DELETED TRANSACTION ID: " + id);
					
					break;
				}


					
				case "0": {
					displayMenu3 = false; 
					System.out.println("EXITED TO MAIN MENU.");
					break;
				}
				
				default: {
					System.out.println("THE INPUT SHOULD BE ONE OF THE FOLLOWING 21, 22, 23, 24, 25, 26 OR 1. TRY AGAIN.");
					break;
				}
				
				} //switch statement ends here
				}//2nd While

				break; 
			}

			case "0": {
				displayMenu = false;
				System.out.println("see ya! come again soon.");
				break;
			}
			
			default: {
				System.out.println("THE INPUT SHOULD BE ONE OF THE FOLLOWING 01, 02, 03, 04 OR 0. TRY AGAIN.");
				break;
			}
			
			} //switch statement ends here
			
		} //while loop ends here
		
		System.out.println("Thank you for using the Banking Application System");
		scan.close(); 
		
	}
	
}
