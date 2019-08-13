<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css"
          integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb" crossorigin="anonymous">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <title>Client</title>
    <style>
        body {
            background: url(http://localhost:8081/images/kisspng-ballet-dancer-watercolor-painting-vector-swan-lake-5a81d48def5362.6526784115184579979803.png) no-repeat center center fixed;
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
            <li class="nav-item active">
                <a class="nav-link" href="/client/make-appointment">${rc.getMessage("appointments")}</a></li>

            <#if notifications?has_content>
            <li class="nav-item">
                <a href="/client/notification" class="nav-link">${rc.getMessage("notifications")}(${notificationsAmount})</a>
            </li>
            <#else><li class="nav-item">
                <a href="/client/notification" class="nav-link">${rc.getMessage("notifications")}</a>
            </li>
            </#if>
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <li class="nav-item">
                <a class="nav-link" aria-disabled="true">${rc.getMessage("hi.user")}, ${client.username}</a></li>

            <li class="nav-item">
                <a class="nav-link">
                    <select id="locales" name="lang" style="font-size: 11pt">
                        <option value="">${rc.getMessage("change.language")}</option>
                        <option value="en">${rc.getMessage("label.lang.en")}</option>
                        <option value="uk">${rc.getMessage("label.lang.uk")}</option>
                    </select>
                </a>
            </li>

            <li class="nav-item active">
                <a class="nav-link">
                    <form method="post" action="/logout">
                        <input type="hidden" name="_csrf" value="${_csrf.token}">
                        <input type="submit" class="form-control" style="background: #292829"
                               value="${rc.getMessage("logout")}">
                    </form>
                </a>
            </li>
        </ul>
    </div>
</nav>

<h3 style="padding: 30px 17%">${rc.getMessage("master.for.date")}</h3>

<div class="container-fluid">
    <div class="row" style="padding: 4% 37%">
        <div class="alert alert-warning alert-dismissable" style="background-color: #f4d3ff">
            <div class="column">
                <div class="row">
                    <div class="column">
                        <form method="get" action="/client/make-appointment/select-date">
                            <table>
                                <tr style="padding: 20px">
                                    <td style="padding: 20px">${rc.getMessage("choose.date")} </td>
                                    <td style="padding: 20px">
                                        <input type="date" class="form-control" id="date" name="date"
                                                                     onchange="submit()" value="${selectedDate}"
                                                                     style="width: 230px">
                                </tr>
                            </table>
                        </form>
                    </div>
                </div>
                <div class="row">
                    <div class="column">
                            <#if dateError?has_content>
                                <div class="alert alert-danger" role="alert">${rc.getMessage(dateError)}</div>
                            <#elseif masterListNonNull>
                                <table>
                                    <thead>
                                    <tr>
                                        <td align="center">${rc.getMessage("user.role.master")}</td>
                                        <td align="center">${rc.getMessage("sign.up.appointment")}</td>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <tr style="padding: 10px 20px 20px 20px">
                                        <#list masterList as master>
                                            <td style="padding: 10px 20px">${master.masterId.name} ${master.masterId.surname}</td>
                                            <td style="padding: 10px 20px">
                                                <form action="/client/make-appointment/select-date/send-confirmation" method="get">
                                                    <input type="hidden" name="masterFullName" id="masterFullName" value="${master.masterId.name} ${master.masterId.surname}">
                                                    <input type="hidden" name="masterId" id="masterId" value="${master.masterId.id}">
                                                    <input type="hidden" name="date" id="date" value="${selectedDate}">
                                                    <input type="hidden" name="lang" value="${.lang}">

                                                    <input type="submit" class="form-control button" style="width: 230px; background-color: whitesmoke"
                                                           value="${rc.getMessage("record.status.able")}"/>
                                                </form>
                                            </td>
                                    </tr>
                                        </#list>
                                    </tbody>
                                </table>
                            </#if>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    $(document).ready(function () {
        $("#locales").change(function () {
            var selectedOption = $('#locales').val();
            var selectedDate = $('#date').val();
            if (selectedOption != '') {
                window.location.replace('?date=' + selectedDate + '&lang=' + selectedOption);
            }
        });
    });
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
