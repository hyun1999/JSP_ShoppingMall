<%--
  Created by IntelliJ IDEA.
  User: withg
  Date: 2025-04-27
  Time: 오후 11:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>로그인</title>
  <style>
    body {
      font-family: Arial, sans-serif;
      margin: 0;
      padding: 0;
      display: flex;
      justify-content: center;
      align-items: center;
      height: 100vh;
      background-color: #f4f4f4;
    }
    .login-container {
      background-color: white;
      padding: 30px;
      border-radius: 10px;
      box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
      width: 300px;
    }
    .login-container h2 {
      text-align: center;
      margin-bottom: 20px;
    }
    .form-group {
      margin-bottom: 15px;
    }
    label {
      display: block;
      margin-bottom: 5px;
    }
    input[type="text"], input[type="password"] {
      width: 100%;
      padding: 8px;
      border: 1px solid #ccc;
      border-radius: 5px;
    }
    .error-message {
      color: red;
      font-size: 0.9em;
      text-align: center;
    }
    button {
      width: 100%;
      padding: 10px;
      background-color: #4CAF50;
      color: white;
      border: none;
      border-radius: 5px;
      cursor: pointer;
    }
    button:hover {
      background-color: #45a049;
    }
  </style>
</head>
<body>
<div class="login-container">
  <h2>로그인</h2>
  <form action="login" method="post">
    <div class="form-group">
      <label for="userId">아이디</label>
      <input type="text" id="userId" name="userId" required>
    </div>
    <div class="form-group">
      <label for="password">비밀번호</label>
      <input type="password" id="password" name="password" required>
    </div>
    <div class="form-group">
      <button type="submit">로그인</button>
    </div>
    <div class="error-message">
      <c:if test="${not empty errorMessage}">
        ${errorMessage}
      </c:if>
    </div>
  </form>
</div>
</body>
</html>

