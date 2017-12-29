$(document).ready(function(){
	$("#feedbackStudenteForm").submit(function(e){
		e.preventDefault();
		$.post('inviaFeedbackStudente', {
			val1 : $("input[name='feedback1']:checked").val(),
			val2 : $("input[name='feedback2']:checked").val(),
			val3 : $("input[name='feedback3']:checked").val(),
			val4 : $("input[name='feedback4']:checked").val(),
			val5 : $("input[name='feedback5']:checked").val(),
			val6 : $("input[name='feedback6']:checked").val(),
			val7 : $("input[name='feedback7']:checked").val(),
			val8 : $("input[name='feedback8']:checked").val(),
			val9 : $("input[name='feedback9']:checked").val(),
			val10 : $("input[name='feedback10']:checked").val(),
			val11 : $("input[name='feedback11']:checked").val(),
			valNote : $("#note").val(),
			idProgettoFormativo : $("#idProgettoFormativo").val(),
		},
		function(dato){
			if(dato == "salvato"){
				$("#success").show();
				$("input[name=feedback1]").attr('disabled', true);
				$("input[name=feedback2]").attr('disabled', true);
				$("input[name=feedback3]").attr('disabled', true);
				$("input[name=feedback4]").attr('disabled', true);
				$("input[name=feedback5]").attr('disabled', true);
				$("input[name=feedback6]").attr('disabled', true);
				$("input[name=feedback7]").attr('disabled', true);
				$("input[name=feedback8]").attr('disabled', true);
				$("input[name=feedback9]").attr('disabled', true);
				$("input[name=feedback10]").attr('disabled', true);
				$("input[name=feedback11]").attr('disabled', true);
				$("#note").attr('disabled', true);
				$("#inviaValutazione").attr('disabled', true);
				$("#inviaValutazione").hide();
			}
		})
	})
})