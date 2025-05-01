<%@ page import="java.util.List" %>
<%@ page import="shoppingMall.domain.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>관리자 페이지</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/adminPage.css">
</head>
<body>
<div class="container">
    <h2><%= session.getAttribute("userName") %> 관리자님, 반갑습니다.</h2>
    <a href="logout.do" class="header-link">로그아웃</a>

    <h3>회원가입 요청 목록</h3>
    <div class="card-container">
        <%
            List<User> pendingUsers = (List<User>) request.getAttribute("allPendingUsers");
            if (pendingUsers != null && !pendingUsers.isEmpty()) {
                for (User user : pendingUsers) {
        %>
        <div class="member-card">
            <form action="approveUser.do" method="post">
                <input type="hidden" name="userId" value="<%= user.getUserId() %>" />
                <p><strong>아이디:</strong> <%= user.getUserId() %></p>
                <p><strong>이름:</strong> <%= user.getUserName() %></p>
                <div class="btn-area">
                    <input type="submit" value="승인" />
                </div>
            </form>
        </div>
        <%
            }
        } else {
        %>
        <p>가입 요청한 회원이 없습니다.</p>
        <% } %>
    </div>

    <h3>탈퇴 요청 회원 목록</h3>
    <div class="card-container">
        <%
            List<User> withdrawalUsers = (List<User>) request.getAttribute("withdrawalUsers");
            if (withdrawalUsers != null && !withdrawalUsers.isEmpty()) {
                for (User user : withdrawalUsers) {
        %>
        <div class="member-card withdrawn-user">
            <form action="deleteMember.do" method="post">
                <input type="hidden" name="userId" value="<%= user.getUserId() %>" />
                <p><strong>아이디:</strong> <%= user.getUserId() %></p>
                <p><strong>이름:</strong> <%= user.getUserName() %></p>
                <p><strong>이메일:</strong> <%= user.getEmail() %></p>
                <div class="btn-area">
                    <input type="submit" value="탈퇴 승인 및 삭제" />
                </div>
            </form>
        </div>
        <%
            }
        } else {
        %>
        <p>탈퇴 요청한 회원이 없습니다.</p>
        <% } %>
    </div>

    <h3>전체 회원 목록</h3>
    <div class="card-container">
        <%
            List<User> allUsers = (List<User>) request.getAttribute("allUsers");
            if (allUsers != null && !allUsers.isEmpty()) {
                for (User user : allUsers) {
        %>
        <div class="member-card">
            <form action="updateMember.do" method="post">
                <input type="hidden" name="userId" value="<%= user.getUserId() %>" />
                <p><strong>아이디:</strong> <%= user.getUserId() %></p>
                <label>이름:
                    <input type="text" name="name" value="<%= user.getUserName() %>" readonly/>
                </label>
                <label>이메일:
                    <input type="email" name="email" value="<%= user.getEmail() %>" readonly/>
                </label>
                <%
                    String mobile = user.getMobileNo();
                    String formattedMobile = "";

                    if (mobile != null && mobile.length() == 11) {
                        if ("Admn".equals(user.getUserType().name())) {
                            formattedMobile = "010-XXXX-XXXX";
                        } else {
                            formattedMobile = "010-" + mobile.substring(3, 7) + "-" + mobile.substring(7);
                        }
                    } else {
                        formattedMobile = mobile;
                    }
                %>
                <label>전화번호:
                    <input type="text" name="mobile" value="<%= formattedMobile %>" readonly/>
                </label>
                <label>상태:
                    <select name="status">
                        <option value="ST00" <%= "ST00".equals(user.getStatus().name()) ? "selected" : "" %>>요청</option>
                        <option value="ST01" <%= "ST01".equals(user.getStatus().name()) ? "selected" : "" %>>정상</option>
                        <option value="ST02" <%= "ST02".equals(user.getStatus().name()) ? "selected" : "" %>>정지</option>
                    </select>
                </label>
                <label>권한:
                    <select name="userType">
                        <option value="User" <%= "User".equals(user.getUserType().name()) ? "selected" : "" %>>일반 사용자</option>
                        <option value="Admn" <%= "Admn".equals(user.getUserType().name()) ? "selected" : "" %>>관리자</option>
                    </select>
                </label>
                <div class="btn-area">
                    <input type="submit" value="수정" />
                </div>
            </form>
        </div>
        <%
            }
        } else {
        %>
        <p>등록된 회원이 없습니다.</p>
        <% } %>
    </div>
</div>
</body>
</html>
