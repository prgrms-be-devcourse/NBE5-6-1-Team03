<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/view/include/page.jsp" %>
<html>
<head>
    <title>결제 페이지</title>
    <%@include file="/WEB-INF/view/include/static.jsp" %>
</head>
<body>
<%@include file="/WEB-INF/view/include/header.jsp" %>

<main class="container">
    <form id="payForm" action="/payment/complete" method="post">
        <!-- 주문자 정보 -->
        이름: <input name="userName"><br>
        이메일: <input name="userEmail"><br>
        주소: <input name="userAddress"><br>

        <!-- 상품 정보 출력 -->
        <c:forEach var="item" items="${menuItems}" varStatus="status">
            <input type="hidden" name="menuItems[${status.index}].menuId" value="${item.menuId}" />
            <input type="hidden" name="menuItems[${status.index}].quantity" value="${item.quantity}" />
            상품명: ${item.menuName}, 수량: ${item.quantity}, 가격: ${item.price} <br>
        </c:forEach>

        총 금액: <b>${totalPrice}</b>원<br>
        <button type="button" onclick="confirmPayment()">결제하기</button>
    </form>

</main>
<%@include file="/WEB-INF/view/include/footer.jsp" %>

</body>
</html>
