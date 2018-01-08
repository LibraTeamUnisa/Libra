<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<!-- Tell the browser to be responsive to screen width -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description"
	content="Sezione che mostra statistiche relative agli studenti attivi o inattivi.">
<meta name="author" content="Solimando Giandomenico">
<!-- Favicon icon -->
<link rel="icon" type="image/png" sizes="16x16"
	href="assets/images/favicon.png">
<title>Libra</title>
<!-- Bootstrap Core CSS -->
<link rel="stylesheet"
	href="assets/plugins/dropify/dist/css/dropify.min.css">
<link href="assets/plugins/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<link href="assets/plugins/css-chart/css-chart.css" rel="stylesheet">
<!-- Editable CSS -->
<link type="text/css" rel="stylesheet"
	href="assets/plugins/jsgrid/dist/jsgrid.min.css" />
<link type="text/css" rel="stylesheet"
	href="assets/plugins/jsgrid/dist/jsgrid-theme.min.css" />
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
	<!-- Main wrapper - style you can find in pages.scss -->
	<!-- ============================================================== -->
	<div id="main-wrapper">
		<div class="row">
			<div class="col-lg-6 col-md-6">
				<div class="card">
					<div class="card-block">
						<h4 class="card-title">File Upload1</h4>
						<label for="input-file-now">Your so fresh input file —
							Default version</label> <input type="file" id="input-file-now"
							class="dropify" />
					</div>
				</div>
			</div>
		</div>
		<button class="btn pull-right hidden-sm-down btn-success"
			onclick="caricaImmagine()">
			<i class="mdi mdi-plus-circle"></i>Upload
		</button>
		<button class="btn pull-right hidden-sm-down btn-success"
			onclick="mostraImmagine()">
			<i class="mdi mdi-plus-circle"></i>Mostra
		</button>
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
	<script src="assets/plugins/styleswitcher/jQuery.style.switcher.js"></script>

	<!-- ============================================================== -->
	<!-- Plugins for this page -->
	<!-- ============================================================== -->
	<!-- jQuery file upload -->
	<script src="assets/plugins/dropify/dist/js/dropify.min.js"></script>
	<script>
    $(document).ready(function() {
        // Basic
        $('.dropify').dropify();

        // Translated
        $('.dropify-fr').dropify({
            messages: {
                default: 'Glissez-déposez un fichier ici ou cliquez',
                replace: 'Glissez-déposez un fichier ou cliquez pour remplacer',
                remove: 'Supprimer',
                error: 'Désolé, le fichier trop volumineux'
            }
        });

        // Used events
        var drEvent = $('#input-file-events').dropify();

        drEvent.on('dropify.beforeClear', function(event, element) {
            return confirm("Do you really want to delete \"" + element.file.name + "\" ?");
        });

        drEvent.on('dropify.afterClear', function(event, element) {
            alert('File deleted');
        });

        drEvent.on('dropify.errors', function(event, element) {
            console.log('Has Errors');
        });

        var drDestroy = $('#input-file-to-destroy').dropify();
        drDestroy = drDestroy.data('dropify')
        $('#toggleDropify').on('click', function(e) {
            e.preventDefault();
            if (drDestroy.isDropified()) {
                drDestroy.destroy();
            } else {
                drDestroy.init();
            }
        })
    });
    var caricaImmagine = function(){
    	var base64 = $(".dropify-render img[src]").attr("src");
    	$.get("caricaImmagine?file="+btoa(base64), function(data, status){
    		console.log(data);
    	});
    }
    var mostraImmagine = function(){
    	$.get("caricaImmagine?action=carica", function(data, status){
    		var image = new Image();
    		image.src = atob(data);
    		document.body.appendChild(image);
    	});
    }
    </script>


</body>

</html>


