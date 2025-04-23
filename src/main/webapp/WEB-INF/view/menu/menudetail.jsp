<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/view/include/page.jsp" %>
<!DOCTYPE html>
<html>

<head>
    <title>메뉴 목록</title>
    <%@include file="/WEB-INF/view/include/static.jsp" %>
</head>


<body>
<main>
    <%@include file="/WEB-INF/view/include/header.jsp" %>
    <h2>커피 메뉴</h2>
    <table border="1">

        <tr>
            <th>이름</th>
            <th>가격</th>
            <th>수량</th>
            <th>설명</th>

        </tr>

        <c:forEach var="menu" items="${menus}">
            <tr>
                <td>${menu.name}</td>
                <td>${menu.price}원</td>
                <td>${menu.amount}원</td>
                <td>${menu.info}원</td>


            </tr>

        </c:forEach>


    </table>

</main>

<%@include file="/WEB-INF/view/include/footer.jsp" %>
</body>

</html>