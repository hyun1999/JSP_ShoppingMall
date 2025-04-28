<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    String userName = (String) session.getAttribute("userName");
%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>MyShop - 메인</title>
    <link rel="stylesheet" href="css/style.css"> <%-- 스타일시트 연결 (있으면) --%>
    <style>
        body {
            font-family: 'Noto Sans KR', sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f8f8f8;
            text-align: center;
        }
        header {
            background-color: #333;
            padding: 20px;
            color: white;
            font-size: 24px;
            font-weight: bold;
        }
        main {
            margin-top: 80px;
        }
        .button-container {
            margin: 30px 0;
        }
        .button-container button {
            padding: 12px 30px;
            font-size: 18px;
            margin: 10px;
            cursor: pointer;
            border: none;
            border-radius: 5px;
            background-color: #4CAF50;
            color: white;
        }
        .button-container button:hover {
            background-color: #45a049;
        }
        .welcome {
            margin-top: 30px;
            font-size: 20px;
            color: #333;
        }
    </style>
</head>
<body>

<header>
    Coupang
</header>

<main>
    <h1>Coupang</h1>

    <div class="button-container">
        <% if (userName == null) { %>
        <button onclick="location.href='register.do'">회원가입</button>
        <button onclick="location.href='login.do'">로그인</button>
        <% } else { %>
        <button onclick="location.href='logout.do'">로그아웃</button>
        <button onclick="location.href='cart/cart.jsp'">장바구니</button>
        <% } %>
    </div>

    <% if (userName != null) { %>
    <div class="welcome"><strong><%= userName %></strong> 님 반갑습니다.</div>
    <% } %>
</main>

<footer>
    <p style="margin-top: 100px; color: #aaa;">&copy; 2025 MyShop. All rights reserved.</p>
</footer>

</body>
</html>