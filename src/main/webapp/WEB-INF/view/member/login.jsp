<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/view/include/page.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>Cafe Grid & Circle</title>
    <%@include file="/WEB-INF/view/include/static.jsp" %>
</head>

<body>
<%@include file="/WEB-INF/view/include/header.jsp" %>
<main style="margin-top: 100px;">
    <div class="row">
        <div class="col s12 m6 offset-m3">
            <div class="card brown lighten-5 z-depth-2">
                <div class="card-content">
                    <span class="card-title center-align brown-text text-darken-3"
                          style="margin-top: 20px; margin-bottom: 40px; font-weight: 700; font-size: 30px; border-bottom: 1px;">로그인</span>

                    <c:if test="${param.error != null}">
                        <div class="card-panel red lighten-2 white-text center-align" style="border-radius: 10px; font-size: 1.1rem;">
                            <i class="material-icons left">error_outline</i>
                            아이디나 비밀번호가 올바르지 않습니다.
                        </div>
                    </c:if>

                    <!--  로그인 form 시작 -->
                    <form:form modelAttribute="loginForm" action="${context}/member/login" method="post">
                        <div class="input-field">
                            <form:input path="id" type="text" id="id" name="id" class="validate"/>
                            <label for="id">아이디</label>
                            <form:errors path="id" cssClass="helper-text"/>
                        </div>

                        <div class="input-field">
                            <form:input path="password" type="password" id="password" name="password"/>
                            <label for="password">비밀번호</label>
                            <form:errors path="password" cssClass="helper-text"/>
                        </div>

                        <div class="row" style="margin-left: 0; margin-bottom: 20px;">
                            <div class="col s12">
                                <label style="margin-left: 10px;">
                                    <input type="checkbox" name="remember-me" style="margin-right: 5px;"/>
                                    <span>로그인 유지</span>
                                </label>
                            </div>
                        </div>

                        <div class="center-align">
                            <button type="submit" class="btn brown darken-2 waves-effect waves-light">
                                로그인
                            </button>
                        </div>
                    </form:form>
                    <!--  로그인 form 끝 -->

                </div>
            </div>
        </div>
    </div>
</main>
<%@include file="/WEB-INF/view/include/footer.jsp" %>

</body>
</html>