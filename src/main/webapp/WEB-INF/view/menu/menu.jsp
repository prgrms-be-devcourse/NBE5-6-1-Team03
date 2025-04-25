<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/view/include/page.jsp" %>
<!DOCTYPE html>
<html>

<head>
    <title>메뉴 목록</title>
    <%@include file="/WEB-INF/view/include/static.jsp" %>

</head>
<link rel="stylesheet" href="../../../../assets/css/menu.css">

<body>
<main>
    <%@include file="/WEB-INF/view/include/header.jsp" %>


    <div class="section container" id="order-statistics">
        <!--메뉴 섹션 -->
        <div class="row">
            <h2 class="brown-text text-darken-2 col s10">커피 메뉴</h2>
        </div>

        <div class="cardbox">
            <c:forEach var="menu" items="${menus}">
                <a href="${pageContext.request.contextPath}/menu/detail?id=${menu.id}" style="text-decoration: none; color: inherit;">
                    <div class="card overlay" title="상세보기">
                        <c:forEach var = "image" items="${images}">
                            <c:if test="${menu.id == image.menuId}">
                                <div class="card-image">
                                    <img src="/download/${image.savePath}${image.renameName}" width="200px" alt="thumnail" />
                                </div>
                            </c:if>

                        </c:forEach>

                        <div class="card-content">
                            <span class="card-title">${menu.name}</span>
                            <p>가격 : ${menu.price}원</p>
                        </div>
                    </div>
                </a>
            </c:forEach>
        </div>
    </div>
</main>

<%@include file="/WEB-INF/view/include/footer.jsp" %>
</body>

</html>