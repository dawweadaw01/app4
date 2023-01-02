<%--
  Created by IntelliJ IDEA.
  User: 20698
  Date: 2022/12/3
  Time: 20:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <base href="http://${header.host}${pageContext.request.contextPath}/"/>
    <title>注册</title>
</head>
<body>
<h1>用户注册</h1>
<form action="register" method="post">
    用户名：<input type="text" name="name"><br>
    密码：<input type="password" name="password"><br>
    <input type="submit" value="注 册">
    <input type="reset" value="重 置">
</form>
</body>
</html>
