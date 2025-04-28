<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/view/include/page.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>Cafe Grid & Circle</title>
    <%@include file="/WEB-INF/view/include/static.jsp" %>
    <!-- Chart.js 라이브러리 추가 -->
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<%--    <script src="https://cdn.jsdelivr.net/npm/chartjs-plugin-datalabels"></script>--%>
    <script src="https://cdn.jsdelivr.net/npm/chartjs-plugin-datalabels@2"></script>
    <style>
      .date-range {
        display: flex;
        gap: 20px;
        margin-bottom: 30px;
      }

      .chart-container {
        position: relative;
        height: 300px;
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
        <h4 class="brown-text text-darken-2">대시보드</h4>
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

        <h5 class="brown-text text-darken-2">주문 통계</h5>

        <div class="brown-text text-darken-2">기준일: ${startDate} ~ ${endDate}</div>

        <!-- 상품별 판매량 차트 -->
        <div class="chart-container">
            <canvas id="orderStatsChart"></canvas>
        </div>

        <table>
            <thead>
            <tr>
                <th>순위</th>
                <th>상품명</th>
                <th>판매량</th>
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
            <tr>
                <td style="font-weight: bold;">총 판매량</td>
                <td></td>
                <td id="statsSum"  style="font-weight: bold;"></td>
            </tr>
            </tbody>
        </table>
    </div>

    <div class="section container" id="sales-statistics">
        <h5 class="brown-text text-darken-2">매출 통계</h5>

        <div class="brown-text text-darken-2">기준일: ${startDate} ~ ${endDate}</div>

        <!-- 일자별 매출액 차트 -->
        <div class="chart-container">
            <canvas id="salesChart"></canvas>
        </div>

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
                    <td>${orderSales.totalAmount}원</td>
                </tr>
            </c:forEach>
            <tr>
                <td style="font-weight: bold;">총 매출액</td>
                <td></td>
                <td id="salesSum"  style="font-weight: bold;"></td>
            </tr>
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

    // 상품별 판매량 차트
    const orderStatsChart = document.getElementById('orderStatsChart').getContext('2d');

    const productNames = [];
    const productQuantities = [];
    let statsSum = 0;

    <c:forEach items="${orderStatsList}" var="orderStats">
    productNames.push("${orderStats.name}");
    productQuantities.push(${orderStats.totalQuantity});
    statsSum += ${orderStats.totalQuantity};
    </c:forEach>

    document.querySelector('#statsSum').innerText = statsSum + '개';


    new Chart(orderStatsChart, {
      type: 'doughnut',
      data: {
        labels: productNames,
        datasets: [{
          label: '상품별 판매량',
          data: productQuantities,
          backgroundColor: [
            'rgba(255, 99, 132, 0.7)',
            'rgba(54, 162, 235, 0.7)',
            'rgba(255, 206, 86, 0.7)',
            'rgba(75, 192, 192, 0.7)',
            'rgba(153, 102, 255, 0.7)',
            'rgba(255, 159, 64, 0.7)',
          ],
          borderColor: [
            'rgba(255, 99, 132, 1)',
            'rgba(54, 162, 235, 1)',
            'rgba(255, 206, 86, 1)',
            'rgba(75, 192, 192, 1)',
            'rgba(153, 102, 255, 1)',
            'rgba(255, 159, 64, 1)',
          ],
          borderWidth: 1
        }]
      },
      options: {
        responsive: true,
        maintainAspectRatio: false,
        plugins: {
          title: {
            display: true,
            text: '상품별 판매량',
            font: {
              size: 18
            }
          },
          legend: {
            display: true,
            position: 'right'
          },
          datalabels: {
            color: '#fff', // 퍼센트 글자색
            formatter: (value, context) => {
              const total = context.chart.data.datasets[0].data.reduce((a, b) => a + b, 0);
              const percentage = (value / total * 100).toFixed(1);
              return percentage +'%';
            },
            font: {
              weight: 'bold',
              size: 14
            }
          }
        }
      },
      plugins: [ChartDataLabels]
    });

    // 일자별 매출액 차트
    const salesChart = document.getElementById('salesChart').getContext('2d');

    const orderDates = [];
    const salesAmounts = [];

    let salesSum = 0;

    <c:forEach items="${orderSalesList}" var="orderSales">
    orderDates.push("${orderSales.orderDate}");
    salesAmounts.push(${orderSales.totalAmount});
    salesSum += ${orderSales.totalAmount};
    </c:forEach>

    document.querySelector('#salesSum').innerText = salesSum + '원';

    new Chart(salesChart, {
      type: 'line',
      data: {
        labels: orderDates,
        datasets: [{
          label: '일자별 매출액',
          data: salesAmounts,
          fill: false,
          borderColor: 'rgb(75, 192, 192)',
          tension: 0.1,
          pointBackgroundColor: 'rgb(75, 192, 192)',
          pointBorderColor: '#fff',
          pointHoverBackgroundColor: '#fff',
          pointHoverBorderColor: 'rgb(75, 192, 192)'
        }]
      },
      options: {
        responsive: true,
        maintainAspectRatio: false,
        scales: {
          y: {
            beginAtZero: true,
            ticks: {
              // 매출액 포맷팅 (천 단위 콤마)
              callback: function (value) {
                return value.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + '원';
              }
            }
          }
        },
        plugins: {
          title: {
            display: true,
            text: '일자별 매출액',
            font: {
              size: 18
            }
          }
        }
      }
    });
  });

  const startDatePicker = document.querySelector('#startDate');
  const endDatePicker = document.querySelector('#endDate');

  document.querySelector('#applyBtn').addEventListener('click', function (e) {
    const startDate = startDatePicker.value;
    const endDate = endDatePicker.value;

    if (startDate && endDate) {
      // 선택된 날짜가 유효한지 확인
      const start = new Date(startDate);
      const end = new Date(endDate);

      if (start > end) {
        e.preventDefault();
        alert('종료일은 시작일보다 이후여야 합니다.')
        return;
      }
    } else {
      e.preventDefault();
      alert('시작일과 종료일을 모두 선택해주세요.')
    }


  });
</script>

</body>
</html>