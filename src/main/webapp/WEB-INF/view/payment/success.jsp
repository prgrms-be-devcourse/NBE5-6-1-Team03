<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %><html>
<%@include file="/WEB-INF/view/include/page.jsp" %>
<html lang='ko'>
<head>
    <title>결제 완료</title>
    <%@ include file="/WEB-INF/view/include/static.jsp" %>

</head>
<body>
<%@ include file="/WEB-INF/view/include/header.jsp" %>
<main class="container">
    <h2>결제가 완료되었습니다</h2>

    <div class="button-box">
        <a class="btn" href="/">메인으로 돌아가기</a>
        <a class="btn" href="/order/check">주문 확인하기</a>
    </div>
</main>
<%@ include file="/WEB-INF/view/include/footer.jsp" %>
</body>
</html>

