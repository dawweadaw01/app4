<%--
  Created by IntelliJ IDEA.
  User: 20698
  Date: 2022/12/3
  Time: 20:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="myfunc" uri="http://cdu.zch/functions" %>
<html>
<head>
    <base href="http://${header.host}${pageContext.request.contextPath}/"/>
    <title>回复</title>
    <link href="css/style.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<div class="msg">
    <div class="subject">${sessionScope.msg.subject}</div>
    <div class="content">${sessionScope.msg.content}</div>
    <div>
        <span>留言人: ${sessionScope.msg.user.name}(${sessionScope.msg.user.id})</span>
        <span>留言时间: ${myfunc:formatDatetime(sessionScope.msg.addMsgTime)}</span>
    </div>
</div>
</div>
<form action="reply" method="post">
    <input type="text" name="id" value="${sessionScope.msg.user.id}" readonly><br>
    <textarea name="reply" rows="3" cols="50"></textarea><br>
    <input type="submit" value="回复">
    <input type="reset" value="取消">
</form>
</body>
</html>
