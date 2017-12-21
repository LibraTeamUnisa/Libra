<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	import="it.unisa.libra.bean.*,
			it.unisa.libra.model.dao.*,
			javax.ejb.EJB,
			javax.naming.InitialContext,
			java.util.Date,
			java.util.Calendar,
			java.util.GregorianCalendar"%>


<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<!-- Tell the browser to be responsive to screen width -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="Profilo personale">
<meta name="author" content="Andrea Lorenzo">
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

	<!--OGGETTI-->
	<%
		/*session = request.getSession();
		String email = (String) session.getAttribute("utenteEmail");
		String ruolo = (String) session.getAttribute("utenteRuolo");*/

		String email = "andrea@studenti.unisa.it";
		String ruolo = "Studente";
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

				<div class="row page-titles">
					<div class="col-md-6 col-8 align-self-center">
						<h3 class="text-themecolor m-b-0 m-t-0">Visualizza Profilo</h3>
						<ol class="breadcrumb">
							<li class="breadcrumb-item"><a href="index.jsp">Home</a></li>
							<li class="breadcrumb-item active">Profilo</li>
						</ol>
					</div>
				</div>


				<!--UTENTE-->
				<%
					IUtenteDao utenteDao = (IUtenteDao) new InitialContext().lookup("java:app/Libra/UtenteJpa");
					Utente u = utenteDao.findById(Utente.class, email);
				%>

				<div class="card card-block">
					<div class="row">
						<div class="col-sm-4">
							<div class="card wild-card">
								<h4 class="text-themecolor m-b-0 m-t-0">
									Profilo di
									<%
									if (ruolo.equals("Azienda")) {
								%>
									<%=u.getAzienda().getNome()%>
									<%
										}
									%>
									<%
										if (ruolo.equals("Presidente")) {
									%>
									<%=u.getPresidente().getNome()%>
									<%
										}
									%>
									<%
										if (ruolo.equals("Segreteria")) {
									%>
									Segreteria
									<%
										}
									%>
									<%
										if (ruolo.equals("TutorInterno")) {
									%>
									<%=u.getTutorInterno().getNome()%>
									<%
										}
									%>
									<%
										if (ruolo.equals("Studente")) {
									%>
									<%=u.getStudente().getNome()%>
									<%
										}
									%>
								</h4>
							</div>
						</div>

						<div class="col-sm-3"></div>

						<div class="col-sm-5">
							<div class="card wild-card">
								<h4 class="text-themecolor m-b-0 m-t-0">
									Tipologia account:
									<%=ruolo%></h4>
							</div>
						</div>
					</div>

					<div class="row">
						<div class="col-sm-4">
							<div class="card wild-card">
								<div class="col-md-8 col-lg-9 text-center"
									style="margin: 0 auto;">
									<a href="#"><img src="assets/images/users/1.jpg" alt="user"
										class="img-circle img-responsive"></a>
								</div>
								<input type="file" placeholder="<%=u.getImgProfilo()%>"
									class="form-control form-control-line" disabled>
							</div>
						</div>


						<!--STUDENTE-->
						<%
							IStudenteDao studenteDao = (IStudenteDao) new InitialContext().lookup("java:app/Libra/StudenteJpa");
							Studente s = studenteDao.findById(Studente.class, email);

							if (ruolo.equals("Studente")) {
						%>
						<div class="col-sm-8">
							<div class="card wild-card"
								style="color: black; font-size: 120%;">

								<h3 class="box-title m-b-0">Dati personali:</h3>
								<br>

								<div class="row">
									<div class="col-sm-4">
										<label class="col-md-12">Nome:</label>
									</div>
									<div class="col-sm-5">
										<input type="text" placeholder="<%=s.getNome()%>"
											class="form-control form-control-line" disabled>
									</div>
								</div>

								<div class="row">
									<div class="col-sm-4">
										<label class="col-md-12">Cognome:</label>
									</div>
									<div class="col-sm-5">
										<input type="text" placeholder="<%=s.getCognome()%>"
											class="form-control form-control-line" disabled>
									</div>
								</div>

								<div class="row">
									<div class="col-sm-4">
										<label class="col-md-12">Data di nascita:</label>
									</div>
									<div class="col-sm-5">
										<%
											Date date = s.getDataDiNascita();
												Calendar calendar = new GregorianCalendar();
												calendar.setTime(date);
												int year = calendar.get(Calendar.YEAR);
												int mm = calendar.get(Calendar.MONTH) + 1;
												int gg = calendar.get(Calendar.DAY_OF_MONTH);
												String day = String.format("%02d", gg);
												String month = String.format("%02d", mm);
										%>
										<input type="date" value="<%=year%>-<%=month%>-<%=day%>"
											min="1900-01-01" class="form-control form-control-line"
											disabled>
									</div>
								</div>

								<div class="row">
									<div class="col-sm-4">
										<label class="col-md-12">Matricola:</label>
									</div>
									<div class="col-sm-5">
										<input type="text" placeholder="<%=s.getMatricola()%>"
											class="form-control form-control-line" disabled>
									</div>
								</div>

								<br>
								<h3 class="box-title m-b-0">Contatti:</h3>
								<br>

								<div class="row">
									<div class="col-sm-4">
										<label class="col-md-12">Indirizzo:</label>
									</div>
									<div class="col-sm-5">
										<input type="text" placeholder="<%=u.getIndirizzo()%>"
											class="form-control form-control-line" disabled>
									</div>
								</div>

								<div class="row">
									<div class="col-sm-4">
										<label class="col-md-12">Email:</label>
									</div>
									<div class="col-sm-5">
										<input type="text" placeholder="<%=u.getEmail()%>"
											class="form-control form-control-line" disabled>
									</div>
								</div>

								<div class="row">
									<div class="col-sm-4">
										<label class="col-md-12">Telefono:</label>
									</div>
									<div class="col-sm-5">
										<input type="text" placeholder="<%=u.getTelefono()%>"
											class="form-control form-control-line" disabled>
									</div>
								</div>

								<div class="row">
									<div class="col-sm-4"></div>
									<div class="col-sm-2"></div>
									<div class="col-sm-4">
										<br>
										<a href="/Libra/modificaProfilo.jsp"><button
												class="btn btn-success" onclick=>Modifica Profilo</button></a>
									</div>
								</div>
								<br>
							</div>
							<%
								}
							%>

							<!--TUTOR INTERNO-->
							<%
								ITutorInternoDao tutorInternoDao = (ITutorInternoDao) new InitialContext()
										.lookup("java:app/Libra/TutorInternoJpa");
								TutorInterno ti = tutorInternoDao.findById(TutorInterno.class, email);

								if (ruolo.equals("TutorInterno")) {
							%>
							<div class="col-sm-8">
								<div class="card wild-card"
									style="color: black; font-size: 120%;">

									<h3 class="box-title m-b-0">Dati personali:</h3>
									<br>

									<div class="row">
										<div class="col-sm-4">
											<label class="col-md-12">Nome:</label>
										</div>
										<div class="col-sm-5">
											<input type="text" placeholder="<%=ti.getNome()%>"
												class="form-control form-control-line" disabled>
										</div>
									</div>

									<div class="row">
										<div class="col-sm-4">
											<label class="col-md-12">Cognome:</label>
										</div>
										<div class="col-sm-5">
											<input type="text" placeholder="<%=ti.getCognome()%>"
												class="form-control form-control-line" disabled>
										</div>
									</div>

									<div class="row">
										<div class="col-sm-4">
											<label class="col-md-12">Data di nascita:</label>
										</div>
										<div class="col-sm-5">
											<%
												Date date = ti.getDataDiNascita();
													Calendar calendar = new GregorianCalendar();
													calendar.setTime(date);
													int year = calendar.get(Calendar.YEAR);
													int mm = calendar.get(Calendar.MONTH) + 1;
													int gg = calendar.get(Calendar.DAY_OF_MONTH);
													String day = String.format("%02d", gg);
													String month = String.format("%02d", mm);
											%>
											<input type="date" value="<%=year%>-<%=month%>-<%=day%>"
												min="1900-01-01" class="form-control form-control-line"
												disabled>
										</div>
									</div>

									<br>
									<h3 class="box-title m-b-0">Contatti:</h3>
									<br>

									<div class="row">
										<div class="col-sm-4">
											<label class="col-md-12">Indirizzo:</label>
										</div>
										<div class="col-sm-5">
											<input type="text" placeholder="<%=u.getIndirizzo()%>"
												class="form-control form-control-line" disabled>
										</div>
									</div>

									<div class="row">
										<div class="col-sm-4">
											<label class="col-md-12">Email:</label>
										</div>
										<div class="col-sm-5">
											<input type="text" placeholder="<%=u.getEmail()%>"
												class="form-control form-control-line" disabled>
										</div>
									</div>

									<div class="row">
										<div class="col-sm-4">
											<label class="col-md-12">Telefono:</label>
										</div>
										<div class="col-sm-5">
											<input type="text" placeholder="<%=u.getTelefono()%>"
												class="form-control form-control-line" disabled>
										</div>
									</div>

									<div class="row">
										<div class="col-sm-4">
											<label class="col-md-12">Sito:</label>
										</div>
										<div class="col-sm-5">
											<input type="text" placeholder="<%=ti.getLinkSito()%>"
												class="form-control form-control-line" disabled>
										</div>
									</div>

									<div class="row">
										<div class="col-sm-4"></div>
										<div class="col-sm-2"></div>
										<div class="col-sm-4">
											<br>
											<a href="/Libra/modificaProfilo.jsp"><button
													class="btn btn-success" onclick=>Modifica Profilo</button></a>
										</div>
									</div>
									<br>
								</div>
								<%
									}
								%>

								<!--PRESIDENTE-->
								<%
									IPresidenteDao presidenteDao = (IPresidenteDao) new InitialContext().lookup("java:app/Libra/PresidenteJpa");
									Presidente p = presidenteDao.findById(Presidente.class, email);

									if (ruolo.equals("Presidente")) {
								%>
								<div class="col-sm-8">
									<div class="card wild-card"
										style="color: black; font-size: 120%;">

										<h3 class="box-title m-b-0">Dati personali:</h3>
										<br>

										<div class="row">
											<div class="col-sm-4">
												<label class="col-md-12">Nome:</label>
											</div>
											<div class="col-sm-5">
												<input type="text" placeholder="<%=p.getNome()%>"
													class="form-control form-control-line" disabled>
											</div>
										</div>

										<div class="row">
											<div class="col-sm-4">
												<label class="col-md-12">Cognome:</label>
											</div>
											<div class="col-sm-5">
												<input type="text" placeholder="<%=p.getCognome()%>"
													class="form-control form-control-line" disabled>
											</div>
										</div>

										<div class="row">
											<div class="col-sm-4">
												<label class="col-md-12">Data di nascita:</label>
											</div>
											<div class="col-sm-5">
												<%
													Date date = p.getDataDiNascita();
														Calendar calendar = new GregorianCalendar();
														calendar.setTime(date);
														int year = calendar.get(Calendar.YEAR);
														int mm = calendar.get(Calendar.MONTH) + 1;
														int gg = calendar.get(Calendar.DAY_OF_MONTH);
														String day = String.format("%02d", gg);
														String month = String.format("%02d", mm);
												%>
												<input type="date" value="<%=year%>-<%=month%>-<%=day%>"
													min="1900-01-01" class="form-control form-control-line"
													disabled>
											</div>
										</div>

										<br>
										<h3 class="box-title m-b-0">Contatti:</h3>
										<br>

										<div class="row">
											<div class="col-sm-4">
												<label class="col-md-12">Indirizzo:</label>
											</div>
											<div class="col-sm-5">
												<input type="text" placeholder="<%=u.getIndirizzo()%>"
													class="form-control form-control-line" disabled>
											</div>
										</div>

										<div class="row">
											<div class="col-sm-4">
												<label class="col-md-12">Email:</label>
											</div>
											<div class="col-sm-5">
												<input type="text" placeholder="<%=u.getEmail()%>"
													class="form-control form-control-line" disabled>
											</div>
										</div>

										<div class="row">
											<div class="col-sm-4">
												<label class="col-md-12">Telefono:</label>
											</div>
											<div class="col-sm-5">
												<input type="text" placeholder="<%=u.getTelefono()%>"
													class="form-control form-control-line" disabled>
											</div>
										</div>

										<div class="row">
											<div class="col-sm-4">
												<label class="col-md-12">Sito:</label>
											</div>
											<div class="col-sm-5">
												<input type="text" placeholder="<%=p.getLinkSito()%>"
													class="form-control form-control-line" disabled>
											</div>
										</div>

										<div class="row">
											<div class="col-sm-4">
												<label class="col-md-12">Ufficio:</label>
											</div>
											<div class="col-sm-5">
												<input type="text" placeholder="<%=p.getUfficio()%>"
													class="form-control form-control-line" disabled>
											</div>
										</div>

										<div class="row">
											<div class="col-sm-4">
												<label class="col-md-12">Ricevimento:</label>
											</div>
											<div class="col-sm-5">
												<input type="text"
													placeholder="<%=p.getGiorniDiRicevimento()%>"
													class="form-control form-control-line" disabled>
											</div>
										</div>

										<div class="row">
											<div class="col-sm-4"></div>
											<div class="col-sm-2"></div>
											<div class="col-sm-4">
												<br>
												<a href="/Libra/modificaProfilo.jsp"><button
														class="btn btn-success" onclick=>Modifica Profilo</button></a>
											</div>
										</div>
										<br>
									</div>
									<%
										}
									%>

									<!--SEGRETERIA-->
									<%
										ISegreteriaDao segreteriaDao = (ISegreteriaDao) new InitialContext().lookup("java:app/Libra/SegreteriaJpa");
										Segreteria seg = segreteriaDao.findById(Segreteria.class, email);

										if (ruolo.equals("Segreteria")) {
									%>

									<div class="col-sm-8">
										<div class="card wild-card"
											style="color: black; font-size: 120%;">

											<div class="row">
												<div class="col-sm-4">
													<label class="col-md-12">Giorni di Ricevimento:</label>
												</div>
												<br>
												<div class="col-sm-5">
													<input type="text"
														placeholder="<%=seg.getGiorniDiRicevimento()%>"
														class="form-control form-control-line" disabled>
												</div>
											</div>

											<br>
											<h3 class="box-title m-b-0">Contatti:</h3>
											<br>

											<div class="row">
												<div class="col-sm-4">
													<label class="col-md-12">Indirizzo:</label>
												</div>

												<div class="col-sm-5">
													<input type="text" placeholder="<%=u.getIndirizzo()%>"
														class="form-control form-control-line" disabled>
												</div>
											</div>

											<div class="row">
												<div class="col-sm-4">
													<label class="col-md-12">Email:</label>
												</div>

												<div class="col-sm-5">
													<input type="text" placeholder="<%=u.getEmail()%>"
														class="form-control form-control-line" disabled>
												</div>
											</div>

											<div class="row">
												<div class="col-sm-4">
													<label class="col-md-12">Telefono:</label>
												</div>

												<div class="col-sm-5">
													<input type="text" placeholder="<%=u.getTelefono()%>"
														class="form-control form-control-line" disabled>
												</div>
											</div>

											<div class="row">
												<div class="col-sm-4"></div>
												<div class="col-sm-2"></div>
												<div class="col-sm-4">
													<br>
													<a href="/Libra/modificaProfilo.jsp"><button
															class="btn btn-success" onclick=>Modifica
															Profilo</button></a>
												</div>
											</div>
											<br>
										</div>
									</div>
									<%
										}
									%>

									<!--AZIENDA-->
									<%
										IAziendaDao aziendaDao = (IAziendaDao) new InitialContext().lookup("java:app/Libra/AziendaJpa");
										Azienda a = aziendaDao.findById(Azienda.class, email);

										if (ruolo.equals("Azienda")) {
									%>
									<div class="col-sm-8">
										<div class="card wild-card"
											style="color: black; font-size: 120%;">

											<br>
											<h3 class="box-title m-b-0">Contatti:</h3>
											<br>

											<div class="row">
												<div class="col-sm-4">
													<label class="col-md-12">Sede:</label>
												</div>

												<div class="col-sm-5">
													<input type="text" placeholder="<%=a.getSede()%>"
														class="form-control form-control-line" disabled>
												</div>
											</div>

											<div class="row">
												<div class="col-sm-4">
													<label class="col-md-12">Telefono:</label>
												</div>

												<div class="col-sm-5">
													<input type="text" placeholder="<%=u.getTelefono()%>"
														class="form-control form-control-line" disabled>
												</div>
											</div>

											<div class="row">
												<div class="col-sm-4"></div>
												<div class="col-sm-2"></div>
												<div class="col-sm-4">
													<br>
													<a href="/Libra/modificaProfilo.jsp"><button
															class="btn btn-success" onclick=>Modifica
															Profilo</button></a>
												</div>
											</div>
											<br>

										</div>
									</div>

									<%
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
				<script
					src="assets/plugins/sticky-kit-master/dist/sticky-kit.min.js"></script>
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


