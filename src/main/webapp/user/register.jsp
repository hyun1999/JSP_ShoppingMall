<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>쿠팡 회원가입</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/register.css?v=1">
    <script>
        const contextPath = "<%= request.getContextPath() %>";
    </script>
    <script src="<%= request.getContextPath() %>/js/register.js" defer></script>
</head>
<body>
<div class="container">
    <div class="register-wrapper">
        <a href="home.do" class="logo">
            <img src="<%= request.getContextPath() %>/images/logo.jpg" alt="Coupang 로고" class="logo-img">
        </a>
        <h2 class="form-title">회원정보를 입력해주세요</h2>
        <form action="register" method="post" class="register-form">
            <!-- 이메일 입력 -->
            <div class="input-group">
                <div class="input-left">
                    <img src="<%= request.getContextPath() %>/images/email-icon.png" alt="이메일 아이콘" class="input-icon">
                </div>
                <div class="input-center">
                    <input type="email" name="userId" id="emailInput" placeholder="아이디(이메일)" class="custom-input" required>
                </div>
            </div>
            <div id="emailError" class="register-email-error">이메일을 입력하세요.</div>

            <!-- 비밀번호 입력 -->
            <div class="input-group">
                <div class="input-left">
                    <img src="<%= request.getContextPath() %>/images/lock-icon.png" alt="비밀번호 아이콘" class="input-icon">
                </div>
                <div class="input-center">
                    <input type="password" name="password" id="passwordInput" placeholder="비밀번호" class="custom-input" required>
                </div>
            </div>
            <div id="passwordError" class="register-password-error">비밀번호를 입력해주세요.</div>

            <!-- 이름 입력 -->
            <div class="input-group">
                <div class="input-left">
                    <img src="<%= request.getContextPath() %>/images/user_gray_icon.png" alt="이름 아이콘" class="input-icon">
                </div>
                <div class="input-center">
                    <input type="text" name="name" placeholder="이름" class="custom-input" required>
                </div>
            </div>

            <!-- 전화번호 입력 -->
            <div class="input-group">
                <div class="input-left">
                    <img src="<%= request.getContextPath() %>/images/phone-icon.png" alt="전화번호 아이콘" class="input-icon">
                </div>
                <div class="input-center">
                    <input type="tel" name="phone_num" placeholder="휴대폰 번호" class="custom-input" required>
                </div>
            </div>

            <div class="radio-group">
                <label><input type="radio" name="user_type" value="user" checked> 일반 사용자</label>
                <label><input type="radio" name="user_type" value="admin"> 관리자</label>
            </div>

            <div class="error-message">
                <c:if test="${not empty errorMessage}">
                    <div class="login-error-message">${errorMessage}</div>
                </c:if>
            </div>

            <button type="submit" class="register-btn">회원가입</button>
        </form>
    </div>
</div>
</body>
</html>
