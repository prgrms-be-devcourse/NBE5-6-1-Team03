<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/view/include/page.jsp" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
    <c:if test="${not empty msg}">
        <script>
          alert('${msg}');
        </script>
    </c:if>

    <!-- 소개 섹션 -->
    <div class="section container" id="order-detail">
        <h4 class="brown-text text-darken-2">주문 상세</h4>
        <h5>주문 번호 ${orderGroup.items[0].orderId} (${orderGroup.items[0].formattedOrderDate})</h5>
    </div>

    <div class="section container" id="order">
        <div class="card white lighten-4 z-depth-1"
             style="margin-top: 0px; margin-bottom: 5px; border-radius: 12px;">
            <div class="card-content" style="padding: 20px 24px;">

                <h5 class="brown-text text-darken-2">주문자 정보</h5>
                <table class="responsive-table">
                    <tbody>
                    <tr>
                        <th>성명</th>
                        <td>
                            <c:if test="${empty orderGroup.orderUserName}">
                                (비회원)
                            </c:if>
                            <c:if test="${not empty orderGroup.orderUserName}">
                                ${orderGroup.orderUserName}
                            </c:if>
                        </td>
                    </tr>
                    <tr>
                        <th>이메일</th>
                        <td>${orderGroup.orderUserEmail}</td>
                    </tr>
                    <tr>
                        <th>주소</th>
                        <td>${orderGroup.orderUserAddress}</td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>

    <div class="section container" id="sales-statistics">
        <div class="card white lighten-4 z-depth-1"
             style="margin-top: 0px; margin-bottom: 30px; border-radius: 12px;">
            <div class="card-content" style="padding: 20px 24px;">
                <h5 class="brown-text text-darken-2">결제 정보</h5>

                <table class="responsive-table">
                    <thead class="grey lighten-5">
                    <tr>
                        <th>상품명</th>
                        <th>수량</th>
                        <th>가격</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${orderGroup.items}" var="item">
                        <tr>
                            <td>${item.menuName}</td>
                            <td>${item.quantity}</td>
                            <td><fmt:formatNumber value="${item.price}" pattern="#,###"/>원</td>

                        </tr>
                    </c:forEach>
                    <tr class="grey lighten-5">
                        <td style="font-weight: bold;">총 결제금액</td>
                        <td></td>
                        <td style="font-weight: bold;"><fmt:formatNumber
                                value="${orderGroup.totalPrice}" pattern="#,###"/>원
                        </td>
                    </tr>
                    </tbody>
                </table>

                <br>
                <h5 class="brown-text text-darken-2">주문 상태</h5>

                <form method="post" action="/admin/orders/${orderGroup.items[0].orderId}">
                    <sec:csrfInput/>
                    <div class="input-field col s12">
                        <select name="orderStatus">
                            <option value="${orderGroup.items[0].status}" disabled
                                    selected>${orderGroup.items[0].statusDesc}</option>
                            <c:if test="${orderGroup.items[0].status != 'ACCEPTED'}">
                                <option value="ACCEPTED">주문접수</option>
                            </c:if>
                            <c:if test="${orderGroup.items[0].status != 'RELEASE'}">
                                <option value="RELEASE">상품발송</option>
                            </c:if>
                            <c:if test="${orderGroup.items[0].status != 'ON_THE_WAY'}">
                                <option value="ON_THE_WAY">배송중</option>
                            </c:if>
                            <c:if test="${orderGroup.items[0].status != 'DELIVERED'}">
                                <option value="DELIVERED">배송완료</option>
                            </c:if>
                            <c:if test="${orderGroup.items[0].status != 'REJECTED'}">
                                <option value="REJECTED">주문거절</option>
                            </c:if>
                            <c:if test="${orderGroup.items[0].status != 'EXCHANGE'}">
                                <option value="EXCHANGE">교환처리</option>
                            </c:if>
                        </select>
                        <label></label>
                    </div>
                    <input type="hidden" name="orderDateTime" value="${param.orderDateTime}">
                    <div style="display: flex; justify-content: end">
                        <button type="submit"
                                class="waves-effect waves-light btn-small green darken-4"
                                style="margin-right: 0.5rem; height: 45px; line-height: 45px">
                            <i class="material-icons left">save</i>저장
                        </button>
                        <button onclick="location.href='/admin/orders?orderDateTime=${param.orderDateTime}'"
                                type="reset"
                                class="waves-effect waves-light btn-small grey darken-2"
                                style="height: 45px; line-height: 45px">
                            <i class="material-icons left">cancel</i>뒤로가기
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>

</main>
<%@include file="/WEB-INF/view/include/footer.jsp" %>

<script>
  document.addEventListener('DOMContentLoaded', function () {
    var elems = document.querySelectorAll('select');
    var instances = M.FormSelect.init(elems);
  });
</script>
</body>
</html>