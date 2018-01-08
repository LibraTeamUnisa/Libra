<%@page import="it.unisa.libra.util.JspPagesIndex"%>
<%@page import="it.unisa.libra.bean.Segreteria"%>
<%@page import="it.unisa.libra.model.dao.ISegreteriaDao"%>
<%@page import="it.unisa.libra.bean.Presidente"%>
<%@page import="it.unisa.libra.model.dao.IPresidenteDao"%>
<%@page import="it.unisa.libra.bean.TutorInterno"%>
<%@page import="it.unisa.libra.model.dao.ITutorInternoDao"%>
<%@page import="it.unisa.libra.bean.Azienda"%>
<%@page import="it.unisa.libra.model.dao.IAziendaDao"%>
<%@page import="it.unisa.libra.bean.Studente"%>
<%@page import="it.unisa.libra.model.dao.IStudenteDao"%>
<%@page import="it.unisa.libra.bean.Utente"%>
<%@page import="it.unisa.libra.model.dao.IUtenteDao"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="it.unisa.libra.util.Actions"%>
 <% 
 	boolean isStudenteHEADER = false;
	boolean isAziendaHEADER = false;
	boolean isTutorInternoHEADER = false;
	boolean isPresidenteHEADER = false;
	boolean isSegreteriaHEADER = false;
	
 	String ruoloHEADER = (String) request.getSession().getAttribute("utenteRuolo"); 
	String emailUtenteHEADER = (String) request.getSession().getAttribute("utenteEmail");
	String ruoloUtenteHEADER = (String) request.getSession().getAttribute("utenteRuolo");
	String nomeUtenteHEADER = "";
	
	IUtenteDao utenteDaoHEADER = (IUtenteDao) new InitialContext().lookup("java:app/Libra/UtenteJpa");
	Utente utenteHEADER = utenteDaoHEADER.findById(Utente.class, emailUtenteHEADER);

	switch (ruoloUtenteHEADER) {
	case "Studente": {
		IStudenteDao dao = (IStudenteDao) new InitialContext().lookup("java:app/Libra/StudenteJpa");
		Studente st = dao.findById(Studente.class, emailUtenteHEADER);
		nomeUtenteHEADER = st.getNome() + " " + st.getCognome();
		isStudenteHEADER = true;
		break;
	}
	case "Azienda": {
		IAziendaDao dao = (IAziendaDao) new InitialContext().lookup("java:app/Libra/AziendaJpa");
		Azienda a = dao.findById(Azienda.class, emailUtenteHEADER);
		nomeUtenteHEADER = a.getNome();
		isAziendaHEADER = true;
		break;
	}
	case "TutorInterno": {
		ITutorInternoDao dao = (ITutorInternoDao) new InitialContext().lookup("java:app/Libra/TutorInternoJpa");
		TutorInterno t = dao.findById(TutorInterno.class, emailUtenteHEADER);
		nomeUtenteHEADER = t.getNome() + " " + t.getCognome();
		isTutorInternoHEADER = true;
		break;
	}
	case "Presidente": {
		IPresidenteDao dao = (IPresidenteDao) new InitialContext().lookup("java:app/Libra/PresidenteJpa");
		Presidente p = dao.findById(Presidente.class, emailUtenteHEADER);
		nomeUtenteHEADER = p.getNome() + " " + p.getCognome();
		isPresidenteHEADER = true;
		break;
	}
	case "Segreteria": {
		ISegreteriaDao dao = (ISegreteriaDao) new InitialContext().lookup("java:app/Libra/SegreteriaJpa");
		Segreteria s = dao.findById(Segreteria.class, emailUtenteHEADER);
		nomeUtenteHEADER = "Segreteria Unisa";
		isSegreteriaHEADER = true;
		break;
	}
	default: break;
}
	%>

<header class="topbar">
            <nav class="navbar top-navbar navbar-toggleable-sm navbar-light">
                <!-- ============================================================== -->
                <!-- Logo -->
                <!-- ============================================================== -->
                <div class="navbar-header">
                    <a class="navbar-brand" href="dashboard<%=ruoloHEADER%>.jsp">
                        <!-- Logo icon -->
                        <b>
                            <!--You can put here icon as well // <i class="wi wi-sunset"></i> //-->
                            <!-- Dark Logo icon -->
                            <img src="assets/images/logo-icon.png" alt="" class="dark-logo" />
                            <!-- Light Logo icon -->
                            <img src="assets/images/logo-icon.png" alt="homepage" class="light-logo" />
                        </b>
                        <!--End Logo icon -->
                        <!-- Logo text -->
                        <span>
                         <!-- dark Logo text -->
                         <img src="assets/images/logo-text.png" alt="homepage" class="dark-logo" />
                         <!-- Light Logo text -->    
                         <img src="assets/images/logo-text.png" class="light-logo" alt="homepage" /></span> </a>
                </div>
                <!-- ============================================================== -->
                <!-- End Logo -->
                <!-- ============================================================== -->
                <div class="navbar-collapse">
                    <!-- ============================================================== -->
                    <!-- toggle and nav items -->
                    <!-- ============================================================== -->
                    <ul class="navbar-nav mr-auto mt-md-0 ">
                        <!-- This is  -->
                        <li class="nav-item"> <a class="nav-link nav-toggler hidden-md-up text-muted waves-effect waves-dark" href="javascript:void(0)"><i class="ti-menu"></i></a> </li>
                        <li class="nav-item"> <a class="nav-link sidebartoggler hidden-sm-down text-muted waves-effect waves-dark" href="javascript:void(0)"><i class="icon-arrow-left-circle"></i></a> </li>
                       
                        <!-- ============================================================== -->
                        <!-- Messages -->
                        <!-- ============================================================== -->
                        <li class="nav-item">
                            <a class="nav-link text-muted waves-effect waves-dark" href="<%=JspPagesIndex.NOTIFICHE.substring(1)%>"> <i class="mdi mdi-email"></i>
                                <div class="notify"> <span class="heartbit"></span> <span class="point"></span> </div>
                            </a>
                        </li>
                        <!-- ============================================================== -->
                        <!-- End Messages -->
                        <!-- ============================================================== -->
                        
                    </ul>
                    <!-- ============================================================== -->
                    <!-- User profile and search -->
                    <!-- ============================================================== -->
                    <ul class="navbar-nav my-lg-0">
                        
                        <li class="nav-item dropdown">

                            <a class="nav-link dropdown-toggle text-muted waves-effect waves-dark" href="" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><img id="profiloImg" src="" onerror="this.src='assets/images/users/default.png';" alt="user" class="profile-pic" /></a>

                            <div class="dropdown-menu dropdown-menu-right animated flipInY">
                                <ul class="dropdown-user">
                                    <li>
                                        <div class="dw-user-box row">

                                            <div class="u-img col-4"><img id="profiloImg" src="" onerror="this.src='assets/images/users/default.png';" alt="user"></div>

                                            <div class="u-text col-8">
                                                <h4><%=nomeUtenteHEADER%></h4>
                                                <p class="text-muted"><%=utenteHEADER.getEmail()%></p>
                                                <a href="dashboard<%=ruoloHEADER%>.jsp" class="btn btn-rounded btn-danger btn-sm">Dashboard</a>
                                                </div>
                                        </div>
                                    </li>
                                    <li role="separator" class="divider"></li>
                                    <li><a href="profilo.jsp"><i class="ti-user"></i> Profilo</a></li>
                                    <li><a href="notifiche.jsp"><i class="ti-email"></i> Notifiche</a></li>
                                    <li role="separator" class="divider"></li>
                                    <li><a href="<%=request.getContextPath()%>/autenticazione?<%=Actions.ACTION+"="+Actions.LOGOUT%>"><i class="fa fa-power-off"></i> Logout</a></li>
                                </ul>
                            </div>
                        </li>
                        
                    </ul>
                </div>
            </nav>
        </header>