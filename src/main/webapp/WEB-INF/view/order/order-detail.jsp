<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/view/include/page.jsp" %>
<html>
<head>
    <title>Cafe Grid & Circle</title>
    <%@include file="/WEB-INF/view/include/static.jsp" %>
</head>
<body>
<%@include file="/WEB-INF/view/include/header.jsp" %>

<main class="container" style="max-width: 800px;">
    <form:form modelAttribute="paymentForm" action="${context}/payment" method="post" class="col s12">
        <div class="card">
            <div class="card-content">
                <h4 class="brown-text text-darken-2">주문자 정보</h4>
                <div class="section">
                    <form:input type="hidden" path="userId" value="${empty orderDetails.userId ? null : orderDetails.userId}" />
                    <form:input type="hidden" path="userEmail" value="${orderDetails.userEmail}"/>
                    <form:input type="hidden" path="userAddress" value="${orderDetails.userAddress}"/>
                    <p><strong>이메일:</strong> ${orderDetails.userEmail}</p>
                    <p><strong>주소:</strong> ${orderDetails.userAddress}</p>
                </div>

                <h4 class="brown-text text-darken-2">주문 내역</h4>
                <ul class="collection">
                    <c:forEach var="item" items="${orderDetails.menuList}">
                        <form:input type="hidden" path="menuId" value="${item.id}"/>
                        <form:input type="hidden" path="quantity" value="${item.quantity}"/>

                        <li class="collection-item">
                            <div>
                                <strong>${item.name}</strong>
                                <span class="right">
                                ${item.quantity}개 × ${item.price}원 = <strong>${item.quantity * item.price}원</strong>
                            </span>
                            </div>
                        </li>
                    </c:forEach>
                </ul>

                <div class="section right-align" style="margin-top: 20px;">
                    <h5 class="brown-text text-darken-3">총 금액: ${totalPrice}원</h5>
                </div>

                <div class="section center-align">
                    <button class="btn-large brown darken-2 waves-effect waves-light" type="submit">
                        결제하기
                    </button>
                </div>
            </div>
        </div>
    </form:form>

</main>

<%@include file="/WEB-INF/view/include/footer.jsp" %>
</body>
</html>