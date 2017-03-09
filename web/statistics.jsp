<%-- 
    Document   : statistics
    Created on : Mar 7, 2017, 3:11:21 PM
    Author     : minamaurer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <!--<link rel="stylesheet" href="css/bootstrap-theme.min.css">-->
    <link rel="stylesheet" href="css/bootstrap-material-design.min.css">
    <link rel="stylesheet" href="css/custom.css">
    <link rel="stylesheet" href="css/jquery.dataTables.min.css">
    <link href="https://fonts.googleapis.com/css?family=Noto+Sans:400,700&amp;subset=vietnamese" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Lobster" rel="stylesheet">
    <link rel="stylesheet" type="text/css"
          href="https://cdn.datatables.net/buttons/1.2.4/css/buttons.dataTables.min.css"/>
    <link rel="stylesheet" type="text/css" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
    <link rel="stylesheet" type="text/css" href="css/responsive.jqueryui.min.css">
    <link rel="stylesheet" type="text/css" href="css/responsive.dataTables.min.css">
    <title>Statistics</title>
</head>
<body>
    <jsp:useBean id="beanClaim" class="entity.Claim" scope="session"></jsp:useBean>
<img src="img/bg_header.jpg" id="bg" alt="">
<div class="container" style="min-height: 1000px">
    <section id="logo">
        <img src="img/logo.png" alt="">

        <h2>The University of <span>Greenwich</span></h2>
    </section>
    <nav class="navbar navbar-default">
        <div class="container-fluid">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                        data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li class=""><a href="index.html">Home<span class="sr-only">(current)</span></a></li>
                    <li><a href="#">View process</a></li>
                    <li><a href="#">View statistic</a></li>
                    <li><a href="#">About university</a></li>
                    <li><a href="#">Logout</a></li>
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container-fluid -->
    </nav>
    <div class="clearfix"></div>
    <div class="row" style="">
        <div class="col-sm-12 panel panel-default">
            <div class="page-header">
                <h2 class="panel-heading text-center">Statistics</h2>


            </div>
            <div class="panel-body">
                <div class="row">
                    <div class="col-sm-12">
                        <form class="form-inline" action="#" method="get">
                            <div class="form-group">
                                <label><strong>Filter: &nbsp;&nbsp;&nbsp;&nbsp;</strong></label>
                            </div>
                            <div class="form-group">
                                <label for="option1">Option 1 </label>
                                <select id="option1" name="option1">
                                    <option value="1">value 1</option>
                                    <option value="2">value 2</option>
                                </select>
                            </div>
                            <div class="form-group">
                                <label for="option2">Option 2 </label>
                                <select id="option2" name="option2">
                                    <option value="1">value 1</option>
                                    <option value="2">value 2</option>
                                </select>
                            </div>
                        </form>
                    </div>
                </div>
                <div class="clearfix"></div>
                <div class="row">
                    <div class="col-sm-6">
                        <h3 class="text-center">Title 1</h3>
                        <canvas id="myChart1" width="400" height="300"></canvas>
                    </div>
                    <div class="col-sm-6">
                        <h3 class="text-center">Title 2</h3>
                        <div style="width: 350px; height: 350px;margin-left: auto;margin-right: auto">
                            <canvas id="myChart2" width="300" height="300"></canvas>
                        </div>

                    </div>
                    <div class="row">
                        <div class="col-sm-12">
                            <h3 class="text-center">Title 3</h3>
                            <canvas id="myChart3" style="width: 100%;height:400px"></canvas>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-sm-12 panel panel-default">
            <div class="page-header">
                <h2 class="panel-heading text-center">Reports</h2>
            </div>
            <div class="panel-body">
                <div class="row">
                    <div class="col-sm-12">
                        <form class="form-inline" action="#" method="get">
                            <div class="form-group">
                                <label><strong>Filter: &nbsp;&nbsp;&nbsp;&nbsp;</strong></label>
                            </div>
                            <div class="form-group">
                                <label for="option3">Option 1 </label>
                                <select id="option3" name="option1">
                                    <option value="1">value 1</option>
                                    <option value="2">value 2</option>
                                </select>
                            </div>
                            <div class="form-group">
                                <label for="option4">Option 2 </label>
                                <select id="option4" name="option2">
                                    <option value="1">value 1</option>
                                    <option value="2">value 2</option>
                                </select>
                            </div>
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
                        <th>Class</th>
                        <th>Claim Title</th>
                        <th>Send Date</th>
                        </thead>
                        <tbody>
                        <c:forEach var="c" items="${beanClaim.list}">
                        <tr>
                            <td>${c.idClaim}</td>
                            <td>${c.userFullName}</td>
                            <td>${c.className}</td>
                            <td>${c.title}</td>
                            <td>${c.sendDate}</td>
                        </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
                <div class="clearfix"></div>
                <div class="col-sm-12">
                    <h3>Title 2</h3>
                    <table class="dataTable table table-bordered table table-responsive">
                        <thead>
                        <th>Column 1</th>
                        <th>Collumn 2</th>
                        <th>Collumn 3</th>
                        <th>Collumn 4</th>
                        <th>Collumn 5</th>
                        </thead>
                        <tfoot>
                        <th>Column 1</th>
                        <th>Collumn 2</th>
                        <th>Collumn 3</th>
                        <th>Collumn 4</th>
                        <th>Collumn 5</th>
                        </tfoot>
                        <tbody>
                        <tr>
                            <td>data 1</td>
                            <td>data 2</td>
                            <td>data 3</td>
                            <td>data 4</td>
                            <td>data 5</td>
                        </tr>
                        <tr>
                            <td>data 1</td>
                            <td>data 2</td>
                            <td>data 3</td>
                            <td>data 4</td>
                            <td>data 5</td>
                        </tr>
                        <tr>
                            <td>data 1</td>
                            <td>data 2</td>
                            <td>data 3</td>
                            <td>data 4</td>
                            <td>data 5</td>
                        </tr>
                        <tr>
                            <td>data 1</td>
                            <td>data 2</td>
                            <td>data 3</td>
                            <td>data 4</td>
                            <td>data 5</td>
                        </tr>
                        <tr>
                            <td>data 1</td>
                            <td>data 2</td>
                            <td>data 3</td>
                            <td>data 4</td>
                            <td>data 5</td>
                        </tr>
                        <tr>
                            <td>data 1</td>
                            <td>data 2</td>
                            <td>data 3</td>
                            <td>data 4</td>
                            <td>data 5</td>
                        </tr>
                        <tr>
                            <td>data 1</td>
                            <td>data 2</td>
                            <td>data 3</td>
                            <td>data 4</td>
                            <td>data 5</td>
                        </tr>
                        <tr>
                            <td>data 1</td>
                            <td>data 2</td>
                            <td>data 3</td>
                            <td>data 4</td>
                            <td>data 5</td>
                        </tr>
                        <tr>
                            <td>data 1</td>
                            <td>data 2</td>
                            <td>data 3</td>
                            <td>data 4</td>
                            <td>data 5</td>
                        </tr>
                        <tr>
                            <td>data 1</td>
                            <td>data 2</td>
                            <td>data 3</td>
                            <td>data 4</td>
                            <td>data 5</td>
                        </tr>
                        <tr>
                            <td>data 1</td>
                            <td>data 2</td>
                            <td>data 3</td>
                            <td>data 4</td>
                            <td>data 5</td>
                        </tr>
                        <tr>
                            <td>data 1</td>
                            <td>data 2</td>
                            <td>data 3</td>
                            <td>data 4</td>
                            <td>data 5</td>
                        </tr>
                        <tr>
                            <td>data 1</td>
                            <td>data 2</td>
                            <td>data 3</td>
                            <td>data 4</td>
                            <td>data 5</td>
                        </tr>
                        <tr>
                            <td>data 1</td>
                            <td>data 2</td>
                            <td>data 3</td>
                            <td>data 4</td>
                            <td>data 5</td>
                        </tr>
                        <tr>
                            <td>data 1</td>
                            <td>data 2</td>
                            <td>data 3</td>
                            <td>data 4</td>
                            <td>data 5</td>
                        </tr>
                        <tr>
                            <td>data 1</td>
                            <td>data 2</td>
                            <td>data 3</td>
                            <td>data 4</td>
                            <td>data 5</td>
                        </tr>
                        <tr>
                            <td>data 1</td>
                            <td>data 2</td>
                            <td>data 3</td>
                            <td>data 4</td>
                            <td>data 5</td>
                        </tr>
                        <tr>
                            <td>data 1</td>
                            <td>data 2</td>
                            <td>data 3</td>
                            <td>data 4</td>
                            <td>data 5</td>
                        </tr>
                        <tr>
                            <td>data 1</td>
                            <td>data 2</td>
                            <td>data 3</td>
                            <td>data 4</td>
                            <td>data 5</td>
                        </tr>
                        <tr>
                            <td>data 1</td>
                            <td>data 2</td>
                            <td>data 3</td>
                            <td>data 4</td>
                            <td>data 5</td>
                        </tr>
                        <tr>
                            <td>data 1</td>
                            <td>data 2</td>
                            <td>data 3</td>
                            <td>data 4</td>
                            <td>data 5</td>
                        </tr>
                        <tr>
                            <td>data 1</td>
                            <td>data 2</td>
                            <td>data 3</td>
                            <td>data 4</td>
                            <td>data 5</td>
                        </tr>
                        <tr>
                            <td>data 1</td>
                            <td>data 2</td>
                            <td>data 3</td>
                            <td>data 4</td>
                            <td>data 5</td>
                        </tr>
                        <tr>
                            <td>data 1</td>
                            <td>data 2</td>
                            <td>data 3</td>
                            <td>data 4</td>
                            <td>data 5</td>
                        </tr>
                        <tr>
                            <td>data 1</td>
                            <td>data 2</td>
                            <td>data 3</td>
                            <td>data 4</td>
                            <td>data 5</td>
                        </tr>
                        <tr>
                            <td>data 1</td>
                            <td>data 2</td>
                            <td>data 3</td>
                            <td>data 4</td>
                            <td>data 5</td>
                        </tr>
                        <tr>
                            <td>data 1</td>
                            <td>data 2</td>
                            <td>data 3</td>
                            <td>data 4</td>
                            <td>data 5</td>
                        </tr>
                        <tr>
                            <td>data 1</td>
                            <td>data 2</td>
                            <td>data 3</td>
                            <td>data 4</td>
                            <td>data 5</td>
                        </tr>
                        <tr>
                            <td>data 1</td>
                            <td>data 2</td>
                            <td>data 3</td>
                            <td>data 4</td>
                            <td>data 5</td>
                        </tr>
                        <tr>
                            <td>data 1</td>
                            <td>data 2</td>
                            <td>data 3</td>
                            <td>data 4</td>
                            <td>data 5</td>
                        </tr>
                        <tr>
                            <td>data 1</td>
                            <td>data 2</td>
                            <td>data 3</td>
                            <td>data 4</td>
                            <td>data 5</td>
                        </tr>


                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>


</div>
<script src="js/jquery-3.1.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>

<script src="js/material.min.js"></script>
<script src="js/ripple.min.js"></script>
<script src="js/Chart.min.js"></script>
<script src="js/jquery.dataTables.min.js"></script>
<script src="js/responsive.jqueryui.min.js"></script>
<script src="js/dataTables.responsive.min.js"></script>

<script src="https://cdn.datatables.net/buttons/1.2.4/js/dataTables.buttons.min.js" type="text/javascript"></script>
<script src="https://cdn.datatables.net/buttons/1.2.4/js/buttons.flash.min.js" type="text/javascript"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jszip/2.5.0/jszip.min.js" type="text/javascript"></script>
<script src="https://cdn.rawgit.com/bpampuch/pdfmake/0.1.18/build/pdfmake.min.js" type="text/javascript"></script>
<script src="https://cdn.rawgit.com/bpampuch/pdfmake/0.1.18/build/vfs_fonts.js" type="text/javascript"></script>
<script src="https://cdn.datatables.net/buttons/1.2.4/js/buttons.html5.min.js" type="text/javascript"></script>
<script src="https://cdn.datatables.net/buttons/1.2.4/js/buttons.print.min.js" type="text/javascript"></script>
<script src="js/main.js"></script>
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
                labels: ["Red", "Blue", "Yellow", "Green", "Purple", "Orange", "Orange", "Orange", "Orange"],
                datasets: [{
                    label: '# of Votes',
                    data: data,
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
                "Red",
                "Blue",
                "Yellow"
            ],
            datasets: [
                {
                    data: [300, 50, 100],
                    backgroundColor: [
                        "#9c3a4e",
                        "#1a4466",
                        "#a0a0a0"
                    ],
                    hoverBackgroundColor: [
                        "#FF6384",
                        "#36A2EB",
                        "#FFCE56"
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
                labels: ["Red", "Blue", "Yellow", "Green", "Purple", "Orange", "Orange", "Orange", "Orange"],
                datasets: [{
                    label: '# of Votes',
                    data: data,
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