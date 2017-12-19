<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
<%@page import="it.unisa.libra.model.jpa.StudenteJpa" %>
<%@page import="it.unisa.libra.model.dao.IStudenteDao" %>
<%@page import="it.unisa.libra.model.jpa.UtenteJpa" %>
<%@page import="it.unisa.libra.model.jpa.GenericJpa" %>
<%@page import="it.unisa.libra.model.dao.IUtenteDao" %>
<%@page import="it.unisa.libra.model.jpa.ProgettoFormativoJpa" %>
<%@page import="it.unisa.libra.model.dao.IProgettoFormativoDao" %>
<%@page import="it.unisa.libra.bean.Studente" %>
<%@page import="it.unisa.libra.bean.Utente" %>
<%@page import="it.unisa.libra.bean.ProgettoFormativo" %>
<%@page import="java.util.List" %>
<%@page import="java.util.Iterator" %>
<%@page import="java.util.Date" %>
<%@page import="javax.naming.InitialContext" %>
<%@page import="java.text.DateFormat" %>
<%@page import="java.text.SimpleDateFormat" %>

<%
	IStudenteDao studenteDao = (IStudenteDao) new InitialContext().lookup("java:app/Libra/StudenteJpa");
	IUtenteDao utenteDao = (IUtenteDao) new InitialContext().lookup("java:app/Libra/UtenteJpa");
	IProgettoFormativoDao progettoFormativoDao = (IProgettoFormativoDao) new InitialContext().lookup("java:app/Libra/ProgettoFormativoJpa");
	Iterator<Studente> listaStudenti = studenteDao.findAll(Studente.class).iterator();
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
<!-- DataTable style -->
<link href="css/datatables.css" rel="stylesheet">
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
				<h1>Lista Studenti</h1>
				<div class="card wizard-card" style="padding: 1%">
					<div class="table-responsive">
						<table class="table">
							<thead>
								<tr>
									<th></th>
									<th>Nome</th>
									<th>Stato</th>
									<th>Data Invio</th>
								</tr>
							</thead>
							<tbody>
								<%
								while(listaStudenti.hasNext()) {
									Studente studente = listaStudenti.next();
									Utente utente = utenteDao.findById(Utente.class, studente.getUtenteEmail());
									ProgettoFormativo progettoFormativo = progettoFormativoDao.getLastProgettoFormativoByStudente(studente);
								%>
									<tr>
										<td><img src="<%=utente.getImgProfilo()%>" alt="user" width="40" class="img-circle"></td>
										<td><%=studente.getCognome() %> <%=studente.getNome() %></td>
										<td>
										<%if(progettoFormativo==null) {%>
											
										<%} else if(progettoFormativo.getStato()==0){%>
											<span class="label label-info">Disponibile</span>
										<%}else if(progettoFormativo.getStato()>0 && progettoFormativo.getStato()<4){%>
											<span class="label label-warning">In Attesa</span>
										<%}else if(progettoFormativo.getStato()==4){ %>
											<span class="label label-info">Verificato</span>
										<%}else if(progettoFormativo.getStato()==5){%>
											<span class="label label-success">Approvato</span>
										<%}else if(progettoFormativo.getStato()==6){%>
											<span class="label label-danger">Rifiutato</span>
										<%} %>
										</td>
										
										<%if(progettoFormativo!=null && progettoFormativo.getDataInvio()!=null) {%>
										<td>
											<%=formatter.format(progettoFormativo.getDataInvio())%>
										</td>
										<%} else {%>
											<td></td>
										<%} %>
									</tr>
								<%
								}
								%>
							</tbody>
						</table>
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
	<script src="js/datatables.js"></script>
	<script>
		var table;
		$(document).ready(function() {
			table = $('.table').DataTable({
				"paging": true,
				"searching": true,
				"pageLength": 10,
				"columnDefs": [	
					{ "searchable": false, "targets": 0 },
					{ "searchable": false, "targets": 2 },
					{ "searchable": false, "targets": 3 },
				  ],
				"initComplete" : function() {
						var input = $('.dataTables_filter input').unbind();
						self = this.api();
						$searchButton = $('<button class="btn btn-primary btn-sm">')
								   .text('search')
								   .click(function() {
										var text = input.val();
										if(/^([a-zA-Z]{0,100})$/.test(text)==false) {
											input.val("Error");
										}else
											self.search(input.val()).draw();
										
								   }),
						$('.dataTables_filter').append($searchButton);
					}
			  }); 
			
			});
		</script>
</body>

</html>


