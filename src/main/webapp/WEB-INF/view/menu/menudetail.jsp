<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/view/include/page.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>

<head>
    <title>메뉴 상세</title>
    <%@include file="/WEB-INF/view/include/static.jsp" %>

</head>
<link rel="stylesheet" href="../../../../assets/css/menu.css">

<body>
<main>
    <%@include file="/WEB-INF/view/include/header.jsp" %>


    <div class="section container" id="order-statistics">
        <div>
            <div class="up">
                <div id="imagebox">
                    <c:forEach var="image" items="${imageList}">
                        <c:if test="${image.menuId == menudetail.id}">
                            <img src="/upload${image.savePath}${image.originalName}" />
                            <p>"/upload${image.savePath}${image.originalName}"</p>
                        </c:if>
                    </c:forEach>
                </div>
                <div id="namebox">
                    <span>${menudetail.name}</span>
                    <p>판매가 ${menudetail.price}</p>
                    <p>적립금 ${menudetail.price * 0.05}원 (5%)</p>
                    <p>배송비 3000원</p>

                </div>
            </div>

            <div class ="down">
                <div id="info">
                    <p>${menudetail.info}</p>
                </div>
            </div>

            <div class ="infoimage">
                <div>
                    <img src="${context}/assets/img/info.jpg" alt="info">

                </div>
            </div>
        </div>

    </div>



</main>

<%@include file="/WEB-INF/view/include/footer.jsp" %>
</body>

</html>