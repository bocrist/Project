<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>



    <title>用户登录</title>
</head>
<body>

    <form action="login" method="post">
        <table>
            <tr>
                <td>用户名:</td>
                <td><input id="username" name="username" type="text"></td>
            </tr>
            <tr>
                <td>密码:</td>
                <td><input id="userpasswd" name="userpasswd" type="password"></td>
            </tr>
            <tr >
                <td><input type="submit" value="登录"  ></td>
                <td><input type="button" value="注册" onclick="window.location.href='regist'"> </td>
            </tr>

        </table>
    </form>
</body>
</html>