<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="product-container">
  <form method="get" action="home.do" class="sort-form">
    <input type="hidden" name="category" value="${selectedCategory}" />
    <input type="hidden" name="query" value="${query}" />
    <label for="sort">정렬: </label>
    <select name="sort" id="sort" onchange="this.form.submit()">
      <option value="">기본</option>
      <option value="price_asc" <c:if test="${selectedSort == 'price_asc'}">selected</c:if>>가격 낮은순</option>
      <option value="price_desc" <c:if test="${selectedSort == 'price_desc'}">selected</c:if>>가격 높은순</option>
    </select>
  </form>

  <section class="products">
    <c:if test="${empty productList}">
      <p class="no-products">해당 조건에 맞는 상품이 없습니다.</p>
    </c:if>

    <c:forEach var="product" items="${productList}">
      <div class="product-card">
        <form action="productDetail.do" method="get" class="product-button">
          <input type="hidden" name="productId" value="${product.noProduct}" />
          <button type="submit" class="product-button">
            <div class="product-image">
              <img
                      src="image.do?idFile=${product.idFile}"
                      alt="${product.nmProduct}"
                      onerror="this.onerror=null; this.src='${pageContext.request.contextPath}/images/no-icon.png';" />
            </div>
            <div class="product-info">
              <div class="product-name">${product.nmProduct}</div>
              <div class="product-description">${product.nmDetailExplain}</div>
              <div class="product-price">
                <fmt:formatNumber value="${product.qtSalePrice}" type="number" groupingUsed="true" /> 원
              </div>
            </div>
          </button>
        </form>
      </div>
    </c:forEach>
  </section>
</div>
