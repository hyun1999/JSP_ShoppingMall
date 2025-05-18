<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>상품 상세보기</title>

  <link rel="stylesheet" href="<%= request.getContextPath() %>/css/productDetail.css?v=1">
</head>
<body>
<div class="container">
  <div class="detail-wrapper">
    <div class="image-box">
      <img src="image.do?idFile=${product.idFile}" alt="${product.nmProduct}" class="product-img">
    </div>

    <h2 class="product-name">${product.nmProduct}</h2>

    <div class="product-info">
      <p><strong>가격:</strong> ₩${product.qtSalePrice}</p>
      <p><strong>소비자 가격:</strong> ₩${product.qtCustomerPrice}</p>
      <p><strong>재고:</strong> ${product.qtStock} 개</p>
      <p><strong>설명:</strong> ${product.nmDetailExplain}</p>
    </div>

    <div class="action-forms">
      <form action="addToCart.do" method="post" class="inline-form">
        <input type="hidden" name="productId" value="${product.noProduct}">
        <label for="quantity"><strong>수량:</strong></label>
        <input type="number" name="quantity" id="quantity" value="1" min="1" max="${product.qtStock}" required class="quantity-input">
        <button type="submit" class="btn btn-cart">장바구니 담기</button>
      </form>

      <form action="orderForm.do" method="post" class="inline-form">
        <input type="hidden" name="productId" value="${product.noProduct}">
        <input type="hidden" name="quantity" id="orderQuantity">
        <button type="submit" class="btn btn-order">바로 주문하기</button>
      </form>
    </div>

    <button onclick="history.back();" class="btn btn-back">이전 페이지로</button>
  </div>
</div>

<script>
  document.querySelector('form[action="orderForm.do"]').addEventListener('submit', function () {
    const selectedQty = document.getElementById("quantity").value;
    document.getElementById("orderQuantity").value = selectedQty;
  });
</script>
</body>
</html>
