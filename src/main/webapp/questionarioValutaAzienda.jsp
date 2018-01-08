<%@page import="it.unisa.libra.model.dao.IStudenteDao"%>
<%@page import="it.unisa.libra.bean.Studente"%>
<%@page import="it.unisa.libra.bean.ProgettoFormativo"%>
<%@page import="it.unisa.libra.model.dao.IProgettoFormativoDao"%>
<%@page import="java.util.ArrayList"%>
<%@page import="it.unisa.libra.bean.FeedbackPK"%>
<%@page import="it.unisa.libra.bean.Feedback"%>
<%@page import="it.unisa.libra.model.dao.IFeedbackDao"%>
<%@page import="it.unisa.libra.model.dao.IDomandaDao"%>
<%@page import="it.unisa.libra.bean.Domanda"%>
<%@page import="java.util.List"%>
<%@page import="javax.naming.InitialContext"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>


<%
	HttpSession sessione = request.getSession();

	IDomandaDao ddao= (IDomandaDao) new  InitialContext().lookup("java:app/Libra/DomandaJpa");
	IFeedbackDao fDao = (IFeedbackDao) new InitialContext().lookup("java:app/Libra/FeedbackJpa");
	IProgettoFormativoDao pfDao = (IProgettoFormativoDao) new InitialContext().lookup("java:app/Libra/ProgettoFormativoJpa");
	IStudenteDao sDao = (IStudenteDao) new InitialContext().lookup("java:app/Libra/StudenteJpa");
	IUtenteDao uDao = (IUtenteDao) new InitialContext().lookup("java:app/Libra/UtenteJpa");
	
	String email = (String) sessione.getAttribute("utenteEmail");
	Utente user= uDao.findById(Utente.class, email);
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
				<div class="row page-titles">
					<div class="col-md-6 col-8 align-self-center">
						<h3 class="text-themecolor m-b-0 m-t-0">Questionario Valuta Azienda</h3>
						<ol class="breadcrumb">
							<%
								if (session != null && session.getAttribute("utenteRuolo") != null) {
									String dashboard = request.getContextPath()
											+ "/dashboard".concat(session.getAttribute("utenteRuolo").toString()).concat(".jsp");
							%>
							<li class="breadcrumb-item"><a href="<%=dashboard%>">Home</a></li>
							<li class="breadcrumb-item active">Questionario Valuta Azienda</li>
							<%
								}
							%>
						</ol>
					</div>
				</div>
				<div class="card wizard-card" style="padding: 1%">
					<form id="form-questionario" method="post" action="gestioneFeedbackAzienda">
						<div class="container">
					<%
						if (email == null ) {
							response.sendRedirect("/Libra/errore.jsp");
						}
						else if(email != null && user.getGruppo().getRuolo().equals("Studente")){
							
							Studente studente = sDao.findById(Studente.class, email);
							List<Domanda> domande= ddao.findByType("Studente");

							ProgettoFormativo pf= pfDao.getLastProgettoFormativoByStudente(studente);
							String note=null;
							Boolean persisted= false;
							if(pf != null && pf.getStato() == 5){
							
								for(Domanda d: domande){ 
									FeedbackPK pk= new FeedbackPK();
									pk.setProgettoFormativoID(pf.getId());
									pk.setDomandaID(d.getId());
									Feedback feed= fDao.findById(Feedback.class, pk);
									if(d.getTesto().equals("Note")){
										if(feed != null){
											note= feed.getValutazione();
										}
										
										continue;
									}
						%>
									<div class="row">
										<!-- panel preview -->						
										<div class="form-group col-md-8">
											<label><%=d.getTesto()%></label>
										</div>
										<div class="form-group col-md-4" style="text-align: center;">
												<%
													for (int i = 1; i <= 5; i++) {
												%>
												<label class="radio-inline" style="margin-left: 3.5%;">
														<%
														if(feed == null){		
															if(i==3){ 
														%> 
															<input type="radio" name="<%=d.getId()%>" value="<%=i%>" checked="checked">
														<%
															}else{ 
														%>
															<input type="radio" name="<%=d.getId()%>" value="<%=i%>">
														<%
															}
														%> 
											
													<%
														}else{
															persisted=true;
															if(i==Integer.parseInt(feed.getValutazione())){
															
													%>
																<input type="radio" name="<%=d.getId()%>" checked="checked" disabled>
															
													<%
															}else{
																
													%>
																<input type="radio" name="<%=d.getId()%>" disabled>
																
													<%
															}
														}
													%>	
														<%=i%>
													</label>	
												<%
													}
												%>
										</div>
									</div>
						<%
								}
							
						%>
								<div class="row">
									<div class="form-group col-md-8">
										<label for="note">Note:</label>
										<%
											if(note == null){ 
										%>
												<textarea class="form-control" rows="5" id="note" name="note"></textarea>
										<%
											}else{ 
										%>
												<textarea class="form-control" rows="5" id="note" name="note" disabled><%=note%></textarea>
										<%
											}
										%>
										<input type="hidden" name="idProgettoFormativo" value="<%=pf.getId()%>"/>
									</div>
								</div>
								<%
									if(!persisted){
								%>
										<button class="btn btn-primary" type="submit">Invia</button>
					<%
									}
							}else{
					%>
								<h4>Nessuna valutazione disponibile.</h4>
					<%
							}
						}
					%>
						
						</div>
					</form>
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
	
	<script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.14.0/jquery.validate.js">
		/*$(document).ready(function(){
			$.post('gestioneFeedbackAzienda', function() {
				alert("Submitted");
				$('#form-questionario').validate({
					rules: {
						note:{
							 required: true,
							 maxlenght: 200
						}
					},
					
					messages: {
						note: {
							required: "Questo campo non può essere vuoto",
							maxlenght: "La lunghezza deve essere massima deve essere di 200 charatteri."
						}	
					},
					
					submitHandler: function(form){
						$(form).ajaxSubmit();
					}
				});
			});
		
		});*/
	
	
	
		
	</script>


</body>

</html>


