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
<%@include file="/WEB-INF/view/include/header.jsp" %>
<main>

    <!-- 메인 배너 -->
    <div class="banner" id="home">
        Cafe Grid & Circle에 오신 걸 환영합니다!
    </div>

    <!-- 소개 섹션 -->
    <div class="section container" id="about">
        <h4 class="brown-text text-darken-2">우리 카페 소개</h4>
        <p>Cafe Grid & Circle는 직접 로스팅한 원두와 감성적인 분위기를 자랑합니다. 편안한 휴식과 대화를 즐기실 수 있는 공간을 제공합니다.</p>
    </div>

    <!-- 인기 메뉴 섹션 -->
    <div class="section container" id="menu">
        <h4 class="brown-text text-darken-2">인기 메뉴</h4>
        <div class="row">
            <div class="cardbox">
                <c:forEach var="popularMenu" items="${popularMenus}">
                    <a href="${pageContext.request.contextPath}/menu/detail?id=${popularMenu.id}" style="text-decoration: none; color: inherit;">
                        <div class="card overlay" title="상세보기">
                            <div class="card-image">
                                <c:if test="${not empty imageMap[popularMenu.id][0]}">
                                    <img src="/download/${imageMap[popularMenu.id][0].savePath}${imageMap[popularMenu.id][0].renameName}" width="200px" />
                                </c:if>
                                <c:if test="${ empty imageMap[popularMenu.id][0]}">
                                    <img src="/download/img/beans/no-image.jpg" width="200px" />
                                </c:if>
                            </div>
                            <div class="card-content">
                                <table>
                                    <tr>
                                            ${popularMenu.name}
                                    </tr>
                                    <tr>
                                        <td>가격</td>
                                        <td>${popularMenu.price}</td>
                                    </tr>
                                </table>
                            </div>
                        </div>
                    </a>
                </c:forEach>
            </div>
        </div>
    </div>

    <!-- 오시는 길 -->
    <div class="section container" id="location">
        <h4 class="brown-text text-darken-2">오시는 길</h4>
        <p>서울시 어딘가 123, Cafe Grid & Circle</p>
        <iframe src="https://maps.google.com/maps?q=서울&t=&z=13&ie=UTF8&iwloc=&output=embed"
                width="100%" height="300" style="border:0;" allowfullscreen="" loading="lazy"></iframe>
    </div>

</main>
<%@include file="/WEB-INF/view/include/footer.jsp" %>

</body>
</html>