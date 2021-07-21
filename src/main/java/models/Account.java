package models;
import java.util.ArrayList;
import java.util.Random;

public class Account {
	//private User owner;
	private int accId;
	private double balance;
	private int accountNumber;
   // private ArrayList<Transaction> transactions;

    public Account() {
        //transactions = new ArrayList<Transaction>();

    }

   /* public Account(int accountId) {
        this.accId = accountId;
       // this.owner = owner;
        this.accountNumber = 100000000 + new Random().nextInt(900000000);
        //this.transactions = new ArrayList<Transaction>();

    }*/
    public Account(int accountId) {
    	this.accId = accountId;
    }
    public Account(int balance, int accountId) {
    	this.balance = balance;
    	this.accId = new Random().nextInt(9999999) + 1000000;
    	this.accountNumber = 100000000 + new Random().nextInt(900000000);
    	System.out.println("This is your accId for wire transfers so please write this down: " + accId);
    }
    public Account(int accountId, double balance, int accNum) {
    	this.accId = accountId;
    	this.balance = balance;
    	this.accountNumber = accNum;
    }
   
	public int getAccId() {
		return accId;
	}

	@Override
	public String toString() {
		return "Account [accId=" + accId + ", balance=" + balance + ", accountNumber=" + accountNumber + "]";
	}

	public void setAccId(int accId) {
		this.accId = accId;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}



}
