<%--
  Created by IntelliJ IDEA.
  User: marie
  Date: 16.07.2019
  Time: 16:29
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<%@ taglib uri='http://java.sun.com/jsp/jstl/fmt' prefix='fmt' %>
<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : 'en'}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="message"/>

<!doctype html>
<html lang="${language}">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css"
          integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb" crossorigin="anonymous">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <title>Client</title>
    <style>
        body {
            background: url(http://localhost:8080/images/kisspng-ballet-dancer-drawing-painting-5c0d8256b97290.1365284715443892067596.png) no-repeat center center fixed;
            background-size: cover;
        }
    </style>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
</head>

<body >

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <button class="navbar-toggler mr-auto" type="button" data-toggle="collapse"
            data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
            aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item dropdown active">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    My records
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                    <a class="dropdown-item" href="${pageContext.request.contextPath}/salon/client/client-past-records?language=${sessionScope.language}">Past records</a>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item" href="${pageContext.request.contextPath}/salon/client/client-future-records?language=${sessionScope.language}">Future records</a>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item" href="${pageContext.request.contextPath}/salon/client/make-appointment">To make appointment </a>
                </div>
            </li>

            <li class="nav-item active">
                <a href="${pageContext.request.contextPath}/salon/client/feedback?language=${sessionScope.language}" class="nav-link">To write feedback</a>
            </li>
        </ul>
        <ul class="nav navbar-nav navbar-right">

            <li class="nav-item ">
                <a class="nav-link">Hi, ${sessionScope.user.username}</a></li>

            <li class="nav-item">
                <a class="nav-link">
                <form method="get"
                      action="${pageContext.request.contextPath}/salon/client/feedback">
                    <label for="language"></label>

                    <select id="language" name="language"
                            onchange="submit()" style="font-size: 11pt">
                        <option value="en" ${language == 'en' ? 'selected' : ''}><fmt:message
                                key="label.lang.en"/></option>
                        <option value="uk" ${language == 'uk' ? 'selected' : ''}><fmt:message
                                key="label.lang.uk"/></option>
                    </select>
                </form>
                </a>
            </li>

            <li class="nav-item active">
                <a href="${pageContext.request.contextPath}/logout" class="nav-link">Logout</a>
            </li>
        </ul>
    </div>
</nav>

<div class="container-fluid">
    <br/>
    <div class="row">
        <div class="column" style="width: 40%">
        </div>
        <div class="column" style="width: 55%">
            <c:forEach var="record" items="${requestScope.recordsWithoutFeedback}">
                <div class="alert alert-warning alert-dismissable" style="background-color: #f4d3ff">
                    <form method="post" action="${pageContext.request.contextPath}/salon/client/submit">
                        <a href="#" class="close" data-dismiss="alert" aria-label="Close">&times;</a>
                        <i>Visit date:</i> <strong>${record.recordDate}</strong>
                            <i>Procedure:</i> <strong>${record.procedure.name}</strong>
                            <i>Master:</i> <strong>${record.master.name} ${record.master.surname}</strong><br/>
                        <input type="hidden" name="recordId" value="${record.id}">
                        <input type="hidden" name="masterId" value="${record.master.id}">
                        <input type="hidden" name="language" value="${sessionScope.language}">
                        Input your feedback:
                        <textarea id="reviewText" name="reviewText" class="form-control"></textarea>
                        <input type="submit" value="Submit" class="form-control button" style="width: 100px">
                    </form>
                </div>
            </c:forEach>
        </div>
        <div class="column" style="width: 5%"></div>
    </div>
</div>
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
