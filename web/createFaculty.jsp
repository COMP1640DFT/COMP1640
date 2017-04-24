<%-- 
    Document   : createFaculty
    Created on : Apr 19, 2017, 9:04:16 PM
    Author     : DaoMinhThien
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

        <title>Faculty</title>

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
        <c:if test="${account.lever != 2}">
            <jsp:forward page="logout.jsp"></jsp:forward>
        </c:if>

        <div class="page-container">

            <div class="sidebar-menu">

                <header class="logo-env">

                    <!-- logo -->
                    <div class="logo">
                        <img src="assets/images/logo.png" alt=""/>
                        <h3>Greenwich University</h3>
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
                    <li class="active"><a href="AdminController?action=viewAllFaculty"><i class="entypo-suitcase"></i><span>Faculty</span></a></li>
                    <li><a href="logout.jsp"><i class="entypo-logout"></i> Logout</a></li>
                </ul>


            </div>
            <div class="main-content">
                <h2>Welcome: ${account.idUser}</h2>

                <br/>
                <div class="col-sm-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h1 class="panel-title">List Faculty</h1>
                        </div>
                        <div class="panel-body">
                            <table class="table table-responsive table-bordered dataTable">
                                <thead>
                                    <tr>
                                        <th>ID</th>
                                        <th>Name</th>
                                        <th>Assessment</th>
                                        <th>----</th>
                                    </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="f" items="${sessionScope['listFaculty']}">
                                    <tr>
                                        <td>${f.id}</td>
                                        <td>${f.name}</td>
                                        <td><a href="AdminController?action=openCreateAsses&id=${f.id}" class="btn btn-default btn-icon"><i class="entypo entypo-plus"></i> Add</a></td>
                                        <td><a onclick="confirmDelete(${f.id})"  class="btn btn-default btn-icon"><i class="entypo entypo-trash"></i> Delete</a></td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
                <div class="col-sm-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h1 class="panel-title">Create a Faculty</h1>
                        </div>

                        <div class="panel-body">
                            <form action="AdminController?action=createFaculty" name="addFaculty" onsubmit="return validate()" method="post" class="form-horizontal form-groups-bordered">
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">Name</label>
                                    <div class="col-sm-3">
                                        <input name="name" class="form-control" type="text"/>
                                        <div id="errorFaculty" class="txtError"></div>
                                    </div>
                                </div>
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
            function confirmDelete(idF) {
                var r = confirm("Do you want to delete faculty?");
                if(r){
                    window.location.href="AdminController?action=deleteFaculty&id="+idF;
                }
            }
            document.addFaculty.name.addEventListener('focus', function () {
                                    $('#errorFaculty').text("");
                                });
            function validate() {
                var result=0;
                
                var names = [<c:forEach var="f" items="${sessionScope['listFaculty']}">'${f.name}',</c:forEach>]
                
                if (document.addFaculty.name.value == "") {
                    $('#errorFaculty').text("Please input name!");
                    result=result+1;
                }
                
                for(n in names){
                    if(document.addFaculty.name.value.toLowerCase() == names[n].toLowerCase()){
                        $('#errorFaculty').text("Name exists!");
                        result=result+1;
                        break;
                    }
                }
                
                
                if(result>0){
                    return false;
                }else{
                    return document.addFaculty.submit();
                }
            }

            $(document).ready(function () {

                $('.dataTable').DataTable({
                    searching: false,
                    bLengthChange: false
        //            responsive: {
        //                details: {
        //                    display: $.fn.dataTable.Responsive.display.modal( {
        //                        header: function ( row ) {
        //                            var data = row.data();
        //                            return 'Details for '+data[0]+' '+data[1];
        //                        }
        //                    } ),
        //                    renderer: $.fn.dataTable.Responsive.renderer.tableAll( {
        //                        tableClass: 'table'
        //                    } )
        //                }
        //            }
                });


            });
        </script>

    </body>
</html>