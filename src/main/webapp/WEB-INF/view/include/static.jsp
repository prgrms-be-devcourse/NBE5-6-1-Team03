<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" %>
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>


<style>
  /*body {*/
  /*  background-color: #efebe9;*/
  /*  font-family: 'Noto Sans KR', sans-serif;*/
  /*}*/

  nav {
    background-color: #6d4c41;
  }
  footer.page-footer {
    background-color: #4e342e;
  }
  .banner {
    background-image: url('${context}/assets/img/banner.jpg');
    background-size: cover;
    background-position: center;
    height: 400px;
    display: flex;
    align-items: center;
    justify-content: center;
    color: white;
    font-size: 3rem;
    font-weight: bold;
    text-shadow: 2px 2px 5px rgba(0,0,0,0.6);
  }
  .menu-card img {
    height: 200px;
    object-fit: cover;
  }

  html, body {
    height: 100%;
    margin: 0;
    padding: 0;
    display: flex;
    flex-direction: column;
  }

  main {
      flex: 1;
  }



</style>
<link rel="icon" href="<c:url value='/assets/img/favicon.ico'/>" type="image/x-icon">

<sec:csrfMetaTags />