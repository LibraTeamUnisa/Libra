<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="it.unisa.libra.model.dao.IPermessoDao"%>
<%@ page import="javax.naming.InitialContext"%>
<%@ page import="javax.naming.Context"%>
<%@ page import="java.util.List"%>
<%@ page import="it.unisa.libra.bean.Permesso"%>
<%@ page import="it.unisa.libra.bean.Gruppo"%>

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
<!--  alert CSS -->
<link href="assets/plugins/sweetalert/sweetalert.css" rel="stylesheet"
	type="text/css">
<!-- chartist CSS -->
<link href="assets/plugins/chartist-js/dist/chartist.min.css"
	rel="stylesheet">
<link href="assets/plugins/chartist-js/dist/chartist-init.css"
	rel="stylesheet">
<link
	href="assets/plugins/chartist-plugin-tooltip-master/dist/chartist-plugin-tooltip.css"
	rel="stylesheet">


<!--  link href="assets/plugins/css-chart/css-chart.css" rel="stylesheet"-->


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
	<!--  div class="preloader">
		<svg class="circular" viewBox="25 25 50 50">
            <circle class="path" cx="50" cy="50" r="20" fill="none"
				stroke-width="2" stroke-miterlimit="10" /> </svg>
		
	</div>
	-->
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

				<!-- 
				<div class="dropdown-menu animated bounceInDown" aria-labelledby="2">
					<div class="alert alert-success">
						<button type="button" class="close" data-dismiss="alert"
							aria-label="Close">
							<span aria-hidden="true">×</span>
						</button>
						<h3 class="text-success">
							<i class="fa fa-check-circle"></i>Modifiche effettuate
						</h3>
					</div>
				</div>

 -->
				<form id="permessiForm" method="post">
					<div class="row page-titles">
						<div class="col-md-6 col-8 align-self-center">
							<h3 class="text-themecolor m-b-0 m-t-0">Gestione permessi
								sui feedback</h3>
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a
									href="dashboardSegreteria.jsp">Home</a></li>
								<li class="breadcrumb-item active">Permessi sui feedback</li>
							</ol>
						</div>
						<div class="col-md-6 col-4 align-self-center">
							<button class="btn pull-right btn-rounded btn-danger"
								type="submit">Salva modifiche</button>
						</div>
					</div>

					<%
						IPermessoDao permessoDao = (IPermessoDao) new InitialContext().lookup("java:app/Libra/PermessoJpa");
						List<Permesso> listPerm = permessoDao.findAll(Permesso.class);
						Permesso checkRicevuti = null, noFeedback = null, conFirma = null, anonimi = null;
						Gruppo g = new Gruppo();
						for (Permesso p : listPerm) {
							if (p.getTipo().equals("conFirma")) {
								conFirma = p;
								continue;
							} else if (p.getTipo().equals("anonimi")) {
								anonimi = p;
								continue;
							} else if (p.getTipo().equals("noFeedback")) {
								noFeedback = p;
								continue;
							} else if (p.getTipo().equals("ricevuti")) {
								checkRicevuti = p;
							}
						}
					%>

					<div class="card card-block">
						<!-- Studente -->
						<%
							g.setRuolo("Studente");
						%>
						<h4 class="card-title text-themecolor">Studente</h4>
						<br>
						<div class="checkbox checkbox-danger" style="margin-left: 18px">
							<input id="studenteRicevuti" type="checkbox"
								<%if (checkRicevuti != null && checkRicevuti.getGruppi().contains(g)) {%>
								checked <%}%>><label for="studenteRicevuti"> Uno
								studente pu&ograve; visualizzare il feedback lasciatogli
								dall'azienda</label>
						</div>
						<br>
						<div class="radio radio-danger">
							<input type="radio" name="radioStudente" id="radio1Studente"
								value="conFirmaStudente"
								<%if (conFirma != null && conFirma.getGruppi().contains(g)) {%>
								checked <%}%>> <label for="radio1Studente"> Uno
								studente pu&ograve; visualizzare i feedback lasciati dagli altri
								studenti alle aziende (Con firma)</label>
						</div>
						<div class="radio radio-danger">
							<input type="radio" name="radioStudente" id="radio2Studente"
								value="anonimiStudente"
								<%if (anonimi != null && anonimi.getGruppi().contains(g)) {%>
								checked <%}%>> <label for="radio2Studente"> Uno
								studente pu&ograve; visualizzare i feedback lasciati dagli altri
								studenti alle aziende (Mantenendo l'anonimato)</label>
						</div>
						<div class="radio radio-danger">
							<input type="radio" name="radioStudente" id="radio3Studente"
								value="noFeedbackStudente"
								<%if (noFeedback != null && noFeedback.getGruppi().contains(g)) {%>
								checked <%}%>> <label for="radio3Studente"> Uno
								studente non pu&ograve; visualizzare i feedback lasciati dagli
								altri studenti alle aziende</label>
						</div>
					</div>

					<div class="card card-block">
						<!-- Azienda -->
						<%
							g.setRuolo("Azienda");
						%>
						<h4 class="card-title text-themecolor">Azienda</h4>
						<br>
						<div class="radio radio-danger">
							<input type="radio" name="radioAzienda" id="radio1Azienda"
								value="conFirmaAzienda"
								<%if (conFirma != null && conFirma.getGruppi().contains(g)) {%>
								checked <%}%>> <label for="radio1Azienda">
								Un'azienda pu&ograve; visualizzare i feedback lasciati dagli
								studenti (Con firma)</label>
						</div>
						<div class="radio radio-danger">
							<input type="radio" name="radioAzienda" id="radio2Azienda"
								value="anonimiAzienda"
								<%if (anonimi != null && anonimi.getGruppi().contains(g)) {%>
								checked <%}%>> <label for="radio2Azienda">
								Un'azienda pu&ograve; visualizzare i feedback lasciati dagli
								studenti (Mantenendo l'anonimato)</label>
						</div>
						<div class="radio radio-danger">
							<input type="radio" name="radioAzienda" id="radio3Azienda"
								value="noFeedbackAzienda"
								<%if (noFeedback != null && noFeedback.getGruppi().contains(g)) {%>
								checked <%}%>> <label for="radio3Azienda">
								Un'azienda non pu&ograve; visualizzare i feedback lasciati dagli
								studenti</label>
						</div>
					</div>

					<div class="card card-block">
						<!-- TutorInterno -->
						<%
							g.setRuolo("TutorInterno");
						%>
						<h4 class="card-title text-themecolor">Tutor Interno</h4>
						<br>
						<div class="checkbox checkbox-danger" style="margin-left: 18px">
							<input id="tutorInternoRicevuti" type="checkbox"
								<%if (checkRicevuti != null && checkRicevuti.getGruppi().contains(g)) {%>
								checked <%}%>><label for="tutorInternoRicevuti">
								Un tutor interno pu&ograve; visualizzare i feedback lasciati
								dalle aziende ai propri studenti</label>
						</div>
						<br>
						<div class="radio radio-danger">
							<input type="radio" name="radioTutorInterno"
								id="radio1TutorInterno" value="conFirmaTutorInterno"
								<%if (conFirma != null && conFirma.getGruppi().contains(g)) {%>
								checked <%}%>> <label for="radio1TutorInterno">
								Un tutor interno pu&ograve; visualizzare i feedback lasciati dai
								propri studenti alle aziende (Con firma)</label>
						</div>
						<div class="radio radio-danger">
							<input type="radio" name="radioTutorInterno"
								id="radio2TutorInterno" value="anonimiTutorInterno"
								<%if (anonimi != null && anonimi.getGruppi().contains(g)) {%>
								checked <%}%>> <label for="radio2TutorInterno">
								Un tutor interno pu&ograve; visualizzare i feedback lasciati dai
								propri studenti alle aziende (Mantenendo l'anonimato)</label>
						</div>
						<div class="radio radio-danger">
							<input type="radio" name="radioTutorInterno"
								id="radio3TutorInterno" value="noFeedbackTutorInterno"
								<%if (noFeedback != null && noFeedback.getGruppi().contains(g)) {%>
								checked <%}%>> <label for="radio3TutorInterno">
								Un tutor interno non pu&ograve; visualizzare i feedback lasciati
								dai propri studenti alle aziende </label>
						</div>
					</div>

					<div class="card card-block">
						<!-- Presidente -->
						<%
							g.setRuolo("Presidente");
						%>
						<h4 class="card-title text-themecolor">Presidente</h4>
						<br>
						<div class="checkbox checkbox-danger" style="margin-left: 18px">
							<input id="presidenteRicevuti" type="checkbox"
								<%if (checkRicevuti != null && checkRicevuti.getGruppi().contains(g)) {%>
								checked <%}%>><label for="presidenteRicevuti">
								Il Presidente pu&ograve; visualizzare i feedback lasciati dalle
								aziende agli studenti</label>
						</div>
						<br>
						<div class="radio radio-danger">
							<input type="radio" name="radioPresidente" id="radio1Presidente"
								value="conFirmaPresidente"
								<%if (conFirma != null && conFirma.getGruppi().contains(g)) {%>
								checked <%}%>> <label for="radio1Presidente"> Il
								Presidente pu&ograve; visualizzare i feedback lasciati dagli
								studenti alle aziende (Con firma)</label>
						</div>
						<div class="radio radio-danger">
							<input type="radio" name="radioPresidente" id="radio2Presidente"
								value="anonimiPresidente"
								<%if (anonimi != null && anonimi.getGruppi().contains(g)) {%>
								checked <%}%>> <label for="radio2Presidente"> Il
								Presidente pu&ograve; visualizzare i feedback lasciati dagli
								studenti alle aziende (Mantenendo l'anonimato)</label>
						</div>
						<div class="radio radio-danger">
							<input type="radio" name="radioPresidente" id="radio3Presidente"
								value="noFeedbackPresidente"
								<%if (noFeedback != null && noFeedback.getGruppi().contains(g)) {%>
								checked <%}%>> <label for="radio3Presidente"> Il
								Presidente non pu&ograve; visualizzare i feedback lasciati dagli
								studenti alle aziende </label>
						</div>
					</div>

					<div class="card card-block">
						<!-- Segreteria -->
						<%
							g.setRuolo("Segreteria");
						%>
						<h4 class="card-title text-themecolor">Segreteria</h4>
						<br>
						<div class="checkbox checkbox-danger" style="margin-left: 18px">
							<input id="segreteriaRicevuti" type="checkbox"
								<%if (checkRicevuti != null && checkRicevuti.getGruppi().contains(g)) {%>
								checked <%}%>><label for="segreteriaRicevuti">
								Il personale di segreteria pu&ograve; visualizzare i feedback
								lasciati dalle aziende agli studenti</label>
						</div>
						<br>
						<div class="radio radio-danger">
							<input type="radio" name="radioSegreteria" id="radio1Segreteria"
								value="conFirmaSegreteria"
								<%if (conFirma != null && conFirma.getGruppi().contains(g)) {%>
								checked <%}%>> <label for="radio1Segreteria"> Il
								personale di segreteria pu&ograve; visualizzare i feedback
								lasciati dagli studenti alle aziende (Con firma)</label>
						</div>
						<div class="radio radio-danger">
							<input type="radio" name="radioSegreteria" id="radio2Segreteria"
								value="anonimiSegreteria"
								<%if (anonimi != null && anonimi.getGruppi().contains(g)) {%>
								checked <%}%>> <label for="radio2Segreteria"> Il
								personale di segreteria pu&ograve; visualizzare i feedback
								lasciati dagli studenti alle aziende (Mantenendo l'anonimato)</label>
						</div>
						<div class="radio radio-danger">
							<input type="radio" name="radioSegreteria" id="radio3Segreteria"
								value="noFeedbackSegreteria"
								<%if (noFeedback != null && noFeedback.getGruppi().contains(g)) {%>
								checked <%}%>> <label for="radio3Segreteria"> Il
								personale di segreteria non pu&ograve; visualizzare i feedback
								lasciati dagli studenti alle aziende </label>
						</div>
					</div>

				</form>

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
	<!-- SweetAlert -->
	<script src="assets/plugins/sweetalert/sweetalert.min.js"></script>
	<script src="assets/plugins/sweetalert/jquery.sweet-alert.custom.js"></script>
	<!--  script
		src="assets/plugins/chartist-plugin-tooltip-master/dist/chartist-plugin-tooltip.min.js"></script-->
	<!-- Chart JS >
	<script src="assets/plugins/echarts/echarts-all.js"></script>
	<script src="js/dashboard5.js"></script-->


	<!-- ============================================================== -->
	<!-- Style switcher -->
	<!-- ============================================================== -->
	<script src="assets/plugins/styleswitcher/jQuery.style.switcher.js"></script>

</body>

</html>


