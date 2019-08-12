<#import "../parts/part-style.ftl" as style>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>${rc.getMessage("authorization")}</title>
    <link rel="icon" href="http://vladmaxi.net/favicon.ico" type="image/x-icon">
    <link rel="shortcut icon" href="http://vladmaxi.net/favicon.ico" type="image/x-icon">
    <link rel='stylesheet' href='http://localhost:8081/static/css/style.css' type='text/css'/>
    <link href='http://fonts.googleapis.com/css?family=Open+Sans:400,700' rel='stylesheet' type='text/css'>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <@style.style/>
</head>

<body>

<div align="right">
        <select id="locales" name="lang" style="font-size: 11pt">
        <option value="">${rc.getMessage("change.language")}</option>
        <option value="en">${rc.getMessage("label.lang.en")}</option>
        <option value="uk">${rc.getMessage("label.lang.uk")}</option>
        </select>
</div>
<div id="login-form">
    <h1>${rc.getMessage("authorization")}</h1>
    <fieldset>
        <form method="post" action="/login">
            <input type="text" name="username" placeholder="${rc.getMessage("username")}" required>
            <input type="password" name="password" placeholder="${rc.getMessage("password")}" required>

            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <input type="submit" value="${rc.getMessage("sign.in")}">
        </form>
    ${rc.getMessage("no.account")} <a href="/registration">${rc.getMessage("sign.up")}</a>
    </fieldset>
</div>

<script type="text/javascript">
    $(document).ready(function() {
        $("#locales").change(function () {
            var selectedOption = $('#locales').val();
            if (selectedOption != ''){
                window.location.replace('?lang=' + selectedOption);
            }
        });
    });
</script>
</body>
</html>