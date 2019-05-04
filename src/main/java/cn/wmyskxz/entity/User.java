package cn.wmyskxz.entity;

public class User {
    private Integer id;

    private String username;

    private String userpasswd;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return username;
    }

    public void setUserName(String username) {
        this.username = username;
    }

    public String getUserPasswd() {
        return userpasswd;
    }

    public void setUserPasswd(String userpasswd) {
        this.userpasswd = userpasswd;
    }

}