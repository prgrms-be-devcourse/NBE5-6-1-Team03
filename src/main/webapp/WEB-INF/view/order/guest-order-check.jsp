<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/view/include/page.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>비회원 주문 확인</title>
    <%@include file="/WEB-INF/view/include/static.jsp" %>
</head>
<body>
<%@include file="/WEB-INF/view/include/header.jsp" %>
<main>
    <div class="container">
        <h2>비회원 주문 확인</h2>
        <div style="text-align: center; margin-top: 30px;">
            <form action="${pageContext.request.contextPath}/order/check/guest" method="post">
                <label for="orderId">주문 번호</label>
                <input type="text" id="orderId" name="orderId" required />
                <button type="submit">주문 확인</button>
            </form>
        </div>
        <div class="order-details" style="margin-top: 30px;">
            <!-- 주문 상세 정보가 출력되는 영역 -->
            <c:if test="${not empty orderDetails}">
                <h3>주문 상세</h3>
                <p>주문 번호: ${orderDetails.orderId}</p>
                <p>주문자 이름: ${orderDetails.userName}</p>
<%--                <p>주문 항목: ${orderDetails.items}</p>--%>
                <p>주문 상태: ${orderDetails.status}</p>
            </c:if>
            <c:if test="${empty orderDetails}">
                <p>해당 주문을 찾을 수 없습니다.</p>
            </c:if>
        </div>
    </div>
</main>
<%@include file="/WEB-INF/view/include/footer.jsp" %>
</body>
</html>