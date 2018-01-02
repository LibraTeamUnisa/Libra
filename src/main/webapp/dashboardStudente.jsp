<%@page import="java.util.HashMap"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.naming.Context"%>
<%@page import="java.text.SimpleDateFormat"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@page import="it.unisa.libra.model.dao.IProgettoFormativoDao"%>
<%@page import="it.unisa.libra.model.dao.IStudenteDao"%>
<%@page import="it.unisa.libra.model.dao.IFeedbackDao"%>
<%@page import="it.unisa.libra.model.dao.IUtenteDao"%>
<%@page import="it.unisa.libra.bean.ProgettoFormativo"%>
<%@page import="it.unisa.libra.bean.Studente"%>
<%@page import="it.unisa.libra.bean.Feedback"%>
<%@page import="it.unisa.libra.bean.Azienda"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>

<%
	String var; /* stringa utilizzata per il controllo dello stato della proposta di progetto formativo */
	int stato = -1;
	Boolean flag = false;
	
	SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");
	
	IFeedbackDao feedbackDAO = (IFeedbackDao) new InitialContext().lookup("java:app/Libra/FeedbackJpa");
	IStudenteDao studenteDAO = (IStudenteDao) new InitialContext().lookup("java:app/Libra/StudenteJpa");
	IProgettoFormativoDao progettoFormativoDAO = (IProgettoFormativoDao) new InitialContext().lookup("java:app/Libra/ProgettoFormativoJpa");
	IUtenteDao utenteDAO= (IUtenteDao) new InitialContext().lookup("java:app/Libra/UtenteJpa");
	
	Studente studente = studenteDAO.findById(Studente.class,(String)session.getAttribute("utenteEmail")); 
	
	/* carico dal database l'ultima proposta caricata dallo studente */
	ProgettoFormativo lastProgettoFormativo = progettoFormativoDAO.getLastProgettoFormativoByStudente(studente);
	
	/* stato dell'ultima proposta caricata dallo studente */
	if(lastProgettoFormativo != null) stato = lastProgettoFormativo.getStato();	
	
	/* lista contenente tutte le proposte di progetto formativo */
	List<ProgettoFormativo> listaProposte = progettoFormativoDAO.findAll(ProgettoFormativo.class);
	List<ProgettoFormativo> listaProposteStudente = new ArrayList<ProgettoFormativo>();
	List<Feedback> listaFeedback = feedbackDAO.findAll(Feedback.class);
	
	/*contollo tutti i pf relativi allo studente*/
	for(ProgettoFormativo pf: listaProposte) {
		String mail = pf.getStudente().getUtenteEmail();
		if(mail != null) {
			if(mail.contains(studente.getUtenteEmail()))
			{
				listaProposteStudente.add(pf);
				
			}
		}
	}
%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" type="image/png" sizes="16x16"
	href="assets/images/favicon.png">
<title>Libra</title>
<link href="assets/plugins/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<link href="assets/plugins/chartist-js/dist/chartist.min.css"
	rel="stylesheet">
<link href="assets/plugins/chartist-js/dist/chartist-init.css"
	rel="stylesheet">
<link
	href="assets/plugins/chartist-plugin-tooltip-master/dist/chartist-plugin-tooltip.css"
	rel="stylesheet">
<link href="assets/plugins/css-chart/css-chart.css" rel="stylesheet">
<link href="css/style.css" rel="stylesheet">
<link href="css/colors/red.css" id="theme" rel="stylesheet">
<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
<script
	src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
</head>

<body class="fix-header fix-sidebar card-no-border">
	<div class="preloader">
		<svg class="circular" viewBox="25 25 50 50">
            <circle class="path" cx="50" cy="50" r="20" fill="none"
				stroke-width="2" stroke-miterlimit="10" /> </svg>
	</div>
	<div id="main-wrapper">
		<%@ include file="header.jsp"%>
		<%@ include file="menu.jsp"%>
		<div class="page-wrapper">
			<div class="container-fluid">
				<div class="row page-titles">
					<div class="col-md-6 col-8 align-self-center">
						<h3 class="text-themecolor m-b-0 m-t-0">Home</h3>
						<ol class="breadcrumb">
							<li class="breadcrumb-item"><a href="index.jsp">Home</a></li>
						</ol>
					</div>
				</div>

				
					<div class="card wizard-card" style="padding: 1%">
						<div class="table-responsive">
							<h4 class="card-title">Stato Progetto Formativo</h4>
							<table class="table">
								<thead>
									<tr>
										<% if(stato == -1) var = "green"; 
							      	 else var = "grey"; 
							      %>
										<th scope="col"><p align="center">
												<i class="fa fa-circle"
													style="font-size:30px;color:<%= var %>;"></i>
											</p></th>

										<% if(stato == 2) var = "green"; 
							      	 else var = "grey"; 
							      %>
										<th scope="col"><p align="center">
												<i class="fa fa-circle"
													style="font-size:30px;color:<%= var %>;"></i>
											</p></th>

										<% if(stato == 3) var = "green"; 
							      	 else var = "grey"; 
							      %>
										<th scope="col"><p align="center">
												<i class="fa fa-circle"
													style="font-size:30px;color:<%= var %>;"></i>
											</p></th>

										<% if(stato == 5) var = "green"; 
							      	 else var = "grey"; 
							      %>
										<th scope="col"><p align="center">
												<i class="fa fa-circle"
													style="font-size:30px;color:<%= var %>;"></i>
											</p></th>

										<% if(stato == 4) var = "green"; 
							      	 else var = "grey"; 
							      %>
										<th scope="col"><p align="center">
												<i class="fa fa-circle"
													style="font-size:30px;color:<%= var %>"></i>
											</p></th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td align="center">Disponibile</td>
										<td align="center">In Attesa</td>
										<td align="center">Verificato</td>
										<td align="center">Rifiutato</td>
										<td align="center">Approvato</td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>

					<div class="card wizard-card" style="padding: 1%">
						<h4 class="card-title">Valutazioni</h4>
						<table class="table table-responsive">
							<tbody>
								<tr>
									<% 
                    		for(ProgettoFormativo pf: listaProposteStudente) {
                    			List<Feedback> feedbackRicevuti= feedbackDAO.findByType(pf.getId(), "Azienda");
                    			List<Feedback> feedbackInviati= feedbackDAO.findByType(pf.getId(), "Studente");
                   		%>
								</tr>
								<tr>
									<td>
										
										<% 
											if(!feedbackRicevuti.isEmpty()) {
										   		flag=true;
										%>
												<p align="center">
													<a href="visualizzaValutazione.jsp?type=Azienda&idPF=<%=pf.getId()%>">
														<i class="fa fa-file-pdf-o" style="font-size: 48px;"></i>
													</a>
												</p>
												<p>Valutazione da:<%= pf.getAzienda().getNome() %></p> 
												 
												
										<% 
											}
										   
											if(!feedbackInviati.isEmpty()) { 
												flag=true; 
										%>
												<p align="center">
													<a href="visualizzaValutazione.jsp?type=Studente&idPF=<%=pf.getId()%>">
														<i class="fa fa-file-pdf-o" style="font-size: 48px;"></i>
													</a>
												</p>
												<p>Valutazione per:<%= pf.getAzienda().getNome() %></p> 
												 
										<% 
											}
										%>
									</td>
						<%
                       		} 
                    	
                        	if(!flag) {
                        %>
								
								<tr>
									<td>Nessuna valutazione effettuata o ricevuta</td>
								</tr>
						<%
                        	}
                        	flag = false;
                        %>
							</tbody>
						</table>
					</div>

					<div class="card wizard-card" style="padding: 1%">
						<h4 class="card-title">Richieste Formative</h4>
						<table class="table table-responsive">
							<tbody>
								<tr>
						<% 
                        if(!listaProposteStudente.isEmpty()) { 
                    		for(ProgettoFormativo p : listaProposteStudente) {
                    				Azienda azienda = p.getAzienda();
                    				String dates = formatter.format(p.getDataInizio());
                    		
                    	%>
								</tr>
								<tr>
									<td>
										<form id="myForm" method="post">
											<p align="center">
												<a href="dettaglioPpf.jsp?id=<%=p.getId()%>"> <i
													class="fa fa-file-pdf-o" style="font-size: 48px;"></i></a>
											</p>
											<p><%= azienda.getNome() %></p>
											<p align="center"><%= dates %></p>
										</form>
									</td>
									<%
                        	}
                        }else{
                    %>
								
								<tr>
									<td>Al momento non hai ricevuto proposte di progetto
										formativo</td>
								</tr>
								<%
                        }
                    %>
							</tbody>
						</table>
					</div>

					<div class="card wizard-card" style="padding: 1%">
						<h4 class="card-title">Domande Caricate</h4>
						<table class="table table-responsive">
							<tbody>
								<tr>
									<% 
                        if(!listaProposteStudente.isEmpty()) { 
                    		for(ProgettoFormativo p : listaProposteStudente) {
                    				if(p.getTutorInterno() != null) {
            							flag = true;
                    					Azienda azienda = p.getAzienda();
                        				String dates = formatter.format(p.getDataInizio());
                        				%>
								</tr>
								<tr>
									<td>
										<form id="myForm2" method="post">
											<p align="center">
												<a href="dettaglioPpf.jsp?id=<%=p.getId()%>"> <i
													class="fa fa-file-pdf-o" style="font-size: 48px;"></i></a>
											</p>
											<p><%= azienda.getNome() %></p>
											<p align="center"><%= dates %></p>
										</form>
									</td>
									<%
                        				
                        				}
                    				}
                    			}  
                    		if(!flag) {
                        		%>
								
								<tr>
									<td>Al momento non hai caricato proposte di progetto
										formativo</td>
								</tr>
								<%
                        	}
                        	%>
							</tbody>
						</table>
						<a href="caricaPpf.jsp"><button class="btn btn-primary">Carica</button></a>
					</div>
			</div>
		</div>
		<%@ include file="footer.jsp"%>
	</div>
	<script src="assets/plugins/jquery/jquery.min.js"></script>
	<script src="assets/plugins/bootstrap/js/tether.min.js"></script>
	<script src="assets/plugins/bootstrap/js/bootstrap.min.js"></script>
	<script src="js/jquery.slimscroll.js"></script>
	<script src="js/waves.js"></script>
	<script src="js/sidebarmenu.js"></script>
	<script src="assets/plugins/sticky-kit-master/dist/sticky-kit.min.js"></script>
	<script src="js/custom.min.js"></script>
	<script src="assets/plugins/chartist-js/dist/chartist.min.js"></script>
	<script
		src="assets/plugins/chartist-plugin-tooltip-master/dist/chartist-plugin-tooltip.min.js"></script>
	<script src="assets/plugins/echarts/echarts-all.js"></script>
	<script src="js/dashboard5.js"></script>
	<script src="assets/plugins/styleswitcher/jQuery.style.switcher.js"></script>
</body>
</html>