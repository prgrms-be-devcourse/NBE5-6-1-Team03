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
            <c:if test="${empty menuList}">
                <h5 class="brown-text text-lighten-1 col s10">등록된 상품이 없습니다</h5>
            </c:if>
            <c:if test="${not empty menuList}">
                <c:set var="imageList" value="${imageMap}"/>
                <c:forEach items="${menuList}" var="menu">
                    <div class="col s6 m3">
                        <div class="card">
                            <div class="card-image">
                                <c:if test="${empty imageMap[menu.id][0]}">
                                    <img src="/assets/img/sample.jpeg">
                                </c:if>
                                <c:if test="${not empty imageMap[menu.id][0]}">
                                    <img src="${imageMap[menu.id][0].url}">
                                </c:if>
                                <a href="/admin/menu/${menu.id}" class="btn-floating halfway-fab waves-effect waves-light green darken-4"><i class="material-icons">mode_edit</i></a>
                            </div>
                            <div class="card-content">
                                <span class="card-title">${menu.name}</span>
                                <p>${menu.info}</p>
                                <p>[가격] : ${menu.price}원</p>
                                <p>[수량] : ${menu.amount}개</p>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </c:if>
        </div>

    </div>

</main>
<%@include file="/WEB-INF/view/include/footer.jsp" %>

</body>
</html>