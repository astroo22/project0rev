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

		System.out.println("Are you a customer, employee or manager?");
		input = scan.nextLine();
		if (input != null) {
			switch (input.toLowerCase()) {
			case "customer":
				System.out.println("Would you like to (login) or (create) a new account?");
				input = scan.nextLine();
				switch (input.toLowerCase()) {
				case "login":
					l = uServ.grabLogin();
					try {
						u = uServ.signIn(l.getUsername(), l.getPassword());
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
					u = uServ.grabInfo();
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
				l = uServ.grabLogin();
				try {
					u = uServ.signIn(l.getUsername(), l.getPassword());
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
				l = uServ.grabLogin();
				try {
					u = uServ.signIn(l.getUsername(), l.getPassword());
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
				System.out.println("What would you like to do?");
				System.out.println("- Request a (new) bank account?");
				System.out.println("- (view) balance.");
				System.out.println("- (manage) funds.");
				System.out.println("- (transfer) funds.");
				System.out.println("- (accept) money.");
				//Scanner scan2 = new Scanner(System.in);
				//String test = scan2.next();
				//System.out.println(test);
				//scan.nextLine();
				do {
					
						input = scan.nextLine();
						System.out.println(input);
			
						switch (input.toLowerCase()) {
						case "new":
							// account
							System.out.println("hit1");
							try {
								System.out.println("How much money would you like to deposit to start your account?");
								int balance = scan.nextInt();
								Account a = new Account(balance, u.getAccKey());
								uServ.addAccount(u, a);
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
								if (u.getAccounts() != null) {
									List<Account> accounts = u.getAccounts();
									accounts.toString();
								} else {
									System.out.println("No accounts found for this user account!");
									System.out
											.println("Please logout and try another account or create a new account!");
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
									System.out.println("Which acc # would you like to deposit in?");
									List<Account> accounts = u.getAccounts();
									for (Account a : accounts) {
										System.out.println(
												"Account ID: (" + a.getAccId() + ") Balance: " + a.getBalance());
									}
									int accountID = scan.nextInt();
									Account a = aDao.getAccountbyId(accountID);
									System.out.println("How much do u want to deposit?");
									double depositAmount = scan.nextDouble();
									a.setBalance((a.getBalance() + depositAmount));
									aDao.depositUserAmount(a);

								} catch (Exception e) {
									System.out.println("COME ON HIT ME!!! BET YOU CANT!");
									e.printStackTrace();
								}
							}

							/*
							 * break; case "transfer": break; case "accept": break; default:
							 * System.out.println("hit default 192"); break; }
							 */
							// request new bank account
							// view balance
							// withdrawal or deposit funds
							// check for negative funds
							// transfer funds
							// check if possible or if create negative
							// accept money from another account

						}

						if (employee) {
							while (loggedIn) {
								// i can approve or reject accounts
								// view customers bank acocunts
								// view all transactions

							}
						}
						if (manager) {
							while (loggedIn) {

							}
						}
					

				} while (input != "quit");
			}
		}
	}
}
