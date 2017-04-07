<%-- 
    Document   : account-view-all
    Created on : Apr 5, 2017, 10:45:30 PM
    Author     : minamaurer
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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

    <link rel="stylesheet" href="../assets/js/jquery-ui/css/no-theme/jquery-ui-1.10.3.custom.min.css"
          id="style-resource-1">
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

    <!-- TS1387507138: Neon - Responsive Admin Template created by Laborator -->
</head>
<body class="page-body">
<jsp:useBean id="account" class="entity.Account" scope="session"></jsp:useBean>
<jsp:useBean id="beanAdminCM" class="entity.Claim" scope="session"></jsp:useBean>
<div class="page-container">

    <div class="sidebar-menu">

        <header class="logo-env">

            <!-- logo -->
            <div class="logo">
                <img src="../assets/images/logo.png" alt=""/>
                <h3>Greenwich University</h3>
            </div>

        </header>


        <ul id="main-menu" class="">
            <li>
                <a href="#"><i class="entypo-user"></i><span>Account</span></a>
                <ul>


                    <li><a href="#"><i class="entypo entypo-user-add"></i> Create</a></li>
                    <li><a href="#"><i class="entypo entypo-users"></i> View all</a></li>

                </ul>
            </li>
            <li><a href="#"><i class="entypo-suitcase"></i> Faculty</a>
                <ul>
                    <li><a href="AdminController?action=openShedule">Create</a></li>
                    <li><a href="AdminController?action=adminViewAll">View all</a></li>
                </ul>

            </li>
            <li><a href="#"><i class="entypo-logout"></i> Logout</a></li>
        </ul>


    </div>
    <div class="main-content">
        <h2>Welcome: ${account.fullName}</h2>

        <br/>
        <div class="col-sm-12">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h1 class="panel-title">All Claim</h1>
                </div>

                <div class="panel-body">
                   <table class="table table-responsive table-responsive">
                            <thead>
                            <th>#</th>
                            <th>Title</th>
                            <th>Create Date</th>
                            <th>End Date</th>
                            <th>Faculty</th>
                            <th>Assessment</th>
                            <th>Item</th>
                            <th>Status</th>
                            <th>#</th>
                            </thead>
                            <tbody>
                            <c:forEach var="c" items="${beanAdminCM.listClaim}">
                                <tr>
                                    <td>${c.idClaim}</td>
                                    <td>${c.title}</td>
                                    <td>${c.createDate}</td>
                                    <td>${c.endDate}</td>
                                    <td>${c.facultyName}</td>
                                    <td>${c.assessmentName}</td>
                                    <td>${c.itemAssessmentName}</td>
                                <c:if test="${c.status == 0}">
                                    <td><span><c:out value="Open"/></span></td>
                                </c:if>
                                <c:if test="${c.status == 1}">
                                    <td><c:out value="Update evidence"/></td>
                                </c:if>
                                <c:if test="${c.status == 2}">
                                    <td><c:out value="Closed"/></td>
                                </c:if>
                                <td>#</td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                </div>
            </div>
        </div>
        <!-- lets do some work here... --><!-- Footer -->
        <footer class="main">


        </footer>
    </div>

</div>


<script src="../assets/js/gsap/main-gsap.js" id="script-resource-1"></script>
<script src="../assets/js/jquery-ui/js/jquery-ui-1.10.3.minimal.min.js" id="script-resource-2"></script>
<script src="../assets/js/bootstrap.min.js" id="script-resource-3"></script>
<script src="../assets/js/joinable.js" id="script-resource-4"></script>
<script src="../assets/js/resizeable.js" id="script-resource-5"></script>
<script src="../assets/js/api.js" id="script-resource-6"></script>
<script src="../assets/js/custom.js" id="script-resource-7"></script>
<script src="../assets/js/demo.js" id="script-resource-8"></script>

<script>
    $(document).ready(function () {
        $('.dataTable').DataTable({
            searching: true, responsive: true
        });
    });
</script>
</body>
</html>