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
                    <li><a href="Controller?action=viewC">View process</a></li>
                    <li><a href="Controller?action=viewstatistic">View statistic</a></li>
                    <li><a href="Controller?action=viewStatisticChart">View chart</a></li>
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