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


        <title>Home</title>

        <link rel="stylesheet" href="../assets/js/jquery-ui/css/no-theme/jquery-ui-1.10.3.custom.min.css"
              id="style-resource-1">
        <link rel="stylesheet" href="../assets/css/font-icons/font-awesome/css/font-awesome.min.css" id="style-resource-1">
        <link rel="stylesheet" href="../assets/css/font-icons/entypo/css/entypo.css" id="style-resource-2">
        <link rel="stylesheet" href="../assets/css/font-icons/entypo/css/animation.css" id="style-resource-3">
        <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Noto+Sans:400,700,400italic"
              id="style-resource-4">
        <link rel="stylesheet" href="../assets/css/main.css" id="style-resource-5">
        <link rel="stylesheet" href="../assets/css/custom.css" id="style-resource-6">

        <script src="../assets/js/jquery-1.10.2.min.js"></script>

        <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
        <![endif]-->

        <!-- TS1387507309: Neon - Responsive Admin Template created by Laborator -->
    </head>
    <body class="page-body">
        <jsp:useBean id="beanAllClaim" class="entity.Claim" scope="session"></jsp:useBean>
            <div class="page-container">

                <div class="sidebar-menu">

                    <header class="logo-env">

                        <!-- logo -->
                        <div class="logo text-center">
                            <a href="dashboard/main/index.html">
                                <img src="../assets/images/logo.png" width="100" alt="" style="margin-right: auto"/>

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

                        <li class="active">
                            <a href="StudentsController?action=viewAllCM"><i class="glyphicon glyphicon-home"></i> All Claims</a>
                        </li>
                        <li><a href="#"><i class="glyphicon glyphicon-log-out"></i> Logout</a></li>


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


                                    Welcome: <strong>${idUser} (${fullName})</strong>


                            </li>

                        </ul>

                    </div>


                    <!-- Raw Links -->
                    <div class="col-md-6 col-sm-4 clearfix hidden-xs">

                        <ul class="list-inline links-list pull-right">

                            <li>
                                <a href="#">
                                    Log Out <i class="entypo-logout right"></i>
                                </a>
                            </li>
                        </ul>

                    </div>

                </div>

                <hr/>


                <h2 class="text-center">All claims</h2>
                <br/>

                <div class="panel panel-primary">
                    <form class="form-inline" style="padding: 10px">
                        <div class="form-group">
                            <label for="status">Filter by status: </label>
                            <select id="status" class="form-control" name="status">
                                <option value="open">Open</option>
                                <option value="closed">Closed</option>
                                <option value="expired">Expired</option>
                            </select>
                        </div>
                    </form>

                    <table class="table  table-responsive">
                        <thead>
                            <tr>
                                <th>#</th>
                                <th>Subject</th>
                                <th>Close Date</th>
                                <th>Status</th>
                                <th>Action</th>
                            </tr>
                        </thead>

                        <tbody>
                             <c:forEach var="c" items="${beanAllClaim.listClaimUnresolved}">
                                <tr>
                                    <td>${c.idClaim}</td>
                                    <td>${c.title}</td>
                                    <td>${c.endDate}</td>
                                    <c:if test="${c.status == 0}">
                                        <td><span class="text-danger"><c:out value="Closed"/></span></td>
                                    </c:if>
                                    <c:if test="${c.status == 1}">
                                        <td><c:out value="Open"/></td>
                                    </c:if>
                                        <td>
                                        <a href="StudentsController?idCM=${c.idClaim}&idUser=${idUser}&action=viewAllClaim">View</a>|<a href="StudentsController?action=AddClaimPage&idC=${c.idSubject}&idCM=${c.idClaim}&idM=${idMajor}">Up Claim</a>
                                    </td>
                                </tr>
                            </c:forEach>
<!--                                
                        </tbody>
                    </table>
                </div><!-- Footer -->

            </div>

        </div>


        <script src="../assets/js/gsap/main-gsap.js" id="script-resource-1"></script>
        <script src="../assets/js/jquery-ui/js/jquery-ui-1.10.3.minimal.min.js" id="script-resource-2"></script>
        <script src="../assets/js/bootstrap.min.js" id="script-resource-3"></script>
        <script src="../assets/js/joinable.js" id="script-resource-4"></script>
        <script src="../assets/js/resizeable.js" id="script-resource-5"></script>
        <script src="../assets/js/api.js" id="script-resource-6"></script>
        <script src="../assets/js/custom.js" id="script-resource-8"></script>
        <script src="../assets/js/demo.js" id="script-resource-9"></script>

    </body>
</html>