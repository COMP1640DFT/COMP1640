<%-- 
    Document   : account-view-all
    Created on : Apr 5, 2017, 10:45:30 PM
    Author     : minamaurer
--%>

<%@page import="entity.Claim"%>
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

        <title>Process</title>


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
    <jsp:useBean id="ClaimDetail" class="entity.Claim" scope="session"></jsp:useBean>
    <jsp:useBean id="DecisionDetail" class="entity.Decision" scope="session"></jsp:useBean>
    <jsp:useBean id="account" class="entity.Account" scope="session"></jsp:useBean>
    <% if (account.getLever() != 3) {%>
    <jsp:forward page="logout.jsp"></jsp:forward>
    <%}%>
    <body class="page-body">

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
                    <li class="active">
                        <a href="Controller?action=viewC"><i class="glyphicon glyphicon-info-sign"></i>  View Process</a>
                    </li>
                    <li>
                        <a href="Controller?action=viewstatistic"><i class="glyphicon glyphicon-dashboard"></i>  View Statistics</a>
                    </li>
                    <li>
                        <a href="managerChangePwd.jsp"><i class="glyphicon glyphicon-lock"></i>  Change password</a>
                    </li>
                    <li><a href="logout.jsp"><i class="glyphicon glyphicon-log-out"></i> Logout</a></li>


                </ul>


            </div>
            <div class="main-content">
                <h2>Welcome: ${account.fullName} (${account.idUser})</h2>

                <br/>
                <div class="col-sm-12">
                    <div class="panel panel-default">
                        <% Claim c = (Claim) session.getAttribute("ClaimDetail");%>

                        <h1>${ClaimDetail.title}</h1>
                        <hr/>
                        <h3>Feedback from: <strong>${ClaimDetail.idUser} (${ClaimDetail.userFullName})</strong></h3>
                        <p>
                            ${ClaimDetail.content}
                        </p>
                        <c:if test="${ClaimDetail.filedata != ''}">
                            <a href="${ClaimDetail.filedata}" target="_blank"><img src="http://placehold.it/350x150?text=File" class="img-thumbnail"></a>
                            </c:if>
                        <hr/>
                        <h3>Reply from: <strong>${DecisionDetail.idUser}</strong></h3>
                        <p>${DecisionDetail.content}</p>
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
        <script src="js/ripple.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script>


            $(document).ready(function () {

                $('.dataTable').DataTable({
                    searching: true,
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