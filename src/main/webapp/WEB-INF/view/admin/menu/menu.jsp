<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/view/include/page.jsp" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
    <div class="section container">
        <div style="display: flex; justify-content: space-between; align-items: end">

            <h4 class="brown-text text-darken-2">등록 상품</h4>

            <a href="/admin/menu/new" class="btn-small green darken-3 waves-effect waves-light"
               data-position="bottom" style="height: 45px; line-height: 45px"><i
                    class="material-icons left">add</i>등록</a>
        </div>

        <c:if test="${not empty msg}">
            <div class="card-panel red lighten-2 text-white">존재하지 않는 상품입니다</div>
        </c:if>

        <div class="row">
            <div class="card white lighten-4 z-depth-1"
                 style="margin-top: 30px; margin-bottom: 30px; border-radius: 12px;">
                <div class="card-content" style="padding: 20px 24px;">

                    <c:if test="${empty menuList}">
                        <h5 class="brown-text text-lighten-1 col s10">등록된 상품이 없습니다</h5>
                    </c:if>
                    <c:if test="${not empty menuList}">
                        <c:set var="imageList" value="${imageMap}"/>
                        <table class="highlight centered responsive-table">
                            <thead class="grey lighten-5">
                            <tr>
                                <th>순번</th>
                                <th>이미지</th>
                                <th>메뉴명</th>
                                <th>설명</th>
                                <th>가격</th>
                                <th>수량</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${menuList}" var="menu" varStatus="status">
                                <tr onclick="location.href='/admin/menu/${menu.id}'" style="cursor: pointer">
                                    <td>${status.count}</td>
                                    <td>
                                        <c:if test="${empty imageMap[menu.id][0]}">
                                            <img src="/assets/img/sample.jpeg" alt="샘플"
                                                 style="width: 60px; height: 60px; object-fit: cover;">
                                        </c:if>
                                        <c:if test="${not empty imageMap[menu.id][0]}">
                                            <img src="${imageMap[menu.id][0].renamedUrl}"
                                                 alt="${menu.name}"
                                                 style="width: 60px; height: 60px; object-fit: cover;">
                                        </c:if>
                                    </td>
                                    <td>${menu.name}</td>
                                    <td>${menu.info}</td>
                                    <td><fmt:formatNumber value="${menu.price}" pattern="#,###"/>원
                                    </td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${menu.amount eq 0}">
                                                <div class="chip yellow lighten-4 yellow-text text-darken-4">
                                                    <fmt:formatNumber value="${menu.amount}"
                                                                      pattern="#,###"/>개
                                                </div>
                                            </c:when>
                                            <c:otherwise>
                                                <div class="chip grey lighten-2 grey-text text-darken-4">
                                                    <fmt:formatNumber value="${menu.amount}"
                                                                      pattern="#,###"/>개
                                                </div>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </c:if>

                </div>
            </div>
        </div>

    </div>

</main>
<%@include file="/WEB-INF/view/include/footer.jsp" %>

</body>
</html>