<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/view/include/page.jsp" %>
<!DOCTYPE html>
<html>

<head>
    <title>메뉴 목록</title>
    <%@include file="/WEB-INF/view/include/static.jsp" %>
</head>


<body>
<%@include file="/WEB-INF/view/include/header.jsp" %>
<main>
    <div class="section container" id="order-statistics">
        <div class="row">
            <h2 class="brown-text text-darken-2 col s10">커피 메뉴</h2>
        </div>

        <div class="row">
            <table class="highlight centered responsive-table">
                <thead>
                <tr>
                    <th>이미지</th>
                    <th>상품명</th>
                    <th>상품정보</th>
                    <th>가격</th>
                    <th>상세보기</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="menu" items="${menus}">
                    <tr>
                        <td>
                            <c:set var="foundImage" value="false" />
                            <c:forEach var="image" items="${images}">
                                <c:if test="${!foundImage && image.menuId == menu.id}">
                                    <img src="/upload/${image.savePath}${image.originalName}" width="60px" style="object-fit: cover;" />
                                    <c:set var="foundImage" value="true" />
                                </c:if>
                            </c:forEach>
                            <c:if test="${!foundImage}">
                                <img src="/assets/img/sample.jpeg" width="60px" style="object-fit: cover;" />
                            </c:if>
                        </td>
                        <td>${menu.name}</td>
                        <td>장바구니</td>
                        <td>${menu.price}원</td>
                        <td>
                            <a href="${pageContext.request.contextPath}/menu/detail?id=${menu.id}">
                                <button type="button" class="btn small brown lighten-1">상세보기</button>
                            </a>
                        </td>
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