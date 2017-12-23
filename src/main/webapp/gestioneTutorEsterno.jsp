<%@page import="it.unisa.libra.bean.Azienda"%>
<%@page import="it.unisa.libra.bean.TutorEsternoPK"%>
<%@page import="it.unisa.libra.model.dao.ITutorEsternoDao"%>
<%@page import="it.unisa.libra.bean.TutorEsterno"%>
<%@ page import="javax.naming.InitialContext" %>
<%@ page import="javax.naming.Context" %>
<%@page import="it.unisa.libra.model.jpa.TutorEsternoJpa"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<<<<<<< HEAD
<!-- 
=======


>>>>>>> branch 'develop' of https://github.com/LibraTeamUnisa/Libra.git
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
<style type="text/css">
#button1:active, #button1:hover {
	background-color: blue;
}

#label1 {
	visibility: visible;
}

#label2 {
	visibility: hidden;
}


@media screen and (min-width: 300px) {
	#label1 {
		visibility: hidden;
	}
	#label2 {
		visibility: visible;
	}
}
</style>
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
				boolean badRequest = false;
				String emailAzienda = (String) request.getSession().getAttribute("utenteEmail");
				String azione = request.getParameter(Actions.ACTION);
				String ambito = null;
				TutorEsternoPK id = null;
				TutorEsterno tutor = null;
				if(azione != null) {
					if (azione.equals(Actions.MODIFICA_TUTOR_ESTERNO)) {
					ambito = (String) request.getParameter("ambito");
					if (ambito == null) badRequest = true;
					else {
				
						id = new TutorEsternoPK();
						id.setAziendaEmail(emailAzienda);
						id.setAmbito(ambito);
					
						ITutorEsternoDao tutorDao = (ITutorEsternoDao) new InitialContext().lookup("java:app/Libra/TutorEsternoJpa");
						tutor = tutorDao.findById(TutorEsterno.class, id);
						if (tutor == null) badRequest = true;
					}
					}
					else if (!azione.equals(Actions.AGGIUNGI_TUTOR_ESTERNO)) badRequest = true;
				}
				else badRequest = true;
				
					if (badRequest) {

            
						//ERRORE: BAD REQUEST
					%>
				<p id="badRequest" style="visibility: hidden;"></p>
				<%
					} else {
				%>
				<div class=".col-sm-8">
					<div class="card card-block">
						<h3 class="box-title m-b-0" style="text-align: center;">
							<%if (azione.equals(Actions.AGGIUNGI_TUTOR_ESTERNO)) {%>
							Aggiungi Tutor Esterno
							<%} else { %>
							Modifica Tutor Esterno
							<%} %>
						</h3>
						<p></p>
						<form id="formTutor" class="form-horizontal">
							<input type="hidden" name="action" value=<%=azione%>
								id="inputAction" />

							<input type="hidden" name="idTutor"  id="inputOldAmbito" 
										<%if (azione.equals(Actions.MODIFICA_TUTOR_ESTERNO)) { %>
										value=<%=id.getAmbito()%>  <%} %> />
							<input type="hidden" name="idAzienda"  id="inputEmailAzienda" 
										<%if (azione.equals(Actions.MODIFICA_TUTOR_ESTERNO)) { %>
										value=<%=emailAzienda%>  <%} %> />
										

							<div class="form-group row">

								<label for="inputAmbito"
									class="col-sm-2 text-right control-label col-form-label"
									id="label1">Ambito</label> <label for="inputAmbito"
									class="col-sm-2 text-left control-label col-form-label"
									id="label2">Ambito</label>

								<div class="col-sm-8">
									<input type="text" name="ambito" required="required"
										class="form-control" id="inputAmbito" placeholder="Ambito"

										maxlength="50" pattern="[a-zA-Z]+[ ]?[a-zA-Z]*" title="solo caratteri alfabetici"
										<%if (azione.equals(Actions.MODIFICA_TUTOR_ESTERNO)) { %>
										value=<%=id.getAmbito()%>  <%} %> />

								</div>
							</div>
							<div class="form-group row">

								<label for="inputNome"
									class="col-sm-2 text-right control-label col-form-label"
									id="label1">Nome</label> <label for="inputNome"
									class="col-sm-2 text-left control-label col-form-label"
									id="label2">Nome</label>

								<div class="col-sm-8">
									<input type="text" name="nome" required="required"
										class="form-control" id="inputNome" placeholder="Nome"

										maxlength="30" pattern="[a-zA-Z]+[ ]?[a-zA-Z]*" title="solo caratteri alfabetici"

										<%if (azione.equals(Actions.MODIFICA_TUTOR_ESTERNO)) { %>
										value=<%=tutor.getNome()%> <%} %> />
								</div>
							</div>
							<div class="form-group row">
								<label for="inputCognome"
									class="col-sm-2 text-right control-label col-form-label"
									id="label1">Cognome</label> <label for="inputCognome"
									class="col-sm-2 text-left control-label col-form-label"
									id="label2">Cognome</label>
								<div class="col-sm-8">
									<input type="text" name="cognome" required="required"
										maxlength="30" class="form-control" id="inputCognome"

										placeholder="Cognome" pattern="[a-zA-Z]+[ ]?[a-zA-Z]*" title="solo caratteri alfabetici"

										<%if (azione.equals(Actions.MODIFICA_TUTOR_ESTERNO)) { %>
										value=<%=tutor.getCognome()%> <%} %> />
								</div>
							</div>
							<div class="form-group row">
								<label for="inputIndirizzo"
									class="col-sm-2 text-right control-label col-form-label"
									id="label1">Indirizzo</label> <label for="inputIndirizzo"
									class="col-sm-2 text-left control-label col-form-label"
									id="label2">Indirizzo </label>
								<div class="col-sm-8">
									<input type="text" name="indirizzo" class="form-control"
										id="inputIndirizzo" placeholder="Indirizzo"

										pattern="[a-zA-Z]+[ ]?[a-zA-Z]*, [0-9]* [a-zA-Z]+[ ]?[a-zA-Z]*" required="required" maxlength="40"
										title="via, numeroCivico citta'"

										<%if (azione.equals(Actions.MODIFICA_TUTOR_ESTERNO)) { %>
										value=<%=tutor.getIndirizzo()%> <%} %> />
								</div>
							</div>
							<div class="form-group row">

								<label for="inputDataDiNascita"
									class="col-sm-2 text-right control-label col-form-label"
									id="label1">Data di nascita</label> <label for="inputDataDiNascita"
									class="col-sm-2 text-left control-label col-form-label"
									id="label2">Data di nascita </label>
								<div class="col-sm-8">
									<input type="date" name="dataDiNascita" class="form-control"
										id="inputDataDiNascita" placeholder="Data di nascita"
										 required="required" 
										<%if (azione.equals(Actions.MODIFICA_TUTOR_ESTERNO)) { %>
										value=<%=tutor.getDataDiNascita()%> <%}%> />
								</div>
							</div>							
							<div class="form-group row">

								<label for="inputTel"
									class="col-sm-2 text-right control-label col-form-label"
									id="label1">Telefono</label> <label for="inputTel"
									class="col-sm-2 text-left control-label col-form-label"
									id="label2">Telefono</label>
								<div class="col-sm-8">
									<input type="text" name="telefono" class="form-control"
										id="inputTel" placeholder="Telefono" required="required"

										maxlength="10" pattern="[0-9]{10}" title="solo caratteri numerici"

										<%if (azione.equals(Actions.MODIFICA_TUTOR_ESTERNO)) { %>
										value=<%=tutor.getTelefono()%> <%} %> />
								</div>
							</div>

							<div class="form-group m-b-0">
								<div class="offset-sm-3 col-sm-9">
									<div class="button-box">
										<%if (azione.equals(Actions.AGGIUNGI_TUTOR_ESTERNO)) { %>
										<button type="submit" id="buttonTutorEsterno"
											class="btn btn-primary" style="text-align: center;">
											Aggiungi Tutor Esterno</button>
										<% } else { %>
										<button type="submit" id="buttonTutorEsterno"
											class="btn btn-primary" style="text-align: center;">
											Modifica Tutor Esterno</button>
										<% } %>
									</div>
								</div>
							</div>
						</form>
						<div class="modal fade" id="modalResult" role="dialog">
							<div class="modal-dialog">

								<div class="modal-content">
									<div class="modal-header">
										<h4 class="modal-title">
											<%if (azione.equals(Actions.AGGIUNGI_TUTOR_ESTERNO)) { %>
											Aggiungi Tutor
											<% } else { %>
											Modifica Tutor
											<%} %>
										</h4>
									</div>
									<div class="modal-body">
										<p id="modalMessage"></p>
									</div>
									<div class="modal-footer">
										<button id="button1" type="button" class="btn btn-primary">
											<a class="btn btn-primary" href="dashboardAzienda.jsp"
												style="text-decoration: none; color: white;"> Dashboard
											</a>
										</button>
									</div>
								</div>

							</div>
						</div>
					</div>
				</div>

				<%}%>
				<div class="modal fade" id="modalBadRequest" role="dialog">
					<div class="modal-dialog">

						<div class="modal-content">
							<div class="modal-header">
								<h4 class="modal-title">Errore</h4>
							</div>
							<div class="modal-body">

								<p>L'operazione richiesta non e' valida.</p>

							</div>
							<div class="modal-footer">
								<button id="button1" type="button" class="btn btn-primary">
									<a class="btn btn-primary" href="dashboardAzienda.jsp"
										style="text-decoration: none; color: white;"> Dashboard </a>
								</button>
							</div>
						</div>

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
	<script>
		$(document).ready(function() {
		if($("#badRequest").length) {
			$("#modalBadRequest").modal('show');
		}
		})
		
			$(document).ready(function() {
					$("#formTutor").submit(function(e) {
													e.preventDefault();
													$.post(
																	'gestioneTutorEsternoServlet',
																	{

																		idTutor : $("#inputOldAmbito").val(),
																		idAzienda : $("#inputEmailAzienda").val(),

																		action : $("#inputAction").val(),
																		ambito : $("#inputAmbito").val(),
																		nome : $("#inputNome").val(),
																		cognome : $("#inputCognome").val(),

																		dataDiNascita : $("#inputDataDiNascita").val(),

																		telefono : $("#inputTel").val(),
																		indirizzo : $("#inputIndirizzo").val()

																	},
																	function(
																			data) {
																		 $("#buttonTutorEsterno").prop("disabled",true);
																		 
																		if (data == "ok") {

																			$("#modalMessage").text("L'operazione di aggiunta e' avvenuta correttamente");

 																			
																		} 
																		else {
																			$("#modalMessage").text(data);
																		}
																		 $("#modalResult").modal('show');
																			
																	});
												});
							})
		</script>





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
-->

