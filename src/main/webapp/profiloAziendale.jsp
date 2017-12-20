<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ page import="java.util.*,it.unisa.libra.bean.Azienda, it.unisa.libra.model.dao.IAziendaDao,java.util.List,javax.naming.InitialContext"%>
<%
	String nome = (String)request.getParameter("nome");
	Azienda a = null;
	if(nome!=null){
		IAziendaDao aziendaDAO = (IAziendaDao) new InitialContext().lookup("java:app/Libra/AziendaJpa");
		a = aziendaDAO.findByName(nome);
	}
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
<link href="assets/plugins/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<!-- DataTables CSS -->
<link href="css/datatables.css" rel="stylesheet">

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
			<!-- CONTROLLO SUL NOME DELL'AZIENDA -->
			<%if (a==null){ %>
				<div class="row page-titles">
					<div class="col-md-6 col-8 align-self-center">
						<h3 class="text-themecolor m-b-0 m-t-0">Azienda non trovata</h3>
					</div>
				</div>
			<%} else { %>
				<div class="row page-titles">
					<div class="col-md-6 col-8 align-self-center">
						<h3 class="text-themecolor m-b-0 m-t-0">Dettagli azienda</h3>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6">
						<div class="card card-outline-warning">
							<div class="card-header">
								<h4 class="m-b-0 text-white">Informazioni</h4>
							</div>
							<div class="card-block">
								<p>
									<strong>Nome</strong> <span class="text-muted"> <%=a.getNome() %></span>
								</p>
								<p>
									<strong>Aree di interesse</strong> 
									<%if (a.getTutorEsterni().size()==0){ %>
										<span class="text-muted"> Nessun ambito disponibile </span>
									<%} else {
									    for(int i = 0; i < a.getTutorEsterni().size(); i++){
									    if(i!=0){
									    %>
									    <span class="text-muted"> - </span>
									    <%} %>
									    <span class="text-muted"><%=a.getTutorEsterni().get(i).getId().getAmbito()%></span>
									<%} %>
								</p>
								
								<p>
									<strong>Tutor esterni</strong> 
									<%if (a.getTutorEsterni().size()==0){ %>
									<span class="text-muted"> Nessun tutor esterno disponibile</span>
									<%} else {
									for(int i = 0; i < a.getTutorEsterni().size(); i++){
									    if(i!=0){
									    %>
									    <span class="text-muted"> - </span>
									    <%} %>
									    <span class="text-muted"><%=a.getTutorEsterni().get(i).getNome()%> <%=a.getTutorEsterni().get(i).getCognome()%></span>
									<%} %>
								</p>
								<p>
									<strong>Partita IVA <span class="text-muted"><%=a.getPartitaIVA()%></span>
									</strong>
								</p>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="card card-outline-danger">
							<div class="card-header">
								<h4 class="m-b-0 text-white">Contatti</h4>
							</div>
							<div class="card-block">
								<p>
									<strong>e-mail</strong> <span class="text-muted"><%=a.getUtenteEmail()%></span>
								</p>
								<p>
									<strong>Numero di telefono</strong> <span class="text-muted"><%=a.getUtente().getTelefono()%></span>
								</p>
								<p>
									<strong>Sede</strong> <span class="text-muted"><%=a.getUtente().getIndirizzo()%>, <%=a.getSede()%></span>
								</p>
								<div class="text-right">
								<!-- CONTROLLO SULL'UTENTE LOGGATO -->
									<button type="button" class="btn btn-rounded btn-danger">Iscriviti</button>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-12">
					<!-- CONTROLLO SUI FEEDBACK -->
						<h4 class="card-title">Feedback</h4>
						<table id="elencoFeedback">
							<thead>
								<tr>
									<th>Studente</th>
									<th>Feedback</th>
									<th>Data</th>
								</tr>
							</thead>
							<tbody>
								
								<tr>
									<td>Angelo Piccolella</td>
									<td>Primo Feedback</td>
									<td>2017-03-11</td>
								</tr>
								<tr>
									<td>Vincenzo Caputo</td>
									<td>Secondo Feedback</td>
									<td>2017-02-16</td>
								</tr>
								<tr>
									<td>Mario Ruggiero</td>
									<td>Terzo Feedback</td>
									<td>2017-10-21</td>
								</tr>
								<tr>
									<td>Umberto Giarritiello</td>
									<td>Quarto Feedback</td>
									<td>2017-03-01</td>
								</tr>
							</tbody>

						</table>

					</div>
				</div>
			<%} %>
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
	<!--Data Tables -->
	<script src="js/datatables.js"></script>
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
	<script>
	$(document).ready(function(){
		$("#elencoFeedback").DataTable({
			"paging":true,
			"searching":false,
		});
	});
		
	</script>
</body>

</html>


