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

<script type="text/javascript" src="http://code.jquery.com/jquery-1.6.2.min.js"></script>


 

</head>


<body>



<script type="text/javascript">
$(document).ready(function() {

  $("#bottoneSubmit").click(function(){
	  
	    var nome = $('#nome').val();
		var cognome = $('#cognome').val();
		var matricola = $('#matricola').val();
		var email = $('#email').val();
		var password = $('#password').val();
		var password1 = $('#password').val();
		var dataNascita = $('#dataNascita').val();
		var indirizzo = $('#indirizzo').val();
		var telefono = $('#telefono').val();
		
    $.ajax({

     //imposto il tipo di invio dati (GET O POST)
      type: "POST",

      //Dove devo inviare i dati recuperati dal form?
      url: "registrazione",

      //Quali dati devo inviare?
      data: "nome= "+ nome + "&cognome=" + cognome + "&matricola=" + matricola + "&email="+ email+"&password="+password+"&dataNascita="+dataNascita+"&indirizzo="+indirizzo+"&telefono="+telefono,
      dataType: "html",

      //Inizio visualizzazione errori
      success: function(responseText)
      {
    	  alert(responseText);
    	  if(responseText.includes("successo")){
    	 	 window.location.href = "menu.jsp";
    	  }
    	  
    	  $('#nome').val("");
    	  $('#cognome').val("");
    	  $('#matricola').val("");
    	  $('#email').val("");
    	  $('#password').val("");
    	  $('#password1').val("");
    	  $('#dataNascita').val("");
    	  $('#indirizzo').val("");
    	  $('#telefono').val("");
      },
      error: function(responseText){
    	  alert(responseText);
          },
      
    });
  });
});
</script>
    <!-- ============================================================== -->
    <!-- Preloader - style you can find in spinners.css -->
    <!-- ============================================================== -->
 
    <!-- ============================================================== -->
    <!-- Main wrapper - style you can find in pages.scss -->
    <!-- ============================================================== -->
    <section id="wrapper" class="login-register login-sidebar"  style="background-image:url(../assets/images/background/login-register.jpg);">
  <div class="login-box card">
    <div class="card-block">
      <form class="form-horizontal form-material" id="loginform" >
        <a href="javascript:void(0)" class="text-center db"><img src="assets/images/logo-icon.png" alt="" class="dark-logo" /><br/><img src="assets/images/logo-icon.png" alt="" class="dark-logo" /></a> 
        <h3 class="box-title m-t-40 m-b-0">REGISTRATI</h3><small>Benvenuto su Libra</small> 
        <div class="form-group m-t-20">
          <div class="col-xs-12">
            <input class="form-control" type="text" required=""  placeholder="Nome" id="nome" name="nome">
          </div>
        </div>
        <div class="form-group ">
          <div class="col-xs-12">
            <input class="form-control" type="text" required="" placeholder="Cognome" id="cognome" name="cognome">
          </div>
        </div>
        <div class="form-group ">
          <div class="col-xs-12">
          
            <input class="form-control" type="email" required="" placeholder="E-mail" id="email" name="email">
          </div>
        </div>
        <div class="form-group ">
          <div class="col-xs-12">
            <input class="form-control" type="text" required="" placeholder="Matricola" id="matricola" name="email">
          </div>
        </div>
        
        <div class="form-group ">
          <div class="col-xs-12">
            <input class="form-control" type="password" required="" placeholder="Password" id="password" name="password">
          </div>
        </div>
        <div class="form-group">
          <div class="col-xs-12">
            <input class="form-control" type="password" required="" placeholder="Confirm Password" id="password1" name="password1">
          </div>
        </div>
        
        <div class="form-group ">
          <div class="col-xs-12">
            <input class="form-control" type="date" required="" placeholder="Data nascita" id="dataNascita" name="dataNascita">
          </div>
        </div>
         <div class="form-group ">
          <div class="col-xs-12">
            <input class="form-control" type="text" required="" placeholder="Indirizzo" id="indirizzo" name="indirizzo">
          </div>
        </div>
         <div class="form-group ">
          <div class="col-xs-12">
            <input class="form-control" type="number" required="" placeholder="Telefono" id="telefono" name="telefono">
          </div>
        </div>
        
        
        <div class="form-group text-center m-t-20">
          <div class="col-xs-12">
            <button class="btn btn-info btn-lg btn-block text-uppercase waves-effect waves-light" id="bottoneSubmit">Sign Up</button>
          </div>
        </div>
        <div class="form-group m-b-0">
          <div class="col-sm-12 text-center">
            <p>Hai già un account? <a href="" class="text-info m-l-5"><b>Accedi</b></a></p>
          </div>
        </div>
      </form>
    </div>
  </div>
</section>
    <!-- ============================================================== -->
    <!-- End Wrapper -->
    <!-- ============================================================== -->
    <!-- ============================================================== -->
    <!-- All Jquery -->
    <!-- ============================================================== -->
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
    <!-- Style switcher -->
    <!-- ============================================================== -->
    <script src="../assets/plugins/styleswitcher/jQuery.style.switcher.js"></script>

</body>


</html>


