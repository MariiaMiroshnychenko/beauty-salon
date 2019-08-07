<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<%@ taglib uri='http://java.sun.com/jsp/jstl/fmt' prefix='fmt' %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : 'en'}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="message"/>

<!doctype html>
<html lang="${language}">
<head>
    <meta charset="UTF-8">
    <title>Authorization</title>
    <link rel="icon" href="http://vladmaxi.net/favicon.ico" type="image/x-icon">
    <link rel="shortcut icon" href="http://vladmaxi.net/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" href="http://localhost:8080/css/style.css" media="screen" type="text/css"/>
    <link href='http://fonts.googleapis.com/css?family=Open+Sans:400,700' rel='stylesheet' type='text/css'>
</head>

<body>

<div align="right">
    <form method="get"
          action="${pageContext.request.contextPath}">
        <label for="language"></label>

        <select id="language" name="language"
                onchange="submit()" style="font-size: 11pt">
            <option value="en" ${language == 'en' ? 'selected' : ''}><fmt:message key="label.lang.en"/></option>
            <option value="uk" ${language == 'uk' ? 'selected' : ''}><fmt:message key="label.lang.uk"/></option>
        </select>
    </form>
</div>
<div id="login-form">
    <h1>АВТОРИЗАЦИЯ</h1>
    <fieldset>
        <form method="post" action="${pageContext.request.contextPath}/salon">
            <input type="text" name="username" placeholder="username" required>
            <input type="password" name="password" placeholder="password" required>
            <input type="submit" value="ВОЙТИ">
        </form>
        Don't you have an account? <a href="${pageContext.request.contextPath}/registration" data-toggle="modal" data-target="#myModal">Sign up</a>
    </fieldset>
</div>
</body>
</html>