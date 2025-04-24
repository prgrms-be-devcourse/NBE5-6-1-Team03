<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<header class="header">
    <nav>
        <div class="nav-wrapper container">
            <a href="${context}/" class="brand-logo">Cafe Grid & Circle</a>
            <ul class="right hide-on-med-and-down">
                <li><a href="/admin">대시 보드</a></li>
                <li><a href="/admin/orders">주문 관리</a></li>
                <li><a href="/admin/menu">상품 관리</a></li>
                <li><a href="/">홈페이지</a></li>
                <li><a href="#" id="logout">로그아웃</a></li>
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