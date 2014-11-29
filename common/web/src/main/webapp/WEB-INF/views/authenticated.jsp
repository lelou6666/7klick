<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page session="false" %>
<!DOCTYPE html>
<html lang="en">
<html>
<c:import url="navbar.jsp"/>
<!-- Button trigger modal -->
<div class="col-lg-4">
    <form class="form-horizontal" action="${pageContext.request.contextPath}/util/ticket" method="post">
        <fieldset>

            <!-- Sign Up Form -->
            <!-- Text input-->
            <div class="control-group">
                <label class="control-label" for="domain">Domain:</label>

                <div class="controls">
                    <input id="domain" name="domain" class="form-control" type="text" class="input-large" required="">
                </div>
            </div>

            <!-- Text input-->
            <div class="control-group">
                <label class="control-label" for="username">Username:</label>

                <div class="controls">
                    <input id="username" name="username" class="form-control" type="text" class="input-large"
                           required="">
                </div>
            </div>
            <!-- Text input-->
            <div class="control-group">
                <label class="control-label" for="password">Password:</label>

                <div class="controls">
                    <input id="password" name="password" class="form-control" type="password" class="input-large"
                           required="">
                </div>
            </div>

            <div class="control-group">
                <label class="control-label" for="createTicket"></label>

                <div class="controls">
                    <button id="createTicket" name="createTicket" class="btn btn-success">Create authentication ticket
                    </button>
                </div>
            </div>
        </fieldset>
    </form>

</div>
<c:set value="${ticket}" var="ticketGenerated" scope="page"/>
<%if (pageContext.getAttribute("token") != null) {%>
<form class="form-horizontal">
    <fieldset>
        <!-- Text input-->
        <div class="control-group">
            <label class="col-md-3 control-label" for="message">Your ticket</label>

            <div class="col-md-8">
                <textarea class="form-control" id="message" name="message"
                          rows="10"><%=pageContext.getAttribute("ticketGenerated")%>
                </textarea>
            </div>
        </div>

        <c:set value="${moduleName}" var="moduleName" scope="page"/>
        <div class="control-group">
            <label class="col-md-3 control-label" for="message">Sign in to</label>

            <div class="col-md-8">
                <select id="moduleName" onmouseup="setModuleURL()" name="moduleName" class="form-control" type="text" class="input-large"
                        required="">
                    <option name="modulename" value="/profile">Profile</option>
                </select>
            </div>
        </div>

        <c:set value="${moduleName}" var="moduleName" scope="page"/>
        <div class="control-group">
            <label class="col-md-3 control-label" for="message"></label>

            <div class="col-md-8">
                <br>
                <a id="linkDynamic" target="_blank" onclick="signOnToModule()" href="">Sign in ${moduleName}</a>
            </div>
        </div>
    </fieldset>
</form>
<script>
    $("#linkDynamic").attr("href",$("#moduleName").val()+"?ticket=${ticketGenerated}");
    function setModuleURL(){
        $("#linkDynamic").attr("href",$("#moduleName").val()+"?ticket=${ticketGenerated}");
    }
</script>
<%}%>
</html>