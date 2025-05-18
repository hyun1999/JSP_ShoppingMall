<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  shoppingMall.domain.Product product = (shoppingMall.domain.Product) request.getAttribute("product");
  int quantity = Integer.parseInt(request.getAttribute("quantity").toString());
%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>주문서</title>
  <link rel="stylesheet" href="<%= request.getContextPath() %>/css/order.css?v=3">
</head>
<body>
<div class="container">
  <div class="order-wrapper">
    <a href="<%= request.getContextPath() %>/home.do" class="logo">
      <img src="<%= request.getContextPath() %>/images/logo.jpg" alt="로고" class="logo-img">
    </a>

    <h2 class="section-title">상품 주문서</h2>

    <form action="submitOrder.do" method="post" class="login-form">
      <input type="hidden" name="productId" value="<%= product.getNoProduct() %>">
      <input type="hidden" name="quantity" value="<%= quantity %>">

      <div class="order-summary">
        <p><strong>상품명:</strong> <%= product.getNmProduct() %></p>
        <p><strong>수량:</strong> <%= quantity %></p>
        <p><strong>총 가격:</strong> <%= product.getQtSalePrice() * quantity %> 원</p>
      </div>

      <div class="tab-under-line"></div>

      <h3 class="form-section-title">주문자 정보 입력</h3>

      <div class="input-group">
        <div class="input-center">
          <input type="text" name="orderPerson" placeholder="주문자 이름" class="custom-input" required>
        </div>
      </div>
      <div class="input-group">
        <div class="input-center">
          <input type="text" name="receiver" placeholder="수령인 이름" class="custom-input" required>
        </div>
      </div>
      <div class="input-group">
        <div class="input-center">
          <input type="text" name="receiverTel" placeholder="연락처" class="custom-input" required>
        </div>
      </div>
      <div class="input-group">
        <div class="input-center">
          <input type="text" name="address" placeholder="주소" class="custom-input" required>
        </div>
      </div>
      <div class="input-group">
        <div class="input-center">
          <input type="text" name="zip" placeholder="우편번호" class="custom-input" required>
        </div>
      </div>
      <div class="input-group">
        <div class="input-center">
          <input type="text" name="deliveryPlace" placeholder="배송 장소 (선택)" class="custom-input">
        </div>
      </div>

      <button type="submit" class="login-btn">결제 및 주문하기</button>
    </form>
  </div>
</div>
</body>
</html>
