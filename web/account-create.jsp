<%-- 
    Document   : account-create
    Created on : Apr 5, 2017, 10:47:10 PM
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

        <title>Creating Account</title>

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
                            <a href="#"><i class="entypo-user"></i><span>Accounts</span></a>
                            <ul>


                                <li><a href="AdminController?action=openCreateUser"><i class="entypo entypo-user-add"></i>Create</a></li>
                                <li><a href="AdminController?action=viewAllUser"><i class="entypo entypo-users"></i>View</a></li>
                            </ul>
                        </li>
                        <li><a href="#"><i class="entypo-suitcase"></i>Claims</a>
                            <ul>
                                <li><a href="AdminController?action=openShedule">Create</a></li>
                                <li><a href="AdminController?action=adminViewAll">View</a></li>
                            </ul>

                        </li>
                        <li><a href="AdminController?action=viewAllFaculty"><i class="entypo-suitcase"></i><span>Faculty</span></a></li>
                        <li><a href="logout.jsp"><i class="entypo-logout"></i>Logout</a></li>
                    </ul>


            </div>
            <div class="main-content">
                <h2>Welcome: ${account.idUser}</h2>

                <br/>
                <div class="col-sm-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h1 class="panel-title">Create new account</h1>
                        </div>

                        <div class="panel-body">
                            <form name="addAcc" action="AdminController?action=createUser" onsubmit="return validate()" method="post" class="form-horizontal form-groups-bordered">
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">Account ID</label>
                                    <div class="col-sm-5"><input type="text" class="form-control" name="id" id="id"/></div>
                                      <div class="classerror" id="erroraccount"></div>  
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">Password</label>
                                    <div class="col-sm-5"><input type="password" class="form-control" name="password" id="password"/></div>
                                     <div class="classerror" id="errorpass"></div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">Confirm Password</label>
                                    <div class="col-sm-5"><input type="password" class="form-control" name="passwordcf" id="passwordcf"/></div>
                                     <div class="classerror" id="errorpasscf"></div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">Full name</label>
                                    <div class="col-sm-5"><input type="text" class="form-control" name="name" id="name"/></div>
                                     <div class="classerror" id="errorfullname"></div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">Email address</label>
                                    <div class="col-sm-5"><input type="email" class="form-control" name="email" id="email"/></div>
                                     <div class="classerror" id="erroremail"></div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">Phone number</label>
                                    <div class="col-sm-5"><input type="tel" class="form-control" name="phone" id="phone"/></div>
                                    <div class="classerror" id="errorphone"></div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">Birth date</label>
                                    <div class="col-sm-3"><input type="date" class="form-control" name="birthdate"/></div>
                                      <div class="classerror" id="errordate"></div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">Role</label>
                                    <div class="col-sm-3">
                                        <select class="form-control" name="role">
                                           
                                            <option value="3">Manager</option>
                                            <option value="4">Coordinator</option>
                                            <option value="1">Student</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group" >
                                    <label class="col-sm-3 control-label">Faculty</label>
                                    <div class="col-sm-3">
                                        <select class="form-control" name="faculty">
                                           
                                             <c:forEach items="${sessionScope['listFaculty']}" var="Faculty">
                                                <option  value="${Faculty.id}">${Faculty.name}</option>
                                            </c:forEach>
                                        </select>
                                    </div>

                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">Academy Year</label>
                                    <div class="col-sm-3">
                                        <select class="form-control" name="academy">
                                           
                                            <c:forEach items="${sessionScope['listAcademy']}" var="Academy">
                                                <option  value="${Academy.id}">${Academy.name}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-sm-7 col-sm-offset-5">
                                       <button class="btn btn-success"  type="submit"><i class="entypo entypo-user-add"></i> Create</button>
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
        <script src="assets/js/joinable.js" id="script-resource-4"></script>
        <script src="assets/js/resizeable.js" id="script-resource-5"></script>
        <script src="assets/js/api.js" id="script-resource-6"></script>
        <script src="assets/js/custom.js" id="script-resource-7"></script>
        <script src="assets/js/demo.js" id="script-resource-8"></script>


    </body>
     <script type="text/javascript">
        document.addAcc.id.addEventListener('focus', function (){
            $('#erroraccount').text("");
        });
        
        document.addAcc.password.addEventListener('focus', function (){
            $('#errorpass').text("");
        });
        
        document.addAcc.passwordcf.addEventListener('focus', function (){
            $('#errorpasscf').text("");
        });
        
        document.addAcc.email.addEventListener('focus', function (){
            $('#erroremail').text("");
        });
        
        document.addAcc.birthdate.addEventListener('focus', function (){
            $('#errordate').text("");
        });
        
        document.addAcc.name.addEventListener('focus', function (){
            $('#errorfullname').text("");
        });
        
        document.addAcc.phone.addEventListener('focus', function (){
            $('#errorphone').text("");
        });

        function validate() {
            var result=0;
            if (document.addAcc.id.value == "") {
                $('#erroraccount').text("Please input your Account Id!");
                result=result+1;
                
            }
            if (document.addAcc.password.value != "") {
                if (document.addAcc.password.value.length < 6) {
                    $('#errorpass').text("Please enter at least 6 characters!");
                    result=result+1;
                }
            }
            if (document.addAcc.password.value == "") {
                $('#errorpass').text("Please enter password!");
                result=result+1;
            }
            if (document.addAcc.password.value != document.addAcc.passwordcf.value) {
                $('#errorpasscf').text("Confirm password incorrect!");
                result=result+1;
            }
            if (document.addAcc.email.value == "") {
                $('#erroremail').text("Please input your Email!");
                result=result+1;
                
            }
             if (document.addAcc.birthdate.value == "") {
                $('#errordate').text("Please input your Birthday!");
                result=result+1;
                
            }
            if (document.addAcc.name.value == "") {
                $('#errorfullname').text("Please input your Name!");
                result=result+1;
                
            }
            if (document.addAcc.phone.value == "") {
                $('#errorphone').text("Please input your Phone!");
                result=result+1;
                
            }
            
            if(result>0) {
               return false;
            }
            else{
                return  document.editForm.submit();
            }
        }



    </script>
</html>