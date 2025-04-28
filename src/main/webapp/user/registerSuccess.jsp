<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>회원가입 완료</title>
</head>
<body>
<h2>회원가입이 완료</h2>
<%
  response.sendRedirect(request.getContextPath() + "/index.jsp");
%>
</body>
</html>
