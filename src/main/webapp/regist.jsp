<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>

    <%-- 引入JQ和Bootstrap --%>
    <script src="js/jquery/2.0.0/jquery.min.js"></script>
    <link href="css/bootstrap/3.3.6/bootstrap.min.css" rel="stylesheet">
    <script src="js/bootstrap/3.3.6/bootstrap.min.js"></script>
    <link href="css/style.css" rel="stylesheet">

    <title>用户登录</title>
</head>
<body>
    <h3>注册页面</h3>
    <hr/>
    <form action="doRegist" method="post">
        <table>
            <tr height="35px">
                <td width="150px">用户名</td>
                <td width="300px">
                    <input type="text" name="username" id="username">
                </td>
            </tr>
            <tr height="35px">
                <td>密码</td>
                <td>
                    <input type="text" name="userpasswd" id="userpasswd">
                </td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <input type="submit" id="regist" value="注册"/>
                </td>
            </tr>
        </table>
    </form>
</body>
</html>