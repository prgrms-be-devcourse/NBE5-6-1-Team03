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
    <form:form modelAttribute="orderCheckForm" action="${context}/order-check/guest" method="post" class="col s12">
        <div class="container" style="max-width: 600px;">
            <h4 class="center-align brown-text text-darken-2"
                style="margin-bottom: 60px; font-weight: 700; font-size: 32px; border-bottom: 1px solid #ccc; padding-bottom: 10px;">
                비회원 주문 확인
            </h4>

            <div class="card z-depth-2" style="padding: 30px;">
                <div class="input-field">
                    <form:input path="email" type="email" id="email" name="email" class="validate"/>
                    <label for="email">이메일</label>
                    <form:errors path="email" cssClass="helper-text red-text"/>
                </div>
                <div class="center-align" style="margin-top: 20px;">
                    <button type="submit" class="btn brown darken-2 waves-effect waves-light">주문 확인</button>
                </div>
            </div>
        </div>
    </form:form>


</main>
<%@include file="/WEB-INF/view/include/footer.jsp" %>
</body>
</html>