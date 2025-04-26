<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/view/include/page.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>Cafe Grid & Circle</title>
    <%@include file="/WEB-INF/view/include/static.jsp" %>
    <style>
      .date-range {
        display: flex;
        gap: 20px;
        margin-bottom: 30px;
      }
    </style>
</head>
<link rel="stylesheet" href="../../../assets/css/footer.css">
<body>
<%@include file="/WEB-INF/view/include/header-admin.jsp" %>

<main>
    <!-- 대시보드 차트 -->
    <div class="section container" id="order-statistics">
        <h4 class="brown-text text-darken-2">주문 통계</h4>

        <form action="/admin" method="get">
            <div class="date-range">
                <div class="input-field col s5">
                    <input type="text" name="startDate" id="startDate" class="datepicker">
                    <label for="startDate">시작일</label>
                </div>

                <div class="input-field col s5">
                    <input type="text" name="endDate" id="endDate" class="datepicker">
                    <label for="endDate">종료일</label>
                </div>
                <button id="applyBtn" type="submit"
                        class="waves-effect waves-light btn green darken-4">
                    <i class="material-icons left">refresh</i>조회
                </button>
            </div>
        </form>

        <div class="brown-text text-darken-2">기준일: ${startDate} ~ ${endDate}</div>
        <table>
            <thead>
            <tr>
                <th>순위</th>
                <th>상품명</th>
                <th>수량</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${orderStatsList}" var="orderStats" varStatus="status">
                <tr>
                    <td>${status.count}</td>
                    <td>${orderStats.name}</td>
                    <td>${orderStats.totalQuantity}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>


    </div>

    <div class="section container" id="sales-statistics">
        <h4 class="brown-text text-darken-2">매출 통계</h4>

        <div class="brown-text text-darken-2">기준일: ${startDate} ~ ${endDate}</div>
        <table>
            <thead>
            <tr>
                <th>구분</th>
                <th>일자</th>
                <th>매출액</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${orderSalesList}" var="orderSales" varStatus="status">
                <tr>
                    <td>${status.count}</td>
                    <td>${orderSales.orderDate}</td>
                    <td>${orderSales.totalAmount}</td>
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

  const startDatePicker = document.querySelector('#startDate');
  const endDatePicker = document.querySelector('#endDate');

  document.querySelector('#applyBtn').addEventListener('click', function () {
    const startDate = startDatePicker.value;
    const endDate = endDatePicker.value;

    if (startDate && endDate) {
      // 선택된 날짜가 유효한지 확인
      const start = new Date(startDate);
      const end = new Date(endDate);

      if (start > end) {
        M.toast({html: '종료일은 시작일보다 이후여야 합니다.', classes: 'red'});
        return;
      }

    } else {
      M.toast({html: '시작일과 종료일을 모두 선택해주세요.', classes: 'red'});
    }
  });
</script>

</body>
</html>