<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/view/include/page.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>Cafe Grid & Circle</title>
    <%@include file="/WEB-INF/view/include/static.jsp" %>
</head>
<link rel="stylesheet" href="../../../../assets/css/footer.css">
<body>
<%@include file="/WEB-INF/view/include/header-admin.jsp" %>

<main>
    <!-- 주문 내역 리스트 -->
    <div class="section container" id="order-statistics">
        <h4 class="brown-text text-darken-2">주문 내역</h4>
        <h5>2025.04.22.</h5>
        <p>리스트로 쭈루룩 표시. 무한 스크롤. 전일 14시 ~ 금일 14시 기준</p>
        <br>
        <p>날짜 선택</p>
        <p>발송 여부 함께 표시</p>
        <table class="highlight">
            <thead>
            <tr>
                <th>주문일자</th>
                <th>주문자명</th>
                <th>주문상품</th>
                <th>주문수량</th>
                <th>주문상태</th>
            </tr>
            </thead>

            <tbody>
            <c:forEach items="${orderList}" var="order">
                <tr>
                    <td>${order.createdAt}</td>
                    <td>${order.name}</td>
                    <td>${order.menu.name}</td>
                    <td>${order.menu.quantity}</td>
                    <td>${order.status}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>





</main>
<%@include file="/WEB-INF/view/include/footer.jsp" %>

</body>
</html>