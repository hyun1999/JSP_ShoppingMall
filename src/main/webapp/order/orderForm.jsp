<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  shoppingMall.domain.Product product = (shoppingMall.domain.Product) request.getAttribute("product");
  int quantity = Integer.parseInt(request.getAttribute("quantity").toString());
%>
<html>
<head>
  <title>주문서</title>
  <link rel="stylesheet" href="<%= request.getContextPath() %>/css/order.css?v=3">
</head>
<body>
<h2>주문서</h2>
<form action="submitOrder.do" method="post">
  <input type="hidden" name="productId" value="<%= product.getNoProduct() %>">
  <input type="hidden" name="quantity" value="<%= quantity %>">
  <p>상품명: <%= product.getNmProduct() %></p>
  <p>수량: <%= quantity %></p>
  <p>총 가격: <%= product.getQtSalePrice() * quantity %> 원</p>

  <p>주문자명: <input type="text" name="orderPerson" required></p>
  <p>수령인명: <input type="text" name="receiver" required></p>
  <p>연락처: <input type="text" name="receiverTel" required></p>
  <p>주소: <input type="text" name="address" required></p>
  <p>우편번호: <input type="text" name="zip" required></p>
  <p>배송 장소: <input type="text" name="deliveryPlace"></p>

  <button type="submit">결제 및 주문하기</button>
</form>
</body>
</html>
