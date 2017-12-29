<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ page import="it.unisa.libra.model.dao.IStudenteDao" %>
<%@ page import="it.unisa.libra.model.dao.IProgettoFormativoDao" %>
<%@ page import="it.unisa.libra.model.dao.IAziendaDao" %>
<%@ page import="javax.naming.InitialContext" %>
<%@ page import="javax.naming.Context" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.Map.Entry" %>
<%@ page import="it.unisa.libra.bean.ProgettoFormativo" %>
<%@ page import="it.unisa.libra.bean.Studente" %>
<%@ page import="it.unisa.libra.bean.Azienda" %>
<%@ page import="it.unisa.libra.bean.TutorInterno" %>
<%
	
	
	IStudenteDao studenteDao = (IStudenteDao) new InitialContext().lookup("java:app/Libra/StudenteJpa");
	IProgettoFormativoDao progettoFormativoDao = (IProgettoFormativoDao) new InitialContext().lookup("java:app/Libra/ProgettoFormativoJpa");
	IAziendaDao aziendaDao = (IAziendaDao) new InitialContext().lookup("java:app/Libra/AziendaJpa");
	int numeroStudenti = studenteDao.findAll(Studente.class).size();
	int numeroProgettiFormativi = progettoFormativoDao.findAll(ProgettoFormativo.class).size();
	int numeroAziende = aziendaDao.findAll(Azienda.class).size();
	int numeroStudentiAttivi = 0;
	List<ProgettoFormativo> progettiInCorso = progettoFormativoDao.findAll(ProgettoFormativo.class);
	ProgettoFormativo progetto = null;
	boolean pass = false;
	if(progettiInCorso!=null){
		for(int i=0;i<progettiInCorso.size();i++){
			progetto = progettiInCorso.get(i);
			if(progetto.getDataInizio()!=null&&progetto.getDataInizio().before(new Date())){
				if(progetto.getDataFine()==null||progetto.getDataFine().after(new Date())){
					numeroStudentiAttivi++;
					pass = true;
				}
			}
			if(pass==false){
				progettiInCorso.remove(i);
				i--;
			}
			else{
				pass=false;
			}
	}
	}
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

<script>


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
        <!-- 

        -->
       <%//@ include file="menu.jsp" %>
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
                
                <!-- ============================================================== -->
                <!-- End Bread crumb and right sidebar toggle -->
                <!-- ============================================================== -->
                <!-- ============================================================== -->
                <!-- Start Page Content -->
                <!-- ============================================================== -->
                <!-- Row -->
                <div class="row">
                    <!-- Column -->
                    <div class="col-lg-6">

                        <div class="card" >
                        
                            <div class="card">
                            <h4 class="card-title" align="center"></h4>
                                <h4 class="card-title" align="center">PROGETTI FORMATIVI IN CORSO</h4>
                                <div class="table-responsive m-t-40">
                                
                                    <table class="table stylish-table">
                                    
                                        <thead>
                                            <tr>
                                                <th colspan="2">Azienda</th>
                                                <th>Studente</th>
                                                <th>Tutor interno</th>
                                                <th>Ambito</th>
                                                <th>Data inizio</th>
                                            </tr>
                                        </thead>
                                        
                                        <%if(progettiInCorso!=null && progettiInCorso.size()>0){
                                        	for(ProgettoFormativo p:progettiInCorso){
                                        %>
                                        <tbody><tr>
                                        		<%if(p.getAzienda()!=null&&p.getAzienda().getNome()!=null) {
                                        		%>
                                               <td style="width:50px;"><span class="round"><%=p.getAzienda().getNome().charAt(0) %></span></td>
                                                <td><h6><%=p.getAzienda().getNome() %></h6></td>
                                               <% } else{%>
                                               <td style="width:50px;"><span class="round">NA</span></td>
                                                <td><h6>Non disponibile</h6></td>
                                                <%} 
                                                if(p.getStudente()!=null&&p.getStudente().getNome()!=null&&p.getStudente().getNome()!=null){
                                                %>
                                               	 <td> <h6> <%=(p.getStudente().getNome()+" "+p.getStudente().getCognome()) %></h6></td>
                                               	 <%} else{ %>
                                               	 <td> <h6> Non disponibileee</h6></td>
                                               	 <%}
                                                if(p.getTutorInterno()!=null&&p.getTutorInterno().getNome()!=null&&p.getTutorInterno().getNome()!=null){
                                                %>
                                                <td> <h6> <%=(p.getTutorInterno().getNome()+" "+p.getTutorInterno().getCognome()) %></h6></td>
                                                <%} else{ %>
                                                <td> <h6> Non disponibile</h6></td>
                                                 <%}
                                                if(p.getAmbito()!=null){
                                                %>
                                                <td><span class="label label-light-success"><%=p.getAmbito() %></span></td>
                                                <%} else{ %>
                                                <td> <h6> Non disponibile</h6></td>
                                               	<%}
                                                if(p.getDataInizio()!=null){
                                                %>
                                                <td><%=(p.getDataInizio().getDate()+"/"+(p.getDataInizio().getMonth()+1)+"/"+(p.getDataInizio().getYear()+1900)) %></td>
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
                                            <h1 class="font-light"><%=numeroStudenti %></h1>
                                            <h6 class="text-muted">Studenti iscritti alla piattaforma</h6></div>
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
                                            <h1 class="font-light"><%=numeroProgettiFormativi %></h1>
                                            <h6 class="text-muted">Progetti formativi </h6></div>
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
                                            <h1 class="font-light"><%=numeroAziende %></h1>
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
                                            <h1 class="font-light"><%=numeroStudentiAttivi%></h1>
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
                    <!--               -->
                   <div class="row">
                    <!-- Column -->
					<canvas id="myLineChart" width="600" height="600">gfhfdgfd</canvas> 
 
fdgfdgdfgfdgd
                        </div>
                        <!--          -->
                     
                </div>
                 
                
                
                
                <div class="right-sidebar">
                    <div class="slimscrollright">
                        <div class="rpanel-title"> Service Panel <span><i class="ti-close right-side-toggle"></i></span> </div>
                        <div class="r-panel-body">
                            <ul id="themecolors" class="m-t-20">
                                <li><b>With Light sidebar</b></li>
                                <li><a href="javascript:void(0)" data-theme="default" class="default-theme">1</a></li>
                                <li><a href="javascript:void(0)" data-theme="green" class="green-theme">2</a></li>
                                <li><a href="javascript:void(0)" data-theme="red" class="red-theme">3</a></li>
                                <li><a href="javascript:void(0)" data-theme="blue" class="blue-theme working">4</a></li>
                                <li><a href="javascript:void(0)" data-theme="purple" class="purple-theme">5</a></li>
                                <li><a href="javascript:void(0)" data-theme="megna" class="megna-theme">6</a></li>
                                <li class="d-block m-t-30"><b>With Dark sidebar</b></li>
                                <li><a href="javascript:void(0)" data-theme="default-dark" class="default-dark-theme">7</a></li>
                                <li><a href="javascript:void(0)" data-theme="green-dark" class="green-dark-theme">8</a></li>
                                <li><a href="javascript:void(0)" data-theme="red-dark" class="red-dark-theme">9</a></li>
                                <li><a href="javascript:void(0)" data-theme="blue-dark" class="blue-dark-theme">10</a></li>
                                <li><a href="javascript:void(0)" data-theme="purple-dark" class="purple-dark-theme">11</a></li>
                                <li><a href="javascript:void(0)" data-theme="megna-dark" class="megna-dark-theme ">12</a></li>
                            </ul>
                            <ul class="m-t-20 chatonline">
                                <li><b>Chat option</b></li>
                                <li>
                                    <a href="javascript:void(0)"><img src="../assets/images/users/1.jpg" alt="user-img" class="img-circle"> <span>Varun Dhavan <small class="text-success">online</small></span></a>
                                </li>
                                <li>
                                    <a href="javascript:void(0)"><img src="../assets/images/users/2.jpg" alt="user-img" class="img-circle"> <span>Genelia Deshmukh <small class="text-warning">Away</small></span></a>
                                </li>
                                <li>
                                    <a href="javascript:void(0)"><img src="../assets/images/users/3.jpg" alt="user-img" class="img-circle"> <span>Ritesh Deshmukh <small class="text-danger">Busy</small></span></a>
                                </li>
                                <li>
                                    <a href="javascript:void(0)"><img src="../assets/images/users/4.jpg" alt="user-img" class="img-circle"> <span>Arijit Sinh <small class="text-muted">Offline</small></span></a>
                                </li>
                                <li>
                                    <a href="javascript:void(0)"><img src="../assets/images/users/5.jpg" alt="user-img" class="img-circle"> <span>Govinda Star <small class="text-success">online</small></span></a>
                                </li>
                                <li>
                                    <a href="javascript:void(0)"><img src="../assets/images/users/6.jpg" alt="user-img" class="img-circle"> <span>John Abraham<small class="text-success">online</small></span></a>
                                </li>
                                <li>
                                    <a href="javascript:void(0)"><img src="../assets/images/users/7.jpg" alt="user-img" class="img-circle"> <span>Hritik Roshan<small class="text-success">online</small></span></a>
                                </li>
                                <li>
                                    <a href="javascript:void(0)"><img src="../assets/images/users/8.jpg" alt="user-img" class="img-circle"> <span>Pwandeep rajan <small class="text-success">online</small></span></a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
                <!-- ============================================================== -->
                <!-- End Right sidebar -->
                <!-- ============================================================== -->
            </div>
            <!-- ============================================================== -->
            <!-- End Container fluid  -->
            
            
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
    <!-- ======================     ======================================= -->
    <!-- Style switcher -->
    <!-- ============================================================== -->
    <script src="assets/plugins/styleswitcher/jQuery.style.switcher.js"></script>
    <script src="../assets/plugins/jquery/jquery.min.js"></script>
    <!-- Bootstrap tether Core JavaScript -->
    <script src="../assets/plugins/bootstrap/js/tether.min.js"></script>
    <script src="../assets/plugins/bootstrap/js/bootstrap.min.js"></script>
    <!-- slimscrollbar scrollbar JavaScript -->
    <script src="js/jquery.slimscroll.js"></script>
    <!--Wave Effects -->
    <script src="js/waves.js"></script>
    <!--Menu sidebar -->
    <script src="js/sidebarmenu.js"></script>
    <!--stickey kit -->
    <script src="../assets/plugins/sticky-kit-master/dist/sticky-kit.min.js"></script>
    <!--Custom JavaScript -->
    <script src="js/custom.min.js"></script>
    <!-- ============================================================== -->
    <!-- This page plugins -->
    <!-- ============================================================== -->
    <!-- chartist chart -->
    <script src="../assets/plugins/chartist-js/dist/chartist.min.js"></script>
    <script src="../assets/plugins/chartist-plugin-tooltip-master/dist/chartist-plugin-tooltip.min.js"></script>
    <script src="../assets/plugins/echarts/echarts-all.js"></script>
    <!-- Vector map JavaScript -->
    <script src="../assets/plugins/vectormap/jquery-jvectormap-2.0.2.min.js"></script>
    <script src="../assets/plugins/vectormap/jquery-jvectormap-world-mill-en.js"></script>
    <!-- Calendar JavaScript -->
    <script src="../assets/plugins/moment/moment.js"></script>
    <script src='../assets/plugins/calendar/dist/fullcalendar.min.js'></script>
    <script src="../assets/plugins/calendar/dist/jquery.fullcalendar.js"></script>
    <script src="../assets/plugins/calendar/dist/cal-init.js"></script>
    <!-- sparkline chart -->
    <script src="../assets/plugins/sparkline/jquery.sparkline.min.js"></script>
    <!-- ============================================================== -->
    <!-- Style switcher -->
    <!-- ============================================================== -->
    <script src="../assets/plugins/styleswitcher/jQuery.style.switcher.js"></script>
    
</body>

</html>


