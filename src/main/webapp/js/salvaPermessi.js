/**
 * Gestisce le chiamate asincrone per il salvataggio dei permessi di
 * visualizzazione feedback.
 * I campi checkbox conterranno true e false e i radio conterranno il valore corrispondente.
 */
$(document).ready(function() {
	$("#permessiForm").submit(function(e) {
		e.preventDefault();
		$.post('permessi', {
			checkboxStudente: $('#studenteRicevuti').is(':checked'),
			radioStudente: $("input[name='radioStudente']:checked").val(),//attenzione ad undefined
			radioAzienda: $("input[name='radioAzienda']:checked").val(),
			checkboxTutorInterno: $('#tutorInternoRicevuti').is(':checked'),
			radioTutorInterno: $("input[name='radioTutorInterno']:checked").val(),
			checkboxPresidente: $('#presidenteRicevuti').is(':checked'),
			radioPresidente: $("input[name='radioPresidente']:checked").val(),
			checkboxSegreteria: $('#segreteriaRicevuti').is(':checked'),
			radioSegreteria: $("input[name='radioSegreteria']:checked").val(),
		}, function(data) {
			if (data == "true") {
				alert("modifiche effettuate");
			} else {
				alert("errore"+data);
			}
		});
	});
})	