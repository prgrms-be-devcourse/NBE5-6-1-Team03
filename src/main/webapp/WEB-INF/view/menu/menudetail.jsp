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
            <th>사진</th>
            <th>이름</th>
            <th>가격</th>
            <th>수량</th>
            <th>설명</th>

        </tr>

        <c:forEach var="menu" items="${menus}">
            <tr>
<%--                <td><img src="${menu.savePath}" width="100px"></td>--%>
                <td>사진 구현 예정...</td>
                <td>${menu.name}</td>
                <td>${menu.price}원</td>
                <td>${menu.amount}원</td>
                <td>${menu.info}원</td>


            </tr>

        </c:forEach>


    </table>
    
    <a href="/menu/detail">
        <button type="button">임시, 상세페이지 가기</button>
    </a>
    <a href="/">
        <button type="button">임시, 홈으로 가기</button>
    </a>
    <a href="/menu">
        <button type="button">임시, 메뉴페이지 가기</button>
    </a>

</main>

<%@include file="/WEB-INF/view/include/footer.jsp" %>
</body>

</html>