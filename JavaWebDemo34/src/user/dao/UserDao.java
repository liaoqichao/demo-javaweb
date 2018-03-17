package user.dao;

import user.domain.User;

public interface UserDao {
	
	public abstract User findByUsername(String username);
	
	public abstract void addUser(User user);
}
