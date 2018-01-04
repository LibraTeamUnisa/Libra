
<%@ page import="java.util.*" import="it.unisa.libra.bean.Azienda"
	import="it.unisa.libra.bean.Utente"
	import="it.unisa.libra.bean.TutorEsterno"
	import="it.unisa.libra.bean.ProgettoFormativo"
	import="it.unisa.libra.bean.Feedback"
	import="it.unisa.libra.bean.Studente"
	import="it.unisa.libra.model.dao.IAziendaDao"
	import="it.unisa.libra.model.dao.IFeedbackDao"
	import="it.unisa.libra.model.dao.IProgettoFormativoDao"
	import="it.unisa.libra.model.dao.ITutorEsternoDao"
	import="it.unisa.libra.model.dao.IStudenteDao" import="java.util.List"
	import="javax.naming.InitialContext"%>
<%
	HttpSession sessione = request.getSession();
	String utenteEmail = (String) sessione.getAttribute("utenteEmail");
	String utenteRuolo = (String) sessione.getAttribute("utenteRuolo");

	String nome = (String) request.getParameter("nome");
	//List<Feedback> feedback = new ArrayList<Feedback>();
	IAziendaDao aziendaDAO = (IAziendaDao) new InitialContext().lookup("java:app/Libra/AziendaJpa");
	ITutorEsternoDao tutorDAO = (ITutorEsternoDao) new InitialContext()
			.lookup("java:app/Libra/TutorEsternoJpa");
	IProgettoFormativoDao progettoFormativoDAO = (IProgettoFormativoDao) new InitialContext()
			.lookup("java:app/Libra/ProgettoFormativoJpa");
	IStudenteDao studenteDAO = (IStudenteDao) new InitialContext().lookup("java:app/Libra/StudenteJpa");
	IFeedbackDao feedbackDAO = (IFeedbackDao) new InitialContext().lookup("java:app/Libra/FeedbackJpa");
	Azienda a = aziendaDAO.findByName(nome);
	List<TutorEsterno> tutorEsterni = tutorDAO.findByAziendaNome(nome);
	List<ProgettoFormativo> progettiFormativi = progettoFormativoDAO.getProgettiFormativiByAzienda(nome);
	IUtenteDao utenteDAO= (IUtenteDao) new InitialContext().lookup("java:app/Libra/UtenteJpa");
	
	int statos = -1;
	int io = 0;
	Boolean flag = false;
	
	
	
	Studente studente = studenteDAO.findById(Studente.class,(String)sessione.getAttribute("utenteEmail")); 
	
	/* carico dal database l'ultima proposta caricata dallo studente */
	ProgettoFormativo lastProgettoFormativo = progettoFormativoDAO.getLastProgettoFormativoByStudente(studente);
	
	/* stato dell'ultima proposta caricata dallo studente */
	if(lastProgettoFormativo != null) statos = lastProgettoFormativo.getStato();	
	
	/* lista contenente tutte le proposte di progetto formativo */
	List<ProgettoFormativo> listaProposte = progettoFormativoDAO.findAll(ProgettoFormativo.class);
	List<ProgettoFormativo> listaProposteStudente = new ArrayList<ProgettoFormativo>();
	List<Feedback> listaFeedback = feedbackDAO.findAll(Feedback.class);
	
	/*contollo tutti i pf relativi allo studente*/
	for(ProgettoFormativo pf1: listaProposte) {
		String mail = pf1.getStudente().getUtenteEmail();
		if(mail != null) {
			if(mail.contains(studente.getUtenteEmail()))
			{
				listaProposteStudente.add(pf1);
				
			}
		}
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
				<%
					if (utenteRuolo.equals("Azienda")) {
				%>
				<div class="row page-titles">
					<div class="col-md-6 col-8 align-self-center">
						<h3 class="text-themecolor m-b-0 m-t-0">Pagina non
							disponibile per l'azienda</h3>
					</div>
				</div>
				<%
					} else {
				%>
				<!-- CONTROLLO SUL NOME DELL'AZIENDA -->
				<%
					if (a == null) {
				%>
				<div class="row page-titles">
					<div class="col-md-6 col-8 align-self-center">
						<h3 class="text-themecolor m-b-0 m-t-0">Azienda non trovata</h3>
					</div>
				</div>
				<%
					} else {
				%>
				<div class="row page-titles">
					<div class="col-md-6 col-8 align-self-center">
						<h3 class="text-themecolor m-b-0 m-t-0">Dettagli Azienda</h3>
						<ol class="breadcrumb">
							<li class="breadcrumb-item"><a href="index.jsp">Home</a></li>
							<li class="breadcrumb-item"><a href="catalogoAziende.jsp">Catalogo
									Aziende</a></li>
							<li class="breadcrumb-item active">Dettagli Azienda</li>
						</ol>
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
									<strong>Nome</strong> <span class="text-muted"> <%=a.getNome()%></span>
								</p>
								<p>
									<strong>Aree di interesse</strong>
									<%
										if (tutorEsterni.size() == 0) {
									%>
									<span class="text-muted"> Nessun ambito disponibile </span>
									<%
										} else {
													for (int i = 0; i < tutorEsterni.size(); i++) {
														if (i != 0) {
									%>
									<span class="text-muted"> - </span>
									<%
										}
									%>
									<span class="text-muted"><%=tutorEsterni.get(i).getId().getAmbito()%></span>
									<%
										}
												}
									%>
								</p>

								<p>
									<strong>Tutor esterni</strong>
									<%
										if (tutorEsterni.size() == 0) {
									%>
									<span class="text-muted"> Nessun tutor esterno
										disponibile</span>
									<%
										} else {
													for (int i = 0; i < tutorEsterni.size(); i++) {
														if (i != 0) {
									%>
									<span class="text-muted"> - </span>
									<%
										}
									%>
									<span class="text-muted"><%=tutorEsterni.get(i).getNome()%>
										<%=tutorEsterni.get(i).getCognome()%></span>
									<%
										}
												}
									%>
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
									<strong>Sede</strong> <span class="text-muted"><%=a.getUtente().getIndirizzo()%>,
										<%=a.getSede()%></span>
									<%
										
									%>
								</p>
								<div class="text-right">
									<!-- CONTROLLO SE E' UNO STUDENTE -->
									<%
										Studente s = studenteDAO.findById(Studente.class, utenteEmail);
												ProgettoFormativo progettoFormativo = progettoFormativoDAO.getLastProgettoFormativoByStudente(s);
										
												if (utenteRuolo.equals("Studente")) {
													//Controllo se lo studente ha gi� un progetto formativo attivo
													if (progettoFormativo == null || progettoFormativo.getStato() == 5
															|| progettoFormativo.getStato() == 6) {
									%>
														<form method="post" id="formRichiediPF">
															<input type="hidden" id="aziendaName" name="aziendaName" value="<%=nome%>" >
															<button type="submit" class="btn btn-rounded btn-danger" id="iscrivitiButton" name="iscrivitiButton">Iscriviti</button>
														</form>
									<%
													}
													 else{
									%>
														 	<div id="errore-message" class="alert alert-danger" style="text-align: center">
													  		<strong>Attenzione!</strong><br> Non � possibile iscriversi all'azienda poich� risulta che lei abbia un progetto formativo attivo.  
															</div>
									<%
													 }
												}
									%>
									
														<div class="modal fade" id="dialogMsg" role="dialog">
															<div class="modal-dialog">
																<div class="modal-content" >
																	<div class="modal-body" style="text-align:center; color:black;">
																	  Iscrizione effettuata!
																	</div>
																	
																	<div class="modal-footer">
																		<a href="profiloAziendale.jsp?nome=<%=nome%>" class="btn btn-success" style="text-decoration:none; color:white;" id="okButton">
																		 Ok
																		</a>
																	</div>
																</div>
															</div>
														</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<%
					 }
					}
				%>

			</div>
			
			
			<div class="card wizard-card" style="padding: 1%">
							<h4 class="card-title">Valutazioni</h4>
							<table class="table table-responsive">
								<tbody>
									<tr>
										<% 
	                    		for(ProgettoFormativo pf1: listaProposteStudente) {
	                    			List<Feedback> feedbackRicevuti= feedbackDAO.findByType(pf1.getId(), "Azienda");
	                    			
	                   		%>
									</tr>
									<tr>
										<td>
											
											<% 
												if(!feedbackRicevuti.isEmpty()) {
											   		flag=true;
											%>
													<p align="center">
														<a href="visualizzaValutazione.jsp?type=Azienda&idPF=<%=pf1.getId()%>">
															<i class="fa fa-file-pdf-o" style="font-size: 48px;"></i>
														</a>
													</p>
													<p>Valutazione da:<%=pf1.getStudente().getNome() %></p> 
													 
													
											
										</td>
							<%
									io++;
	                       		} 
	                    	
	                        	if(!flag) {
	                        %>
									
									<tr>
										<td>Nessuna valutazione ricevuta</td>
									</tr>
							<%
	                        	}
	                        	flag = false;
	                        
	                    		}	
	                        %>
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
		$(document).ready(function() {
			$("#formRichiediPF").submit(function(e) {
				e.preventDefault();
				
				$.post('richiediPfServlet', {
					aziendaName : $("#aziendaName").val()
					}, 
				function(data) {
					if (data == "Iscrizione effettuata!") {
						$("#dialogMsg").modal({backdrop: 'static', keyboard: false});
					} else {
						$(".modal-body").html(data);
						$("#dialogMsg").modal({backdrop: 'static', keyboard: false});
					}
				});
			});
		})
	</script>
</body>
</html>

