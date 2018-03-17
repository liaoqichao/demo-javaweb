package update.dao;

import java.util.List;

import update.domain.User;

public interface UserDao {

	void save(User user);

	void delete(Integer id);

	void update(User user);

	User get(Integer id);

	List<User> list();
	
	User findByUsername(String username);

}