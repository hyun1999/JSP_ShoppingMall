<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<section class="products">
  <c:forEach var="product" items="${productList}">
    <div class="product">
      <form action="productDetail.do" method="get">
        <input type="hidden" name="productId" value="${product.noProduct}" />
        <button type="submit" style="border:none;background:none;padding:0;cursor:pointer;width:100%;">
          <img src="image.do?idFile=${product.idFile}" alt="${product.nmProduct}" width="150" height="150">
          <p>${product.nmProduct}</p>
          <p>₩${product.qtSalePrice}</p>
        </button>
      </form>
    </div>
  </c:forEach>
</section>
