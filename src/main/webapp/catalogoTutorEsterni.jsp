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
				//for java dei tutor

				<div id="body1" class="card-block">
					<h4 class="card-title">Lista Tutor Aziendali</h4>
					<div class="table-responsive">
						<table class="table" id="table1">
							<thead>
								<tr>
									<th>Cognome</th>
									<th>Nome</th>
									<th>Codice Fiscale</th>
									<th>Email</th>
									<th>Indirizzo</th>
									<th>Telefono</th>
									<th>Gestione Tutor</th>
								</tr>
							</thead>
							<tbody>
								<%
									//for
								%>
								<tr>
									<td><%=%></td>
									<td><%=%></td>
									<td><span class="text-muted"> <%=%></span></td>
									<td><%=%></td>
									<td>
										<div class="label label-table label-info"><%=%></div>
									</td>
									<td><%=%></td>
									<td class="text-nowrap">
										<form id="formTutor" method="get" action=" ">
											<a href="gestioneTutorEsterno.jsp" data-toggle="tooltip"
												data-original-title="Edit"
												onclick="document.getElementById('formTutor').setAttribute('action', 'modificaTutorEsterno');">
												<i class="fa fa-pencil text-inverse m-r-10"></i>
											</a><a href="#" data-toggle="tooltip" data-original-title="Close"
												data-toggle="modal" data-target=".bs-example-modal-lg">
												<i class="fa fa-close text-danger"></i>
											</a>


											<div class="modal fade bs-example-modal-lg" tabindex="-1"
												role="dialog" aria-labelledby="myLargeModalLabel"
												style="display: none;" aria-hidden="true">
												<div class="modal-dialog modal-lg">
													<div class="modal-content">
														<div class="modal-header">
															<h4 class="modal-title" id="myLargeModalLabel">Rimozione
																Tutor Esterno</h4>
														</div>
														<div class="modal-body">
															<p>Sei sicuro di volere rimuovere questo tutor
																esterno?</p>
															<div class="row">
																<div class="col-md-3 col-xs-6">
																	<strong>Nome</strong> <br>
																	<p class="text-muted"><%=%></p>
																</div>
																<div class="col-md-3 col-xs-6 b-r">
																	<strong>Cognome</strong> <br>
																	<p class="text-muted"><%=%></p>
																</div>
																<div class="col-md-3 col-xs-6 b-r">
																	<strong>Codice Fiscale</strong> <br>
																	<p class="text-muted"><%=%></p>
																</div>
																<div class="col-md-3 col-xs-6 b-r">
																	<strong>Email</strong> <br>
																	<p class="text-muted"><%=%></p>
																</div>

															</div>

															<button style="float: left;" type="submit"
																class="btn btn-info waves-effect text-right"
																onclick="document.getElementById('formTutor').setAttribute('action', 'rimuoviTutorEsterno');"
																data-dismiss="modal" id="buttonmodal">Rimuovi</button>

															<button style="float: right;" type="button"
																class="btn btn-danger waves-effect text-left"
																data-dismiss="modal" id="buttonmodal">Close</button>
														</div>
													</div>
													<!-- /.modal-content -->
												</div>
												<!-- /.modal-dialog -->
											</div>
											<!-- /.modal -->
										</form>
									</td>
								</tr>
								<%
									//endfor
								%>
							</tbody>
						</table>
						<table class="table" id="table2">
							<thead>
								<tr>
									<th>Cognome</th>
									<th>Nome</th>
									<th>Codice Fiscale</th>
									<th>Email</th>
									<th>Indirizzo</th>
									<th>Telefono</th>
									<th>Gestione Tutor</th>
								</tr>
							</thead>
							<tbody>
								<%
									//for
								%>
								<tr>
									<td><%=%></td>
									<td><%=%></td>
									<td><%=%></td>
									<td><%=%></td>
									<td><%=%></td>
									<td><%=%></td>
									<td class="text-nowrap">

										<form id="formTutor" method="get" action=" ">
											<a href="gestioneTutorEsterno.jsp" data-toggle="tooltip"
												data-original-title="Edit"
												onclick="document.getElementById('formTutor').setAttribute('action', 'modificaTutorEsterno');"><i
												class="fa fa-pencil text-inverse m-r-10"></i> </a><a href="#"
												data-toggle="tooltip" data-original-title="Close"
												data-toggle="modal" data-target=".bs-example-modal-lg"><i
												class="fa fa-close text-danger"></i> </a>


											<div class="modal fade bs-example-modal-lg" tabindex="-1"
												role="dialog" aria-labelledby="myLargeModalLabel"
												style="display: none;" aria-hidden="true">
												<div class="modal-dialog modal-lg">
													<div class="modal-content">
														<div class="modal-header">
															<h4 class="modal-title" id="myLargeModalLabel">Rimozione
																Tutor Esterno</h4>
														</div>
														<div class="modal-body">
															<p>Sei sicuro di volere rimuovere questo tutor
																esterno?</p>
															<div class="row">
																<div class="col-md-3 col-xs-6">
																	<strong>Nome</strong> <br>
																	<p class="text-muted"><%=%></p>
																</div>
																<div class="col-md-3 col-xs-6 b-r">
																	<strong>Cognome</strong> <br>
																	<p class="text-muted"><%=%></p>
																</div>
																<div class="col-md-3 col-xs-6 b-r">
																	<strong>Codice Fiscale</strong> <br>
																	<p class="text-muted"><%=%></p>
																</div>
																<div class="col-md-3 col-xs-6 b-r">
																	<strong>Email</strong> <br>
																	<p class="text-muted"><%=%></p>
																</div>

															</div>

															<button style="float: left;" type="submit"
																class="btn btn-info waves-effect text-right"
																onclick="document.getElementById('formTutor').setAttribute('action', 'rimuoviTutorEsterno');"
																data-dismiss="modal" id="buttonmodal">Rimuovi</button>

															<button style="float: right;" type="button"
																class="btn btn-danger waves-effect text-left"
																data-dismiss="modal" id="buttonmodal">Close</button>
														</div>
													</div>
													<!-- /.modal-content -->
												</div>
												<!-- /.modal-dialog -->
											</div>
											<!-- /.modal -->
										</form>
									</td>
								</tr>
								<%
									//endfor
								%>
							</tbody>
						</table>
					</div>
				</div>




			</div>
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
<style type="text/css">
#buttonmodal:hover, #buttonmodal:focus {
	background-color: orange;
	border-color: red;
}
#table1 {
	visibility: visible;
}

#table2 {
	visibility: hidden;
}

@media screen and (max-width: 700px) {
	#table1 {
		visibility: collapse;
	}
	#table2 {
		visibility: visible;
		font-size: 85%;
	}
}
</style>
</html>


