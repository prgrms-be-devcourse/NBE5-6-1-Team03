<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/view/include/page.jsp" %>
<html>
<head>
    <title>결제 실패</title>
    <%@include file="/WEB-INF/view/include/static.jsp" %>
</head>
<body>
<%@include file="/WEB-INF/view/include/header.jsp" %>
<main class="container">
    <h2>결제가 실패했습니다</h2>

    <p>문제가 발생하여 결제가 정상적으로 처리되지 않았습니다.</p>
    <p>다시 시도해 주세요</p>

    <hr />

    <a href="/">메인으로 돌아가기</a>
</main>
<%@include file="/WEB-INF/view/include/footer.jsp" %>
</body>

</html>
