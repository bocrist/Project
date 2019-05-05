package cn.wmyskxz.dao;

import cn.wmyskxz.entity.User;

public interface UserDao {
	User findname(String username);
	

	void addUser(User user);

}

