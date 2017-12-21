$(document).ready(function() {
	$("#passwordnuovaform").submit(function(e){
		e.preventDefault();
		$.post('modificaPassword', {
			pwn1 : $("#pwn1").val(),
			pwn2 : $("#pwn2").val(),
			action : "cambia"
			},
		function(dato){
			if(dato == "finito"){
			$("#success").show();
			$("#error-message2").hide();
			
			setTimeout(function() {
			 				 window.location.href = "/Libra/profilo.jsp";
			}, 3000);				
			}else if(dato =="errore"){
				$("#success").hide();
				$("#error-message2").show();
			}
			
		});
	});
});