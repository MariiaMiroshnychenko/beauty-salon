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
            background: url(http://localhost:8080/images/kisspng-ballet-dancer-watercolor-painting-vector-swan-lake-5a81d48def5362.6526784115184579979803.png) no-repeat center center fixed;
            background-size: cover;
        }
    </style>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
</head>

<body>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <button class="navbar-toggler mr-auto" type="button" data-toggle="collapse"
            data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
            aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item dropdown active">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown"
                   aria-haspopup="true" aria-expanded="false">
                    Appointments
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                    <a class="dropdown-item"
                       href="${pageContext.request.contextPath}/salon/client/client-past-records?language=${sessionScope.language}">Past
                        appointments</a>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item"
                       href="${pageContext.request.contextPath}/salon/client/client-future-records?language=${sessionScope.language}">Future
                        appointments</a>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item" href="${pageContext.request.contextPath}/salon/client/make-appointment">To
                        make appointment </a>
                </div>
            </li>

            <li class="nav-item active">
                <a href="${pageContext.request.contextPath}/salon/client/feedback?language=${sessionScope.language}"
                   class="nav-link">To write feedback</a>
            </li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <li class="nav-item">
                <a class="nav-link" aria-disabled="true">Hi, ${sessionScope.user.username}</a></li>

            <li class="nav-item">
                <a class="nav-link">
                    <form method="get"
                          action="${pageContext.request.contextPath}/salon/client/make-appointment-confirm">
                        <label for="language"></label>

                        <input type="hidden" name="date" value="${param.date}">
                        <input type="hidden" name="masterId" value="${param.masterId}">
                        <input type="hidden" name="masterFullName" value="${param.masterFullName}">
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
    <div class="row" style="padding: 6% 39%">
        <div class="alert alert-warning" style="background-color: #f4d3ff">
            <form action="${pageContext.request.contextPath}/salon/client/make-appointment-success" method="get">

                <table>
                    <thead>
                    <tr>
                        <td align="center" style="padding: 20px">Your choice</td>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td align="center">Master:</td>
                        <td align="right" style="width: 230px; padding: 10px">
                            <input type="hidden" name="masterId" id="masterId" value="${param.masterId}">
                            <p>${param.masterFullName} </p></td>
                    </tr>
                    <tr>
                        <td align="center">Date:</td>
                        <input type="hidden" name="date" id="date" value="${param.date}">
                        <td align="right" style="width: 230px; padding: 10px"><p> ${param.date} </p></td>
                    </tr>
                    <tr>
                        <td align="center">Time:</td>
                        <td align="right" style="padding: 10px">
                            <select id="time" name="time" class="form-control" style="font-size: 11pt; width: 230px;align-items: center">
                                <c:forEach var="t" items="${sessionScope.availableTimes}">
                                    <option value="${t}">${t}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td align="center">Procedure:</td>
                        <td align="right" style="padding: 10px">
                            <select id="procedure" class="form-control" name="procedure" style="font-size: 11pt; width: 230px">
                                <c:forEach var="p" items="${sessionScope.procedures}">
                                    <option value="${p.id}">${p.name}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    </tbody>
                    <tfoot>
                    <tr>
                        <td></td>
                        <td align="center" style="padding: 10px">
                            <input type="submit" class="form-control button btn-success" style="width: 230px"
                                   value="<fmt:message key="record.status.able"/>"/>
                        </td>
                    </tr>
                    </tfoot>
                </table>
            </form>
        </div>
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
