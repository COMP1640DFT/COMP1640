<%-- 
    Document   : statistic
    Created on : Apr 5, 2017, 10:59:43 PM
    Author     : minamaurer
--%>

<%@page import="entity.Account"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <!--[if IE]>
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"><![endif]-->

        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>


        <title>Statistics</title>

        <link rel="stylesheet" href="assets/js/jquery-ui/css/no-theme/jquery-ui-1.10.3.custom.min.css">
        <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/buttons/1.2.4/css/buttons.dataTables.min.css"/>
        <link rel="stylesheet" href="assets/css/font-icons/font-awesome/css/font-awesome.min.css" id="style-resource-1">
        <link rel="stylesheet" href="assets/css/font-icons/entypo/css/entypo.css" id="style-resource-2">
        <link rel="stylesheet" href="assets/css/font-icons/entypo/css/animation.css" id="style-resource-3">
        <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Noto+Sans:400,700,400italic"
              id="style-resource-4">
        <link rel="stylesheet" href="assets/css/main.css" id="style-resource-5">
        <link rel="stylesheet" href="assets/css/custom.css" id="style-resource-6">


        <link rel="stylesheet" href="assets/css/responsive.bootstrap.min.css">
        <link rel="stylesheet" href="assets/css/responsive.dataTables.min.css">

        <script src="assets/js/jquery-1.10.2.min.js"></script>

        <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
        <![endif]-->

        <!-- TS1387507309: Neon - Responsive Admin Template created by Laborator -->
    </head>

    <body class="page-body">
        <jsp:useBean id="beanClaim" class="entity.Claim" scope="session"></jsp:useBean>
        <jsp:useBean id="beanStatistic" class="entity.Statistic" scope="session"></jsp:useBean>
        <jsp:useBean id="account" class="entity.Account" scope="session"></jsp:useBean>
        <% if (account.getLever() != 3) {%>
        <jsp:forward page="logout.jsp"></jsp:forward>
        <%}%>

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
                        <a href="Controller?action=viewC"><i class="glyphicon glyphicon-info-sign"></i>  View Process</a>
                    </li>
                    <li class="active">
                        <a href="Controller?action=viewstatistic"><i class="glyphicon glyphicon-dashboard"></i>  View Statistics</a>
                    </li>
                    <li>
                        <a href="managerChangePwd.jsp"><i class="glyphicon glyphicon-lock"></i>  Change password</a>
                    </li>
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


                    <!-- Raw Links -->
                    <div class="col-md-6 col-sm-4 clearfix hidden-xs">

                        <ul class="list-inline links-list pull-right">

                            <li>
                                <a href="logout.jsp">
                                    Log Out <i class="entypo-logout right"></i>
                                </a>
                            </li>
                        </ul>

                    </div>

                </div>


                <div class="clearfix"></div>
                <div class="row" id="mydiv">
                    <div class="col-sm-12 panel panel-dark">
                        <div class="page-header">
                            <h2 class="panel-heading text-center">Statistics</h2>


                        </div>
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-sm-12">
                                    <form class="form-inline" action="Controller?action=viewstatistic" method="POST">
                                        <div class="form-group">
                                            <label><strong>Filter: &nbsp;&nbsp;&nbsp;&nbsp;</strong></label>
                                        </div>
                                        <div class="form-group">
                                            <label for="option1">Year</label>
                                            <select id="option1" name="year">
                                                <c:forEach var="y" items="${beanStatistic.listItemYear}">
                                                    <c:choose>
                                                        <c:when test="${y.selected==true}">
                                                            <option selected value="${y.value}">${y.data}</option>
                                                        </c:when>    
                                                        <c:otherwise>
                                                            <option value="${y.value}">${y.data}</option>
                                                        </c:otherwise>
                                                    </c:choose>

                                                </c:forEach>
                                            </select>
                                        </div>
                                        <div class="form-group">
                                            <label for="option2">Major</label>
                                            <select id="option2" name="idmajor">
                                                <c:forEach var="m" items="${beanStatistic.listItemMajor}">
                                                    <c:choose>
                                                        <c:when test="${m.selected==true}">
                                                            <option selected value="${m.value}">${m.data}</option>
                                                        </c:when>    
                                                        <c:otherwise>
                                                            <option value="${m.value}">${m.data}</option>
                                                        </c:otherwise>
                                                    </c:choose>

                                                </c:forEach>
                                            </select>
                                        </div>
                                        <div class="form-group"><input type="submit" class="btn btn-success form-control" value="Search"/></div>
                                    </form>
                                </div>
                            </div>
                            <div class="clearfix"></div>
                            <div class="row">
                                <div class="row">
                                    <div class="clearfix"></div>
                                    <div class="col-sm-12">
                                        <h3 class="text-center">Student up claim in ${beanStatistic.year} of ${beanStatistic.major}</h3>
                                        <table class="dataTable table table-bordered table table-responsive responsive">
                                            <thead>
                                            <th>Claim title</th>
                                            <th>Date</th>
                                            <th>Subject</th>
                                            <th>User</th>
                                            </thead>
                                            <tbody>
                                                <c:forEach var="c" items="${beanStatistic.listItemTableClaim}">
                                                    <tr>
                                                        <td>${c.titleClaim}</td>
                                                        <td>${c.year}</td>
                                                        <td>${c.nameSubject}</td>
                                                        <td>${c.user}</td>
                                                    </tr>
                                                </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>
                                    <div class="col-sm-12">
                                        <h3 class="text-center">Number of claims in ${beanStatistic.year} of ${beanStatistic.major}</h3>
                                        <canvas id="myChart1" style="width: 100%;height:400px"></canvas>
                                    </div>
                                </div>
                                <br/><br/>
                                <div class="row">
                                    <div class="col-sm-12">
                                        <h3 class="text-center">Number of student up claims in ${beanStatistic.year} of ${beanStatistic.major}</h3>
                                        <canvas id="myChart3" style="width: 100%;height:400px"></canvas>
                                    </div>
                                </div>

                                <br/><br/><br/>
                                <div class="row">
                                    <div class="col-sm-12">
                                        <div class="col-sm-12">
                                            <h3 class="text-center">All claims in ${beanStatistic.year}</h3>
                                            <table class="dataTable table table-bordered table table-responsive responsive">
                                                <thead>
                                                <th>Major</th>
                                                <th>Total claims</th>
                                                </thead>
                                                <tbody>
                                                    <c:forEach var="c" items="${beanStatistic.listStatisticAllMajor}">
                                                        <tr>
                                                            <td>${c.title}</td>
                                                            <td>${c.data}</td>
                                                        </tr>
                                                    </c:forEach>
                                                </tbody>
                                            </table>
                                        </div>

                                        <canvas id="myChart2" style="width: 100%;height:400px"></canvas>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-12 panel panel-dark">
                        <div class="page-header">
                            <h2 class="panel-heading text-center">Reports</h2>
                        </div>
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-sm-12">
                                    <form class="form-inline" action="Controller?action=viewstatisticwithfilter" method="post">
                                        <div class="form-group">
                                            <label><strong>Filter: &nbsp;&nbsp;&nbsp;&nbsp;</strong></label>
                                        </div>
                                        <div class="form-group">
                                            <label for="option1">Year</label>
                                            <select id="option1" name="year">
                                                <c:forEach var="y" items="${beanClaim.listSelectedYear}">
                                                    <c:choose>
                                                        <c:when test="${y.selected==true}">
                                                            <option selected value="${y.value}">${y.data}</option>
                                                        </c:when>    
                                                        <c:otherwise>
                                                            <option value="${y.value}">${y.data}</option>
                                                        </c:otherwise>
                                                    </c:choose>

                                                </c:forEach>
                                            </select>
                                        </div>
                                        <div class="form-group">
                                            <label for="option2">Major</label>
                                            <select id="option2" name="idmajor">
                                                <c:forEach var="m" items="${beanClaim.listSelectedMajor}">
                                                    <c:choose>
                                                        <c:when test="${m.selected==true}">
                                                            <option selected value="${m.value}">${m.data}</option>
                                                        </c:when>    
                                                        <c:otherwise>
                                                            <option value="${m.value}">${m.data}</option>
                                                        </c:otherwise>
                                                    </c:choose>

                                                </c:forEach>
                                            </select>
                                        </div>
                                        <div class="form-group"><input class="btn btn-success form-control" type="submit" value="Search"/></div>
                                    </form>
                                </div>
                            </div>
                            <div class="clearfix"></div>
                            <div class="col-sm-12">
                                <h3>Student up claim without evidence</h3>
                                <table class="dataTable table table-bordered table table-responsive responsive">
                                    <thead>
                                    <th>ID</th>
                                    <th>Name</th>
                                    <th>Title</th>
                                    <th>Date</th>
                                    <th>Assessment</th>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="c" items="${beanClaim.listClaimWithoutEvidence}">
                                            <tr>
                                                <td>${c.idClaim}</td>
                                                <td>${c.userFullName}</td>
                                                <td>${c.title}</td>
                                                <td>${c.sendDate}</td>
                                                <td>${c.assessmentName}</td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                            <div class="clearfix"></div>
                            <div class="col-sm-12">
                                <h3>Claim unresolved after two weeks</h3>
                                <table class="dataTable table table-bordered table table-responsive">
                                    <thead>
                                    <th>Claim ID</th>
                                    <th>User</th>
                                    <th>Title</th>
                                    <th>Assessment</th>
                                    <th>Evidence</th>
                                    <th>Send Date</th>
                                    <th>Status</th>
                                    
                                    </thead>
                                    <tfoot>
                                    <th>Claim ID</th>
                                    <th>User</th>
                                    <th>Title</th>
                                    <th>Assessment</th>
                                    <th>Evidence</th>
                                    <th>Send Date</th>
                                    <th>Status</th>
                                    
                                    </tfoot>
                                    <tbody>

                                        <c:forEach var="cl" items="${beanClaim.listClaimUnresolved}">
                                            <tr>
                                                <td>${cl.idClaim}</td>
                                                <td>${cl.idUser}</td>
                                                <td>${cl.title}</td>
                                                <td>${cl.assessmentName}</td>
                                                <c:if test="${cl.filedata == ''}">
                                                    <td><span class="text-danger"><c:out value="No"/></span></td>
                                                    </c:if>
                                                    <c:if test="${cl.filedata != ''}">
                                                    <td><span ><c:out value="Yes"/></span></td>
                                                    </c:if>
                                                <td>${cl.sendDate}</td>
                                                <c:if test="${cl.status == 0}">
                                                    <td><c:out value="Waiting"/></td>
                                                </c:if>
                                                
                                            </tr>
                                        </c:forEach>


                                    </tbody>
                                </table>
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
        <script src="https://cdn.datatables.net/buttons/1.2.4/js/dataTables.buttons.min.js" type="text/javascript"></script>
        <script src="https://cdn.datatables.net/buttons/1.2.4/js/buttons.flash.min.js" type="text/javascript"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jszip/2.5.0/jszip.min.js" type="text/javascript"></script>
        <script src="https://cdn.rawgit.com/bpampuch/pdfmake/0.1.18/build/pdfmake.min.js" type="text/javascript"></script>
        <script src="https://cdn.rawgit.com/bpampuch/pdfmake/0.1.18/build/vfs_fonts.js" type="text/javascript"></script>
        <script src="https://cdn.datatables.net/buttons/1.2.4/js/buttons.html5.min.js" type="text/javascript"></script>
        <script src="https://cdn.datatables.net/buttons/1.2.4/js/buttons.print.min.js" type="text/javascript"></script>
        <script src="assets/js/joinable.js" id="script-resource-4"></script>
        <script src="assets/js/resizeable.js" id="script-resource-5"></script>
        <script src="assets/js/select2/select2.min.js" id="script-resource-12"></script>
        <script src="assets/js/Chart.min.js" id="script-resource-13"></script>
        <script src="assets/js/api.js" id="script-resource-6"></script>
        <script src="assets/js/custom.js" id="script-resource-8"></script>
        <script src="assets/js/demo.js" id="script-resource-9"></script>

        <script>
            $("#menu-toggle").click(function (e) {
                e.preventDefault();
                $("#wrapper").toggleClass("toggled");
            });

            $(document).ready(function () {
                var ctx = document.getElementById("myChart1");
                var data = [12, 19, 3, 5, 2, 3, 6, 7, 8];
                var myChart = new Chart(ctx,
                        {
                            type: 'bar',
                            data: {
                                labels: ["1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"],
                                datasets: [{
                                        label: '# of claims',
                                        data: [<c:forEach var="m" items="${beanStatistic.listNumOfClaim}">${m.data},</c:forEach>],
                                        backgroundColor: "#758EA7",
                                        borderWidth: 0
                                    }]},
                            options: {
                                scales: {
                                    yAxes: [{ticks: {
                                                beginAtZero: true
                                            }}]
                                }
                            }
                        });

                var ctx2 = document.getElementById("myChart2");
                var data2 = {
                    labels: [<c:forEach var="m" items="${beanStatistic.listStatisticAllMajor}">"${m.title}",</c:forEach>],
                            datasets: [{
                                    data: [<c:forEach var="m" items="${beanStatistic.listStatisticAllMajor}">${m.data},</c:forEach>],
                                    backgroundColor: ["#9c3a4e", "#1a4466"],
                                    hoverBackgroundColor: ["#FF6384", "#36A2EB"]}]
                };

                var myPieChart = new Chart(ctx2, {
                    type: 'pie',
                    data: data2,
                    options: {}
                });

                var ctx3 = document.getElementById('myChart3');
                var myChart3 = new Chart(ctx3, {
                    type: 'bar',
                    data: {
                        labels: ["1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"],
                        datasets: [{
                                label: '# of student up claims',
                                data: [<c:forEach var="s" items="${beanStatistic.listNumOfStudent}">${s.data},</c:forEach>],
                                backgroundColor: "#7A8E59",
                                borderColor: "#44572f",
                                borderWidth: 3
                            }]},
                    options: {
                        scales: {
                            yAxes: [{ticks: {beginAtZero: true}}]
                        }
                    }
                });

                $('.dataTable').DataTable({
                    dom: 'Bfrtip',
                    buttons: ['copy', 'csv', 'excel', 'pdf', 'print'],
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
                            renderer: $.fn.dataTable.Responsive.renderer.tableAll({tableClass: 'table'})
                        }
                    }
                });
            });
        </script>
    </body>
</html>
