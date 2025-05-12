<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="product-detail" style="max-width: 800px; margin: 30px auto; padding: 20px; border: 1px solid #ccc;">
  <img src="image.do?idFile=${product.idFile}" alt="${product.nmProduct}" style="width: 300px;">
  <h2>${product.nmProduct}</h2>
  <p><strong>가격:</strong> ₩${product.qtSalePrice}</p>
  <p><strong>소비자 가격:</strong> ₩${product.qtCustomerPrice}</p>
  <p><strong>재고:</strong> ${product.qtStock} 개</p>
  <p><strong>설명:</strong> ${product.nmDetailExplain}</p>

  <!-- 주문 수량 선택 -->
  <div style="margin-top: 20px;">
    <form action="addToCart.do" method="post" style="display: inline-block;">
      <input type="hidden" name="productId" value="${product.noProduct}">
      <label for="quantity"><strong>수량:</strong></label>
      <input type="number" name="quantity" id="quantity" value="1" min="1" max="${product.qtStock}" required style="width: 60px;">
      <button type="submit" style="margin-left: 10px; padding: 10px 20px;">장바구니 담기</button>
    </form>

    <form action="orderForm.do" method="post" style="display: inline-block; margin-left: 10px;">
      <input type="hidden" name="productId" value="${product.noProduct}">
      <input type="hidden" name="quantity" id="orderQuantity">
      <button type="submit" style="padding: 10px 20px;">바로 주문하기</button>
    </form>
  </div>

  <br><br>
  <button onclick="history.back();" style="padding: 10px 20px;">이전 페이지로</button>
</div>

<script>
  // "바로 주문하기" 눌렀을 때 수량 동기화
  document.querySelector('form[action="orderForm.do"]').addEventListener('submit', function () {
    const selectedQty = document.getElementById("quantity").value;
    document.getElementById("orderQuantity").value = selectedQty;
  });
</script>
