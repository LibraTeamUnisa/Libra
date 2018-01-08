<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.naming.Context"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@page import="it.unisa.libra.model.dao.IAziendaDao"%>
<%@page import="it.unisa.libra.model.dao.IUtenteDao"%>
<%@page import="it.unisa.libra.bean.Azienda"%>
<%@page import="it.unisa.libra.bean.Utente"%>
<%@page import="java.util.List"%>



<%
	IAziendaDao aziendaDAO= (IAziendaDao) new InitialContext().lookup("java:app/Libra/AziendaJpa");
	IUtenteDao utenteDAO= (IUtenteDao) new InitialContext().lookup("java:app/Libra/UtenteJpa");
	List<Azienda> aziende = aziendaDAO.findAll(Azienda.class);
	HttpSession sessione = request.getSession();
	String email = (String) sessione.getAttribute("utenteEmail");
	
%>


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
<link href="css/datatables.css" 
	rel="stylesheet">
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
<style>
	@media only screen and (max-width : 640px){
		#DataTables_Table_0_length{
			display: none;
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
				<div class="row page-titles">
					<div class="col-md-6 col-8 align-self-center">
						<h3 class="text-themecolor m-b-0 m-t-0">Aziende Convenzionate</h3>
						<ol class="breadcrumb">
							<%
								if (session != null && session.getAttribute("utenteRuolo") != null) {
									String dashboard = request.getContextPath()
											+ "/dashboard".concat(session.getAttribute("utenteRuolo").toString()).concat(".jsp");
							%>
							<li class="breadcrumb-item"><a href="<%=dashboard%>">Home</a></li>
							<li class="breadcrumb-item active">Aziende Convenzionate</li>
							<%
								}
							%>
						</ol>
					</div>
				</div>
				<div class="card wizard-card" style="padding:1%">
					<div class="table-responsive">
						<table class="table">
							<thead>
								<tr>
									<th>Logo</th>
									<th>Nome</th>
									<th>Sede</th>
								</tr>
							</thead>
							<tbody>
							<%
								if (email == null ) {
									response.sendRedirect("/Libra/errore.jsp");
								}
								else{
									for(Azienda a: aziende){
										Utente utente =(Utente)utenteDAO.findById(Utente.class, a.getUtenteEmail());	
							%>
								<tr>
									<td><a href="profiloAziendale.jsp?nome=<%=a.getNome()%>"><img
											src="<%=utente.getImgProfilo()%>" alt="logoAzienda.png"
											width="40" class="img-circle" /></a></td>
									<td><%=a.getNome() %></td>
									<td><%=a.getSede() %></td>
								</tr>
							<%
									}
								}
							%>
							</tbody>
						</table>
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
					{ "searchable": false, "targets": 0 },
					{ "searchable": false, "targets": 2 },
				  ],
				"language": {
		            "lengthMenu": "Mostra _MENU_ risultati per pagina",
		            "zeroRecords": "Nessun risultato trovato",
		            "info": "Pagina _PAGE_ di _PAGES_",
		            "infoEmpty": "Nessun risultato presente",
		            "infoFiltered": "(Cercati su _MAX_ risultati totali)",
		            "paginate": {
		                "first":      "Prima",
		                "last":       "Ultima",
		                "next":       "Successiva",
		                "previous":   "Precedente"
		            }
			    },
				"initComplete" : function() {
					$(".dataTables_filter").empty();
					$(".dataTables_filter").html(
						'<div class="input-group add-on">'+
						'<input class="form-control" placeholder"Cerca" name="srch-term" id="srch-term" type="text">'+
							'<div class="input-group-btn">'+
								'<button class="btn btn-default buttonSearch">'+
									'<i class="mdi mdi-magnify"></i>'+
								'</button>'+
							'</div>'+
						'</div>');
					var input= $('.dataTables_filter input');
					self= this.api();
					$(".buttonSearch").mouseenter(function(){
						$(this).css({'background-color': '#D91A5F'});	
					})
					$(".buttonSearch").mouseleave(function(){
						$(this).css({'background-color':'#DDDDDD'});	
					})
					$(".buttonSearch").click(function(){
						var text= input.val();
						$(this).css({'outline':'none', 'box-shadow':'none'});
						$('.text-danger').remove();
						if(/^([0-9a-zA-Z\s@:./]{0,100})$/.test(text)==false){
							$('.dataTables_filter').append('<small class="text-danger">Input errato. Sono ammessi solo caratteri</small>');
						}else{
							self.search(input.val()).draw();
						}
					})
				}
					
			});
		});
	</script>
</body>

</html>


