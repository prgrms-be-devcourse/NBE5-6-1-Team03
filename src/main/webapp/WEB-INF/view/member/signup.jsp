<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/view/include/page.jsp" %>
<!DOCTYPE html>
<html>
<head>
  <title>Cafe Grid & Circle</title>
  <%@include file="/WEB-INF/view/include/static.jsp" %>
  <style>
    .input-field .material-icons.prefix {
      position: absolute;
      top: 50%;
      /*left: 0.1rem; !* 추가! 왼쪽으로 더 붙임 *!*/
      transform: translateY(-50%);
      font-size: 2rem;
    }

    .input-field {
      margin-bottom: 1.5rem; /* 필드 사이 간격 넓힘 */
    }
    .input-field label {
      font-size: 1.2rem;
    }
    .input-field .helper-text {
      margin-left: 2.5rem; /* 에러메시지 */
    }
  </style>

</head>

<body>
<%@include file="/WEB-INF/view/include/header.jsp" %>

<main style="margin-top: 100px; margin-bottom: 100px">
  <div class="row">
    <div class="col s12 m6 offset-m3">
      <div class="card brown lighten-5 z-depth-2">
        <div class="card-content" style="padding: 3rem;" >
          <span class="card-title center-align brown-text text-darken-3"
                style="margin-top: 20px; margin-bottom: 40px; font-weight: 700; font-size: 30px; border-bottom: 1px;" >회원가입</span>

          <!-- 회원가입 form 시작 -->
          <form:form modelAttribute="signupForm" action="${context}/member/signup" method="post" id="signupForm">
            <div class="input-field">
              <i class="material-icons prefix">account_circle</i>
              <form:input path="id" id="id" name="id" type="text" placeholder="ID" class="validate"/>
              <label for="id">아이디</label>
              <form:errors path="id" cssClass="helper-text red-text"/>
              <span class="helper-text" id="idCheckMsg" style="display: none"></span>
            </div>

            <div class="input-field">
              <i class="material-icons prefix">lock</i>
              <form:input path="password" id="password" name="password" type="password" placeholder="PASSWORD" class="validate"/>
              <label for="password">비밀번호</label>
              <form:errors path="password" cssClass="helper-text red-text"/>
            </div>

            <div class="input-field">
              <i class="material-icons prefix">text_fields</i>
              <form:input path="name" id="name" name="name" type="text" placeholder="김그렙" class="validate"/>
              <label for="name">이름</label>
              <form:errors path="name" cssClass="helper-text red-text"/>
            </div>

            <div class="input-field">
              <i class="material-icons prefix">email</i>
              <form:input path="email" id="email" name="email" type="email" placeholder="girdncircle@grepp.com" class="validate"/>
              <label for="email">이메일</label>
              <form:errors path="email" cssClass="helper-text red-text"/>
            </div>

            <div class="input-field">
              <i class="material-icons prefix">phone</i>
              <form:input path="tel" id="tel" name="tel" type="tel" placeholder="010-1533-1886" class="validate"/>
              <label for="tel">전화번호</label>
              <form:errors path="tel" cssClass="helper-text red-text"/>
            </div>

            <div class="input-field">
              <i class="material-icons prefix">business</i>
              <form:input path="address" id="address" name="address" type="text" placeholder="서울특별시 서초구 반포대로 45" class="validate"/>
              <label for="address">주소</label>
              <form:errors path="address" cssClass="helper-text red-text"/>
            </div>

            <div class="center-align" style="margin-top: 2rem; margin-bottom: 0.5rem">
              <button type="submit" class="btn brown darken-2 waves-effect waves-light">
                회원가입
                <i class="material-icons right">person_add</i>
              </button>
            </div>
          </form:form>
          <!-- 회원가입 form 끝 -->

        </div>
      </div>
    </div>
  </div>
</main>

<%@include file="/WEB-INF/view/include/footer.jsp" %>
</body>
<script>
  const validElement = document.querySelector('#idCheckMsg');
  document.querySelector('#id').addEventListener('focusout', async ev => {
    const id = ev.target.value;
    if(!id) return;
    const response = await fetch('/api/member/exists/' + id);
    const data = await response.json();
    validElement.style.display = 'block';
    validElement.textContent = data.data ? '사용이 불가능한 아이디 입니다.' : '사용 가능한 아이디 입니다.';
  });

  document.querySelector('#signupForm').addEventListener('submit', async ev => {
    // form tag 의 기본 이벤트 차단
    ev.preventDefault();

    const id = document.querySelector('#id').value;
    if(!id) return;
    const response = await fetch('/api/member/exists/' + id);
    const data = await response.json();

    if(data.data){
      document.querySelector('#id').focus();
      validElement.textContent = '사용이 불가능한 아이디 입니다.';
      return;
    }

    ev.target.submit();
  });
</script>

</html>
