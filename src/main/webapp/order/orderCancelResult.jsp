<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>주문 취소 결과</title>
  <link rel="stylesheet" href="<%= request.getContextPath() %>/css/orderCancelResult.css?v=<%= System.currentTimeMillis() %>">

</head>
<body>
<div class="order-list-container">
  <h2>주문 취소 결과</h2>
  <p>${message}</p>
  <div style="margin-top: 20px;">
    <a href="orderList.do" class="back-btn">← 주문 목록으로 돌아가기</a>
  </div>
</div>
</body>
</html>