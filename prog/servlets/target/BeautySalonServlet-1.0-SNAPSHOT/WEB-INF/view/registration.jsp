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
    <title><fmt:message key="registration"/></title>
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
<div id="login-form" style="width: 21%">
    <h1 style="width: auto"><fmt:message key="registration"/></h1>
    <fieldset style="width: auto">
        <label style="font-size: 9pt; text-align: left"><fmt:message key="enter.name.english"/></label><br/>
        <form method="post" action="${pageContext.request.contextPath}/register">
            <table>
                <tbody>
                <tr>
                    <td><fmt:message key="name"/>: </td>
                    <td><input type="text" class="form-control" name="name"
                               style="width: 250px" required pattern="[A-Z][a-z]+"></td>
                </tr>
                <tr>
                    <td><fmt:message key="surname"/>: </td>
                    <td><input type="text" class="form-control" name="surname"
                               style="width: 250px" required pattern="[A-Z][a-z]+"></td>
                </tr>
                <tr>
                    <td><fmt:message key="email"/>: </td>
                    <td><input type="email" class="form-control" name="email" style="width: 250px" required></td>
                </tr>
                </tbody>
            </table>
            <hr/>
            <table>
                <tbody>
                <tr>
                    <td><fmt:message key="username"/>: </td>
                    <td><input type="text" class="form-control" name="username_reg"
                               style="width: 250px" required pattern="[A-Za-z0-9_]{8,20}" maxlength="20"></td>
                </tr>
                <tr>
                    <td><fmt:message key="password"/>: </td>
                    <td><input type="password" class="form-control" name="password" id="password1"
                               style="width: 250px"required pattern="[A-Za-z0-9_]{8,20}" maxlength="20"></td>
                </tr>
                <tr>
                    <td><fmt:message key="repeat.password"/> </td>
                    <td><input type="password" class="form-control" name="repeatedPassword" id="password2"
                               style="width: 250px" required pattern="[A-Za-z0-9_]{8,20}"></td>
                </tr>
                </tbody>
            </table>
            <br/>
            <input type="submit" class="form-control" value="Sign up">
        </form>
    </fieldset>
</div>
<script type="text/javascript">
    window.onload = function () {
        document.getElementById("password1").onchange = validatePassword;
        document.getElementById("password2").onchange = validatePassword;
    };

    function validatePassword(){
        var pass2=document.getElementById("password2").value;
        var pass1=document.getElementById("password1").value;
        if(pass1!=pass2)
            document.getElementById("password2").setCustomValidity("Passwords don't match");
        else
            document.getElementById("password2").setCustomValidity('');
    }
</script>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
        integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>
</body>
</html>