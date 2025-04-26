<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/view/include/page.jsp" %>
<html>
<head>
    <title>결제 실패</title>
    <%@include file="/WEB-INF/view/include/static.jsp" %>
</head>
<link rel="stylesheet" href="../../../../assets/css/footer.css">
<body>
<%@include file="/WEB-INF/view/include/header.jsp" %>
<main class="container">
    <h2>결제가 실패했습니다</h2>
    <p>죄송합니다. 결제 처리 중 오류가 발생했습니다.</p>
    <p>다시 시도해 주세요</p>

    <!-- 재고 부족 메시지 출력 -->
    <c:if test="${not empty errorMessage}">
        <p><strong>${errorMessage}</strong></p>
    </c:if>

    <a href="/">메인으로 돌아가기</a>
</main>
<%@include file="/WEB-INF/view/include/footer.jsp" %>
</body>

</html>
