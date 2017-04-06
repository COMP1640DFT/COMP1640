<%-- 
    Document   : newjsp
    Created on : Mar 4, 2017, 12:14:32 AM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <!--<link rel="stylesheet" href="css/bootstrap-theme.min.css">-->
    <link rel="stylesheet" href="css/bootstrap-material-design.min.css">
    <link rel="stylesheet" href="css/custom.css">
    <link href="https://fonts.googleapis.com/css?family=Noto+Sans:400,700&amp;subset=vietnamese" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Lobster" rel="stylesheet">
         <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
    <title>Sign in</title>
</head>
<body>
    <script>
            $(function() {

                if (localStorage.chkbx && localStorage.chkbx != '') {
                    $('#checkremember').attr('checked', 'checked');
                    $('#username').val(localStorage.usrname);
                    $('#password').val(localStorage.pass);
                } else {
                    $('#checkremember').removeAttr('checked');
                    $('#username').val('');
                    $('#password').val('');
                }

                $('#checkremember').click(function() {

                    if ($('#checkremember').is(':checked')) {
                        // save username and password
                        localStorage.usrname = $('#username').val();
                        localStorage.pass = $('#password').val();
                        localStorage.chkbx = $('#checkremember').val();
                    } else {
                        localStorage.usrname = '';
                        localStorage.pass = '';
                        localStorage.chkbx = '';
                    }
                });
            });

        </script>

<img src="img/bg_header.jpg" id="bg" alt="">
<div class="container" style="min-height: 800px">
    <section id="logo">
        <img src="img/logo.png" alt="">

        <h2>The University of <span>Greenwich</span></h2>
    </section>
    <div class="clearfix"></div>
    <div class="row" style="min-height:600px">
        <div class="container text-center">
            <div class="col-sm-3"></div>
            <div class="col-sm-6" style="margin-left: auto;margin-right: auto">
                <div class="panel panel-primary panel-inverse" id="login-panel">
                   <div class="panel-body">
                        <form action="LoginController?action=checklogin" method="post">
                            <h2 style="margin-bottom: 50px">Who are you?</h2>
                            <div class="form-group">
                                <input class="form-control" id="username" name="username" type="text" required placeholder="Username"/>
                            </div>
                            <div class="form-group">
                                <input class="form-control" id="password" name="password" type="password" required placeholder="Password"/>
                            </div>
                            <div class="form-group text-left">
                                <input type="checkbox"  id="checkremember" class="form-control-static"/>
                                Remember me
                            </div>
                            <div class="form-group">
                                <input class="btn btn-success" value="Sign in" type="submit" style="width: 100%"/>
                            </div>
                        </form>
                   </div>
                </div>
            </div>
            <div class="col-sm-3"></div>
        </div>
    </div>
    

</div>
<script src="js/jquery-3.1.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>

<script src="js/material.min.js"></script>
<script src="js/ripple.min.js"></script>
<script>
    $("#menu-toggle").click(function (e) {
        e.preventDefault();
        $("#wrapper").toggleClass("toggled");
    });
</script>
</body>
</html>