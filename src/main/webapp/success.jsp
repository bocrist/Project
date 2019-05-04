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

<div class="addDIV">

    <div class="panel panel-success">
            <div class="panel-heading">
            <h3 class="panel-title"> <strong> welcome,${sessionScope.user.username}! </strong></h3>
        </div>
        <div class="panel-body">
        
    this is success page!

    <a href="anotherpage">点我跳到另一个页面</a>

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