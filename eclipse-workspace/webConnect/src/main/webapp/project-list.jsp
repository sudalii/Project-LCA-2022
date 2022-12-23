<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="Content-Type" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>LCA Project</title>

    <!-- Custom fonts for this template-->
    <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link
        href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
        rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="css/sb-admin-2.css" rel="stylesheet">

    <!-- Custom styles for this page -->
    <link href="vendor/datatables/dataTables.bootstrap4.css" rel="stylesheet">

</head>

<body id="page-top">

    <!-- Page Wrapper -->
    <div id="wrapper">

        <!-- Sidebar -->
        <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

            <!-- Sidebar - Brand -->
            <a class="sidebar-brand d-flex align-items-center justify-content-center" href="index.html">
                <div class="sidebar-brand-icon">
                    <img src="img/keti_logo_119x25.jpg">
                    <!--<i class="fas fa-laugh-wink"></i>-->
                </div>
                <div class="sidebar-brand-text mx-3">User Input</div>
            </a>

            <!-- Divider -->
            <hr class="sidebar-divider my-0">

            <!-- Nav Item - Project Management -->
            <li class="nav-item active">
                <a class="nav-link" href="project-list">
                    <i class="fas fa-fw fa-tachometer-alt"></i>
                    <span>Project Management</span></a>
            </li>

            <!-- Nav Item - Project overview -->
            <li class="nav-item">
                <a class="nav-link" href="project-overview.html">
                    <i class="fas fa-fw fa-tachometer-alt"></i>
                    <span>Project Overview</span></a>
            </li>            

            <!-- Divider -->
            <hr class="sidebar-divider">

            <!-- Heading -->
            <div class="sidebar-heading">
                Project Infomation
            </div>

            <!-- Nav Item - Tables -->
            <li class="nav-item">
                <a class="nav-link" href="project-info.jsp">
                    <i class="fas fa-fw fa-table"></i>
                    <span>Project <br>&emsp;&ensp; General Infomation</span>
                    </a>
            </li>

            <!-- Divider -->
            <hr class="sidebar-divider d-none d-md-block">

            <!-- Sidebar Toggler (Sidebar) -->
            <div class="text-center d-none d-md-inline">
                <button class="rounded-circle border-0" id="sidebarToggle"></button>
            </div>

        </ul>
        <!-- End of Sidebar -->

        <!-- Content Wrapper -->
        <div id="content-wrapper" class="d-flex flex-column">

            <!-- Main Content -->
            <div id="content">

                <!-- Topbar -->
                <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

                    <!-- Sidebar Toggle (Topbar) -->
                    <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
                        <i class="fa fa-bars"></i>
                    </button>

                    <!-- Topbar Search -->
                    <form
                        class="d-none d-sm-inline-block form-inline mr-auto ml-md-3 my-2 my-md-0 mw-100 navbar-search">
                        <div class="input-group">
                            <input type="text" class="form-control bg-light border-0 small" placeholder="Search for..."
                                aria-label="Search" aria-describedby="basic-addon2">
                            <div class="input-group-append">
                                <button class="btn btn-primary" type="button">
                                    <i class="fas fa-search fa-sm"></i>
                                </button>
                            </div>
                        </div>
                    </form>

                    <!-- Topbar Navbar -->
                    <ul class="navbar-nav ml-auto">

                        <!-- Nav Item - Search Dropdown (Visible Only XS) -->
                        <li class="nav-item dropdown no-arrow d-sm-none">
                            <a class="nav-link dropdown-toggle" href="#" id="searchDropdown" role="button"
                                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <i class="fas fa-search fa-fw"></i>
                            </a>
                            <!-- Dropdown - Messages -->
                            <div class="dropdown-menu dropdown-menu-right p-3 shadow animated--grow-in"
                                aria-labelledby="searchDropdown">
                                <form class="form-inline mr-auto w-100 navbar-search">
                                    <div class="input-group">
                                        <input type="text" class="form-control bg-light border-0 small"
                                            placeholder="Search for..." aria-label="Search"
                                            aria-describedby="basic-addon2">
                                        <div class="input-group-append">
                                            <button class="btn btn-primary" type="button">
                                                <i class="fas fa-search fa-sm"></i>
                                            </button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </li>

                        <div class="topbar-divider d-none d-sm-block"></div>

                        <!-- Nav Item - User Information -->
                        <li class="nav-item dropdown no-arrow">
                            <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button"
                                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <span class="mr-2 d-none d-lg-inline text-gray-600 small">KETI</span>
                                <img class="img-profile rounded-circle"
                                    src="img/undraw_profile.svg">
                            </a>
                            <!-- Dropdown - User Information -->
                            <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
                                aria-labelledby="userDropdown">
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
                                    Logout
                                </a>
                            </div>
                        </li>

                    </ul>

                </nav>
                <!-- End of Topbar -->

                <!-- Begin Page Content -->
                <div class="container-fluid">
                    <!-- Page Heading -->
                    <div class="mb-4 d-sm-inline-block">
                        <h1 class="h3 mb-0 text-gray-800">Project Management</h1>
                        
                        <!-- Buttons: Create, Copy, Save, Delete -->
                        <!-- <a href="project-create.html" class="btn btn-warning btn-circle btn-sm"> -->
                    </div>
                    <div class="mb-4 d-sm-inline-block" style="float:right;">
                        <a class="btn btn-warning btn-circle btn-sm mr-2" data-toggle="modal" href="#createProjectModal">
                            <i class="fas fa-solid fa-plus"></i>
                        </a>
                    
                        <a class="btn btn-success btn-circle btn-sm mr-2" data-toggle="modal" href="#copyProjectModal">
                            <i class="fas fa-solid fa-copy"></i>
                        </a>

                        <a class="btn btn-danger btn-circle btn-sm mr-2" data-toggle="modal" href="#deleteProjectModal">
                            <i class="fas fa-trash"></i>
                        </a>

                        <a class="btn btn-info btn-circle btn-sm mr-2" data-toggle="modal" href="#saveProjectModal">
                            <i class="fas fa-check"></i>
                        </a>                        

                        <a class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm"><i
                                class="fas fa-download fa-sm text-white-50"></i> Generate Report</a>
                    </div>
                    <main class="main">
                        <!--<form action="" method="GET" id="survey-form" class="survey" novalidate>-->

                            <!-- <div class="process-wrap active-step1"> -->
                                <!-- <div class="process-main"> -->
                            
                            <!-- Content Row -->
                            <div class="row mt-1"> 

                                <!-- LCIA Select -->
                                <div class="card my-card1 shadow col-lg-12 mb-2">
                                    <div class="card-body">

                                        <h6 class="mt-3 font-weight-bold text-primary">Project Tables</h6>
                                        <div class="table-responsive col-sm-12">
                                            <table class="table table-bordered" id="project_list" width="100%" cellspacing="0">
                                                <thead>
                                                    <tr>
                                                        <th>Id</th>                                                  
                                                        <th>Project Name</th>
                                                        <th>Product Name / productAmount</th>
                                                        <th>Create Date</th>
                                                        <th>LCIA Method</th>
                                                        <th>Calculation Results</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <c:forEach items="${voList}" var="vo">
                                                        <tr>
                                                            <td>${vo.id }</td>
                                                            <td>${vo.projectName }</td>
                                                            <td>${vo.productName } / ${vo.productAmount}</td>
                                                            <td>${vo.createDate }</td>
                                                            <td>${vo.lciaMethod }</td>
                                                            <td>${vo.impactCategory }: ${vo.result} ${vo.resultUnit}</td>
                                                        </tr>    
                                                    </c:forEach>
                                                    
                                                </tbody>
                                            </table>
                                        </div>
 
                                    </div>
                                </div> 
                            </div>    
				                    
                            <!-- Content Row -->
                            <div class="row">         
                                <!-- Content Column -->
                                <div class="col-lg-6 mb-4">

                                </div>              	
                            </div>         	                    				                     

                        <!-- </form> -->
                    </main>
                </div>
                <!-- /.container-fluid -->

            </div>
            <!-- End of Main Content -->

            <!-- Footer -->
            <footer class="sticky-footer bg-white">
                <div class="container my-auto">
                    <div class="copyright text-center my-auto">
                        <!--<span>Copyright &copy; Your Website 2020</span>-->
                    </div>
                </div>
            </footer>
            <!-- End of Footer -->

        </div>
        <!-- End of Content Wrapper -->

    </div>
    <!-- End of Page Wrapper -->

    <!-- Scroll to Top Button-->
    <a class="scroll-to-top rounded" href="#page-top">
        <i class="fas fa-angle-up"></i>
    </a>

    <!-- Logout Modal-->
    <div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
        aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
                    <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">��</span>
                    </button>
                </div>
                <div class="modal-body">Select "Logout" below if you are ready to end your current session.</div>
                <div class="modal-footer">
                    <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
                    <a class="btn btn-primary" href="login.html">Logout</a>
                </div>
            </div>
        </div>
    </div>

    <!-- Create Project Modal-->
    <div class="modal fade" id="createProjectModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
        aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <div class="modal-title" id="exampleModalLabel">
                        <h5>새 프로젝트 생성</h5>
                        <p>(해당되는 공정 중복선택)</p>
                    </div>
                    <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">×</span>
                    </button>
                </div>
                <div class="modal-body row">
                    <div class="custom-checkbox ml-3">
                        <!-- 제품 구분 buttons: 생산재, 서비스, 비내구재, 에너지 비사용 내구재, 에너지 사용 내구재 -->
                        <!--<div class="my-6"></div>-->
                        <input type="checkbox" name="project_create" checked>
                        <label>물질 및 부품제조</label>
                    </div>
                    <div class="custom-checkbox ml-3">
                        <input type="checkbox" name="project_create" checked>
                        <label>가공공정</label>
                    </div>
                    <div class="custom-checkbox ml-3">
                        <input type="checkbox" name="project_create" checked>
                        <label>수송</label>
                    </div>
                    <div class="custom-checkbox ml-3">
                        <input type="checkbox" name="project_create">
                        <label>사용</label>
                    </div>
                    <div class="custom-checkbox ml-3">
                        <input type="checkbox" name="project_create" checked>
                        <label>재활용 및 폐기</label>
                    </div>           
                    <!--         
                        <li><i class="fa-regular fa-mountain-city"></i></li>
                        <li>물질 및 부품제조</li>
                        <li>가공공정</li>
                        <li>수송</li>
                        <li>사용</li>
                        <li>재활용 및 폐기</li>
                    -->
                        <!--
                        <a href="project-info.html"><i class="fa-duotone fa-industry-windows"></i></a>
                        <a href="project-info.html"><i class="fa-duotone fa-truck"></i></a>
                        <a href="project-info.html"><i class="fa-duotone fa-users"></i></a>
                        <a href="project-info.html"><i class="fa-solid fa-recycle"></i></a>
                        -->

                </div>
                <div class="modal-footer">
                    <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
                    <a class="btn btn-primary" href="project-info.jsp" onclick="getCheckboxValue()">Create</a>
                </div>
            </div>
        </div>
    </div>

    <!-- Copy Project Modal-->
    <div class="modal fade" id="copyProjectModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
        aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">프로젝트를 복제하시겠습니까?</h5>
                    <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">×</span>
                    </button>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
                    <a class="btn btn-primary" href="">Yes</a>
                </div>
            </div>
        </div>
    </div>

    <!-- Delete Project Modal-->
    <div class="modal fade" id="deleteProjectModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
        aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">프로젝트를 삭제하시겠습니까?</h5>
                    <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">×</span>
                    </button>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
                    <a class="btn btn-primary" href="">Yes</a>
                </div>
            </div>
        </div>
    </div>
    
    <!-- Save Project Modal-->
    <div class="modal fade" id="saveProjectModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
        aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">프로젝트를 저장하시겠습니까?</h5>
                    <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">×</span>
                    </button>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
                    <a class="btn btn-primary" href="">Yes</a>
                </div>
            </div>
        </div>
    </div>    

    <!-- Bootstrap core JavaScript-->
    <script src="vendor/jquery/jquery.min.js"></script>
    <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Core plugin JavaScript-->
    <script src="vendor/jquery-easing/jquery.easing.min.js"></script>

    <!-- Custom scripts for all pages-->
    <script src="js/sb-admin-2.js"></script>

    <!-- Page level plugins -->
    <script src="vendor/datatables/jquery.dataTables.js"></script>
    <script src="vendor/datatables/dataTables.bootstrap4.js"></script>

    <!-- Page level custom scripts -->
    <script src="js/demo/datatables-demo.js"></script>

</body>

</html>