<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>선택 상품 주문서</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/orderSelectedForm.css?v=<%= System.currentTimeMillis() %>">
    <script>
        const contextPath = "<%= request.getContextPath() %>";
    </script>
</head>
<body>
<div class="container">
    <div class="login-wrapper">
        <a href="<%= request.getContextPath() %>/home.do" class="logo">
            <img src="<%= request.getContextPath() %>/images/logo.jpg" alt="로고" class="logo-img">
        </a>

        <h2 class="section-title">선택 상품 주문</h2>

        <form action="submitSelectedOrder.do" method="post" class="login-form">
            <table class="order-table">
                <thead>
                <tr>
                    <th>상품 ID</th>
                    <th>수량</th>
                    <th>금액</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="item" items="${selectedItems}">
                    <tr>
                        <td>${item.productId}</td>
                        <td>${item.quantity}</td>
                        <td>₩${item.amount}</td>
                        <input type="hidden" name="itemIds" value="${item.itemId}">
                    </tr>
                </c:forEach>
                </tbody>
            </table>

            <!-- 합계 출력 -->
            <div class="order-summary">
                <p><strong>상품 총액:</strong> <fmt:formatNumber value="${productTotal}" type="currency" currencySymbol="₩" groupingUsed="true"/></p>
                <p><strong>배송비 합계:</strong> <fmt:formatNumber value="${deliveryFee}" type="currency" currencySymbol="₩" groupingUsed="true"/></p>
                <p><strong>총 결제금액:</strong> <fmt:formatNumber value="${totalAmount}" type="currency" currencySymbol="₩" groupingUsed="true"/></p>
            </div>


            <!-- 서버 전달용 hidden 필드 -->
            <input type="hidden" name="productTotal" value="${productTotal}">
            <input type="hidden" name="deliveryFee" value="${deliveryFee}">
            <input type="hidden" name="totalAmount" value="${totalAmount}">

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
                    <input type="text" name="receiverTel" placeholder="전화번호" class="custom-input" required>
                </div>
            </div>
            <div class="input-group">
                <div class="input-center">
                    <input type="text" name="zip" placeholder="우편번호" class="custom-input" required>
                </div>
            </div>
            <div class="input-group">
                <div class="input-center">
                    <input type="text" name="address" placeholder="주소" class="custom-input" required>
                </div>
            </div>
            <div class="input-group">
                <div class="input-center">
                    <input type="text" name="deliveryPlace" placeholder="배송 요청사항" class="custom-input">
                </div>
            </div>

            <button type="submit" class="login-btn">주문하기</button>
        </form>
    </div>
</div>
</body>
</html>
