<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
  long version = System.currentTimeMillis();
%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>주문 내역</title>
  <link rel="stylesheet" href="<%= request.getContextPath() %>/css/orderList.css?v=<%= version %>">
</head>
<body>
<div class="order-list-container">
  <h2>주문 내역</h2>

  <c:choose>
    <c:when test="${not empty orderList}">
      <table class="order-table">
        <thead>
        <tr>
          <th>주문번호</th>
          <th>주문일시</th>
          <th>금액</th>
          <th>배송비</th>
          <th>수령인</th>
          <th>연락처</th>
          <th>주소</th>
          <th>상태</th>
          <th>상세</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="order" items="${orderList}">
          <tr>
            <td>${order.idOrder}</td>
            <td><fmt:formatDate value="${order.convertedDate}" pattern="yyyy-MM-dd HH:mm"/></td>
            <td>₩<fmt:formatNumber value="${order.qtOrderAmount}" type="number"/></td>
            <td>₩<fmt:formatNumber value="${order.qtDeliMoney}" type="number"/></td>
            <td>${order.nmReceiver}</td>
            <td>${order.nmReceiverTelno}</td>
            <td>${order.nmDeliveryAddress}</td>
            <td>
              <c:choose>
                <c:when test="${order.stOrder == '10'}">주문완료</c:when>
                <c:when test="${order.stOrder == '20'}">배송중</c:when>
                <c:when test="${order.stOrder == '30'}">배송완료</c:when>
                <c:when test="${order.stOrder == '30'}">취소완료</c:when>
                <c:otherwise>${order.stOrder}</c:otherwise>
              </c:choose>
            </td>
            <td>
              <form action="orderDetail.do" method="get">
                <input type="hidden" name="orderId" value="${order.idOrder}">
                <button type="submit">보기</button>
              </form>
            </td>
          </tr>
        </c:forEach>
        </tbody>
      </table>
    </c:when>
    <c:otherwise>
      <p class="no-orders">주문 내역이 없습니다.</p>
    </c:otherwise>
  </c:choose>

  <!-- 마이페이지로 돌아가기 버튼 -->
  <div style="text-align: center; margin-top: 30px;">
    <a href="mypage.do" class="back-btn">← 마이페이지로 돌아가기</a>
  </div>
</div>
</body>
</html>
