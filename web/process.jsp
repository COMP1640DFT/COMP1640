<%-- 
    Document   : process
    Created on : Mar 7, 2017, 3:08:52 PM
    Author     : minamaurer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
        <!--<link rel="stylesheet" href="css/bootstrap-theme.min.css">-->
        <link rel="stylesheet" href="css/custom.css">
        <link rel="stylesheet" href="css/jquery.dataTables.min.css">
        <link href="https://fonts.googleapis.com/css?family=Noto+Sans:400,700&amp;subset=vietnamese" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Lobster" rel="stylesheet">
        <link rel="stylesheet" type="text/css"
              href="https://cdn.datatables.net/buttons/1.2.4/css/buttons.dataTables.min.css"/>
        <link rel="stylesheet" type="text/css" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
        <link rel="stylesheet" type="text/css" href="css/responsive.jqueryui.min.css">
        <link rel="stylesheet" type="text/css" href="css/responsive.dataTables.min.css">
        <title>Process</title>
        <link rel="stylesheet" href="css/bootstrap-material-design.min.css">
        <style>
            .form-group{
                margin-right: 30px;
                margin-top: 0;
            }
        </style>
    </head>
    <body>
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
                            <li><a href="Controller?action=viewC">View process</a></li>
                            <li><a href="#">View statistic</a></li>
                            <li><a href="#">About university</a></li>
                            <li><a href="#">Logout</a></li>
                        </ul>
                    </div>
                    <!-- /.navbar-collapse -->
                </div>
                <!-- /.container-fluid -->
            </nav>
            <div class="row">
                <div class="col-sm-12 panel panel-default">

                    <div class="panel-body">
                        <div class="col-sm-12">
                            <h2 class="panel-heading text-center" style="padding: 0">Claim infomation</h2>
                            <form class="form-inline" action="#" method="get">
                                <div class="form-group">
                                    <label for="option3">Faculty: </label>
                                    <select id="option3" class="form-control" name="option1">
                                        <option value="1">value 1</option>
                                        <option value="2">value 2</option>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label for="option4">Year: </label>
                                    <select id="option4" name="option2" class="form-control">
                                        <option value="1">value 1</option>
                                        <option value="2">value 2</option>
                                    </select>
                                </div>
                                <div class="form-group right" style="float: right;margin-right: 0">
                                    <button type="submit" class="btn btn-raised"><i class="glyphicon glyphicon-search"></i> Search</button>
                                </div>
                            </form>
                        </div>
                        <div class="clearfix"></div>
                        <div class="col-sm-12" style="margin-bottom: 50px">
                            <form action="Controller?action=viewC"  method="POST"  >
                                <table class="dataTable table table-bordered table table-responsive responsive">
                                    <thead>
                                    <th>Student ID</th>
                                    <th>EC Coordinator</th>
                                    <th>Contents</th>
                                    <th>Datetime</th>

                                    </thead>
                                    <tfoot>
                                    <th>Student ID</th>
                                    <th>EC Coordinator</th>
                                    <th>Contents</th>
                                    <th>Datetime</th>
                                    </tfoot>
                                    <tbody>
                                        <c:forEach items="${sessionScope['listClaim']}" var="cl">
                                            <tr>
                                            <td>${cl.idUser}</td>
                                            <td>${cl.decision.idUser}</td>
                                          
                                            <td><a href="Controller?action=viewDetail&id=${cl.idClaim}" class="btn-reader">${cl.content}</a></td>
                                            <td>${cl.date}</td>
                                        </tr>
                                        </c:forEach>
                                       
                                    </tbody>
                                </table>
                            </form>
                        </div>
                    </div>
                </div>
            </div>


        </div>
        <div class="modal fade" tabindex="-1" role="dialog" id="reader-model">
            <div class="modal-dialog modal-lg" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title">Modal title</h4>
                    </div>
                    <div class="modal-body">
                        <iframe id="reader-frame" width="100%" height="400" ></iframe>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    </div>
                </div><!-- /.modal-content -->
            </div><!-- /.modal-dialog -->
        </div><!-- /.modal -->
        <!-- Button trigger modal -->
        <button type="button" style="display: none" id="modal-btn" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#reader-model">
            Launch demo modal
        </button>

        <script src="js/jquery-3.1.1.min.js"></script>
        <script src="js/bootstrap.min.js"></script>

        <script src="js/material.min.js"></script>
        <script src="js/ripple.min.js"></script>
        <script src="js/Chart.min.js"></script>
        <script src="js/jquery.dataTables.min.js"></script>
        <script src="js/responsive.jqueryui.min.js"></script>
        <script src="js/dataTables.responsive.min.js"></script>
        <script src="js/main.js"></script>
        <script>
            $("#menu-toggle").click(function (e) {
                e.preventDefault();
                $("#wrapper").toggleClass("toggled");
            });

            $(document).ready(function () {
                $('.dataTable').DataTable({
                    searching: false, responsive: true
                });
                $('.btn-reader').click(function (e) {
                    e.preventDefault();
                    $('#reader-frame').attr('src', $(this).attr('href'));
                    $('#reader-model .modal-title').html($(this).html());
                    $('#modal-btn').click();
                });

            });



        </script>
    </body>
</html>
