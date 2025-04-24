<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/include/page.jsp" %>
<!DOCTYPE html>
<html>

<head>
    <title>메뉴 상세</title>
    <%@ include file="/WEB-INF/view/include/static.jsp" %>
</head>

<body>
<main>
    <%@ include file="/WEB-INF/view/include/header.jsp" %>
    <h2>메뉴 상세</h2>

    <table border="1">
        <tr>
            <th>사진</th>
            <th>이름</th>
            <th>가격</th>
            <th>수량</th>
            <th>상세</th>
        </tr>
        <c:if test="${not empty menudetail}">
            <tr>
                <td>
                    <div class="card-image">
                        <c:forEach var="image" items="${images}">
                            <c:if test="${image.menuId == menu.id}">
                                <img src="/upload/${image.savePath}${image.originalName}" width="200px" />
                            </c:if>

                        </c:forEach>
                    </div>
                </td>

                <td>${menudetail.name}</td>
                <td>${menudetail.price}원</td>
                <td>${menudetail.amount}개</td>
                <td>${menudetail.info}개</td>

            </tr>
        </c:if>
        <!-- menudetail이 비어있으면 처리하는 부분 -->
        <c:if test="${empty menudetail}">
            <tr>
                <td colspan="5">상세 정보를 찾을 수 없습니다.</td>
            </tr>
        </c:if>

    </table>

    <a href="/menu">
        <button type="button">메뉴 페이지로 돌아가기</button>
    </a>

</main>

<%@ include file="/WEB-INF/view/include/footer.jsp" %>
</body>

</html>
