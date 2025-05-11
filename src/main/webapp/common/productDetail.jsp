<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="product-detail" style="max-width: 800px; margin: 30px auto; padding: 20px; border: 1px solid #ccc;">
  <img src="image.do?idFile=${product.idFile}" alt="${product.nmProduct}" style="width: 300px;">
  <h2>${product.nmProduct}</h2>
  <p><strong>가격:</strong> ₩${product.qtSalePrice}</p>
  <p><strong>소비자 가격:</strong> ₩${product.qtCustomerPrice}</p>
  <p><strong>재고:</strong> ${product.qtStock} 개</p>
  <p><strong>설명:</strong> ${product.nmDetailExplain}</p>

  <form action="orderForm.do" method="post" style="margin-top: 20px;">
    <input type="hidden" name="productId" value="${product.noProduct}">
    <label for="quantity"><strong>주문 수량:</strong></label>
    <input type="number" name="quantity" id="quantity" value="1" min="1" max="${product.qtStock}" required style="width: 60px;">

    <button type="submit" style="margin-left: 10px; padding: 10px 20px;">주문하기</button>
  </form>

  <br>
  <button onclick="history.back();" style="padding: 10px 20px;">이전 페이지로</button>
</div>
