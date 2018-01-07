<%@page import="it.unisa.libra.model.dao.ISegreteriaDao"%>
<%@page import="it.unisa.libra.model.dao.IPresidenteDao"%>
<%@page import="it.unisa.libra.model.dao.ITutorInternoDao"%>
<%@page import="it.unisa.libra.bean.Azienda"%>
<%@page import="it.unisa.libra.model.dao.IAziendaDao"%>
<%@page import="it.unisa.libra.model.dao.IStudenteDao"%>
<%@page import="it.unisa.libra.util.JspPagesIndex"%>
<%@page import="it.unisa.libra.bean.Studente"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="it.unisa.libra.model.dao.IUtenteDao"%>
<%@ page import="javax.naming.InitialContext"%>
<%@ page import="javax.naming.Context"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.Map.Entry"%>
<%@ page import="it.unisa.libra.bean.Segreteria"%>
<%@ page import="it.unisa.libra.bean.Utente"%>
<%@ page import="it.unisa.libra.bean.Presidente"%>
<%@ page import="it.unisa.libra.bean.TutorInterno"%>
<%@ page import="it.unisa.libra.util.Actions"%>

<%
	/* ci sono tutti i suffissi menu per evitare conflitti con altre variabili:
		il menu è incluso in tutte le altre jsp */
	boolean isStudenteMENU = false;
	boolean isAziendaMENU = false;
	boolean isTutorInternoMENU = false;
	boolean isPresidenteMENU = false;
	boolean isSegreteriaMENU = false;

	String emailUtenteMENU = (String) request.getSession().getAttribute("utenteEmail");
	String ruoloUtenteMENU = (String) request.getSession().getAttribute("utenteRuolo");
	String nomeUtenteMENU = "";

	IUtenteDao utenteDaoMENU = (IUtenteDao) new InitialContext().lookup("java:app/Libra/UtenteJpa");
	Utente utenteMENU = utenteDaoMENU.findById(Utente.class, emailUtenteMENU);
	String pathImmagineProfiloMENU = utenteMENU.getImgProfilo();

	switch (ruoloUtenteMENU) {
		case "Studente" : {
			IStudenteDao dao = (IStudenteDao) new InitialContext().lookup("java:app/Libra/StudenteJpa");
			Studente st = dao.findById(Studente.class, emailUtenteMENU);
			nomeUtenteMENU = st.getNome() + " " + st.getCognome();
			isStudenteMENU = true;
			break;

		}
		case "Azienda" : {
			IAziendaDao dao = (IAziendaDao) new InitialContext().lookup("java:app/Libra/AziendaJpa");
			Azienda a = dao.findById(Azienda.class, emailUtenteMENU);
			nomeUtenteMENU = a.getNome();
			isAziendaMENU = true;
			break;
		}
		case "TutorInterno" : {
			ITutorInternoDao dao = (ITutorInternoDao) new InitialContext()
					.lookup("java:app/Libra/TutorInternoJpa");
			TutorInterno t = dao.findById(TutorInterno.class, emailUtenteMENU);
			nomeUtenteMENU = t.getNome() + " " + t.getCognome();
			isTutorInternoMENU = true;
			break;
		}
		case "Presidente" : {
			IPresidenteDao dao = (IPresidenteDao) new InitialContext().lookup("java:app/Libra/PresidenteJpa");
			Presidente p = dao.findById(Presidente.class, emailUtenteMENU);
			nomeUtenteMENU = p.getNome() + " " + p.getCognome();
			isPresidenteMENU = true;
			break;
		}
		case "Segreteria" : {
			ISegreteriaDao dao = (ISegreteriaDao) new InitialContext().lookup("java:app/Libra/SegreteriaJpa");
			Segreteria s = dao.findById(Segreteria.class, emailUtenteMENU);
			nomeUtenteMENU = "Segreteria Unisa";
			isSegreteriaMENU = true;
			break;
		}
		default :
			break;
	}
%>

<aside class="left-sidebar">
	<!-- Sidebar scroll-->
	<div class="scroll-sidebar">


		<!-- User profile -->
		<div class="user-profile">
			<!-- User profile image -->
			<%
				if (pathImmagineProfiloMENU != null && !("".equals(pathImmagineProfiloMENU))) {
			%>
			<div class="profile-img">
				<img src="" id="profiloImg" alt="" />
			</div>
			<%
				} else {
			%>
			<div class="profile-img">
				<img src="assets/images/logo-icon.png" alt="" />
			</div>
			<%
				}
			%>

			<!-- User profile text-->
			<div class="profile-text">
				<a href="#" class="dropdown-toggle link u-dropdown"
					data-toggle="dropdown" role="button" aria-haspopup="true"
					aria-expanded="true"><%=nomeUtenteMENU%><span class="caret"></span></a>
				<div class="dropdown-menu animated flipInY">
					<a href="<%=JspPagesIndex.PROFILO.substring(1)%>"
						class="dropdown-item"><i class="ti-user"></i>Profilo</a> <a
						href="<%=JspPagesIndex.NOTIFICHE.substring(1)%>"
						class="dropdown-item"><i class="ti-email"></i>Notifiche</a>
					<div class="dropdown-divider"></div>
					<a
						href="<%=request.getContextPath()%>/autenticazione?<%=Actions.ACTION + "=" + Actions.LOGOUT%>"
						class="dropdown-item"><i class="fa fa-power-off"></i>Logout</a>
				</div>
			</div>
		</div>
		<!-- End User profile text-->


		<!-- Sidebar navigation-->
		<nav class="sidebar-nav">
			<ul id="sidebarnav">


				<%
					if (isStudenteMENU) {
				%>
				<!-- menu Studente -->
				<li><a href="<%=JspPagesIndex.CATALOGO_AZIENDE.substring(1)%>"
					aria-expanded="false"><span class="hide-menu">Aziende
							convenzionate</span></a></li>
				<li><a href="<%=JspPagesIndex.REPORT_STUDENTE.substring(1)%>"
					aria-expanded="false"><span class="hide-menu">Report</span></a></li>
				<li><a
					href="<%=JspPagesIndex.QUESTIONARIO_VALUTA_AZIENDA.substring(1)%>"
					aria-expanded="false"><span class="hide-menu">Valuta
							Azienda</span></a></li>

				<%
					} else if (isAziendaMENU) {
				%>
				<!-- menu Azienda -->
				<li><a href="<%=JspPagesIndex.CARICA_PPF.substring(1)%>"
					aria-expanded="false"><span class="hide-menu">Proposte
							Progetti Formativi</span></a></li>
				<li><a href="<%=JspPagesIndex.PF_INVIATI.substring(1)%>"
					aria-expanded="false"><span class="hide-menu">Progetti
							Formativi</span></a></li>
				<li><a
					href="<%=JspPagesIndex.QUESTIONARIO_VALUTA_STUDENTE.substring(1)%>"
					aria-expanded="false"><span class="hide-menu">Valuta
							Studenti</span></a></li>

				<%
					} else if (isTutorInternoMENU) {
				%>
				<!-- menu Tutor Interno -->
				<li><a href="<%=JspPagesIndex.CATALOGO_AZIENDE.substring(1)%>"
					aria-expanded="false"><span class="hide-menu">Aziende
							convenzionate</span></a></li>
				<li><a href="<%=JspPagesIndex.LISTA_STUDENTI.substring(1)%>"
					aria-expanded="false"><span class="hide-menu">Studenti</span></a></li>
				<li><a href="<%=JspPagesIndex.REPORT_STUDENTE.substring(1)%>"
					aria-expanded="false"><span class="hide-menu">Report</span></a> <%
 	} else if (isPresidenteMENU) {
 %> <!-- menu Presidente -->
				<li><a href="<%=JspPagesIndex.CATALOGO_AZIENDE.substring(1)%>"
					aria-expanded="false"><span class="hide-menu">Aziende
							convenzionate</span></a></li>
				<li><a href="<%=JspPagesIndex.LISTA_STUDENTI.substring(1)%>"
					aria-expanded="false"><span class="hide-menu">Studenti</span></a></li>
				<li><a href="<%=JspPagesIndex.REPORT_STUDENTE.substring(1)%>"
					aria-expanded="false"><span class="hide-menu">Report</span></a></li>
				<li><a href="<%=JspPagesIndex.STATISTICHE.substring(1)%>"
					aria-expanded="false"><span class="hide-menu">Statistiche</span></a></li>

				<%
					} else if (isSegreteriaMENU) {
				%>
				<!-- menu Segreteria -->
				<li><a href="<%=JspPagesIndex.CATALOGO_AZIENDE.substring(1)%>"
					aria-expanded="false"><span class="hide-menu">Aziende
							convenzionate</span></a></li>
				<li><a href="<%=JspPagesIndex.LISTA_STUDENTI.substring(1)%>"
					aria-expanded="false"><span class="hide-menu">Studenti</span></a></li>
				<li><a href="<%=JspPagesIndex.REPORT_STUDENTE.substring(1)%>"
					aria-expanded="false"><span class="hide-menu">Report</span></a></li>
				<li><a href="<%=JspPagesIndex.STATISTICHE.substring(1)%>"
					aria-expanded="false"><span class="hide-menu">Statistiche</span></a></li>
				<li><a href="<%=JspPagesIndex.GESTIONE_PERMESSI.substring(1)%>"
					aria-expanded="false"><span class="hide-menu">Permessi
							feedback</span></a></li>
				<li><a href="<%=JspPagesIndex.AGGIUNGI_UTENTE.substring(1)%>"
					aria-expanded="false"><span class="hide-menu">Aggiungi
							utente</span></a></li>
				<li><a href="<%=JspPagesIndex.RIMUOVI_UTENTE.substring(1)%>"
					aria-expanded="false"><span class="hide-menu">Rimuovi
							utente</span></a></li>
				<%
					}
				%>

				<!-- menu comune a tutti gli attori -->
				<li><a
					href="<%=JspPagesIndex.CONTATTI_DIPARTIMENTO.substring(1)%>"
					aria-expanded="false"><span class="hide-menu">Contatti
							Dipartimento</span></a></li>

				<li class="nav-devider"></li>

			</ul>
		</nav>
		<!-- End Sidebar navigation -->
	</div>
	<!-- End Sidebar scroll-->


	<!-- Bottom points-->
	<div class="sidebar-footer">
		<!-- item -->
		<a
			href="<%=request.getContextPath()%>/autenticazione?<%=Actions.ACTION + "=" + Actions.LOGOUT%>"
			class="link" data-toggle="tooltip" title="Logout"><i
			class="mdi mdi-power"></i></a>
	</div>
	<!-- End Bottom points-->
	<script type="text/javascript">
		var mostraImmagine = function() {
			$.get("caricaImmagine?action=carica", function(data, status) {
				$('#profiloImg').attr('src', atob(data));
			});
		}
		mostraImmagine();
	</script>
</aside>