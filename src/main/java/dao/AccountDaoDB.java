package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import models.Account;
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
			String sql = "SELECT * FROM accounts GROUP BY account_id HAVING account_id = 'account_id'";
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(sql);
			while(rs.next()) {
				a.setAccId(rs.getInt(1));
				a.setBalance(rs.getDouble(2));
				a.setAccountNumber(rs.getInt(3));
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
			String sql = "UPDATE accounts SET balance = balance+? WHERE account_number = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setDouble(1, a.getBalance());
			ps.setInt(2, a.getAccId());
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
			String sql = "UPDATE accounts SET balance = balance-? WHERE account_number =?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setDouble(1, a.getBalance());
			ps.setInt(2, a.getAccId());
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
			String sql = "INSERT INTO accounts(accId, balance, accountNumber) values" + "(?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, a.getAccId());
			ps.setDouble(2, a.getBalance());
			ps.setInt(3, a.getAccountNumber());
			ps.execute();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}	
	}
}
