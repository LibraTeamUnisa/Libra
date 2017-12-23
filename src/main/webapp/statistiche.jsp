<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@page import="it.unisa.libra.model.dao.IStudenteDao"%>
<%@page import="it.unisa.libra.model.dao.IUtenteDao"%>
<%@page import="it.unisa.libra.model.dao.IProgettoFormativoDao"%>
<%@page import="it.unisa.libra.bean.Studente"%>
<%@page import="it.unisa.libra.bean.Utente"%>
<%@page import="it.unisa.libra.bean.ProgettoFormativo"%>
<%@page import="java.util.Iterator"%>
<%@page import="it.unisa.libra.model.dao.ITutorInternoDao"%>
<%@ page import="javax.naming.InitialContext"%>
<%@ page import="javax.naming.Context"%>
<%@page import="it.unisa.libra.bean.TutorInterno"%>
<%@page import="java.util.*,it.unisa.*"%>





<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<!-- Tell the browser to be responsive to screen width -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description"
	content="Sezione che mostra statistiche relative agli studenti attivi o inattivi.">
<meta name="author" content="Solimando Giandomenico">
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

				<div class="card">
					<div class="card-block">
						<h4 class="card-title">Lista Studenti</h4>
						<% String emailTutor = (String) request.getSession().getAttribute("giando26@gmail.com");
					
						ITutorInternoDao tutorInternoDao = (ITutorInternoDao) new InitialContext().lookup("java:app/Libra/TutorInternoJpa");
						TutorInterno tutorInterno = tutorInternoDao.findById(TutorInterno.class, "giando26@gmail.com");
			
						
						if(emailTutor.length()==0 || tutorInterno==null ){
							
							
							%>
						<h6 class="card-subtitle">Nessuno Studente Presente</h6>
						<% 
							
						}else{
	
			
	List<ProgettoFormativo> listaPF = tutorInterno.getProgettiFormativi();
	if (listaPF.isEmpty()){
		%>
		<h6 class="card-subtitle">Nessuno Studente Presente</h6>
		<% 
	}else{
%>

						<h6 class="card-subtitle"></h6>
						<div class="table-responsive">
							<table id="demo-foo-addrow"
								class="table m-t-30 table-hover contact-list footable-loaded footable"
								data-page-size="10">
								<thead>
									<tr>
										<th class="footable-sortable footable-sorted">#<span
											class="footable-sort-indicator"></span></th>
										<th class="footable-sortable">Matricola<span
											class="footable-sort-indicator"></span></th>
										<th class="footable-sortable">Nome<span
											class="footable-sort-indicator"></span></th>
										<th class="footable-sortable">Cognome<span
											class="footable-sort-indicator"></span></th>
										<th class="footable-sortable">Azienda<span
											class="footable-sort-indicator"></span></th>
										<th class="footable-sortable">Ambito<span
											class="footable-sort-indicator"></span></th>
										<th class="footable-sortable">Stato Tirocinio<span
											class="footable-sort-indicator"></span></th>
										<th class="footable-sortable">Data Inizio<span
											class="footable-sort-indicator"></span></th>
										<th class="footable-sortable">Data Fine<span
											class="footable-sort-indicator"></span></th>
									</tr>
								</thead>
								<tbody>
									<% Iterator<?> it = listaPF.iterator(); 
									int i = 1;
									while (it.hasNext()) {
										ProgettoFormativo progettoFormativo = (ProgettoFormativo) it.next();
										
                                	   %>
									<tr class="footable-even" style="">
										<td><span class="footable-toggle"></span><%=i %></td>
										<td><%=progettoFormativo.getStudente().getMatricola() %></td>
										<td><%=progettoFormativo.getStudente().getNome() %></td>
										<td><%=progettoFormativo.getStudente().getCognome() %></td>
										<td><%=progettoFormativo.getAzienda()%></td>
										<td><%=progettoFormativo.getAmbito()%></td>
										<%
										if((progettoFormativo.getStato() >=0) && (progettoFormativo.getStato()<4)){%>
										<td><span class="label label-warning">In Attesa</span></td>
										
										<%}else if(progettoFormativo.getStato()==4){ %>

										<span class="label label-primary">Verificato</span>
										</td>
										<%}else if(progettoFormativo.getStato()==5){%>
										<td>
										
										<span class="label label-success">Approvato</span>
										</td>
										<%}else if(progettoFormativo.getStato()==6){%>
										<td>
										
										<span class="label label-danger">Rifiutato</span>
										</td>
										<%} %>

										<td><%=progettoFormativo.getDataInizio()%></td>
										<td><%=progettoFormativo.getDataFine() %></td>

									</tr>
									<% i++;		}
									%>

								</tbody>
							</table>
							
						</div>
						<%}}%>
						
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


