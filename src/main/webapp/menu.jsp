<%@ page import="it.unisa.libra.util.Actions" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="it.unisa.libra.model.dao.ISegreteriaDao" %>
<%@ page import="it.unisa.libra.model.dao.IUtenteDao" %>
<%@ page import="javax.naming.InitialContext" %>
<%@ page import="javax.naming.Context" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.Map.Entry" %>
<%@ page import="it.unisa.libra.bean.Segreteria" %>
<%@ page import="it.unisa.libra.bean.Utente" %>


<%
	 
	IUtenteDao utenteDao = (IUtenteDao) new InitialContext().lookup("java:app/Libra/UtenteJpa");
	request.getSession().setAttribute("utenteRuolo", "Segreteria");
	request.getSession().setAttribute("utenteEmail", "segreteriaprova@unisa.it");
	String ruoloUtente = (String) request.getSession().getAttribute("utenteRuolo");
	String email = (String) request.getSession().getAttribute("utenteEmail");
	boolean segreteria = false;
	if(request.getSession().getAttribute("utenteRuolo").equals("Segreteria")){
		Utente utente = utenteDao.findById(Utente.class, "segreteriaprova@unisa.it");
		segreteria = true;
	}
%>


<aside class="left-sidebar">
            <!-- Sidebar scroll-->
            <div class="scroll-sidebar">
                <!-- User profile -->
                <div class="user-profile">
                    <!-- User profile image -->
                    <div class="profile-img"> <img src="assets/images/users/1.jpg" alt="" /> </div>
                    <!-- User profile text-->
                    <div class="profile-text"> <a href="#" class="dropdown-toggle link u-dropdown" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="true">Markarn Doe <span class="caret"></span></a>
                        <div class="dropdown-menu animated flipInY">
                            <a href="profilo.jsp" class="dropdown-item"><i class="ti-user"></i> Il mio profilo</a>
                            <a href="#" class="dropdown-item"><i class="ti-email"></i> Notifiche</a>
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
                        	<%if(segreteria==true){ %>
                        	<a href="#" aria-expanded="false"><span class="hide-menu">Catalogo aziende</span></a>     
                        	<%}else { %>
                            <a href="#" aria-expanded="false"><span class="hide-menu">Dashboard </span></a>    
                            <%} %>                        
                        </li>
                        <li>
                        	<%if(segreteria==true){ %>
                        	<a href="#" aria-expanded="false"><span class="hide-menu">Studenti</span></a> 
                        	<%}else {%>
                            <a href="#" aria-expanded="false"><span class="hide-menu">Apps</span></a>
                            <%} %>
                        </li>
                        <li>
                        	<%if(segreteria==true){ %>
                        	<a href="#" aria-expanded="false"><span class="hide-menu">Statistiche</span></a>  
                        	<%}else {%>
                            <a href="#" aria-expanded="false"><span class="hide-menu">Inbox</span></a>              
                            <%} %>              
                        </li>
                        <li>
                        	<%if(segreteria==true){ %>
                        	<a href="#" aria-expanded="false"><span class="hide-menu">Permessi feedback</span></a>   
                        	<%}else {%>
                            <a href="#" aria-expanded="false"><span class="hide-menu">Ui Elements</span></a>       
                            <%} %>                    
                        </li>
                        	<%if(segreteria==true){ %>
                        	<li>
                        	<a href="#" aria-expanded="false"><span class="hide-menu">Aggiungi utente</span></a> 
                        	 </li>
                        	<li>
                            <a href="#" aria-expanded="false"><span class="hide-menu">Rimuovi utente</span></a> 
                        	<% }%>
                        	 </li>
                        <li>
                            <a href="contattiDipartimento.jsp" aria-expanded="false"><span class="hide-menu">Contatti Dipartimento</span></a>                           
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