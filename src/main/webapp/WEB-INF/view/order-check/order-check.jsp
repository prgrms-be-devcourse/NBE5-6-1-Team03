<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/view/include/page.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>주문 확인</title>
    <%@include file="/WEB-INF/view/include/static.jsp" %>
</head>
<body>
<%@include file="/WEB-INF/view/include/header.jsp" %>
<main>
    <div class="container">
        <h2>주문 확인</h2>
        <div style="text-align: center; margin-top: 30px;">
            <button onclick="location.href='${context}/order-check/member'">회원 주문 확인</button>
            <button onclick="location.href='${context}/order-check/guest'">비회원 주문 확인</button>
        </div>
    </div>
</main>
<%@include file="/WEB-INF/view/include/footer.jsp" %>

</body>
</html>