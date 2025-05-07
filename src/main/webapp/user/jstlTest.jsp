<%--<%@ taglib prefix="c" uri="https://java.sum.com/jsp/jstl/core" %>--%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%--  변수 설정 --%>
<c:set var="userName" value="홍길동"/>
<p>설정된 이름: <c:out value="${userName}" /></p>

<%--변수 제거--%>
<c:remove var="userName"/>

<%--제거 후 출력--%>
<p>제거된 이름: <c:out value="${userName}" default="이름 없음"/> </p>
</body>
</html>
