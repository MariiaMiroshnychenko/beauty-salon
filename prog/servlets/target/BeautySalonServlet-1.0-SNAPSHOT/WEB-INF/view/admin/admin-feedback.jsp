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
    <title>Admin</title>
    <style>
        body {
            background: url(http://localhost:8080/images/dc4c183f-da97-4c3b-910e-37bb8c38ae49.png) no-repeat center center fixed;
            background-size: cover;
        }
    </style>
    <link rel="stylesheet" href="http://localhost:8080/css/admin_menu_style.css" media="screen" type="text/css"/>
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
        <ul class="navbar-nav mr-auto"></ul>
        <ul class="nav navbar-nav navbar-right">
            <li class="nav-item">
                <a class="nav-link" aria-disabled="true">Hi, ${sessionScope.user.username}</a></li>

            <li class="nav-item">
                <a class="nav-link">
                    <c:choose>
                        <c:when test="${not empty param.master}">
                            <form method="get"
                                  action="${pageContext.request.contextPath}/salon/admin/master-feedback/master-submit">
                                <label for="language"></label>

                                <input type="hidden" name="master" value="${param.master}">
                                <select id="language1" name="language"
                                        onchange="submit()" style="font-size: 11pt">
                                    <option value="en" ${language == 'en' ? 'selected' : ''}><fmt:message
                                            key="label.lang.en"/></option>
                                    <option value="uk" ${language == 'uk' ? 'selected' : ''}><fmt:message
                                            key="label.lang.uk"/></option>
                                </select>
                            </form>
                        </c:when>
                        <c:otherwise>
                            <form method="get"
                                  action="${pageContext.request.contextPath}/salon/admin/master-feedback">
                                <label for="language"></label>

                                <input type="hidden" name="master" value="${param.master}">
                                <select id="language" name="language"
                                        onchange="submit()" style="font-size: 11pt">
                                    <option value="en" ${language == 'en' ? 'selected' : ''}><fmt:message
                                            key="label.lang.en"/></option>
                                    <option value="uk" ${language == 'uk' ? 'selected' : ''}><fmt:message
                                            key="label.lang.uk"/></option>
                                </select>
                            </form>
                        </c:otherwise>
                    </c:choose>
                </a>
            </li>

            <li class="nav-item active">
                <a href="${pageContext.request.contextPath}/logout" class="nav-link">Logout</a>
            </li>
        </ul>
    </div>
</nav>

<div class="container-fluid">
    <div class="row">
        <div class="column"></div>
        <div class="menu">
            <ul>
                <li><a href="${pageContext.request.contextPath}/salon/admin/master-feedback"><span
                        class="fa fa-book"></span>Reviews</a></li>

                <li>
                    <a href="${pageContext.request.contextPath}/salon/admin/master-records?date=${sessionScope.availableDate}"><span
                            class="fa fa-pencil"></span>Appointments</a></li>

            </ul>
        </div>
        <div class="column" style="padding: 30px">
            <form method="get" action="${pageContext.request.contextPath}/salon/admin/master-feedback/master-submit">
                <label for="master">Choose master</label>
                <select id="master" class="form-control" style="width: 250px" name="master">
                    <c:forEach var="master" items="${sessionScope.masters}">
                        <option value="${master.id}">
                                ${master.name} ${master.surname}
                        </option>
                    </c:forEach>

                </select>
                <input type="submit" style="width: 250px" class="form-control">
            </form>
            <table class="table table-bordered" style="background-color: #eeeeee">
                <thead>
                <tr>
                    <th align="center">Visit date</th>
                    <th align="center">Procedure</th>
                    <th align="center">Master</th>
                    <th align="center" style="width: 300px">Review text</th>
                    <th align="center">Client username</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="rev" items="${requestScope.reviewsForMaster}">
                    <tr>
                        <td>${rev.record.recordDate}</td>
                        <td>${rev.record.procedure.name}</td>
                        <td>${rev.master.name} ${rev.master.surname}</td>
                        <td style="width: 300px">${rev.text}</td>
                        <td>${rev.client.username}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
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
