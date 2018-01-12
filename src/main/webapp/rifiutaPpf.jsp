<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ page import="it.unisa.libra.model.dao.IStudenteDao" %>
<%@ page import="it.unisa.libra.bean.Studente" %>
<%@ page import="it.unisa.libra.model.dao.IProgettoFormativoDao" %>
<%@ page import="it.unisa.libra.bean.ProgettoFormativo" %>
<%@ page import="javax.naming.InitialContext" %>
<%@page import="java.text.DateFormat" %>
<%@page import="java.text.SimpleDateFormat" %>
<%@page import="it.unisa.libra.util.FileUtils"%>
<%@page import="java.util.Base64"%>

<% 
	int propostaId = 0;
	try {
		propostaId = Integer.parseInt(request.getParameter("id"));
	} catch(NumberFormatException e) {
		
	}
	if(propostaId==0){
		response.sendRedirect("errore.jsp");
	}
	IProgettoFormativoDao pfDao = (IProgettoFormativoDao) new InitialContext().lookup("java:app/Libra/ProgettoFormativoJpa");
	ProgettoFormativo progettoFormativo = pfDao.findById(ProgettoFormativo.class, propostaId);
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
	                    <h3 class="text-themecolor m-b-0 m-t-0">Rifiuta Proposta</h3>
	                    <ol class="breadcrumb">
	                        <%
								if (session != null && session.getAttribute("utenteRuolo") != null) {
									String dashboard = request.getContextPath()
											+ "/dashboard".concat(session.getAttribute("utenteRuolo").toString()).concat(".jsp");
							%>
							<li class="breadcrumb-item"><a href="<%=dashboard%>">Home</a></li>
							<%} %>
	                        <li class="breadcrumb-item"><a href="<%=request.getContextPath()%>/dettaglioStudente?action=<%=Actions.DETTAGLIO_STUDENTE%>&email-studente=<%=progettoFormativo.getStudente().getUtenteEmail()%>">Dettaglio Studente</a></li>
	                        <li class="breadcrumb-item active">Rifiuta Proposta</li>
	                    </ol>
	                </div>
	                
	            </div>
	            <div class="card card-block">
		            	<h3 class="box-title m-b-0">Studente: <%=progettoFormativo.getStudente().getCognome() %> <%=progettoFormativo.getStudente().getNome() %></h3>
			            <div class="row mt-4">
			            	<div class="col-sm-2 col-md-1 mt-2">
			            		<%
								  String path= FileUtils.readBase64FromFile(FileUtils.PATH_PDF_PROGETTOF, progettoFormativo.getDocumento());
								  String doc="";
								  if(path != null){
								    doc = (new String(Base64.getUrlDecoder().decode(path), "UTF-8"));
								    doc = doc.substring(28);
								  }
								%>
								<a href="data:application/octet-stream;base64, <%=doc%>" download="<%=progettoFormativo.getId()%>.pdf">
			            	 	<i class="fa fa-file-pdf-o" style="font-size:60px;"></i></a>
			            	 </div>
			            	<div class="col-sm mt-2">
			            		<p class="text-left"><i>Inviata il <%=formatter.format(progettoFormativo.getDataInvio())%></i></p>
			            		<p class="text-left"><strong>Azienda: </strong><%=progettoFormativo.getAzienda().getNome() %></p>
			            		<p class="text-left"><strong>Note: </strong>
			            			<%if(progettoFormativo.getNote()==null) {%>
			            				<i>Nessuna nota</i>
			            			<%} else { %>
			            				<%=progettoFormativo.getNote() %>
			            			<%} %>
			            		</p>
			            	</div>
			            </div>
			            <div class="mt-4">
				            <form action="<%=request.getContextPath()%>/verificaProgettoFormativo" method="post">
				            	<input type="hidden" id="pf_idInput" name="pf_id" value="<%=propostaId%>">
				            	<div class="form-group">
							    	<label for="motRifiuto">Motivazione</label>
							    	<textarea class="form-control" id="motRifiuto" maxlength="500" rows="3" name="motivazione"></textarea>
							  	</div>
							  	<div class="row">
							  		<div class="col-6"><button type="button" id="rifiutaButton" class="btn btn-primary">Rifiuta</button></div>
		  							<div class="col-6"><a class="btn btn-secondary pull-right" href="<%=request.getContextPath()%>/dettaglioStudente?action=<%=Actions.DETTAGLIO_STUDENTE%>&email-studente=<%=progettoFormativo.getStudente().getUtenteEmail()%>">Annulla</a></div>
				            	</div>
				            </form>
				        </div>
		            </div>
		        <div class="modal fade" id="okDialogMsg" role="dialog">
		            <div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-body">
								L'operazione è avvenuta con successo!
							</div>
							<div class="modal-footer">
									<a class="btn btn-success" style="text-decoration: none; color: white;" href="<%=request.getContextPath()%>/dettaglioStudente?action=<%=Actions.DETTAGLIO_STUDENTE%>&email-studente=<%=progettoFormativo.getStudente().getUtenteEmail()%>">
										Ok 
									</a>
							</div>
						</div>
					</div>
				</div>
				<div class="modal fade" id="errorDialogMsg" role="dialog">
		            <div class="modal-dialog">
		           		<div class="modal-content">
							<div class="modal-header">
								<h4 class="modal-title">Ops!</h4>
							</div>
							<div class="modal-body" id="error-modal-body">
							</div>
							<div class="modal-footer">
									<a id="okButton" class="btn btn-warning" style="text-decoration: none; color: white;">
										Ok 
									</a>
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
    <script>
    $(document).ready(function() {
    	$("#rifiutaButton").click(function(e){
    		e.preventDefault();
    		
    		$.post('verificaProgettoFormativo', {
    			pf_id : $("#pf_idInput").val(),
    			motivazione : $("#motRifiuto").val()
    			},
    		function(data){
    			if(data == "ok"){
    				$('#okDialogMsg').modal({backdrop: 'static', keyboard: false});
    				
    			}else {
    				$("#error-modal-body").html(data);
    				$('#errorDialogMsg').modal({backdrop: 'static', keyboard: false});
    				$('#okButton').click(function() {
    					$('#errorDialogMsg').modal('toggle');
    				});
    			}
    			
    		});
    	});
    });
    </script>
</body>

</html>