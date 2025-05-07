<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="shoppingMall.domain.Category" %>
<%
    List<Category> categories = (List<Category>) request.getAttribute("categoryList");
%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>카테고리 관리</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/manageCategory.css">
</head>
<body>
<div class="container">
    <h2>카테고리 관리</h2>
    <form action="manageCategory.do" method="post" id="categoryForm" onsubmit="setCorrectParentId(event)">
        <input type="hidden" name="action" value="create" id="actionInput">
        <input type="hidden" name="categoryId" id="categoryId">
        <input type="hidden" name="parentId" id="parentId">

        <label for="name">카테고리 이름</label>
        <input type="text" name="name" id="name" required>

        <label for="description">설명</label>
        <textarea name="description" id="description" rows="3"></textarea>

        <label>카테고리 계층 선택</label>
        <div>
            <select id="level1" onchange="renderLevel2()">
                <option value="">1차 카테고리 선택</option>
            </select>
            <select id="level2">
                <option value="">2차 카테고리 선택</option>
            </select>
        </div>

        <div class="button-wrapper">
            <button type="submit" class="button">등록 / 수정</button>
        </div>
    </form>

    <table>
        <thead>
        <tr>
            <th>ID</th>
            <th>이름</th>
            <th>설명</th>
            <th>상위 카테고리</th>
            <th>수정</th>
            <th>삭제</th>
        </tr>
        </thead>
        <tbody>
        <%
            if (categories != null) {
                for (Category category : categories) {
                    String parentName = "없음";
                    for (Category c : categories) {
                        if (c.getCategoryId() == category.getParentCategoryId()) {
                            parentName = c.getName();
                            break;
                        }
                    }
        %>
        <tr>
            <td><%= category.getCategoryId() %></td>
            <td><%= category.getName() %></td>
            <td><%= category.getDescription() %></td>
            <td><%= parentName %></td>
            <td>
                <button type="button" class="edit-btn"
                        data-id="<%= category.getCategoryId() %>"
                        data-name="<%= category.getName() %>"
                        data-description="<%= category.getDescription() %>"
                        data-parent="<%= category.getParentCategoryId() %>">
                    수정
                </button>
            </td>
            <td>
                <form method="post" action="manageCategory.do" onsubmit="return confirm('정말 삭제하시겠습니까?');">
                    <input type="hidden" name="action" value="delete">
                    <input type="hidden" name="categoryId" value="<%= category.getCategoryId() %>">
                    <button type="submit" class="delete-btn">삭제</button>
                </form>
            </td>
        </tr>
        <%
                }
            }
        %>
        </tbody>
    </table>
</div>

<!-- JS에서 사용할 카테고리 목록 전달 -->
<script>
    const allCategories = [];
    <% if (categories != null) {
        for (Category c : categories) { %>
    allCategories.push({
        id: <%= c.getCategoryId() %>,
        name: "<%= c.getName().replace("\"", "\\\"") %>",
        parentId: <%= c.getParentCategoryId() %>
    });
    <%  }
    } %>
</script>

<script src="${pageContext.request.contextPath}/js/manageCategory.js"></script>
</body>
</html>
