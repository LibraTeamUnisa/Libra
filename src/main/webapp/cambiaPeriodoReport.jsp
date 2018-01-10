<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="it.unisa.libra.model.dao.ITutorInternoDao,
                                      it.unisa.libra.model.jpa.TutorInternoJpa,
                                      it.unisa.libra.bean.TutorInterno,
                                      it.unisa.libra.bean.ProgettoFormativo,
                                      it.unisa.libra.bean.Studente,
                                      it.unisa.libra.bean.Azienda,
                                      java.util.List,
                                      java.util.ArrayList,
                                      java.util.Date,
                                      java.text.SimpleDateFormat,
                                      javax.naming.InitialContext"%>
<%!
   private static final int APPROVED_STATE=4;

   private List<ProgettoFormativo> getActive(List<ProgettoFormativo> pfList)
   {
	 List<ProgettoFormativo> result=new ArrayList<ProgettoFormativo>();
	 for(ProgettoFormativo pf:pfList){
		 if(pf.getStato()==APPROVED_STATE)
			 result.add(pf);
	 }
	 return result.size()==0?null:result;
   }
   
   private String parseDate(Date date)
   {
	   SimpleDateFormat dFormat=new SimpleDateFormat("dd/MM/yyyy");
	   return dFormat.format(date);
   }
 %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- Tell the browser to be responsive to screen width -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="Page for changing report period">
    <meta name="author" content="Mauro Vitale">
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
    <link href="css/cambiaperiodoreport-style.css" rel="stylesheet"/>
    <!-- You can change the theme colors from here -->
    <link href="css/colors/red.css" id="theme" rel="stylesheet">
    <!-- Bootstrap Form Helper for number spinner -->
    <link href="assets/plugins/bootstrap-form-helper/css/bootstrap-formhelpers.min.css" rel="stylesheet"/>
    <!-- FontAwesome icons -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <!-- Toastr's toast CSS -->
    <link rel="stylesheet" href="http://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/css/toastr.min.css">
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
            <%
               ITutorInternoDao tutorDao=(ITutorInternoDao)new InitialContext().lookup("java:app/Libra/TutorInternoJpa");
               TutorInterno tutor=tutorDao.findById(TutorInterno.class, (String)session.getAttribute("utenteEmail"));
             %>
                 <div class="container">
                     <!-- MAIN CARD  -->
                     <div class="card">
                         <!-- START CARD HEADER -->
                         <img class="card-img-top img-fluid" src="assets/images/background/report-period-card-header.jpg"/>
                         <div class="card-img-overlay">
                             <h1 class="card-title">Gestione Studenti</h1>
                         </div>
                         <!-- END CARD HEADER -->
                         <!-- CARD BODY -->
                         <div class="card-body">
                         <br>
                             <%
                                List<ProgettoFormativo> pfList=tutor.getProgettiFormativi();
                                pfList= getActive(pfList);
                                if(pfList!=null&&pfList.size()!=0){
                                	
                                	for(ProgettoFormativo pf:pfList){ 
                                		Studente studente=pf.getStudente();
                                		Azienda azienda=pf.getAzienda();
                              %>
                              <form>
                                  <div class="row card-row">
                                      <!-- STUDENT COLUMN -->
                                      <div class="col-4">
                                          <!-- TOP BADGE -->
                                          <div class="row row-badge">
                                              <span class="badge badge-pill badge-warning">Studente</span>
                                          </div>
                                          <div class="row">
                                              <!-- PROFILE IMAGE -->
                                              <div class="col-4">
                                                  <img class="rounded-circle profile" src="<%= studente.getUtente().getImgProfilo() %>" onerror="this.onerror=null;this.src='assets/images/users/default.png';"/>
                                              </div>
                                              <!-- STUDENT DATA -->
                                              <div class="col-8">
                                                  <dl class="row">
                                                      <dt class="col-sm-6">Nome</dt>
                                                      <dd class="col-sm-6"><%= studente.getNome()+" "+studente.getCognome() %></dd>
                                                      <div class="separator"></div>
                                                      <dt class="col-sm-6">Matricola</dt>
                                                      <dd class="col-sm-6"><%= studente.getMatricola() %></dd>
                                                      <div class="separator"></div>
                                                      <dt class="col-sm-6">Nato il</dt>
                                                      <dd class="col-sm-6"><%= parseDate(studente.getDataDiNascita()) %></dd>
                                                  </dl>
                                              </div>
                                          </div>
                                          <!-- COPY BUTTON -->
                                          <div class="row">
                                              <button id="email-value" type="button" class="btn btn-outline-warning" data-toggle="tooltip" data-placement="bottom" title="Copia negli appunti" onclick="copyToClipboard(this)">
                                                  <!-- STUDENT EMAIL -->
                                                  <div class="text-truncate"><%= studente.getUtenteEmail() %></div>
                                              </button>
                                          </div>
                                      </div>
                                      <!-- COMPANY COLUMN -->
                                      <div class="col-4">
                                          <!-- TOP BADGE -->
                                          <div class="row row-badge">
                                              <span class="badge badge-pill badge-danger">Azienda</span>
                                          </div>
                                          <div class="row">
                                              <!-- PROFILE IMAGE -->
                                              <div class="col-4">
                                                  <img class="rounded-circle profile" src="<%= azienda.getUtente().getImgProfilo() %>" onerror="this.onerror=null;this.src='assets/images/users/default.png';"/>
                                              </div>
                                              <!-- COMPANY DATA -->
                                              <div class="col-8">
                                                  <dl class="row">
                                                      <dt class="col-sm-6">Nome</dt>
                                                      <dd class="col-sm-6"><%= azienda.getNome() %></dd>
                                                      <div class="separator"></div>
                                                      <dt class="col-sm-3">Sede</dt>
                                                      <dd class="col-sm-9 text-truncate"><%= azienda.getSede() %></dd>
                                                      <div class="separator"></div>
                                                      <dt class="col-sm-6">Telefono</dt>
                                                      <dd class="col-sm-6"><%= azienda.getUtente().getTelefono() %></dd>
                                                  </dl>
                                              </div>
                                          </div>
                                          <!-- COPY BUTTON -->
                                          <div class="row">
                                              <button id="email-value" type="button" class="btn btn-outline-danger" data-toggle="tooltip" data-placement="bottom" title="Copia negli appunti" onclick="copyToClipboard(this)">
                                                  <!-- COMPANY EMAIL -->
                                                  <div class="text-truncate"><%=azienda.getUtenteEmail() %></div>
                                              </button>
                                          </div>
                                      </div>
                                      <!-- TRAINERSHIP COLUMN -->
                                      <div id="pf" class="col-4">
                                          <!-- TOP BADGE -->
                                          <div class="row row-badge">
                                              <span class="badge badge-pill badge-primary">Progetto Formativo</span>
                                          </div>
                                          <!-- TRAINERSHIP DATA -->
                                          <dl class="row" style="margin: 0px!important">
                                              <dt class="col-sm-6">Inizio-Fine</dt>
                                              <dd class="col-sm-6"><%= parseDate(pf.getDataInizio())+"-"+parseDate(pf.getDataFine()) %></dd>
                                              <div class="separator"></div>
                                              <dt class="col-sm-3">Ambito</dt>
                                              <dd class="col-sm-9"><%= pf.getAmbito() %></dd>
                                              <div class="separator"></div>
                                              <dt class="col-sm-6 periodo">Periodicit&agrave; Report</dt>
                                              <dd class="col-sm-6 periodo">
                                                  <!-- NUMBER SPINNER -->
                                                  <div class="row row-spinner">
                                                      <!-- TRAINERSHIP ID -->
                                                      <input type="hidden" name="pf" value="<%=pf.getId()%>"/>
                                                      <!-- TRAINERSHIP PERIOD -->
                                                      <input name="periodo" type="text" class="form-control bfh-number" value="<%=pf.getPeriodoReport() %>" data-min="1" data-max="30">
                                                  </div>
                                              </dd>
                                          </dl>
                                          <!-- SAVE BUTTON -->
                                          <div class="row">
                                              <button id="submit" type="submit" class="btn btn-outline-primary" data-toggle="tooltip" data-placement="bottom" title="Salva i cambiamenti">Salva</button>
                                          </div>
                                      </div>
                                  </div>
                              </form>
                               <%
                                     //Aggiungi separator se ci sono altre elementi
                                     if(pfList.indexOf(pf)+1<pfList.size()){
                                %>
                                        <br><div class="separator element-separator"></div><br>
                               <%       } else{ 
                                %>
                                        <br><br>
                               <%
                                       }
                                	}
                                } else {
                                %>
                                	<p id="empty-card"><i class="fa fa-times" aria-hidden="true"></i> Non stai seguendo nessuno studente con tirocini in corso</p>
                               <%
                                }
                                %>
                         </div>
                         <!-- CARD FOOTER -->
                         <div class="card-footer"></div>
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
    <!-- Script for Toastr's toast -->
    <script src="http://cdnjs.cloudflare.com/ajax/libs/toastr.js/2.0.2/js/toastr.min.js"></script>
    <!--Custom JavaScript -->
    <script src="js/custom.min.js"></script>
    <script src="js/cambiaperiodoreport.js"></script>
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
    <!-- Script for tooltips-->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.6/umd/popper.min.js"></script>
    <!-- Script for Bootstrap Form Helper number spinner -->
    <script src="assets/plugins/bootstrap-form-helper/js/bootstrap-formhelpers.min.js"></script>
</body>

</html>


