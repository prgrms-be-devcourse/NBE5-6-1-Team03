<%@ page isErrorPage="true" contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@include file="/WEB-INF/view/include/page.jsp" %>
<head>
    <title>Sorry, we can't find that page!</title>
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <link rel="icon" href="favicon.ico" type="image/x-icon"/>
    <style>
      .not-found-page-wrapper {
        display: flex;
        align-items: center;
        justify-content: center;
        min-height: 100vh;
        flex-direction: column;
        text-align: center;
      }
      .not-found-page-wrapper img {
        max-width: 400px;
        margin-bottom: 20px;
      }
    </style>
</head>
<body>
    <div class="not-found-page-wrapper">
        <img src="/assets/img/404.png" alt="404 Error" />
        <h3>Sorry, we can't find that page.</h3>
        <h3><a href="${context}/">Click here to go home.</a></h3>
    </div>
</body>
</html>
