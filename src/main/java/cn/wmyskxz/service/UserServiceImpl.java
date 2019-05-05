package cn.wmyskxz.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.wmyskxz.dao.UserDao;
import cn.wmyskxz.entity.Student;
import cn.wmyskxz.entity.User;
import cn.wmyskxz.service.UserService;

@Service("userService")
public  class UserServiceImpl implements UserService{
	@Resource
	private UserDao userDao;



	

	@Override
	public User findname(String username) {
		// TODO Auto-generated method stub
		return userDao.findname(username);
	}


	
	    public void addUser(User user) {
	        userDao.addUser(user);
	    }

 

 
	
	
}

