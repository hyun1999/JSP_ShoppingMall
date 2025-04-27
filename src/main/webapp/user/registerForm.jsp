<%--
  Created by IntelliJ IDEA.
  User: withg
  Date: 2025-04-26
  Time: 오전 1:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>회원가입</title>
    <script>
        function checkEmailDuplicate() {
            var userId = document.getElementsByName('id')[0].value;
            var errorMessage = document.getElementById('email-error-message');

            if (userId) {
                var xhr = new XMLHttpRequest();
                xhr.open('POST', 'check-email', true);
                xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
                xhr.onreadystatechange = function() {
                    if (xhr.readyState == 4 && xhr.status == 200) {
                        // 서버에서 반환된 메시지에 따라 오류 메시지 표시
                        if (xhr.responseText == '사용 불가능한 이메일') {
                            errorMessage.innerText = '이미 사용 중인 이메일입니다.';
                        } else {
                            errorMessage.innerText = '사용 가능한 이메일입니다.';
                        }
                    }
                };
                xhr.send('userId=' + userId);
            } else {
                errorMessage.innerText = '이메일을 입력해주세요.';
            }
        }
    </script>
</head>
<body>
<h1>회원가입</h1>
<form action="register" method="post">
    <label>이메일: <input type="text" name="id"></label><br><br>
    <button type="button" onclick="checkEmailDuplicate()">중복 확인</button><br><br>
    <div id="email-error-message" style="color: red;"></div>

    <label>비밀번호: <input type="password" name="password"></label><br><br>
    <label>이름: <input type="text" name="name"></label><br><br>
    <label>전화번호: <input type="text" name="phone_num"></label><br><br>
    <label>관리자: <input type="radio" name="user_type" value="admin"></label>
    <label>일반 사용자: <input type="radio" name="user_type" value="user"></label><br><br>
    <button type="submit">회원가입</button>
    <c:if test="${not empty errorMessage}">
        <div class="error-message">
                ${errorMessage}
        </div>
    </c:if>
</form>
</body>
</html>
