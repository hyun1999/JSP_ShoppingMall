<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
  String userName = (String) session.getAttribute("userName");
%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>Coupang</title>
  <link rel="stylesheet" href="css/style.css?v=<%= System.currentTimeMillis() %>">
</head>
<body>

<jsp:include page="/common/header.jsp" />

<main>
  <jsp:include page="/common/menu.jsp" />

  <c:choose>
    <c:when test="${not empty product}">
      <jsp:include page="/common/productDetail.jsp" />
    </c:when>
    <c:otherwise>
      <jsp:include page="/common/products.jsp" />
    </c:otherwise>
  </c:choose>
</main>

<jsp:include page="/common/footer.jsp" />

</body>
</html>
