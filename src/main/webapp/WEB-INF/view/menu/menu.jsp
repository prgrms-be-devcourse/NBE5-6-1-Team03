<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/view/include/page.jsp" %>
<!DOCTYPE html>
<html>

<head>
    <title>Cafe Grid & Circle</title>
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
                        <div class="card-image">
                            <c:if test="${not empty imageMap[menu.id][0]}">
                                <img src="/download/${imageMap[menu.id][0].savePath}${imageMap[menu.id][0].renameName}" width="200px" />
                            </c:if>
                            <c:if test="${ empty imageMap[menu.id][0]}">
                                <img src="/download/img/beans/no-image.jpg" width="200px" />
                            </c:if>
                        </div>
                        <div class="card-content">
                            <table>
                                <tr>
                                    ${menu.name}
                                </tr>
                                <tr>
                                    <td>가격</td>
                                    <td>${menu.price}</td>
                                </tr>
                            </table>
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