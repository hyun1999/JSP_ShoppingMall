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
      <a href="home.do" class="logo">
        <img src="<%= request.getContextPath() %>/images/logo.jpg" alt="Coupang 로고" class="logo-img">
      </a>
      <form action="searchResult.do" method="get" class="styled-search-form">
        <div class="select-wrapper">
          <div id="selected-category" class="custom-select-display">전체</div>
          <img id="arrow-icon" src="<%= request.getContextPath() %>/images/down-arrow.png" alt="화살표" class="arrow-icon">

          <div id="dropdown-menu" class="dropdown-menu" style="display: none;">
            <div class="dropdown-item" data-value="all">전체</div>
            <div class="dropdown-item" data-value="electronics">전자제품</div>
            <div class="dropdown-item" data-value="clothing">의류</div>
            <div class="dropdown-item" data-value="books">도서</div>
          </div>
        </div>

        <input type="hidden" name="category" id="category-input" value="all">
        <input type="text" name="query" class="search-input" placeholder="찾고 싶은 상품을 검색해보세요!" required />
        <button type="submit" class="search-button">
          <img src="<%= request.getContextPath() %>/images/search_icon.png" alt="검색" class="search-icon-img">
        </button>
      </form>

      <div class="utility-links">
        <a href="mypage.do" class="myPage">
          <div class="icon-container">
            <img src="<%= request.getContextPath() %>/images/user_icon.png" alt="마이쿠팡" class="mypage-icon-img">
            <span class="text">마이쿠팡</span>
          </div>
        </a>
        <a href="cart/cart.jsp" class="cart">
          <div class="icon-container">
            <img src="<%= request.getContextPath() %>/images/cart_icon.png" alt="장바구니" class="cart-icon-img">
            <span class="text">장바구니</span>
          </div>
        </a>
      </div>
    </div>
  </div>
</header>
