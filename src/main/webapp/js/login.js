/**
 * Gestisce le chiamate asincrone per la procedura di login.
 */
$(document).ready(function() {
	$("#loginform").submit(function(e) {
		e.preventDefault();
		$.post('autenticazione', {
			email : $("#email").val(),
			password : $("#password").val()
		}, function(data) {
			if (data == "false") {
				$("#error-message").show();
				$("#email").val("");
				$("#password").val("");
			} else {
				$("#error-message").hide();
				window.location = data;
			}
		});
	});
})
