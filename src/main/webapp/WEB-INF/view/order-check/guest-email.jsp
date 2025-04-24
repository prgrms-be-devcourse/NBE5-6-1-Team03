<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/view/include/page.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>회원 주문 확인</title>
    <%@include file="/WEB-INF/view/include/static.jsp" %>
</head>
<body>
<%@include file="/WEB-INF/view/include/header.jsp" %>
<main>
    <div class="section container" id="order-statistics">
        <div class="row">
            <h2 class="brown-text text-darken-2 col s10">주문 목록</h2>
        </div>

        <div class="row">
            <table class="highlight centered responsive-table">
                <thead>
                <tr>
                    <th>주문 ID</th>
                    <th>주문 수량</th>
                    <th>총 가격</th>
                    <th>상세보기</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="order" items="${orders}">
                    <tr>
                        <td>${order.id}</td>
                        <td>${order.totalQuantity}개</td>
                        <td>${order.totalPrice}개</td>
                        <td>
                            <a href="${context}/order-check?id=${order.id}">
                                <button type="button" class="btn small brown lighten-1">상세보기</button>
                            </a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>


</main>
<%@include file="/WEB-INF/view/include/footer.jsp" %>
</body>
</html>