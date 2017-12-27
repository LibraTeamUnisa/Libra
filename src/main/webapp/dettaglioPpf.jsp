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
   <!-- STUDENTE --> 
                 	<%
/*		session = request.getSession();
		String email = (String) session.getAttribute("utenteEmail");
		String ruolo = (String) session.getAttribute("utenteRuolo");
		
		if (email == null || ruolo == null || !ruolo.equals("Studente")) {
			response.sendRedirect("/Libra/errore.jsp");
		}
		IUtenteDao utenteDao = (IUtenteDao) new InitialContext().lookup("java:app/Libra/UtenteJpa");
		Utente u = utenteDao.findById(Utente.class, email);
		IStudenteDao studenteDao = (IStudenteDao) new InitialContext().lookup("java:app/Libra/StudenteJpa");
		Studente s = studenteDao.findById(Studente.class, email);
*/	%>
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

				<div class="card card-block">
					<div class="row">
						<div class="col-sm-4">
							<div class="card wild-card">
								<h4 class="text-themecolor m-b-0 m-t-0">
									Dettaglio Proposta
								</h4>
							</div>
						</div>
					</div>

					<div class="row">
						<!-- PROGETTO FORMATIVO-->
						<%
							String idString = request.getParameter("id");
							int id = Integer.parseInt(idString);
							IProgettoFormativoDao pfdao = (IProgettoFormativoDao) new InitialContext().lookup("java:app/Libra/ProgettoFormativoJpa");
							ProgettoFormativo pf = pfdao.findById(ProgettoFormativo.class, id);
						%>
						<div class="row">
									<div class="col-sm-4">
										<label class="col-md-12">Data invio:</label>
									</div>
									<div class="col-sm-5">
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
									<div class="col-sm-4">
										<label class="col-md-12">Azienda:</label>
									</div>
									<div class="col-sm-5">
									<%
										Azienda azienda = pf.getAzienda();
									%>
									<p><%=azienda.getNome() %></p>
									</div>
								</div>
								<div class="row">
									<div class="col-sm-4">
										<label class="col-md-12">Note:</label>
									</div>
									<div class="col-sm-5">
										<p><%=pf.getNote() %></p>
									</div>
								</div>
								<div class="row">
									<div class="col-sm-4">
										<label class="col-md-12">Contenuto proposta:</label>
									</div>
									<div class="col-sm-5">
										<p><%=pf.getDocumento() %></p>
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
</body>

</html>


