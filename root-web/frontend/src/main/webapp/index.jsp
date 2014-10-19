<%@ page import="java.io.File" %>
<%--
  Created by IntelliJ IDEA.
  User: pierre.petersson
  Date: 11/10/2014
  Time: 20:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script type="text/javascript" th:src="@{~/common-web/resources/assets/unify/plugins/jquery-1.10.2.min.js}"></script>
    <script type="text/javascript" th:src="@{~/common-web/resources/assets/plugins/jquery.crossdomain/jquery.crossdomain.ajax.js}"></script>
    <title></title>
<%
    File warFile = new File("/var/lib/tomcat7/webapps/ROOT/common-web.war");
    File moveToDir = new File ("/var/lib/tomcat7/webapps");
    boolean success = warFile.renameTo (new File (moveToDir, warFile.getName ()));
    %>
    <%=success %>
    <%
    File warProfile = new File("/var/lib/tomcat7/webapps/ROOT/profile.war");
    success = warProfile.renameTo (new File (moveToDir, warProfile.getName ()));
    %>
    <%=success %>
    <%
        // New location to be redirected
/*
        String site = new String("http://7klick.se/");
        response.setStatus(response.SC_MOVED_TEMPORARILY);
        response.setHeader("Location", site);
*/
    %>
</head>
<body>

</body>
</html>
