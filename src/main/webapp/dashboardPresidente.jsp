<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="it.unisa.libra.bean.Azienda" %>
<%@ page import="it.unisa.libra.bean.ProgettoFormativo" %>
<%@ page import="it.unisa.libra.bean.Studente" %>
<%@ page import="it.unisa.libra.bean.TutorInterno" %>
<%@ page import="it.unisa.libra.model.dao.IAziendaDao" %>
<%@ page import="it.unisa.libra.model.dao.IProgettoFormativoDao" %>
<%@ page import="it.unisa.libra.model.dao.IStudenteDao" %>

<%@ page import="javax.naming.Context" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="javax.naming.InitialContext" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.Map.Entry" %>

<%
	
	/*Recupero le interfacce per interagire con il Database*/
	IStudenteDao studenteDao = (IStudenteDao) new InitialContext().lookup("java:app/Libra/StudenteJpa");
	IProgettoFormativoDao progettoFormativoDao = (IProgettoFormativoDao) new InitialContext().lookup("java:app/Libra/ProgettoFormativoJpa");
	IAziendaDao aziendaDao = (IAziendaDao) new InitialContext().lookup("java:app/Libra/AziendaJpa");
	int numeroProgetti = progettoFormativoDao.contaOccorrenze();
	/*Raccolgo alcuni dati statistici*/
	int numeroStudenti = studenteDao.contaOccorrenze();
	int numeroAziende = aziendaDao.contaOccorrenze();
	
	List<Map<String,String>> progettiInCorso = progettoFormativoDao.findUltime10();

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
    
    <!-- ============================================================== -->
    <!-- Main wrapper - style you can find in pages.scss -->
    <!-- ============================================================== -->
    <div id="main-wrapper">
        <!-- ============================================================== -->
        <!-- Topbar header - style you can find in pages.scss -->
        <!-- ============================================================== -->
       	<%//@ include file="header.jsp" %>
        <!-- ============================================================== -->
        <!-- End Topbar header -->
        <!-- ============================================================== -->
        <!-- ============================================================== -->
        <!-- Left Sidebar - style you can find in sidebar.scss  -->
        <!-- 

        -->
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
                                <h4 class="card-title" align="center">Ultimi 10 PROGETTI FORMATIVI in corso</h4>
                                <h6 class="card-title" align="center">
                                </h6>
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
                                        
                                        <%
                                        /* Metto in tabella gli ultimi 10 progetti formativi*/
                                        
                                        if(progettiInCorso!=null && progettiInCorso.size()>0){
                                        	for(Map<String,String> p:progettiInCorso){
                                        %>
                                        <tbody><tr>
                                        		<%if(p.get("azienda")!=null) {
                                        		%>
                                               <td style="width:50px;"><span class="round"><%=p.get("azienda").charAt(0) %></span></td>
                                                <td><h6><%=p.get("azienda") %></h6></td>
                                               <% } else{%>
                                               <td style="width:50px;"><span class="round">NA</span></td>
                                                <td><h6>Non disponibile</h6></td>
                                                <%} 
                                                if(p.get("studente")!=null){
                                                %>
                                               	 <td> <h6> <%=p.get("studente") %></h6></td>
                                               	 <%} else{ %>
                                               	 <td> <h6> Non disponibile</h6></td>
                                               	 <%}
                                                if(p.get("tutor")!=null){
                                                %>
                                                <td> <h6> <%=p.get("tutor") %></h6></td>
                                                <%} else{ %>
                                                <td> <h6> Non disponibile</h6></td>
                                                 <%}
                                                if(p.get("ambito")!=null){
                                                %>
                                                <td><span class="label label-light-success"><%=p.get("ambito") %></span></td>
                                                <%} else{ %>
                                                <td> <h6> Non disponibile</h6></td>
                                               	<%}
                                                if(p.get("dataInizio")!=null){
                                                %>
                                                <td><%=p.get("dataInizio") %></td>
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
                                            <h1 class="font-light"><%=numeroProgetti %></h1>
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
                            
                        </div>
                        
                        
                        
                        <%
                        /*Creo e popolo un hashmap dove per ogni mese c'è il numero dei progetti iniziati
                        in quello stesso mese*/
                 int annoCorrente = new Date().getYear()+1900;
                 List<ProgettoFormativo> progetti = progettoFormativoDao.getInOrdineCronologico();
                 Map<Integer,String> progettiPerMese = new HashMap<Integer,String>();
                 ProgettoFormativo p = null;
                 int contaPerMese = 1;
                 int size = progetti.size();
                 if(progetti!=null&&size>=1){
                	 if(size==1){
                       	 	progettiPerMese.put(progetti.get(0).getDataInizio().getMonth()+1,1+"");
                	 }
                 	for(int j=1;j<size;j++){
                 		p = progetti.get(j);
                 		/*Se il mese del j-esimo progetto coincide con quello del j-1esimo allora possiamo incrementare
                 		la variabile contaPerMese
                 		Se ci troviamo nell'ultimo elemento della lista salviamo l'elemento ed il valore attuale di contaPerMese
                 		nel HashMap
                 			*/
                 		if(p.getDataInizio().getMonth()==progetti.get(j-1).getDataInizio().getMonth()){
                 				contaPerMese++;
                 				if(j==size-1){
                 					progettiPerMese.put(progetti.get(j).getDataInizio().getMonth()+1,contaPerMese+"");
                 				}
                 				/*Nel caso i due mesi non coincidano si mette nel hasmap il numero del j-1esimo mese
                 				ed il valore attuale di contaPerMese, dopodichè si azzera contaPerMese.
                 				Nel caso si trattasse dell'ultimo elemento della lista salviamo nel hasmap entrambi i mesi(j-1,j)*/
                 		}else {
                 				if(j==size-1){
                 					progettiPerMese.put(progetti.get(j-1).getDataInizio().getMonth()+1,contaPerMese+"");
                 					progettiPerMese.put(progetti.get(j).getDataInizio().getMonth()+1,1+"");
                 				}else {
                 					progettiPerMese.put(progetti.get(j-1).getDataInizio().getMonth()+1,contaPerMese+"");
                 					contaPerMese = 1;
                 				}
                 			}
                 		
                 		
                	 }
                 }
                 %>
                <form>
                	<%
                	/*Creo una form fittizia dala quale javascript potrà prendere i dati java per il grafico*/
                	for(int x=0;x<12;x++){
                		if(progettiPerMese.get(x+1)!=null){
                	%>
                		<input type="hidden" id="<%="mese"+x%>" value="<%=progettiPerMese.get(x+1)%>">
                		<%  }else{ %>
                		<input type="hidden" id="<%="mese"+x%>" value="<%=0%>">
                		<%}
                		}
                 
                	%>
                </form>
                
                
                
                
                        <div class="row">
                        <div class="col-lg-12">
                        <div class="card">
                            <div class="card-block">
                             <ul class="list-inline pull-right">
                                    <li>
                                        <h6 class="text-muted"><i class="fa fa-circle m-r-5 text-info"></i><%=annoCorrente %></h6>
                                    </li>
                                </ul>
                                <h4 class="card-title">Progetti formativi per mese</h4>
                                <div class="clear"></div>
                                <div class="total-revenue" style="height: 240px;"></div>
                            </div>
                        </div>
                    </div>
                    </div>
                        </div>
                        <!--          -->
                     
                </div>
                 
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
    <!-- ============================================================== -->
    <!-- Style switcher -->
    <!-- ============================================================== -->
    <script src="assets/plugins/styleswitcher/jQuery.style.switcher.js"></script>
    <script>
    	var numero = 1;
    	var mese1,mese2,mese3,mese4,mese5,mese6,mese7,mese8,mese9,mese10,mese11,mese12;
    	mese1 = $('#mese0').val();
    	mese2 = $('#mese1').val();
    	mese3 = $('#mese2').val();
    	mese4 = $('#mese3').val();
    	mese5 = $('#mese4').val();
    	mese6 = $('#mese5').val();
    	mese7 = $('#mese6').val();
    	mese8 = $('#mese7').val();
    	mese9 = $('#mese8').val();
    	mese10 = $('#mese9').val();
    	mese11 = $('#mese10').val();
    	mese12 = $('#mese11').val();
    	
		var grafico;
		$(document).ready(function() {
			$(function () {
			    "use strict";
			    // ============================================================== 
			    // Total revenue chart
			    // ============================================================== 
			    grafico = new Chartist.Line('.total-revenue', {
			        labels: ['gen', 'feb', 'mar', 'apr', 'mag', 'giu','lug','ago','set','ott','nov','dic']
			        , series: [
			        [mese1, mese2, mese3, mese4, mese5, mese6, mese7, mese8, mese9,mese10,mese11,mese12]
			      ]
			    }, {
			        high: 20
			        , low: 0
			        , fullWidth: true
			        , plugins: [
			        Chartist.plugins.tooltip()
			      ]
			        , // As this is axis specific we need to tell Chartist to use whole numbers only on the concerned axis
			        axisY: {
			            onlyInteger: true
			            , offset: 30
			            , labelInterpolationFnc: function (numero) {
			                return (numero);
			            }
			        }
			    });
			    }); 
			
			});
		</script>
</body>

</html>


