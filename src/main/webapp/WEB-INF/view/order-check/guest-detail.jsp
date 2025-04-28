<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/view/include/page.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>Cafe Grid & Circle</title>
    <%@include file="/WEB-INF/view/include/static.jsp" %>
    <style>
        /* 헤더 스타일 */
        .section h2 {
            font-weight: 700;
            font-size: 36px; /* 폰트 크기 증가 */
            color: #6a4f39; /* 헤더 색상 강조 */
            margin-top: 40px;
            margin-bottom: 50px; /* 테이블과의 간격을 더 넓힘 */
        }

        /* 주문 상세 테이블 스타일 */
        .order-summary {
            font-family: Arial, sans-serif;
            font-size: 16px;
        }

        table.highlight {
            width: 100%;
            border-spacing: 0;
            border-collapse: collapse;
            margin-top: 10px; /* 테이블과 다른 요소 사이에 여백 추가 */
            margin-top: 40px;
        }

        table.highlight th, table.highlight td {
            padding: 12px;
            text-align: center;
        }

        table.highlight th {
            background-color: #f0f0f0;
        }

        /* 테이블의 내용 스타일 */
        table.highlight td {
            background-color: #fafafa;
        }
    </style>
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
            <p><strong>주문번호: </strong>${orderId}</p>
            <c:if test="${not empty orderHeader}">
                <p><strong>총 주문 금액: </strong>${orderHeader.totalPrice}원</p>
                <p><strong>주문 상태:</strong> ${orderHeader.orderStatus}</p>
                <p><strong>배송 주소: </strong>${orderHeader.userAddress}</p>
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