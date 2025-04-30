<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    String userName = (String) session.getAttribute("userName");
%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>Coupang</title>
    <link rel="stylesheet" href="css/style.css?v=2">
</head>
<body>

<jsp:include page="/common/header.jsp" />

<main>
    <% if (userName != null) { %>
    <div class="welcome"><strong><%= userName %></strong> 님 반갑습니다.</div>
    <% } %>

    <jsp:include page="/common/menu.jsp" />
    <jsp:include page="/common/products.jsp" />
</main>

<jsp:include page="/common/footer.jsp" />

</body>
</html>
