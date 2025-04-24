<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/view/include/page.jsp" %>
<!DOCTYPE html>
<html>

<head>
    <title>메뉴 목록</title>
    <%@include file="/WEB-INF/view/include/static.jsp" %>
</head>


<body>
<main>
    <%@include file="/WEB-INF/view/include/header.jsp" %>


    <div class="section container" id="order-statistics">
        <div class="row">
            <h2 class="brown-text text-darken-2 col s10">커피 메뉴</h2>
        </div>

        <div class="row">
            <div class="col s6 m3">
                <div class="card">
                    <c:forEach var="menu" items="${menus}">

                    <div class="card-image">
                        <c:forEach var="image" items="${images}">
                            <c:if test="${image.menuId == menu.id}">
                                <img src="/upload/${image.savePath}${image.originalName}" width="200px" />
                            </c:if>

                        </c:forEach>
                    </div>

                    <div class="card-content">
                        <span class="card-title">상품명</span>
                        <p>장바구니</p>
                        <p>상품정보 : ${menu.name}</p>
                        <p>가격 : ${menu.price}원</p>
                        <p>재고 : ${menu.amount}개</p>

                        <p>
                        <a href="${pageContext.request.contextPath}/menu/detail?id=${menu.id}">
                            <button type="button">상세보기</button>
                        </a>
                        </p>

                        <p>
                         <form method="post" action="${pageContext.request.contextPath}/cart/add">
                            <input type="hidden" name="menuId" value="${menu.id}"/>
                            <button type="submit">담기</button>
                         </form>
                        </p>


                    </div>
                    </c:forEach>

                </div>
            </div>
        </div>

    </div>



    <div>
        <a href="/menu/detail">
            <button type="button">임시, 상세페이지 가기</button>
        </a>
        <a href="/">
            <button type="button">임시, 홈으로 가기</button>
        </a>
        <a href="/menu">
            <button type="button">임시, 메뉴페이지 가기</button>
        </a>


    </div>





</main>

    <%@include file="/WEB-INF/view/include/footer.jsp" %>
</body>

</html>