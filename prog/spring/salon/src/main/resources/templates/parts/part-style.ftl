<#macro style>

<style>
html {
background: url('https://i.pinimg.com/originals/f5/cc/bc/f5ccbc78638c97bec1faef157c85c8f4.jpg') no-repeat center center fixed;
-webkit-background-size: cover;
-moz-background-size: cover;
-o-background-size: cover;
background-size: cover;
}

body {
color: #ff8b3d;
font-family: 'Open Sans', sans-serif;
font-size: 16px;
}

#login-form {
background-color: #363636;
border-radius: 5px;
-moz-border-radius: 5px;
-webkit-border-radius: 5px;
margin: 150px auto;
width: 300px;
font-family: 'Open Sans', sans-serif;
font-size: 16px;
-webkit-box-shadow:  0px 10px 20px 0px rgba(0, 0, 0, 0.3);
box-shadow:  0px 10px 20px 0px rgba(0, 0, 0, 0.3);

}

#login-form h1 {
background-color: #292829;
border-radius: 5px 5px 0 0;
-moz-border-radius: 5px 5px 0 0;
-webkit-border-radius: 5px 5px 0 0;
color: #fff;
padding: 20px;
text-align: center;
text-transform: uppercase;
font-family: 'Open Sans', sans-serif;
font-size: 14px;
}

#login-form fieldset {
border: none;
padding: 20px;
position: relative;
}


#login-form fieldset form {
overflow: hidden;
width: 270px;
}

#login-form fieldset form input {
font-family: Arial, Helvetica, sans-serif;
font-size: 14px;
outline: none;
-webkit-appearance:none;
width: 260px;
align: center;
position: center;


}

#login-form fieldset form input[type="text"] {
border: 1px solid #292829;
border-radius: 3px 3px 0px 0px;
-moz-border-radius: 3px 3px 0px 0px;
-webkit-border-radius: 3px 3px 0px 0px;
padding: 12px 10px;
width: 238px;
-webkit-appearance:none;
}

#login-form fieldset form input[type="password"] {
border: 1px solid #292829;
border-top: none;
border-radius: 0px 0px 3px 3px;
-moz-border-radius: 0px 0px 3px 3px;
-webkit-border-radius: 0px 0px 3px 3px;
padding: 12px 10px;
width: 238px;
-webkit-appearance:none;
}

#login-form fieldset form input[type="submit"] {
background-color: #ebebeb;
border: none;
border-radius: 3px;
-moz-border-radius: 3px;
-webkit-border-radius: 3px;
color: #404040;
cursor: pointer;
float: none;
font-weight: bold;
margin-top: 20px;
padding: 12px 12px;
-webkit-appearance: none;
}

#login-form fieldset form input[type="submit"]:hover {
background-color: #e0e0e0;
}

#login-form fieldset form span:before {
background-color: #c9c9c9;
border-radius: 50%;
-moz-border-radius: 50%;
-webkit-border-radius: 50%;
content: "?";
display: inline-block;
height: 20px;
line-height: 20px;
margin-right: 10px;
text-align: center;
width: 20px;
}

#login-form fieldset form span {
font-size: 12px;
margin-top: 40px;
}

#login-form fieldset form span a{
color: #eb6d1a;
text-decoration: none;
}

#login-form fieldset form span a:hover {
color: #eb6d1a;
}

.vladmaxi-top{
line-height: 24px;
font-size: 11px;
background: #eee;
text-transform: uppercase;
z-index: 9999;
position: fixed;
top:0;
left:0;
width:100%;
font-family: calibri;
font-size: 13px;
box-shadow: 1px 0px 2px rgba(0,0,0,0.2);
-webkit-animation: slideOut 0.5s ease-in-out 0.3s backwards;
}
@-webkit-keyframes slideOut{
0%{top:-30px; opacity: 0;}
100%{top:0px; opacity: 1;}
}

.vladmaxi-top a{
padding: 0px 10px;
letter-spacing: 1px;
color: #333;
text-shadow: 0px 1px 1px #fff;
display: block;
float: left;
text-decoration:none;
}
.vladmaxi-top a:hover{
background: #fff;
}
.vladmaxi-top span.right{
float: right;
}
.vladmaxi-top span.right a {
float: left;
display: block;
}
</style>
</#macro>