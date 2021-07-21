package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import models.Account;
import models.Transaction;
import models.User;
import utils.ConnectionUtil;

public class AccountDaoDB implements AccountDao {
	ConnectionUtil conUtil = ConnectionUtil.getConnectionUtil();
	@Override
	public List<Account> getAllaccount() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account getAccountbyId(int account_id) {
		// TODO Auto-generated method stub
		Account a = new Account();
		try {
			Connection con = conUtil.getConnection();
			String sql = "SELECT * FROM accounts WHERE account_key = " + account_id;
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(sql);
			while(rs.next()) {
				a.setAccId(rs.getInt(1));
				a.setAccId(rs.getInt(2));
				a.setBalance(rs.getDouble(3));
				a.setAccountNumber(rs.getInt(4));
			}
			return a;
		}catch(SQLException e) {
			System.out.println("SQL error");
			e.printStackTrace();
		}
		System.out.println("typed in accountid incorrectly couldn't find probably!");
		return null;
	}

	@Override
	public void depositUserAmount(Account a) {
		// TODO Auto-generated method stub
		try {
			Connection con = conUtil.getConnection();
			String sql = "UPDATE accounts SET balance = ? WHERE account_number = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			System.out.println(a.getBalance());
			ps.setDouble(1, a.getBalance());
			ps.setInt(2, a.getAccountNumber());
			ps.execute();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void withdrawUserAmount(Account a) {
		// TODO Auto-generated method stub
		try {
			Connection con = conUtil.getConnection();
			String sql = "UPDATE accounts SET balance = ? WHERE account_number =?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setDouble(1, a.getBalance());
			ps.setInt(2, a.getAccountNumber());
			ps.execute();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
	@Override
	public void recordTransaction(Account sender, Account reciever, double transferAmount) {
		// TODO Auto-generated method stub
		try {
			Connection con = conUtil.getConnection();
			String sql = "INSERT INTO transactions(sending_acc_id,recieving_acc_id,transfer_amount) values(?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, sender.getAccId());
			ps.setInt(2, reciever.getAccId());
			ps.setDouble(3, transferAmount);
			ps.execute();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void addAmount(Account a) {
		// TODO Auto-generated method stub
		try {
			Connection con = conUtil.getConnection();
			String sql = "INSERT INTO accounts(balance, account_number) values" + "(?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setDouble(1, a.getBalance());
			ps.setInt(2, a.getAccId());
			ps.execute();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public Account vewAccount(int account_id) {
		// TODO Auto-generated method stub
		Account acc = new Account();
		try {
			Connection con = conUtil.getConnection();
			String sql = "SELECT SUM(balance) FROM accounts where account_number =" + account_id;
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(sql);
			while(rs.next()) {
				acc.setBalance(rs.getInt(1));
				acc.setAccId(rs.getInt(2));
				}
			return acc;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void addAccount(Account a) {
		// TODO Auto-generated method stub
		try {
			Connection con = conUtil.getConnection();
			String sql = "INSERT INTO accounts(account_key, balance, account_number) values" + "(?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, a.getAccId());
			ps.setDouble(2, a.getBalance());
			ps.setInt(3, a.getAccountNumber());
			ps.execute();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}	
	}

	@Override
	public void viewAllTransactions() {
		// TODO Auto-generated method stub
		Transaction t = new Transaction();
		try {
			Connection con = conUtil.getConnection();
			String sql = "SELECT * FROM transactions";
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(sql);
			while(rs.next()) {
				t.setId(rs.getInt(1));
				t.setSendingAccId(rs.getInt(2));
				t.setRecievingAccId(rs.getInt(3));
				t.setTransferAmount(rs.getInt(4));
				System.out.println("Transaction ID: " + t.getId() + " Sending Acc: " + t.getSendingAccId() + " Recieving acc: " + t.getRecievingAccId() + " Transfer Amount: "+ t.getTransferAmount());
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void viewAllAccounts() {
		// TODO Auto-generated method stub
		Account a = new Account();
		try {
			Connection con = conUtil.getConnection();
			String sql = "SELECT * FROM Accounts";
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(sql);
			while(rs.next()) {
				a.setAccId(rs.getInt(2));
				a.setBalance(rs.getDouble(3));
				a.setAccountNumber(rs.getInt(4));
				System.out.println("Account ID: " + a.getAccId() + " Balance: " + a.getBalance() + "Account Number: " + a.getAccountNumber());
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	
}
