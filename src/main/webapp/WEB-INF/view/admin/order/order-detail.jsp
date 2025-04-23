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
    <!-- 소개 섹션 -->
    <div class="section container" id="order-detail">
        <h4 class="brown-text text-darken-2">주문 상세</h4>
        <p>2025.4.22. 주문</p>
        <p>주문번호</p>
        <p>사진</p>
        <p>상품명</p>
        <p>금액</p>
        <p>수량</p>
    </div>

    <div class="section container" id="order-">
        <h4 class="brown-text text-darken-2">받는사람 정보</h4>
        <p>받는 사람: 박종욱</p>
        <p>연락처: 010-1111-2222</p>
        <p>받는 주소: (11111) 서울 서초구 반포대로 45</p>
        <p>배송요청사항: 새벽: 문 앞</p>
    </div>

    <div class="section container" id="sales-statistics">
        <h4 class="brown-text text-darken-2">결제 정보</h4>
        <p>결제수단: 신용카드</p>
        <p>총 상품가격: 10,000원</p>
        <p>할인금액: 100원</p>
        <p>배송비: 2,500원</p>
        <p>총 결제금액: 12,400원</p>
    </div>

</main>
<%@include file="/WEB-INF/view/include/footer.jsp" %>

</body>
</html>