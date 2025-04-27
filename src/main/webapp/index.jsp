<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Hello World!" %>
</h1>
<br/>
<div style="text-align: center; margin-top: 50px;">
    <div style="margin-bottom: 20px;">
        <button onclick="location.href='register.do'" style="padding: 10px 20px; font-size: 16px;">회원가입</button>
    </div>
    <div>
        <button onclick="location.href='login.do'" style="padding: 10px 20px; font-size: 16px;">로그인</button>
    </div>
</div>
<br/>
</body>
</html>