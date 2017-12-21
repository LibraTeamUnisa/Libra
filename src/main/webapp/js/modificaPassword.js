$(document).ready(function() {
	$("#passwordform").submit(function(e) {
		e.preventDefault();
		$.post('modificaPassword', {
			password : $("#password").val(),
			action : "verifica"
		},
		 function(data) {
			if (data =="false") {
				$("#error-message").show();
				$("#password").val("");
			} else if(data == "true") {
				$("#error-message").hide();
				$("#error-message2").hide();
				$("#primoForm").hide(); 
				$("#passwordnuovaform").show();
			}
		});
	});
});
