<%-- 
    Document   : createclaim
    Created on : Mar 13, 2017, 10:26:56 PM
    Author     : minamaurer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <!--[if IE]>
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"><![endif]-->

        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>


        <title>Send a claim</title>

        <link rel="stylesheet" href="assets/js/jquery-ui/css/no-theme/jquery-ui-1.10.3.custom.min.css"
              id="style-resource-1">
        <link rel="stylesheet" href="assets/css/font-icons/font-awesome/css/font-awesome.min.css" id="style-resource-1">
        <link rel="stylesheet" href="assets/css/font-icons/entypo/css/entypo.css" id="style-resource-2">
        <link rel="stylesheet" href="assets/css/font-icons/entypo/css/animation.css" id="style-resource-3">
        <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Noto+Sans:400,700,400italic"
              id="style-resource-4">
        <link rel="stylesheet" href="assets/css/main.css" id="style-resource-5">
        <link rel="stylesheet" href="assets/css/custom.css" id="style-resource-6">
        <!--<link rel="stylesheet" href="assets/css/filesupload.css">-->

        <script src="assets/js/jquery-1.10.2.min.js"></script>

    </head>
    <jsp:useBean id="beanCM" class="entity.ClaimManage" scope="session"></jsp:useBean>
    <jsp:useBean id="account" class="entity.Account" scope="session"></jsp:useBean>
    <c:if test="${account.lever != 1}">
        <jsp:forward page="logout.jsp"></jsp:forward>
    </c:if>
    <body class="page-body">

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

                <hr/>


                <h2 class="text-center">Create a claim</h2>
                <br/>

                <div class="panel panel-primary" style="padding: 10px">
                    <form  id="addclaim" name="addclaim"  method="POST" class="form-horizontal" onsubmit="move()"   >
                        <div class="form-group">
                            <label for="subject" class="col-sm-3 control-label">Subject</label>
                            <div class="col-sm-9">
                                <input type="text" id="subject" name="subject" placeholder="Input your claim subject" class="form-control"/>
                            </div>
                        </div>

                        <input type="hidden" value="${beanCM.id}" name="idCM"/>
                        <input type="hidden" value="${idMajor}" name="idM"/>
                        <div class="form-group">
                            <label for="description" class="col-sm-3 control-label">Description</label>
                            <div class="col-sm-9">
                                <textarea rows="8" placeholder="Input your claim description" class="form-control" name="description" id="description"></textarea>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">Attachments</label>

                            <div class="col-sm-5">
                                <progress value="0" max="100" id="uploader">0%</progress><span id="stt-file"></span>
                                <input type="file" id="fileButton" name="file" class="btn btn-default"
                                       />

                                <input type="hidden" value="" name="linkfile" id="linkfile"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-sm-9 col-sm-offset-3">
                                <input class="btn btn-success" type="submit" id="btnSend" value="Send Claim"/>
                                <button class="btn btn-default pull-right" type="reset">Reset</button>

                            </div>

                        </div>

                    </form>


                </div><!-- Footer -->

            </div>

        </div>


        <script src="assets/js/gsap/main-gsap.js" id="script-resource-1"></script>
        <script src="assets/js/jquery-ui/js/jquery-ui-1.10.3.minimal.min.js" id="script-resource-2"></script>
        <script src="assets/js/bootstrap.min.js" id="script-resource-3"></script>
        <script src="assets/js/joinable.js" id="script-resource-4"></script>
        <script src="assets/js/resizeable.js" id="script-resource-5"></script>
        <script src="assets/js/fileinput.js" id="script-resource-7"></script>

        <script src="assets/js/api.js" id="script-resource-6"></script>
        <script src="assets/js/custom.js" id="script-resource-8"></script>
        <script src="assets/js/demo.js" id="script-resource-9"></script>
        <script src="https://www.gstatic.com/firebasejs/3.7.6/firebase.js"></script>
        <script type="text/javascript">
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



                        function validate() {

                            var flag = true;
                            var subject = document.getElementById('subject');
                            var description = document.getElementById('description');

                            if (subject.value == "")
                            {
                                return flag = false;
                            }
                            if (description.value == "")
                            {
                                return flag = false;
                            }
                            return flag;
                        }
                        function move() {
                            var flag = validate();
                            if (flag) {
                                addclaim.action = "StudentsController?action=createClaim&Uid=${idUser}";
                                addclaim.submit();
                            } else {
                                alert("Please, Enter full fields! ");
                            }
                        }


        </script>

    </body>
</html>