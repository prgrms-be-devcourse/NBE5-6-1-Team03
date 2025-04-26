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
<main class="container" style="max-width: 800px;">
    <div class="card">
        <div class="card-content">
            <div class="fail center-align">
                <h2 class="brown-text text-darken-2">결제 실패</h2>
                <div style="text-align: center;">
                    <p style="margin: 0; font-size: 20px;">주문처리에 실패하여</p>
                    <p style="font-size: 20px;">주문을 완료하지 못하였습니다.</p>
                </div>

                <!-- 재고 부족 메시지 출력 -->
                <c:if test="${not empty errorMessage}">
                    <p style="font-size: 24px; text-align: center;"><strong>${errorMessage}</strong></p>
                </c:if>
            </div>
            <br>

            <div class="btn-container center-align">
                <button class="btn-large brown darken-2 waves-effect waves-light" type="button">
                    <a href="/" class="white-text">홈으로 돌아가기</a>
                </button>
            </div>
        </div>
    </div>
</main>
<%@include file="/WEB-INF/view/include/footer.jsp" %>
</body>
</html>
