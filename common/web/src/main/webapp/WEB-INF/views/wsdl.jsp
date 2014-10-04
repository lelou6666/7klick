<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page session="false" %>
<!DOCTYPE html>
<html lang="en">
<html>
<c:import url="navbar.jsp"/>
<div class="col-lg-4">
    <form id="formWsdl" class="form-horizontal" action="" method="post" target="_blank">
        <fieldset>
            <div class="control-group">
                <label class="control-label" for="moduleName">Module</label>

                <div class="controls">
                    <select id="moduleName" name="moduleName" class="form-control" type="text" class="input-large"
                            required="">
                        <option name="modulename" value="/common-web">Common</option>
                        <option name="modulename" value="/profile">Profile</option>
                    </select>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label" for="signin"></label>

                <div class="controls">
                    <button id="signin" name="signin" type="button" onclick="setWSDLPath()" class="btn btn-success">Open WSDL</button>
                </div>
            </div>
        </fieldset>
    </form>
    <script>
        function setWSDLPath(){
            $("#formWsdl").attr("action", $("#moduleName").val()+"/resources/ws");
            $("#formWsdl").submit();

        }
    </script>
</div>
</html>