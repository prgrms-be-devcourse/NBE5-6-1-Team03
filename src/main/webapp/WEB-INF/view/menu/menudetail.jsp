<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/view/include/page.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
                    <table >
                        <tr>
                            <td>이름</td>
                            <td>${menu.name}</td>
                        </tr>
                        <tr>
                            <td>판매가</td>
                            <td>${menu.price}</td>
                        </tr>
                        <tr>
                            <td>적립금</td>
                            <td>${menu.price * 0.05}원 (5%)</td>
                        </tr>
                        <tr>
                            <td>배송비</td>
                            <td>3000원</td>
                        </tr>
                    </table>
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