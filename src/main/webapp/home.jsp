<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
<%@page import="it.unisa.libra.util.JspPagesIndex"%>

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
<link rel="icon" type="image/png" sizes="16x16"
	href="assets/images/favicon.png">
<title>Libra</title>
<!-- Bootstrap Core CSS -->
<link href="assets/plugins/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
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

<body>
	<!-- ============================================================== -->
	<!-- Preloader - style you can find in spinners.css -->
	<!-- ============================================================== -->
	<!--  div class="preloader">
		<svg class="circular" viewBox="25 25 50 50">
            <circle class="path" cx="50" cy="50" r="20" fill="none"
				stroke-width="2" stroke-miterlimit="10" /> </svg>
	</div>
	-->
	<!-- ============================================================== -->
	<!-- Main wrapper - style you can find in pages.scss -->
	<!-- ============================================================== -->
	<section id="wrapper">
		<div class="login-register"
			style="background-image: url(assets/images/background/sfondo.jpg);background-size:cover;position:fixed;z-index:-1;">
			<div class="login-box card">
				<div class="card-block">
					<form class="form-horizontal form-material" id="loginform"
						method="post">
						<h3 class="box-title m-b-20">Login</h3>
						<div class="form-group ">
							<div class="col-xs-12">
								<input id="email" class="form-control" type="email" required
									placeholder="Email" maxlength="50">
							</div>
						</div>
						<div class="form-group">
							<div class="col-xs-12">
								<input id="password" class="form-control" type="password"
									required placeholder="Password" pattern="[A-Za-z0-9]{8,20}$">
							</div>
						</div>
						<div class="form-group">
							<div class="col-md-12">

								<div id="error-message" class="alert alert-danger"
									style="display: none">Login errato, riprova</div>

								<a href="<%=JspPagesIndex.RECUPERA_PASSWORD%>" id="to-recover" class="text-dark pull-right"><i class="fa fa-lock m-r-5"></i>Password dimenticata?</a>

							</div>
						</div>
						
						<div id="error-message" class="col-md-12 alert alert-danger has-danger"
									style="display:none">Credenziali non corrette, riprova</div>
						
						<div class="form-group text-center m-t-20">
							<div class="col-xs-12">
								<button
									class="btn btn-info btn-lg btn-block text-uppercase waves-effect waves-light"
									type="submit">Login</button>
							</div>
						</div>
						<div class="row">
							<div class="col-xs-12 col-sm-12 col-md-12 m-t-10 text-center">

							</div>
					</div><div class="form-group m-b-0">
                        <div class="col-sm-12 text-center">
                            <p>Don't have an account? <a href="registrazione.jsp" class="text-info m-l-5"><b>Sign Up</b></a></p>
                        </div>
                    </div>

						</div>
						<div class="form-group m-b-0">
							<div class="col-sm-12 text-center">
								<p>
									Non hai ancora un account? <a href="register.html"
										class="text-info m-l-5"><b>Registrati</b></a>
								</p>
							</div>
						</div>

					</form>

				</div>
			</div>
		</div>

	</section>
	<!-- ============================================================== -->
	<!-- End Wrapper -->
	<!-- ============================================================== -->
	<!-- ============================================================== -->
	<!-- All Jquery -->
	<!-- ============================================================== -->

	<script src="assets/plugins/jquery/jquery.min.js"></script>
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
	<!-- Style switcher -->
	<!-- ============================================================== -->
	<script src="assets/plugins/styleswitcher/jQuery.style.switcher.js"></script>

	<script src="js/login.js"></script>

</body>

</html>