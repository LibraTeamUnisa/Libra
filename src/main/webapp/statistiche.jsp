<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<!-- Tell the browser to be responsive to screen width -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description"
	content="Sezione che mostra statistiche relative agli studenti attivi o inattivi.">
<meta name="author" content="Solimando Giandomenico">
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
<!-- Editable CSS -->
<link type="text/css" rel="stylesheet"
	href="assets/plugins/jsgrid/dist/jsgrid.min.css" />
<link type="text/css" rel="stylesheet"
	href="assets/plugins/jsgrid/dist/jsgrid-theme.min.css" />
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
<style>
#topLast30Days svg {
	height: 345px;
}

@media ( min-width : 768px) {
	.marginmd {
		margin-top: 5px;
	}
}

@media ( max-width : 767px) {
	.marginmd {
		margin-top: 5px;
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

				<!-- ============================================================== -->
				<!-- Bread crumb and right sidebar toggle -->
				<!-- ============================================================== -->
				<div class="row page-titles">
					<div class="col-md-6 col-8 align-self-center">
						<h3 class="text-themecolor m-b-0 m-t-0">Statistiche Studenti</h3>
						<ol class="breadcrumb">
							<%
								if (session != null && session.getAttribute("utenteRuolo") != null) {
									String dashboard = request.getContextPath()
											+ "/dashboard".concat(session.getAttribute("utenteRuolo").toString()).concat(".jsp");
							%>
							<li class="breadcrumb-item"><a href="<%=dashboard%>">Home</a></li>
							<li class="breadcrumb-item active">Statistiche</li>
							<%
								}
							%>
						</ol>
					</div>
				</div>

				<div class="row">
					<!-- column -->
					<div class="col-lg-6 col-md-12">
						<div class="card">
							<div class="card-block">
								<h4 class="card-title text-center">Nuovi tirocinanti negli
									ultimi 30 giorni</h4>
								<div id="topLast30Days"></div>
								<ul id="topLast30DaysList" class="list-inline text-center"></ul>
							</div>
						</div>
					</div>
					<div class="col-lg-6 col-md-12">
						<!-- Column -->
						<div class="card card-inverse card-warning"
							style="margin-bottom: 15px">
							<div class="card-block">
								<div class="d-flex">
									<div class="m-r-20 align-self-center">
										<h1 class="text-white">
											<i class="ti-stats-up"></i>
										</h1>
									</div>
									<div>
										<h3 class="card-title">Tirocini completati</h3>
										<h6 id="currentDateCompletati" class="card-subtitle"></h6>
									</div>
								</div>
								<div class="row">
									<div class="col-4 align-self-center text-center">
										<font id="currentDateCompletatiValue"
											class="display-3 text-white">0</font>
									</div>
									<div class="col-8 align-self-center">
										<div class="usage chartist-chart" style="height: 120px"></div>
									</div>
								</div>
							</div>
						</div>
						<!-- Column -->
						<!-- Column -->
						<div class="card card-inverse card-danger">
							<div class="card-block">
								<div class="d-flex">
									<div class="m-r-20 align-self-center">
										<h1 class="text-white">
											<i class="ti-agenda"></i>
										</h1>
									</div>
									<div>
										<h3 class="card-title">Report creati</h3>
										<h6 id="currentDateReports" class="card-subtitle"></h6>
									</div>
								</div>
								<div class="row">
									<div class="col-4 align-self-center text-center">
										<font id="currentDateReportsValue"
											class="display-3 text-white">0</font>
									</div>
									<div class="col-8  text-right">
										<div class="spark-count" style="height: 120px"></div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-12 col-md-12 col-xs-12">
						<div class="card">
							<div class="card-block">
								<h4 class="card-title text-center">Valutazioni generali
									degli studenti</h4>
								<div class="row">
									<div class="col-lg-3 col-md-6 col-xs-12 marginmd">
										<div class="row">
											<label for="name" class="align-self-center col-3">Azienda</label>
											<div class="col-9">
												<input type="text" class="form-control" id="ragSocFilt">
											</div>
										</div>
									</div>
									<div class="col-lg-3 col-md-6 col-xs-12 marginmd">
										<div class="row">
											<div class="btn-group" data-toggle="buttons"
												style="margin: auto;">
												<label class="btn btn-warning" aria-pressed="true">
													<input type="radio" name="options" id="option1"
													autocomplete="off">In Corso
												</label> <label class="btn btn-warning" aria-pressed="true">
													<input type="radio" name="options" id="option2"
													autocomplete="off">Terminati
												</label> <label class="btn btn-warning active" aria-pressed="true">
													<input type="radio" name="options" id="option3"
													autocomplete="off">Tutti
												</label>
											</div>
										</div>
									</div>
									<div class="col-lg-3 col-md-6 col-xs-12 marginmd">
										<div class="row">
											<label for="name" class="align-self-center col-2">Dal</label>
											<div class="col-10">
												<input type="date" class="form-control" id="dalFilt">
											</div>
										</div>
									</div>
									<div class="col-lg-3 col-md-6 col-xs-12 marginmd">
										<div class="row">
											<label for="name" class="align-self-center col-2">Al</label>
											<div class="col-10">
												<input type="date" class="form-control" id="alFilt">
											</div>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-12" style="margin-top: 10px;">
										<ul class="list-inline pull-right">
											<li>
												<h6 class="text-muted">
													<i class="fa fa-circle m-r-5 text-success"></i>Product A
												</h6>
											</li>
											<li>
												<h6 class="text-muted">
													<i class="fa fa-circle m-r-5 text-info"></i>Product B
												</h6>
											</li>
										</ul>
										<h3>Revenue Statistics</h3>
										<h6 class="card-subtitle">January 2017</h6>
									</div>
								</div>
								<div class="row">
									<div class="col-12">
										<!-- <div class="total-revenue4" style="height: 350px;"></div> -->
										<div class="ct-animation-chart" style="height: 400px;"></div>
									</div>
								</div>
								<div class="row">
									<div class="col-12">
										<!-- Column -->
										<h4 class="card-title"></h4>
										<div id="basicgrid"></div>
									</div>
								</div>
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
	<!--Morris JavaScript -->
	<script src="assets/plugins/raphael/raphael-min.js"></script>
	<script src="assets/plugins/morrisjs/morris.js"></script>
	<script src="assets/plugins/sparkline/jquery.sparkline.min.js"></script>
	<script type="text/javascript"
		src="assets/plugins/jsgrid/dist/jsgrid.min.js"></script>
	<script src="js/jQuery.date.js"></script>
	<script>
		var date = $.date('F Y');
		document.getElementById("currentDateCompletati").innerHTML = date;
		document.getElementById("currentDateReports").innerHTML = date;
	</script>

	<script src="js/stats.js"></script>
	<script src="assets/plugins/jsgrid/db.js"></script>
	<script src="js/stats-jsgrid-init.js"></script>

	<!-- ============================================================== -->
	<!-- Style switcher -->
	<!-- ============================================================== -->
	<script src="assets/plugins/styleswitcher/jQuery.style.switcher.js"></script>
</body>

</html>


