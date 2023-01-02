<%--
  Created by IntelliJ IDEA.
  User: 20698
  Date: 2022/12/2
  Time: 22:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <base href="http://${header.host}${pageContext.request.contextPath}/"/>
    <title>登录</title>
</head>
<body>
<form action="login" method="post">
    用户名：<input type="text" name="name"><br>
    密码：<input type="password" name="password"><br>
    <input type="submit" value="登 录">
</form>
</body>
</html>
