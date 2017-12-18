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
<!-- DataTable style -->
<link href="css/datatables.css" rel="stylesheet">
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
				<h1>Lista Studenti</h1>
				<table class="table">
					<thead>
						<tr>
							<th>Nome</th>
							<th>Stato</th>
							<th>Data Invio</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td><a href="javascript:void(0)"><img src="assets/images/users/4.jpg"
									alt="user" width="40" class="img-circle" /> Genelia Deshmukh</a></td>
							<td><span class="label label-danger">Rifiutato</span></td>
							<td>12-10-2014</td>
						</tr>
						<tr>
							<td><a href="javascript:void(0)"><img src="assets/images/users/5.jpg"
									alt="user" width="40" class="img-circle" /> Arijit Singh</a></td>
							<td><span class="label label-info">Verificato</span></td>
							<td>10-09-2014</td>
						</tr>
						<tr>
							<td><a href="javascript:void(0)"><img src="assets/images/users/6.jpg"
									alt="user" width="40" class="img-circle" /> Govinda jalan</a></td>
							<td><span class="label label-success">Approvato</span></td>
							<td>1-10-2013</td>
						</tr>
						<tr>
							<td><a href="javascript:void(0)"><img src="assets/images/users/7.jpg"
									alt="user" width="40" class="img-circle" /> Hritik Roshan</a></td>
							<td><span class="label label-warning">In Attesa</span></td>
							<td>2-10-2017</td>

						</tr>
						<tr>
							<td><a href="javascript:void(0)"><img src="assets/images/users/8.jpg"
									alt="user" width="40" class="img-circle" /> John Abraham</a></td>
							<td><span class="label label-danger">Rifiutato</span></td>
							<td>10-9-2015</td>
						</tr>
						<tr>
							<td><a href="javascript:void(0)"><img src="assets/images/users/1.jpg"
									alt="user" width="40" class="img-circle" /> Pawandeep kumar</a></td>
							<td><span class="label label-warning">In Attesa</span></td>
							<td>10-5-2013</td>
						</tr>
						<tr>
							<td><a href="javascript:void(0)"><img src="assets/images/users/2.jpg"
									alt="user" width="40" class="img-circle" /> Ritesh Deshmukh</a></td>
							<td><span class="label label-danger">Rifiutato</span></td>
							<td>05-10-2012</td>
						</tr>
						<tr>
							<td><a href="javascript:void(0)"><img src="assets/images/users/2.jpg"
									alt="user" width="40" class="img-circle" /> Salman Khan</a></td>
							<td><span class="label label-info">Verificato</span></td>
							<td>11-10-2014</td>
						</tr>
						<tr>
							<td><a href="javascript:void(0)"><img src="assets/images/users/3.jpg"
									alt="user" width="40" class="img-circle" /> Govinda jalan</a></td>
							<td><span class="label label-success">Approvato</span></td>
							<td>12-5-2017</td>
						</tr>
						<tr>
							<td><a href="javascript:void(0)"><img src="assets/images/users/4.jpg"
									alt="user" width="40" class="img-circle" /> Sonu Nigam</a></td>
							<td><span class="label label-success">Approvato</span></td>
							<td>18-5-2009</td>
						</tr>
						<tr>
							<td><a href="javascript:void(0)"><img src="assets/images/users/5.jpg"
									alt="user" width="40" class="img-circle" /> Varun Dhawan</a></td>
							<td><span class="label label-danger">Rifiutato</span></td>
							<td>12-10-2010</td>
						</tr>
						<tr>
							<td><a href="javascript:void(0)"><img src="assets/images/users/6.jpg"
									alt="user" width="40" class="img-circle" /> Genelia Deshmukh</a></td>
							<td><span class="label label-danger">Rifiutato</span></td>
							<td>12-10-2014</td>
						</tr>
						<tr>

							<td><a href="javascript:void(0)"><img src="assets/images/users/7.jpg"
									alt="user" width="40" class="img-circle" /> Arijit Singh</a></td>
							<td><span class="label label-info">Verificato</span></td>
							<td>10-09-2014</td>
						</tr>
						<tr>
							<td><a href="javascript:void(0)"><img src="assets/images/users/8.jpg"
									alt="user" width="40" class="img-circle" /> Govinda jalan</a></td>
							<td><span class="label label-success">Approvato</span></td>
							<td>1-10-2013</td>
						</tr>
					</tbody>
				</table>
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
	<script src="js/dashboard5.js"></script>
	<!-- ============================================================== -->
	<!-- Style switcher -->
	<!-- ============================================================== -->
	<script src="assets/plugins/styleswitcher/jQuery.style.switcher.js"></script>
	<script src="js/datatables.js"></script>
	<script>
		var table;
		$(document).ready(function() {
			table = $('.table').DataTable({
				"paging": true,
				"searching": true,
				"pageLength": 10,	
				"columnDefs": [	
					{ "searchable": false, "targets": 1 },
					{ "searchable": false, "targets": 2 },
				  ],
				"initComplete" : function() {
						var input = $('.dataTables_filter input').unbind();
						self = this.api();
						$searchButton = $('<button class="btn btn-primary btn-sm">')
								   .text('search')
								   .click(function() {
										var text = input.val();
										if(/^([a-zA-Z]{0,100})$/.test(text)==false) {
											input.val("Error");
										}else
											self.search(input.val()).draw();
										
								   }),
						$('.dataTables_filter').append($searchButton);
					}
			  }); 
			
			});
		</script>
</body>

</html>


