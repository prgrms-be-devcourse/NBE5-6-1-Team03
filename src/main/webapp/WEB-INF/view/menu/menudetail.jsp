<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/include/page.jsp" %>
<!DOCTYPE html>
<html>

<head>
    <title>메뉴 상세</title>
    <%@ include file="/WEB-INF/view/include/static.jsp" %>
</head>

<body>
<%@ include file="/WEB-INF/view/include/header.jsp" %>
<main>
    <c:choose>
        <c:when test="${not empty menudetail}">
            <div class="container" style="max-width: 800px;">
                <div class="card horizontal z-depth-2" style="padding: 20px;">
                    <!-- 이미지 영역 -->
                    <div class="card-image" style="min-width: 300px;">
                        <c:set var="foundImage" value="false" />
                        <c:forEach var="image" items="${imageList}">
                            <c:if test="${!foundImage && image.menuId == menudetail.id}">
                                <img src="/upload/${image.savePath}${image.originalName}" alt="${menudetail.name}" style="width: 100%; height: auto; object-fit: cover;" />
                                <c:set var="foundImage" value="true" />
                            </c:if>
                        </c:forEach>
                        <c:if test="${!foundImage}">
                            <img src="/assets/img/sample.jpeg" alt="샘플 이미지" style="width: 100%; height: auto; object-fit: cover;" />
                        </c:if>
                    </div>

                    <!-- 내용 영역 -->
                    <div class="card-stacked">
                        <div class="card-content">
                            <h5 class="brown-text text-darken-3">${menudetail.name}</h5>
                            <p><strong>설명:</strong> ${menudetail.info}</p>
                            <p><strong>가격:</strong> ${menudetail.price}원</p>
                        </div>
                        <div class="card-action">
                            <a href="${pageContext.request.contextPath}/menu" class="btn brown lighten-2">메뉴로 돌아가기</a>
                        </div>
                    </div>
                </div>
            </div>
        </c:when>

        <c:otherwise>
            <div class="container" style="max-width: 600px;">
                <div class="card-panel red lighten-4 center-align">
                    상세 정보를 찾을 수 없습니다.
                </div>
            </div>
        </c:otherwise>
    </c:choose>

</main>

<%@ include file="/WEB-INF/view/include/footer.jsp" %>
</body>

</html>
