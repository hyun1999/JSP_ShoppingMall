<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="shoppingMall.domain.Category" %>
<%@ page import="java.util.*" %>

<%
  List<Category> categoryList = (List<Category>) request.getAttribute("categoryList");

  Map<Integer, List<Category>> categoryMap = new HashMap<>();
  for (Category c : categoryList) {
    categoryMap.computeIfAbsent(c.getParentCategoryId(), k -> new ArrayList<>()).add(c);
  }

  List<Category> topCategories = categoryMap.getOrDefault(0, new ArrayList<>());
%>

<nav class="menu-nav">
  <ul class="menu">
    <% for (Category top : topCategories) { %>
    <li class="dropdown">
      <a href="home.do?category=<%= top.getCategoryId() %>"><%= top.getName() %></a>

      <% List<Category> secondLevel = categoryMap.getOrDefault(top.getCategoryId(), new ArrayList<>()); %>
      <% if (!secondLevel.isEmpty()) { %>
      <ul class="submenu">
        <% for (Category second : secondLevel) { %>
        <li class="dropdown-sub">
          <a href="home.do?category=<%= second.getCategoryId() %>"><%= second.getName() %></a>

          <% List<Category> thirdLevel = categoryMap.getOrDefault(second.getCategoryId(), new ArrayList<>()); %>
          <% if (!thirdLevel.isEmpty()) { %>
          <ul class="submenu submenu-right">
            <% for (Category third : thirdLevel) { %>
            <li>
              <a href="home.do?category=<%= third.getCategoryId() %>"><%= third.getName() %> </a>
            </li>
            <% } %>
          </ul>
          <% } %>

        </li>
        <% } %>
      </ul>
      <% } %>

    </li>
    <% } %>
  </ul>
</nav>
