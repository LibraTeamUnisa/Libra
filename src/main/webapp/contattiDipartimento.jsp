<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="it.unisa.libra.model.dao.ISegreteriaDao" %>
<%@ page import="it.unisa.libra.model.dao.IPresidenteDao" %>
<%@ page import="javax.naming.InitialContext" %>
<%@ page import="javax.naming.Context" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.Map.Entry" %>
<%@ page import="it.unisa.libra.bean.Segreteria" %>
<%@ page import="it.unisa.libra.bean.Presidente" %>
<%@ page import="it.unisa.libra.util.JsonUtils" %>

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
                        <h3 class="text-themecolor m-b-0 m-t-0">Contatti Dipartimento</h3>
                        <ol class="breadcrumb">
                        <%
                        if(session != null && session.getAttribute("utenteRuolo") != null){ 
                        	String dashboard = request.getContextPath()
                                + "/dashboard".concat(session.getAttribute("utenteRuolo").toString()).concat(".jsp");
                        %>
                            <li class="breadcrumb-item"><a href="<%=dashboard%>">Home</a></li>
                            <li class="breadcrumb-item active">Contatti</li>
                        <%} %>
                        </ol>
                    </div>
                    
                </div>
                
            	<div class="row">
            
            	<!--SEGRETERIA-->
            	
            	<%  ISegreteriaDao segreteriaDao = (ISegreteriaDao) new InitialContext().lookup("java:app/Libra/SegreteriaJpa");
             		List<Segreteria> listSeg = segreteriaDao.findAll(Segreteria.class);
             		for(Segreteria seg: listSeg){
            	 %>
             		<div class="col-md-6 col-lg-5 col-xlg-4">
                        <div class="card card-block">
                            <div class="row">
                                <div class="col-md-4 col-lg-3 text-center">
                                    <a href="#"><img src="assets/images/users/1.jpg" alt="user" class="img-circle img-responsive"></a>
                                </div>
                                <div class="col-md-8 col-lg-9">
                                    <h3 class="box-title m-b-0">Segreteria</h3>
                                    <small>Dipartimento di Informatica</small>
                                    <span class="badge badge-primary">Edificio F</span>
                                    <br>
                                    <span class="mail-desc">Orari di apertura</span>
                                    <address>
                                    	<% 
                                    	Map<String,String> giorniAp = JsonUtils.parseOrariApertura(seg.getGiorniDiRicevimento());
                                    	if(!CheckUtils.isNullOrEmpty(giorniAp))
                                    	for (Entry<String, String> entry : giorniAp.entrySet()){
                                    	%>
                                    		<span class="badge badge-info"><%=entry.getKey()%></span>
                                       		<span class="time"><%=entry.getValue()%></span>
                                        	<br>
                                		<%}%>
                                    </address>
                                    <span class="mdi mdi-phone"> <%=seg.getUtente().getTelefono()%></span>
                                    <br>
                                    <span class="mdi mdi-email"> <%=seg.getUtenteEmail()%></span>
                                    <br>
                                    <br>
                                </div>
                            </div>
                        </div>
                    </div>
             <% } %>
             
             <!--PRESIDENTE-->
            	
            	<%  IPresidenteDao presidenteDao = (IPresidenteDao) new InitialContext().lookup("java:app/Libra/PresidenteJpa");
             		List<Presidente> listPres = presidenteDao.findAll(Presidente.class);
             		for(Presidente pres: listPres){
            	 %>
             		<div class="col-md-6 col-lg-5 col-xlg-4">
                        <div class="card card-block">
                            <div class="row">
                                <div class="col-md-4 col-lg-3 text-center">
                                    <a href="#"><img src="assets/images/users/2.jpg" alt="user" class="img-circle img-responsive"></a>
                                </div>
                                <div class="col-md-8 col-lg-9">
                                    <h3 class="box-title m-b-0"><%=pres.getCognome()+ " " +pres.getNome()%></h3>
                                    <small>Dipartimento di Informatica</small>
                                    <span class="badge badge-primary"><%=pres.getUfficio()%></span>
                                    <br>
                                    	<% 
                                    	Map<String,String> giorniAp = null;
                                    	try{
                                    		giorniAp = JsonUtils.parseOrariApertura(pres.getGiorniDiRicevimento());
                                    	}catch(Exception e){
                                    	}
                                    	if(!CheckUtils.isNullOrEmpty(giorniAp)){
                                    		%>
                                    <span class="mail-desc">Orari di ricevimento</span>
                                    <address>
                                    	<%
                                    		for (Entry<String, String> entry : giorniAp.entrySet()){
                                    	%>
                                    		<span class="badge badge-info"><%=entry.getKey()%></span>
                                       		<span class="time"><%=entry.getValue()%></span>
                                        	<br>
                                		<%}
                                		} else {%><br><br><br><%}%>
                                    </address>
                                    <span class="mdi mdi-phone"> <%=pres.getUtente().getTelefono()%></span>
                                    <br>
                                    <span class="mdi mdi-email"> <%=pres.getUtenteEmail()%></span>
                                    <br>
                                    	<%
                                    	if(pres.getLinkSito() != null && !pres.getLinkSito().isEmpty()){ %>
                                    		<span class="fa fa-graduation-cap"> <a href=<%=pres.getLinkSito()%>>Pagina universitaria</a></span>
                                    	<%} else {%>
                                    		<br>
                                    	<%}%>
                                </div>
                            </div>
                        </div>
                    </div>
             <% } %>
             
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


