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
            background: url(http://localhost:8081/images/dc4c183f-da97-4c3b-910e-37bb8c38ae49.png) no-repeat center center fixed;
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
            <li class="nav-item">
                <a href="/client/make-appointment" class="nav-link">${rc.getMessage("appointments")}</a>
            </li>

            <#if notifications?has_content>
            <li class="nav-item">
                <a href="/client/notification" class="nav-link">${rc.getMessage("notifications")}(${notificationsAmount})</a>
            </li>
            <#else>
                <li class="nav-item">
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

<div class="row">
    <#if notifications.content?has_content>
        <div class="column" style="padding: 10px 35% 5px 5%">
        <#list notifications.content as n>
        <div>
            <table>
                <tbody style="background-color: #f4d3ff">
                <tr>
                    <td>${rc.getMessage("from")}: <b>beauty_salon</b></td>
                </tr>
                <tr>
                    <td>${rc.getMessage("to")}: ${client.username}</td>
                </tr>
                <tr>
                    <td>${n.text}</td>
                </tr>
                <tr>
                    <td>
                        ${rc.getMessage("to.leave.feedback")} <a
                            href="/client/feedback?master=${n.recordId.masterId.id}&masterName=${n.recordId.masterId.name}&masterSurname=${n.recordId.masterId.surname}&procedure=${n.recordId.procedureId.name}&record=${n.recordId.id}&email=${n.id}">${rc.getMessage("this.link")}</a>
                    </td>
                </tr>
                </tbody>
            </table><br/>
        </#list>

        <#if notifications.getTotalPages() gt 7>
            <#assign
            totalPages = notifications.getTotalPages()
            pageNumber = notifications.getNumber() + 1

            head = (pageNumber > 4)?then([1, -1], [1, 2, 3])
            tail = (pageNumber < totalPages - 3)?then([-1, totalPages], [totalPages - 2, totalPages - 1, totalPages])
            bodyBefore = (pageNumber > 4 && pageNumber < totalPages - 1)?then([pageNumber - 2, pageNumber - 1], [])
            bodyAfter = (pageNumber > 2 && pageNumber < totalPages - 3)?then([pageNumber + 1, pageNumber + 2], [])

            body = head + bodyBefore + (pageNumber > 3 && pageNumber < totalPages - 2)?then([pageNumber], []) + bodyAfter + tail
            >
        <#else>
            <#assign body = 1..notifications.getTotalPages()>
        </#if>
        </div>
        <div>
            <ul class="pagination pagination-sm">
                <li class="page-item disabled">
                    <a class="page-link" href="" tabindex="-1">${rc.getMessage("pages")}</a>
                </li>
    <#list body as p>
        <#if (p - 1) == notifications.getNumber()>
        <li class="page-item active">
            <a class="page-link" href="#" tabindex="-1">${p}</a>
        </li>
        <#elseif p == -1>
                    <li class="page-item disabled">
                        <a class="page-link" href="#" tabindex="-1">...</a>
                    </li>
        <#else>
                    <li class="page-item">
                        <a class="page-link" href="/client/notification?page=${p - 1}&size=${notifications.getSize()}"
                           tabindex="-1">${p}</a>
                    </li>
        </#if>
    </#list>
            </ul>
        </div>
    </div>
    <#else><label style="padding: 30px;">${rc.getMessage("no.notifications")}</label>
    </#if>
</div>
<script type="text/javascript">
    $(document).ready(function () {
        $("#locales").change(function () {
            var selectedOption = $('#locales').val();
            if (selectedOption != '') {
                window.location.replace('?lang=' + selectedOption);
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
