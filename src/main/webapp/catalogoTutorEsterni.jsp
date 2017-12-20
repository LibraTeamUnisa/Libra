<%@page import="it.unisa.libra.bean.Azienda"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="it.unisa.libra.bean.TutorEsternoPK"%>
<%@page import="it.unisa.libra.model.dao.IAziendaDao"%>
<%@ page import="javax.naming.InitialContext" %>
<%@ page import="javax.naming.Context" %>
<%@page import="it.unisa.libra.bean.TutorEsterno"%>
<%@page import="it.unisa.libra.model.jpa.AziendaJpa"%>
<%@page import="java.util.*,it.unisa.*"%>
<%
String emailAzienda = (String) request.getSession().getAttribute("email");
emailAzienda = "azienda@prova.it";
IAziendaDao aziendaDao = (IAziendaDao) new InitialContext().lookup("java:app/Libra/AziendaJpa");
Azienda azienda = aziendaDao.findById(Azienda.class, emailAzienda);
Collection<?> tutorEsterni = (Collection<?>) azienda.getTutorEsterni();
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
				<div class="row" id="body1">
					<div class="col-12">
						<div class="card">
							<div class="card-block">
								<h4 class="card-title">Lista Tutor Aziendali</h4>

								<div class="table-responsive m-t-40">
									<div id="example23_wrapper" class="dataTables_wrapper">

										<div id="example23_filter" class="dataTables_filter">
											<label> Search: <input type="search" class=""
												placeholder="" aria-controls="example23"></label>
										</div>
										<table id="example23"
											class="display nowrap table table-hover table-striped table-bordered dataTable"
											cellspacing="0" width="100%" role="grid"
											aria-describedby="example23_info" style="width: 100%;">
											<thead>
												<tr role="row">
													<th class="sorting_asc" tabindex="0"
														aria-controls="example23" rowspan="1" colspan="1"
														aria-sort="ascending"
														aria-label="Cognome: activate to sort column descending"
														style="width: 77px;">Cognome</th>
													<th class="sorting" tabindex="0" aria-controls="example23"
														rowspan="1" colspan="1"
														aria-label="Nome: activate to sort column ascending"
														style="width: 85px;">Nome</th>
														<th class="sorting" tabindex="0" aria-controls="example23"
														rowspan="1" colspan="1"
														aria-label="Ambito: activate to sort column ascending"
														style="width: 85px;">Ambito</th>
													<th class="sorting" tabindex="0" aria-controls="example23"
														rowspan="1" colspan="1"
														aria-label="Data di Nascita: activate to sort column ascending"
														style="width: 72px;">Data di Nascita</th>
													<th class="sorting" tabindex="0" aria-controls="example23"
														rowspan="1" colspan="1"
														aria-label="Indirizzo: activate to sort column ascending"
														style="width: 85px;">Indirizzo</th>
													<th class="sorting" tabindex="0" aria-controls="example23"
														rowspan="1" colspan="1"
														aria-label="Telefono: activate to sort column ascending"
														style="width: 83px;">Telefono</th>
													<th class="sorting" tabindex="0" aria-controls="example23"
														rowspan="1" colspan="1"
														aria-label="Gestione Tutor: activate to sort column ascending"
														style="width: 83px;">Gestione Tutor</th>
												</tr>
											</thead>

											<tbody>
												<%
													if (tutorEsterni != null && tutorEsterni.size() != 0) {
														Iterator<?> it = tutorEsterni.iterator();
														
															while (it.hasNext()) {
																TutorEsterno bean = (TutorEsterno) it.next();
												%>
												<tr role="row" class="odd">
												
													<td class="sorting_1"><%=bean.getCognome()%></td>
													<td><%=bean.getNome()%></td>
								<!-- Ambito	 -->	<td><%=bean.getId().getAmbito()%></td> 
													<td><%=bean.getDataDiNascita()%></td>
													<td><%=bean.getIndirizzo()%></td>
													<td><%=bean.getTelefono()%></td>
													<td class="text-nowrap">
													
															<a href="gestioneTutorEsterno.jsp?action=modificaTutorEsterno&ambito=<%=bean.getId().getAmbito() %>" data-toggle="tooltip"
																data-original-title="Edit"
																class="fa fa-pencil text-inverse m-r-10"></a><a
																href="#" data-toggle="tooltip"
																data-original-title="Close" data-toggle="myLargeModalLabel"
																data-target=".modal fade bs-example-modal-lg"><i
																class="fa fa-close text-danger"></i> </a>


															<div class="modal fade bs-example-modal-lg" tabindex="-1"
																role="dialog" aria-labelledby="myLargeModalLabel"
																style="display: none;" aria-hidden="true">
																<div class="modal-dialog modal-lg">
																	<div class="modal-content">
																		<div class="modal-header">
																			<h4 class="modal-title" id="myLargeModalLabel">Rimozione
																				Tutor Esterno</h4>
																		</div>
																		<div class="modal-body">
																			<p>Sei sicuro di volere rimuovere questo tutor
																				esterno?</p>
																			<div class="row">
																				<div class="col-md-3 col-xs-6">
																					<strong>Nome</strong> <br>
																					<p class="text-muted"><%=bean.getNome()%>
																					</p>
																				</div>
																				<div class="col-md-3 col-xs-6">
																					<strong>Ambito</strong> <br>
																					<p class="text-muted"><%=bean.getId().getAmbito()%>
																					</p>
																				</div>
																				<div class="col-md-3 col-xs-6 b-r">
																					<strong>Cognome</strong> <br>
																					<p class="text-muted"><%=bean.getCognome()%>
																					</p>
																				</div>
																				<div class="col-md-3 col-xs-6 b-r">
																					<strong>Telefono</strong> <br>
																					<p class="text-muted"><%=bean.getTelefono()%>
																					</p>
																				</div>
																				<div class="col-md-3 col-xs-6 b-r">
																					<strong>Indirizzo</strong> <br>
																					<p class="text-muted"><%=bean.getIndirizzo()%></p>
																				</div>

																			</div>

																			<button style="float: left;" type="submit"
																				class="btn btn-info waves-effect text-right"
																				onclick="gestioneTutorEsternoServlet?action=rimuoviTutorEsterno&ambito=<%=bean.getId().getAmbito() %>"
																				data-dismiss="modal" id="buttonmodal">Rimuovi</button>

																			<button style="float: right;" type="button"
																				class="btn btn-danger waves-effect text-left"
																				data-dismiss="modal" id="buttonmodal">Close</button>
																		</div>
																	</div>
																	<!-- /.modal-content -->
																</div>
																<!-- /.modal-dialog -->
															</div>
															<!-- /.modal -->
													
													</td>
												</tr>
												<%
													}
														
													} else {
												%>
												<tr role="row" class="odd">
													<td colspan="7">Tutor non Trovato</td>
												</tr>
												<%
													}
												%>
											</tbody>
										</table>

									</div>
								</div>

							</div>
						</div>
					</div>
				</div>
			</div>
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
<style type="text/css">
#buttonmodal:hover, #buttonmodal:focus {
	background-color: orange;
	border-color: red;
}

#table1 {
	visibility: visible;
}

#table2 {
	visibility: hidden;
}

@media screen and (max-width: 700px) {
	#table1 {
		visibility: collapse;
	}
	#table2 {
		visibility: visible;
		font-size: 85%;
	}
}
</style>
</html>


