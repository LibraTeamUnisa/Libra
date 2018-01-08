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
	            
	    <section id="wrapper">
	    
	       <center>
	    		<div>
	    			<select id="ruolo" name="ruolo" onchange="cambiaForm()">
	         			<option value="Presidente" name="Presidente" selected="selected" >Presidente</option>
	         			<option value="TutorInterno" name="TutorInterno">Tutor Interno</option>
	         			<option value="Azienda" name="Azienda">Azienda</option>
	    			</select>
	    		</div>
	    	</center>
	    
	    <div id="formPresidente" style="display: block; " >
	    	<form action="GestioneUtenteServlet" method="post" class="form-horizontal form-material">
	    		<input type="hidden" name="ruolo" value="Presidente">
	    		<div class="row">
	  				<div class="col">Email Istituzionale:<input placeholder="" class="form-control" id="email" name="email" type="email" required></div>
		  			<div class="col">Nome:<input placeholder="" type="text" class="form-control" id="nome" name="nome" pattern="[A-Za-z]{1,20}" value="" required></div>
		  		</div>
		  		<br><br>
		  		<div class="row">	  	
		  			<div class="col">Cognome:<input placeholder="" type="text" class="form-control" id="cognome" name="cognome" pattern="[A-Za-z]{1,20}" value="" required></div>
		  			<div class="col">Telefono:<input placeholder="" type="text" class="form-control" id="telefono" name="telefono" value="" pattern="[0-9]{8-15}"  required></div>
		  		</div>	
		  		<br><br>
	          	<div class="row">
	          		<div class="col">Indirizzo:<input placeholder="" type="text" class="form-control" id="indirizzo" name="indirizzo"  value=""  required></div>
	 				<div class="col">Ufficio: <input placeholder="" type="text" class="form-control" id="ufficio" name="ufficio" value=""  required></div>
		  		</div>
		  		<br><br>	
	          	<div class="row">
	          		<div class="col">Giorni di ricevimento:<input placeholder="" type="text" class="form-control" id="giornoDiRicevimento" name="giornoDiRicevimento" value="" required></div>
	 				<div class="col">WebSite:<input placeholder="" type="text" class="form-control" id="webSite" name="webSite"value="" required></div>
		  		</div>	
		  		<br><br>
	          	<div class="row">
	          		<div class="col">Data Di Nascita:<input placeholder="" type="date" class="form-control" id="bday" name="bday" value="" required></div>
		  		</div>	
		  		<br><br>
	          	<div class="row">
	 				<div class="col">Password:<input placeholder="" type="password" class="form-control" id="password" name="password"  required></div>
	          		<div class="col">Conferma Password:<input placeholder="" type="password" class="form-control" id="confirmPassword" name="confirmPassword" required></div>
		  		</div>	
		  		<br><br>
	          	<div class="row" align="center">
	          		<div class="col"><input class="button" type="submit" value="Invia"></div>
				</div>
				<br><br>
	    	</form>
	    </div>
	    
	    
	     <div id="formTutorInterno" class="form-horizontal form-material" style="display: none;">
	    	<form action="GestioneUtenteServlet" method="post" class="form-horizontal form-material">
	    		<input type="hidden" name="ruolo" value="TutorEsterno">
	    		<div class="row">
	  				<div class="col">Email Istituzionale:<input placeholder="" class="form-control" id="email" name="email" type="email" required></div>
		  			<div class="col">Nome:<input placeholder="" type="text" class="form-control" id="nome" name="nome" pattern="[A-Za-z]{1,20}" value="" required></div>
		  		</div>
		  		<br><br>
		  		<div class="row">	  	
		  			<div class="col">Cognome:<input placeholder="" type="text" class="form-control" id="cognome" name="cognome" pattern="[A-Za-z]{1,20}" value="" required></div>
		  			<div class="col">Telefono:<input placeholder="" type="text" class="form-control" id="telefono" name="telefono" value="" pattern="[0-9]{8-15}"  required></div>
		  		</div>	
		  		<br><br>
	          	<div class="row">
	          		<div class="col">Indirizzo:<input placeholder="" type="text" class="form-control" id="indirizzo" name="indirizzo"  value=""  required></div>
	 				<div class="col">WebSite:<input placeholder="" type="text" class="form-control" id="webSite" name="webSite"value="" required></div>
		  		</div>
		  		<br><br>	
	          	<div class="row">
	          		<div class="col">Data Di Nascita:<input placeholder="" type="date" class="form-control" id="bday" name="bday" value="" required></div>
		  		</div>	
		  		<br><br>
	          	<div class="row">
	 				<div class="col">Password:<input placeholder="" type="password" class="form-control" id="password" name="password"  required></div>
	          		<div class="col">Conferma Password:<input placeholder="" type="password" class="form-control" id="confirmPassword" name="confirmPassword"  required></div>
		  		</div>	
		  		<br><br>
	          	<div class="row" align="center">
	          		<div class="col"><input class="button" type="submit" value="Invia"></div>
				</div>
				<br><br>
	    	</form>
	    </div>
	    
	    <div id="formAzienda" class="form-horizontal form-material" style="display: none;">
	    	<form action="GestioneUtenteServlet" method="post" class="form-horizontal form-material">
	    		<input type="hidden" name="ruolo" value="Azienda">
	    		<div class="row">
	  				<div class="col">Email Istituzionale:<input placeholder="" class="form-control" id="email" name="email" type="email" required></div>
		  			<div class="col">Nome:<input placeholder="" type="text" class="form-control" id="nome" name="nome" pattern="[A-Za-z]{1,20}" value="" required></div>
		  		</div>
		  		<br><br>
		  		<div class="row">	  	
		  			<div class="col">Partita IVA:<input placeholder="" type="text" class="form-control" id="partitaIva2" name="partitaIva" value="" pattern="[0-9]{11}" required></div>
		  			<div class="col">Telefono:<input placeholder="" type="text" class="form-control" id="telefono" name="telefono" value=""   required></div>
		  		</div>	
		  		<br><br>
	          	<div class="row">
	          		<div class="col">Indirizzo:<input placeholder="" type="text" class="form-control" id="indirizzo" name="indirizzo"  value=""  required></div>
	 				<div class="col">WebSite:<input placeholder="" type="text" class="form-control" id="webSite" name="webSite"value="" required></div>
		  		</div>
		  		<br><br>	
	          	<div class="row">
	 				<div class="col">Password:<input placeholder="" type="password" class="form-control" id="password" name="password"  required></div>
	          		<div class="col">Conferma Password:<input placeholder="" type="password" class="form-control" id="confirmPassword" name="confirmPassword"  required></div>
		  		</div>	
		  		<br><br>
	          	<div class="row" align="center">
	          		<div class="col"><input class="button" type="submit" value="Invia"></div>
				</div>
				<br><br>
	    	</form>
	    </div>
	    
	   <!--   
	   	
	   <div id="formPresidente" style="display: block; " >
	    <table class="customalignment">
	      <tr align="center">
	        <td align="center">
	         <form action="GestioneUtente" method="post" class="form-horizontal form-material">
	          <br><br><br><br>
	          
	           <input type="hidden" name="ruolo" value="Presidente">
	 
	          <div class="form-group ">
	          	<div class="col-xs-12">
	          		Email Istituzionale:	
	          		<input placeholder="" class="form-control" id="email" name="email" type="email" required>
	          		<br><br>
	          	</div>	
	          </div>	
	          <div class="form-group ">
	          	<div class="col-xs-12">
	          		Nome:
	          		<input placeholder="" type="text" class="form-control" id="nome" name="nome" pattern="[A-Za-z]{1,20}" value="" required>
	          		<br><br>
	          	</div>
	          </div>
	          <div class="form-group ">
	          	<div class="col-xs-12">
	          		Cognome:
	          		<input placeholder="" type="text" class="form-control" id="cognome" name="cognome" pattern="[A-Za-z]{1,20}" value="" required>
	          		<br><br>
	          	</div>
	          </div>
	          <div class="form-group ">
	          	<div class="col-xs-12">
	          		Data Di Nascita:
	          		<input placeholder="" type="date" class="form-control" id="bday" name="bday" value="" required>
	          		<br><br>
	          	</div>
	          </div>
	          <div class="form-group ">
	          	<div class="col-xs-12">
	          		Indirizzo:
	          		<input placeholder="" type="text" class="form-control" id="indirizzo" name="indirizzo"  value=""  required>
	          		<br><br>
	          	</div>
	          </div>
	          <div class="form-group ">
	          	<div class="col-xs-12">
	          		Ufficio:
	          		<input placeholder="" type="text" class="form-control" id="ufficio" name="ufficio" value=""  required>
	          		<br><br>
	          	</div>
	          </div>
	          <div class="form-group ">
	          	<div class="col-xs-12">
	          		Giorni di ricevimento:
	          		<input placeholder="" type="text" class="form-control" id="giornoDiRicevimento" name="giornoDiRicevimento" value="" required>
	          		<br><br>
	          	</div>
	          </div>
	          <div class="form-group ">
	          	<div class="col-xs-12">
	          		WebSite:
	          		<input placeholder="" type="text" class="form-control" id="webSite" name="webSite"value="" required>
	          		<br><br>
	          	</div>
	          </div>
	          <div class="form-group ">
	          	<div class="col-xs-12">
	          		Telefono:
	          		<input placeholder="" type="text" class="form-control" id="telefono" name="telefono" value="" pattern="[0-9]{8-15}"  required>
	          		<br><br>
	          	</div>
	          </div>
	          <div class="form-group ">
	          	<div class="col-xs-12">
	          		Password:
	          		<input placeholder="" type="password" class="form-control" id="password" name="password" pattern="[A-Za-z0-9]{8-20}" required>
	          		<br><br>
	          	</div>
	          </div>
	          <div class="form-group ">
	          	<div class="col-xs-12">
	          	Password:
	          		<input placeholder="" type="password" class="form-control" id="password" name="password" pattern="[A-Za-z0-9]{8-20}" required>
	          		Conferma Password:
	          		<input placeholder="" type="password" class="form-control" id="confirmPassword" name="confirmPassword" pattern="[A-Za-z0-9]{8-20}" required>
	          		<br><br>
	          	</div>
	          </div>
	          <div class="form-group ">
	          	<div class="col-xs-12">
	          		<input class="button" type="submit" value="Invia">
	          	</div>
	          </div>
	          </form>
	        </td>
	      </tr>
	    </table>
	    </div>
	    
	    
	        
	    <div id="formTutorInterno" class="form-horizontal form-material" style="display: none;">
	     <table class="customalignment">
	      <tr align="center">
	        <td align="center">
	        <form action="GestioneUtente" method="post" class="form-horizontal form-material">
	          <br><br><br><br>
	          
	          <input type="hidden" name="ruolo" value="TutorInterno">
	          
	     
	          
	          <br><br>
	       
	          <div class="form-group ">
	          	<div class="col-xs-12">
	          		Email Istituzionale:	
	          		<input placeholder="" class="form-control" id="email1"name="email" type="email" required>
	          		<br><br>
	          	</div>	
	          </div>
	          
	          <div class="form-group ">
	          	<div class="col-xs-12">
	          		Nome:
	          		<input placeholder="" type="text" class="form-control" id="nome1" name="nome" value="" pattern="[A-Za-z]{1,20}" required>
	          		<br><br>
	          	</div>
	          </div>
	          <div class="form-group ">
	          	<div class="col-xs-12">
	          		Cognome:
	          		<input placeholder="" type="text" class="form-control" id="cognome1" name="cognome" value="" pattern="[A-Za-z]{1,20}" required>
	          		<br><br>
	          	</div>
	          </div>
	          <div class="form-group ">
	          	<div class="col-xs-12">
	          		Data Di Nascita:
	          		<input placeholder="" type="date" class="form-control" id="bday1" name="bday" value="" required>
	          		<br><br>
	          	</div>
	          </div>
	          <div class="form-group ">
	          	<div class="col-xs-12">
	          		Indirizzo:
	          		<input placeholder="" type="text" class="form-control" id="indirizzo1" name="indirizzo" value="" required>
	          		<br><br>
	          	</div>
	          </div>
	          <div class="form-group ">
	          	<div class="col-xs-12">
	          		WebSite:
	          		<input placeholder="" type="text" class="form-control" id="webSite1" name="webSite" value="" required>
	          		<br><br>
	          	</div>
	          </div>
	          <div class="form-group ">
	          	<div class="col-xs-12">
	          		Telefono:
	          		<input placeholder="" type="text" class="form-control" id="telefono1" name="telefono" value="" pattern="[0-9]{8-15}" required>
	          		<br><br>
	          	</div>
	          </div>
	          <div class="form-group ">
	          	<div class="col-xs-12">
	          		Password:
	          		<input placeholder="" type="password" class="form-control" id="password1" name="password" pattern="[A-Za-z0-9]{8-20}"  required>
	          		<br><br>
	          	</div>
	          </div>
	          <div class="form-group ">
	          	<div class="col-xs-12">
	          		Conferma Password:
	          		<input placeholder="" type="password" class="form-control" id="confirmPassword1" name="confirmPassword" pattern="[A-Za-z0-9]{8-20}" required>
	          		<br><br>
	          	</div>
	          </div>
	          <div class="form-group ">
	          	<div class="col-xs-12">
	          		<input class="button" type="submit" value="Invia">
	          	</div>
	          </div>
	          </form>
	        </td>
	      </tr>
	    </table>
	    </div>
	   
	    <div id="formAzienda" class="form-horizontal form-material" style="display: none;">
	    <table class="customalignment">
	      <tr align="center">
	       
	        <td align="center">
	          <form action="GestioneUtente" method="post" class="form-horizontal form-material">
	          <br><br><br><br>
	          
	           <input type="hidden" name="ruolo" value="Azienda">
	           
	          
	          <div class="form-group ">
	          	<div class="col-xs-12">
	          		Email Istituzionale:	
	          		<input placeholder="" class="form-control" id="email2" name="email" type="email" required>
	          		<br><br>
	          	</div>	
	          </div>	
	          <div class="form-group ">
	          	<div class="col-xs-12">
	          		Nome:
	          		<input placeholder="" type="text" class="form-control" id="nome2" name="nome" value="" pattern="[A-Za-z]{1,20}" required>
	          		<br><br>
	          	</div>
	          </div>
	          <div class="form-group ">
	          	<div class="col-xs-12">
	          		Indirizzo:
	          		<input placeholder="" type="text" class="form-control" id="indirizzo2" name="indirizzo" value=""  required>
	          		<br><br>
	          	</div>
	          </div>
	          <div class="form-group ">
	          	<div class="col-xs-12">
	          		Partita IVA:<input placeholder="" type="text" class="form-control" id="partitaIva2" name="partitaIva" value="" pattern="[0-9]{11}" required>
	          		<br><br>
	          	</div>
	          </div>
	          <div class="form-group ">
	          	<div class="col-xs-12">
	          		WebSite:
	          		<input placeholder="" type="text" class="form-control" id="webSite2" name="webSite" value="" required>
	          		<br><br>
	          	</div>
	          </div>
	          <div class="form-group ">
	          	<div class="col-xs-12">
	          		Telefono:
	          		<input placeholder="" type="text" class="form-control" id="telefono2" name="telefono" value="" pattern="[0-9]{8-15}" required>
	          		<br><br>
	          	</div>
	          </div>
	          <div class="form-group ">
	          	<div class="col-xs-12">
	          		Password:
	          		<input placeholder="" type="password" class="form-control" id="password2" name="password" pattern="[A-Za-z0-9]{8-20}" required>
	          		<br><br>
	          	</div>
	          </div>
	          <div class="form-group ">
	          	<div class="col-xs-12">
	          		Conferma Password:
	          		<input placeholder="" type="password" class="form-control" id="confirmPassword2" name="confirmPassword" pattern="[A-Za-z0-9]{8-20}" required>
	          		<br><br>
	          	</div>
	          </div>
	          <div class="form-group ">
	          	<div class="col-xs-12">
	          		<input class="button" type="submit" value="Invia">
	          	</div>
	          </div>
	          </form>
	          
	        </td>
	      </tr>
	    </table>
	    </div>
	    
	    
	    -->
	    
	  </section>        
	             
	             
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
	    
	    <script >
	    	
	    function cambiaForm() { var ruolo = $( "#ruolo option:selected" ).val(); hideAllForms(); showForm(ruolo); } 
	    function hideAllForms() { $("#formPresidente").css({"display" : "none"}); $("#formTutorInterno").css({"display" : "none"}); $("#formAzienda").css({"display" : "none"}); } 
	    function showForm(r) { $("#form" + r).css({"display" : "block"}); }
	    
		</script>
		
		<script type="text/javascript">
			var password = document.getElementById("password"), confirmPassword = document.getElementById("confirmPassword");
			function validatePassword(){
				if(password.value != confirmPassword.value) {
					confirmPassword.setCustomValidity("Passwords Don't Match");
				} else {
					confirmPassword.setCustomValidity('');
				}	
			}
	
			password.onchange = validatePassword;
			confirmPassword.onkeyup = validatePassword;
		</script>
	    
	    
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