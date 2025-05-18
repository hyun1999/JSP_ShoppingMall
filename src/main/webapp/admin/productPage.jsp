<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>상품 관리</title>
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/manageProduct.css'/>">
    <script src="<c:url value='/js/manageProduct.js'/>"></script>
</head>
<body>
<div class="admin-container">

    <div class="admin-header">
        <div class="nav-left">
            <span class="admin-welcome">상품 관리</span>
        </div>
        <div class="nav-right">
            <a href="adminPage.do" class="header-link">관리자페이지</a>
            <span>|</span>
            <a href="manageDisplayCategory.do" class="header-link">전시 카테고리 관리</a>
            <span>|</span>
            <a href="manageProduct.do" class="header-link">상품 관리</a>
            <span>|</span>
            <a href="logout.do" class="header-link logout-link">로그아웃</a>
        </div>
    </div>

    <section class="product-form-section">
        <h2>${empty selectedProduct ? "상품 등록" : "상품 수정"}</h2>

        <form action="manageProduct.do" method="post" enctype="multipart/form-data" id="productForm">
            <input type="hidden" name="action" value="${empty selectedProduct ? 'create' : 'update'}">
            <input type="hidden" name="noProduct" value="${selectedProduct.noProduct}" />
            <input type="hidden" name="existingFileName" value="${selectedProduct.idFile}" />

            <label>상품명</label>
            <input type="text" name="nmProduct" value="${selectedProduct.nmProduct}" required />

            <label>상세설명</label>
            <textarea name="nmDetailExplain">${selectedProduct.nmDetailExplain}</textarea>

            <label>판매시작일 (YYYYMMDD)</label>
            <input type="text" name="dtStartDate" value="${selectedProduct.dtStartDate}" />

            <label>판매종료일 (YYYYMMDD)</label>
            <input type="text" name="dtEndDate" value="${selectedProduct.dtEndDate}" />

            <label>소비자가격</label>
            <input type="number" name="qtCustomerPrice" value="${selectedProduct.qtCustomerPrice}" />

            <label>판매가격</label>
            <input type="number" name="qtSalePrice" value="${selectedProduct.qtSalePrice}" required />

            <label>재고수량</label>
            <input type="number" name="qtStock" value="${selectedProduct.qtStock}" />

            <label>배송비</label>
            <input type="number" name="qtDeliveryFee" value="${selectedProduct.qtDeliveryFee}" />

<%--            <label>카테고리</label>--%>
<%--            <select name="categoryId" required>--%>
<%--                <option value="">카테고리 선택</option>--%>
<%--                <c:forEach var="category" items="${categoryList}">--%>
<%--                    <option value="${category.categoryId}" ${category.categoryId == selectedProduct.categoryId ? "selected" : ""}>--%>
<%--                            ${category.name}--%>
<%--                    </option>--%>
<%--                </c:forEach>--%>
<%--            </select>--%>

            <label>상품 이미지</label>
            <input type="file" name="productImage" />

            <c:if test="${not empty selectedProduct.idFile}">
                <div class="image-preview">
                    현재 이미지:
                    <img src="/uploads/${selectedProduct.idFile}" alt="이미지" width="100" />
                </div>
            </c:if>

            <div class="btn-area">
                <button type="submit" class="btn-blue">${empty selectedProduct ? "등록" : "수정"}</button>
            </div>
        </form>
    </section>

    <section class="product-list-section">
        <h2>상품 목록</h2>
        <table>
            <thead>
            <tr>
                <th>코드</th><th>상품명</th><th>가격</th><th>재고</th><th>상태</th><th>관리</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="p" items="${productList}">
                <tr>
                    <td>${p.noProduct}</td>
                    <td>${p.nmProduct}</td>
                    <td>${p.qtSalePrice}</td>
                    <td>${p.qtStock}</td>
                    <td>
                        <c:choose>
                            <c:when test="${p.qtStock == 0}">품절</c:when>
                            <c:otherwise>판매중</c:otherwise>
                        </c:choose>
                    </td>
                    <td>
                        <form action="manageProduct.do" method="get">
                            <input type="hidden" name="noProduct" value="${p.noProduct}" />
                            <button type="submit" class="btn-gray">수정</button>
                        </form>
                        <form action="manageProduct.do" method="post" onsubmit="return confirmDelete();">
                            <input type="hidden" name="action" value="delete" />
                            <input type="hidden" name="noProduct" value="${p.noProduct}" />
                            <button type="submit" class="btn-red">삭제</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </section>

</div>
</body>
</html>
