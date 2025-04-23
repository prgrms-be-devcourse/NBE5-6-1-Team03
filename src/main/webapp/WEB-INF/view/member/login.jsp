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
    <div class="row">
        <div class="col s12 m6 offset-m3">
            <div class="card brown lighten-5 z-depth-2">
                <div class="card-content">
                    <span class="card-title center-align brown-text text-darken-3">로그인</span>

                    <!-- ✅ 로그인 form 시작 -->
                    <form:form action="${pageContext.request.contextPath}/login" method="post">
                        <div class="input-field">
                            <input type="text" id="username" name="username" required />
                            <label for="username">아이디</label>
                        </div>

                        <div class="input-field">
                            <input type="password" id="password" name="password" required />
                            <label for="password">비밀번호</label>
                        </div>

                        <div class="center-align">
                            <button type="submit" class="btn brown darken-2 waves-effect waves-light">
                                로그인
                            </button>
                        </div>
                    </form:form>
                    <!-- ✅ 로그인 form 끝 -->

                </div>
            </div>
        </div>
    </div>
</main>
<%@include file="/WEB-INF/view/include/footer.jsp" %>

</body>
</html>