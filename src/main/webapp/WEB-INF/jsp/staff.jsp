<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>${sessionScope.userId}登录成功</h1>
    <h2>欢迎您,${sessionScope.name}</h2>
    <h2>欢迎您,${sessionScope.department}</h2>
</body>
</html>
