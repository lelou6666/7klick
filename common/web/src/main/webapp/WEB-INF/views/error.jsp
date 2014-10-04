<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${cirrusError.userMessage}</title>
</head>
<body>
<div class="cirrus-error-content">
<div class="errorMessageUser">${cirrusError.userMessage}</div>
<div class="errorURL" style="visibility: hidden">${cirrusError.url}</div>
<div class="errorMessageTechnical" style="visibility: hidden">${cirrusError.technicalMessage}</div>
</div>
</body>
</html>
