<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@page import="it.unisa.libra.model.dao.IStudenteDao"%>
<%@page import="it.unisa.libra.model.dao.IUtenteDao"%>
<%@page import="it.unisa.libra.model.dao.IAziendaDao"%>
<%@page import="it.unisa.libra.model.dao.IDomandaDao"%>
<%@page import="it.unisa.libra.model.dao.IAziendaDao"%>
<%@page import="it.unisa.libra.model.dao.IFeedbackDao"%>
<%@page import="it.unisa.libra.model.dao.IProgettoFormativoDao"%>
<%@page import="it.unisa.libra.bean.Studente"%>
<%@page import="it.unisa.libra.bean.Azienda"%>
<%@page import="it.unisa.libra.bean.Feedback"%>
<%@page import="it.unisa.libra.bean.FeedbackPK"%>
<%@page import="it.unisa.libra.bean.Domanda"%>
<%@page import="it.unisa.libra.bean.Utente"%>
<%@page import="it.unisa.libra.bean.ProgettoFormativo"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.*"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>

<%
	DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
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
	<%
		session = request.getSession();
		String email = (String) session.getAttribute("utenteEmail");
		String ruolo = (String) session.getAttribute("utenteRuolo");
		String emailStudente = (String) request.getParameter("studente");
		String idPF = (String) request.getParameter("pf");
		Studente studente = null;
		ProgettoFormativo progettoFormativo = null;

		if (emailStudente != null) {
			IStudenteDao studenteDao = (IStudenteDao) new InitialContext().lookup("java:app/Libra/StudenteJpa");
			studente = studenteDao.findById(Studente.class, emailStudente);
		}
		if (idPF != null) {
			IProgettoFormativoDao progettoFormativoDao = (IProgettoFormativoDao) new InitialContext()
					.lookup("java:app/Libra/ProgettoFormativoJpa");
			progettoFormativo = progettoFormativoDao.findById(ProgettoFormativo.class, Integer.parseInt(idPF));
		}
		if (email == null || ruolo == null) {
			response.sendRedirect("/Libra/errore.jsp");
		}
	%>
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
				<%
					IAziendaDao aziendaDao = (IAziendaDao) new InitialContext().lookup("java:app/Libra/AziendaJpa");
					Azienda a = aziendaDao.findById(Azienda.class, email);
					if (!ruolo.equalsIgnoreCase("Azienda")) {
				%>
				<div class="row page-titles">
					<div class="col-md-6 col-8 align-self-center">
						<h3 class="text-themecolor m-b-0 m-t-0">Non puoi visualizzare
							questa pagina.</h3>
					</div>

				</div>
				<%
					} else if (emailStudente == null || idPF == null || studente == null || progettoFormativo == null
							|| (!a.getUtenteEmail().equals(progettoFormativo.getAzienda().getUtenteEmail()))
							|| (!studente.getUtenteEmail().equals(progettoFormativo.getStudente().getUtenteEmail()))) {
				%>
				<div class="row page-titles">
					<div class="col-md-6 col-8 align-self-center">
						<h3 class="text-themecolor m-b-0 m-t-0">Studente o progetto
							formativo non trovato.</h3>
					</div>

				</div>
				<%
					} else if (progettoFormativo.getStato() != 4) {
				%>
				<div class="row page-titles">
					<div class="col-md-6 col-8 align-self-center">
						<h3 class="text-themecolor m-b-0 m-t-0">
							Valutazione non disponibile per lo studente
							<%=studente.getNome()%>
							<%=studente.getCognome()%></h3>
					</div>

				</div>
				<%
					} else {
						IFeedbackDao feedbackDao = (IFeedbackDao) new InitialContext().lookup("java:app/Libra/FeedbackJpa");
						FeedbackPK fpk = new FeedbackPK();
						fpk.setProgettoFormativoID(progettoFormativo.getId());
						List<Feedback> f = new ArrayList<Feedback>();
						for (int z = 1; z <= 12; z++) {
							fpk.setDomandaID(z);
							f.add(feedbackDao.findById(Feedback.class, fpk));
						}
						Boolean esistente = false;
						if (f.get(0) != null) {
							esistente = true;
						}
						IDomandaDao domandaDao = (IDomandaDao) new InitialContext().lookup("java:app/Libra/DomandaJpa");
						Domanda d = null;
				%>
				<div class="row page-titles">
					<div class="col-md-6 col-8 align-self-center">
						<h3 class="text-themecolor m-b-0 m-t-0">
							Valuta
							<%=studente.getNome()%>
							<%=studente.getCognome()%></h3>
						<ol class="breadcrumb">
							<li class="breadcrumb-item"><a href="index.jsp">Home</a></li>
							<li class="breadcrumb-item"><a href="valutaStudenti.jsp">Valuta
									Studenti</a></li>
							<li class="breadcrumb-item active">Valuta <%=studente.getNome()%>
								<%=studente.getCognome()%></li>
						</ol>
					</div>

				</div>
				<div class="card wizard-card" style="padding: 1%">
					<form id="feedbackStudenteForm" method="post">

						<div class="container">
							<h4>Relativamente al progetto di tirocinio:</h4>
							<%
								for (int i = 0; i < 11; i++) {
										d = domandaDao.findById(Domanda.class, i + 1);
										if (i == 4) {
							%>
							<h4>Relativamente al tirocinante:</h4>
							<%
								}
										if (i == 8) {
							%>
							<h4>Relativamente alle strutture universitarie addette alla
								gestione dei Tirocini:</h4>
							<%
								}
							%>
							<div class="row">
								<!-- panel preview -->
								<div class="form-group col-md-8">
									<label><%=d.getTesto()%></label>
								</div>
								<div class="form-group col-md-4" style="text-align: center;">
									<%
										if (esistente) {
											%>
											<label style="margin-left:3.5%;"><%=f.get(i).getValutazione()%></label>
												<%} else {
													for (int j = 1; j <= 5; j++) {
									%>
									<label class="radio-inline" style="margin-left: 3.5%;">
										<input type="radio" name="feedback<%=d.getId()%>"
										value="<%=j%>" <%if (j == 3) {%> checked="checked"
										<%}%>> <%=j%>
									</label>
									<%
										}
												}
									%>
								</div>
							</div>
							<%
								} //END FOR
							%>
							<div class="row">
								<div class="form-group col-md-8">
									<label for="note">Note: </label> <span class="text-danger"
										style="margin-left: 20%;<%if (esistente) {%>display:none;<%}%>"
										id="hideNote">Il campo non pu&ograve; essere vuoto.</span>
									<textarea class="form-control" rows="5" id="note"
										onchange='controlloNote()' <%if (esistente) {%> disabled <%}%>><%if (esistente) {%><%=f.get(11).getValutazione()%><%}%></textarea>
									<textarea id="idProgettoFormativo" style="display: none"><%=progettoFormativo.getId()%></textarea>
								</div>
							</div>
							<button class="btn btn-primary" type="submit"
								id="inviaValutazione" onclick='controlloNote()'
								style="margin:0 auto;<%if(esistente){%>display:none;<%}%>" disabled>Invia</button>
							<div class="alert alert-success" id="success"
								style="display: none">Valutazione effettuata con successo!</div>
						</div>
					</form>

				</div>
				<!-- END CARD WIZARD CARD -->
				<%
					}
				%>

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
	<script src="js/valutaStudente.js" type="text/javascript"></script>
	<script src="js/inviaValutazioneStudente.js" type="text/javascript"></script>
</body>

</html>


