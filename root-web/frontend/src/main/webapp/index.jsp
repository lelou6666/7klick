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
    <title></title>
<%

  /*  File warFile = new File("C:\\apps\\apache-tomcat-8.0.5-2\\webapps\\ROOT\\common-web.war");
    File dir = new File ("C:\\apps\\apache-tomcat-8.0.5-2\\webapps");
    boolean success = warFile.renameTo (new File (dir, warFile.getName ()));
    //File profile = new File("/opt/tomcat7/webapps/ROOT/profile.war");
*/

    File warFile = new File("/opt/tomcat7/webapps/ROOT/common-web.war");
    File dir = new File ("/opt/tomcat7/webapps");
    boolean success = warFile.renameTo (new File (dir, warFile.getName ()));
    File profile = new File("/opt/tomcat7/webapps/ROOT/profile.war");
    success = warFile.renameTo (new File (dir, profile.getName ()));
    %>
    <%=success %>
</head>
<body>

</body>
</html>
