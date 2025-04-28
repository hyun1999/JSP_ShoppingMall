<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<meta charset="UTF-8">
<%
  String userName = (String) session.getAttribute("userName");
%>
<header>

  <div class="header-container">
    <div class="header-button-container">
      <% if (userName == null) { %>
      <button onclick="location.href='register.do'">회원가입</button>
      <button onclick="location.href='login.do'">로그인</button>
      <% } else { %>
      <button onclick="location.href='logout.do'">로그아웃</button>
      <button onclick="location.href='cart/cart.jsp'">장바구니</button>
      <% } %>
    </div>
  </div>

  <div class="search-container">
    <h1>Coupang</h1>
    <form action="searchResult.do" method="get">
      <input type="text" name="query" id="search-input" placeholder="검색어를 입력하세요..." required />
      <button type="submit">검색</button>
    </form>
    <div class="myPage">마이페이지</div>
    <div class="cart">장바구니</div>
  </div>
</header>
