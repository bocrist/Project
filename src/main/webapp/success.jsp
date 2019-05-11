<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>


    <title>用户登录</title>
</head>
<body>

<div class="addDIV">

    <div class="panel panel-success">
            <div class="panel-heading">
            <h3 class="panel-title"> <strong> welcome,${sessionScope.user.username}! </strong></h3>
        </div>
        <div class="panel-body">
        
    this is success page!

    <a href="listStudent">点我跳到另一个页面</a>

    <form action="outLogin">
        <table>
            <tr>
                <td><input type="submit" value="退出登录"></td>
            </tr>
        </table>
    </form>
      </div>
    </div>
    </div>
</body>
</html>