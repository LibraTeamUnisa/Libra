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
			setTimeout(function() {
			 				 window.location.href = "/Libra/profilo.jsp";
			}, 3000);				
			}else if(dato =="errore"){
				alert("PASSWORD NON MODIFICATA!");
				$("#error-message2").show();
			}
			
		});
	});
});