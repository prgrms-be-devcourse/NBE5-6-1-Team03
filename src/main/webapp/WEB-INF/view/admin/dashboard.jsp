<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/view/include/page.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>Cafe Grid & Circle</title>
    <%@include file="/WEB-INF/view/include/static.jsp" %>
</head>
<link rel="stylesheet" href="../../../assets/css/footer.css">
<body>
<%@include file="/WEB-INF/view/include/header-admin.jsp" %>

<main>
    <!-- 대시보드 차트 -->
    <div class="section container" id="order-statistics">
        <h4 class="brown-text text-darken-2">주문 통계</h4>
        <p>날짜 지정하고 검색 누르면 일자별 매출량 막대 그래프로 표시</p>
    </div>

    <div class="section container" id="sales-statistics">
        <h4 class="brown-text text-darken-2">매출 통계</h4>
        <p>날짜 지정하고 검색 누르면 일자별, 월별 매출 막대 그래프로 표시</p>
    </div>

</main>
<%@include file="/WEB-INF/view/include/footer.jsp" %>

</body>
</html>