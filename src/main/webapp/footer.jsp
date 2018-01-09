<%@ page import="it.unisa.libra.model.dao.IUtenteDao"%>
<%@ page import="javax.naming.InitialContext"%>
<%@ page import="javax.naming.Context"%>
<%@ page import="it.unisa.libra.bean.Utente"%>
<%
	String emailUtente = (String) session.getAttribute("utenteEmail");
%>

<footer class="footer"> &copy; Libra (Since 2018) </footer>

<script src="assets/plugins/jquery/jquery.min.js"></script>

<script type="text/javascript">
		var mostraImmagine = function() {
			$.get('caricaImmagine?action=mostra&email=<%=emailUtente%>', function(
				data, status) {
			$('img.imgProfiloUtenteCorrente').attr('src', atob(data));
		});
	}
	mostraImmagine();
</script>
