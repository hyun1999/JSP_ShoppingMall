<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
  long version = System.currentTimeMillis();
%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>장바구니</title>
  <link rel="stylesheet" href="<%= request.getContextPath() %>/css/cart.css?v=<%= version %>">
  <script src="<%= request.getContextPath() %>/js/cart.js?v=<%= version %>" defer></script>
</head>
<body>

<div class="cart-container">
  <h2>장바구니</h2>

  <form action="orderSelected.do" method="post">
    <table class="cart-table">
      <thead>
      <tr>
        <th class="select-col">
          <label class="select-all-label">
            <input type="checkbox" onclick="toggleSelectAll(this)">
            전체
          </label>
        </th>
        <th>상품 ID</th>
        <th>수량</th>
        <th>금액</th>
        <th>수정</th>
        <th>삭제</th>
      </tr>
      </thead>
      <tbody>
      <c:forEach var="item" items="${cartItems}">
        <tr>
          <td class="select-col">
            <input type="checkbox" name="selectedItems" value="${item.itemId}">
          </td>
          <td>${item.productId}</td>
          <td>
            <form action="updateCartItem.do" method="post">
              <input type="hidden" name="itemId" value="${item.itemId}">
              <input type="number" name="quantity" value="${item.quantity}" min="1">
              <button type="submit" class="update-btn cart-button">변경</button>
            </form>
          </td>
          <td data-amount="${item.amount}">₩${item.amount}</td>
          <td>
            <form action="deleteCartItem.do" method="post">
              <input type="hidden" name="itemId" value="${item.itemId}">
              <button type="submit" class="delete-btn cart-button">삭제</button>
            </form>
          </td>
        </tr>
      </c:forEach>
      </tbody>
    </table>

    <div class="bottom-actions">
      <form action="clearCart.do" method="post" style="display:inline;">
        <button type="submit" class="clear-btn cart-button">🗑 장바구니 비우기</button>
      </form>
      <button type="submit" class="order-btn cart-button">🛍 선택상품 주문</button>
    </div>
    <div class="selection-summary">
      <span id="totalAmount">총 금액: ₩0</span>
      <button type="submit" formaction="deleteSelected.do" class="delete-btn cart-button">🗑 선택 상품 삭제</button>
    </div>

  </form>

  <div class="back-button-wrapper">
    <a href="home.do" class="back-btn">← 홈으로 돌아가기</a>
  </div>
</div>

</body>
</html>
