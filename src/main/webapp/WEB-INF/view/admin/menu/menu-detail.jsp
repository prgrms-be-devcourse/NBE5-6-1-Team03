<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/view/include/page.jsp" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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
    <!-- 상품 수정 -->
    <div class="section container" id="order-statistics">

        <h4 class="brown-text text-darken-2 col s10">상품 수정</h4>

        <div class="card white lighten-4 z-depth-1"
             style="margin-top: 30px; margin-bottom: 30px; border-radius: 12px;">
            <div class="card-content" style="padding: 20px 24px;">

                <div class="row">
                    <form:form class="col s12" action="/admin/menu/${menuRegistForm.menuId}"
                               method="post"
                               enctype="multipart/form-data" modelAttribute="menuRegistForm">
                        <sec:csrfInput/>
                        <div class="card" style="width: 25%; min-width: 200px">
                            <div class="card-image">
                                <c:if test="${empty imageList}">
                                    <img id="preview-image" src="/assets/img/sample.jpeg">
                                </c:if>
                                <c:if test="${not empty imageList}">
                                    <img id="preview-image" src="${imageList[0].renamedUrl}"
                                         style="width: 100%">
                                </c:if>
                            </div>
                        </div>
                        <div class="file-field input-field">
                            <div class="btn">
                                <span>File</span>
                                <input type="file" name="thumbnail" id="thumbnail" multiple>
                                <form:errors path="thumbnail" cssClass="red-text"/>
                            </div>
                            <div class="file-path-wrapper">
                                <input class="file-path validate" type="text"
                                       placeholder="Upload one or more files">
                            </div>
                        </div>
                        <div class="row">
                            <div class="input-field col s12">
                                <form:textarea path="name" id="name" name="name"
                                               class="materialize-textarea"></form:textarea>
                                <label for="name" class="active">상품명</label>
                                <form:errors path="name" cssClass="red-text"/>
                            </div>
                        </div>
                        <div class="row">
                            <div class="input-field col s12">
                                <form:textarea path="info" id="info" name="info"
                                               class="materialize-textarea"></form:textarea>
                                <label for="info" class="active">상품 정보</label>
                                <form:errors path="info" cssClass="red-text"/>
                            </div>
                        </div>
                        <div class="row">
                            <div class="input-field col s12">
                                <form:input path="price" id="price" name="price" type="number"
                                            class="validate"></form:input>
                                <label for="price" class="active">가격</label>
                                <form:errors path="price" cssClass="red-text"/>
                            </div>
                        </div>
                        <div class="row">
                            <div class="input-field col s12">
                                <form:input path="amount" id="amount" name="amount" type="number"
                                            class="validate"></form:input>
                                <label for="amount" class="active">재고</label>
                                <form:errors path="amount" cssClass="red-text"/>
                            </div>
                        </div>
                        <div style="display: flex; justify-content: end">
                            <button type="submit"
                                    class="waves-effect waves-light btn-small green darken-4"
                                    style="margin-right: 0.5rem; height: 45px; line-height: 45px">
                                <i class="material-icons left">save</i>저장
                            </button>
                            <button type="button" onclick="deleteMenu()"
                                    class="waves-effect waves-light btn-small red darken-4"
                                    style="margin-right: 0.5rem; height: 45px; line-height: 45px">
                                <i class="material-icons left">delete</i>삭제
                            </button>
                            <button onclick="location.href='/admin/menu'" type="reset"
                                    class="waves-effect waves-light btn-small grey darken-2"
                                    style="height: 45px; line-height: 45px">
                                <i class="material-icons left">cancel</i>뒤로가기
                            </button>
                        </div>
                    </form:form>
                </div>

            </div>
        </div>
    </div>

</main>
<%@include file="/WEB-INF/view/include/footer.jsp" %>

<script>
  const csrfToken = '${_csrf.token}';
  const csrfHeader = '${_csrf.headerName}';
  const menuId = '${menuRegistForm.menuId}';

  function deleteMenu() {
    if (confirm('정말 삭제하시겠습니까?')) {
      fetch('/admin/menu/' + menuId, {
        method: 'DELETE',
        headers: {
          'Content-Type': 'application/json',
          [csrfHeader]: csrfToken
        }
      })
      .then(response => {
        if (response.ok) {
          alert('삭제되었습니다.');
          window.location.href = '/admin/menu';
        } else {
          alert('삭제에 실패했습니다.');
        }
      })
    }
  }

  document.getElementById('thumbnail').addEventListener('change', function (event) {
    const file = event.target.files[0];
    if (!file) {
      return;
    }
    const reader = new FileReader();
    reader.onload = function (e) {
      const previewImage = document.getElementById('preview-image');
      previewImage.src = e.target.result;
    };
    reader.readAsDataURL(file);
  });
</script>

</body>
</html>