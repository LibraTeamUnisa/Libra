<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ page import="it.unisa.libra.model.dao.IProgettoFormativoDao" %>
<%@ page import="it.unisa.libra.model.dao.IAziendaDao" %>
<%@ page import="javax.naming.InitialContext" %>
<%@ page import="javax.naming.Context" %>
<%@ page import="java.util.List" %>
<%@ page import="it.unisa.libra.bean.ProgettoFormativo" %>
<%@ page import="it.unisa.libra.bean.Azienda" %>
<%@ page import="it.unisa.libra.bean.TutorInterno" %>
<%@page import="java.text.DateFormat" %>
<%@page import="java.text.SimpleDateFormat" %>

<%
 	String email = (String) request.getSession().getAttribute("utenteEmail");
	DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	IAziendaDao aziendaDao = (IAziendaDao) new InitialContext().lookup("java:app/Libra/AziendaJpa");
	IProgettoFormativoDao progettoFormativoDao = (IProgettoFormativoDao) new InitialContext().lookup("java:app/Libra/ProgettoFormativoJpa");
	List<Object[]> progettiDaRevisionare = (List<Object[]>) progettoFormativoDao.getPfDaRevisionareTutorInterno(email);

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
             
             
                  <div class="row">
                    <!-- Column -->
                    <div class="col-lg-6">

                        <div class="card" >
                        
                            <div class="card">
                            <h4 class="card-title" align="center"></h4>
                                <h4 class="card-title" align="center">PROGETTI FORMATIVI DA REVISIONARE</h4>
                                <div class="table-responsive m-t-40">
                                
                                    <table class="table stylish-table">
                                    
                                        <thead>
                                            <tr>
                                                <th colspan="2">Studente</th>
                                                <th>Azienda</th>
                                                <th>Ambito</th>
                                                <th>Data invio</th>
                                            </tr>
                                        </thead>
                                        
                                        <%if(progettiDaRevisionare!=null && progettiDaRevisionare.size()>0){
                                        	for(Object[] p : progettiDaRevisionare){
                                        %>
                                        <tbody><tr>
                                               <td style="width:50px;">
                                               	<a href="<%=request.getContextPath()%>/dettaglioStudente?action=<%=Actions.DETTAGLIO_STUDENTE%>&email-studente=<%=p[0]%>"> <span class="round"><%=((String)p[3]).charAt(0)%></span></a>
                                               </td>
                                               
                                                <% 
                                                if(p[3]!=null && p[4]!=null){
                                                %>
                                               	 <td> <h6> <%=(p[4]+" "+p[3]) %></h6></td>
                                               	 <%} else{ %>
                                               	 <td> <h6> Non disponibile</h6></td><%} %>
                                               	 
                                               	<%if(p[1]!=null) {%>
                                                <td><h6><%=p[1] %></h6></td>
                                               	<% } else{%>
                                               	<td style="width:50px;"><span class="round">NA</span></td>
                                                <td><h6>Non disponibile</h6></td><%} %>
                                               	 
                                                 <%
                                                if(p[2]!=null){
                                                %>
                                                <td><span class="label label-light-success"><%=p[2] %></span></td>
                                                <%} else{ %>
                                                <td> <h6> Non disponibile</h6></td><%} %>
                                                
                                               	<%
                                                if(p[5]!=null){
                                                %>
                                                <td><%=formatter.format(p[5])%></td>
                                                <%}else{
                                                	%>
                                                	<td> <h6> Non disponibile</h6></td>
                                               <% }%> 
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
                    
                    <div class="col-lg-6">
                        <!-- Row -->
                        <div class="row">
                            <!-- Column -->
                            <div class="col-sm-6">
                                <div class="card card-block">
                                    <!-- Row -->
                                    <div class="row p-t-10 p-b-10">
                                        <!-- Column -->
                                        <div class="col p-r-0">
                                            <h1 class="font-light"><%=progettoFormativoDao.getNumStudentiAssociati(email)%></h1>
                                            <h6 class="text-muted">Studenti associati</h6></div>
                                        <!-- Column -->
                                        <div class="col text-right align-self-center">
                                            <div data-label="20%" class="css-bar m-b-0 css-bar-primary css-bar-20"><i class="mdi mdi-receipt"></i></div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- Column -->
                            <div class="col-sm-6">
                                <div class="card card-block">
                                    <!-- Row -->
                                    <div class="row p-t-10 p-b-10">
                                        <!-- Column -->
                                        <div class="col p-r-0">
                                            <h1 class="font-light"><%=progettoFormativoDao.getPfTutor(email)%></h1>
                                            <h6 class="text-muted">Progetti formativi associati</h6></div>
                                        <!-- Column -->
                                        <div class="col text-right align-self-center">
                                            <div data-label="30%" class="css-bar m-b-0 css-bar-danger css-bar-20"><i class="mdi mdi-receipt"></i></div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- Column -->
                            <div class="col-sm-6">
                                <div class="card card-block">
                                    <!-- Row -->
                                    <div class="row p-t-10 p-b-10">
                                        <!-- Column -->
                                        <div class="col p-r-0">
                                            <h1 class="font-light"><%=aziendaDao.contaOccorrenze()%></h1>
                                            <h6 class="text-muted">Aziende convenzionate</h6></div>
                                        <!-- Column -->
                                        <div class="col text-right align-self-center">
                                            <div data-label="40%" class="css-bar m-b-0 css-bar-warning css-bar-40"><i class="mdi mdi-receipt"></i></div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- Column -->
                            <div class="col-sm-6">
                                <div class="card card-block">
                                    <!-- Row -->
                                    <div class="row p-t-10 p-b-10">
                                        <!-- Column -->
                                        <div class="col p-r-0">
                                            <h1 class="font-light"><%=progettoFormativoDao.getNumStudentiAttivi()%></h1>
                                            <h6 class="text-muted">Numero studenti attivi</h6></div>
                                        <!-- Column -->
                                        <div class="col text-right align-self-center">
                                            <div data-label="60%" class="css-bar m-b-0 css-bar-info css-bar-60"><i class="mdi mdi-receipt"></i></div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                        
                    </div>
                   
                </div>
                             
                <!-- ============================================================== -->
                <!-- End Right sidebar -->
                <!-- ============================================================== -->
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


