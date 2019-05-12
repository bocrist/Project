<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<%-- 引入JQ和Bootstrap --%>
    <link href="css/style.css" rel="stylesheet">

<html>
<head>


    <title>学生管理页面 - 首页</title>

    <script>
        $(function () {
            $("ul.pagination li.disabled a").click(function () {
                return false;
            });
        });
    </script>
</head>

<body>

<div>
        <strong> welcome,<shiro:principal/> </strong>
    </div>
  <div>  
    <form action="findname" method="post">
<input type="text" name="search" placeholder="根据姓名查询" style="    border: 1px solid #E6E6E6;;height:18px;width:120px;">
<button class="layui-btn layui-btn-sm"><i class="layui-icon">&#xe615;</i> 搜索</button>
</form>
 </div>

<div class="listDIV">
    <table class="table table-striped table-bordered table-hover table-condensed">

        <caption>学生列表</caption>
        <thead>
        <tr class="success">
            <th>学号</th>
            <th>姓名</th>
            <th>年龄</th>
            <th>性别</th>
            <th>出生日期</th>

           
        </tr>
        </thead>

        <tbody>
        <c:forEach items="${students}" var="s" varStatus="status">
            <tr>
                <td>${s.studentid}</td>
                <td>${s.name}</td>
                <td>${s.age}</td>
                <td>${s.sex}</td>
                <td>${s.birthday}</td>

               
            </tr>
        </c:forEach>

        </tbody>
    </table>
</div>



<div class="outDIV">
    <form action="listStudent">
        <table>
            <tr>
                <td><input type="submit" value="返回"></td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>