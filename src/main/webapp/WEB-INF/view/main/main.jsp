<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/view/include/page.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>Grepp</title>
    <%@include file="/WEB-INF/view/include/static.jsp" %>
</head>

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
            <div class="col s12 m4">
                <div class="card menu-card">
                    <div class="card-image">
                        <img src="${context}/assets/coffee1.jpg" alt="아메리카노">
                        <span class="card-title">아메리카노</span>
                    </div>
                    <div class="card-content">
                        <p>고소하고 깔끔한 맛의 스페셜티 아메리카노.</p>
                    </div>
                </div>
            </div>
            <div class="col s12 m4">
                <div class="card menu-card">
                    <div class="card-image">
                        <img src="${context}/assets/latte.jpg" alt="카페라떼">
                        <span class="card-title">카페라떼</span>
                    </div>
                    <div class="card-content">
                        <p>부드러운 우유 거품과 에스프레소의 조화.</p>
                    </div>
                </div>
            </div>
            <div class="col s12 m4">
                <div class="card menu-card">
                    <div class="card-image">
                        <img src="${context}/assets/dessert.jpg" alt="티라미수">
                        <span class="card-title">티라미수</span>
                    </div>
                    <div class="card-content">
                        <p>직접 만든 이탈리안 디저트로 달콤함을 더하세요.</p>
                    </div>
                </div>
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