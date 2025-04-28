<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<%@include file="/WEB-INF/view/include/page.jsp" %>
<html lang='ko'>
<head>
    <title>Cafe Grid & Circle</title>
    <%@ include file="/WEB-INF/view/include/static.jsp" %>
    <style>
      .card {
        margin-top: 50px;
      }
    </style>
</head>
<body>
<%@ include file="/WEB-INF/view/include/header.jsp" %>
<main class="container" style="max-width: 800px;">
    <div class="card">
        <div class="card-content">
            <h2 class="brown-text text-darken-2 center-align">🎉 결제 완료 🎉</h2>


            <h4 class="brown-text text-darken-2">주문 상세</h4>
            <ul class="collection" style="margin: 0; padding: 0; list-style-type: none;">
                <li class="collection-item" style="border: none;"><strong>주문번호: </strong>${orderId}</li>
                <li class="collection-item" style="border: none;"><strong>이메일: </strong>${order.userEmail}</li>
                <li class="collection-item" style="border: none;"><strong>주소: </strong>${order.userAddress}</li>
                <li class="collection-item" style="border: none;"><strong>주문 날짜: </strong>${createdAt}</li>
            </ul>

            <div class="row">
                <table class="highlight centered">
                    <thead>
                    <tr>
                        <th>메뉴명</th>
                        <th>수량</th>
                        <th>단가</th>
                        <th>금액</th>
                        <td></td>
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
            </div>
            <div class="total right-align">
                <h5 class="brown-text text-darken-3">총 결제 금액: ${totalPrice}원</h5>
            </div>

            <div class="btn-container center-align">
                <button class="btn-large brown darken-2 waves-effect waves-light" type="submit">
                    <a href="/" class="white-text">홈으로 가기</a>
                </button>
            </div>

        </div>
    </div>

</main>
<%@include file="/WEB-INF/view/include/footer.jsp" %>
</body>
</html>
