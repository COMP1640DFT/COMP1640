<%-- 
    Document   : schedule
    Created on : Apr 5, 2017, 10:44:37 PM
    Author     : minamaurer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <!--[if IE]>
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"><![endif]-->

        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
        <meta name="description" content="Neon Admin Panel"/>
        <meta name="author" content="Laborator.co"/>

        <title>Neon | Blank Page</title>

        <link rel="stylesheet" href="assets/js/jquery-ui/css/no-theme/jquery-ui-1.10.3.custom.min.css"
              id="style-resource-1">
        <link rel="stylesheet" href="assets/css/font-icons/entypo/css/entypo.css" id="style-resource-2">
        <link rel="stylesheet" href="assets/css/font-icons/entypo/css/animation.css" id="style-resource-3">
        <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Noto+Sans:400,700,400italic"
              id="style-resource-4">
        <link rel="stylesheet" href="assets/css/main.css" id="style-resource-5">
        <link rel="stylesheet" href="assets/css/custom.css" id="style-resource-6">

        <script src="assets/js/jquery-1.10.2.min.js"></script>

        <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
        <![endif]-->

        <!-- TS1387507138: Neon - Responsive Admin Template created by Laborator -->
    </head>
    <body class="page-body">
        <jsp:useBean id="account" class="entity.Account" scope="session"></jsp:useBean>
        <jsp:useBean id="beanASD" class="entity.AsssessmentDetail" scope="session"></jsp:useBean>
        <c:if test="${account.lever != 2}">
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
                    <li>
                        <a href="#"><i class="entypo-user"></i><span>Account</span></a>
                        <ul>


                            <li><a href="AdminController?action=openCreateUser"><i class="entypo entypo-user-add"></i> Create</a></li>
                            <li><a href="AdminController?action=viewAllUser"><i class="entypo entypo-users"></i> View all</a></li>
                        </ul>
                    </li>
                    <li><a href="#"><i class="entypo-suitcase"></i> Claim</a>
                        <ul>
                            <li><a href="AdminController?action=openShedule">Create</a></li>
                            <li><a href="AdminController?action=adminViewAll">View all</a></li>
                        </ul>

                    </li>
                    <li><a href="logout.jsp"><i class="entypo-logout"></i> Logout</a></li>
                </ul>


            </div>
            <div class="main-content">
                <h2>Welcome: ${account.fullName}</h2>

                <br/>

                <div class="col-sm-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h1 class="panel-title">Create a schedule</h1>
                        </div>

                        <div class="panel-body">
                            <form action="AdminController?action=addnewCM" name="formcreate" method="post" onsubmit="return validate()" class="form-horizontal form-groups-bordered">
                                <!--                        <div class="form-group">
                                                            <label class="col-sm-3 control-label">Faculty</label>
                                                            <div class="col-sm-3">
                                                                <select class="form-control">
                                                                    <option>---</option>
                                                                    <option selected value="1">Information Technology</option>
                                                                    <option value="2">Business</option>
                                                                    <option value="3">Commerce</option>
                                                                </select>
                                                            </div>
                                
                                                        </div>-->
<!--                                <div class="form-group">
                                    <label class="col-sm-3 control-label">Title</label>
                                    <div class="col-sm-3">
                                        <input name="title" class="form-control" type="text"/>
                                        <div class="txtError" id="titleer"></div>
                                    </div>

                                </div>-->
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">Item Assessment</label>
                                    <div class="col-sm-3">
                                        <select class="form-control" name="iditem">
                                            <c:forEach var="i" items="${beanASD.list}" >
                                                <option value="${i.value}">${i.data}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-sm-3 control-label">From</label>
                                    <div class="col-sm-3">
                                        <input name="from" class="form-control" type="date"/>
                                        <div class="txtError" id="fromdateer"></div>
                                    </div>

                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">To</label>
                                    <div class="col-sm-3">
                                        <input name="to" class="form-control" type="date"/>
                                        <div class="txtError" id="todateer"></div>
                                    </div>

                                </div>
                                <!--                        <div class="form-group">
                                                            <label class="col-sm-3 control-label">Status</label>
                                                            <div class="col-sm-3">
                                                                <select class="form-control" name="status">
                                                                    <option value="active">Active</option>
                                                                    <option value="deactive">Deactive</option>
                                                                </select>
                                                            </div>
                                                        </div>-->
                                <div class="form-group">
                                    <div class="col-sm-7 col-sm-offset-5">
                                        <button class="btn btn-success" type="submit"><i class="entypo entypo-floppy"></i> Create</button>
                                        <button class="btn btn-default" type="reset"><i class="entypo entypo-cancel-circled"></i> Reset</button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
                <!-- lets do some work here... --><!-- Footer -->
                <footer class="main">


                </footer>
            </div>

        </div>


        <script src="assets/js/gsap/main-gsap.js" id="script-resource-1"></script>
        <script src="assets/js/jquery-ui/js/jquery-ui-1.10.3.minimal.min.js" id="script-resource-2"></script>
        <script src="assets/js/bootstrap.min.js" id="script-resource-3"></script>
        <script src="assets/js/joinable.js" id="script-resource-4"></script>
        <script src="assets/js/resizeable.js" id="script-resource-5"></script>
        <script src="assets/js/api.js" id="script-resource-6"></script>
        <script src="assets/js/custom.js" id="script-resource-7"></script>
        <script src="assets/js/demo.js" id="script-resource-8"></script>

        <script type="text/javascript">
                                function validate() {
                                    var rs = 0;
                                    if (document.formcreate.title.value == "") {
                                        $('#titleer').text("Please input title!");
                                        rs = rs + 1;
                                    }
                                    if (document.formcreate.from.value == "") {
                                        $('#fromdateer').text("Please input title!");
                                        rs = rs + 1;
                                    }
                                    if (document.formcreate.to.value == "") {
                                        $('#todateer').text("Please input title!");
                                        rs = rs + 1;
                                    }
                                    if (rs > 0) {
                                        return false;
                                    }
                                    else {
                                        document.formcreate.submit();
                                    }
                                }
        </script>
    </body>
</html>