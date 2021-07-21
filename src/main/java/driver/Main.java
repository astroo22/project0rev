package driver;

import java.io.*;
import java.io.IOException;
import java.util.*;

import dao.AccountDao;
import dao.AccountDaoDB;
import dao.UserDao;
import dao.UserDaoDB;
import models.Account;
import models.User;
import models.login;
import services.AccountServices;
import services.UserServices;

public class Main {
	private static UserDao uDao = new UserDaoDB();
	private static UserServices uServ = new UserServices(uDao);
	private static AccountDao aDao = new AccountDaoDB();
	private static AccountServices aServ = new AccountServices(aDao);

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Welcome to our super trustworthy bank!");
		System.out.println("where there aren't any bugs only features!!!");
		Scanner scan = new Scanner(System.in);
		String input = "";
		User u = null;
		login l = null;
		boolean loggedIn = false;
		boolean customer = false;
		boolean employee = false;
		boolean manager = false;

		// --vars for password username etc
		String username = "";
		String password = "";

		System.out.println("Are you a customer, employee or manager?");
		input = scan.nextLine();
		if (input != null) {
			switch (input.toLowerCase()) {
			case "customer":
				System.out.println("Would you like to (login) or (create) a new account?");
				input = scan.nextLine();
				switch (input.toLowerCase()) {
				case "login":

					// l = uServ.grabLogin();
					System.out.println("Please enter your username: ");
					username = scan.nextLine();
					System.out.println("Please enter a password: ");
					password = scan.nextLine();
					try {
						u = uServ.signIn(username, password);
						System.out.println("Welcome " + u.getFirstName());
						loggedIn = true;
						customer = true;
						break;
					} catch (Exception e) {
						e.printStackTrace();
						System.out.println("Username or password was incorect. Goodbye");
						// done = true;
					}
					break;
				case "create":
					// u = uServ.grabInfo();
					System.out.print("Please enter you first name: ");
					String first = scan.nextLine();
					System.out.println("Please enter your last name: ");
					String last = scan.nextLine();
					System.out.println("Please enter your email: ");
					String email = scan.nextLine();
					System.out.println("Please enter a password: ");
					password = scan.nextLine();
					u = new User(first, last, email, password);
					try {
						u = uServ.signUp(u.getFirstName(), u.getLastName(), u.getEmail(), u.getPassword());
						System.out.println("You may now log in with the username: " + u.getUsername());
						loggedIn = true;
						break;
					} catch (Exception e) {
						System.out.println("Sorry, we could not process your request");
						System.out.println("Please try again later");
						// done = true;
					}
					break;
				default:
					System.out.println("didn't recognize or scan error who knows?");
					System.out.println("input read: " + input.toLowerCase());
					break;
				}
				break;
			case "employee":
				System.out.println("Please Login");
				// <-- minimize this -->
				System.out.println("Please enter your username: ");
				username = scan.nextLine();
				System.out.println("Please enter a password: ");
				password = scan.nextLine();
				try {
					u = uServ.signIn(username, password);
					System.out.println("Welcome " + u.getFirstName());
					loggedIn = true;
					employee = true;
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("Username or password was incorect. Goodbye");
					// done = true;
				}
				// <------------------------->

				// employeeservices.login
				break;
			case "manager":
				System.out.println("Please Login");
				// managerservices.login
				System.out.println("Please enter your username: ");
				username = scan.nextLine();
				System.out.println("Please enter a password: ");
				password = scan.nextLine();
				try {
					u = uServ.signIn(username, password);
					System.out.println("Welcome " + u.getFirstName());
					loggedIn = true;
					manager = true;
				} catch (Exception e) {
					System.out.println("Username or password was incorect. Goodbye");
				}
				break;
			case "quit":
				System.exit(0);
				break;
			default:
				System.out.println("I couldn't understand that please try again");
				break;
			}
			// might jump down here once logged in will have to fix while loop
			System.out.println("we made it!");
			if (customer) {
				do {
					uServ.printMenuCustomer();
					input = scan.nextLine();
					// System.out.println(input);
					switch (input.toLowerCase()) {
					case "new":
						// account
						System.out.println("hit1");
						try {
							System.out.println("How much money would you like to deposit to start your account?");
							int balance = scan.nextInt();
							Account a = new Account(balance, u.getAccKey());
							// uServ.addAccount(u, a);
							/*
							 * fix all account issues use u.getAccKey make that a primary key also create
							 * the sql statement n other stuff also create the stuff for the aDao
							 * 
							 */
							aServ.addAccount(a);
						} catch (Exception e) {
							System.out.println("COME ON HIT ME!!! BET YOU CANT!ln141");
							e.printStackTrace();
						}

						break;
					case "view":

						try {
							if (aDao.getAccountbyId(u.getAccKey()) != null) {
								// List<Account> accounts = u.getAccounts();
								// accounts.toString();
								Account temp = aDao.getAccountbyId(u.getAccKey());
								System.out.println("AccId: " + temp.getAccId() + " Balance: " + temp.getBalance());

							} else {
								System.out.println("No accounts found for this user account!");
								System.out.println("Please logout and try another account or create a new account!");
							}
						} catch (Exception e) {
							System.out.println("COME ON HIT ME!!! BET YOU CANT!ln155");
							e.printStackTrace();
						}

						break;
					case "manage":
						System.out.println("Would you like to (deposit) or (withdraw) money?");
						input = scan.nextLine();
						switch (input.toLowerCase()) {
						case "deposit":
							try {
								System.out.println("What is the account ID you would like to deposit in?");
								int accountID = scan.nextInt();
								Account a = aDao.getAccountbyId(accountID);
								System.out.println("How much do u want to deposit?");
								double depositAmount = scan.nextDouble();
								System.out.println("AccId: " + a.getAccId() + " Balance: " + a.getBalance()
										+ " Accountnum" + a.getAccountNumber());
								double temp = a.getBalance() + depositAmount;
								a.setBalance(temp);
								aDao.depositUserAmount(a);

							} catch (Exception e) {
								System.out.println("COME ON HIT ME!!! BET YOU CANT!");
								e.printStackTrace();
							}
							break;
						case "withdraw":
							try {
								System.out.println("What is the account ID you would like to withdraw from?");
								int accountID = scan.nextInt();
								Account a = aDao.getAccountbyId(accountID);
								System.out.println("How much you want from ur account?");
								double withdrawAmount = scan.nextDouble();
								System.out.println("AccId: " + a.getAccId() + " Balance: " + a.getBalance()
										+ " Accountnum" + a.getAccountNumber());
								if (a.getBalance() >= withdrawAmount) {
									double temp = a.getBalance() - withdrawAmount;
									a.setBalance(temp);
									aDao.withdrawUserAmount(a);
								} else {
									System.out.println(
											"sorry but money must come from somewhere and u dont have enough :(");
									break;
								}
							} catch (Exception e) {
								System.out.println("shouldn't be hard to hit this one");
								e.printStackTrace();
							}
							break;
						}
					case "transfer":
						try {
							System.out.println("What is the account ID of the account you want to deposit into?");
							int recievingAccount = scan.nextInt();
							System.out.println("what is the account ID of the account you are transfering from?");
							int sendingAccount = scan.nextInt();
							System.out.println("How much we transfering?");
							double transferAmount = scan.nextDouble();
							Account recieve = aDao.getAccountbyId(recievingAccount);
							Account sender = aDao.getAccountbyId(sendingAccount);
							if (sender.getBalance() >= transferAmount) {
								recieve.setBalance((recieve.getBalance() + transferAmount));
								sender.setBalance((sender.getBalance() - transferAmount));
								aDao.withdrawUserAmount(sender);
								aDao.depositUserAmount(recieve);
								aDao.recordTransaction(sender, recieve, transferAmount);
							} else {
								System.out.println("homie u poor... money aint free try again");
							}
						} catch (Exception e) {
							System.out.println("COME ON HIT ME!!! transfer case");
							e.printStackTrace();
						}
						break;
					}
				} while (input != "quit");
			}
			if (employee) {
				do {
					// i can approve or reject accounts
					// view customers bank acocunts
					uServ.printMenuEmployee();
					input = scan.nextLine();
					// System.out.println(input);
					switch (input.toLowerCase()) {
					case "viewt":
						try {
							aDao.viewAllTransactions();
						} catch (Exception e) {
							System.out.println("Honestly no idea how u hit this...impressive");
							e.printStackTrace();
						}
						break;
					case "viewc":
						try {
							uDao.viewAllUsers();
						} catch (Exception e) {
							System.out.println("Honestly no idea how u hit this either...");
							e.printStackTrace();
						}
						break;
					case "viewa":
						try {
							aDao.viewAllAccounts();
						}catch(Exception e) {
							System.out.println("Honestly no idea how u hit this either...");
							e.printStackTrace();
						}
						break;
					case "approve":
						System.out.println("Would you like to approve the current accounts?(yes)/(no)");
						input = scan.nextLine();
						if(input.equals("yes")) {
							System.out.println("Approving outstanding unapproved accounts.");
						}
						
					break;
					}
					
					// view all transactions
				} while (input != "quit");
			}
			if (manager) {
				do {
					
				}while(input != "quit");
			}
		}
	}
}
