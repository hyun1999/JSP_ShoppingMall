<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="shoppingMall.domain.Category, shoppingMall.domain.Product" %>
<%
  long version = System.currentTimeMillis();
%>
<html>
<head>
  <title>전시 카테고리 통합 관리</title>
  <link rel="stylesheet" href="<%= request.getContextPath() %>/css/manageDisplayCategory.css?v=<%= version %>">
</head>
<body>
<div class="admin-container">
  <div class="admin-header">
    <div class="nav-left">
      <a href="adminPage.do" class="header-link">관리자페이지</a>
      <span>|</span>
      <a href="manageProduct.do" class="header-link">상품 관리</a>
      <span>|</span>
      <a href="manageDisplayCategory.do" class="header-link">전시 카테고리 관리</a>
    </div>
    <div class="nav-right">
      <a href="logout.do" class="header-link">로그아웃</a>
    </div>
  </div>

  <c:if test="${not empty error}">
    <div class="error-message">
        ${error}
    </div>
  </c:if>

  <section class="category-section">
    <h2>① 전시 카테고리 등록</h2>
    <form action="addDisplayCategory.do" method="post" class="category-form">
      <div class="form-row"><label>카테고리명:</label> <input type="text" name="name" required /></div>
      <div class="form-row"><label>설명:</label> <input type="text" name="description" /></div>
      <div class="form-row"><label>상위 카테고리:</label>
        <select name="parentCategoryId">
          <option value="0">최상위</option>
          <c:forEach var="cat" items="${categoryList}">
            <option value="${cat.categoryId}">${cat.fullCategoryName}</option>
          </c:forEach>
        </select>
      </div>
      <div class="form-row"><label>순번:</label> <input type="number" name="order" value="1" /></div>
      <div class="form-row">
        <label>사용 여부:</label>
        <select name="used">
          <option value="Y">Y</option>
          <option value="N">N</option>
        </select>
      </div>
      <div class="form-actions">
        <input type="submit" value="카테고리 등록" />
      </div>
    </form>
  </section>

  <section class="mapping-section">
    <h2>② 상품 매핑</h2>
    <form action="mapProductToCategory.do" method="post" class="mapping-form">
      <div class="form-row">
        <label>카테고리:</label>
        <select name="nbCategory">
          <c:forEach var="cat" items="${categoryList}">
            <option value="${cat.categoryId}" ${!cat.leaf ? "disabled style='color:gray;'" : ""}>
                ${cat.fullCategoryName}${!cat.leaf ? " (하위 존재)" : ""}
            </option>
          </c:forEach>
        </select>
      </div>
      <div class="form-row">
        <label>상품:</label>
        <select name="noProduct">
          <c:forEach var="prod" items="${allProducts}">
            <c:set var="isMapped" value="false" />
            <c:forEach var="mapped" items="${mappedProducts}">
              <c:if test="${mapped.noProduct == prod.noProduct}">
                <c:set var="isMapped" value="true" />
              </c:if>
            </c:forEach>
            <option value="${prod.noProduct}" ${isMapped ? "disabled style='color:gray;'" : ""}>
                ${prod.nmProduct}${isMapped ? " (이미 매핑됨)" : ""}
            </option>
          </c:forEach>
        </select>
      </div>
      <div class="form-row">
        <label>순번:</label>
        <input type="number" name="cnOrder" value="1" />
      </div>
      <div class="form-actions">
        <input type="submit" value="매핑 등록" />
      </div>
    </form>
  </section>

  <section class="mapped-section">
    <h2>③ 매핑된 상품 목록</h2>
    <form action="manageDisplayCategory.do" method="get" class="category-selector-form">
      <select name="selectedCategoryId">
        <c:forEach var="cat" items="${categoryList}">
          <option value="${cat.categoryId}" ${cat.categoryId == selectedCategoryId ? 'selected' : ''}>
              ${cat.fullCategoryName}
          </option>
        </c:forEach>
      </select>
      <input type="submit" value="조회" />
    </form>

    <c:if test="${not empty mappedProducts}">
      <table class="mapped-product-table">
        <thead>
        <tr>
          <th>상품코드</th>
          <th>상품명</th>
          <th>해제</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="prod" items="${mappedProducts}">
          <tr>
            <td>${prod.noProduct}</td>
            <td>${prod.nmProduct}</td>
            <td>
              <form action="unmapProductFromCategory.do" method="post">
                <input type="hidden" name="categoryId" value="${selectedCategoryId}" />
                <input type="hidden" name="productId" value="${prod.noProduct}" />
                <input type="submit" value="해제" />
              </form>
            </td>
          </tr>
        </c:forEach>
        </tbody>
      </table>
    </c:if>
  </section>
</div>
</body>
</html>
