<#import "parts/part-style.ftl" as style>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>${rc.getMessage("registration")}</title>
    <link rel="icon" href="http://vladmaxi.net/favicon.ico" type="image/x-icon">
    <link rel="shortcut icon" href="http://vladmaxi.net/favicon.ico" type="image/x-icon">
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

<div id="login-form" style="width: 21%">
    <h1 style="width: auto">${rc.getMessage("registration")}</h1>
    <fieldset style="width: auto">
        <label style="font-size: 9pt; text-align: left">${rc.getMessage("enter.name.english")}</label><br/>
        <form method="post" action="/registration/register">
            <#if usernameError>
                <div class="alert alert-danger" role="alert">
                    ${rc.getMessage("error.username")}
                </div>
            </#if>

            <table>
                <tbody>
                <tr>
                    <td>${rc.getMessage("name")}: </td>
                    <td><input type="text" class="form-control" id="name" name="name"
                               style="width: 250px" required pattern="[A-Z][a-z]+"></td>
                </tr>
                <tr>
                    <td>${rc.getMessage("surname")}: </td>
                    <td><input type="text" class="form-control" id="surname" name="surname"
                               style="width: 250px" required pattern="[A-Z][a-z]+"></td>
                </tr>
                <tr>
                    <td>${rc.getMessage("email")}: </td>
                    <td><input type="email" class="form-control" id="email" name="email" style="width: 250px" required></td>
                </tr>
                </tbody>
            </table>
            <hr/>
            <table>
                <tbody>
                <tr>
                    <td>${rc.getMessage("username")}: </td>
                    <td><input type="text" class="form-control" id="usernameReg" name="usernameReg"
                               style="width: 250px" required pattern="[A-Za-z0-9_]{8,20}" maxlength="20"></td>
                </tr>
                <tr>
                    <td>${rc.getMessage("password")}: </td>
                    <td><input type="password" class="form-control" name="password" id="password"
                               style="width: 250px"required pattern="[A-Za-z0-9_]{8,20}" maxlength="20"></td>
                </tr>
                <tr>
                    <td>${rc.getMessage("repeat.password")}: </td>
                    <td><input type="password" class="form-control" name="repeatedPassword" id="repeatedPassword"
                               style="width: 250px" required pattern="[A-Za-z0-9_]{8,20}"></td>
                </tr>
                </tbody>
            </table>
            <br/>
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <input type="submit" class="form-control" value="${rc.getMessage("sign.up")}">
        </form>
    </fieldset>
</div>
<script type="text/javascript">
    window.onload = function () {
        document.getElementById("password").onchange = validatePassword;
        document.getElementById("repeatedPassword").onchange = validatePassword;
    };


    function validatePassword(){
        var pass2=document.getElementById("repeatedPassword").value;
        var pass1=document.getElementById("password").value;
        if(pass1!=pass2)
            document.getElementById("repeatedPassword").setCustomValidity("Passwords don't match");
        else
            document.getElementById("repeatedPassword").setCustomValidity('');
    }


    $(document).ready(function() {
        $("#locales").change(function () {
            var selectedOption = $('#locales').val();
            if (selectedOption != ''){
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