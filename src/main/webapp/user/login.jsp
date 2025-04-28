<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>로그인</title>
  <link rel="stylesheet" href="css/login.css">
  <script src="js/login.js" defer></script>
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

