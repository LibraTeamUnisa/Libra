<%@page import="it.unisa.libra.util.JspPagesIndex"%>
<%@page import="it.unisa.libra.bean.Azienda"%>
<%@page import="it.unisa.libra.bean.TutorEsternoPK"%>
<%@page import="it.unisa.libra.model.dao.ITutorEsternoDao"%>
<%@page import="it.unisa.libra.bean.TutorEsterno"%>
<%@ page import="javax.naming.InitialContext" %>
<%@ page import="javax.naming.Context" %>
<%@page import="it.unisa.libra.model.jpa.TutorEsternoJpa"%>
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
<style>
::placeholder {
    color: #e0e0e0!important;
    opacity: 1; /* Firefox */
}

:-ms-input-placeholder { /* Internet Explorer 10-11 */
   color: #e0e0e0!important;
}

::-ms-input-placeholder { /* Microsoft Edge */
   color: #e0e0e0!important;
}

#buttonTutorEsterno {
   float:right;
   margin:0px!important;
}

#buttonTutorRow {
   padding:0px!important;
}

#modal-link:hover{
   color:white!important;
}

#button1:hover > a
{
  color:white!important;
}

#button1:active > a
{
  color:white!important;
}

#button1:focus > a
{
  color:white!important;
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
				
			<div class="row page-titles">
                    <div class="col-md-6 col-8 align-self-center">
                        <h3 class="text-themecolor m-b-0 m-t-0"><%if (azione.equals(Actions.AGGIUNGI_TUTOR_ESTERNO)) {%>
							Aggiungi Tutor Esterno
							<%} else { %>
							Modifica Tutor Esterno
							<%} %></h3>
                        <ol class="breadcrumb">
                            <li class="breadcrumb-item"><a href="<%=JspPagesIndex.DASHBOARD_AZIENDA.substring(1)%>">Dashboard </a></li>
                            <%if (azione.equals(Actions.AGGIUNGI_TUTOR_ESTERNO)) {%>
							<li class="breadcrumb-item active">Aggiungi Tutor Esterno</li>
							<%} else { %>
							<li class="breadcrumb-item"><a href="<%=JspPagesIndex.CATALOGO_TUTOR_ESTERNI.substring(1)%>">Tutor</a></li>
                            <li class="breadcrumb-item active">Modifica Tutor Esterno</li>
							<%} %>
                        </ol>
                    </div>
                    
                </div>
			<div class="row">
                    <div class="col-sm-12">
                        <div class="card">
                            <div class="card-block">
						<h3 class="box-title m-b-0" style="text-align: center;">
							<%if (azione.equals(Actions.AGGIUNGI_TUTOR_ESTERNO)) {%>
							Aggiungi Tutor Esterno
							<%} else { %>
							Modifica Tutor Esterno
							<%} %>
						</h3>
						<p></p>
						<form class="form" id="formTutor" >
						<input type="hidden" name="action" value=<%=azione%>
								id="inputAction" />

							<input type="hidden" name="idTutor"  id="inputOldAmbito" 
										<%if (azione.equals(Actions.MODIFICA_TUTOR_ESTERNO)) { %>
										value="<%=id.getAmbito()%>"  <%} %> />
							<input type="hidden" name="idAzienda"  id="inputEmailAzienda" 
										<%if (azione.equals(Actions.MODIFICA_TUTOR_ESTERNO)) { %>
										value=<%=emailAzienda%>  <%} %> />
                                    <div class="form-group m-t-40 row">
                                        <label for="example-text-input" class="col-2 col-form-label">Ambito</label>
                                        <div class="col-10">
                                            <input class="form-control" type="text" id="example-text-input" name="ambito" placeholder="Nuovo Ambito"

										maxlength="50" pattern="[a-zA-Z]+[a-zA-z ']*[a-zA-Z]+" title="solo caratteri alfabetici"
										<%if (!azione.equals(Actions.MODIFICA_TUTOR_ESTERNO)) { %>
										required="required"  <%} %> />
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label for="example-search-input" class="col-2 col-form-label">Nome</label>
                                        <div class="col-10">
                                            <input class="form-control" type="text"  id="example-search-input" name="nome" placeholder="Nuovo Nome"

										maxlength="30" pattern="[a-zA-Z]+[a-zA-z ']*[a-zA-Z]+" title="solo caratteri alfabetici"

										<%if (!azione.equals(Actions.MODIFICA_TUTOR_ESTERNO)) { %>
										required="required" <%} %> />
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label for="example-email-input" class="col-2 col-form-label">Cognome</label>
                                        <div class="col-10">
                                            <input class="form-control" type="text" id="example-email-input" type="text" name="cognome" maxlength="30" 
                                                   
                                                   placeholder="Nuovo Cognome" pattern="[a-zA-Z]+[a-zA-z ']*[a-zA-Z]+" title="solo caratteri alfabetici"

										<%if (!azione.equals(Actions.MODIFICA_TUTOR_ESTERNO)) { %>
										required="required" <%} %> />
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label for="example-url-input" class="col-2 col-form-label">Indirizzo</label>
                                        <div class="col-10">
                                            <input class="form-control" id="example-url-input" type="text" name="indirizzo"  placeholder="Indirizzo"

										pattern="[a-zA-Z]+[a-zA-Z, 0-9]*[a-zA-Z]+" maxlength="40"
										title="via, numeroCivico citta'"

										<%if (!azione.equals(Actions.MODIFICA_TUTOR_ESTERNO)) { %>
										required="required" <%} %> />
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label for="example-tel-input" class="col-2 col-form-label">Data Di Nascita</label>
                                        <div class="col-10">
                                            <input class="form-control" id="example-tel-input" type="date" name="dataDiNascita" placeholder="Nuova Data di nascita"
                                            
										<%if (!azione.equals(Actions.MODIFICA_TUTOR_ESTERNO)) { %>
										required="required" <%}%> />
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label for="example-password-input" class="col-2 col-form-label">Telefono</label>
                                        <div class="col-10">
                                            <input class="form-control"  id="example-password-input" type="text" name="telefono" placeholder="Nuovo Numero Telefonico"

										maxlength="10" pattern="[0-9]{8,11}" title="solo caratteri numerici"

										<%if (!azione.equals(Actions.MODIFICA_TUTOR_ESTERNO)) { %>
										required="required" <%} %> />
                                        </div>
                                    </div>
                                    <div class="form-group m-b-0">
								<div id="buttonTutorRow" class="offset-sm-3 col-sm-9">
									<div class="button-box">
										<%if (azione.equals(Actions.AGGIUNGI_TUTOR_ESTERNO)) { %>
										<button type="submit" id="buttonTutorEsterno"
											class="btn btn-outline-danger" style="text-align: center;">
											Aggiungi Tutor Esterno</button>
										<% } else { %>
										<button type="submit" id="buttonTutorEsterno"
											class="btn btn-outline-danger" style="text-align: center;">
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
										<p id="modalMessage"> </p>
									</div>
									<div class="modal-footer">
										<button id="button1" type="button" class="btn btn-outline-danger">
											<a id="modal-link" href="dashboardAzienda.jsp"> Dashboard
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
			<!--<%@ include file="footer.jsp"%>-->
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
																		action : $("#inputAction").val(),

																		idTutor : $("#inputOldAmbito").val(),
																		idAzienda : $("#inputEmailAzienda").val(),
																		 
																		ambito : $("#example-text-input").val(),
																		nome : $("#example-search-input").val(),
																		cognome : $("#example-email-input").val(),

																		dataDiNascita : $("#example-tel-input").val(),

																		telefono : $("#example-password-input").val(),
																		indirizzo : $("#example-url-input").val()

																	},
																	function(data,status) {
																		 $("#buttonTutorEsterno").prop("disabled",true);
																		 $("#modalMessage").text(data);
																		 $("#modalResult").modal('show');
																			
																	})
																	.fail(
																		    function(jqXHR, textStatus, errorThrown) {
																		    	$("#buttonTutorEsterno").prop("disabled",true);
																				$("#modalMessage").text(jqXHR.responseText);
																				$("#modalResult").modal('show');
																		     }
																		 );
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

