<%-- 
    Document   : index
    Created on : Mar 13, 2017, 10:33:32 PM
    Author     : minamaurer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <!--[if IE]>
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"><![endif]-->

        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>


        <title>Account</title>

        <link rel="stylesheet" href="assets/js/jquery-ui/css/no-theme/jquery-ui-1.10.3.custom.min.css">

        <link rel="stylesheet" href="assets/css/font-icons/font-awesome/css/font-awesome.min.css" id="style-resource-1">
        <link rel="stylesheet" href="assets/css/font-icons/entypo/css/entypo.css" id="style-resource-2">
        <link rel="stylesheet" href="assets/css/font-icons/entypo/css/animation.css" id="style-resource-3">
        <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Noto+Sans:400,700,400italic"
              id="style-resource-4">
        <link rel="stylesheet" href="assets/css/main.css" id="style-resource-5">
        <link rel="stylesheet" href="assets/css/custom.css" id="style-resource-6">

        <script src="assets/js/jquery-1.10.2.min.js"></script>

        <link rel="stylesheet" type="text/css" href="../css/responsive.jqueryui.min.css">
        <link rel="stylesheet" type="text/css" href="../css/responsive.dataTables.min.css">
        <link rel="stylesheet" href="assets/js/jquery-ui/css/no-theme/jquery-ui-1.10.3.custom.min.css"
              id="style-resource-1">
        <link rel="stylesheet" href="assets/css/font-icons/entypo/css/entypo.css" id="style-resource-2">
        <link rel="stylesheet" href="assets/css/font-icons/entypo/css/animation.css" id="style-resource-3">
        <link rel="stylesheet" href="assets/css/main.css" id="style-resource-5">
        <link rel="stylesheet" href="assets/css/custom.css" id="style-resource-6">

        <script src="assets/js/jquery-1.10.2.min.js"></script>
        <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
        <![endif]-->

        <!-- TS1387507309: Neon - Responsive Admin Template created by Laborator -->
    </head>
    <body class="page-body">
        <jsp:useBean id="account" class="entity.Account" scope="session"></jsp:useBean>
        <c:if test="${account.lever != 4}">
            <jsp:forward page="logout.jsp"></jsp:forward>
        </c:if>
        <div class="page-container">

            <div class="sidebar-menu">

                <header class="logo-env">

                    <!-- logo -->
                    <div class="logo text-center">
                        <a href="dashboard/main/index.html">
                            <img src="assets/images/logo.png" width="100" alt="" style="margin-right: auto"/>

                        </a>
                        <h3>Greenwich University</h3>
                    </div>


                    <!-- open/close menu icon (do not remove if you want to enable menu on mobile devices) -->
                    <div class="sidebar-mobile-menu visible-xs">
                        <a href="#" class="with-animation"><!-- add class "with-animation" to support animation -->
                            <i class="entypo-menu"></i>
                        </a>
                    </div>

                </header>


                <ul id="main-menu" class="">

                    <li >
                            <a href="CoordinatorController?action=viewAllClaim"><i class="glyphicon glyphicon-home"></i> Home</a>
                        </li>
                        <li class="active"><a href="eccoorChangePwd.jsp"><i class="glyphicon glyphicon-lock"></i>Change password</a></li>
                        <li><a href="logout.jsp"><i class="glyphicon glyphicon-log-out"></i> Logout</a></li>


                </ul>


            </div>
            <div class="main-content">

                <div class="row">

                    <!-- Profile Info and Notifications -->
                    <div class="col-md-6 col-sm-8 clearfix">

                        <ul class="user-info pull-left pull-none-xsm">

                            <!-- Profile Info -->
                            <li class="profile-info dropdown">
                                <!-- add class "pull-right" if you want to place this from right -->
                                Welcome: <strong> ${account.fullName} (${account.idUser})</strong>
                            </li>
                        </ul>

                    </div>

                </div>

                <hr/>
                <div class="row">
                    <div class="col-sm-12">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h1 class="panel-title">Create new account</h1>
                            </div>

                            <div class="panel-body">
                                <form name="changePass" action="CoordinatorController?action=changePass" onsubmit="return validate()" method="post" class="form-horizontal form-groups-bordered">
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label">Old Password</label>
                                        <div class="col-sm-5"><input type="password" class="form-control" name="oldpass" id="oldpass"/></div>
                                        <input type="hidden" class="form-control" name="idUser" value="${account.idUser}" id="id"/>
                                        <div class="classerror" id="erroroldpass"></div>  
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label">New Password</label>
                                        <div class="col-sm-5"><input type="password" class="form-control" name="password" id="password"/></div>
                                        <div class="classerror" id="errorpass"></div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label">New Confirm Password</label>
                                        <div class="col-sm-5"><input type="password" class="form-control" name="passwordcf" id="passwordcf"/></div>
                                        <div class="classerror" id="errorpasscf"></div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-sm-7 col-sm-offset-5">
                                            <button class="btn btn-success"  type="submit"><i class="entypo entypo-user-add"></i> Create</button>
                                            <button class="btn btn-default" type="reset"><i class="entypo entypo-cancel-circled"></i> Reset</button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>


            </div>

        </div>


        <script src="assets/js/gsap/main-gsap.js" id="script-resource-1"></script>
        <script src="assets/js/jquery-ui/js/jquery-ui-1.10.3.minimal.min.js" id="script-resource-2"></script>
        <script src="assets/js/bootstrap.min.js" id="script-resource-3"></script>
        <script src="https://cdn.datatables.net/1.10.13/js/jquery.dataTables.min.js" ></script>
        <script src="https://cdn.datatables.net/1.10.13/js/dataTables.bootstrap.min.js" ></script>
        <script src="https://cdn.datatables.net/responsive/2.1.1/js/dataTables.responsive.min.js"></script>
        <script src="https://cdn.datatables.net/responsive/2.1.1/js/responsive.bootstrap.min.js"></script>
        <script src="assets/js/joinable.js" id="script-resource-4"></script>
        <script src="assets/js/resizeable.js" id="script-resource-5"></script>
        <script src="assets/js/select2/select2.min.js" id="script-resource-12"></script>
        <script src="assets/js/Chart.min.js" id="script-resource-13"></script>
        <script src="assets/js/api.js" id="script-resource-6"></script>
        <script src="assets/js/custom.js" id="script-resource-8"></script>
        <script src="assets/js/demo.js" id="script-resource-9"></script>

        <script>
                                    document.changePass.oldpass.addEventListener('focus', function () {
                                        $('#erroroldpass').text("");
                                    });

                                    document.changePass.password.addEventListener('focus', function () {
                                        $('#errorpass').text("");
                                    });

                                    document.changePass.passwordcf.addEventListener('focus', function () {
                                        $('#errorpasscf').text("");
                                    });


                                    function validate() {
                                        var result = 0;
                                        if (document.changePass.oldpass.value == "") {
                                            $('#erroroldpass').text("Please input your password!");
                                            result = result + 1;

                                        }
                                        
                                        if (document.changePass.oldpass.value != "") {
                                            if (document.changePass.oldpass.value.length < 6) {
                                                $('#erroroldpass').text("Please enter at least 6 characters!");
                                                result = result + 1;
                                            }
                                        }
                                        
                                        if (document.changePass.password.value != "") {
                                            if (document.changePass.password.value.length < 6) {
                                                $('#errorpass').text("Please enter at least 6 characters!");
                                                result = result + 1;
                                            }
                                        }
                                        if (document.changePass.password.value == "") {
                                            $('#errorpass').text("Please enter password!");
                                            result = result + 1;
                                        }
                                        if (document.changePass.password.value != document.changePass.passwordcf.value) {
                                            $('#errorpasscf').text("Confirm password incorrect!");
                                            result = result + 1;
                                        }
                                        if (result > 0) {
                                            return false;
                                        }
                                        else {
                                            return  document.changePass.submit();
                                        }
                                    }

                                    $(document).ready(function () {

                                        $('.dataTable').DataTable({
                                            searching: false,
                                            bLengthChange: false,
                                            responsive: {
                                                details: {
                                                    display: $.fn.dataTable.Responsive.display.modal({
                                                        header: function (row) {
                                                            var data = row.data();
                                                            return 'Details for ' + data[0] + ' ' + data[1];
                                                        }
                                                    }),
                                                    renderer: $.fn.dataTable.Responsive.renderer.tableAll({
                                                        tableClass: 'table'
                                                    })
                                                }
                                            }
                                        });


                                    });
        </script>
    </body>
</html>