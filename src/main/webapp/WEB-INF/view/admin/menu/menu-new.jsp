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
    <!-- 신규 상품 추가 -->
    <div class="section container" id="order-statistics">
        <c:if test="${not empty alertMessage}">
            <div class="card-panel red lighten-2 text-white">${alertMessage}</div>
        </c:if>

        <h4 class="brown-text text-darken-2 col s10">상품 추가</h4>

        <div class="row">
            <form:form class="col s12" action="${context}/admin/menu/new" method="post" enctype="multipart/form-data" modelAttribute="menuRegistForm">
                <div class="card" style="width: 25%; min-width: 200px">
                    <div class="card-image">
                        <img src="/assets/img/sample.jpeg" style="width: 100%">
                    </div>
                </div>
                <div class="file-field input-field">
                    <div class="btn">
                        <span>File</span>
                        <input type="file" name="thumbnail" multiple>
                    </div>
                    <div class="file-path-wrapper">
                        <input class="file-path validate" type="text"
                               placeholder="Upload one or more files">
                    </div>
                </div>
                <div class="row">
                    <div class="input-field col s12">
                        <form:textarea path="name" id="name" name="name" class="materialize-textarea"></form:textarea>
                        <form:errors path="name"/>
                        <label for="name">상품명</label>
                    </div>
                </div>
                <div class="row">
                    <div class="input-field col s12">
                        <form:textarea path="info" id="info" name="info" class="materialize-textarea"></form:textarea>
                        <form:errors path="info"/>
                        <label for="info">상품 정보</label>
                    </div>
                </div>
                <div class="row">
                    <div class="input-field col s12">
                        <form:input path="price" id="price" name="price" type="number" class="validate"></form:input>
                        <form:errors path="price"/>
                        <label for="price">가격</label>
                    </div>
                </div>
                <div class="row">
                    <div class="input-field col s12">
                        <form:input path="amount" id="amount" name="amount" type="number" class="validate"></form:input>
                        <form:errors path="amount"/>
                        <label for="amount">재고</label>
                    </div>
                </div>
                <button type="submit" class="waves-effect waves-light btn col s2 green darken-4"><i
                        class="material-icons left">save</i>저장
                </button>
                <button onclick="location.href='/admin/menu'" type="reset" class="waves-effect waves-light btn col s2 red darken-4">
                    <i class="material-icons left">cancel</i>취소
                </button>
            </form:form>
        </div>
    </div>

</main>
<%@include file="/WEB-INF/view/include/footer.jsp" %>

</body>
</html>