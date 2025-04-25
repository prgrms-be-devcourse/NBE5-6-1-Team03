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
    <h3>주문한 메뉴</h3>
    <table>
        <thead>
        <tr>
            <th>메뉴명</th>
            <th>수량</th>
            <th>단가</th>
            <th>금액</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="item" items="${menuItems}">
            <tr>
                <td>${item.name}</td>
                <td>${item.quantity}</td>
                <td>${item.price}원</td>
                <td>${item.price * item.quantity}원</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <p class="total">총 결제 금액: <strong>${totalPrice}원</strong></p>

    <div class="btn">
        <a href="/">홈으로 가기</a>
    </div>
</main>
</body>
</html>

