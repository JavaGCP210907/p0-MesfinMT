package com.revature.models;

import java.util.List;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.dao.AccountDao;
import com.revature.dao.AccountTypeDao;
import com.revature.dao.CustomerDao;
import com.revature.dao.TransactionDao;

//The Menu class allow us to loop through the different menu of the banking application using While and nested while loop
//and switch and nested switch flow controls
public class Menu {

	CustomerDao cDao = new CustomerDao(); //so we can use the CustomerDao methods
	AccountTypeDao atDao = new AccountTypeDao(); //so we can use the AccountTypeDao methods
	AccountDao aDao = new AccountDao(); //so we can use the AccountDao methods
	TransactionDao tDao = new TransactionDao(); //so we can use the TransactionDao methods
	Logger log = LogManager.getLogger(Menu.class); //Logger object so that we can implement Logging

	//A method used to clear the console screen
	public void clearScreen() {
		for (int i=0;i<10;i++)
		{
		System.out.print((char)13);
		}
	}
	
	public void displayMenu() throws InterruptedException  {
		
		boolean displayMenu = true; 
		Scanner scan = new Scanner(System.in);
		Menu objectMenu=new Menu();
		objectMenu.clearScreen();
		
		System.out.println("*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*");
		System.out.println("*  WELCOME TO BANKING OPERATIONS MANAGEMENT SYSTEM!  *");
		System.out.println("*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*");
		
		
		while(displayMenu) {//Main while loops starts here
			
			System.out.println("---------------------------");
			System.out.println("----MAIN MENU OPTIONS------");
			System.out.println("---------------------------");
			
			//menu options
			System.out.println("01 -> ACCOUNT TYPES RELATED OPERATIONS");
			System.out.println("02 -> CUSTOMER RELATED OPERATIONS");
			System.out.println("03 -> ACCOUNT RELATED OPERATIONS");
			System.out.println("04 -> TRANSACTION RELATED OPERATIONS");
			System.out.println("0 -> EXIT THE APPLICATION");
			objectMenu.clearScreen();
			
			String input = scan.nextLine();
			
			switch(input) {//Main switch starts here
			
			case "01": {

				boolean displayMenu2 = true; 
				while(displayMenu2) {//Nested while loops starts here
					System.out.println("----------------------------------");
					System.out.println("----ACCOUNT TYPE MENU OPTIONS------");
					System.out.println("----------------------------------");
					System.out.println("05 -> LIST ALL ACCOUNT TYPES");
					System.out.println("06 -> ADD ACCOUNT TYPES");
					System.out.println("07 -> UPDATE INTEREST RATE");
					System.out.println("08 -> DELETE ACCOUNT TYPES");
					System.out.println("0 ->  BACK TO MAIN MENU");
					objectMenu.clearScreen();

				String inputc = scan.nextLine();
				
				switch(inputc) {//Nested switch starts here
				
				case "05": {

					List<AccountType> accounttype = atDao.getAccountTypes();

					for(AccountType actyp : accounttype) {
						System.out.println(actyp);
					}
					
					break; 
				}
				
				case "06": {
					objectMenu.clearScreen();
					System.out.println("Please enter Account type");
					String type = scan.nextLine();
					
					//Validate the Interest Rate input. It should be positive number.
					double rate;
				       do {
				            System.out.print("Please enter Interest Rate: ");
				            while (!scan.hasNextDouble()) {
				                String inputx = scan.next();
				                System.out.printf("\"%s\" is not a valid number.\n", inputx);
				            }
				            rate = scan.nextDouble();
				        } while (rate < 0);
					scan.nextLine();
					
					AccountType actyp = new AccountType(type, rate);
				
					atDao.addAccountType(actyp);
					break;
				}
				
				case "07": {
					objectMenu.clearScreen();
					int id;
				       do {
				            System.out.print("Please enter Account Type ID to change: ");
				            while (!scan.hasNextInt()) {
				                String inputx = scan.next();
				                System.out.printf("\"%s\" is not a valid number.\n", inputx);
				            }
				            id = scan.nextInt();
				        } while (id < 0);
					scan.nextLine();
					//Validate the Interest Rate input. It should be positive number.
					double rateInput;
				       do {
				            System.out.print("Please enter a new Rate for the Account Type: ");
				            while (!scan.hasNextDouble()) {
				                String inputx = scan.next();
				                System.out.printf("\"%s\" is not a valid number.\n", inputx);
				            }
				            rateInput = scan.nextDouble();
				        } while (rateInput < 0);
					scan.nextLine();
					atDao.updateInterestRate(id, rateInput);

					break;
				}

				case "08": {
					objectMenu.clearScreen();
					int id;
				       do {
				            System.out.print("Please enter the id of the Account Type to be deleted: ");
				            while (!scan.hasNextInt()) {
				                String inputx = scan.next();
				                System.out.printf("\"%s\" is not a valid number.\n", inputx);
				            }
				            id = scan.nextInt();
				        } while (id < 0);
					scan.nextLine();
					
					atDao.removeAccountType(id);
					
					
					break;
				}

				case "0": {
					objectMenu.clearScreen();

					displayMenu2 = false; 
					System.out.println("EXITED TO MAIN MENU.");
					break;
				}
				
				default: {
					System.out.println("THE INPUT SHOULD BE ONE OF THE FOLLOWING 05, 06, 07, 08 OR 0. TRY AGAIN.");
					break;
				}
				
				} //Nested switch ends here
				} //Nested while ends here

				break;
			}
			
			case "02": {
				objectMenu.clearScreen();

				boolean displayMenu2 = true; 
				while(displayMenu2) {//Nested while loops starts here
					System.out.println("----------------------------------");
					System.out.println("-------CUSTOMER MENU OPTIONS------");
					System.out.println("----------------------------------");
					System.out.println("09 -> LIST ALL CUSTOMERS");
					System.out.println("10 -> LIST CUSTOMERS BY ID");
					System.out.println("11 -> LIST CUSTOMERS BY NAME");
					System.out.println("12 -> ADD CUSTOMER");
					System.out.println("13 -> UPDATE CUSTOMER ADDRESS");
					System.out.println("14 -> DELETE CUSTOMER BY ID");
					System.out.println("0 ->  BACK TO MAIN MENU");
					objectMenu.clearScreen();

				String input3 = scan.nextLine();
				
				switch(input3) {//Nested switch starts here
				
				case "09": {
					objectMenu.clearScreen();
					List<Customer> customer = cDao.getCustomer();
					
					for(Customer cus : customer) {
						System.out.println(cus);
					}
					
					break; 
				}
				
			
				case "10": {
					objectMenu.clearScreen();
					int idInput;
				       do {
				            System.out.print("Please enter customer id to search for: ");
				            while (!scan.hasNextInt()) {
				                String inputx = scan.next();
				                System.out.printf("\"%s\" is not a valid number.\n", inputx);
				            }
				            idInput = scan.nextInt();
				        } while (idInput < 0);
						scan.nextLine();
					List<Customer> customers = cDao.getCustomerById(idInput);
					
					for(Customer cus : customers) {
						System.out.println(cus);
					}
					System.out.println("There is no customer with the specified ID"); 
				
					break;
				}
						
				case "11": {
					objectMenu.clearScreen();
					System.out.println("Enter customer name to search for?");
					String name = scan.nextLine(); 
					List<Customer> customers = cDao.getCustomerByName(name);
					
					for(Customer cus : customers) {
						System.out.println(cus);
					}
					
					break;
				}
				case "12": {
					objectMenu.clearScreen();
					System.out.println("Enter Customer Name");
					String name = scan.nextLine();
					
					System.out.println("Enter Address");
					String address = scan.nextLine();
					System.out.println("Enter City");
					String city = scan.nextLine();
					System.out.println("Enter State");
					String state = scan.nextLine();
					//Validate the zipcode input. It should be positive number.
					int zipcode;
				       do {
				            System.out.print("Please enter Zipcode to change: ");
				            while (!scan.hasNextInt()) {
				                String inputx = scan.next();
				                System.out.printf("\"%s\" is not a valid number.\n", inputx);
				            }
				            zipcode = scan.nextInt();
				        } while (zipcode < 0);
					
					Customer cus = new Customer(name, address,city,state,zipcode);
					
					cDao.addCustomer(cus);
					break;
				}
				case "13": {
					objectMenu.clearScreen();
					//Validate the customer number input. It should be positive number.
					int number;
				       do {
				            System.out.print("Please enter Customer ID to change: ");
				            while (!scan.hasNextInt()) {
				                String inputx = scan.next();
				                System.out.printf("\"%s\" is not a valid number.\n", inputx);
				            }
				            number = scan.nextInt();
				        } while (number < 0);
						scan.nextLine();
						
				     System.out.println("Please enter the new customer Address");
					String address = scan.nextLine();
					cDao.updateCustomer(number,address);
					break;
				}
				case "14": {
					objectMenu.clearScreen();
					//Validate the customer number input. It should be positive number.
					int number;
				       do {
				            System.out.print("Please enter Customer ID to delete: ");
				            while (!scan.hasNextInt()) {
				                String inputx = scan.next();
				                System.out.printf("\"%s\" is not a valid number.\n", inputx);
				            }
				            number = scan.nextInt();
				        } while (number < 0);
					scan.nextLine();
					
					cDao.removeCustomer(number);
					
					
					break;
				}

				case "0": {
					objectMenu.clearScreen();
					displayMenu2 = false; 
					System.out.println("EXITED TO MAIN MENU.");
					break;
				}
				
				default: {
					System.out.println("THE INPUT SHOULD BE ONE OF THE FOLLOWING 09, 10, 11, 12, 13, 14 OR 0. TRY AGAIN.");
					break;
				}
				
				} //Nested switch ends here
				} //Nested while ends here

				break; 
			}
						
			case "03": {
				objectMenu.clearScreen();

				boolean displayMenu2 = true; 
				while(displayMenu2) {
					System.out.println("----------------------------------");
					System.out.println("-------ACCOUNTS MENU OPTIONS------");
					System.out.println("----------------------------------");
					System.out.println("15 -> LIST ALL ACCOUNTS");
					System.out.println("16 -> LIST ACCOUNT BY ID");
					System.out.println("17 -> LIST ACCOUNTS BY NAME");
					System.out.println("18 -> ADD ACCOUNT");
					System.out.println("19 -> UPDATE ACCOUNT TYPE");
					System.out.println("20 -> DELETE ACCOUNT BY ID");
					System.out.println("0 ->  BACK TO MAIN MENU");
					objectMenu.clearScreen();

				String input3 = scan.nextLine();
				
				switch(input3) {
				
				case "15": {
					objectMenu.clearScreen();
					List<Account> account = aDao.getAccount();
					
					for(Account acc : account) {
						System.out.println(acc);
					}
					
					break; 
				}
				
				case "16": {
					objectMenu.clearScreen();
					int idInput;
				       do {
				            System.out.print("Please enter account id to search for: ");
				            while (!scan.hasNextInt()) {
				                String inputx = scan.next();
				                System.out.printf("\"%s\" is not a valid number.\n", inputx);
				            }
				            idInput = scan.nextInt();
				        } while (idInput < 0);
						scan.nextLine();
					List<Account> accounts = aDao.getAccountById(idInput);
					
					for(Account acc : accounts) {
						System.out.println(acc);
					}
					System.out.println("There is no account with the specified ID"); 
					
					break;
				}
				
				case "17": {
					objectMenu.clearScreen();
					System.out.println("Enter customer name to search for?");
					String name = scan.nextLine(); 
					List<Customer> customers = cDao.getCustomerByName(name);
					if (customers.isEmpty()) {
						System.out.println("An account with the name " + name +" not found.");
					} else {
					
					List<Account> accounts = aDao.getAccountByName(name);
					for(Account acc : accounts) {
						System.out.println(acc);
					}
					}
					break;
				}
				case "18": {
					objectMenu.clearScreen();

					int accid;
				       do {
				            System.out.print("Please enter Account TYPE ID: ");
				            while (!scan.hasNextInt()) {
				                String inputx = scan.next();
				                System.out.printf("\"%s\" is not a valid number.\n", inputx);
				            }
				            accid = scan.nextInt();
				        } while (accid < 0);
					scan.nextLine();
					
					int cusid;
				       do {
				            System.out.print("Please enter Customer ID: ");
				            while (!scan.hasNextInt()) {
				                String inputx = scan.next();
				                System.out.printf("\"%s\" is not a valid number.\n", inputx);
				            }
				            cusid = scan.nextInt();
				        } while (cusid < 0);
					scan.nextLine();
					
					Account acc = new Account(accid,cusid);
					
					aDao.addAccount(acc);
					break;
				}
				case "19": {
					objectMenu.clearScreen();
					int accountid;
				       do {
				            System.out.print("Please enter Account ID to change: ");
				            while (!scan.hasNextInt()) {
				                String inputx = scan.next();
				                System.out.printf("\"%s\" is not a valid number.\n", inputx);
				            }
				            accountid = scan.nextInt();
				        } while (accountid < 0);
					scan.nextLine();
					
					int accounttypeid;
				       do {
				            System.out.print("Please enter the new Account Type ID: ");
				            while (!scan.hasNextInt()) {
				                String inputx = scan.next();
				                System.out.printf("\"%s\" is not a valid number.\n", inputx);
				            }
				            accounttypeid = scan.nextInt();
				        } while (accounttypeid < 0);
						scan.nextLine();
					
					aDao.updateAccount(accountid,accounttypeid);
					break;
				}
				case "20": {
					objectMenu.clearScreen();

					int id;
				       do {
				            System.out.print("Please enter account ID to be delete: ");
				            while (!scan.hasNextInt()) {
				                String inputx = scan.next();
				                System.out.printf("\"%s\" is not a valid number.\n", inputx);
				            }
				            id = scan.nextInt();
				        } while (id < 0);
						scan.nextLine();
					
					aDao.removeAccount(id);
					
					break;
				}

				case "0": {
					objectMenu.clearScreen();

					displayMenu2 = false; 
					System.out.println("EXITED TO MAIN MENU.");
					break;
				}
				
				default: {
					System.out.println("THE INPUT SHOULD BE ONE OF THE FOLLOWING 15, 16, 17, 18, 19, 20 OR 0. TRY AGAIN.");
					break;
				}
				
				} //Nested switch ends here
				} //Nested while ends here

				break; 
			}
			case "04": {
				objectMenu.clearScreen();

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
					System.out.println("0 ->  BACK TO MAIN MENU");
					objectMenu.clearScreen();

				String input3 = scan.nextLine();
				
				switch(input3) {
				
				case "21": {
					objectMenu.clearScreen();

					List<Transaction> transaction = tDao.getTransaction();
					
					for(Transaction trn : transaction) {
						System.out.println(trn);
					}
					
					break; 
				}
				
				case "22": {
					objectMenu.clearScreen();
					int idInput;
				       do {
				            System.out.print("Please enter transaction id to search for: ");
				            while (!scan.hasNextInt()) {
				                String inputx = scan.next();
				                System.out.printf("\"%s\" is not a valid number.\n", inputx);
				            }
				            idInput = scan.nextInt();
				        } while (idInput < 0);
						scan.nextLine();
					List<Transaction> transaction = tDao.getTransactionByTransactionId(idInput);
					
					for(Transaction trn : transaction) {
						System.out.println(trn);
					}
					System.out.println("There is no transaction with the specified transaction ID"); 
					
					break;
				}

					
				case "23": {
					objectMenu.clearScreen();
					int idInput;
				       do {
				            System.out.print("Please enter account id to search for: ");
				            while (!scan.hasNextInt()) {
				                String inputx = scan.next();
				                System.out.printf("\"%s\" is not a valid number.\n", inputx);
				            }
				            idInput = scan.nextInt();
				        } while (idInput < 0);
						scan.nextLine();
					List<Transaction> transaction = tDao.getTransactionByAccountId(idInput);
					
					for(Transaction trn : transaction) {
						System.out.println(trn);
					}
					System.out.println("There is no transaction with the specified account ID"); 
					
					break;
				}

				case "24": {
					objectMenu.clearScreen();

					int accid;
				       do {
				            System.out.print("Please enter account ID: ");
				            while (!scan.hasNextInt()) {
				                String inputx = scan.next();
				                System.out.printf("\"%s\" is not a valid number.\n", inputx);
				            }
				            accid = scan.nextInt();
				        } while (accid<0);
						scan.nextLine();
					
					double amount;
				       do {
				            System.out.print("Please enter amount: ");
				            while (!scan.hasNextDouble()) {
				                String inputx = scan.next();
				                System.out.printf("\"%s\" is not a valid number.\n", inputx);
				            }
				            amount = scan.nextDouble();
				        } while (amount < 0);
					scan.nextLine();
					
					System.out.println("Enter the Description/Reference");
					String desc = scan.nextLine();
					
					Transaction trn = new Transaction(amount,desc,accid);
					
					tDao.addTransaction(trn);
					break;
				}

				case "25": {
					objectMenu.clearScreen();

					int id;
				       do {
				            System.out.print("Please enter Transaction ID to change: ");
				            while (!scan.hasNextInt()) {
				                String inputx = scan.next();
				                System.out.printf("\"%s\" is not a valid number.\n", inputx);
				            }
				            id = scan.nextInt();
				        } while (id<0);
						scan.nextLine();
					
					System.out.println("Enter the new Transaction Reference");
					String desc = scan.nextLine();
					
					tDao.updateTransaction(id,desc);
					break;
				}

				case "26": {
					objectMenu.clearScreen();

					int id;
				       do {
				            System.out.print("Please enter Transaction ID to delete: ");
				            while (!scan.hasNextInt()) {
				                String inputx = scan.next();
				                System.out.printf("\"%s\" is not a valid number.\n", inputx);
				            }
				            id = scan.nextInt();
				        } while (id<0);
					
					tDao.removeTransaction(id);
					
					
					break;
				}


					
				case "0": {
					objectMenu.clearScreen();

					displayMenu3 = false; 
					System.out.println("EXITED TO MAIN MENU.");
					break;
				}
				
				default: {
					System.out.println("THE INPUT SHOULD BE ONE OF THE FOLLOWING 21, 22, 23, 24, 25, 26 OR 0. TRY AGAIN.");
					break;
				}
				
				} //Nested switch ends here
				} //Nested while ends here

				break; 
			}

			case "0": {
				objectMenu.clearScreen();
				objectMenu.clearScreen();
				objectMenu.clearScreen();

				System.out.println("Y/N -> Are you sure you want to completely exit from the application. Press Y(for yes) or N(for no)");
				
				String inputd = scan.nextLine();
				
				switch(inputd) {//Nested switch starts here
				
				case "Y": {

				displayMenu = false;
				break;}
				case "N": {

				break;}
				
			}
			}
			
			} //switch statement ends here
			
		} //while loop ends here

		//Clear screen method - used to clear the screen
		for (int i=0;i<3;i++) {
		objectMenu.clearScreen();
		}
		System.out.println("Thank you for using the Banking Application System!");
		Thread.sleep(1000);
		for (int i=0;i<3;i++) {
		objectMenu.clearScreen();
		}
		scan.close(); 
		
	}
	
}
