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
  <div class="section container" id="order-statistics">
    <div class="row">
      <h2 class="brown-text text-darken-2 col s10"
          style="font-weight: 700; font-size: 28px; margin-top : 40px; margin-bottom: 40px;">
        주문 상세 정보
      </h2>
    </div>

    <div class="order-summary">
      <p>주문번호: ${orderId}</p>
      <c:if test="${not empty orderHeader}">
        <p>총 주문 금액: ${orderHeader.totalPrice}원</p>
        <p>주문 상태: ${orderHeader.orderStatus}</p>
        <p>배송 주소: ${orderHeader.userAddress}</p>
      </c:if>

      <table class="highlight centered responsive-table">
        <thead>
        <tr>
          <th>순서</th>
          <th>상품</th>
          <th>가격</th>
          <th>개수</th>
          <th>합계</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="detail" items="${orderDetails}" varStatus="status">
          <tr>
            <td>${status.index + 1}</td>
            <td>${detail.menuName}</td>
            <td>${detail.menuPrice}</td>
            <td>${detail.quantity}</td>
            <td>${detail.totalPricePerItem}</td>
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