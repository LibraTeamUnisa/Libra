<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="it.unisa.libra.bean.TutorEsternoPK"%>
<%@ page import="it.unisa.libra.bean.Azienda"%>
<%@ page import="it.unisa.libra.model.dao.ITutorEsternoDao"%>
<%@ page import="javax.naming.InitialContext"%>
<%@ page import="javax.naming.Context"%>
<%@ page import="it.unisa.libra.bean.TutorEsterno"%>
<%@ page import="it.unisa.libra.model.jpa.AziendaJpa"%>
<%@ page import="java.util.*,it.unisa.*"%>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.text.SimpleDateFormat" %>
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
  i.fa 
  {
  display: inline-block;
  border-radius: 60px;
  box-shadow: 0px 3px 10px #888;
  padding: 0.5em 0.6em;
  }
  
  i.fa-plus
  {
    color:white!important;
    background-color:#FF8F00;
  }
  
  i.fa-pencil
  {
    color:white!important;
    background-color:#FF8F00;
  }
  
  i.fa-close
  {
    color:white!important;
    background-color:#f62d51;
  }
</style>
</head>
<%!
private String parseDate(Date date)
{

	   SimpleDateFormat dFormat=new SimpleDateFormat("dd/MM/yyyy");

	   return dFormat.format(date);

}
%>
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
			
			<%
				String emailAzienda = (String) request.getSession().getAttribute("utenteEmail");
				ITutorEsternoDao tutorDao = (ITutorEsternoDao) new InitialContext().lookup("java:app/Libra/TutorEsternoJpa");
				List<TutorEsterno> tutorEsterni = (List<TutorEsterno>) tutorDao.findByEmailAzienda(emailAzienda);
			%>


			<div class="container-fluid">
			
			
				<div class="row page-titles">
                    <div class="col-md-6 col-8 align-self-center">
                        <h3 class="text-themecolor m-b-0 m-t-0">Lista Tutor</h3>
                        <ol class="breadcrumb">
                            <li class="breadcrumb-item"><a href="<%=JspPagesIndex.DASHBOARD_AZIENDA.substring(1)%>">Dashboard </a></li>
                            <li class="breadcrumb-item active">Gestione Tutor Esterno</li>
					    </ol>
                    </div>
                </div>
			
			
				<div class="row" id="body1">
					<div class="col-12">
						<div class="card">
							<div class="card-block">


								<!-- ============================================================== -->
								<!-- Modal per il risultato della rimozione  -->
								<!-- ============================================================== -->
								<div class="modal fade" id="modalResult" role="dialog">
									<div class="modal-dialog"><div class="modal-content">
											<div class="modal-header">
												<h4 class="modal-title">Rimuovi Tutor Esterno</h4>
											</div>
											<div class="modal-body">
												<p id="modalMessage"></p>
											</div>
											<div class="modal-footer">
											<a class="btn btn-warning"
												href="javascript:window.location.reload();"
												style="text-decoration: none; color: white;"> 
												Ok 
											</a>
											</div>
										</div>
									</div>
								</div>
								<!-- Fine modal -->


								<!-- ============================================================== -->
								<!-- Modal per la conferma della rimozione  -->
								<!-- ============================================================== -->
								<div class="modal fade" id="my_modal" role="dialog">
									<div class="modal-dialog">
										<div class="modal-content">
											<div class="modal-header">
												<h4 class="modal-title" id="myLargeModalLabel">
												Rimozione Tutor Esterno
												</h4>
												<button type="button" class="close" data-dismiss="modal">&times;</button>
											</div>
											<div class="modal-body">
												<p>Sei sicuro di volere rimuovere questo tutor esterno?</p>
												<div>
													<form id="formRimuoviTutor">
														<input type="hidden" name="ambito" id="inputAmbito" /> 
														<input type="hidden" name="action" id="inputAction" />
														<button type="submit" style="float: left;"
															class="btn btn-danger waves-effect text-right"
															id="buttonRimuoviModal">
														Rimuovi
														</button>
													</form>
													<button style="float: right;" type="button"
														class="btn btn-warning waves-effect text-left"
														data-dismiss="modal" id="buttonmodal">
													Annulla
													</button>
												</div>
											</div>
										</div>
										<!-- /.modal-content -->
									</div>
									<!-- /.modal-dialog -->
								</div>
								<!-- /.modal -->


<!-- ============================================================== -->
<!-- Script per il caricamento dati  -->
<!-- ============================================================== -->
<script type="text/javascript">
function funzioneApriModal(amb) {
	
	$("#inputAction").val('<%=Actions.RIMUOVI_TUTOR_ESTERNO%>');
	$("#inputAmbito").val(amb);
}
</script>



	<%	if (tutorEsterni != null && tutorEsterni.size() != 0) { %>

			<div class="row"><div class="col-12">
			<a class="pull-right" href="gestioneTutorEsterno.jsp?action=aggiungiTutorEsterno"
			data-toggle="tooltip" data-original-title="Aggiungi"> 
			<i class="fa fa-plus text-inverse m-r-10"></i>
			</a>
			</div></div>
			
			
								<div class="table-responsive m-t-40">
									<div id="example23_wrapper" class="dataTables_wrapper">

										<table id="example23"
											class="display nowrap table table-hover table-striped table-bordered dataTable"
											cellspacing="0" width="100%" role="grid"
											aria-describedby="example23_info" style="width: 100%;">
											<thead>
												<tr role="row">
													<th tabindex="0" aria-controls="example23" rowspan="1"
														colspan="1" style="width: 77px;">Cognome</th>
													<th tabindex="0" aria-controls="example23" rowspan="1"
														colspan="1" style="width: 85px;">Nome</th>
													<th tabindex="0" aria-controls="example23" rowspan="1"
														colspan="1" style="width: 85px;">Ambito</th>
													<th tabindex="0" aria-controls="example23" rowspan="1"
														colspan="1" style="width: 72px;">Data di Nascita</th>
													<th tabindex="0" aria-controls="example23" rowspan="1"
														colspan="1" style="width: 85px;">Indirizzo</th>
													<th tabindex="0" aria-controls="example23" rowspan="1"
														colspan="1" style="width: 83px;">Telefono</th>
													<th tabindex="0" aria-controls="example23" rowspan="1"
														colspan="1" style="width: 83px;">Gestisci</th>
												</tr>
											</thead>

											<tbody>
												<%													
												Iterator<?> it = tutorEsterni.iterator();
														while (it.hasNext()) {
															TutorEsterno bean = (TutorEsterno) it.next();
															String ambito = bean.getId().getAmbito();
												%>
												<tr role="row" class="odd">

													<td><%=bean.getCognome()%></td>
													<td><%=bean.getNome()%></td>
													<!-- Ambito	 -->
													<td><%=ambito%></td>
													<td><%=parseDate(bean.getDataDiNascita())%></td>
													<td><%=bean.getIndirizzo()%></td>
													<td><%=bean.getTelefono()%></td>
													<td class="text-nowrap"><a
														href="gestioneTutorEsterno.jsp?action=<%=Actions.MODIFICA_TUTOR_ESTERNO%>&ambito=<%= URLEncoder.encode(ambito,"UTF-8") %>"
														data-toggle="tooltip" data-original-title="Modifica"> <i
															class="fa fa-pencil text-inverse m-r-10"></i>
													</a> <span data-toggle="tooltip" data-original-title="Rimuovi"><a href="#" onclick="funzioneApriModal('<%=ambito%>')"
														data-original-title="Rimuovi" data-toggle="modal"
														data-target="#my_modal">
															<i class="fa fa-close text-danger"></i>
													</a></span></td>
												</tr>
												<%
													}
												%>
												</tbody>
												</table>
												</div>
								</div>
												<%
													} else {
												%>
												<h5>Non ci sono tutor.</h5>
												<a class="btn btn-warning" href="gestioneTutorEsterno.jsp?action=aggiungiTutorEsterno">Aggiungi</a>
												<%
													}
												%>
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


<script>
$(document).ready(function() {
	$("#formRimuoviTutor").submit(function(e) {
		e.preventDefault();
		$("#my_modal").modal('hide');
		$.post('gestioneTutorEsternoServlet', {
		action : $("#inputAction").val(),
		ambito : $("#inputAmbito").val()
		},
		function(data) {
			if (data == "ok") {
				$("#modalMessage").text("L'operazione &egrave; avvenuta correttamente");
			} else {
				$("#modalMessage").text(data);
			}
			$("#modalResult").modal('show');
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
	<script src="assets/plugins/styleswitcher/jQuery.style.switcher.js"></script>
</body>
</html>