<%@ page import="shoppingMall.dto.UserTypeDto" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    UserTypeDto user = (UserTypeDto) request.getAttribute("user");
%>

<html>
<head>
    <title>editProfile</title>
</head>
<body>
<h1>Edit Profile</h1>
    <form action="editProfile.do" method="post">
        아이디: <input type="text" name="userId" value="<%= user.getUserId() %>" readonly><br>
        이름: <input type="text" name="userName" value="<%= user.getUserName() %>"><br>
        이메일: <input type="email" name="email" value="<%= user.getEmail() %>"><br>
        전화번호: <input type="text" name="mobile" value="<%= user.getMobileNo() %>"><br>
        <input type="submit" value="Update">
    </form>
</body>
</html>
