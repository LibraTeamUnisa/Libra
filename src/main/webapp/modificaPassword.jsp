<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>


<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- Tell the browser to be responsive to screen width -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <!-- Favicon icon -->
    <link rel="icon" type="image/png" sizes="16x16" href="assets/images/favicon.png">
<title>Libra</title>
    <!-- Bootstrap Core CSS -->
    <link href="assets/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <!-- chartist CSS -->
    <link href="assets/plugins/chartist-js/dist/chartist.min.css" rel="stylesheet">
    <link href="assets/plugins/chartist-js/dist/chartist-init.css" rel="stylesheet">
    <link href="assets/plugins/chartist-plugin-tooltip-master/dist/chartist-plugin-tooltip.css" rel="stylesheet">
    <link href="assets/plugins/css-chart/css-chart.css" rel="stylesheet">
    <!-- Custom CSS -->
    <link href="css/style.css" rel="stylesheet">
    <!-- You can change the theme colors from here -->
    <link href="css/colors/red.css" id="theme" rel="stylesheet">
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
<![endif]-->
</head>

<body class="fix-header fix-sidebar card-no-border">
    <!-- ============================================================== -->
    <!-- Preloader - style you can find in spinners.css -->
    <!-- ============================================================== -->
    <div class="preloader">
        <svg class="circular" viewBox="25 25 50 50">
            <circle class="path" cx="50" cy="50" r="20" fill="none" stroke-width="2" stroke-miterlimit="10" /> </svg>
    </div>
    <!-- ============================================================== -->
    <!-- Main wrapper - style you can find in pages.scss -->
    <!-- ============================================================== -->
    <div id="main-wrapper">
        <!-- ============================================================== -->
        <!-- Topbar header - style you can find in pages.scss -->
        <!-- ============================================================== -->
       	<%@ include file="header.jsp" %>
        <!-- ============================================================== -->
        <!-- End Topbar header -->
        <!-- ============================================================== -->
        <!-- ============================================================== -->
        <!-- Left Sidebar - style you can find in sidebar.scss  -->
        <!-- ============================================================== -->
        <%@ include file="menu.jsp" %>
        <!-- ============================================================== -->
        <!-- End Left Sidebar - style you can find in sidebar.scss  -->
        <!-- ============================================================== -->
        <!-- ============================================================== -->
        <!-- Page wrapper  -->
        <!-- ============================================================== -->
        <div class="page-wrapper">
            <!-- ============================================================== -->
            <!-- Container fluid  -->
            <!-- ============================================================== -->
            <div class="container-fluid" 
            style="padding-right:10px; padding-left: 10px;">
             
<section id="wrapper">
		<div class="ModificaPassword">
			<div class="login-box card">
				<div class="card-block">
					<form class="form-horizontal form-material" id="passwordform" method="post">
						<h3 class="box-title m-b-20">ModificaPassword</h3>
						
						<div class="form-group " id="primoForm">
							<div class="col-xs-5">
								<p> Vecchia Password: </p>
							</div>
							<div class="col-xs-12">
								<input id="password" class="form-control" type="password" placeholder="Inserisci Password" maxlength="20" required>
							</div>
							 
							<button id="bottone00"
									class="btn btn-info btn-lg btn-block text-uppercase waves-effect waves-light"
									type="submit">Prosegui</button>
						</div>
						
						<div class="alert alert-success"
						 id="success" style="display: none">
						 PASSWORD MODIFICATA CON SUCCESSO, VERRAI REINDIRIZZATO
						  AL TUO PROFILO. </div>
						
						
						<div class="form-group has-danger">
							<div class="col-md-12">
								<div id="error-message" class="alert alert-danger"
									style="display: none">Password errata, riprova</div>
							</div>
						</div>
						
					</form>
					
					<form class="form-horizontal form-material" id="passwordnuovaform" method="post" style="display: none" >
						<div class="form-group">
							<div class="col-xs-5">
								<p> Nuova Password: </p>
							</div>
							<div class="col-xs-12">
								<input id="pwn1" class="form-control" type="password"
								 required placeholder="Password" pattern="[A-Za-z0-9]{8,20}$">
							</div>
						</div>
						
						<div class="form-group">
							<div class="col-xs-5">
								<p> Nuova Password: </p>
							</div>
							<div class="col-xs-12">
								<input id="pwn2" class="form-control" type="password"
									required placeholder="Password" pattern="[A-Za-z0-9]{8,20}$">
							</div>
							<div class="col-md-12">
								<div id="error-message2" class="alert alert-danger"
									style="display: none">Le password non corrispondono!</div>
							</div>
						</div>
						

						
						<div class="form-group text-center m-t-20">
							<div class="col-xs-12">
								<button
									class="btn btn-info btn-lg btn-block text-uppercase waves-effect waves-light"
									type="submit">Salva</button>
							</div>
						</div>
						
						<div class="row">
							<div class="col-xs-12 col-sm-12 col-md-12 m-t-10 text-center">

							</div>
						</div>
					</form>

				</div>
			</div>
		</div>

	</section>

            </div>
            <!-- ============================================================== -->
            <!-- End Container fluid  -->
            <!-- ============================================================== -->
            <!-- ============================================================== -->
            <!-- footer -->
            <!-- ============================================================== -->
            <%@ include file="footer.jsp" %>
            <!-- ============================================================== -->
            <!-- End footer -->
            <!-- ============================================================== -->
        </div>
        <!-- ============================================================== -->
        <!-- End Page wrapper  -->
        <!-- ============================================================== -->
    </div>
    <!-- ============================================================== -->
    <!-- End Wrapper -->
    <!-- ============================================================== -->
    <!-- ============================================================== -->
    <!-- All Jquery -->
    <!-- ============================================================== -->
    <script src="assets/plugins/jquery/jquery.min.js"></script>
    <script src="js/modificaPassword.js"></script>
    <script src="js/modificaPw2.js"></script>
    <!-- Bootstrap tether Core JavaScript -->
    <script src="assets/plugins/bootstrap/js/tether.min.js"></script>
    <script src="assets/plugins/bootstrap/js/bootstrap.min.js"></script>
    <!-- slimscrollbar scrollbar JavaScript -->
    <script src="js/jquery.slimscroll.js"></script>
    <!--Wave Effects -->
    <script src="js/waves.js"></script>
    <!--Menu sidebar -->
    <script src="js/sidebarmenu.js"></script>
    <!--stickey kit -->
    <script src="assets/plugins/sticky-kit-master/dist/sticky-kit.min.js"></script>
    <!--Custom JavaScript -->
    <script src="js/custom.min.js"></script>
    <!-- ============================================================== -->
    <!-- This page plugins -->
    <!-- ============================================================== -->
    <!-- chartist chart -->
    <script src="assets/plugins/chartist-js/dist/chartist.min.js"></script>
    <script src="assets/plugins/chartist-plugin-tooltip-master/dist/chartist-plugin-tooltip.min.js"></script>
    <!-- Chart JS -->
    <script src="assets/plugins/echarts/echarts-all.js"></script>
    <script src="js/dashboard5.js"></script>
    <!-- ============================================================== -->
    <!-- Style switcher -->
    <!-- ============================================================== -->
    <script src="assets/plugins/styleswitcher/jQuery.style.switcher.js"></script>
    

</body>

</html>


