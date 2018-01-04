<<<<<<< HEAD
<%@page import="java.util.HashMap"%>
	<%@page import="javax.naming.InitialContext"%>
	<%@page import="javax.naming.Context"%>
	<%@page import="java.text.SimpleDateFormat"%>
	
	<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	    pageEncoding="ISO-8859-1"%>
	
	<%@ page import="it.unisa.libra.bean.Studente, it.unisa.libra.bean.ProgettoFormativo" %> 
	
	
	<%@page import="it.unisa.libra.model.dao.IProgettoFormativoDao"%>
	<%@page import="it.unisa.libra.model.dao.IStudenteDao"%>
	<%@page import="it.unisa.libra.model.dao.IFeedbackDao"%>
	<%@page import="it.unisa.libra.model.dao.IGruppoDao"%>
	<%@page import="it.unisa.libra.model.dao.IUtenteDao"%>
	<%@page import="it.unisa.libra.bean.ProgettoFormativo"%>
	<%@page import="it.unisa.libra.bean.Studente"%>
	<%@page import="it.unisa.libra.bean.Feedback"%>
	<%@page import="it.unisa.libra.bean.Azienda"%>
	<%@page import="it.unisa.libra.bean.Permesso"%>
	<%@page import="it.unisa.libra.bean.Gruppo"%>
	<%@page import="java.util.List"%>
	<%@page import="java.util.ArrayList"%>
	
	
	<%
		String var; /* stringa utilizzata per il controllo dello stato della proposta di progetto formativo */
		int statos = -1;
		int i = 0;
		Boolean flag = false;
		
		HttpSession sessione = request.getSession();
		String utenteEmail = (String) sessione.getAttribute("utenteEmail");
		String utenteRuolo = (String) sessione.getAttribute("utenteRuolo");
		
		IGruppoDao gruppoDAO = (IGruppoDao) new InitialContext().lookup("java:app/Libra/GruppoJpa");
		IFeedbackDao feedbackDAO = (IFeedbackDao) new InitialContext().lookup("java:app/Libra/FeedbackJpa");
		IStudenteDao studenteDAO = (IStudenteDao) new InitialContext().lookup("java:app/Libra/StudenteJpa");
		IProgettoFormativoDao progettoFormativoDAO = (IProgettoFormativoDao) new InitialContext().lookup("java:app/Libra/ProgettoFormativoJpa");
		IUtenteDao utenteDAO= (IUtenteDao) new InitialContext().lookup("java:app/Libra/UtenteJpa");
		
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



	
	<% 
	Studente s = (Studente) request.getAttribute("studente");
	ProgettoFormativo pf = (ProgettoFormativo) request.getAttribute("progettoFormativo");
	
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
	    <link rel="icon" type="image/png" sizes="16x16" href="assets/images/favicon.png">
	<title>Libra</title>
	    <!-- Bootstrap Core CSS -->
	    <link href="assets/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet">
	    <link href="assets/plugins/bootstrap-select/bootstrap-select.min.css" rel="stylesheet" />
	    <!--alerts CSS -->
	    <link href="assets/plugins/sweetalert/sweetalert.css" rel="stylesheet" type="text/css">
	        
	    <!-- chartist CSS -->
	    <link href="assets/plugins/chartist-js/dist/chartist.min.css" rel="stylesheet">
	    <link href="assets/plugins/chartist-js/dist/chartist-init.css" rel="stylesheet">
	    <link href="assets/plugins/chartist-plugin-tooltip-master/dist/chartist-plugin-tooltip.css" rel="stylesheet">
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
	            <circle class="path" cx="50" cy="50" r="20" fill="none" stroke-width="2" stroke-miterlimit="10" /> </svg>
	    </div>
	    <!-- ============================================================== -->
	    <!-- Main wrapper - style you can find in pages.scss -->
	    <!-- ============================================================== -->
	    <div id="main-wrapper">
	        <!-- ============================================================== -->
	        <!-- Topbar header - style you can find in pages.scss -->
	        <!-- ============================================================== -->
	       	<%@ include file="header.jsp" %>
	        <!-- ============================================================== -->
	        <!-- End Topbar header -->
	        <!-- ============================================================== -->
	        <!-- ============================================================== -->
	        <!-- Left Sidebar - style you can find in sidebar.scss  -->
	        <!-- ============================================================== -->
	        <%@ include file="menu.jsp" %>
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
	                    <div class="col-md-12 align-self-center">
	                        <h3 class="text-themecolor m-b-0 m-t-0">Dettaglio Studente</h3>
	                        <ol class="breadcrumb">
	                            <li class="breadcrumb-item"><a href="javascript:void(0)">Home</a></li>
	                            <li class="breadcrumb-item active">Dettaglio Studente</li>
	                        </ol>
	                    </div>
	                </div>
	            	<div class="row">
		             	<div class="col-md-4">
		                        <div class="card">
		                            <div class="card-block align-self-center">
		                                <div class="text-center"> 
			                                <img src="<%= s.getUtente().getImgProfilo() %>" class="img-circle" width="150">
			                                <br>
			                                <div class="card-block">
			                                    <h4 class="card-title"><%= s.getNome() %> <%= s.getCognome() %></h4>
			                                    <p class="card-text text-center">Stato <br> 
			                                    <% if (pf != null) {
			                                    	int stato = pf.getStato();
			                                     	if (stato == 0 || stato == 1 || stato == 2 || stato == 3) { %>
			                                    <button type="button" class="btn btn-rounded btn-sm btn-warning">In attesa</button></p>
			                                    <% } else if (stato == 4) { %>
			                                    <button type="button" class="btn btn-rounded btn-sm btn-primary">Verificato</button></p>
			                                    <% } else if (stato == 5) { %>
			                                    <button type="button" class="btn btn-rounded btn-sm btn-success">Approvato</button></p>
			                                   	<% } else if (stato == 6) { %>
			                                    <button type="button" class="btn btn-rounded btn-sm btn-danger">Rifiutato</button></p>
	                               				<% } 
	                               				} else {%>
			                                    <button type="button" class="btn btn-rounded btn-sm btn-info">Disponibile</button></p>
												<% } %>
	                               			</div>
	                               		</div>
		                            </div>
		                        </div>
		                    </div>
		                    <div class="col-md-8">
		                        <div class="card">
		                            <div class="card-block">
		                                <h3 class="card-title">Dati personali</h3>
		                                <p class="card-text">
		                                	<strong>Nome:</strong><span class="text-muted"> <%= s.getNome() %></span> <br>
		                                	<strong>Cognome:</strong><span class="text-muted"> <%= s.getCognome() %></span> <br>
		                                	<strong>Data di nascita:</strong><span class="text-muted"> <%= s.getDataDiNascita() %></span> <br>
		                                	<strong>Matricola:</strong><span class="text-muted"> <%= s.getMatricola() %></span>
		                                </p>
		                                <h3 class="card-title">Contatti</h3>
		                                <p class="card-text">
		                                	<strong>Indirizzo:</strong><span class="text-muted"> <%= s.getUtente().getIndirizzo() %></span> <br>
		                                	<strong>E-mail:</strong><span class="text-muted"> <%= s.getUtente().getEmail() %></span> <br>
		                                	<strong>Telefono:</strong><span class="text-muted">  <%= s.getUtente().getTelefono() %></span> 
		                                </p>
		                            </div>
		                        </div>
		                    </div>
		                </div>
		                <div class="row">
		                	<div class="col-md-12">
		                		<div class="card">
		                			<div class="card-block">
		                				<h3 class="card-title">Proposta di Progetto Formativo</h3>
		                				<% if (pf != null) { %>
		                				<div class="row card-block">
			                				<div class="col-md-2">
				                				<p class="card-text text-center">
				                					<i class="fa fa-file-pdf-o" style="font-size:6em"></i>
				                				</p>
			                				</div>
			                				<div class="col-md-9">
			                					<p class="card-text">
			                						<span class="text-muted">Inviata il <%= pf.getDataInvio() %></span><br>
			                						<strong>Azienda:</strong><span class="text-muted"> <%= pf.getAzienda().getNome() %> </span><br>
			                						<strong>Note:</strong><span class="text-muted"> <%= pf.getNote() %> </span><br>
			                					</p>
			                				</div>
			                			</div>
			                			<% if (request.getSession().getAttribute("utenteRuolo").equals("Segreteria")) { %>
		                				<div class="row card-block">
			                				<div class="col-md-3">
			                					<button type="button" class="btn btn-outline-success"><i class="fa fa-check"></i> Approva</button>
			                				</div>
			                				<div class="col-md-7">
			                					<div class="form-group row">
			                						<label class="text-muted text-right align-self-center control-label col-md-3">Stato corrente: </label>
			                						<div class="col-md-5">
				                						 <select class="selectpicker" data-style="form-control btn-secondary" id="select-stato">
				                                            <option name="select-stato" <% if (pf == null) { %> selected <% } %> value="-1">Disponibile</option>
				                                            <option name="select-stato" <% if (pf.getStato() == 0) { %> selected <% } %> value="0">Richiesto</option>
				                                            <option name="select-stato" <% if (pf.getStato() == 1) { %> selected <% } %> value="1">Inviato allo studente</option>
				                                            <option name="select-stato" <% if (pf.getStato() == 2) { %> selected <% } %> value="2">In verifica al Tutor Interno</option>
				                                            <option name="select-stato" <% if (pf.getStato() == 3) { %> selected <% } %> value="3">In verifica al Presidente</option>
				                                            <option name="select-stato" <% if (pf.getStato() == 4) { %> selected <% } %> value="4">Verificato</option>
				                                            <option name="select-stato" <% if (pf.getStato() == 5) { %> selected <% } %> value="5">Approvato</option>
				                                            <option name="select-stato" <% if (pf.getStato() == 6) { %> selected <% } %> value="6">Rifiutato</option>
			                                       		 </select>
			                                       		 <input type="hidden" id="stato" value="<%= pf.getStato() %>">
			                                       		 <input type="hidden" name="pfId" id="pfId" value="<%= pf.getId() %>">		                                       		 
		                                       		 </div>
			                					</div>
			                				</div>
			                				<div class="col-md-2">
			                           			<button type="button" class="btn btn-outline-primary" id="sa-warning-tirocinio"> Conferma</button>		                				
			                				</div>
		                				</div>
		                				<% } else { %>
		                				<div class="row card-block">
			                				<div class="col-md-4">
			                					<button type="button" class="btn btn-outline-success"><i class="fa fa-check"></i> Invia</button>
			                					<button type="button" class="btn btn-outline-danger"><i class="fa fa-close"></i> Rifiuta</button>
			                				</div>
			                				<div class="col-md-8">
			                				</div>
			                			</div>
		                				<% } %>
		                			</div>
		                			<% } else { %>
		                				<div class="row card-block">
		                					<p class="card-text">
		                						Nessuna proposta di Progetto Formativo.
		                					</p>
		                				</div>
		                			<% } %>
		                		</div>
		                	</div>
		                </div>
		             </div>
	            </div>
	            
	            <%
	            
	            boolean control = false;
	            String ruolo = (String)session.getAttribute("utenteRuolo");
	            Gruppo gruppo = gruppoDAO.findById(Gruppo.class, ruolo);
	            if(gruppo != null){
	            	
	            	List<Permesso> listaPermessi = gruppo.getPermessi();
	            	for(Permesso p : listaPermessi){
	            		
	            		if(p.getTipo().contains("ricevuti")) {
	            			
	            			control = true;
	            		}
	            		
	            	}
	            }
	            
	            %>
		
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
												if(!feedbackRicevuti.isEmpty() && (control)) {
											   		flag=true;
											%>
													<p align="center">
														<a href="visualizzaValutazione.jsp?type=Azienda&idPF=<%=pf1.getId()%>">
															<i class="fa fa-file-pdf-o" style="font-size: 48px;"></i>
														</a>
													</p>
													<p>Valutazione da:<%= pf1.getAzienda().getNome() %></p> 
													 
													
											
										</td>
							<%
									i++;
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
	            <%@ include file="footer.jsp" %>
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
	    <script src="assets/plugins/bootstrap-select/bootstrap-select.min.js" type="text/javascript"></script>  
	    <!-- ============================================================== -->
	    <!-- Sweet-Alert  -->
	    <script src="assets/plugins/sweetalert/sweetalert.min.js"></script>
	    <script src="assets/plugins/sweetalert/jquery.sweet-alert.custom.js"></script>
	    <!-- ============================================================== -->    
	    <!-- chartist chart -->
	    <script src="assets/plugins/chartist-js/dist/chartist.min.js"></script>
	    <script src="assets/plugins/chartist-plugin-tooltip-master/dist/chartist-plugin-tooltip.min.js"></script>
	    <!-- Chart JS -->
	    <script src="assets/plugins/echarts/echarts-all.js"></script>
	    <script src="js/dashboard5.js"></script>
	    <!-- ============================================================== -->
	    <!-- Style switcher -->
	    <!-- ============================================================== -->
	    <script src="assets/plugins/styleswitcher/jQuery.style.switcher.js"></script>
	</body>
	
	</html>
