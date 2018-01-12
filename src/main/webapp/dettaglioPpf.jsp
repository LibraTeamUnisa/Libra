<%@page import="java.util.Base64"%>
<%@page import="it.unisa.libra.util.FileUtils"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	import="it.unisa.libra.bean.*,
			it.unisa.libra.model.dao.*,
			javax.ejb.EJB,
			javax.naming.InitialContext,
			java.util.Date,
			java.util.Calendar,
			java.util.GregorianCalendar,
			java.util.List,
			java.util.Map,
			java.util.Map.Entry,
			it.unisa.libra.util.JsonUtils
			"%>


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
	
	<%
	String idPf = request.getParameter("id");
	if (idPf == null) {
		response.sendError(HttpServletResponse.SC_BAD_REQUEST);
		return;
	}
	int id = Integer.parseInt(idPf);
	IProgettoFormativoDao pfdao = (IProgettoFormativoDao) new InitialContext().lookup("java:app/Libra/ProgettoFormativoJpa");
	ProgettoFormativo pf = pfdao.findById(ProgettoFormativo.class, id);
	if (pf == null) {
		response.sendError(HttpServletResponse.SC_BAD_REQUEST);
		return;
	}
	boolean isStudente = false;
	boolean isAzienda = false;
	Studente studente = null;
	Azienda azienda = null;
	session = request.getSession();
	String ruolo = (String) session.getAttribute("utenteRuolo");
	if ("Azienda".equals(ruolo)) {
		//sono un'azienda e devo vedere lo studente
		isAzienda = true;
		studente = pf.getStudente();
	}
	else if ("Studente".equals(ruolo)) {
		// sono studente e devo vedere l'azienda
		isStudente = true;
		azienda = pf.getAzienda();
	}
	else {
		response.sendError(HttpServletResponse.SC_BAD_REQUEST);
		return;
	}
			
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
					<div class="col-12 align-self-center">
						<h3 class="text-themecolor m-b-0 m-t-0">Dettagli Progetto
							Formativo</h3>
						<ol class="breadcrumb">
							<li class="breadcrumb-item"><a href="dashboard<%=ruolo%>.jsp">Dashboard</a></li>
							<% if (isAzienda) { %>
							<li class="breadcrumb-item"><a href="pfInviati.jsp">Progetti Formativi</a></li>
							<% } %>
							<li class="breadcrumb-item active">Dettagli Progetto Formativo</li>
						</ol>
					</div>
				</div>

				<div class="card card-block">
									
					<div class="row">
								
								<div class="col-4">
								<%
								  String path= FileUtils.readBase64FromFile(FileUtils.PATH_PDF_PROGETTOF, pf.getDocumento());
								  String doc="";
								  if(path != null){
								    doc = (new String(Base64.getUrlDecoder().decode(path), "UTF-8"));
								    doc = doc.substring(28);
								  }
								%>
								<p class="card-text text-center">
			                	<a href="data:application/octet-stream;base64, <%=doc%>" download="<%=id%>.pdf">
									<i class="fa fa-file-pdf-o" style="font-size:6em" data-toggle="tooltip" data-original-title="Scarica"></i>
								</a>
			                	</p>
			                	<div class="card-text text-center">
			                	<h4 class="label label-warning" align="center" style="color:white;">
			                	<a href="data:application/octet-stream;base64, <%=doc%>" download="<%=id%>.pdf">
									Scarica
								</a>
								</h3>
			                	</div>
								</div> 
								
					<!-- PROGETTO FORMATIVO-->
					<div class="col-8">
						<div class="card wild-card" style="color: black; font-size: 120%;">
							<% if (isStudente) { %>
							<div class="row">
								<div class="col-3">
									<label>Azienda:</label>
								</div>
								<div class="col-9">
									<p><%=azienda.getNome()%></p>
								</div>
							</div>
							<% } else if (isAzienda) { %>
							<div class="row">
								<div class="col-3">
									<label>Studente:</label>
								</div>
								<div class="col-9">
									<p><%=studente.getNome()%> <%=studente.getCognome()%></p>
								</div>
							</div>
							<% } %>
							
							<div class="row">
								<div class="col-3">
									<label>Ambito:</label>
								</div>
								<div class="col-9">
									<p><%=pf.getAmbito()%></p>
								</div>
							</div>
							
							<div class="row">
								<div class="col-3">
									<label>Data invio:</label>
								</div>
								<div class="col-9">
									<%
										Date date = pf.getDataInvio();
										Calendar calendar = new GregorianCalendar();
										calendar.setTime(date);
										int year = calendar.get(Calendar.YEAR);
										int mm = calendar.get(Calendar.MONTH) + 1;
										int gg = calendar.get(Calendar.DAY_OF_MONTH);
										String day = String.format("%02d", gg);
										String month = String.format("%02d", mm);
									%>
									<p><%=year%>-<%=month%>-<%=day%></p>
								</div>
							</div>
							
							<div class="row">
								<div class="col-3">
									<label>Note:</label>
								</div>
								<div class="col-9">
									<%
										String note = pf.getNote();
										if (note != null) {
									%>
									<p><%=note%></p>
									<%
										} else {
									%>
									<p>Non ci sono note</p>
									<%
										}
									%>
								</div>
							</div>
							
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


