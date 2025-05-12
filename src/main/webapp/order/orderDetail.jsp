<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
  long version = System.currentTimeMillis();
%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>주문 상세</title>
  <link rel="stylesheet" href="<%= request.getContextPath() %>/css/orderList.css?v=<%= version %>">
  <link rel="stylesheet" href="<%= request.getContextPath() %>/css/orderDetail.css?v=<%= version %>">
</head>
<body>
<div class="order-list-container">
  <h2>주문 상세</h2>

  <c:if test="${not empty order}">
    <p><strong>주문번호:</strong> ${order.idOrder}</p>
    <p><strong>주문일시:</strong> <fmt:formatDate value="${order.convertedDate}" pattern="yyyy-MM-dd HH:mm"/></p>
    <p><strong>주문자:</strong> ${order.nmOrderPerson}</p>
    <p><strong>수령인:</strong> ${order.nmReceiver}</p>
    <p><strong>연락처:</strong> ${order.nmReceiverTelno}</p>
    <p><strong>주소:</strong> ${order.nmDeliveryAddress}</p>
    <p><strong>요청사항:</strong> ${order.nmDeliverySpace}</p>
    <p><strong>결제상태:</strong>
      <c:choose>
        <c:when test="${order.stPayment == '20'}">
          <span class="status-badge status-paid">결제완료</span>
        </c:when>
        <c:otherwise>
          <span class="status-badge status-unpaid">미결제</span>
        </c:otherwise>
      </c:choose>
    </p>
    <p><strong>배송상태:</strong>
      <c:choose>
        <c:when test="${order.stOrder == '10'}">
          <span class="status-badge status-processing">주문완료</span>
        </c:when>
        <c:when test="${order.stOrder == '20'}">
          <span class="status-badge status-shipped">배송중</span>
        </c:when>
        <c:when test="${order.stOrder == '30'}">
          <span class="status-badge status-delivered">배송완료</span>
        </c:when>
        <c:when test="${order.stOrder == '99'}">
          <span class="status-badge status-delivered">취소완료</span>
        </c:when>
        <c:otherwise>
          <span class="status-badge">${order.stOrder}</span>
        </c:otherwise>
      </c:choose>
    </p>

    <c:if test="${order.stOrder == '10'}">
      <form action="cancelOrder.do" method="post" style="margin-top: 20px;">
        <input type="hidden" name="orderId" value="${order.idOrder}">
        <button type="submit" class="delete-btn cart-button">❌ 주문 취소하기</button>
      </form>
    </c:if>
  </c:if>

  <h3>주문 상품 목록</h3>
  <table class="order-table">
    <thead>
    <tr>
      <th>상품코드</th>
      <th>수량</th>
      <th>단가</th>
      <th>합계</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="item" items="${items}">
      <tr>
        <td>${item.noProduct}</td>
        <td>${item.qtOrderItem}</td>
        <td>₩<fmt:formatNumber value="${item.qtUnitPrice}" type="number"/></td>
        <td>₩<fmt:formatNumber value="${item.qtOrderItemAmount}" type="number"/></td>
      </tr>
    </c:forEach>
    </tbody>
  </table>

  <div class="back-button-wrapper" style="margin-top:30px; text-align:center;">
    <a href="orderList.do" class="back-btn">← 주문 내역으로 돌아가기</a>
  </div>
</div>
</body>
</html>
