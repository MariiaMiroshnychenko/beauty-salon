<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css"
          integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb" crossorigin="anonymous">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <title>${rc.getMessage("user.role.admin")}</title>
    <style>
        body {
            background: url(http://localhost:8081/images/dc4c183f-da97-4c3b-910e-37bb8c38ae49.png) no-repeat center center fixed;
            background-size: cover;
        }
    </style>
    <link rel="stylesheet" href="http://localhost:8081/css/admin_menu_style.css" media="screen" type="text/css"/>
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
                <a class="nav-link" aria-disabled="true">${rc.getMessage("hi.user")}, ${admin.username}</a></li>

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

<div class="container-fluid">
    <div class="row">
        <div class="column"></div>
        <div class="menu">
            <ul>
                <li><a href="/admin/master-feedback"><span
                        class="fa fa-book"></span>${rc.getMessage("feedback")}</a></li>

                <li>
                    <a href="/admin/salon-schedule"><span
                            class="fa fa-pencil"></span>${rc.getMessage("appointments")}</a></li>

            </ul>
        </div>
        <div class="column" style="padding: 30px">
            <fieldset>
                <form method="get" action="/admin/master-feedback/submit">
                    <label for="master">${rc.getMessage("choose.master")}</label>
                    <select id="master" class="form-control" style="width: 250px" name="master">
                    <#list masters as master>
                        <option value="${master.id}">${master.name} ${master.surname}</option>
                    </#list>
                    </select>
                    <input type="hidden" name="lang" value="${.lang}">
                    <input type="submit" style="width: 250px" class="form-control">
                </form>
            </fieldset>
            <br/>
            <div style="align-content: center">
            <#if reviewsForMasterNonNull>
                <table class="table-bordered" style="background-color: whitesmoke">
                    <thead>
                    <tr>
                        <td align="center">${rc.getMessage("visit.date")}</td>
                        <td align="center">${rc.getMessage("procedure")}</td>
                        <td align="center">${rc.getMessage("user.role.master")}</td>
                        <td align="center" style="width: 300px">${rc.getMessage("feedback.text")}</td>
                        <td align="center">${rc.getMessage("user.role.client")}</td>
                    </tr>
                    </thead>
                    <tbody>

                <#list reviewsForMaster.content as rev>
                <tr style="padding: 5px">
                    <td align="center" style="width: 100px">${rev.recordId.recordDate}</td>
                    <td align="center" style="width: 150px">${rev.recordId.procedureId.name}</td>
                    <td align="center" style="width: 200px">
                        <input type="hidden" id="selectedMasterId" name="selectedMasterId"
                               value="${rev.masterId.id}">
                        ${rev.masterId.name} ${rev.masterId.surname}</td>
                    <td style="width: 300px">${rev.text}</td>
                    <td align="center" style="width: 150px">${rev.clientId.username}</td>
                </tr>
                </#list>

                    </tbody>
                </table>
                <#if reviewsForMaster.getTotalPages() gt 7>
                    <#assign
                    totalPages = reviewsForMaster.getTotalPages()
                    pageNumber = reviewsForMaster.getNumber() + 1

                    head = (pageNumber > 4)?then([1, -1], [1, 2, 3])
                    tail = (pageNumber < totalPages - 3)?then([-1, totalPages], [totalPages - 2, totalPages - 1, totalPages])
                    bodyBefore = (pageNumber > 4 && pageNumber < totalPages - 1)?then([pageNumber - 2, pageNumber - 1], [])
                    bodyAfter = (pageNumber > 2 && pageNumber < totalPages - 3)?then([pageNumber + 1, pageNumber + 2], [])

                    body = head + bodyBefore + (pageNumber > 3 && pageNumber < totalPages - 2)?then([pageNumber], []) + bodyAfter + tail
                    >
                <#else>
                    <#assign body = 1..reviewsForMaster.getTotalPages()>
                </#if>
                <div style="padding: 5px 0 0 0;">
                    <ul class="pagination pagination-sm">
                        <li class="page-item disabled">
                            <a class="page-link" href="" tabindex="-1">${rc.getMessage("pages")}</a>
                        </li>
    <#list body as p>
        <#if (p - 1) == reviewsForMaster.getNumber()>
        <li class="page-item active">
            <a class="page-link" href="#" tabindex="-1">${p}</a>
        </li>
        <#elseif p == -1>
                    <li class="page-item disabled">
                        <a class="page-link" href="#" tabindex="-1">...</a>
                    </li>
        <#else>
                    <li class="page-item">
                        <a class="page-link"
                           href="/admin/master-feedback/submit?master=${master}&lang=${.lang}&page=${p - 1}&size=${reviewsForMaster.getSize()}"
                           tabindex="-1">${p}</a>
                    </li>
        </#if>
    </#list>
                    </ul>
                </div>
            </#if>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    $(document).ready(function () {
        $('#locales').change(function () {
            var selectedOption = $('#locales').val();
            var selectedMaster = $('#selectedMasterId').val();
            if (selectedOption != '' || selectedMaster != 0) {
                window.location.replace('?master=' + selectedMaster + '&lang=' + selectedOption);
            } else {
                selectedOption = 'en';
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
