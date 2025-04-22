<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/view/include/page.jsp" %>
<!DOCTYPE html>
<html>

<head>
    <title>메뉴 목록</title>
    <%@include file="/WEB-INF/view/include/static.jsp" %>
</head>


<body>
<%@include file="/WEB-INF/view/include/header.jsp" %>
<h2>커피 메뉴</h2>
<table border="1">
    <tr>
        <th>이름</th>
        <th>가격</th>
        <th>상세</th>
    </tr>
    <c:forEach var="menu" items="${menus}">
        <tr>
            <td>${menu.name}</td>
            <td>${menu.price}원</td>
            <td><a href="/menu/detail?id=${menu.id}">상세보기</a></td>
        </tr>
    </c:forEach>
</table>
<%@include file="/WEB-INF/view/include/footer.jsp" %>
</body>

</html>