<%--
  Created by IntelliJ IDEA.
  User: withg
  Date: 2025-04-26
  Time: 오전 1:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>회원가입</title>
</head>
<body>
<h1>회원가입</h1>
<form action="register" method="post">
    <label>이메일: <input type="text" name="id"></label><br><br>
    <label>비밀번호: <input type="password" name="password"></label><br><br>
    <label>이름: <input type="text" name="name"></label><br><br>
    <label>전화번호: <input type="text" name="name"></label><br><br>
    <label>관리자: <input type="radio" name="user_type" value="admin"></label>
    <label>일반 사용자: <input type="radio" name="user_type" value="user"></label><br><br>
    <button type="submit">회원가입</button>
</form>
</body>
</html>
