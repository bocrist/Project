package cn.wmyskxz.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.wmyskxz.dao.UserDao;
import cn.wmyskxz.entity.User;

@Service("userService")
public  class UserServiceImpl implements UserService{
	@Resource
	private UserDao userDao;



	

	@Override
	public User findname(String username) {
		// TODO Auto-generated method stub
		return userDao.findname(username);
	}


 

 

 
	
	
}

