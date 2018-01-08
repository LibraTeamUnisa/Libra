
!function($) {
    "use strict";

    var SweetAlert = function() {};

    //examples 
    SweetAlert.prototype.init = function() {
        
    //Basic
    $('#sa-basic').click(function(){
        swal("Here's a message!");
    });

    //A title with a text under
    $('#sa-title').click(function(){
        swal("Here's a message!", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed lorem erat eleifend ex semper, lobortis purus sed.")
    });

    //Success Message
    $('#sa-success').click(function(){
        swal("Good job!", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed lorem erat eleifend ex semper, lobortis purus sed.", "success")
    });

    //Warning Message
    $('#sa-warning').click(function(){
        swal({   
            title: "Are you sure?",   
            text: "You will not be able to recover this imaginary file!",   
            type: "warning",   
            showCancelButton: true,   
            confirmButtonColor: "#DD6B55",   
            confirmButtonText: "Yes, delete it!",   
            closeOnConfirm: false 
        }, function(){   
            swal("Deleted!", "Your imaginary file has been deleted.", "success"); 
        });
    });
    
    //Warning Message Stato Tirocinio
    $('#sa-warning-tirocinio').click(function(){
        swal({   
            title: "Sei sicuro?",   
            text: "Lo stato del tirocinio verr\xE0 cambiato.",   
            type: "warning",   
            showCancelButton: true,   
            confirmButtonColor: "#DD6B55",   
            confirmButtonText: "Prosegui",   
            closeOnConfirm: false 
        }, function(){ 
        	var pfId = $("#pfId").val();
        	var newStato = $("#select-stato").find(":selected").val();
        	var newStatoText = $("#select-stato").find(":selected").text();
        	var stato = $("#stato").val();
        	var statoText = $("[name='select-stato'][value='" + stato + "']").text();
        	
        	$.post('gestionePfServlet', 
					{
						action: "modificaStato",
						id: pfId,
						stato: newStato 
					}, function(data) {
						if (data == "true") {
				            swal({
				            	title: "Stato tirocinio cambiato!",   
				                text: "Lo stato del tirocinio \xE8 stato modificato con successo da "+ statoText + " a " + newStatoText + ".",   
				                type: "success",
				            }, function() {
				            	location.reload();
				            }); 
				            
						} else {
							swal("Errore", "Lo stato del tirocinio non \xE8; stato cambiato", "error");
						}
					});
        	
        });
    });    

    //Parameter
    $('#sa-params').click(function(){
        swal({   
            title: "Are you sure?",   
            text: "You will not be able to recover this imaginary file!",   
            type: "warning",   
            showCancelButton: true,   
            confirmButtonColor: "#DD6B55",   
            confirmButtonText: "Yes, delete it!",   
            cancelButtonText: "No, cancel plx!",   
            closeOnConfirm: false,   
            closeOnCancel: false 
        }, function(isConfirm){   
            if (isConfirm) {     
                swal("Deleted!", "Your imaginary file has been deleted.", "success");   
            } else {     
                swal("Cancelled", "Your imaginary file is safe :)", "error");   
            } 
        });
    });

    //Custom Image
    $('#sa-image').click(function(){
        swal({   
            title: "Govinda!",   
            text: "Recently joined twitter",   
            imageUrl: "../assets/images/users/1.jpg" 
        });
    });

    //Auto Close Timer
    $('#sa-close').click(function(){
         swal({   
            title: "Auto close alert!",   
            text: "I will close in 2 seconds.",   
            timer: 2000,   
            showConfirmButton: false 
        });
    });

    
  //js per salvaPermessi
    $("#permessiForm").submit(function(e) {
    		e.preventDefault();
    		$.post('permessi', {
    			checkboxStudente: $('#studenteRicevuti').is(':checked'),
    			radioStudente: $("input[name='radioStudente']:checked").val(),
    			radioAzienda: $("input[name='radioAzienda']:checked").val(),
    			checkboxTutorInterno: $('#tutorInternoRicevuti').is(':checked'),
    			radioTutorInterno: $("input[name='radioTutorInterno']:checked").val(),
    			checkboxPresidente: $('#presidenteRicevuti').is(':checked'),
    			radioPresidente: $("input[name='radioPresidente']:checked").val(),
    			checkboxSegreteria: $('#segreteriaRicevuti').is(':checked'),
    			radioSegreteria: $("input[name='radioSegreteria']:checked").val(),
    		}, function(data) {
    			if (data == "true") {
    				swal({
    					title: "",
    					text: "Modifiche effettuate con successo",
    					timer: 7000,
    					type: "success"
    				})
    			} else {
    				swal({
    					title: "",
    					text: "Si \xE8 verificato un errore",
    					timer: 7000,
    					type: "warning"
    				})
    			}
    		});
    	});


    },
    //init
    $.SweetAlert = new SweetAlert, $.SweetAlert.Constructor = SweetAlert
}(window.jQuery),

//initializing 
function($) {
    "use strict";
    $.SweetAlert.init()
}(window.jQuery);


