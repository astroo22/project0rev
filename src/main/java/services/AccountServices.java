package services;

import java.util.ArrayList;

import dao.AccountDao;
import models.Account;
import models.User;

public class AccountServices {
	private String name;
	private ArrayList<User> users;
	private ArrayList<Account> accounts;
	//private ArrayList<Employee> employees;
	private AccountDao aDao;
	
	public AccountServices(AccountDao aDao) {
		this.aDao = aDao;
	}
	
	public Account transferAmount(int accountId1, int accountId2, int amount) {
		try {
			Account sub = new Account(accountId1, amount);
			aDao.withdrawUserAmount(sub);
			Account add = new Account(accountId2, amount);
			aDao.depositUserAmount(add);
			System.out.println("Your $" + amount + "successfully transfered to account #:" + accountId2 );
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public Account viewAccount(int account_id) {
		Account a = aDao.getAccountbyId(account_id);
		return a;
	}
	public Account withdrawAmount(Account a, double withdrawAmount) {
		double temp = a.getBalance();
		if(temp >= withdrawAmount) {
			double temp2 = a.getBalance() - withdrawAmount;
			Account withdraw = new Account(a.getAccId(),temp2,a.getAccountNumber());
			aDao.withdrawUserAmount(withdraw);
			System.out.println("your balance has been deducted $" + withdrawAmount);
			return withdraw;
		}else {
			System.out.println("you are not able to withdraw.");
			return null;
		}
		
	}
	public Account addDesposit(Account a, double depositAmount) {
		double balance = a.getBalance() + depositAmount;
		Account add = new Account(a.getAccId(),balance,a.getAccountNumber());
		aDao.addAmount(add);
		System.out.println("Your amount $" + balance + " has been deposit");
		return add;

	}

	public void addAccount(Account a) {
		// TODO Auto-generated method stub
		System.out.println("adding account to db");
		aDao.addAccount(a);
		System.out.println("lit we made it fam account added");
		
	}
}
