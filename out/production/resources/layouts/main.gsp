<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="e-learning system">
    <meta name="author" content="Petar Djurickovic">
    <title>
        <g:layoutTitle default="Grails"/>
    </title>
    <asset:link rel="icon" href="favicon.ico" type="image/x-ico"/>
    <asset:stylesheet src="application.css"/>
    <g:layoutHead/>
</head>
<body>
<div id="wrapper">
    <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">
        <a class="sidebar-brand d-flex align-items-center justify-content-center" href="/">
            <div class="sidebar-brand-icon rotate-n-15">
                <i class="fas fa-laugh-wink"></i>
            </div>
            <div class="sidebar-brand-text mx-3">E-Learning</div>
        </a>
        <hr class="sidebar-divider my-0">
        <li class="nav-item">
            <a class="nav-link" href="/">
                <i class="fa fa-fw fa-tachometer-alt"></i>
                <span><g:message code="begin" /></span></a>
        </li>
<sec:ifAnyGranted roles="ROLE_ADMIN,ROLE_INSTRUCTOR">
        <hr class="sidebar-divider">
        <div class="sidebar-heading">
            <g:message code="account" />
        </div>
        <li class="nav-item">
            <a class="nav-link" href="#">
                <i class="fas fa-fw fa-user"></i>
                <span><g:message code="account" /></span>
            </a>
        </li>
</sec:ifAnyGranted>
        <hr class="sidebar-divider">
        <div class="sidebar-heading">
            <g:message code="courses" />

        </div>
        <li class="nav-item">
            <a class="nav-link" href="${createLink(controller:'course',action:'index')}">
                <i class="fas fa-fw fa-book-open"></i>
                <span><g:message code="courses.browse" /></span>
            </a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="${createLink(controller:'course',action:'enr')}">
                <i class="fas fa-fw fa-bookmark"></i>
                <span><g:message code="enrollments.my" /></span>
            </a>
        </li>
        <sec:ifAnyGranted roles="ROLE_ADMIN,ROLE_INSTRUCTOR">
            <li class="nav-item">
                <a class="nav-link" href="${createLink(controller:'course',action:'create')}">
                    <i class="fas fa-fw fa-bookmark"></i>
                    <span>Create course</span>
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${createLink(controller:'course',action:'myCourses')}">
                    <i class="fas fa-fw fa-bookmark"></i>
                    <span>My courses</span>
                </a>
            </li>
        </sec:ifAnyGranted>
        <hr class="sidebar-divider">
        <div class="sidebar-heading">
            <g:message code="support" />
        </div>
        <li class="nav-item">
            <a class="nav-link" href="#">
                <i class="fas fa-fw fa-question-circle"></i>
                <span><g:message code="help.get" /></span>
            </a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="#">
                <i class="fas fa-fw fa-envelope "></i>
                <span><g:message code="help.contact" /></span>
            </a>
        </li>
        <hr class="sidebar-divider d-none d-md-block">
        <div class="text-center d-none d-md-inline">
            <button class="rounded-circle border-0" id="sidebarToggle"></button>
        </div>
    </ul>

    <div id="content-wrapper" class="d-flex flex-column">
        <div id="content">
            <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">
                <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
                    <i class="fa fa-bars"></i>
                </button>
                <!-- Topbar Navbar ***** -->
                <ul class="navbar-nav ml-auto">
                    <sec:ifLoggedIn>
                        <!-- Nav Item - Alerts -->
%{--                        <li class="nav-item dropdown no-arrow mx-1">--}%
%{--                            <a class="nav-link dropdown-toggle" href="#" id="alertsDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">--}%
%{--                                <i class="fas fa-bell fa-fw"></i>--}%
%{--                                <!-- Counter - Alerts -->--}%
%{--                                <span class="badge badge-danger badge-counter">3+</span>--}%
%{--                            </a>--}%
%{--                            <!-- Dropdown - Alerts -->--}%
%{--                            <div class="dropdown-list dropdown-menu dropdown-menu-right shadow animated--grow-in" aria-labelledby="alertsDropdown">--}%
%{--                                <h6 class="dropdown-header">--}%
%{--                                    Alerts Center--}%
%{--                                </h6>--}%
%{--                                <a class="dropdown-item d-flex align-items-center" href="#">--}%
%{--                                    <div class="mr-3">--}%
%{--                                        <div class="icon-circle bg-primary">--}%
%{--                                            <i class="fas fa-file-alt text-white"></i>--}%
%{--                                        </div>--}%
%{--                                    </div>--}%
%{--                                    <div>--}%
%{--                                        <div class="small text-gray-500">December 12, 2019</div>--}%
%{--                                        <span class="font-weight-bold">A new monthly report is ready to download!</span>--}%
%{--                                    </div>--}%
%{--                                </a>--}%
%{--                                <a class="dropdown-item d-flex align-items-center" href="#">--}%
%{--                                    <div class="mr-3">--}%
%{--                                        <div class="icon-circle bg-success">--}%
%{--                                            <i class="fas fa-donate text-white"></i>--}%
%{--                                        </div>--}%
%{--                                    </div>--}%
%{--                                    <div>--}%
%{--                                        <div class="small text-gray-500">December 7, 2019</div>--}%
%{--                                        $290.29 has been deposited into your account!--}%
%{--                                    </div>--}%
%{--                                </a>--}%
%{--                                <a class="dropdown-item d-flex align-items-center" href="#">--}%
%{--                                    <div class="mr-3">--}%
%{--                                        <div class="icon-circle bg-warning">--}%
%{--                                            <i class="fas fa-exclamation-triangle text-white"></i>--}%
%{--                                        </div>--}%
%{--                                    </div>--}%
%{--                                    <div>--}%
%{--                                        <div class="small text-gray-500">December 2, 2019</div>--}%
%{--                                        Spending Alert: We've noticed unusually high spending for your account.--}%
%{--                                    </div>--}%
%{--                                </a>--}%
%{--                                <a class="dropdown-item text-center small text-gray-500" href="#">Show All Alerts</a>--}%
%{--                            </div>--}%
%{--                        </li>--}%
%{--                        <!-- Nav Item - Messages -->--}%
%{--                        <li class="nav-item dropdown no-arrow mx-1">--}%
%{--                            <a class="nav-link dropdown-toggle" href="#" id="messagesDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">--}%
%{--                                <i class="fas fa-envelope fa-fw"></i>--}%
%{--                                <!-- Counter - Messages -->--}%
%{--                                <span class="badge badge-danger badge-counter">7</span>--}%
%{--                            </a>--}%
%{--                            <!-- Dropdown - Messages -->--}%
%{--                            <div class="dropdown-list dropdown-menu dropdown-menu-right shadow animated--grow-in" aria-labelledby="messagesDropdown">--}%
%{--                                <h6 class="dropdown-header">--}%
%{--                                    Message Center--}%
%{--                                </h6>--}%
%{--                                <div class="dropdown-list-image mr-3">--}%
%{--                                    <img class="rounded-circle" src="https://source.unsplash.com/fn_BT9fwg_E/60x60" alt="">--}%
%{--                                    <div class="status-indicator bg-success"></div>--}%
%{--                                </div>--}%
%{--                                <div class="font-weight-bold">--}%
%{--                                    <div class="text-truncate">Hi there! I am wondering if you can help me with a problem I've been having.</div>--}%
%{--                                    <div class="small text-gray-500">Emily Fowler · 58m</div>--}%
%{--                                </div>--}%
%{--                            </a>--}%
%{--                                <a class="dropdown-item d-flex align-items-center" href="#">--}%
%{--                                    <div class="dropdown-list-image mr-3">--}%
%{--                                        <img class="rounded-circle" src="https://source.unsplash.com/AU4VPcFN4LE/60x60" alt="">--}%
%{--                                        <div class="status-indicator"></div>--}%
%{--                                    </div>--}%
%{--                                    <div>--}%
%{--                                        <div class="text-truncate">I have the photos that you ordered last month, how would you like them sent to you?</div>--}%
%{--                                        <div class="small text-gray-500">Jae Chun · 1d</div>--}%
%{--                                    </div>--}%
%{--                                </a>--}%
%{--                                <a class="dropdown-item d-flex align-items-center" href="#">--}%
%{--                                    <div class="dropdown-list-image mr-3">--}%
%{--                                        <img class="rounded-circle" src="https://source.unsplash.com/CS2uCrpNzJY/60x60" alt="">--}%
%{--                                        <div class="status-indicator bg-warning"></div>--}%
%{--                                    </div>--}%
%{--                                    <div>--}%
%{--                                        <div class="text-truncate">Last month's report looks great, I am very happy with the progress so far, keep up the good work!</div>--}%
%{--                                        <div class="small text-gray-500">Morgan Alvarez · 2d</div>--}%
%{--                                    </div>--}%
%{--                                </a>--}%
%{--                                <a class="dropdown-item d-flex align-items-center" href="#">--}%
%{--                                    <div class="dropdown-list-image mr-3">--}%
%{--                                        <img class="rounded-circle" src="https://source.unsplash.com/Mv9hjnEUHR4/60x60" alt="">--}%
%{--                                        <div class="status-indicator bg-success"></div>--}%
%{--                                    </div>--}%
%{--                                    <div>--}%
%{--                                        <div class="text-truncate">Am I a good boy? The reason I ask is because someone told me that people say this to all dogs, even if they aren't good...</div>--}%
%{--                                        <div class="small text-gray-500">Chicken the Dog · 2w</div>--}%
%{--                                    </div>--}%
%{--                                </a>--}%
%{--                                <a class="dropdown-item text-center small text-gray-500" href="#">Read More Messages</a>--}%
%{--                            </div>--}%
%{--                        </li>--}%
                        <div class="topbar-divider d-none d-sm-block"></div>
                        <!-- Nav Item - User Information -->
                        <li class="nav-item dropdown no-arrow">
                            <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <span class="mr-2 d-none d-lg-inline text-gray-600 small"><sec:username/></span>
                                <img class="img-profile rounded-circle" src="https://source.unsplash.com/QAB-WJcbgJk/60x60">
                            </a>
                            <!-- Dropdown - User Information -->
                            <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in" aria-labelledby="userDropdown">
                                <a class="dropdown-item" href="#">
                                    <i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i>
                                    Profile
                                </a>
                                <a class="dropdown-item" href="#">
                                    <i class="fas fa-cogs fa-sm fa-fw mr-2 text-gray-400"></i>
                                    Settings
                                </a>
                                <a class="dropdown-item" href="#">
                                    <i class="fas fa-list fa-sm fa-fw mr-2 text-gray-400"></i>
                                    Activity Log
                                </a>
                                <div class="dropdown-divider"></div>
                                <a class="dropdown-item" href="#" data-toggle="modal" data-target="#logoutModal">
                                    <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
                                    <g:message code='logout.label' />
                                </a>
                            </div>
                        </li>
                    </sec:ifLoggedIn>
                </ul>
                <sec:ifNotLoggedIn>
                    <a class="btn btn-primary btn-sm" href="${createLink(controller:'login')}" role="button"><g:message code='login.btn.label' /></a>
                </sec:ifNotLoggedIn>
            </nav>

            <div class="container-fluid">
                <g:layoutBody/>
            </div>
        </div>
        <footer class="sticky-footer bg-white">
            <div class="container my-auto">
                <div class="copyright text-center my-auto">
                    <span>Copyright &copy; Petar Djurickovic 2022</span>
                </div>
            </div>
        </footer>
    </div>
</div>
<a class="scroll-to-top rounded" href="#page-top">
    <i class="fas fa-angle-up"></i>
</a>
<!-- Logout Modal-->
<div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
                <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">×</span>
                </button>
            </div>
            <div class="modal-body">Select "Logout" below if you are ready to end your current session.</div>
            <div class="modal-footer">
                <g:link method="post" elementId='logout' controller='logout' class="btn btn-primary"><g:message code='logout.label' /></g:link>
            </div>
        </div>
    </div>
</div>
<asset:javascript src="application.js"/>
</body>
</html>
