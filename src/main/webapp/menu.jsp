
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="it.unisa.libra.model.dao.IUtenteDao" %>
<%@ page import="javax.naming.InitialContext" %>
<%@ page import="javax.naming.Context" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.Map.Entry" %>
<%@ page import="it.unisa.libra.bean.Segreteria" %>
<%@ page import="it.unisa.libra.bean.Utente" %>
<%@ page import="it.unisa.libra.bean.Presidente" %>
<%@ page import="it.unisa.libra.util.Actions" %>

<%
	String nomeUtente = "";
	String cognomeUtente = "";
	String ruoloUtente = "";
	String emailUtente = "";
	boolean segreteria = false;
	boolean presidente = false;
	String immagineProfilo = "";
	IUtenteDao utenteDBAccess = (IUtenteDao) new InitialContext().lookup("java:app/Libra/UtenteJpa");
	if(request.getSession().getAttribute("utenteRuolo")!=null && request.getSession().getAttribute("utenteEmail")!=null){
		ruoloUtente = (String) request.getSession().getAttribute("utenteRuolo");
		emailUtente = (String) request.getSession().getAttribute("utenteEmail");
		
		Utente utenteVar = null;
		Presidente accountPresidente = null;
		
		if(ruoloUtente.equals("Segreteria")){
			utenteVar = utenteDBAccess.findById(Utente.class, emailUtente);
			nomeUtente = emailUtente;
			immagineProfilo = utenteVar.getImgProfilo();
			cognomeUtente = "";
			segreteria = true;
			
		}else if(ruoloUtente.equals("Presidente")){
			utenteVar = utenteDBAccess.findById(Utente.class, emailUtente);
			accountPresidente = utenteVar.getPresidente();
			immagineProfilo = utenteVar.getImgProfilo();
			nomeUtente = accountPresidente.getNome();
			cognomeUtente = accountPresidente.getCognome();
			presidente = true;	
		}
	}
%>


<aside class="left-sidebar">
            <!-- Sidebar scroll-->
            <div class="scroll-sidebar">
                <!-- User profile -->
                <div class="user-profile">
                    <!-- User profile image -->
                    <%if(immagineProfilo!=null&&!(immagineProfilo.equals(""))){ %>
                    <div class="profile-img"> <img src=<%=immagineProfilo+""%> alt="" /> </div>
                    <%}else { %>
                    <div class="profile-img"> <img src="assets/images/logo-icon.png" alt="" /> </div>
                    <%} %>
                    <!-- User profile text-->
                    <div class="profile-text"> <a href="#" class="dropdown-toggle link u-dropdown" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="true"><%=(nomeUtente+" "+cognomeUtente)%><span class="caret"></span></a>
                        <div class="dropdown-menu animated flipInY">
                            <a href="profilo.jsp" class="dropdown-item"><i class="ti-user"></i> Il mio profilo</a>
                            <a href="notifiche.jsp" class="dropdown-item"><i class="ti-email"></i> Notifiche</a>
                          <!--    <div class="dropdown-divider"></div> <a href="#" class="dropdown-item"><i class="ti-settings"></i> Account Setting</a> -->
                              <div class="dropdown-divider"></div> <a href="${pageContext.request.contextPath}/autenticazione?<%=Actions.ACTION+"="+Actions.LOGOUT%>" class="dropdown-item"><i class="fa fa-power-off"></i> Logout</a>
                        </div>
                    </div>
                </div>
                <!-- End User profile text-->
                <!-- Sidebar navigation-->
                <nav class="sidebar-nav">
                    <ul id="sidebarnav">
                        <li>
                        	<%if(segreteria==true || presidente==true){ %>
                        	<a href="catalogoAziende.jsp" aria-expanded="false"><span class="hide-menu">Catalogo aziende</span></a>       
                            <%}else {%>
                            <a href="#" aria-expanded="false"><span class="hide-menu">Apps</span></a>
                            <%} %>                     
                        </li>
                        <li>
                        	<%if(segreteria==true || presidente ==true){ %>
                        	<a href="listaStudenti.jsp" aria-expanded="false"><span class="hide-menu">Studenti</span></a> 
                            <%}else {%>
                            <a href="#" aria-expanded="false"><span class="hide-menu">Apps</span></a>
                            <%} %>
                        </li>
                        <li>
                        	<%if(segreteria==true){ %>
                        	<a href="statistiche.jsp" aria-expanded="false"><span class="hide-menu">Statistiche</span></a>  
                        	<%}else if(presidente==true){%>
                            <a href="reportStudente.jsp" aria-expanded="false"><span class="hide-menu">Report</span></a>              
                            <%}else {%>
                            <a href="#" aria-expanded="false"><span class="hide-menu">Apps</span></a>
                            <%} %>            
                        </li>
                        <li>
                        	<%if(segreteria==true){ %>
                        	<a href="gestionePermessi.jsp" aria-expanded="false"><span class="hide-menu">Permessi feedback</span></a>   
                        	<%}else if(presidente == true){%>
                            <a href="statistiche.jsp" aria-expanded="false"><span class="hide-menu">Statistiche</span></a>       
                            <%}else {%>
                            <a href="#" aria-expanded="false"><span class="hide-menu">Apps</span></a>
                            <%} %>                    
                        </li>
                        	<%if(segreteria==true){ %>
                        	<li>
                        	<a href="aggiungiUtente.jsp" aria-expanded="false"><span class="hide-menu">Aggiungi utente</span></a> 
                        	 </li>
                        	<li>
                            <a href="rimuoviUtente.jsp" aria-expanded="false"><span class="hide-menu">Rimuovi utente</span></a> 
                        	<% }%>
                        	 </li>
                        <li>
                        	<%if(segreteria==true || presidente==true){ %>
                        	<a href="contattiDipartimento.jsp" aria-expanded="false"><span class="hide-menu">Contatti Dipartimento</span></a>    
                            <%}else {%>
                            <a href="#" aria-expanded="false"><span class="hide-menu">Apps</span></a>
                            <%} %>                               
                        </li>
                        <li class="nav-devider"></li>
                   
                    </ul>
                </nav>
                <!-- End Sidebar navigation -->
            </div>
            <!-- End Sidebar scroll-->
            <!-- Bottom points-->
            <div class="sidebar-footer">
                
                <!-- item -->
                <a href="${pageContext.request.contextPath}/autenticazione?<%=Actions.ACTION+"= "+ Actions.LOGOUT %>" class="link" data-toggle="tooltip" title="Logout"><i class="mdi mdi-power"></i></a>
          
            </div>
            <!-- End Bottom points-->
        </aside>