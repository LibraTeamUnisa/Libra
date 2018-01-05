package it.unisa.libra.controller;

import static org.junit.Assert.fail;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import it.unisa.libra.bean.Gruppo;
import it.unisa.libra.bean.Utente;
import it.unisa.libra.model.dao.IUtenteDao;


public class RecuperoPasswordServletTest extends RecuperoPasswordServlet {
  
    private Utente utente; private Gruppo gruppo; private EntityManager em;
    
    @Mock 
    private HttpServletRequest request;
    
    @Mock 
    private HttpServletResponse response;
    
    private static final String RIGHT_EMAIL_TEST="maurovitale3c@virgilio.it";
    private static final String WRONG_EMAIL_TEST = "vitalemauro"; 
    private static final long serialVersionUID = 1L;
    
    @Before public void setUp() throws Exception 
    { 
      MockitoAnnotations.initMocks(this); 
      em =Persistence.createEntityManagerFactory("libraTestPU").createEntityManager(); 
      super.userDao =new IUtenteDaoTest(em);
      
      gruppo = new Gruppo(); 
      gruppo.setRuolo("Tester");
    
      utente = new Utente(); 
      utente.setEmail(RIGHT_EMAIL_TEST);
      utente.setPassword("maurovitale"); 
      utente.setIndirizzo("Via delle Rose 1");
      utente.setTelefono("1234567890"); 
      utente.setImgProfilo("profilo.jpg");
      utente.setGruppo(gruppo);
    
      em.getTransaction().begin();
      em.persist(gruppo); 
      em.persist(utente);
      em.getTransaction().commit(); 
      }

    @After public void tearDown() throws Exception
    { 
      em.getTransaction().begin();
      em.remove(utente); 
      em.remove(gruppo); 
      em.getTransaction().commit(); 
    }
    
    @Test public void successfulSending() 
    { 
      try {
        when(request.getParameter("email")).thenReturn(utente.getEmail()); 
        PrintWriter writer = new PrintWriter(new StringWriter()); 
        when(response.getWriter()).thenReturn(writer);
        super.doGet(request, response); 
        verify(request).getParameter("email");
        verify(response).setStatus(HttpServletResponse.SC_OK); 
        } catch (Exception ex) {
        fail("Test fallito: " + ex.getMessage()); 
        } 
    }
    
    @Test public void failedSending() 
    { 
      try {
        when(request.getParameter("email")).thenReturn(WRONG_EMAIL_TEST); 
        PrintWriter writer = new PrintWriter(new StringWriter()); 
        when(response.getWriter()).thenReturn(writer);
        super.doGet(request, response); verify(request).getParameter("email");
        verify(response).setStatus(HttpServletResponse.SC_BAD_REQUEST); 
        } catch (Exception ex) {
        fail("Test fallito: " + ex.getMessage()); 
        } 
    }
    
    private class IUtenteDaoTest implements IUtenteDao 
    {
      private EntityManager em;
    
      public IUtenteDaoTest(EntityManager em) { this.em = em; }
    
      @Override public void persist(Utente entity) {}
    
      @Override public Utente findById(Class<Utente> entityClass, String id) {return em.find(entityClass, id);}
    
      @Override public List<Utente> findAll(Class<Utente> entityClass) {return null; }
    
      @Override public void remove(Class<Utente> entityClass, String id) { }

      @Override public Utente getUtente(String mail, String pwd) {return null;}
    }  
}

