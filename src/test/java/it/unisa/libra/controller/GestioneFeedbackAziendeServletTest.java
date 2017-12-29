package it.unisa.libra.controller;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.SwingWorker;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import it.unisa.libra.bean.Domanda;
import it.unisa.libra.bean.ProgettoFormativo;
import it.unisa.libra.model.dao.IDomandaDao;
import it.unisa.libra.model.dao.IFeedbackDao;
import it.unisa.libra.model.dao.IProgettoFormativoDao;

public class GestioneFeedbackAziendeServletTest {

	@Mock
	private IDomandaDao domandaDao;
	@Mock
	private IFeedbackDao feedbackDao;
	@Mock
	private IProgettoFormativoDao progettoformativoDao;
	@Mock
	private ProgettoFormativo pf;
	GestioneFeedbackAziendaServlet servlet = new GestioneFeedbackAziendaServlet();
	@Mock
	private HttpSession session;
	@Mock
	HttpServletRequest request;
	@Mock
	HttpServletResponse response;
	@Mock
	RequestDispatcher dispatcher;
	@Mock
	ServletContext servletContex;
	@Mock
	List domande;
	@Mock
	Domanda domanda;
	
	@Before
	public void setUp() throws Exception {
		servlet.init();
	    session = mock(HttpSession.class);
	    request = mock(HttpServletRequest.class);
	    response = mock(HttpServletResponse.class);
	    domandaDao = mock(IDomandaDao.class);
	    feedbackDao = mock(IFeedbackDao.class);
	    progettoformativoDao= mock(IProgettoFormativoDao.class);
	    domande = mock(ArrayList.class);
	    dispatcher= mock(RequestDispatcher.class);
	    servletContex= mock(ServletContext.class);
	    domanda= mock(Domanda.class);
	}

	@After
	public void tearDown() throws Exception {
		 request = null;
		 response = null;
		 session = null;
		 domandaDao = null;
		 feedbackDao = null;
		 progettoformativoDao= null;
	}

	@Test
	public void verificaFeedbackPersistedTest() throws Exception {

		String idPF="1";
		domanda.setTipo("Note");
		domanda.setId(25);
		domande.add(domanda);
		
		when(domandaDao.findByType("Studenti")).thenReturn(new ArrayList<Domanda>());
		
		when(request.getParameter("idProgettoFormativo")).thenReturn(idPF);
		
		when(domande.get(0)).thenReturn(new Domanda());
		
		when(request.getParameter(""+domanda.getId())).thenReturn("14");
		
		when(progettoformativoDao.findById(ProgettoFormativo.class, 1)).thenReturn(new ProgettoFormativo());
		
		when(request.getServletContext()).thenReturn(servletContex);
		
		when(servletContex.getRequestDispatcher("/questionarioValutaAzienda.jsp")).thenReturn(dispatcher);
		
		servlet.setDomandaDao(domandaDao);
		servlet.setFeedbackDao(feedbackDao);
		servlet.setProgettoFormativoDao(progettoformativoDao);
		
		servlet.persistFeedback("1", domanda.getId(), Integer.parseInt(idPF));
		
		servlet.doPost(request, response);
		
		verify(dispatcher).forward(request, response);
		
	}
	
	

}
