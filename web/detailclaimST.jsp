<%-- 
    Document   : detailclaim
    Created on : Mar 13, 2017, 10:31:24 PM
    Author     : minamaurer
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
    </head>
    <body class="page-body">
        <jsp:useBean id="beanDecision" class="entity.Decision" scope="session"></jsp:useBean>
        <jsp:useBean id="beanClaim" class="entity.Claim" scope="session"></jsp:useBean>
        <jsp:useBean id="account" class="entity.Account" scope="session"></jsp:useBean>
        <c:if test="${account.lever != 1}">
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
                        <a href="StudentsController?action=viewAllCM"><i class="glyphicon glyphicon-home"></i> All Claims</a>
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


                                Welcome: <strong>${idUser} (${fullName})</strong>


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
                                    ${beanClaim.title}
                                    <!--                                    <p>
                                                                            <span class="text-danger">Remaining time: 13 days 4 hours</span>
                                    
                                                                        </p>-->

                                </div>

                                <!-- links -->
                                <div class="mail-links">

                                    <span>Status: <span class="label label-success">Open</span></span>

                                </div>
                                <br/><br/>

                                <p>${beanClaim.title}</p>
                            </div>


                            <div class="mail-info">

                                <div class="mail-sender dropdown">

                                    <a href="#">
                                        EC Coordinator: <span>${beanDecision.fullNameEC}</span>
                                        <span>(${beanDecision.idUserEC})</span>
                                    </a>


                                </div>

                                <div class="mail-date">
                                    ${beanDecision.sendDate}
                                </div>

                            </div>

                            <div class="mail-text">

                                <p>${beanDecision.content}</p>


                            </div>

                            <div class="mail-attachments">

                                <h4>
                                    <i class="entypo-attach"></i> Attachments <span></span>
                                </h4>
                                <c:if test="${beanClaim.filedata != ''}">
                                    <ul>
                                        <a href="${beanClaim.filedata}" class="thumb">
                                            <img src="http://placehold.it/350x150?text=File" class="img-rounded"/>
                                        </a>
                                    </ul>
                                </c:if>

                                <form action="StudentsController?action=updateFile" method="post">
                                    <div class="form-group">

                                        <progress value="0" max="100" id="uploader">0%</progress><span id="stt-file"></span>
                                        <input type="file" id="fileButton" name="file" class="btn btn-default"
                                               />

                                        <input type="hidden" value="" name="linkfile" id="linkfile"/>

                                    </div>
                                    <div class="form-group">

                                        <input class="btn btn-success" disabled="true" type="submit" id="btnSend" value="Update"/>
                                    </div>

                                </form>
                            </div>
                            <div class="clearfix" style="margin-bottom: 20px"></div>

                        </div>

                        <!--                    <div class="mail-reply">
                        
                                                <div class="fake-form owner">
                                                    New the her nor case that lady paid read. Invitation friendship travelling eat everything
                                                    the
                                                    out two. Shy you who scarcely expenses debating hastened resolved.
                                                    <p><strong>Phan Huyen Trang</strong> - 2017/03/03 08:30pm</p>
                                                </div>
                                                <div class="fake-form owner">
                                                    New the her nor case that lady paid read. Invitation friendship travelling eat everything
                                                    the
                                                    out two. Shy you who scarcely expenses debating hastened resolved.
                                                    <p><strong>Phan Huyen Trang</strong> - 2017/03/03 08:50pm</p>
                                                </div>
                                                <div class="fake-form coordinator">
                                                    New the her nor case that lady paid read. Invitation friendship travelling eat everything
                                                    the
                                                    out two. Shy you who scarcely expenses debating hastened resolved.
                                                    <p><strong>Dao Minh Thien</strong> - 2017/03/03 08:55pm</p>
                                                </div>
                                                <div class="fake-form">
                                                    <form action="#" method="post">
                                                        <div class="form-group">
                                                            <label for="message">Message:</label>
                                                            <textarea class="form-control" name="message" id="message"></textarea>
                                                        </div>
                                                        <div class="form-group">
                                                            <button type="submit" class="btn btn-success"><i
                                                                    class="glyphicon glyphicon-send"></i> Send
                                                            </button>
                                                        </div>
                                                    </form>
                                                </div>
                        
                                            </div>-->

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

    <script src="https://www.gstatic.com/firebasejs/3.7.6/firebase.js"></script>
    <script>
        var config = {
            apiKey: "AIzaSyD-iK-14OkVNECKE4w4O6-pxhJLVtN8a1k",
            authDomain: "comp1640-164013.firebaseapp.com",
            databaseURL: "https://comp1640-164013.firebaseio.com",
            projectId: "comp1640-164013",
            storageBucket: "comp1640-164013.appspot.com",
            messagingSenderId: "693486338842"
        };
        firebase.initializeApp(config);

        var uploader = document.getElementById('uploader');
        var fileButton = document.getElementById('fileButton');
        var sttfile = document.getElementById('stt-file');
        //
        fileButton.addEventListener('change', function (e) {
            // get file
            var file = e.target.files[0];
            var max_file_size = 20000000;
            var d = new Date();

            if (file.size > max_file_size) {
                console.log(file.size);
            } else {
                $('#btnSend').prop("disabled", true);
                var storageRef = firebase.storage().ref('files/' + d.getTime() + "_${account.idUser}_" + file.name);

                // upload file;
                var task = storageRef.put(file);
                sttfile.innerHTML = "Uploading..";
                task.on('state_changed',
                        function progress(snapshot) {
                            var percentage = (snapshot.bytesTransferred / snapshot.totalBytes) * 100;
                            uploader.value = percentage;
                        },
                        function error(err) {

                        },
                        function complete() {

                        }
                );

                task.then(function (snapshot) {
                    console.log('Uploaded', snapshot.totalBytes, 'bytes.');
                    console.log(snapshot.metadata);
                    var url = snapshot.downloadURL;
                    console.log('File available at', url);
                    // [START_EXCLUDE]
                    document.getElementById('linkfile').value = url;
                    sttfile.innerHTML = "Done!";
                    $('#btnSend').prop("disabled", false);
                    // [END_EXCLUDE]
                }).catch(function (error) {
                    // [START onfailure]
                    console.error('Upload failed:', error);
                    // [END onfailure]
                });
            }
        });
    </script>

</body>
</html>