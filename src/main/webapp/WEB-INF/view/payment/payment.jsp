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

        <h3>주문자 정보</h3>
        이메일: ${paymentForm.userEmail}<br>
        주소: ${paymentForm.userAddress}<br>

        <h3>주문 내역</h3>
        <c:forEach var="item" items="${menuItems}" varStatus="status">
            <input type="hidden" name="menuItems[${status.index}].id" value="${item.menuId}" />
            <input type="hidden" name="menuItems[${status.index}].quantity" value="${item.quantity}" />
            상품명: ${item.menuName}, 수량: ${item.quantity}, 가격: ${item.price}원<br>
        </c:forEach>

        <br>
        총 금액: <b>${totalPrice}</b>원<br><br>
        <button type="submit">결제하기</button>
    </form>
</main>

<%@include file="/WEB-INF/view/include/footer.jsp" %>

</body>
</html>
