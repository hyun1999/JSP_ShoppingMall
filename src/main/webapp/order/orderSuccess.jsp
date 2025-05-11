<%@ page import="shoppingMall.domain.Order" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    Order order = (Order) request.getAttribute("order");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>주문 완료</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/order.css?v=1">
</head>
<body>
<div class="container order-success">
    <h2>주문이 완료되었습니다!</h2>
    <p><strong>주문번호:</strong> ${order.idOrder}</p>
    <p><strong>수령인:</strong> ${order.nmReceiver}</p>
    <p><strong>주소:</strong> ${order.nmDeliveryAddress}</p>
    <p><strong>총 금액:</strong> ₩${order.qtOrderAmount}</p>
    <a href="home.do">다른상품 보러가기</a>
</div>
</body>
</html>
