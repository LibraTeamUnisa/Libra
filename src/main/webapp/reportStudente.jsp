<%@page import="it.unisa.libra.bean.TutorInterno"%>
<%@page import="it.unisa.libra.model.dao.ITutorInternoDao"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@page import="it.unisa.libra.model.dao.IReportDao"%>
<%@page import="it.unisa.libra.model.dao.IStudenteDao"%>
<%@page import="it.unisa.libra.model.dao.IProgettoFormativoDao"%>
<%@page import="it.unisa.libra.bean.Studente"%>
<%@page import="it.unisa.libra.bean.ProgettoFormativo"%>
<%@page import="it.unisa.libra.bean.Report"%>
<%@ page import="javax.naming.InitialContext"%>
<%@ page import="javax.naming.Context"%>
<%@ page import="java.util.List"%>
<%@  page import="javax.servlet.*"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="java.util.Map.Entry"%>
<%@ page import="it.unisa.libra.bean.Report"%>
<%@ page import="it.unisa.libra.util.JsonUtils"%>
<%
	boolean badRequest = false;
	String ruolo = (String) request.getSession().getAttribute("utenteRuolo");
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
<link href="assets/plugins/bootstrap/css/bootstrap-table.css"
	rel="stylesheet">
<link href="assets/plugins/bootstrap/css/bootstrap-table.min.css"
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
			<div class="container-fluid">
				<!-- ============================================================== -->
				<!-- Container fluid  -->
				<!-- ============================================================== -->
				<%
					IStudenteDao studenteDao = (IStudenteDao) new InitialContext().lookup("java:app/Libra/StudenteJpa");

					IProgettoFormativoDao progettoFormativoDao = (IProgettoFormativoDao) new InitialContext()
							.lookup("java:app/Libra/ProgettoFormativoJpa");
					IReportDao reportDao = (IReportDao) new InitialContext().lookup("java:app/Libra/ReportJpa");
					ITutorInternoDao tutorInternoDao = (ITutorInternoDao) new InitialContext()
							.lookup("java:app/Libra/TutorInternoJpa");

					if (ruolo.equals("Studente")) {
				%>
				<div class="row page-titles">
					<div class="col-md-6 col-8 align-self-center">
						<h3 class="text-themecolor m-b-0 m-t-0">Report</h3>
						<ol class="breadcrumb">
							<li class="breadcrumb-item"><a href="dashboardStudente.jsp">Dashboard</a></li>
							<li class="breadcrumb-item active">Sezione Report</li>
						</ol>
					</div>
				</div>
				<%
					String emailStudente = (String) request.getSession().getAttribute("utenteEmail");
						Studente studente = studenteDao.findById(Studente.class, emailStudente);
						ProgettoFormativo progettoFormativo = progettoFormativoDao.getLastProgettoFormativoByStudente(studente);
						if (progettoFormativo == null){%>
						
						<h3>Non sei iscritto a nessun Progetto Formativo</h3>
				<%
					String dashboard = request.getContextPath() + "/dashboard".concat("Studente").concat(".jsp");
				%>
				<button type="button" class="btn btn-success"
					onclick="setTimeout(function(){window.location.href =' <%=dashboard%> ';},2000);">
					Dashboard</button>
						<%
						
						}else if ((progettoFormativo.getReports() == null)
								|| (progettoFormativo.getReports().isEmpty())) {
				%>

				<h1>Nessun report disponibile</h1>
				<%
					String dashboard = request.getContextPath() + "/dashboard".concat("Studente").concat(".jsp");
				%>
				<button type="button" class="btn btn-success"
					onclick="setTimeout(function(){window.location.href ='<%=dashboard%>';},2000);">
					Dashboard</button>

				<%
					if (progettoFormativo.getStato() == 4) {
				%>
				<button type="button" class="btn btn-success" data-toggle="modal"
					data-target="#myModal">Aggiungi Report</button>
				<input type="hidden" name="action"
					value=<%=Actions.AGGIUNGI_REPORT%> id="inputAction" />
				<%
					}
						} else {

							List<Report> listaReportStudenti = progettoFormativo.getReports();
							Iterator<?> it = listaReportStudenti.iterator();
				%>

				<form class="form-horizontal form-material" id="listReport">

					<div class="card">
						<div class="card-block">
							<h4 class="card-title"></h4>
							<h6 class="card-subtitle"></h6>
							<div class="bootstrap-table">
								<div class="fixed-table-container"
									style="height: 430px; margin-top: -40px;">
									<div class="table-responsive m-t-40">
										<table id="example23"
											class="display nowrap table table-hover table-striped table-bordered dataTable"
											cellspacing="0" width="100%" role="grid"
											aria-describedby="example23_info"
											style="width: 100%; margin-top: -1%;">
											<thead>
												<tr>
													<th style="width: 28%;" data-field="4"><div
															class="th-inner ">Data</div>
														<div class="fht-cell"></div></th>
													<th style="width: 28%;" data-field="5"><div
															class="th-inner ">Ora</div>
														<div class="fht-cell"></div></th>
													<th style="width: 28%;" data-field="6"><div
															class="th-inner ">Oggetto</div>
														<div class="fht-cell"></div></th>
													<th style="width: 25%;" data-field="6"><div
															class="th-inner ">Action</div>
														<div class="fht-cell"></div></th>
												</tr>
											</thead>
										</table>
										<div id="example23_wrapper" class="dataTables_wrapper"
											style="overflow-x: hidden; overflow-y: auto; height: 370px">
											<div class="fixed-table-loading"
												style="top: 41px; display: none;">Loading, please
												wait...</div>
											<table id="example23"
												class="display nowrap table table-hover table-striped table-bordered dataTable"
												cellspacing="0" width="100%" role="grid"
												aria-describedby="example23_info"
												style="width: 100%; margin-top: -2%;">
												<thead>
													<tr>
														<th style="width: 34%;" data-field="4"><div
																class="th-inner "></div>
															<div class="fht-cell"></div></th>
														<th style="width: 34%;" data-field="5"><div
																class="th-inner "></div>
															<div class="fht-cell"></div></th>
														<th style="width: 34%;" data-field="6"><div
																class="th-inner "></div>
															<div class="fht-cell"></div></th>
														<th style="width: 34%;" data-field="6"><div
																class="th-inner "></div>
															<div class="fht-cell"></div></th>
													</tr>
												</thead>
												<tbody>
													<%
														while (it.hasNext()) {
																	Report rep = (Report) it.next();
																	if (rep.getProgettoFormativo().getId() == progettoFormativo.getId()) {
													%>

													<tr class="tr-class-1">
														<td class="td-class-1" style="width: 34%;">
															<div class="th-inner">
																<%
																	int anno = rep.getId().getData().getYear() + 1900;
																					int mese = rep.getId().getData().getMonth() + 1;
																					String x = "" + anno + " / " + mese + " / " + rep.getId().getData().getDay();
																%>
																<%=x%>
															</div>
															<div class="fht-cell"></div>
														</td>

														<td class="td-class-1" style="width: 34%;">
															<div class="th-inner">
																<%
																	String y = "" + rep.getId().getData().getHours() + " : "
																							+ rep.getId().getData().getMinutes();
																%>
																<%=y%>
															</div>
															<div class="fht-cell"></div>
														</td>

														<td class="td-class-1" style="width: 34%;">
															<div class="th-inner">
																<%=rep.getTesto().subSequence(0, 5)%>...
															</div>
															<div class="fht-cell"></div>
														</td>
														<td class="td-class-1" style="width: 34%;">
															<div class="th-inner">
																<%
																	Long oo = rep.getId().getData().getTime();
																%>
																<%
																	if (progettoFormativo.getStato() != 4) {
																%>
																<button type="button" class="btn btn-warning"
																	disabled="disabled" id="<%=oo%>">ModificaReport</button>
																<%
																	} else {
																%>
																<button type="button" class="btn btn-warning"
																	onclick="$('#exampleModal<%=oo%>').modal('show')"
																	id="<%=oo%>">ModificaReport</button>
																<%
																	}
																%>
																<div class="modal fade" id="exampleModal<%=oo%>"
																	tabindex="-1" role="dialog"
																	aria-labelledby="exampleModalLabel" aria-hidden="true">
																	<div class="modal-dialog" role="document">
																		<div class="modal-content">
																			<div class="modal-header">
																				<h5 class="modal-title"
																					id="exampleModalLabel<%=oo%>">Modifica Report</h5>
																				<button type="button" class="close"
																					data-dismiss="modal" aria-label="Close">
																					<span aria-hidden="true">&times;</span>
																				</button>
																			</div>
																			<div class="modal-body">
																				<div class="form-group">
																					<div class="col-md-12">

																						<textarea class="form-control" rows="5"
																							id="test<%=oo%>"
																							style="width: 100%; height: auto;"
																							maxlength="500"
																							oninput='document.getElementById("testoReportModificato").value = this.value'
																							minlength="6"
																							onkeypress='document.getElementById("data").value =<%=oo%>'><%=rep.getTesto()%></textarea>
																						<input type="hidden" id="testoReportModificato"
																							name="testoReportModificato<%=oo%>">

																					</div>

																					<input type="hidden" name="action"
																						value=<%=Actions.MODIFICA_REPORT%>
																						id="inputActionModifica" /> <input type="hidden"
																						id="data">


																				</div>
																			</div>
																			<div class="modal-footer">
																				<button type="submit" class="btn btn-success"
																					style="float: left;" id="confermaModifica"
																					onclick='modifica()'>Salva le modifiche</button>

																				<button type="button" class="btn btn-danger"
																					data-dismiss="modal" style="float: right;">Chiudi</button>
																			</div>
																			<script>
																				function modifica() {
																					$("#listReport").submit(function(e) {
																										e.preventDefault();
																										$.post(
																														'ReportStudenteServlet',
																														{
																															action : $("#inputActionModifica").val(),
																															data : $("#data").val(),
																															testoReportModificato : $("#testoReportModificato").val()
																														},
																														function(data) {
																															$("#confermaModifica").prop("disabled",true);
																															window.setTimeout(function() {
																																				location.reload();
																																			},1000);

																														});
																									});

																				}
																			</script>

																		</div>
																	</div>
																</div>
															</div>
															<div class="fht-cell"></div>
														</td>

													</tr>


													<%
														}
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
				</form>
				<%
					if (progettoFormativo.getStato() == 4) {
				%>
				<button type="button" class="btn btn-success" data-toggle="modal"
					data-target="#myModal">Aggiungi Report</button>

				<input type="hidden" name="action"
					value=<%=Actions.AGGIUNGI_REPORT%> id="inputAction" />
				<%
					} 
						}
					} else if ((ruolo.equals("Presidente")) || (ruolo.equals("Segreteria"))) {
				%>
				<div class="row page-titles">
					<div class="col-md-6 col-8 align-self-center">
						<h3 class="text-themecolor m-b-0 m-t-0">Report</h3>
						<ol class="breadcrumb">
							<%
								if (ruolo.equals("Presidente")) {
							%>
							<li class="breadcrumb-item"><a
								href="dashboardPresidente.jsp">Dashboard</a></li>
							<%
								} else if (ruolo.equals("Segreteria")) {
							%>
							<li class="breadcrumb-item"><a
								href="dashboardSegreteria.jsp">Dashboard</a></li>
							<%
								}
							%>
							<li class="breadcrumb-item active">Sezione Report</li>
						</ol>
					</div>
				</div>

				<div class="card">
					<div class="card-block"><%
					int i = 0;
														List<Report> listaReportStudentia = reportDao.findAll(Report.class);
														if((listaReportStudentia.isEmpty())||(listaReportStudentia==null)){
														
														%>
						<h1>Non sono presenti Report</h1>
				<%
												String dashboard="";
												if (ruolo.equals("Presidente")) 
													dashboard = request.getContextPath() + "/dashboard".concat("Presidente").concat(".jsp");
												else if (ruolo.equals("Segreteria"))
													
											dashboard = request.getContextPath() + "/dashboard".concat("Segreteria").concat(".jsp");
												
												
												%>
				<button type="button" class="btn btn-info"
					onclick="setTimeout(function(){window.location.href ='<%=dashboard%>';},2000);">
					Dashboard</button>
						<%}else{ %>
						
						<h4 class="card-title"></h4>
						<h6 class="card-subtitle"></h6>
						<div class="bootstrap-table">
							<div class="fixed-table-container"
								style="height: 430px; margin-top: -40px;">
								<div class="table-responsive m-t-40">
									<table id="example23"
										class="display nowrap table table-hover table-striped table-bordered dataTable"
										cellspacing="0" width="100%" role="grid"
										aria-describedby="example23_info"
										style="width: 100%; margin-top: -1%;">
										<thead>
											<tr>
												<th style="width: 8%;" data-field="0"><div
														class="th-inner ">Nome</div>
													<div class="fht-cell"></div></th>
												<th style="width: 8%;" data-field="1"><div
														class="th-inner ">Cognome</div>
													<div class="fht-cell"></div></th>
												<th style="width: 8%;" data-field="2"><div
														class="th-inner ">Matricola</div>
													<div class="fht-cell"></div></th>
												<th style="width: 8%;" data-field="3"><div
														class="th-inner ">Azienda</div>
													<div class="fht-cell"></div></th>
												<th style="width: 8%;" data-field="4"><div
														class="th-inner ">Data</div>
													<div class="fht-cell"></div></th>

												<th style="width: 8%;" data-field="6"><div
														class="th-inner ">Oggetto</div>
													<div class="fht-cell"></div></th>
												<th style="width: 8%;" data-field="6"><div
														class="th-inner ">Action</div>
													<div class="fht-cell"></div></th>
											</tr>
										</thead>
									</table>
									<div id="example23_wrapper" class="dataTables_wrapper"
										style="overflow-x: hidden; overflow-y: auto; height: 370px">
										<div class="fixed-table-loading"
											style="top: 41px; display: none;">Loading, please
											wait...</div>
										<table id="example23"
											class="display nowrap table table-hover table-striped table-bordered dataTable"
											cellspacing="0" width="100%" role="grid"
											aria-describedby="example23_info"
											style="width: 100%; margin-top: -2%;">
											<thead>
												<tr>
													<th style="width: 8%;" data-field="0"><div
															class="th-inner "></div>
														<div class="fht-cell"></div></th>
													<th style="width: 8%;" data-field="1"><div
															class="th-inner "></div>
														<div class="fht-cell"></div></th>
													<th style="width: 8%;" data-field="2"><div
															class="th-inner "></div>
														<div class="fht-cell"></div></th>
													<th style="width: 8%;" data-field="3"><div
															class="th-inner "></div>
														<div class="fht-cell"></div></th>
													<th style="width: 8%;" data-field="4"><div
															class="th-inner "></div>
														<div class="fht-cell"></div></th>

													<th style="width: 8%;" data-field="6"><div
															class="th-inner "></div>
														<div class="fht-cell"></div></th>
													<th style="width: 8%;" data-field="6"><div
															class="th-inner "></div>
														<div class="fht-cell"></div></th>
												</tr>
											</thead>
											<tbody>
												<%

												Iterator<Report> listReport = listaReportStudentia.iterator(); 
														while (listReport.hasNext()) {

															Report report = listReport.next();
															ProgettoFormativo progForm = report.getProgettoFormativo();

															int anno = report.getId().getData().getYear() + 1900;
															int mese = report.getId().getData().getMonth() + 1;
															String x11 = "" + anno + " / " + mese + " / " + report.getId().getData().getDay();
															String Nome = progForm.getStudente().getNome();
															String Cognome = progForm.getStudente().getCognome();
												%>
												<tr id="tr-id-<%=i + 1%>" class="tr-class-<%=i + 1%>"
													data-index="<%=i%>">
													<td id="td-id-<%=i + 1%>" class="td-class-<%=i + 1%>"
														style="width: 13%;"><%=Nome%></td>
													<td id="td-id-<%=i + 1%>" class="td-class-<%=i + 1%>"
														style="width: 14%;"><%=Cognome%></td>
													<td id="td-id-<%=i + 1%>" class="td-class-<%=i + 1%>"
														style="width: 14%;"><%=progForm.getStudente().getMatricola()%></td>
													<td id="td-id-<%=i + 1%>" class="td-class-<%=i + 1%>"
														style="width: 13%;"><%=progForm.getAzienda().getNome()%></td>
													<td id="td-id-<%=i + 1%>" class="td-class-<%=i + 1%>"
														style="width: 13%;"><%=x11%></td>


													<td id="td-id-<%=i + 1%>" class="td-class-<%=i + 1%>"
														style="width: 10%;"><%=report.getTesto().subSequence(0, 5)%>...</td>

													<%
														Long reportDa = report.getId().getData().getTime();
													%>
													<td id="td-id-<%=i + 1%>" class="td-class-<%=i + 1%>"
														style="width: 10%;"><button type="button"
															class="btn btn-success" data-toggle="modal"
															data-target="#exampleModalReport<%=reportDa%>">Visualizza
															Report</button>
														<div class="modal fade show"
															id="exampleModalReport<%=reportDa%>" tabindex="-1"
															role="dialog" aria-labelledby="exampleModalLabel1">
															<div class="modal-dialog" role="document">
																<div class="modal-content">
																	<div class="modal-header">
																		<h4 class="modal-title" id="exampleModalLabel1">Report</h4>
																		<label style="margin-top: 8px; margin-left: 5%;"
																			for="recipient-name" class="control-label"
																			id="StudenteNomeCognomeMatricola<%=reportDa%>"><%=Cognome%>
																			<%=Nome%>,Matricola <%=progForm.getStudente().getMatricola()%></label>

																		<button type="button" class="close"
																			data-dismiss="modal">&times;</button>

																	</div>
																	<div class="modal-body"
																		style="overflow-x: hidden; overflow-y: auto; height: 200px;">
																		<form>
																			<div class="form-group">
																				<p id="Report<%=reportDa%>"><%=report.getTesto()%></p>
																			</div>
																		</form>
																	</div>
																	<div class="modal-footer">
																		<button type="button" class="btn btn-danger"
																			data-dismiss="modal">Close</button>
																	</div>
																</div>
															</div>
														</div></td>

												</tr>

												<%
													i++;
														}
												%>
											</tbody>
										</table>
									</div>
								</div>

							</div>
						</div>
						<%} %>
					</div>
				</div>
				<%
					} else if (ruolo.equals("TutorInterno")) {
				%>
				<div class="row page-titles">
					<div class="col-md-6 col-8 align-self-center">
						<h3 class="text-themecolor m-b-0 m-t-0">Report</h3>
						<ol class="breadcrumb">

							<li class="breadcrumb-item"><a
								href="dashboardTutorInterno.jsp">Dashboard</a></li>

							<li class="breadcrumb-item active">Sezione Report</li>
						</ol>
					</div>
				</div>

				<div class="card">
					<div class="card-block">
					<%
													int i = 0;
														String emailTutor = (String) request.getSession().getAttribute("utenteEmail");

														TutorInterno tutorInterno = tutorInternoDao.findById(TutorInterno.class, emailTutor);

														List<Report> listaReportStudentit = reportDao.findAll(Report.class);
														
														if((tutorInterno==null)||(listaReportStudentit==null)||(listaReportStudentit.isEmpty())){%>
												<h1>Non sono presenti Report</h1>
				<%
													String dashboard = request.getContextPath() + "/dashboard".concat("TutorInterno").concat(".jsp");
												%>
				<button type="button" class="btn btn-info"
					onclick="setTimeout(function(){window.location.href ='<%=dashboard%>';},2000);">
					Dashboard</button>

						<%}else{ %>
						<h4 class="card-title"></h4>
						<h6 class="card-subtitle"></h6>
						<div class="bootstrap-table">
							<div class="fixed-table-container"
								style="height: 430px; margin-top: -40px;">
								<div class="table-responsive m-t-40">
									<table id="example23"
										class="display nowrap table table-hover table-striped table-bordered dataTable"
										cellspacing="0" width="100%" role="grid"
										aria-describedby="example23_info"
										style="width: 100%; margin-top: -1%;">
										<thead>
											<tr>
												<th style="width: 8%;" data-field="0"><div
														class="th-inner ">Nome</div>
													<div class="fht-cell"></div></th>
												<th style="width: 8%;" data-field="1"><div
														class="th-inner ">Cognome</div>
													<div class="fht-cell"></div></th>
												<th style="width: 8%;" data-field="2"><div
														class="th-inner ">Matricola</div>
													<div class="fht-cell"></div></th>
												<th style="width: 8%;" data-field="3"><div
														class="th-inner ">Azienda</div>
													<div class="fht-cell"></div></th>
												<th style="width: 8%;" data-field="4"><div
														class="th-inner ">Data</div>
													<div class="fht-cell"></div></th>

												<th style="width: 8%;" data-field="6"><div
														class="th-inner ">Oggetto</div>
													<div class="fht-cell"></div></th>
												<th style="width: 8%;" data-field="6"><div
														class="th-inner ">Action</div>
													<div class="fht-cell"></div></th>
											</tr>
										</thead>
									</table>
									<div id="example23_wrapper" class="dataTables_wrapper"
										style="overflow-x: hidden; overflow-y: auto; height: 370px">
										<div class="fixed-table-loading"
											style="top: 41px; display: none;">Loading, please
											wait...</div>
										<table id="example23"
											class="display nowrap table table-hover table-striped table-bordered dataTable"
											cellspacing="0" width="100%" role="grid"
											aria-describedby="example23_info"
											style="width: 100%; margin-top: -2%;">
											<thead>
												<tr>
													<th style="width: 8%;" data-field="0"><div
															class="th-inner "></div>
														<div class="fht-cell"></div></th>
													<th style="width: 8%;" data-field="1"><div
															class="th-inner "></div>
														<div class="fht-cell"></div></th>
													<th style="width: 8%;" data-field="2"><div
															class="th-inner "></div>
														<div class="fht-cell"></div></th>
													<th style="width: 8%;" data-field="3"><div
															class="th-inner "></div>
														<div class="fht-cell"></div></th>
													<th style="width: 8%;" data-field="4"><div
															class="th-inner "></div>
														<div class="fht-cell"></div></th>
													<th style="width: 8%;" data-field="6"><div
															class="th-inner "></div>
														<div class="fht-cell"></div></th>
													<th style="width: 8%;" data-field="6"><div
															class="th-inner "></div>
														<div class="fht-cell"></div></th>
												</tr>
											</thead>
											<tbody>

												<%
														Iterator<Report> listd = listaReportStudentit.iterator();
														while (listd.hasNext()) {
															Report reportprfo = listd.next();
															if ((reportprfo.getProgettoFormativo().getTutorInterno().getUtenteEmail())
																	.equalsIgnoreCase(emailTutor)) {

																int anno = reportprfo.getId().getData().getYear() + 1900;
																int mese = reportprfo.getId().getData().getMonth() + 1;
																String x11 = "" + anno + " / " + mese + " / " + reportprfo.getId().getData().getDay();
																String Nome = reportprfo.getProgettoFormativo().getStudente().getNome();
																String Cognome = reportprfo.getProgettoFormativo().getStudente().getCognome();
												%>

												<tr id="tr-id-<%=i + 1%>" class="tr-class-<%=i + 1%>"
													data-index="<%=i%>">
													<td id="td-id-<%=i + 1%>" class="td-class-<%=i + 1%>"
														style="width: 13%;"><%=Nome%></td>
													<td id="td-id-<%=i + 1%>" class="td-class-<%=i + 1%>"
														style="width: 14%;"><%=Cognome%></td>
													<td id="td-id-<%=i + 1%>" class="td-class-<%=i + 1%>"
														style="width: 14%;"><%=reportprfo.getProgettoFormativo().getStudente().getMatricola()%></td>
													<td id="td-id-<%=i + 1%>" class="td-class-<%=i + 1%>"
														style="width: 13%;"><%=reportprfo.getProgettoFormativo().getAzienda().getNome()%></td>
													<td id="td-id-<%=i + 1%>" class="td-class-<%=i + 1%>"
														style="width: 13%;"><%=x11%></td>


													<td id="td-id-<%=i + 1%>" class="td-class-<%=i + 1%>"
														style="width: 10%;"><%=reportprfo.getTesto().subSequence(0, 5)%>...</td>

													<%
														Long reportDa = reportprfo.getId().getData().getTime();
													%>
													<td id="td-id-<%=i + 1%>" class="td-class-<%=i + 1%>"
														style="width: 10%;"><button type="button"
															class="btn btn-success" data-toggle="modal"
															data-target="#exampleModalReport<%=reportDa%>">Visualizza
															Report</button>
														<div class="modal fade show"
															id="exampleModalReport<%=reportDa%>" tabindex="-1"
															role="dialog" aria-labelledby="exampleModalLabel1">
															<div class="modal-dialog" role="document">
																<div class="modal-content">
																	<div class="modal-header">
																		<h4 class="modal-title" id="exampleModalLabel1">Report</h4>
																		<label style="margin-top: 8px; margin-left: 5%;"
																			for="recipient-name" class="control-label"
																			id="StudenteNomeCognomeMatricola<%=reportDa%>"><%=Cognome%>
																			<%=Nome%>,Matricola <%=reportprfo.getProgettoFormativo().getStudente().getMatricola()%></label>

																		<button type="button" class="close"
																			data-dismiss="modal">&times;</button>

																	</div>
																	<div class="modal-body"
																		style="overflow-x: hidden; overflow-y: auto; height: 200px;">
																		<form>
																			<div class="form-group">
																				<p id="Report<%=reportDa%>"><%=reportprfo.getTesto()%></p>
																			</div>
																		</form>
																	</div>
																	<div class="modal-footer">
																		<button type="button" class="btn btn-danger"
																			data-dismiss="modal">Close</button>
																	</div>
																</div>
															</div>
														</div></td>

												</tr>


												<%
													i++;
															}
														}
												%>
											</tbody>
										</table>
									</div>
								</div>

							</div>
						</div>
						<%} %>
					</div>
				</div>
				<%
					} else {
						badRequest = true;
					}
					if (badRequest) {
						//ERRORE: BAD REQUEST
				%>
				<p id="badRequest" style="visibility: hidden;"></p>
				<%
					}
				%>
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
								<button id="button1" type="button" class="btn btn-danger">
									<a class="btn btn-primary" href="dashboardStudente.jsp"
										style="text-decoration: none; color: white;"> Dashboard </a>
								</button>
							</div>
						</div>

					</div>
				</div>
				<!-- Modal -->
				<form class="form-horizontal form-material" id="reportform">
					<div class="modal fade" id="myModal" role="dialog">

						<div class="modal-dialog modal-lg">
							<div class="modal-content">
								<div class="modal-header">
									<h4 class="modal-title">Aggiungi Report</h4>
									<button type="button" class="close" data-dismiss="modal">&times;</button>

								</div>
								<div class="modal-body">
									<div class="form-group">

										<div class="col-md-12">
											<textarea id="testoNuovoReport" minlength="6"
												class="form-control form-control-line" rows="5"
												placeholder="Scrivi qui il tuo Report" maxlength="500"  
												onkeydown="reportColor()"></textarea>
											<script>
												function reportColor(){
													if((($("#testoNuovoReport").val().length) >= 4)&&(($("#testoNuovoReport").val().length) <= 8))
													{$("#confermaAggiunta").prop("disabled", false);
													} else{
														$("#confermaAggiunta").prop("disabled", false);
													}
												}
												
											</script>
											<script>
												function reportColorText(){
													if(( $("#testoReportModificato").val().length) < 6){
														$("#confermaModifica").prop("disabled", true);
													} else if((($("#testoReportModificato").val().length) >= 4)&&(($("#testoReportModificato").val().length) <= 8))
													{$("#confermaModifica").prop("disabled", false);
													} else{
														$("#confermaModifica").prop("disabled", false);
													}
												}
												</script>
										</div>

									</div>
								</div>
								<div class="modal-footer">
									<button type="submit"
										class="btn btn-success waves-effect waves-light m-r-10"
										id="confermaAggiunta" style="float: left;" onclick="cambia()" >Conferma</button>
										<script type="text/javascript">
										function cambia(){
											if(( $("#testoNuovoReport").val().length) < 6){

												$("#confermaAggiunta").prop("disabled", true);
											}
											
											
										}</script>
									<button type="button" class="btn btn-danger"
										style="float: right;" data-dismiss="modal">Close</button>
								</div>
							</div>
						</div>

					</div>

				</form>


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
			if ($("#badRequest").length) {
				$("#modalBadRequest").modal('show');
			}
		})

		$(document).ready(function() {

			$("#reportform").submit(function(e) {
				$("#myModal").modal("hide");

				e.preventDefault();
				$.post('ReportStudenteServlet', {
					action : $("#inputAction").val(),
					testoNuovoReport : $("#testoNuovoReport").val()

				}, function(data) {
					$("#confermaAggiunta").prop("disabled", true);
					window.setTimeout(function() {
						location.reload();
					}, 1000);

				});
			});
		})
	</script>

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

	<script
		src="https://cdn.datatables.net/buttons/1.2.2/js/buttons.flash.min.js"></script>
	<script
		src="https://cdn.datatables.net/buttons/1.2.2/js/dataTables.buttons.min.js"></script>
</body>

</html>