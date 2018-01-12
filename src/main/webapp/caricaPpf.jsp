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
	String ambito = null;

	IAziendaDao aziendaDAO = (IAziendaDao) new InitialContext().lookup("java:app/Libra/AziendaJpa");
	IProgettoFormativoDao progettoFormativoDAO = (IProgettoFormativoDao) new InitialContext().lookup("java:app/Libra/ProgettoFormativoJpa");
	ITutorInternoDao tutorInternoDAO = (ITutorInternoDao) new InitialContext().lookup("java:app/Libra/TutorInternoJpa");
	ITutorEsternoDao tutorEsternoDAO = (ITutorEsternoDao) new InitialContext().lookup("java:app/Libra/TutorEsternoJpa");
	IUtenteDao utenteDAO= (IUtenteDao) new InitialContext().lookup("java:app/Libra/UtenteJpa");
		
	List<TutorInterno> listaTutor = tutorInternoDAO.findAll(TutorInterno.class);
	ProgettoFormativo progetto;
	TutorEsterno tutor = new TutorEsterno();
	List<TutorEsterno> listaTutorEsterni = new ArrayList<TutorEsterno>();
	String dashboard = "";
%>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="pagina caricamento proposta">
    <meta name="author" content="UmbertoGiarritiello">
    <link rel="icon" type="image/png" sizes="16x16" href="assets/images/favicon.png">
	<title>Libra</title>
    <link href="assets/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="assets/plugins/chartist-js/dist/chartist.min.css" rel="stylesheet">
    <link href="assets/plugins/chartist-js/dist/chartist-init.css" rel="stylesheet">
    <link href="assets/plugins/chartist-plugin-tooltip-master/dist/chartist-plugin-tooltip.css" rel="stylesheet">
    <link href="assets/plugins/css-chart/css-chart.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">
    <link href="css/colors/red.css" id="theme" rel="stylesheet">
    <link href="assets/plugins/dropify/dist/css/dropify.min.css" rel="stylesheet">
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
							<%
								if (session != null && session.getAttribute("utenteRuolo") != null) {
									dashboard = request.getContextPath()
											+ "/dashboard".concat(session.getAttribute("utenteRuolo").toString()).concat(".jsp");
							%>
							<li class="breadcrumb-item"><a href="<%=dashboard%>">Home</a></li>
							<li class="breadcrumb-item active">Carica Proposta</li>
							<%
								}
							%>
						</ol>
                    </div>
                </div>
                				
            	<form id="myForm">
            	<div class="row">
            	<div class="col-sm-4">
					<div class="card wild-card">
						<div class="text-center"
							style="margin: 0 auto;">
								<div class="card">
									<div class="card-block">
											<h4 class="card-title">Carica Proposta</h4>
											<input type="file" id="input-file-now" class="dropify" required/> <br>
  											<% if(ruolo.contains("Studente") || ruolo.contains("Azienda")) { %>
  											<p id="errorMessage" hidden="hidden"><i>Parametri inseriti non validi</i></p>
  											<% } %>
									</div>
								</div>
						</div>
					</div>
				</div>
  				
  				<div class="col-sm-4">
  				<% if(ruolo.contains("Studente") || ruolo.contains("Azienda")) { %>
  				<div class="card wizard-card" style="padding:1%">
					<h4 class="card-title">Note:</h4>
						<textarea class="form-control" id="textarea" name="note" rows="8"></textarea>
				</div>
  				<% } %>
  				</div>
  				
  				<div class="col-sm-4">
  				<% if(ruolo.contains("Studente")) { %>
  				<div class="card wizard-card" style="padding:1%">
  					<h4 class="card-title">Scegli un tutor interno:</h4>
    					<select class="form-control" name="tutorInterno" id="tutorInterno" required>
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
    					<input type="hidden" name="id" id="propostaId" value= <%= request.getParameter("id") %>>
    					<input type="hidden" name="ruolo" id="ruolo" value="Studente">
  				</div>
  				<% } %>
  				
  				<% if(ruolo.contains("Azienda")) { %>
  				<div class="card wizard-card" style="padding:1%">
  					<h4 class="card-title">Ambito:</h4>
  					<%
  		                listaTutorEsterni = tutorEsternoDAO.findByEmailAzienda(mail);
  						if(!listaTutorEsterni.isEmpty()) 
  						{
  							tutor = listaTutorEsterni.get(0);
  							ambito = tutor.getId().getAmbito();
  						}
  						if(ambito != null) {
  							%> <input type="text" name="ambito" id="ambito" value=<%= ambito %> readonly required> <%
  					    } %>
  				</div>
  				<% } %>
  				
  				<% if(ruolo.contains("Azienda")) { %>
  				<div class="card wizard-card" style="padding:1%">
  					<h4 class="card-title">Scegli uno studente:</h4>
    					<select class="form-control" name="id" id="propostaId" required>
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
    					<input type="hidden" name="ruolo" id="ruolo" value="Azienda">
    			</div>
    			<% } %>
    			
    			<% if(ruolo.contains("Azienda")) { %>
    			<div class="card wizard-card" style="padding:1%">
    				<h4 class="card-title">Scegli un tutor esterno:</h4>
    					<select class="form-control" name="tutorEsterno" id="tutorEsternoAmbito" onclick="validateSelect()" required>
    					<%
    					listaTutorEsterni = tutorEsternoDAO.findByEmailAzienda(mail);
      					for(TutorEsterno t : listaTutorEsterni) {
							ambito = t.getId().getAmbito();
							String nome = t.getNome();
							String cognome = t.getCognome();
						%>
						<option value="<%= ambito %>"> <%= nome %> <%= cognome %> </option>
					 <% } %>
    				 </select>
  				</div>
  				<% } %>
  				</div> <!-- END COL SM -->
    			</div> <!-- END ROW -->
    			
    			<div class="modal fade" id="okDialogMsg" role="dialog">
		            <div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-body">
								Il caricamento della proposta � avvenuto con successo!
							</div>
							<div class="modal-footer">
									<a class="btn btn-success" style="text-decoration: none; color: white;" href="<%=dashboard%>">
										Ok 
									</a>
							</div>
						</div>
					</div>
				</div>
    			
  				<% if(ruolo.contains("TutorInterno") || (ruolo.contains("Presidente"))) { %>
  					<input type="hidden" name="id" id="propostaId" value="<%= request.getParameter("id")%>">
  					<input type="hidden" name="ruolo" id="ruolo" value="TutorPresidente">
  				<% } %>
  					<button type="submit" id="caricaButton" class="btn btn-primary">Invia</button>
  					<button type="reset" class="btn btn-primary">Annulla</button>
  				</form>
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
    <script src="assets/plugins/dropify/dist/js/dropify.min.js"></script>
    <script>
    	function validateSelect() {
    	    var ambito = $("#tutorEsternoAmbito").val();
    	    var select = document.getElementById('ambito');
    	    
    	    $('#ambito').val(ambito);
    	}
    </script>
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
	<script>
    $(document).ready(function() {
        // Basic
        $('.dropify').dropify();
        // Translated
        $('.dropify-fr').dropify({
            messages: {
                default: 'Glissez-déposez un fichier ici ou cliquez',
                replace: 'Glissez-déposez un fichier ou cliquez pour remplacer',
                remove: 'Supprimer',
                error: 'Désolé, le fichier trop volumineux'
            }
        });
        // Used events
        var drEvent = $('#input-file-events').dropify();
        drEvent.on('dropify.beforeClear', function(event, element) {
            return confirm("Do you really want to delete \"" + element.file.name + "\" ?");
        });
        drEvent.on('dropify.afterClear', function(event, element) {
            alert('File deleted');
        });
        drEvent.on('dropify.errors', function(event, element) {
            console.log('Has Errors');
        });
        var drDestroy = $('#input-file-to-destroy').dropify();
        drDestroy = drDestroy.data('dropify')
        $('#toggleDropify').on('click', function(e) {
            e.preventDefault();
            if (drDestroy.isDropified()) {
                drDestroy.destroy();
            } else {
                drDestroy.init();
            }
        })
    });
    $("#caricaButton").click(function(e){ 
	e.preventDefault();

    	var propostaId;
    	var ambito;
    	var note;
    	var tutorEsternoAmbito;
    	var tutorInternoMail;
    	var ruolo = $("#ruolo").val();
    		
    	if(ruolo == "Studente") {
    		if(validateForm()) {
    			propostaId = $("#propostaId").val();
    			tutorInternoMail = $('#tutorInterno').val();
    			note = $('textarea#textarea').val();
    			
        		var base64; // = $(".dropify-render img[src]").attr("src");
    			var input = document.getElementById('input-file-now');
    			var file = input.files[0];
    			var fr = new FileReader();
    			fr.onload = function () {
    				base64=fr.result;
    				$.post("carica",
    		        		{
    		        			file: btoa(base64),
    		        			id: propostaId,
    		        			note: note, 
    		        			tutorInterno: tutorInternoMail
    		        		},function(data) {
    		        			if(data == "ok") {
    		        				$('#okDialogMsg').modal({backdrop: 'static', keyboard: false});
    		        			}
    		        			}
    		        		);
    			}
    			fr.readAsDataURL(file);	
    		}
    	}
    	else if(ruolo == "Azienda") {
    		if(validateForm()) {
    			ambito = $("#ambito").val();
    			propostaId = $('#propostaId').val();
    			tutorEsternoAmbito = $("#tutorEsternoAmbito").val();
    			note = $('textarea#textarea').val();
    			
        		var base64; // = $(".dropify-render img[src]").attr("src");
    			var input = document.getElementById('input-file-now');
    			var file = input.files[0];
    			var fr = new FileReader();
    			fr.onload = function () {
    				base64=fr.result;
    				$.post("carica",
    		        		{
    		        			file: btoa(base64),
    		        			id: propostaId,
    		        			note: note,
    		        			ambito: ambito, 
    		        			tutorEsterno: tutorEsternoAmbito
    		        		},function(data) {
    		        			if(data == "ok") {
    		        				$('#okDialogMsg').modal({backdrop: 'static', keyboard: false});
    		        			}
    		        		}
    		        		);
    			}
    			fr.readAsDataURL(file);			
    		}
    	}
    	else if(ruolo == "TutorPresidente") {
    		propostaId = $('#propostaId').val();

    		var base64; // = $(".dropify-render img[src]").attr("src");
			var input = document.getElementById('input-file-now');
			var file = input.files[0];
			var fr = new FileReader();
			fr.onload = function () {
				base64=fr.result;
				$.post("carica",
			        	{
			        		file: btoa(base64),
			        		id: propostaId
			        	},function(data) {
			        		if(data == "ok") {
			    				$('#okDialogMsg').modal({backdrop: 'static', keyboard: false});
			        		}
			        		}
			        	);
			}
			fr.readAsDataURL(file);       	
    	}
    })
    </script>
</body>
</html>