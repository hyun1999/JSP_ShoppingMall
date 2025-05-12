<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    String userName = (String) session.getAttribute("userName");

    if (userName == null) {
        response.sendRedirect("login.do");
        return;
    }
%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>마이페이지</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/mypage.css">
    <script src="<%= request.getContextPath() %>/js/mypage.js"></script>
</head>
<body>
<div class="mypage-container">
    <h1>마이페이지</h1>

    <div class="user-info">
        <p><strong>환영합니다, <%= userName %> 님!</strong></p>
        <p>가입된 이메일: <%= session.getAttribute("email") %></p>
    </div>

    <div class="actions">
        <a href="orderList.do">주문 내역 보기</a>
        <a href="editProfile.do">회원정보 수정</a>
        <form id="deleteForm" action="memberDelete.do" method="post">
            <button type="submit" id="deleteLink">탈퇴하기</button>
        </form>
    </div>

    <div class="back-to-home">
        <a href="home.do" class="home-btn">← 홈으로 돌아가기</a>
    </div>
</div>
</body>
</html>
