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
    <div class="section container" id="order-statistics">
        <h4 class="brown-text text-darken-2">주문 내역</h4>

        <c:if test="${not empty msg}">
            <script>
              alert('${msg}');
            </script>
        </c:if>

        <h5>${date}</h5>
        <form action="/admin/orders" class="col s2" method="get">
            <div class="row">
                <div class="input-field">
                    <input type="text" name="date" id="date" class="datepicker" required>
                    <label for="date">기준일자</label>
                    <button type="submit" class="waves-effect waves-light btn green darken-4">
                        <i class="material-icons left">refresh</i>새로고침
                    </button>
                </div>
            </div>
        </form>

        <c:forEach items="${orderGroupList}" var="group">
            <div style="margin-top: 30px;">
                <h5 style="color: darkgreen">
                        ${group.orderUserEmail}
                    <medium>(${empty group.orderUserName ? '비회원' : group.orderUserName})</medium>
                </h5>
                <div style="display: flex; flex-direction: row-reverse ; align-items: center">
                    <c:if test="${group.items[0].status eq '주문접수'}">
                        <form action="/admin/orders/status" method="post" style="display: inline">
                            <sec:csrfInput/>
                            <input type="hidden" name="baseDate" value="${param.date}">
                            <input type="hidden" name="status" value="발송완료">
                            <input type="hidden" name="orderUserEmail" value="${group.orderUserEmail}">
                            <input type="hidden" name="orderDateTime" value="${group.items[0].orderDate}">
                            <button type="submit" class="btn-small blue darken-1" style="margin-right: 20px">발송</button>
                        </form>
                    </c:if>
                </div>

                <table class="highlight">
                    <thead>
                    <tr>
                        <th>주문일시</th>
                        <th>상품명</th>
                        <th>수량</th>
                        <th>주문상태</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${group.items}" var="item">
                        <tr>
                            <td>${item.formattedOrderDate}</td>
                            <td>${item.menuName}</td>
                            <td>${item.quantity}</td>
                            <td>${item.status}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <hr style="margin: 30px 0;">
        </c:forEach>

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