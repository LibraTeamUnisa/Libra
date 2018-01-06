<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.naming.Context"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@page import="it.unisa.libra.model.dao.ITutorInternoDao"%>
<%@page import="it.unisa.libra.model.dao.IProgettoFormativoDao"%>
<%@page import="it.unisa.libra.model.dao.IStudenteDao"%>
<%@page import="it.unisa.libra.model.dao.IAziendaDao"%>
<%@page import="it.unisa.libra.model.dao.ITutorEsternoDao"%>
<%@page import="it.unisa.libra.model.dao.IUtenteDao"%>
<%@page import="it.unisa.libra.bean.TutorInterno"%>
<%@page import="it.unisa.libra.bean.Studente"%>
<%@page import="it.unisa.libra.bean.TutorEsterno"%>
<%@page import="it.unisa.libra.bean.ProgettoFormativo"%>
<%@page import="it.unisa.libra.bean.Azienda"%>

<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>

<%
	String mail = (String)session.getAttribute("utenteEmail");
	String ruolo = (String)session.getAttribute("utenteRuolo");

	IAziendaDao aziendaDAO = (IAziendaDao) new InitialContext().lookup("java:app/Libra/AziendaJpa");
	IProgettoFormativoDao progettoFormativoDAO = (IProgettoFormativoDao) new InitialContext().lookup("java:app/Libra/ProgettoFormativoJpa");
	ITutorInternoDao tutorInternoDAO = (ITutorInternoDao) new InitialContext().lookup("java:app/Libra/TutorInternoJpa");
	ITutorEsternoDao tutorEsternoDAO = (ITutorEsternoDao) new InitialContext().lookup("java:app/Libra/TutorEsternoJpa");
	IUtenteDao utenteDAO= (IUtenteDao) new InitialContext().lookup("java:app/Libra/UtenteJpa");
		
	List<TutorInterno> listaTutor = tutorInternoDAO.findAll(TutorInterno.class);
	ProgettoFormativo progetto;
	List<TutorEsterno> listaTutorEsterni = new ArrayList<TutorEsterno>();
%>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" type="image/png" sizes="16x16" href="assets/images/favicon.png">
	<title>Libra</title>
    <link href="assets/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="assets/plugins/chartist-js/dist/chartist.min.css" rel="stylesheet">
    <link href="assets/plugins/chartist-js/dist/chartist-init.css" rel="stylesheet">
    <link href="assets/plugins/chartist-plugin-tooltip-master/dist/chartist-plugin-tooltip.css" rel="stylesheet">
    <link href="assets/plugins/css-chart/css-chart.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">
    <link href="css/colors/red.css" id="theme" rel="stylesheet">
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
</head>

<body class="fix-header fix-sidebar card-no-border">
    <div class="preloader">
        <svg class="circular" viewBox="25 25 50 50">
            <circle class="path" cx="50" cy="50" r="20" fill="none" stroke-width="2" stroke-miterlimit="10" /> </svg>
    </div>
    <div id="main-wrapper">
       	<%@ include file="header.jsp" %>
        <%@ include file="menu.jsp" %>
        <div class="page-wrapper">
            <div class="container-fluid">
            	<div class="row page-titles">
                    <div class="col-md-6 col-8 align-self-center">
            	 		<h3 class="text-themecolor m-b-0 m-t-0">Carica Proposta</h3>
                 			<ol class="breadcrumb">
                    			<li class="breadcrumb-item"><a href="dashboardStudente.jsp">Home</a></li>
                        		<li class="breadcrumb-item active">Carica Proposta</li>
                    		</ol>
                    </div>
                </div>
                				
            	<form id="myForm" action="carica" method="post" enctype="multipart/form-data" onsubmit="return validateForm()">
            	<div class="card wizard-card" style="padding:1%">
            		<div class="form-group">
    					<input type="file" class="form-control-file" id="fileInput" aria-describedby="fileHelp" name="file" required>
  						<small id="fileHelp" class="form-text text-muted">Carica la proposta che intendi inviare</small>
  						<% if(ruolo.contains("Studente") || ruolo.contains("Azienda")) { %>
  							<p id="errorMessage" hidden="hidden"><i>Parametri inseriti non validi</i></p>
  						<% } %>
  					</div>
  				</div>
  				
  				<% if(ruolo.contains("Studente") || ruolo.contains("Azienda")) { %>
  				<div class="card wizard-card" style="padding:1%">
					<h4 class="card-title">Note:</h4>
						<textarea class="form-control" name="note" rows="6"></textarea>
				</div>
  				<% } %>
  					
  				<% if(ruolo.contains("Studente")) { %>
  				<div class="card wizard-card" style="padding:1%">
  					<h4 class="card-title">Scegli un tutor interno:</h4>
    					<select class="form-control" name="tutorInterno">
      						<%
      							for(TutorInterno t : listaTutor) {
									String nome = t.getNome();
									String cognome = t.getCognome();
							%>
								<option value="<%= t.getUtenteEmail() %>"> <%= nome %> <%= cognome %> </option>
							<%
      							}
      						%>
    					</select>
  				</div>
  				<% } %>
  				
  				<% if(ruolo.contains("Azienda")) { %>
  				<div class="card wizard-card" style="padding:1%">
  					<h4 class="card-title">Ambito:</h4>
  						<input type="text" name="ambito" required>
  				</div>
  				<% } %>
  				
  				<% if(ruolo.contains("Azienda")) { %>
  				<div class="card wizard-card" style="padding:1%">
  					<h4 class="card-title">Scegli uno studente:</h4>
    					<select class="form-control" name="studente" required>
      					<%
      						Azienda azienda = aziendaDAO.findById(Azienda.class,mail);
      						List<ProgettoFormativo> listaProposte = progettoFormativoDAO.getProgettiFormativiByAzienda(azienda.getNome());
      						if(listaProposte != null) {
      							for(ProgettoFormativo p : listaProposte)
      							{
      								if(p.getStato() == 0) {
      								String nome = p.getStudente().getNome();
      								String cognome = p.getStudente().getCognome();
      								%>
    								<option value="<%= p.getId() %>">
    								<%= nome %> <%= cognome %> </option>
    								<%
      								}
      							}
      						}
      						else {
      							%> <p> Nessuna richiesta ricevuta </p> <% 
      						}
      					%>
    					</select>
    			</div>
    			<% } %>
    			
    			<% if(ruolo.contains("Azienda")) { %>
    			<div class="card wizard-card" style="padding:1%">
    				<h4 class="card-title">Scegli un tutor esterno:</h4>
    					<select class="form-control" name="tutorEsterno" required>
      					<%
      						listaTutorEsterni = tutorEsternoDAO.findByEmailAzienda(mail);
      						for(TutorEsterno ts : listaTutorEsterni) {
								String nome = ts.getNome();
								String cognome = ts.getCognome();							
						%>
								<option value="<%= ts.getId().getAmbito() %>">
								<%= nome %> <%= cognome %> </option>
						<%
      						}
      					%>
    					</select>
  				</div>
  				<% } %>
  				
  					<input type="hidden" name="id" value= <%= request.getParameter("id") %>>
  					<button type="submit" class="btn btn-primary">Invia</button>
  					<button type="reset" class="btn btn-primary">Annulla</button>
  				</form>
  				
  				<script>
					function validateForm() {
						var note = $("textarea[name=note]").val();
						var textFormat = /^([0-9a-zA-Z\s_%?!]{0,200})$/;
				
						if(note.match(textFormat))
						{
							return true;
						}
						else 
						{
							$("#errorMessage").show();
							return false;
						}
					}
				</script>
	
  			</div>
            </div>
            <%@ include file="footer.jsp" %>
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
    <script src="assets/plugins/chartist-plugin-tooltip-master/dist/chartist-plugin-tooltip.min.js"></script>
    <script src="assets/plugins/echarts/echarts-all.js"></script>
    <script src="js/dashboard5.js"></script>
    <script src="assets/plugins/styleswitcher/jQuery.style.switcher.js"></script>
</body>
</html>