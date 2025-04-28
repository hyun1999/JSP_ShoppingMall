<%--
  Created by IntelliJ IDEA.
  User: withg
  Date: 2025-04-28
  Time: 오후 8:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
  <div><%=session.getAttribute("userName")%>관리자님 반갑습니다.</div>
  <div><%=session.getAttribute("userType")%>관리자님 반갑습니다.</div>
</body>
</html>
