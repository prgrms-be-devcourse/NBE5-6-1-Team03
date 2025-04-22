<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/view/include/page.jsp" %>
<!DOCTYPE html>
<html>

<head>
    <h2>커피 메뉴</h2>
    <%@include file="/WEB-INF/view/include/static.jsp" %>
</head>


<body>
<%@include file="/WEB-INF/view/include/header.jsp" %>

    <h2>메뉴 상세 정보</h2>
        <p><strong>이름:</strong> ${menu.name}</p>
        <p><strong>가격:</strong> ${menu.price}원</p>
        <p><strong>설명:</strong> ${menu.description}</p>
        <p><strong>원산지:</strong> ${menu.origin}</p>
<%@include file="/WEB-INF/view/include/footer.jsp" %>
</body>

</html>