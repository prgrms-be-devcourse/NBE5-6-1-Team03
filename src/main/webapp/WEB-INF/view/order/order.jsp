<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/view/include/page.jsp" %>

<html>
<head>
    <title>Grepp</title>
    <%@include file="/WEB-INF/view/include/static.jsp" %>
</head>
<body>
<%@include file="/WEB-INF/view/include/header.jsp" %>
<main>
    <div class="container">
        <div class="menu-list">
            <h2>메뉴</h2>
            <c:forEach var="menu" items="${menuList}">
                <div class="menu-item" data-id="${menu.id}" data-name="${menu.name}" data-price="${menu.price}">
                    <span>${menu.name}</span>
                    <span>${menu.price}원</span>
                    <button onclick="addToOrder(this)">추가</button>
                </div>
            </c:forEach>
        </div>

        <div class="order-summary">
            <h2>주문 요약</h2>
            <table id="orderTable">
                <thead>
                <tr><th>메뉴</th><th>수량</th><th>가격</th><th>삭제</th></tr>
                </thead>
                <tbody></tbody>
            </table>

            <div class="customer-info">
                <h3>주문자 정보</h3>
                <input type="email" name="email" placeholder="이메일" value="${member.email}" />
                <input type="text" name="address" placeholder="주소" value="${member.address}" />
            </div>

            <form id="orderForm" action="${pageContext.request.contextPath}/order/submit" method="post">
                <input type="hidden" name="orderJson" id="orderJson" />
                <button type="submit">주문하기</button>
            </form>
        </div>
    </div>
</main>

<%@include file="/WEB-INF/view/include/footer.jsp" %>
</body>
</html>
