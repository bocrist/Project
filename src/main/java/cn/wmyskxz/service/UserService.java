package cn.wmyskxz.service;



import cn.wmyskxz.entity.User;

public interface UserService {
	User findname(String username);
	  /**
	 增加一条数据
     * @Param user
     */
    void addUser(User user);
}

