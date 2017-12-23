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
<link rel="icon" type="image/png" sizes="16x16"
	href="assets/images/favicon.png">
<title>Libra</title>
<!-- Bootstrap Core CSS -->
<link href="assets/plugins/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<!-- chartist CSS -->
<link href="assets/plugins/chartist-js/dist/chartist.min.css"
	rel="stylesheet">
<link href="assets/plugins/chartist-js/dist/chartist-init.css"
	rel="stylesheet">
<link
	href="assets/plugins/chartist-plugin-tooltip-master/dist/chartist-plugin-tooltip.css"
	rel="stylesheet">
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
<style type="text/css">
#label1 {
	visibility: hidden;
}

#label2 {
	visibility: visible;
}

@media screen and (max-width: 600px) {
	#label1 {
		visibility: visible;
	}
	#label2 {
		visibility: hidden;
	}
}
</style>
</head>

<body class="fix-header fix-sidebar card-no-border">
	<!-- ============================================================== -->
	<!-- Preloader - style you can find in spinners.css -->
	<!-- ============================================================== -->
	<div class="preloader">
		<svg class="circular" viewBox="25 25 50 50">
            <circle class="path" cx="50" cy="50" r="20" fill="none"
				stroke-width="2" stroke-miterlimit="10" /> </svg>
	</div>
	<!-- ============================================================== -->
	<!-- Main wrapper - style you can find in pages.scss -->
	<!-- ============================================================== -->
	<div id="main-wrapper">
		<!-- ============================================================== -->
		<!-- Topbar header - style you can find in pages.scss -->
		<!-- ============================================================== -->
		<%@ include file="header.jsp"%>
		<!-- ============================================================== -->
		<!-- End Topbar header -->
		<!-- ============================================================== -->
		<!-- ============================================================== -->
		<!-- Left Sidebar - style you can find in sidebar.scss  -->
		<!-- ============================================================== -->
		<%@ include file="menu.jsp"%>
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
			<div class="container-fluid">
				
<form id="formRecupera">
					<div class="card-block" align="center"
						style="margin-top: 8%; margin-right: 11%; margin-left: 11%; background-color: white;">
						<h4 class="card-title" style="margin: 2%;">Recupera Password</h4>
						<p class="card-text">
							Se hai dimenticato la password del tuo Account, <br>inserisci
							la tua <b>email istituzionale</b> qui sotto.<br>Ti invieremo
							la <b>nuova password</b> all'indirizzo fornito
						</p>

						<div class="group row" style="margin-top: 2%;">
							<label for="inputEmail" id="label2"
								class="col-sm-4 text-right control-label col-form-label">Email</label>
							<label for="inputEmail" id="label1" style="margin-bottom: -50%;"
								class="col-sm-2 text-left control-label col-form-label">Email</label>
							<div class="col-sm-4">
								<input type="email" name="email" required="required" id="labelEmail"
									class="form-control" placeholder="Email" maxlength="30" /> 
							</div>
							<div style="margin-left: 30%; margin-top:3%;" align="center">
							
								<button id="buttonRichiedi" type="submit" class="btn btn-info" >Richiedi
									Password</button>
								
							</div>
						</div>
					</div>
				</form>

				<div class="modal fade" id="modalResult" role="dialog">
					<div class="modal-dialog">

						<div class="modal-content">
							<div class="modal-header">
								<h4 class="modal-title">Recupero Password</h4>
							</div>
							<div class="modal-body">
								<p id="modalMessage"></p>
							</div>
							<div class="modal-footer">
								<button id="button1" type="button" class="btn btn-primary">
									<a class="btn btn-primary" href="dashboardAzienda.jsp"
										style="text-decoration: none; color: white;"> Dashboard </a>
								</button>
							</div>
						</div>

					</div>
				</div>

			</div>
			<!-- ============================================================== -->
			<!-- End Container fluid  -->
			<!-- ============================================================== -->
			<!-- ============================================================== -->
			<!-- footer -->
			<!-- ============================================================== -->
			<%@ include file="footer.jsp"%>
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
	<script>
		$(document).ready(function() {
							$("#formRecupera").submit(
											function(e) {
												e.preventDefault();
												$.post('recupero', {
													email : $("#labelEmail").val()
																},
																function(data) {
																	$("#buttonRichiedi").prop("disabled",true);
																	

																	if (data == "ok") {
																		$(
																				"#modalMessage")
																				.text(
																						"L'operazione &egrave; avvenuta correttamente");

																	} else {
																		$(
																				"#modalMessage")
																				.text(
																						data);
																	}
																	$(
																			"#modalResult")
																			.modal(
																					'show');

																});
											});
						})
	</script>
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
	<script
		src="assets/plugins/chartist-plugin-tooltip-master/dist/chartist-plugin-tooltip.min.js"></script>
	<!-- Chart JS -->
	<script src="assets/plugins/echarts/echarts-all.js"></script>
	<script src="js/dashboard5.js"></script>
	<!-- ============================================================== -->
	<!-- Style switcher -->
	<!-- ============================================================== -->
	<script src="assets/plugins/styleswitcher/jQuery.style.switcher.js"></script>
</body>

</html>


