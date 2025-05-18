<%@ page import="shoppingMall.domain.Order" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%
    Order order = (Order) request.getAttribute("order");
%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>주문 완료</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/order.css?v=1">
</head>
<body>
<div class="container">
    <div class="order-wrapper">
        <a href="<%= request.getContextPath() %>/home.do" class="logo">
            <img src="<%= request.getContextPath() %>/images/logo.jpg" alt="로고" class="logo-img">
        </a>

        <h2 class="section-title">주문이 완료되었습니다!</h2>


        <div class="order-summary">
            <p><strong>주문번호:</strong> ${order.idOrder}</p>
            <p><strong>수령인:</strong> ${order.nmReceiver}</p>
            <p><strong>주소:</strong> ${order.nmDeliveryAddress}</p>

            <p><strong>상품 금액:</strong>
                <fmt:formatNumber value="${order.qtOrderAmount}" type="currency" currencySymbol="₩" groupingUsed="true"/>
            </p>
            <p><strong>배송비:</strong>
                <fmt:formatNumber value="${order.qtDeliMoney}" type="currency" currencySymbol="₩" groupingUsed="true"/>
            </p>
            <p><strong>총 결제금액:</strong>
                <fmt:formatNumber value="${order.qtOrderAmount + order.qtDeliMoney}" type="currency" currencySymbol="₩" groupingUsed="true"/>
            </p>
        </div>


        <a href="home.do" class="login-btn">다른 상품 보러가기</a>
    </div>
</div>
</body>
</html>
