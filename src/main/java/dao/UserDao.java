package dao;
import models.Account;
import models.User;
import java.sql.SQLException;
import java.util.List;

public interface UserDao {
	
	List<User> getAllUsers();
	
	User getUserByUsername(String username);
	
	void createUser(User u) throws SQLException;
	
	void updateUser(User u);
	
	void deleteUser(User u);

	void addAccKey(Account a, User u) throws SQLException;
	
}
