<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@page import="it.unisa.libra.model.dao.IStudenteDao"%>
<%@page import="it.unisa.libra.model.dao.IUtenteDao"%>
<%@page import="it.unisa.libra.model.dao.IProgettoFormativoDao"%>
<%@page import="it.unisa.libra.bean.Studente"%>
<%@page import="it.unisa.libra.bean.Utente"%>
<%@page import="it.unisa.libra.bean.ProgettoFormativo"%>
<%@page import="java.util.Iterator"%>
<%@page import="it.unisa.libra.model.dao.ITutorInternoDao"%>
<%@ page import="javax.naming.InitialContext"%>
<%@ page import="javax.naming.Context"%>
<%@page import="it.unisa.libra.bean.TutorInterno"%>
<%@page import="java.util.*,it.unisa.*"%>

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
				<div class="card">
					<div class="card-block">
						<h4 class="card-title">Lista Studenti</h4>
						<%
							String emailTutor = (String) request.getSession().getAttribute("utenteEmail");
							String emailTutorParamiter = (String) request.getParameter("email");

							ITutorInternoDao tutorInternoDao = (ITutorInternoDao) new InitialContext()
									.lookup("java:app/Libra/TutorInternoJpa");
							TutorInterno tutorInterno = tutorInternoDao.findById(TutorInterno.class, "utenteEmail");

							if (emailTutor.length() == 0 || tutorInterno == null) {
						%>
						<h6 class="card-subtitle">Nessuno Studente Presente</h6>
						<%
							} else {

								List<ProgettoFormativo> listaPF = tutorInterno.getProgettiFormativi();
								if (listaPF.isEmpty()) {
						%>
						<h6 class="card-subtitle">Nessuno Studente Presente</h6>
						<%
							} else {
						%>

						<h6 class="card-subtitle"></h6>
						<div class="table-responsive">

							<table id="example23"
								class="display nowrap table table-hover table-striped table-bordered dataTable"
								cellspacing="0" width="100%" role="grid"
								aria-describedby="example23_info" style="width: 100%;">
								<thead>
									<tr role="row">
										<th class="sorting_desc" tabindex="0"
											aria-controls="example23" rowspan="1" colspan="1"
											aria-label="Numero: activate to sort column ascending"
											style="width: 91px;" aria-sort="descending">#</th>
										<th class="sorting" tabindex="0" aria-controls="example23"
											rowspan="1" colspan="1"
											aria-label="Matricola: activate to sort column ascending"
											style="width: 121px;">Matricola</th>
										<th class="sorting" tabindex="0" aria-controls="example23"
											rowspan="1" colspan="1"
											aria-label="Cognome: activate to sort column ascending"
											style="width: 75px;">Cognome</th>
										<th class="sorting" tabindex="0" aria-controls="example23"
											rowspan="1" colspan="1"
											aria-label="Nome: activate to sort column ascending"
											style="width: 50px;">Nome</th>
										<th class="sorting" tabindex="0" aria-controls="example23"
											rowspan="1" colspan="1"
											aria-label="Azienda: activate to sort column ascending"
											style="width: 85px;">Azienda</th>
										<th class="sorting" tabindex="0" aria-controls="example23"
											rowspan="1" colspan="1"
											aria-label="Ambito: activate to sort column ascending"
											style="width: 83px;">Ambito</th>
											<th class="sorting" tabindex="0" aria-controls="example23"
											rowspan="1" colspan="1"
											aria-label="Stato Tirocinio: activate to sort column ascending"
											style="width: 83px;">Stato Tirocinio</th>
											<th class="sorting" tabindex="0" aria-controls="example23"
											rowspan="1" colspan="1"
											aria-label="Data Inizio: activate to sort column ascending"
											style="width: 83px;">Data Inizio</th>
											<th class="sorting" tabindex="0" aria-controls="example23"
											rowspan="1" colspan="1"
											aria-label="Data Fine: activate to sort column ascending"
											style="width: 83px;">Data Fine</th>
									</tr>
								</thead>
								<tfoot>
									<tr>
										<th rowspan="1" colspan="1">#</th>
										<th rowspan="1" colspan="1">Matricola</th>
										<th rowspan="1" colspan="1">Cognome</th>
										<th rowspan="1" colspan="1">Nome</th>
										<th rowspan="1" colspan="1">Azienda</th>
										<th rowspan="1" colspan="1">Ambito</th>
										
										<th rowspan="1" colspan="1">Stato Tirocinio</th>
										
										<th rowspan="1" colspan="1">Data Inizio</th>
										
										<th rowspan="1" colspan="1">Data Fine</th>
									</tr>
								</tfoot>
											
								<tbody>
									<%
										Iterator<?> it = listaPF.iterator();
												int i = 1;
												while (it.hasNext()) {
													ProgettoFormativo progettoFormativo = (ProgettoFormativo) it.next();
									%>
									<tr role="row" class="odd">
										<td class="sorting_1"><%=i%></td>
										<td><%=progettoFormativo.getStudente().getMatricola()%></td>
										<td><%=progettoFormativo.getStudente().getNome()%></td>
										<td><%=progettoFormativo.getStudente().getCognome()%></td>
										<td><%=progettoFormativo.getAzienda()%></td>
										<td><%=progettoFormativo.getAmbito()%></td>
										<td>
										<%
											if ((progettoFormativo.getStato() >= 0) && (progettoFormativo.getStato() < 4)) {
										%>
										<span class="label label-warning">In Attesa</span>

										<%
											} else if (progettoFormativo.getStato() == 4) {
										%>

										<span class="label label-primary">Verificato</span>
									
										<%
											} else if (progettoFormativo.getStato() == 5) {
										%>
										<span class="label label-success">Approvato</span>
										<%
											} else if (progettoFormativo.getStato() == 6) {
										%>
										<span class="label label-danger">Rifiutato</span>
										<%
											}
										%>
</td>
										<td><%=progettoFormativo.getDataInizio()%></td>
										<td><%=progettoFormativo.getDataFine()%></td>

									</tr>
									<%
										i++;
												}
									%>

								</tbody>
							</table>

						</div>
						<%
							}
							}
						%>

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
</body>

</html>


