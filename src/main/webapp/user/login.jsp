<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>쿠팡 로그인</title>
  <link rel="stylesheet" href="<%= request.getContextPath() %>/css/login.css?v=3">
  <script>
    const contextPath = "<%= request.getContextPath() %>";
  </script>
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

<div class="container container-bottom">
  <div class="login-wrapper">

    <form action="login.do" method="post" class="login-form tab-content" id="tab-email">
      <div class="input-group email-group">
        <div class="input-left">
          <img src="<%= request.getContextPath() %>/images/email-icon.png" alt="이메일 아이콘" class="input-icon">
        </div>
        <div class="input-center">
          <input type="email" id="emailInput" name="userId" placeholder="아이디(이메일)" class="custom-input" required>
        </div>
        <div class="input-right">
          <img src="<%= request.getContextPath() %>/images/clear-icon.png" class="clear-icon clear-email" alt="입력 지우기">
        </div>
      </div>
      <div id="emailError" class="email-error">아이디(이메일)를 입력해주세요.</div>

      <div class="input-group">
        <div class="input-left">
          <img src="<%= request.getContextPath() %>/images/lock-icon.png" alt="비밀번호 아이콘" class="input-icon">
        </div>
        <div class="input-center">
          <input type="password" name="password" id="passwordInput" placeholder="비밀번호" class="custom-input" required>
        </div>
        <div class="input-right">
          <img src="<%= request.getContextPath() %>/images/eye-icon.png" class="clear-icon toggle-password" alt="비밀번호 보기/숨기기">
        </div>
      </div>
      <div id="passwordError" class="password-error">비밀번호를 입력해주세요.</div>
      <div class="error-message">
        <!-- 로그인 실패 시 오류 메시지 표시 -->
        <c:if test="${not empty error}">
          <div class="login-error-message">${error}</div>
        </c:if>
      </div>
      <div class="custom-checkbox-wrapper">
        <label class="custom-checkbox-label">
          <input type="checkbox" name="autoLogin" class="custom-checkbox-input">
          <span class="custom-checkbox-box"></span>
          자동 로그인
        </label>
        <a href="#" class="find-id-password-with-icon">
          <span class="find-id-password">아이디·비밀번호 찾기</span>
          <img src="<%= request.getContextPath() %>/images/right-arrow-icon.png" alt="화살표 아이콘" class="find-icon">
        </a>
      </div>


      <button type="submit" class="login-btn">로그인</button>
      <div class="btn-split"></div>
      <button type="button" class="signup-btn" onclick="location.href='register.do'">회원가입</button>


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

<script src="<%= request.getContextPath() %>/js/login.js"></script>
</body>
</html>
