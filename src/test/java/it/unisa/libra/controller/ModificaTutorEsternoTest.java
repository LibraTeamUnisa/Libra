package it.unisa.libra.controller;

import static org.junit.Assert.*;

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
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

import it.unisa.libra.bean.Azienda;
import it.unisa.libra.bean.TutorEsterno;
import it.unisa.libra.bean.TutorEsternoPK;
import it.unisa.libra.model.dao.ITutorEsternoDao;
import it.unisa.libra.util.Actions;
import it.unisa.libra.util.CheckUtils;

public class ModificaTutorEsternoTest extends GestioneTutorEsternoServlet
{
  private static final long   serialVersionUID   = 1L;
  
  private EntityManager  em;
  private TutorEsterno   tutor;
  private TutorEsternoPK key;
  private Azienda        azienda;
  
  @Mock
  private HttpServletRequest request;
  @Mock
  private HttpServletResponse response;
  
  private static final String NEW_NAME="Pinco";
  private static final String NEW_SURNAME="Pallino";
  private static final String NEW_SCOPE="Reti";
  private static final String NEW_DATE="11/05/1989";
  private static final String NEW_ADDRESS="Via delle rose 1";
  private static final String NEW_TELEPHONE="123456789";
  
  @Before
  public void setUp() throws Exception 
  {
    MockitoAnnotations.initMocks(this);
    em=Persistence.createEntityManagerFactory("libraTestPU").createEntityManager();
    super.setTutorDao(new ITutorEsternoDaoTest(em));
    
    azienda=new Azienda();
    azienda.setUtenteEmail("azienda@yahoo.it");
    azienda.setNome("Azienda");
    azienda.setPartitaIVA("12345678");
    azienda.setSede("Roma");
    
    key=new TutorEsternoPK();
    key.setAmbito("Intelligenza Artificiale");
    key.setAziendaEmail("azienda@yahoo.it");
    tutor=new TutorEsterno();
    tutor.setId(key);
    tutor.setNome("Giovanni");
    tutor.setCognome("Della Brenda");
    tutor.setDataDiNascita(CheckUtils.checkDate("12/05/1990"));
    tutor.setIndirizzo("Via de Gasperi 1");
    tutor.setTelefono("3337132234");
    em.getTransaction().begin();
    em.persist(azienda);
    em.persist(tutor);
    em.getTransaction().commit();
  }

  @After
  public void tearDown() throws Exception 
  {
    em.getTransaction().begin();
    em.remove(tutor);
    em.remove(azienda);
    em.getTransaction().commit();
  }

  @Test
  public void test() 
  {
    try {
      when(request.getParameter("action")).thenReturn(Actions.UPDATE);
      when(request.getParameter("idTutor")).thenReturn(tutor.getId().getAmbito());
      when(request.getParameter("idAzienda")).thenReturn(tutor.getId().getAziendaEmail());
      when(request.getParameter("nome")).thenReturn(NEW_NAME);
      when(request.getParameter("cognome")).thenReturn(NEW_SURNAME);
      when(request.getParameter("telefono")).thenReturn(NEW_TELEPHONE);
      when(request.getParameter("dataDiNascita")).thenReturn(NEW_DATE);
      when(request.getParameter("indirizzo")).thenReturn(NEW_ADDRESS);
      when(request.getParameter("ambito")).thenReturn(NEW_SCOPE);
      when(response.getWriter()).thenReturn(new PrintWriter(new StringWriter()));
      super.doPost(request, response);
      verify(response).setStatus(200);
      TutorEsterno result=em.find(TutorEsterno.class,key);
      System.out.println(result.getNome());
      System.out.println(result.getCognome());
      System.out.println(result.getTelefono());
      System.out.println(result.getIndirizzo());
    } catch(Exception ex) {
      fail("Test fallito: "+ex.getMessage());
      ex.printStackTrace();
    }
  }
  
  private class ITutorEsternoDaoTest implements ITutorEsternoDao
  {
    private EntityManager em;
    
    public ITutorEsternoDaoTest(EntityManager em) 
    {
      this.em=em;
    }
    
    @Override
    public void persist(TutorEsterno entity) 
    {
      // TODO Auto-generated method stub
      
    }

    @Override
    public void remove(TutorEsterno entity) 
    {
      // TODO Auto-generated method stub
    }

    @Override
    public TutorEsterno findById(Class<TutorEsterno> entityClass, TutorEsternoPK id) 
    {
      return em.find(entityClass,id);
    }

    @Override
    public List<TutorEsterno> findAll(Class<TutorEsterno> entityClass) {
      // TODO Auto-generated method stub
      return null;
    }
    
  }

}
