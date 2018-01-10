<%@page import="it.unisa.libra.util.StatoPf"%>
<%@page import="it.unisa.libra.model.dao.ITutorEsternoDao"%>
<%@page import="it.unisa.libra.bean.Azienda"%>
<%@page import="it.unisa.libra.model.dao.IAziendaDao"%>
<%@page import="it.unisa.libra.model.dao.IStudenteDao"%>
<%@page import="it.unisa.libra.model.dao.IProgettoFormativoDao"%>
<%@page import="it.unisa.libra.bean.ProgettoFormativo"%>
<%@page import="it.unisa.libra.util.JspPagesIndex, it.unisa.libra.bean.Studente, java.util.List, java.util.ArrayList"%>
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
    <link rel="icon" type="image/png" sizes="16x16" href="assets/images/favicon.png">
<title>Libra</title>
    <!-- Bootstrap Core CSS -->
    <link href="assets/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet">
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
            
         <%            
            // recupero i dao
           	IAziendaDao aziendaDao = (IAziendaDao) new InitialContext().lookup("java:app/Libra/AziendaJpa");
            IProgettoFormativoDao pfDao = (IProgettoFormativoDao) new InitialContext().lookup("java:app/Libra/ProgettoFormativoJpa");
            IStudenteDao studenteDao = (IStudenteDao) new InitialContext().lookup("java:app/Libra/StudenteJpa");
            ITutorEsternoDao tutorDao = (ITutorEsternoDao) new InitialContext().lookup("java:app/Libra/TutorEsternoJpa");
            
            // azienda loggata
            String aziendaEmail = (String) request.getSession().getAttribute("utenteEmail");
            Azienda azienda = aziendaDao.findById(Azienda.class, aziendaEmail);
            
            // totale studenti che hanno stipulato rapporti con l'azienda 
            long numIscritti = studenteDao.countByAzienda(azienda);
            
            // totale progetti formativi completati con successo e approvati dalla segreteria
            long numCompletati = pfDao.countByAziendaAndStato(azienda, StatoPf.APPROVATO);
            
            // totale valutazioni espresse tramite il rilascio di feedback
            long numValutazioni = pfDao.countValutatiByAzienda(azienda);
            
           	// tutor esterni dell'azienda
            long numTutor = tutorDao.countByEmailAzienda(aziendaEmail);
            
            // per la parte sinistra: studenti che hanno richiesto un pf
            int pfRichiestiMax = 5;
            List<ProgettoFormativo> pfRichiesti = pfDao.findByAziendaAndStato(azienda, StatoPf.RICHIESTO);
            
        	// per la parte destra: pf inviati
            int pfInviatiMax = 3;            
            // tutti tranne StatoPf.RICHIESTO
            int[] statiDaRicercare = {StatoPf.INVIATO, StatoPf.VERIFICA_TUTOR, StatoPf.VERIFICA_PRESIDENTE, StatoPf.VERIFICATO, StatoPf.APPROVATO, StatoPf.RIFIUTATO};
            List<ProgettoFormativo> pfInviati = pfDao.findByAziendaAndStato(azienda, statiDaRicercare);
            
            // link 
            String linkAggiungiTutor = JspPagesIndex.GESTIONE_TUTOR_ESTERNO.substring(1)+"?action="+Actions.AGGIUNGI_TUTOR_ESTERNO;
            String linkModificaTutor = JspPagesIndex.CATALOGO_TUTOR_ESTERNI.substring(1);
            String linkRimuoviTutor = JspPagesIndex.CATALOGO_TUTOR_ESTERNI.substring(1);
            String linkPfInviati = (JspPagesIndex.PF_INVIATI).substring(1);
            String linkCreaPpf = (JspPagesIndex.CARICA_PPF).substring(1);
            %>
            
            
           	<!-- ============================================================== -->
            <!-- Breadcrumb  -->
            <!-- ============================================================== -->
    			<div class="row page-titles">
                    <div class="col-md-6 col-8 align-self-center">
                        <h3 class="text-themecolor m-b-0 m-t-0">Dashboard</h3>
                    </div>
                </div>
            <!-- ============================================================== -->
            <!-- End Breadcrumb -->
            <!-- ============================================================== -->
            
            
            <!-- ============================================================== -->
            <!-- Quattro card informative  -->
            <!-- ============================================================== -->
           <div class="row">
                    <!-- Totale studenti iscritti -->
                    <div class="col-lg-3 col-md-6">
                    	<div class="card card-inverse card-info">
                            <div class="card-block">
                            	<h4 class="card-title text-white">Studenti iscritti</h4>
                            	<h5 class="card-subtitle text-white">dalla stipula della convenzione</h5>
                            	<br/>
                            	<div>
                            	<h2 class="font-light m-b-0 text-white pull-right"><%=numIscritti%></h2>
                            	<h1><i class="fa fa-handshake-o text-white"></i></h1>
                                </div>
                             </div>
                    	</div>
                    </div>
                    <!-- End Totale studenti iscritti -->
                    <!-- Studenti che hanno compeltato con successo -->
                    <div class="col-lg-3 col-md-6">
                        <div class="card card-inverse card-success">
                            <div class="card-block">
                                <h4 class="card-title">Progetti Formativi completati</h4>
                                <h5 class="card-subtitle">e approvati da UniSa</h5>
                                <br/>
                                <div>
                                    <h2 class="font-light m-b-0 text-white pull-right"><%=numCompletati%></h2>
                                    <h1><i class="fa fa-check-circle text-white"></i></h1>
                                </div>
                             </div>
                        </div>
                    </div>
                    <!-- End Studenti che hanno completato con successo -->
                    <!-- Studenti valutati -->
                    <div class="col-lg-3 col-md-6">
                        <div class="card card-inverse card-warning">
                            <div class="card-block">
                                <h4 class="card-title">Valutazioni espresse</h4>
                                <h5 class="card-subtitle">tramite il rilascio di feedback</h5>
                                <br/>
                                <div>
                                    <h2 class="font-light m-b-0 text-white pull-right"><%=numValutazioni%></h2>
                                    <h1><i class="fa fa-star-half-o text-white"></i></h1>
                                </div>
                             </div>
                        </div>
                    </div>
                    <!-- End Studenti valutati -->
                    <!-- Numero tutor disponibili -->
                    <div class="col-lg-3 col-md-6">
                        <div class="card card-inverse card-danger">
                            <div class="card-block">
                                <h4 class="card-title">Tutor</h4>
                                <h5 class="card-subtitle">disponibili per Progetti Formativi</h5>
                                <br/>
                                <div>
                                    <h2 class="font-light m-b-0 text-white pull-right"><%=numTutor%></h2>
                                    <h1><i class="fa fa-suitcase text-white"></i></h1>
                                </div>
                             </div>
                        </div>
                    </div>
                    <!-- End Numero tutor disponibili -->
                </div>
            <!-- ============================================================== -->
            <!-- End quattro card informative  -->
            <!-- ============================================================== -->
           

            <!-- ============================================================== -->
            <!-- Corpo della dashboard  -->
            <!-- ============================================================== --> 
             <div class="row">
             
             
             	 <!-- col sinistra -->
                 <div class="col-md-6 col-sm-12 p-20">
                     <h4 class="card-title text-center">Iscrizioni</h4>
                     	<div class="list-group">
                     	<% if (pfRichiesti.isEmpty()) { %>
	                     	<div class="list-group-item">
	                     	<p>Non ci sono nuovi iscritti</p>
	                     	</div>
	                     	</div>
                     	<% } else { %>
                       	<%		for (int i = 0; i < pfRichiestiMax && i < pfRichiesti.size(); i++) { %>
		                <%			ProgettoFormativo pf = pfRichiesti.get(i); 						%>
                     	<%			Studente s = pf.getStudente();	  			 					%>
		                            <div class="row list-group-item">
		                                <!-- immagine, nome, cognome, matricola -->
		                                <div class="col-4 col-md-4 col-sm-4">
		                                <img src="<%=s.getUtente().getImgProfilo()%>" onerror="this.src='assets/images/users/default.png';" alt="user" class="img-circle img-responsive">
		                                </div>
		                                <div class="col-8 col-md-8 col-sm-8">
		                                <h5><%=s.getNome()%> <%=s.getCognome()%></h5>
		                                <h6 class="text-muted">matricola <%=s.getMatricola()%></h6>
		                                </div>
		                            </div>
                            <% } %>
                            	</div>
                            	<!-- end list group -->
                            	
                            	<div class="row">
		                     	<div class="col-md-12 col-sm-12" style="padding-right:0px;">
			                     	<a class="btn btn-warning pull-right" href="<%=linkCreaPpf%>">
			                     		Invia proposta...
			                     	</a>
		                     	</div>
		                     	</div>
                         <% } %>
                 </div>
                 <!--  end col sinistra -->
                 
                 
                 <!-- col destra -->
                 <div class="col-md-6 col-sm-12 p-20">
                     
                     
                     <!-- pf inviati -->
                     <div class="row">
                     	<div class="col-md-12 col-sm-12 p-20" style="padding-top: 0px; padding-bottom: 0px;">
                     	 <h4 class="card-title text-center">Progetti Formativi</h4>
		                     <div class="list-group">
		                     <% if (pfInviati.isEmpty()) { %>
			                     	<div class="list-group-item">
			                     	<p>Non ci sono progetti formativi</p>
			                     	</div>
			                     	</div>
			                     	<!-- end list group -->
		                     	<% } else { %>
		                     	<%		for (int i = 0; i < pfInviatiMax && i < pfInviati.size(); i++) { %>
		                     	<%			ProgettoFormativo pf = pfInviati.get(i); %>
		                     	<%			Studente s = pf.getStudente(); %>
				                            <div class="row list-group-item">
				                                <!-- immagine, nome, cognome, matricola, ambito e stato progetto -->
				                                <div class="col-4 col-md-4 col-sm-4">
				                                <img src="<%=s.getUtente().getImgProfilo()%>" onerror="this.src='assets/images/users/default.png';" alt="user" class="img-circle img-responsive">
				                                </div>
				                                <div class="col-8 col-md-8 col-sm-8">
				                                <!-- icona diversa a seconda dello stato del progetto -->
				                                <% int stato = pf.getStato(); %>
				                                <% if (stato == StatoPf.INVIATO || stato == StatoPf.VERIFICA_TUTOR || stato == StatoPf.VERIFICA_PRESIDENTE) { %>	
				                                		<h1><i class="fa fa-clock-o text-info pull-right" title="In attesa"></i></h1>
				                                <% } else if (stato == StatoPf.VERIFICATO) { %>
				                                		<h1><i class="fa fa-thumbs-o-up text-info pull-right" title="Verificato"></i></h1>
				                                <% } else if (stato == StatoPf.APPROVATO) { %>
				                                		<h1><i class="fa fa-check-circle text-success pull-right" title="Approvato"></i></h1>
				                                <% } else if (stato == StatoPf.RIFIUTATO) { %>
				                                		<h1><i class="fa fa-times text-danger pull-right" title="Rifiutato"></i></h1>
				                                <% } %>
				                                <h5><%=s.getNome()%> <%=s.getCognome()%></h5>
				                                <h6 class="text-muted">matricola <%=s.getMatricola()%></h6>
				                                <h6 class="label label-warning"><%=pf.getAmbito()%></h6>
				                            	</div>
				                            </div>
		                        <% 		} %>
		                        		</div>
		                        		<!-- end list group -->
		                        		
				                     	<div class="row">
				                     	<div class="col-md-12 col-sm-12" style="padding-right:0px;">
					                     	<a class="btn btn-warning pull-right" href="<%=linkPfInviati%>">
					                     		Mostra tutto
					                     	</a>
				                     	</div>
				                     	</div>
		                         <% } %>
                     	</div>
                 	</div>
                	<!-- end pf inviati -->
                 
                 
                 <!-- gestione tutor -->
                 <div class="row">
                 <div class="col-md-12 col-sm-12 p-20">
                     <h4 class="card-title text-center">Gestione Tutor</h4>
                     <div class="list-group">
                     	<div class="row list-group-item" style="border-bottom: 0px;">
                     	<div class="col-6 col-md-6 col-sm-6 text-center"><a href="<%=linkAggiungiTutor%>" class="btn btn-warning">Aggiungi</a></div>
                        <div class="col-6 col-md-6 col-sm-6 text-center"><a href="<%=linkModificaTutor%>" class="btn btn-warning">Modifica</a></div>
                        </div>
                        <div class="row list-group-item" style="border-top: 0px;">
                        <div class="col-12 col-md-12 col-sm-12 text-center"><a href="<%=linkRimuoviTutor%>" class="btn btn-warning">Rimuovi</a></div>
                    	</div>
                     </div>
                 </div>
                 </div>
                 <!-- end gestione tutor -->
                 
                 
                 </div>
                 <!--  end col destra-->
                 
             </div>
             <!-- ============================================================== -->
           	 <!-- End corpo della dashboard  -->
           	 <!-- ============================================================== -->  
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