package it.unisa.libra.controller;

import static org.mockito.Mockito.RETURNS_DEEP_STUBS;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import it.unisa.libra.bean.Azienda;
import it.unisa.libra.bean.Tutoresterno;
import it.unisa.libra.bean.TutoresternoPK;
import it.unisa.libra.model.dao.IAziendaDao;
import it.unisa.libra.model.dao.ITutorEsternoDao;

public class GestioneTutorEsternoServletTest {

	private GestioneTutorEsternoServlet servlet = new GestioneTutorEsternoServlet();
	private HttpServletRequest request;
	private HttpServletResponse response;
	private IAziendaDao aziendaDao;
	private ITutorEsternoDao tutorDao;
	private RequestDispatcher dispatcher;
	
	@Before
	public void setUp() {
		request = mock(HttpServletRequest.class, RETURNS_DEEP_STUBS);
		response = mock(HttpServletResponse.class);
		aziendaDao = mock(IAziendaDao.class);
		tutorDao = mock(ITutorEsternoDao.class);
	}

	@After
	public void tearDown() {
		request = null;
		response = null;
		aziendaDao = null;
		tutorDao = null;
	}

	@Test
	public void actionNullTest() throws Exception {
		when(request.getParameter("action")).thenReturn(null);
		servlet.doPost(request, response);
		verify(response).sendError(400);
	}

	@Test
	public void addSuccessTest() throws Exception {
		dispatcher = mock(RequestDispatcher.class);
		setUpCorrectly();
		setUpMocksCorrectly();
		servlet.setAziendaDao(aziendaDao);
		servlet.setTutorDao(tutorDao);
		servlet.doPost(request, response);
		verify(request).setAttribute("message", "L'aggiunta del tutor è avvenuta con successo.");
		verify(request).getRequestDispatcher("dashboardAzienda.jsp");
		verify(dispatcher).forward(request, response);
	}

	@Test
	public void aziendaNullTest() throws Exception {
		when(request.getParameter("action")).thenReturn("aggiungi");
		when(request.getSession().getAttribute("email")).thenReturn(EMAIL_AZIENDA);
		when(aziendaDao.findById(Azienda.class, EMAIL_AZIENDA)).thenReturn(null);
		servlet.setAziendaDao(aziendaDao);
		servlet.doPost(request, response);
		verify(response).sendError(422);
	}

	@Test
	public void tutorAlreadyInTest() throws Exception {
		setUpCorrectly();
		when(request.getParameter("action")).thenReturn("aggiungi");
		when(request.getSession().getAttribute("email")).thenReturn(EMAIL_AZIENDA);
		when(aziendaDao.findById(Azienda.class, EMAIL_AZIENDA)).thenReturn(azienda);
		when(request.getParameter("ambito")).thenReturn(AMBITO);
		when(tutorDao.findById(Tutoresterno.class, id)).thenReturn(new Tutoresterno());
		servlet.setAziendaDao(aziendaDao);
		servlet.setTutorDao(tutorDao);
		servlet.doPost(request, response);
		verify(response).sendError(400,
				"Non è stato possibile aggiungere il tutor. Esiste già un tutor responsabile dell'ambito " + AMBITO);
	}

	private void setUpCorrectly() {
		azienda = new Azienda();
		azienda.setNome("nome");
		azienda.setPartitaIVA("partitaIVA");
		azienda.setSede("sede");
		azienda.setUtenteEmail(EMAIL_AZIENDA);
		id = new TutoresternoPK();
		id.setAziendaEmail(EMAIL_AZIENDA);
		id.setAmbito(AMBITO);
	}

	private void setUpMocksCorrectly() {
		when(request.getParameter("action")).thenReturn("aggiungi");
		when(request.getSession().getAttribute("email")).thenReturn(EMAIL_AZIENDA);
		when(aziendaDao.findById(Azienda.class, EMAIL_AZIENDA)).thenReturn(azienda);
		when(request.getParameter("ambito")).thenReturn(AMBITO);
		when(tutorDao.findById(Tutoresterno.class, id)).thenReturn(null);
		when(request.getParameter("nome")).thenReturn("nome");
		when(request.getParameter("cognome")).thenReturn("cognome");
		when(request.getParameter("dataDiNascita")).thenReturn("2017-12-16 22:16:00");
		when(request.getParameter("indirizzo")).thenReturn("indirizzo");
		when(request.getParameter("telefono")).thenReturn("telefono");
		when(request.getRequestDispatcher("dashboardAzienda.jsp")).thenReturn(dispatcher);

	}

	private TutoresternoPK id;
	private Azienda azienda;
	private static final String AMBITO = "ambito1";
	private static final String EMAIL_AZIENDA = "azienda1@example.com";
}
