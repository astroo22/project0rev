package dao;

import java.util.List;

import models.Account;

public interface AccountDao {
	public List<Account> getAllaccount();
	public Account getAccountbyId(int account_id);
	public void depositUserAmount(Account e);
	public void withdrawUserAmount(Account u);
	public void addAmount(Account add);
	public Account vewAccount(int account_id);
	public void addAccount(Account a);
}
