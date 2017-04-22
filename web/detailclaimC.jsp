<%-- 
    Document   : detailclaim
    Created on : Mar 27, 2017, 4:41:20 PM
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


        <title>Claim</title>

        <link rel="stylesheet" href="assets/js/jquery-ui/css/no-theme/jquery-ui-1.10.3.custom.min.css">
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
        <script>

            var select = $('select')[0];
            select.selectedIndex = 0;
        </script>
    </head>
    
    <body class="page-body">
        <jsp:useBean id="claimD" class="entity.Claim" scope="session"></jsp:useBean>
        <jsp:useBean id="decision" class="entity.Decision" scope="session"></jsp:useBean>
        <jsp:useBean id="account" class="entity.Account" scope="session"></jsp:useBean>
        <c:if test="${account.lever != 4}">
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

                        <li class="active">
                            <a href="CoordinatorController?action=viewAllClaim"><i class="glyphicon glyphicon-home"></i> Home</a>
                        </li>
                        <li><a href="eccoorChangePwd.jsp"><i class="glyphicon glyphicon-lock"></i>Change password</a></li>
                        <li><a href="logout"><i class="glyphicon glyphicon-log-out"></i> Logout</a></li>

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


                                    Welcome: <strong>${fullName}</strong>


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
                                                    <span class="image pull-right">
                                                        <img src="assets/images/thumb-1.png" alt="" class="img-circle"/>
                                                    </span>

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
                                                    <span class="image pull-right">
                                                        <img src="assets/images/thumb-2.png" alt="" class="img-circle"/>
                                                    </span>

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
                                                    <span class="image pull-right">
                                                        <img src="assets/images/thumb-3.png" alt="" class="img-circle"/>
                                                    </span>

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
                                                    <span class="image pull-right">
                                                        <img src="assets/images/thumb-4.png" alt="" class="img-circle"/>
                                                    </span>

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
                <div class="panel panel-primary">
                    <div class="mail-env">
                        <div class="mail-body">

                            <div class="mail-header">
                                <!-- title -->
                                <div class="mail-title">
                                    ${claimD.title}
                                    <p>
                                        <span class="text-danger">Remaining time: 13 days 4 hours</span>

                                    </p>

                                </div>

                                <!-- links -->
                                <div class="mail-links">
                                    <c:if test="${claimD.status == 0}">
                                        <span>Status: <span class="label label-default">Waiting</span></span>
                                    </c:if>
                                    <c:if test="${claimD.status == 1}"> 
                                        <span>Status: <span class="label label-success">Approve</span></span>
                                    </c:if>
                                    <c:if test="${claimD.status == 2}"> 
                                        <span>Status: <span class="label label-danger">Reject</span></span>
                                    </c:if>  
                                </div>
                            </div>

                            <div class="mail-info">

                                <div class="mail-sender dropdown">

                                    <a href="#">
                                        Student: <span> ${claimD.idUser}</span>
                                        <span>( ${claimD.userFullName})</span>
                                    </a>


                                </div>

                                <div class="mail-date">
                                    ${claimD.sendDate}
                                </div>

                            </div>

                            <div class="mail-text">

                                ${claimD.content}
                            </div>

                            <div class="mail-attachments">

                                <h4>
                                    <i class="entypo-attach"></i> Attachments <span></span>
                                </h4>

                                <c:if test="${claimD.filedata != ''}">
                                    <ul>
                                        <li>
                                            <a href="${claimD.filedata}" class="thumb">
                                                <img src="http://placehold.it/350x150?text=File" class="img-rounded"/>
                                            </a>
                                        </li>
                                    </ul>
                                </c:if>

                            </div>

                            <div class="mail-reply">
                                <c:choose>
                                    <c:when test="${claimD.status!=0}">
                                        <div class="fake-form coordinator">
                                            ${decision.content}
                                            <p><strong>${decision.fullNameEC}</strong>   ${decision.sendDate}</p>
                                        </div>
                                    </c:when>
                                </c:choose>

                                <div class="fake-form">
                                    <form action="CoordinatorController?action=AddMessage" method="post">
                                        <div class="form-group">
                                            <label for="message">Status:</label>
                                            <select style="margin-top: 10px;"addMessage name="selectStatus" id="selectStatus"  >                          
                                                <option  value="1"   >Approve</option>
                                                <option  value="0" >Reject</option>
                                            </select><br/>
                                            <label for="message">Message:</label>
                                            <textarea class="form-control" name="message" id="message"></textarea>
                                        </div>
                                        <div class="form-group">
                                            <input type="submit" class="btn btn-success" value="${textbtn}">

                                        </div>
                                    </form>
                                </div>

                            </div>

                        </div>
                    </div>
                </div><!-- Footer -->

            </div>

        </div>


        <script src="assets/js/gsap/main-gsap.js" id="script-resource-1"></script>
        <script src="assets/js/jquery-ui/js/jquery-ui-1.10.3.minimal.min.js" id="script-resource-2"></script>
        <script src="assets/js/bootstrap.min.js" id="script-resource-3"></script>
        <script src="assets/js/joinable.js" id="script-resource-4"></script>
        <script src="assets/js/resizeable.js" id="script-resource-5"></script>
        <script src="assets/js/mail.js" id="script-resource-7"></script>
        <script src="assets/js/api.js" id="script-resource-6"></script>
        <script src="assets/js/custom.js" id="script-resource-8"></script>
        <script src="assets/js/demo.js" id="script-resource-9"></script>

    </body>
</html>
