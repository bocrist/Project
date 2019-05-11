<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>



    <title>用户登录</title>
</head>
<body>

    <div>
        <strong> ${sessionScope.user.username}!!!!! </strong>
    </div>
    <form action="outLogin">
        <table>
            <tr>
                <td><input type="submit" value="退出登录" ></td>
            </tr>
        </table>
    </form>
</body>
</html>