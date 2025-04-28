<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<header class="header">
    <nav>
        <div class="nav-wrapper container">
            <a href="${context}/" class="brand-logo">Cafe Grid & Circle</a>
            <ul class="right hide-on-med-and-down">
                <sec:authorize access="isAnonymous()">
                    <li><a href="${context}/">홈</a></li>
                    <li><a href="${context}/menu">메뉴</a></li>
                    <li><a href="${context}/order">주문하기</a></li>
                    <li><a href="${context}/order-check/guest">주문확인</a></li>
                    <li><a href="${context}/member/login">로그인</a></li>
                    <li><a href="${context}/member/signup">회원가입</a></li>
                </sec:authorize>
                <sec:authorize access="hasAuthority('ROLE_USER')">
                    <li><a href="${context}/main">홈</a></li>
                    <li><a href="${context}/menu">메뉴</a></li>
                    <li><a href="${context}/order">주문하기</a></li>
                    <li><a href="${context}/order-check/member">주문확인</a></li>
                    <li><a href="#" id="logout">로그아웃</a></li>
                </sec:authorize>
                <sec:authorize access="hasAuthority('ROLE_ADMIN')">
                    <li><a href="${context}/main">홈</a></li>
                    <li><a href="${context}/menu">메뉴</a></li>
                    <li><a href="${context}/order">주문하기</a></li>
                    <li><a href="${context}/admin">대시보드</a></li>
                    <li><a href="${context}/order-check/member">주문확인</a></li>
                    <li><a href="#" id="logout">로그아웃</a></li>
                </sec:authorize>
            </ul>
        </div>
    </nav>
</header>
<form:form action="/logout" method="post" id="logoutForm">
</form:form>

<script>

  (() => {

    const logout = document.querySelector('#logout');
    if(!logout) return;

    logout.addEventListener('click', ev => {
      ev.preventDefault();
      logoutForm.submit();
    });

  })();
</script>