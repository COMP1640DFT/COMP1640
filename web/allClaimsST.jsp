<%-- 
    Document   : newClaims
    Created on : Mar 20, 2017, 6:54:22 PM
    Author     : DaoMinhThien
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

        <link rel="stylesheet" href="assets/js/jquery-ui/css/no-theme/jquery-ui-1.10.3.custom.min.css"
              id="style-resource-1">
        <link rel="stylesheet" href="assets/css/font-icons/font-awesome/css/font-awesome.min.css" id="style-resource-1">
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

        <!-- TS1387507309: Neon - Responsive Admin Template created by Laborator -->
    </head>
    <body class="page-body">
        <jsp:useBean id="beanAllStudentClaim" class="entity.Claim" scope="session"></jsp:useBean>
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
                            <a href=""><i class="glyphicon glyphicon-home"></i> Home</a>
                        </li>
                        <li><a href="#"><i class="glyphicon glyphicon-log-out"></i> Logout</a></li>


                    </ul>


                </div>
                <div class="main-content">

                    <div class="row">

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
    <!--                        <a href="StudentsController?action=AddClaimPage&idC=${c.idCourse}&idCM=${c.idClaim}" class="btn btn-default" style="float: right;margin-top: 20px"><i
                                    class="glyphicon glyphicon-plus-sign"></i> New</a>-->
                    </form>

                    <table class="dataTable table table-bordered table table-responsive">
                        <thead>
                            <tr>
                                <th>#</th>
                                <th>Subject</th>
                                <th>EC Coordinator</th>
                                <th>File</th>
                                <th>Status</th>
                            </tr>
                        </thead>
                        <tfoot>
                            <tr>
                                <th>#</th>
                                <th>Subject</th>
                                <th>EC Coordinator</th>
                                <th>File</th>
                                <th>Status</th>
                            </tr>
                        </tfoot>

                        <tbody>
                            <c:forEach var="c" items="${beanAllStudentClaim.listClaim}">
                                <tr>
                                    <td>${c.idClaim}</td>
                                    <td><a href="StudentsController?id=${c.idClaim}&action=viewDecision">${c.title}</a></td>
                                    <td>${c.idUser}</td>
                                    <c:if test="${c.filedata == ''}">
                                        <td><span class="text-danger"><c:out value="No"/></span></td>
                                        </c:if>
                                        <c:if test="${c.filedata != ''}">
                                        <td><span class="text-danger"><c:out value="Yes"/></span></td>
                                        </c:if>
                                        <c:if test="${c.status == 0}">
                                        <td><span class="text-danger"><c:out value="Waiting"/></span></td>
                                        </c:if>
                                        <c:if test="${c.status == 1}">
                                        <td><c:out value="Done"/></td>
                                    </c:if>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div><!-- Footer -->

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