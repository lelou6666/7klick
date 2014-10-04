<%--
  Created by IntelliJ IDEA.
  User: pierre.petersson
  Date: 4/6/2014
  Time: 3:11 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <title>7klick portal</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <!-- Bootstrap -->
    <link href="${pageContext.request.contextPath}/resources/assets/unify/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen">
    <script src="${pageContext.request.contextPath}/resources/assets/unify/plugins/jquery-1.10.2.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/assets/unify/plugins/bootstrap/js/bootstrap.min.js"></script>
</head>
<nav class="navbar navbar-default" role="navigation">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>
        <a class="navbar-brand" href="/common-web">7klick portal</a>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
        <ul class="nav navbar-nav">
            <li><a href="${pageContext.request.contextPath}/util/ticket/init">Login to module</a></li>
            <li><a href="${pageContext.request.contextPath}/util/wsdl/init">Webservices WSDL</a></li>
        </ul>
    </div><!-- /.navbar-collapse -->
</nav>
