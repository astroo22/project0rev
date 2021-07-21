package models;


import java.util.*;
public class User {
	//things we care about
	private int id;
	private String email;
	private String firstName;
	private String lastName;
	private String username;
	private String password;
	private boolean employee;
	private boolean manager;
	private List<Account> accounts;
	private int checkingAccount;
	private int accKey;
	
	public User() {
		super();
	}
	public User(int id, String firstName, String lastName, String email, String password) {
		this.id = id;
		this.accKey = new Random().nextInt(9999999) + 1000000;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = firstName + lastName + (new Random().nextInt(9000) + 1000);
		this.email = email;
		this.password = password;
		this.employee = false;
		this.manager = false;
		this.accounts = new ArrayList<Account>();
		this.checkingAccount = (new Random().nextInt(900)+1000);
		
	}
	public User(String firstName, String lastName, String email, String password) {
		this.id = (new Random().nextInt(9000) + 1000);
		this.accKey = new Random().nextInt(9999999) + 1000000;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = firstName + lastName + (new Random().nextInt(9000) + 1000);
		this.email = email;
		this.password = password;
		this.employee = false;
		this.manager = false;
		this.accounts = new ArrayList<Account>();
	}
	public User(int id, String firstName, String lastName, String username, String email, String password) {
		this.id = id;
		this.accKey = new Random().nextInt(9999999) + 1000000;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.email = email;
		this.password = password;
		this.employee = false;
		this.manager = false;
		this.accounts = new ArrayList<Account>();
	}

	public int getAccKey() {
		return accKey;
	}
	public void setAccKey(int accKey) {
		this.accKey = accKey;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public List<Account> getAccounts() {
		return accounts;
	}
	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}
	public int getCheckingAccount() {
		return checkingAccount;
	}
	public void setCheckingAccount(int checkingAccount) {
		this.checkingAccount = checkingAccount;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}