<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>선택 상품 주문서</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/orderSelectedForm.css?v=<%= System.currentTimeMillis() %>">

</head>
<body>
<h2>선택 상품 주문</h2>

<form action="submitSelectedOrder.do" method="post">
    <table border="1">
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
                <td>${item.amount}</td>
            </tr>
            <input type="hidden" name="itemIds" value="${item.itemId}">
        </c:forEach>
        </tbody>
    </table>

    <h3>주문자 정보 입력</h3>
    <label>주문자 이름: <input type="text" name="orderPerson" required></label><br>
    <label>수령인 이름: <input type="text" name="receiver" required></label><br>
    <label>전화번호: <input type="text" name="receiverTel" required></label><br>
    <label>우편번호: <input type="text" name="zip" required></label><br>
    <label>주소: <input type="text" name="address" required></label><br>
    <label>배송 요청사항: <input type="text" name="deliveryPlace"></label><br>

    <button type="submit">주문하기</button>
</form>
</body>
</html>