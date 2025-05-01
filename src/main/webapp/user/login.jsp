<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>쿠팡 로그인</title>
  <link rel="stylesheet" href="<%= request.getContextPath() %>/css/login.css">

</head>
<body>
<div class="container">
  <div class="login-wrapper">
    <a href="home.do" class="logo">
      <img src="<%= request.getContextPath() %>/images/logo.jpg" alt="Coupang 로고" class="logo-img">
    </a>
    <div class="login-tabs">
      <span class="tab-btn active" data-tab="email">이메일 로그인</span>
      <span class="tab-btn" data-tab="phone">휴대폰번호 로그인</span>
      <span class="tab-btn" data-tab="qr">QR코드 로그인</span>
    </div>
  </div>
</div>

<div class="tab-under-line"></div>

<div class="container">
  <div class="login-wrapper">

    <!-- 이메일 로그인 -->
    <form action="login" method="post" class="login-form tab-content" id="tab-email">
      <div class="input-group">
        <input type="email" name="userId" placeholder="이메일을 입력하세요" value="withgustj@naver.com" required>
      </div>
      <div class="input-group">
        <input type="password" name="password" placeholder="비밀번호" required>
      </div>
      <div class="extra-options">
        <label><input type="checkbox" name="autoLogin"> 자동 로그인</label>
        <a href="#">아이디·비밀번호 찾기</a>
      </div>
      <button type="submit" class="login-btn">로그인</button>
      <button type="button" class="signup-btn" onclick="location.href='signup.do'">회원가입</button>

      <div class="error-message">
        <c:if test="${not empty errorMessage}">
          ${errorMessage}
        </c:if>
      </div>
    </form>

    <!-- 휴대폰 로그인 -->
    <form action="loginPhone" method="post" class="login-form tab-content" id="tab-phone" style="display:none;">
      <div class="input-group">
        <input type="tel" name="phoneNumber" placeholder="휴대폰번호" required>
      </div>
      <div class="input-group">
        <input type="password" name="password" placeholder="비밀번호" required>
      </div>
      <button type="submit" class="login-btn">로그인</button>
    </form>

    <!-- QR코드 로그인 안내 -->
    <div class="login-form tab-content" id="tab-qr" style="display:none;">
      <p>QR코드로 로그인하시려면 쿠팡 앱에서 QR코드를 스캔해주세요.</p>
      <img src="<%= request.getContextPath() %>/images/qr-placeholder.png" alt="QR코드 안내" style="width: 100px;">
    </div>

  </div>
</div>

<script src="js/login.js"></script>
</body>
</html>