<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ page import="it.unisa.libra.bean.Studente, it.unisa.libra.bean.ProgettoFormativo" %> 
<%@ page import="java.util.Base64"%>
<%@ page import="it.unisa.libra.util.FileUtils" %>
<%@ page import="java.text.DateFormat" %>
<%@ page import="java.text.SimpleDateFormat" %>

<% 
Studente studente = (Studente) request.getAttribute("studente");
ProgettoFormativo pf = (ProgettoFormativo) request.getAttribute("progettoFormativo");
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
					<div class="col-md-6 col-8 align-self-center">
						<h3 class="text-themecolor m-b-0 m-t-0">Dettaglio Studente</h3>
						<ol class="breadcrumb">
							<%
								if (session != null && session.getAttribute("utenteRuolo") != null) {
									String dashboard = request.getContextPath()
											+ "/dashboard".concat(session.getAttribute("utenteRuolo").toString()).concat(".jsp");
							%>
							<li class="breadcrumb-item"><a href="<%=dashboard%>">Home</a></li>
							<li class="breadcrumb-item active">Dettaglio Studente</li>
							<%
								}
							%>
						</ol>
					</div>
				</div>
            	<div class="row">
	             	<div class="col-md-4">
	                        <div class="card">
	                            <div class="card-block align-self-center">
	                                <div class="text-center"> 
	                                	<%
		                                	String path= FileUtils.readBase64FromFile(FileUtils.PATH_IMG_PROFILO, studente.getUtente().getImgProfilo());
											String img="";
											if(path != null){
												img= (new String(Base64.getUrlDecoder().decode(path), "UTF-8") + "\n");
											}
	                                	%>
		                                <img src="<%if(path != null){%><%=img%><%}else{%>assets/images/users/default.png<%}%>" class="img-circle" width="150">
		                                <br>
		                                <div class="card-block">
		                                    <h4 class="card-title"><%= studente.getNome() %> <%= studente.getCognome() %></h4>
		                                    <p class="card-text text-center">Stato <br> 
		                                    <% if (pf != null) {
		                                    	int stato = pf.getStato();
		                                     	if (stato == 0 || stato == 1 || stato == 2 || stato == 3) { %>
		                                    <span class="label label-warning">In attesa</span></p>
		                                    <% } else if (stato == 4) { %>
		                                    <span class="label label-primary">Verificato</span></p>
		                                    <% } else if (stato == 5) { %>
		                                    <span class="label label-success">Approvato</span></p>
		                                   	<% } else if (stato == 6) { %>
		                                    <span class="label label-danger">Rifiutato</span></p>
                               				<% } 
                               				} else {%>
		                                    <span class="label label-info">Disponibile</span></p>
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
	                                	<strong>Nome:</strong><span class="text-muted"> <%= studente.getNome() %></span> <br>
	                                	<strong>Cognome:</strong><span class="text-muted"> <%= studente.getCognome() %></span> <br>
	                                	<strong>Data di nascita:</strong><span class="text-muted"> <%= formatter.format(studente.getDataDiNascita()) %></span> <br>
	                                	<strong>Matricola:</strong><span class="text-muted"> <%= studente.getMatricola() %></span>
	                                </p>
	                                <h3 class="card-title">Contatti</h3>
	                                <p class="card-text">
	                                	<strong>Indirizzo:</strong><span class="text-muted"> <%= studente.getUtente().getIndirizzo() %></span> <br>
	                                	<strong>E-mail:</strong><span class="text-muted"> <%= studente.getUtente().getEmail() %></span> <br>
	                                	<strong>Telefono:</strong><span class="text-muted">  <%= studente.getUtente().getTelefono() %></span> 
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
	                				<% if (pf != null && pf.getStato()!=0) { %>
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
	                				<% } else if ((request.getSession().getAttribute("utenteRuolo").equals("TutorInterno") && pf.getStato() == 2) || (request.getSession().getAttribute("utenteRuolo").equals("Presidente") && pf.getStato() == 3)) { %>
	                				<div class="row card-block">
		                				<div class="col-md-4">
		                					<a href="caricaPpf.jsp?id=<%= pf.getId()%>"><button type="button" class="btn btn-outline-success"><i class="fa fa-check"></i> Invia</button></a>
		                					<a href="rifiutaPpf.jsp?id=<%= pf.getId()%>"><button type="button" class="btn btn-outline-danger"><i class="fa fa-close"></i> Rifiuta</button></a>
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


