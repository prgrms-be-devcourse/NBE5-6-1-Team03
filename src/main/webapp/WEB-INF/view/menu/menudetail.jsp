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
                    <c:if test="${not empty image}">
                        <c:set var="found" value="true" />
                        <img src="/download/${image.savePath}${image.renameName}" />
                    </c:if>
                    <c:if test="${empty image}">
                        <img src="/download/img/beans/no-image.jpg" alt="이미지 없음" width="200px" />
                    </c:if>
                </div>

                <div id="namebox">
                    <span>${menu.name}</span>
                    <p>판매가 ${menu.price}</p>
                    <p>적립금 ${menu.price * 0.05}원 (5%)</p>
                    <p>배송비 3000원</p>
                </div>

            </div>

            <div class ="down">
                <div id="info">
                    <p>${menu.info}</p>
                </div>
            </div>

            <div class ="infoimage">
                <div>
                    <img src="/download/${image.savePath}infopage.jpg" />

                </div>
            </div>
        </div>

    </div>



</main>

<%@include file="/WEB-INF/view/include/footer.jsp" %>
</body>

</html>