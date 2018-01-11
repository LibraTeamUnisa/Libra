<%@page import="java.util.Base64"%>
<%@page import="it.unisa.libra.util.FileUtils"%>
<%@page import="it.unisa.libra.model.dao.IProgettoFormativoDao"%>
<%@page import="it.unisa.libra.util.StatoPf"%>
<%@page import="it.unisa.libra.bean.ProgettoFormativo"%>
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
    <link rel="icon" type="image/png" sizes="16x16" href="assets/images/favicon.png">
<title>Libra</title>
    <!-- Bootstrap Core CSS -->
    <link href="css/datatables.css" rel="stylesheet">
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
					<h3 class="text-themecolor m-b-0 m-t-0">Progetti Formativi</h3>
					<ol class="breadcrumb">
						<li class="breadcrumb-item"><a href="dashboardAzienda.jsp">Dashboard</a></li>
						<li class="breadcrumb-item active">Progetti Formativi</li>
					</ol>
				</div>
			</div>
             
 		<%
          IAziendaDao aziendaDao = (IAziendaDao) new InitialContext().lookup("java:app/Libra/AziendaJpa");
          IProgettoFormativoDao pfDao = (IProgettoFormativoDao) new InitialContext().lookup("java:app/Libra/ProgettoFormativoJpa");
          
          // azienda loggata
          String aziendaEmail = (String) request.getSession().getAttribute("utenteEmail");
          Azienda azienda = aziendaDao.findById(Azienda.class, aziendaEmail);
          int[] statiDaRicercare = {StatoPf.INVIATO, StatoPf.VERIFICA_TUTOR, StatoPf.VERIFICA_PRESIDENTE, StatoPf.VERIFICATO, StatoPf.APPROVATO, StatoPf.RIFIUTATO};
          List<ProgettoFormativo> pfInviati = pfDao.findByAziendaAndStato(azienda, statiDaRicercare);
             
		%>
             
             <div class="card wizard-card" style="padding:1%">
					<div class="table-responsive">
						<table class="table" id="tablePf">
							<thead>
								<tr>
									<th>Nome Studente</th>
									<th>Cognome Studente</th>
									<th>Ambito Progetto</th>
									<th>Stato Progetto</th>
								</tr>
							</thead>
							<tbody>
							<% for (ProgettoFormativo pf : pfInviati) { %>
							<% 			Studente s = pf.getStudente(); 		 %>
		                            <tr>
		                            <td>
		                            	<%=s.getNome()%>
		                            </td>
		                            <td>
		                            	<%=s.getCognome()%>
		                            </td>
		                            <td>
		                            	<%=pf.getAmbito()%>
		                            </td>
		                            <td>
		                                <% int stato = pf.getStato(); %>
		                                <% if (stato == StatoPf.INVIATO || stato == StatoPf.VERIFICA_TUTOR || stato == StatoPf.VERIFICA_PRESIDENTE) { %>	
		                                		<span class="label label-info">In attesa</span>
		                                <% } else if (stato == StatoPf.VERIFICATO) { %>
		                                		<span class="label label-info">Verificato</span>
		                                <% } else if (stato == StatoPf.APPROVATO) { %>
		                                		<span class="label label-success">Approvato</span>
		                                <% } else if (stato == StatoPf.RIFIUTATO) { %>
		                                		<span class="label label-danger">Rifiutato</span>
		                                <% } %>
		                             </td>
		                        </tr>
                        <% 		} %>
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


<script src="js/datatables.js"></script>
	<script>
		var table;
		$(document).ready(function() {
			table = $('#tablePf').DataTable({
				"paging": true,
				"searching": true,
				"pageLength": 10,
				"columnDefs": [	
					{ "searchable": false, "targets": 3 },
				  ],
				"language": {
		            "lengthMenu": "Mostra _MENU_ risultati per pagina",
		            "zeroRecords": "Nessun risultato trovato",
		            "info": "Pagina _PAGE_ di _PAGES_",
		            "infoEmpty": "Nessun risultato presente",
		            "infoFiltered": "(Cercati su _MAX_ risultati totali)",
		            "paginate": {
		                "first":      "Prima",
		                "last":       "Ultima",
		                "next":       "Successiva",
		                "previous":   "Precedente"
		            }
			    },
				"initComplete" : function() {
					$(".dataTables_filter").empty();
					$(".dataTables_filter").html(
						'<div class="input-group add-on">'+
						'<input class="form-control" placeholder="Cerca" name="srch-term" id="srch-term" type="text">'+
							'<div class="input-group-btn">'+
								'<button class="btn btn-default buttonSearch">'+
									'<i class="mdi mdi-magnify"></i>'+
								'</button>'+
							'</div>'+
						'</div>');
					var input= $('.dataTables_filter input');
					self= this.api();
					$(".buttonSearch").mouseenter(function(){
						$(this).css({'background-color': '#D91A5F'});	
					})
					$(".buttonSearch").mouseleave(function(){
						$(this).css({'background-color':'#DDDDDD'});	
					})
					$(".buttonSearch").click(function(){
						var text= input.val();
						$(this).css({'outline':'none', 'box-shadow':'none'});
						$('.text-danger').remove();
						if(/^([0-9a-zA-Z\s@:./]{0,100})$/.test(text)==false){
							$('.dataTables_filter').append('<small class="text-danger">Input errato. Sono ammessi solo caratteri</small>');
						}else{
							self.search(input.val()).draw();
						}
					})
				}
					
			});
		});
		
		
	</script>


</body>

</html>


