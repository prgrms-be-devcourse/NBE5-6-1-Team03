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
        <div style="font-size: 1rem; color: #757575">기준일: ${date}</div>
        <c:if test="${not empty msg}">
            <script>
              alert('${msg}');
            </script>
        </c:if>

        <form action="/admin/orders" method="get"
              style="margin-top: 20px; display: flex; gap: 12px; align-items: center;">
            <div class="input-field" style="margin: 0;">
                <input type="text" name="orderDate" id="orderDate" class="datepicker" required
                       style="border-bottom: 1px solid; box-shadow: none; font-size: 1.1rem;">
                <label for="orderDate" class="active">기준일 선택
                </label>
            </div>
            <button type="submit"
                    class="btn-small green darken-3 waves-effect waves-light tooltipped"
                    data-position="bottom" data-tooltip="선택한 날짜로 조회합니다"
                    style="height: 45px; line-height: 45px;">
                <i class="material-icons left" style="margin-right: 5px;">search</i>조회
            </button>
        </form>

        <c:forEach items="${orderGroupList}" var="group">
            <div class="card white lighten-4 z-depth-1"
                 style="margin-top: 30px; margin-bottom: 30px; border-radius: 12px;">
                <div class="card-content" style="padding: 20px 24px;">
                    <div style="display: flex; justify-content: space-between; align-items: center;">
                        <div>
                <span style="font-size: 1.4rem; font-weight: 600; color: #2e7d32;">
                        ${group.orderUserEmail}
                </span><br>
                            <small style="font-size: 1rem; color: #757575;">
                                (${empty group.orderUserName ? '비회원' : group.orderUserName})
                            </small>
                        </div>
                        <div>
                            <c:if test="${group.items[0].status eq 'ACCEPTED'}">
                                <form action="/admin/orders/status" method="post"
                                      style="display: inline;">
                                    <sec:csrfInput/>
                                    <input type="hidden" name="baseDate" value="${param.date}">
                                    <input type="hidden" name="status" value="RELEASE">
                                    <input type="hidden" name="orderUserEmail"
                                           value="${group.orderUserEmail}">
                                    <input type="hidden" name="orderDateTime"
                                           value="${group.items[0].orderDateTime}">
                                    <button type="submit"
                                            class="btn-floating btn-small blue darken-2 tooltipped"
                                            data-position="left" data-tooltip="그룹 발송"
                                            style="margin-right: 10px;">
                                        <i class="material-icons">send</i>
                                    </button>
                                </form>
                            </c:if>
                        </div>
                    </div>

                    <table class="highlight centered" style="margin-top: 20px; font-size: 0.95rem;">
                        <thead class="grey lighten-5">
                        <tr>
                            <th>주문일시</th>
                            <th>상품명</th>
                            <th>수량</th>
                            <th>주문상태</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${group.items}" var="item">
                            <tr onclick="location.href='/admin/orders/${item.orderId}?orderDateTime=${item.orderDateTime}'"
                                style="cursor: pointer;">
                                <td>${item.formattedOrderDate}</td>
                                <td>${item.menuName}</td>
                                <td>${item.quantity}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${item.status eq 'ACCEPTED'}">
                                            <div class="chip blue lighten-4 blue-text text-darken-4">
                                                주문접수
                                            </div>
                                        </c:when>
                                        <c:when test="${item.status eq 'RELEASE'}">
                                            <div class="chip green lighten-4 green-text text-darken-4">
                                                상품발송
                                            </div>
                                        </c:when>
                                        <c:when test="${item.status eq 'ERROR'}">
                                            <div class="chip yellow lighten-4 yellow-text text-darken-4">
                                                확인필요
                                            </div>
                                        </c:when>
                                        <c:otherwise>
                                            <div class="chip grey lighten-2 grey-text text-darken-2">${item.statusDesc}</div>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
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