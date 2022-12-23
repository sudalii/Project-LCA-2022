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
    <link href="css/sb-admin-2.css?v=1" rel="stylesheet">
    <link rel="stylesheet" href="css/leaflet.css?v=1" />
    <style>
        html,
        body,
        #map {
            width: 100%;
            height: 100%;
            margin: 0px;
            padding: 0px;
            height: 100%;
        }
    </style>

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
            <li class="nav-item">
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
            <li class="nav-item active">
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

                    <main class="main">
                        <form action="calc-insert" method="post" id="survey-form" class="survey" novalidate>

                            <!-- <div class="process-wrap active-step1"> -->
                                <!-- <div class="process-main"> -->
                            <div class="process-wrap active-step4" tabindex="0" role="progressbar" aria-valuemin="1" aria-valuemax="6" aria-valuenow="1" aria-valuetext="Question 1 of 5: How long have you been using our product?">
                                <div class="row my-row">
                                    <div class="col-2 my-col">
                                        <div class="process-step-cont">
                                            <div class="process-step step-1"></div>
                                            <span class="process-label">프로젝트<br>정보입력</span>
                                        </div>
                                    </div>
                                    <div class="col-2 my-col">
                                        <div class="process-step-cont">
                                            <div class="process-step step-2"></div>
                                            <span class="process-label">물질 및<br>부품제조</span>
                                        </div>
                                    </div>
                                    <div class="col-2 my-col">
                                        <div class="process-step-cont">
                                            <div class="process-step step-3"></div>
                                            <span class="process-label">가공공정</span>
                                        </div>
                                    </div>
                                    <div class="col-2 my-col">
                                        <div class="process-step-cont">
                                            <div class="process-step step-4"></div>
                                            <span class="process-label">수송</span>
                                        </div>
                                    </div>
                                    <div class="col-2 my-col">
                                        <div class="process-step-cont">
                                            <div class="process-step step-5"></div>
                                            <span class="process-label">재활용 및<br>폐기</span>
                                        </div>
                                    </div>
                                    <div class="col-2 my-col">
                                        <div class="process-step-cont">
                                            <div class="process-step step-6"></div>
                                            <span class="process-label">GWP<br>결과산출</span>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="mt-2">
                                <button class="button" type="submit" name="calc" aria-label="Calc" aria-hidden="false">
                                    LCA Calculation</button>    
                                <button class="button" type="button" name="prev" aria-label="Next" aria-hidden="true"
                                onclick="location.href='disuse-entry.jsp';">Next</button>
                                <button class="button" type="button" name="prev" aria-label="Previous" aria-hidden="true"
                                onclick="location.href='manufacture-process.jsp';">Prev</button>
                            </div>

                            <div class="mt-1">
                                <!-- 데이터 수집기간/장소 -->
                                <div class="card my-card1 shadow mb-2">
                                    <div class="card-header my-card-header py-3">
                                        <h6 class="m-0 font-weight-bold text-primary">데이터<br>수집기간/장소</h6>
                                    </div>
                                    <div class="card-body my-inline">
                                        <input type="date" class="form-control form-control-user mr-2 col-2">
                                        <h4 class="mr-2">~</h4>
                                        <input type="date" class="form-control form-control-user col-2">
                                        <input type="text" class="form-control form-control-user ml-4 col-3" required placeholder="데이터 수집장소 입력"/>
                                    </div>
                                </div> 
                                <!-- Map 보기 -->
                                <div class="card my-card1 shadow mb-2"  style="height:27em">
                                    <div class="card-header my-card-header py-3">
                                        <h6 class="m-0 font-weight-bold text-primary">지도로 확인</h6>
                                    </div>
                                    <div class="card-body">
                                        <div id="map"></div>
                                    </div>
                                </div> 
                                <div class="card my-card1 shadow mb-2">
                                    <div class="card-header my-card-header py-3">
                                        <h6 class="m-0 font-weight-bold text-primary">Input<br>/Output</h6>
                                    </div>
                                    <div class="card-body">
                                        <a class="btn btn-warning btn-circle btn-sm ml-2" id="table-handling" onclick="addRow()">
                                            <i class="fas fa-solid fa-plus"></i>
                                        </a>
                                        <a class="btn btn-danger btn-circle btn-sm" id="table-handling" onclick="">
                                            <i class="fas fa-trash"></i>
                                        </a>
                                        <!-- Input Parameter Table -->
                                        <div class="table-responsive col-12"><!-- style="display:inline-block;">-->
                                            <table class="table table-bordered" id="normal" width="100%" cellspacing="0">
                                                <thead>
                                                    <tr>
                                                        <th class="text-primary" width="30%">운송 수단</th>        
                                                        <th>연료 종류</th>                                          
                                                        <th colspan="2">거리</th>
                                                        <th colspan="2">차량 무게</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <tr>
                                                        <td>
                                                            <div class="dropdown">
                                                                <select name="iUnits" id="dropdown" class="form-control">
                                                                    <option value="truck">트럭</option>
                                                                    <option value="railroad">기차</option>
                                                                    <option value="refrigerator_truck">냉장차량</option>
                                                                </select>
                                                            </div>
                                                        </td>
                                                        <td>
                                                            <div class="dropdown">
                                                                <select name="iUnits" id="dropdown" class="form-control">
                                                                    <option value="truck">디젤</option>
                                                                    <option value="railroad">휘발유</option>
                                                                    <option value="refrigerator_truck">항공유</option>
                                                                    <option value="refrigerator_truck">전기</option>                                                                  
                                                                </select>
                                                            </div>
                                                        </td>
                                                        <td><input type="text" name="inputs" class="form-control form-control-user" required placeholder="1.0"></td>
                                                        <td>
                                                            <div class="dropdown">
                                                                <select name="iUnits" id="dropdown" class="form-control">
                                                                    <option value="km">km</option>
                                                                    <option value="kg">kg</option>
                                                                    <option value="m">m</option>
                                                                    <option value="ea">ea</option>
                                                                    <option value="kBq">kBq</option>
                                                                    <option value="unit">unit</option>
                                                                </select>
                                                            </div>
                                                        </td>
                                                        <td><input type="text" name="inputs" class="form-control form-control-user" required placeholder="1.0"></td>
                                                        <td>
                                                            <div class="dropdown">
                                                                <select name="iUnits" id="dropdown" class="form-control">
                                                                    <option value="kWh">t</option>
                                                                    <option value="kg">kg</option>
                                                                    <option value="ea">ea</option>
                                                                    <option value="kBq">kBq</option>
                                                                    <option value="unit">unit</option>
                                                                </select>
                                                            </div>
                                                        </td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                        </div>
                                        <!-- end table area -->
                                    </div>
                                </div> 
                                <!-- LCIA Select -->
                                <div class="card my-card1 shadow mb-2">
                                    <!--<div class="card-header my-card-header py-3">
                                        <h6 class="m-0 font-weight-bold text-primary">LCIA 방법론 선택</h6>
                                    </div>-->
                                    <div class="card-body">
                                        <h6 class="m-0 font-weight-bold text-primary">환경영향범주 선택</h6>
                                        <div class="dropdown" class="form-control form-control-user">
                                            <select name="impactCategory" id="dropdown" class="form-control">
                                                <option value="GWP">지구온난화(GWP)</option>
                                            </select>
                                        </div>
                                    </div>

                                    <div class="card-body">
                                        <h6 class="m-0 font-weight-bold text-primary">LCIA 방법론 선택</h6>
                                        <div class="dropdown" class="form-control form-control-user">
                                            <select name="lciaMethod" id="dropdown" class="form-control">
                                                <option value="CML-IA baseline">CML-IA baseline</option>
                                                <option value="IPCC 2013 GWP 100a">IPCC 2013 GWP 100a</option>
                                            </select>
                                        </div>
                                    </div>
                                </div>   
                  
                                <!-- End Card -->
                        </form>
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
                        <span aria-hidden="true">×</span>
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

    <!-- Bootstrap core JavaScript-->
    <script src="vendor/jquery/jquery.min.js"></script>
    <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Core plugin JavaScript-->
    <script src="vendor/jquery-easing/jquery.easing.min.js"></script>

    <!-- Custom scripts for all pages-->
    <script src="js/sb-admin-2.js?v=1"></script>

    <!-- Page level plugins -->
    <script src="vendor/datatables/jquery.dataTables.js"></script>
    <script src="vendor/datatables/dataTables.bootstrap4.js"></script>

    <!-- Page level custom scripts -->
    <script src="js/demo/datatables-demo.js"></script>
    <script src="js/table-handling.js"></script>

    <!-- Map scripts -->
    <script src="js/map/leaflet.js"></script>
    <script src="js/map/heatmap.min.js"></script>
    <script src="js/map/leaflet-heatmap.js"></script>
    <script>

        var baseLayer = L.tileLayer(
            'http://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
            attribution: '© OpenStreetMap contributors',
            maxZoom: 18
        });

        // heatmap config see https://www.patrick-wied.at/static/heatmapjs/example-heatmap-leaflet.html
        var cfg = {
            // radius should be small ONLY if scaleRadius is true (or small radius is intended)
            // if scaleRadius is false it will be the constant radius used in pixels
            "radius": 5,
            "maxOpacity": 0.8,
            "minOpacity": 0.2,
            // scales the radius based on map zoom
            "scaleRadius": true,
            // if set to false the heatmap uses the global maximum for colorization
            // if activated: uses the data maximum within the current map boundaries
            //   (there will always be a red spot with useLocalExtremas true)
            // "useLocalExtrema": true,
            // which field name in your data represents the latitude - default "lat"
            latField: 'latitude',
            // which field name in your data represents the longitude - default "lng"
            lngField: 'longitude',
            // which field name in your data represents the data value - default "value"
            valueField: 'weight',
            gradient: {
                // enter n keys between 0 and 1 here
                // for gradient color customization
                '.01': 'blue',
                '.95': 'red'
            }
        };
        var heatmapLayer = new HeatmapOverlay(cfg);
        var map = new L.Map('map', {
            center: new L.LatLng(36, 128),
            zoom: 7,
            layers: [baseLayer, heatmapLayer]
        });

        function setData(data) {
            if (!data) {
                return;
            }
            var max = 0.0;
            for (var i = 0; i < data.length; i++) {
                if (data[i].weight > max) {
                    max = data[i].weight;
                }
            }
            heatmapLayer.setData({
                max: max,
                data: data
            });
        }

    </script>

</body>

</html>