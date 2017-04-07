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


    <title>Home</title>

    <link rel="stylesheet" href="../assets/js/jquery-ui/css/no-theme/jquery-ui-1.10.3.custom.min.css">

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
<jsp:useBean id="beanClaim" class="entity.Claim" scope="session"></jsp:useBean>
<jsp:useBean id="beanStatistic" class="entity.Statistic" scope="session"></jsp:useBean>
<jsp:useBean id="account" class="entity.Account" scope="session"></jsp:useBean>
<% if(account.getLever()!=3){%>
    <jsp:forward page="../logout.jsp"></jsp:forward>
<%}%>

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

            <li>
                <a href="Controller?action=viewC"><i class="glyphicon glyphicon-info-sign"></i>  View Process</a>
            </li>
            <li class="active">
                <a href="Controller?action=viewstatistic"><i class="glyphicon glyphicon-dashboard"></i>  View Statistics</a>
            </li>
            <li><a href="../logout.jsp"><i class="glyphicon glyphicon-log-out"></i> Logout</a></li>


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

                <ul class="user-info pull-left pull-right-xs pull-none-xsm">
                    <!-- Message Notifications -->
                    <li class="notifications dropdown">

                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown"
                           data-close-others="true">
                            <i class="fa fa-globe fa-3x" style="font-size: 2.0em"></i>
                            <span class="badge badge-secondary">10</span>
                        </a>

                        <ul class="dropdown-menu">
                            <li>
                                <ul class="dropdown-menu-list scroller">
                                    <li class="active">
                                        <a href="#">

                                            <span class="line">
					<strong>Luc Chartier</strong>
					- yesterday
				</span>

                                            <span class="line desc small">
					This ain’t our first item, it is the best of the rest.
				</span>
                                        </a>
                                    </li>

                                    <li class="active">
                                        <a href="#">

                                            <span class="line">
					<strong>Salma Nyberg</strong>
					- 2 days ago
				</span>

                                            <span class="line desc small">
					Oh he decisively impression attachment friendship so if everything.
				</span>
                                        </a>
                                    </li>

                                    <li>
                                        <a href="#">


                                            <span class="line">
					Hayden Cartwright
					- a week ago
				</span>

                                            <span class="line desc small">
					Whose her enjoy chief new young. Felicity if ye required likewise so doubtful.
				</span>
                                        </a>
                                    </li>

                                    <li>
                                        <a href="#">


                                            <span class="line">
					Sandra Eberhardt
					- 16 days ago
				</span>

                                            <span class="line desc small">
					On so attention necessary at by provision otherwise existence direction.
				</span>
                                        </a>
                                    </li>
                                </ul>
                            </li>

                            <li class="external">
                                <a href="mailbox/main/index.html">All Messages</a>
                            </li>
                        </ul>

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


    <div class="clearfix"></div>
    <div class="row">
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
                                <div class="form-group"><input type="submit" value="Search"/></div>
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
                            <div class="form-group"><input type="submit" value="Search"/></div>
                        </form>
                    </div>
                </div>
                <div class="clearfix"></div>
                 <div class="col-sm-12">
                    <h3>Student up claim without evidence</h3>
                    <table class="dataTable table table-bordered table table-responsive responsive">
                        <thead>
                        <th>Claim ID</th>
                        <th>Student Name</th>
                        <th>Claim Title</th>
                        <th>Send Date</th>
                        </thead>
                        <tbody>
                        <c:forEach var="c" items="${beanClaim.listClaimWithoutEvidence}">
                        <tr>
                            <td>${c.idClaim}</td>
                            <td>${c.userFullName}</td>
                            <td>${c.title}</td>
                            <td>${c.sendDate}</td>
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
                        <th>Title</th>
                        <th>Content</th>
                        <th>Evidence File</th>
                        <th>Send Date</th>
                        <th>Status</th>
                        <th>User</th>
                        <th>Full Name</th>
                        </thead>
                       
                        <tbody>
                       
                        <c:forEach var="cl" items="${beanClaim.listClaimUnresolved}">
                        <tr>
                            <td>${cl.idClaim}</td>
                            <td>${cl.title}</td>
                            <td>${cl.content}</td>
                            <td>${cl.filedata}</td>
                            <td>${cl.sendDate}</td>
                            <c:if test="${cl.status == 0}">
                                <td><c:out value="Waiting"/></td>
                            </c:if>
                            <td>${cl.idUser}</td>
                            <td>${cl.userFullName}</td>
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


<script src="../assets/js/gsap/main-gsap.js" id="script-resource-1"></script>
<script src="../assets/js/jquery-ui/js/jquery-ui-1.10.3.minimal.min.js" id="script-resource-2"></script>
<script src="../assets/js/bootstrap.min.js" id="script-resource-3"></script>
<script src="../assets/js/joinable.js" id="script-resource-4"></script>
<script src="../assets/js/resizeable.js" id="script-resource-5"></script>
<script src="../assets/js/jquery.dataTables.min.js" id="script-resource-7"></script>
<script src="../assets/js/dataTables.bootstrap.js" id="script-resource-11"></script>
<script src="../assets/js/select2/select2.min.js" id="script-resource-12"></script>
<script src="../js/Chart.min.js" id="script-resource-13"></script>
<script src="../assets/js/api.js" id="script-resource-6"></script>
<script src="../assets/js/custom.js" id="script-resource-8"></script>
<script src="../assets/js/demo.js" id="script-resource-9"></script>

<script>
    $("#menu-toggle").click(function (e) {
        e.preventDefault();
        $("#wrapper").toggleClass("toggled");
    });
    $(document).ready(function () {
        var ctx = document.getElementById("myChart1");
                    var data = [12, 19, 3, 5, 2, 3, 6, 7, 8];
                    var myChart = new Chart(ctx, {
                    type: 'bar',
                            data: {
                            labels: ["1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"],
                                    datasets: [{
                                    label: '# of claims',
                                            data: [<c:forEach var="m" items="${beanStatistic.listNumOfClaim}">
                ${m.data},
            </c:forEach>],
                                            backgroundColor: "#758EA7",
                                            borderWidth: 0
                                    }]
                            },
                            options: {
                            scales: {
                            yAxes: [{
                            ticks: {
                            beginAtZero: true
                            }
                            }]
                            }
                            }
                    });
                    var ctx2 = document.getElementById("myChart2");
                    var data2 = {
                    labels: [
            <c:forEach var="m" items="${beanStatistic.listStatisticAllMajor}">
                    "${m.title}",
            </c:forEach>
                    ],
                            datasets: [
                            {
                            data: [<c:forEach var="m" items="${beanStatistic.listStatisticAllMajor}">
                ${m.data},
            </c:forEach>],
                                    backgroundColor: [
                                            "#9c3a4e",
                                            "#1a4466"
                                    ],
                                    hoverBackgroundColor: [
                                            "#FF6384",
                                            "#36A2EB"
                                    ]
                            }]
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
                                            data: [<c:forEach var="s" items="${beanStatistic.listNumOfStudent}">
                ${s.data},
            </c:forEach>],
                                            backgroundColor: "#7A8E59",
                                            borderColor: "#44572f",
                                            borderWidth: 3
                                    }]
                            },
                            options: {
                            scales: {
                            yAxes: [{
                            ticks: {
                            beginAtZero: true
                            }
                            }]
                            }
                            }
                    });


$('.dataTable').DataTable({
            searching: false, responsive: true, dom: 'Bfrtip',
            buttons: [
                'copy', 'csv', 'excel', 'pdf', 'print'
            ]
        });

    });

</script>
</body>
</html>
