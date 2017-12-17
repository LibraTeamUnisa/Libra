package it.unisa.libra.controller;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

import it.unisa.libra.bean.Utente;
import it.unisa.libra.model.dao.IUtenteDao;

public class RecuperoPasswordServletTest extends RecuperoPasswordServlet
{
	private Utente        utente;
	private EntityManager em;
	
	@Mock
	private HttpServletRequest  request;
	@Mock
	private HttpServletResponse response;
	
	private static final String WRONG_EMAIL_TEST   ="vitalemauro";
	private static final long   serialVersionUID   = 1L;
	
	@Before
	public void setUp() throws Exception 
	{
		MockitoAnnotations.initMocks(this);
		em=Persistence.createEntityManagerFactory("libraTestPU").createEntityManager();
		super.userDao=new IUtenteDaoTest(em);
		
		utente=new Utente();
		utente.setEmail("vitalemauro@outlook.it");
		utente.setPassword("maurovitale");
		utente.setIndirizzo("Via delle Rose 1");
		utente.setTelefono("1234567890");
		utente.setImgProfilo("profilo.jpg");
		
		em.getTransaction().begin();
		em.persist(utente);
		em.getTransaction().commit();
	}
	
	@After
	public void tearDown() throws Exception
	{
		em.getTransaction().begin();
		em.remove(utente);
		em.getTransaction().commit();
	}

	@Test
	public void successfulSending() 
	{
		try
		{
			when(request.getParameter("email")).thenReturn(utente.getEmail());
			PrintWriter writer=new PrintWriter(new StringWriter());
			when(response.getWriter()).thenReturn(writer);
			super.doGet(request, response);
			verify(request).getParameter("email");
			verify(response).setStatus(HttpServletResponse.SC_OK);
		}
		catch(Exception ex)
		{
			fail("Test fallito: "+ex.getMessage());
		}
	}
	
	@Test
	public void failedSending()
	{
		try
		{
			when(request.getParameter("email")).thenReturn(WRONG_EMAIL_TEST);
			PrintWriter writer=new PrintWriter(new StringWriter());
			when(response.getWriter()).thenReturn(writer);
			super.doGet(request, response);
			verify(request).getParameter("email");
			verify(response).setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}
		catch(Exception ex)
		{
			fail("Test fallito: "+ex.getMessage());
		}
	}
	
	private class IUtenteDaoTest implements IUtenteDao
	{
		private EntityManager em;
		
		public IUtenteDaoTest(EntityManager em)
		{
			this.em=em;
		}
		
		@Override
		public void persist(Utente entity) 
		{
		}

		@Override
		public void remove(Utente entity) 
		{
		}

		@Override
		public Utente findById(Utente entity, String id) 
		{
			return em.find(entity.getClass(), id);
		}

		@Override
		public List<Utente> findAll(Class<Utente> clazz) 
		{
			return null;
		}
		
	}
}
