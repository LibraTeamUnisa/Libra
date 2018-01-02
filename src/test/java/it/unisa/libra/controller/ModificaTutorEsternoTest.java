package it.unisa.libra.controller;

import static org.junit.Assert.*;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
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
  
  private EntityManager     em;
  private EntityTransaction et;
  private TutorEsterno      tutor;
  private TutorEsternoPK    key;
  private Azienda           azienda;
  
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
    key.setAziendaEmail(azienda.getUtenteEmail());
    tutor=new TutorEsterno();
    tutor.setId(key);
    tutor.setNome("Giovanni");
    tutor.setCognome("Della Brenda");
    tutor.setDataDiNascita(CheckUtils.parseDate("12/05/1990"));
    tutor.setIndirizzo("Via de Gasperi 1");
    tutor.setTelefono("3337132234");
    
    et=em.getTransaction();
    et.begin();
    em.persist(azienda);
    em.persist(tutor);
    et.commit();
  }

  @After
  public void tearDown() throws Exception 
  {
    et.begin();
    em.remove(tutor);
    em.remove(azienda);
    et.commit();
  }

  @Test
  public void allParameters() 
  {
    try {
      
      initTest(tutor.getId().getAmbito(),tutor.getId().getAziendaEmail(),NEW_NAME,NEW_SURNAME,NEW_DATE,NEW_TELEPHONE,NEW_ADDRESS,NEW_SCOPE);
      
      super.doPost(request, response);
      verify(response).setStatus(HttpServletResponse.SC_OK);
      TutorEsterno result=em.find(TutorEsterno.class,key);
      
      assertEquals(result.getId().getAmbito(),NEW_SCOPE);
      assertEquals(result.getNome(),NEW_NAME);
      assertEquals(result.getCognome(),NEW_SURNAME);
      assertEquals(result.getDataDiNascita(),CheckUtils.parseDate(NEW_DATE));
      assertEquals(result.getTelefono(),NEW_TELEPHONE);
      assertEquals(result.getIndirizzo(),NEW_ADDRESS);
    } catch(Exception ex) {
      fail("Test fallito: "+ex.getMessage());
      ex.printStackTrace();
    }
  }
  
  @Test
  public void zeroParameters()
  {
    try {
      initTest(tutor.getId().getAmbito(), tutor.getId().getAziendaEmail(), null, null, null, null, null, null);
      super.doPost(request, response);
      verify(response).setStatus(HttpServletResponse.SC_BAD_REQUEST);
      TutorEsterno result=em.find(TutorEsterno.class,key);
      assertEquals(tutor.getNome(),result.getNome());
      assertEquals(tutor.getCognome(),result.getCognome());
      assertEquals(tutor.getIndirizzo(),result.getIndirizzo());
      assertEquals(tutor.getTelefono(),result.getTelefono());
      assertEquals(tutor.getId().getAmbito(),result.getId().getAmbito());
    } catch(Exception ex) {
      fail("Test fallito: "+ex.getMessage());
      ex.printStackTrace();
    }
  }
  
  @Test
  public void oneParameter()
  {
    try {
      initTest(tutor.getId().getAmbito(), tutor.getId().getAziendaEmail(), NEW_NAME, null, null, null, null, null);
      super.doPost(request, response);
      verify(response).setStatus(HttpServletResponse.SC_OK);
      TutorEsterno result=em.find(TutorEsterno.class,key);
      assertEquals(NEW_NAME,result.getNome());
      assertEquals(tutor.getCognome(),result.getCognome());
      assertEquals(tutor.getIndirizzo(),result.getIndirizzo());
      assertEquals(tutor.getTelefono(),result.getTelefono());
      assertEquals(tutor.getId().getAmbito(),result.getId().getAmbito());
    } catch(Exception ex) {
      fail("Test fallito: "+ex.getMessage());
      ex.printStackTrace();
    }
  }
  
  @Test
  public void twoParameter()
  {
    try {
      initTest(tutor.getId().getAmbito(), tutor.getId().getAziendaEmail(), null, null, null, NEW_TELEPHONE, NEW_ADDRESS, null);
      super.doPost(request, response);
      verify(response).setStatus(HttpServletResponse.SC_OK);
      TutorEsterno result=em.find(TutorEsterno.class,key);
      assertEquals(tutor.getNome(),result.getNome());
      assertEquals(tutor.getCognome(),result.getCognome());
      assertEquals(NEW_ADDRESS,result.getIndirizzo());
      assertEquals(NEW_TELEPHONE,result.getTelefono());
      assertEquals(tutor.getId().getAmbito(),result.getId().getAmbito());
    } catch(Exception ex) {
      fail("Test fallito: "+ex.getMessage());
      ex.printStackTrace();
    }
  }
  
  @Test
  public void threeParameter()
  {
    try {
      initTest(tutor.getId().getAmbito(), tutor.getId().getAziendaEmail(), null, null, null, NEW_TELEPHONE, NEW_ADDRESS, NEW_SCOPE);
      super.doPost(request, response);
      verify(response).setStatus(HttpServletResponse.SC_OK);
      TutorEsterno result=em.find(TutorEsterno.class,key);
      assertEquals(tutor.getNome(),result.getNome());
      assertEquals(tutor.getCognome(),result.getCognome());
      assertEquals(NEW_ADDRESS,result.getIndirizzo());
      assertEquals(NEW_TELEPHONE,result.getTelefono());
      assertEquals(NEW_SCOPE,result.getId().getAmbito());
    } catch(Exception ex) {
      fail("Test fallito: "+ex.getMessage());
      ex.printStackTrace();
    }
  }
  
  @Test
  public void fourParameter()
  {
    try {
      initTest(tutor.getId().getAmbito(), tutor.getId().getAziendaEmail(), null, null, NEW_DATE, NEW_TELEPHONE, NEW_ADDRESS, NEW_SCOPE);
      super.doPost(request, response);
      verify(response).setStatus(HttpServletResponse.SC_OK);
      TutorEsterno result=em.find(TutorEsterno.class,key);
      assertEquals(tutor.getNome(),result.getNome());
      assertEquals(CheckUtils.parseDate(NEW_DATE),result.getDataDiNascita());
      assertEquals(NEW_ADDRESS,result.getIndirizzo());
      assertEquals(NEW_TELEPHONE,result.getTelefono());
      assertEquals(NEW_SCOPE,result.getId().getAmbito());
    } catch(Exception ex) {
      fail("Test fallito: "+ex.getMessage());
      ex.printStackTrace();
    }
  }
  
  @Test
  public void fiveParameter()
  {
    try {
      initTest(tutor.getId().getAmbito(), tutor.getId().getAziendaEmail(), null, NEW_SURNAME, NEW_DATE, NEW_TELEPHONE, NEW_ADDRESS, NEW_SCOPE);
      super.doPost(request, response);
      verify(response).setStatus(HttpServletResponse.SC_OK);
      TutorEsterno result=em.find(TutorEsterno.class,key);
      assertEquals(tutor.getNome(),result.getNome());
      assertEquals(NEW_SURNAME,result.getCognome());
      assertEquals(CheckUtils.parseDate(NEW_DATE),result.getDataDiNascita());
      assertEquals(NEW_ADDRESS,result.getIndirizzo());
      assertEquals(NEW_TELEPHONE,result.getTelefono());
      assertEquals(NEW_SCOPE,result.getId().getAmbito());
    } catch(Exception ex) {
      fail("Test fallito: "+ex.getMessage());
      ex.printStackTrace();
    }
  }
  
  @Test
  public void withouthIdentifier()
  {
    try {
      initTest(null, null, null, null, null, null, null, null);
      super.doPost(request, response);
      verify(response).setStatus(HttpServletResponse.SC_BAD_REQUEST);
    } catch(Exception ex) {
      fail("Test fallito: "+ex.getMessage());
      ex.printStackTrace();
    }
  }
  
  @Test
  public void scopeAlreadyOccupied()
  {
    try {
      
      TutorEsternoPK ntKey=new TutorEsternoPK();
      ntKey.setAmbito(NEW_SCOPE);
      ntKey.setAziendaEmail(azienda.getUtenteEmail());
      TutorEsterno newTutor=new TutorEsterno();
      newTutor.setId(ntKey);
      newTutor.setNome("Antonio");
      newTutor.setCognome("Cirillo");
      newTutor.setIndirizzo("Via Trebisonda 2");
      newTutor.setTelefono("3338998789");
      newTutor.setDataDiNascita(CheckUtils.parseDate("13/08/1978"));
      
      et.begin();
      em.persist(newTutor);
      et.commit();

      initTest(tutor.getId().getAmbito(), tutor.getId().getAziendaEmail(), null, null, null, NEW_TELEPHONE, null,NEW_SCOPE);
      super.doPost(request, response);
      verify(response).setStatus(HttpServletResponse.SC_FORBIDDEN);
      
      et.begin();
      em.remove(newTutor);
      et.commit();
      
    } catch(Exception ex) {
      fail("Test fallito: "+ex.getMessage());
      ex.printStackTrace();
    }
  }
  
  private void initTest(String idTutor,String idAzienda,String nome,String cognome,String data,String telefono,String indirizzo,String ambito) throws Exception
  {
    when(request.getParameter("action")).thenReturn(Actions.MODIFICA_TUTOR_ESTERNO);
    when(request.getParameter("idTutor")).thenReturn(idTutor);
    when(request.getParameter("idAzienda")).thenReturn(idAzienda);
    when(request.getParameter("nome")).thenReturn(nome);
    when(request.getParameter("cognome")).thenReturn(cognome);
    when(request.getParameter("telefono")).thenReturn(telefono);
    when(request.getParameter("dataDiNascita")).thenReturn(data);
    when(request.getParameter("indirizzo")).thenReturn(indirizzo);
    when(request.getParameter("ambito")).thenReturn(ambito);
    when(response.getWriter()).thenReturn(new PrintWriter(new StringWriter()));
  }
  
  private class ITutorEsternoDaoTest implements ITutorEsternoDao
  {
    private EntityManager em;
    
    public ITutorEsternoDaoTest(EntityManager em) {
      this.em=em;
    }

    @Override
    public void persist(TutorEsterno entity) {
      // TODO Auto-generated method stub
      
    }

    @Override
    public void remove(Class<TutorEsterno> entityClass, TutorEsternoPK id) {
      // TODO Auto-generated method stub
      
    }

    @Override
    public TutorEsterno findById(Class<TutorEsterno> entityClass, TutorEsternoPK id) {
      return em.find(entityClass,id);
    }

    @Override
    public List<TutorEsterno> findAll(Class<TutorEsterno> entityClass) {
      // TODO Auto-generated method stub
      return null;
    }

    @Override
    public List<TutorEsterno> findByEmailAzienda(String emailAzienda) {
      // TODO Auto-generated method stub
      return null;
    }

    @Override
    public List<TutorEsterno> findByAziendaNome(String nome) {
      // TODO Auto-generated method stub
      return null;
    }
  }

}
