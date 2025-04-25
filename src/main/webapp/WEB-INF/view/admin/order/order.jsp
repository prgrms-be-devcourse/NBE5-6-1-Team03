<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/view/include/page.jsp" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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
    <!-- 주문 내역 리스트 -->
    <div class="section container" id="order-statistics">
        <h4 class="brown-text text-darken-2">주문 내역</h4>

        <%-- TODO 조회한 날짜가 표출되도록 --%>

        <h5>${date}</h5>
        <form action="/admin/orders" class="col s2" method="get">
            <div class="row">
                <div class="input-field">
                    <input type=text name="date" id="date" class="datepicker" required>
                    <label for="date">기준일자</label>
                    <button type="submit" class="waves-effect waves-light btn green darken-4">
                        <i class="material-icons left">refresh</i>새로고침
                    </button>
                </div>
            </div>
        </form>
        <table class="highlight">
            <thead>
            <tr>
                <th>번호</th>
                <th>이메일</th>
                <th>성명</th>
                <th>상품명</th>
                <th>총 수량</th>
                <th>최근 주문일시</th>
                <th>주문상태</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${orderInfoList}" var="order" varStatus="status">
                <tr>
                    <td>${status.count}</td>
                    <td>${order.orderUserEmail}</td>
                    <c:if test="${empty order.orderUserName}">
                        <td>비회원</td>
                    </c:if>
                    <c:if test="${not empty order.orderUserName}">
                        <td>${order.orderUserName}</td>
                    </c:if>
                    <td>${order.menuName}</td>
                    <td>${order.quantity}</td>
                    <td>${order.formattedOrderDate}</td>
                    <td>
                        ${order.status}
                        <c:if test="${order.status eq '주문접수'}">
                            <form action="/admin/orders/${order.orderId}" method="post" style="display: inline">
                                <sec:csrfInput/>
                                <input type="hidden" name="date" value="${param.date}">
                                <input type="hidden" name="status" value="발송완료">
                                <button type="submit" class="btn-small blue darken-1" style="margin-left: 10px">발송</button>
                            </form>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>


</main>
<%@include file="/WEB-INF/view/include/footer.jsp" %>
<script>
  document.addEventListener('DOMContentLoaded', function () {
    var elems = document.querySelectorAll('.datepicker');
    var instances = M.Datepicker.init(elems, {
      autoClose: true,
      format: 'yyyy-mm-dd',
      defaultDate: new Date(),
      yearRange: [1980, (new Date()).getFullYear()]
    });
  });

  function refresh() {

  }
</script>

</body>
</html>