<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/view/include/page.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>Cafe Grid & Circle</title>
    <%@include file="/WEB-INF/view/include/static.jsp" %>
</head>
<link rel="stylesheet" href="../../../../assets/css/footer.css">
<body>
<%@include file="/WEB-INF/view/include/header-admin.jsp" %>

<main>
    <!-- 등록 상품 리스트 -->
    <div class="section container" id="order-statistics">
        <c:if test="${not empty msg}">
            <div class="card-panel red lighten-2 text-white">존재하지 않는 상품입니다</div>
        </c:if>
        <div class="row">
            <h4 class="brown-text text-darken-2 col s10">등록 상품</h4>
            <a href="/admin/menu/new" class="waves-effect waves-light btn col s2 green darken-4"><i class="material-icons left">add</i>신규 등록</a>
        </div>
        <div class="row">
            <div class="col s6 m3">
                <div class="card">
                    <div class="card-image">
                        <img src="/assets/img/sample.jpeg">
                        <a href="/admin/menu/1005" class="btn-floating halfway-fab waves-effect waves-light green darken-4"><i class="material-icons">mode_edit</i></a>
                    </div>
                    <div class="card-content">
                        <span class="card-title">상품명</span>
                        <p>상품정보</p>
                        <p>가격</p>
                        <p>재고</p>
                    </div>
                </div>
            </div>
            <div class="col s6 m3">
                <div class="card">
                    <div class="card-image">
                        <img src="/assets/img/sample.jpeg">
                        <a href="/admin/menu/${menu.id}" class="btn-floating halfway-fab waves-effect waves-light green darken-4"><i class="material-icons">mode_edit</i></a>
                    </div>
                    <div class="card-content">
                        <span class="card-title">상품명</span>
                        <p>상품정보</p>
                        <p>가격</p>
                        <p>재고</p>
                    </div>
                </div>
            </div>
            <div class="col s6 m3">
                <div class="card">
                    <div class="card-image">
                        <img src="/assets/img/sample.jpeg">
                        <a href="/admin/menu/${menu.id}" class="btn-floating halfway-fab waves-effect waves-light green darken-4"><i class="material-icons">mode_edit</i></a>
                    </div>
                    <div class="card-content">
                        <span class="card-title">상품명</span>
                        <p>상품정보</p>
                        <p>가격</p>
                        <p>재고</p>
                    </div>
                </div>
            </div>
            <div class="col s6 m3">
                <div class="card">
                    <div class="card-image">
                        <img src="/assets/img/sample.jpeg">
                        <a href="/admin/menu/${menu.id}" class="btn-floating halfway-fab waves-effect waves-light green darken-4"><i class="material-icons">mode_edit</i></a>
                    </div>
                    <div class="card-content">
                        <span class="card-title">상품명</span>
                        <p>상품정보</p>
                        <p>가격</p>
                        <p>재고</p>
                    </div>
                </div>
            </div>

        </div>

    </div>

</main>
<%@include file="/WEB-INF/view/include/footer.jsp" %>

</body>
</html>