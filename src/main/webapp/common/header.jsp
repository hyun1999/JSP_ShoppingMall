<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<meta charset="UTF-8">
<%
  String userName = (String) session.getAttribute("userName");
%>

<script>
  const contextPath = "<%= request.getContextPath() %>";
</script>
<script src="<%= request.getContextPath() %>/js/header.js" defer></script>


<header>
  <div class="header-container">
    <div class="header-button-container">
      <% if (userName == null) { %>
      <a href="login.do" class="header-link">로그인</a>
      <a href="register.do" class="header-link">회원가입</a>
      <% } else { %>
      <a href="logout.do" class="header-link">로그아웃</a>
      <a href="cart/cart.jsp" class="header-link">장바구니</a>
      <% } %>
    </div>
  </div>

  <div class="search-container">
    <div class="search-inner">
      <h1>Coupang</h1>
      <form action="searchResult.do" method="get" class="styled-search-form">
        <div class="select-wrapper">
          <div id="selected-category" class="custom-select-display">전체</div>
          <img id="arrow-icon" src="<%= request.getContextPath() %>/images/downArrow.png" alt="화살표" class="arrow-icon">

          <div id="dropdown-menu" class="dropdown-menu" style="display: none;">
            <div class="dropdown-item" data-value="all">전체</div>
            <div class="dropdown-item" data-value="electronics">전자제품</div>
            <div class="dropdown-item" data-value="clothing">의류</div>
            <div class="dropdown-item" data-value="books">도서</div>
          </div>
        </div>

        <input type="hidden" name="category" id="category-input" value="all">
        <input type="text" name="query" class="search-input" placeholder="찾고 싶은 상품을 검색해보세요!" required />
        <button type="submit" class="search-button">🔍</button>
      </form>

      <div class="utility-links">
        <a href="mypage.do" class="myPage">마이페이지</a>
        <a href="#" class="cart">장바구니</a>
      </div>
    </div>
  </div>
</header>
