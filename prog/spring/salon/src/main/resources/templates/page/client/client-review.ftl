<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css"
          integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb" crossorigin="anonymous">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <title>${rc.getMessage("user.role.client")}</title>
    <style>
        body {
            background: url(http://localhost:8081/images/kisspng-ballet-dancer-drawing-painting-5c0d8256b97290.1365284715443892067596.png) no-repeat center center fixed;
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
                <a href="/client/make-appointment" class="nav-link">${rc.getMessage("appointments")}</a>
            </li>
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

            <li class="nav-item ">
                <a class="nav-link">${rc.getMessage("hi.user")}, ${client.username}</a></li>

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
<br/>
<div class="container-fluid">
    <div class="row">
        <div class="column" style="width: 40%">
        </div>
        <div class="column" style="width: 55%">
            <div class="alert" style="background-color: #f4d3ff">
                <i>${rc.getMessage("procedure")}:</i> <strong>${procedure}</strong>
                <input type="hidden" id="procedure" value="${procedure}">
                <input type="hidden" id="record" value="${record}">
                <input type="hidden" id="email" value="${email}">
                <i>${rc.getMessage("user.role.master")}:</i> <strong>${masterName} ${masterSurname}</strong><br/>
                <input type="hidden" id="masterName" value="${masterName}">
                <input type="hidden" id="masterSurname" value="${masterSurname}">
                <form method="post" action="/client/feedback/submit">
                    <input type="hidden" id="record" name="record" value="${record}">
                    <input type="hidden" id="master" name="master" value="${master}">
                    <input type="hidden" id="email" name="email" value="${email}">
                    <input type="hidden" name="lang" value="${.lang}">
                ${rc.getMessage("feedback.text")}
                    <textarea id="feedbackText" name="feedbackText" class="form-control"></textarea>
                    <input type="hidden" name="_csrf" value="${_csrf.token}">
                    <input type="submit" value="${rc.getMessage("submit")}" class="form-control button"
                           style="width: 200px">
                </form>
            </div>
        </div>
        <div class="column" style="width: 5%"></div>
    </div>
</div>
<script type="text/javascript">
    $(document).ready(function () {
        $("#locales").change(function () {
            var selectedOption = $('#locales').val();
            var selectedMaster = $('#master').val();
            var selectedMasterName = $('#masterName').val();
            var selectedMasterSurname = $('#masterSurname').val();
            var selectedProcedure = $('#procedure').val();
            var selectedRecord = $('#record').val();
            var selectedEmail = $('#email').val();
            if (selectedOption != '') {
                window.location.replace('?master=' + selectedMaster + '&masterName=' + selectedMasterName +
                        '&masterSurname=' + selectedMasterSurname +'&procedure=' + selectedProcedure +
                        '&record=' + selectedRecord + '&email=' + selectedEmail +'&lang=' + selectedOption);
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
