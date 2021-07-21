package services;

import java.sql.SQLException;
import java.util.Scanner;

import dao.UserDao;
import dao.UserDaoDB;
import exceptions.InvalidCredentialsException;
import exceptions.UserDoesNotExistException;
import exceptions.UserNameAlreadyExistsException;
import models.Account;
import models.User;
import models.login;

public class UserServices {
	//employee login
	//customer login 
	//register for customer acc
	private UserDao uDao;
	
	public UserServices(UserDao u) {
		this.uDao = u;
		
	}
	public User grabInfo() {
		Scanner scan = new Scanner(System.in);
		System.out.print("Please enter you first name: ");
		String first = scan.nextLine();
		System.out.println("Please enter your last name: ");
		String last = scan.nextLine();
		System.out.println("Please enter your email: ");
		String email = scan.nextLine();
		System.out.println("Please enter a password: ");
		String password = scan.nextLine();
		User u = new User(first, last, email, password);
		scan.close();
		return u;
		
	}
	public void addAccount(User u, Account a) {
		u.getAccounts().add(a);
		a.setAccId(u.getAccKey());
	}
	public login grabLogin() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Please enter your username: ");
		String username = scan.nextLine();
		System.out.println("Please enter a password: ");
		String password = scan.nextLine();
		login u = new login(username,password);
		scan.close();
		return u;
	}
	
	public User signUp(String first, String last, String email, String password) throws UserNameAlreadyExistsException{
		User u = new User(first, last, email, password);
		try {
			uDao.createUser(u);
			//Logging.logger.info("New user has register");
			
		}catch(SQLException e) {
			e.printStackTrace();
			throw new UserNameAlreadyExistsException();
		}
		return u;
	//maybe manager login
	}
	public User signIn(String username, String password) throws UserDoesNotExistException, InvalidCredentialsException{
		User u = uDao.getUserByUsername(username);
		System.out.println(u.getFirstName());
		if(u.getFirstName() == null) {
			//Logging.logger.warn("User tried loggging in that does not exist");
			throw new InvalidCredentialsException();
		}
		else {
			//Logging.logger.info("User was logged in");
			return u;
		}
	}
}
