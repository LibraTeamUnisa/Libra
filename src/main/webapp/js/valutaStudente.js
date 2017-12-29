function controlloNote() {
	var note = document.getElementById("note").value;
	var campoNote = false;
	var lungNote = note.lenght;
	if (note == "") {
		document.getElementById("hideNote").innerHTML = "Il campo non pu&ograve; essere vuoto.";
		campoNote = false;
	} else if (lungNote > 200) {
		document.getElementById("hideNote").innerHTML = "Il campo non pu&ograve; essere di oltre 200 caratteri.";
		campoNote = false;
	} else {
		document.getElementById("hideNote").innerHTML = "";
		campoNote = true;
	}

	if (campoNote == true) {
		document.getElementById("inviaValutazione").removeAttribute("disabled");
	} else {
		document.getElementById("inviaValutazione").setAttribute("disabled",
				true);
	}
}