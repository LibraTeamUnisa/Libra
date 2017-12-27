<%@ page import="it.unisa.libra.util.Actions"%>

<aside class="left-sidebar">
	<!-- Sidebar scroll-->
	<div class="scroll-sidebar">
		<!-- User profile -->
		<div class="user-profile">
			<!-- User profile image -->
			<div class="profile-img">
				<img src="assets/images/users/1.jpg" alt="user" />
			</div>
			<!-- User profile text-->
			<div class="profile-text">
				<a href="#" class="dropdown-toggle link u-dropdown"
					data-toggle="dropdown" role="button" aria-haspopup="true"
					aria-expanded="true">Markarn Doe <span class="caret"></span></a>
				<div class="dropdown-menu animated flipInY">
					<a href="profilo.jsp" class="dropdown-item"><i class="ti-user"></i>
						My Profile</a> <a href="#" class="dropdown-item"><i
						class="ti-wallet"></i> My Balance</a> <a href="#"
						class="dropdown-item"><i class="ti-email"></i> Inbox</a>
					<div class="dropdown-divider"></div>
					<a href="#" class="dropdown-item"><i class="ti-settings"></i>
						Account Setting</a>
					<div class="dropdown-divider"></div>
					<a
						href="${pageContext.request.contextPath}/autenticazione?<%=Actions.ACTION+"="+Actions.LOGOUT%>"
						class="dropdown-item"><i class="fa fa-power-off"></i> Logout</a>
				</div>
			</div>
		</div>
		<!-- End User profile text-->
		<!-- Sidebar navigation-->
		<nav class="sidebar-nav">
			<ul id="sidebarnav">
				<li><a href="#" aria-expanded="false"><span
						class="hide-menu">Dashboard </span></a></li>
				<li><a href="#" aria-expanded="false"><span
						class="hide-menu">Apps</span></a></li>
				<li><a href="#" aria-expanded="false"><span
						class="hide-menu">Inbox</span></a></li>
				<li><a href="#" aria-expanded="false"><span
						class="hide-menu">Ui Elements</span></a></li>
				<%
					if (session.getAttribute("utenteRuolo") != null
							&& session.getAttribute("utenteRuolo").equals("Segreteria")) {
				%>
				<li><a href="gestionePermessi.jsp" aria-expanded="false"><span
						class="hide-menu">Permessi sui feedback</span></a></li>
				<%
					}
				%>
				<li><a href="contattiDipartimento.jsp" aria-expanded="false"><span
						class="hide-menu">Contatti Dipartimento</span></a></li>
				<li class="nav-devider"></li>

			</ul>
		</nav>
		<!-- End Sidebar navigation -->
	</div>
	<!-- End Sidebar scroll-->
	<!-- Bottom points-->
	<div class="sidebar-footer">

		<!-- item-->
		<a
			href="${pageContext.request.contextPath}/autenticazione?<%=Actions.ACTION+"="+Actions.LOGOUT%>"
			class="link" data-toggle="tooltip" title="Logout"><i
			class="mdi mdi-power"></i></a>
	</div>
	<!-- End Bottom points-->
</aside>