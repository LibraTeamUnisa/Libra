package it.unisa.libra.controller;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import it.unisa.libra.bean.Azienda;
import it.unisa.libra.bean.Gruppo;
import it.unisa.libra.bean.ProgettoFormativo;
import it.unisa.libra.bean.Studente;
import it.unisa.libra.bean.TutorInterno;
import it.unisa.libra.bean.Utente;
import it.unisa.libra.model.dao.IGenericDao;
import it.unisa.libra.util.CheckUtils;

public class CambiaPeriodoReportServletTest extends CambiaPeriodoReportServlet 
{
  private static final long serialVersionUID = 1L;
  
  private EntityManager em;
  
  private GenericDaoTest<TutorInterno,String> tTutorDao;
  private GenericDaoTest<ProgettoFormativo,Integer> tPfDao;
  
  private List<Utente>      utenti=new ArrayList<Utente>();
  private TutorInterno      tutor;
  private Studente          studente;
  private Azienda           azienda;
  private List<Gruppo>      gruppi=new ArrayList<Gruppo>();
  private ProgettoFormativo pf;
  
  @Before
  public void setUp() throws Exception 
  {
    em=Persistence.createEntityManagerFactory("libraTestPU").createEntityManager();
    tTutorDao=new GenericDaoTest<TutorInterno,String>(em);
    tPfDao=new GenericDaoTest<ProgettoFormativo,Integer>(em);
    
    initGruppi();
    initUtenti();
    initAzienda();
    initTutor();
    initStudente();
    initProgettoFormativo();
    saveAll();
  }

  @After
  public void tearDown() throws Exception 
  {
    removeAll();
  }

  @Test
  public void test() 
  {
    
  }
  
  private void initGruppi()
  {
    String[] list= {"Azienda","Tutor Interno","Studente"};
    
    for(int i=0;i<3;i++)
    {
      gruppi.add(new Gruppo());
      gruppi.get(i).setRuolo(list[i]);
    }
  }
  
  private void initUtenti()
  {
    String[] list= {"azienda@email.it","tutor@email.it","studente@email.it"};
    for(int i=0;i<3;i++)
    {
      utenti.add(new Utente());
      utenti.get(i).setEmail(list[i]);
      utenti.get(i).setGruppo(gruppi.get(i));
      utenti.get(i).setPassword("pass");
      utenti.get(i).setIndirizzo("address");
      utenti.get(i).setTelefono("1234567890");
      utenti.get(i).setImgProfilo("img.jpg");
    }
  }
  
  private void initAzienda()
  {
    azienda=new Azienda();
    azienda.setUtente(utenti.get(0));
    azienda.setUtenteEmail(utenti.get(0).getEmail());
    azienda.setNome("Azienda");
    azienda.setPartitaIVA("1234567890123");
    azienda.setSede("Roma");
  }
  
  private void initTutor()
  {
    tutor=new TutorInterno();
    tutor.setUtente(utenti.get(1));
    tutor.setUtenteEmail(utenti.get(1).getEmail());
    tutor.setCognome("Vitolo");
    tutor.setNome("Anna");
    tutor.setDataDiNascita(CheckUtils.checkDate("01/02/1980"));
    tutor.setLinkSito("www.tutor.it");
  }
  
  private void initStudente()
  {
    studente=new Studente();
    studente.setUtente(utenti.get(2));
    studente.setUtenteEmail(utenti.get(2).getEmail());
    studente.setMatricola("0512103728");
    studente.setCognome("Rosato");
    studente.setNome("Marco");
    studente.setDataDiNascita(CheckUtils.checkDate("12/04/1996"));
  }
  
  private void initProgettoFormativo()
  {
    pf=new ProgettoFormativo();
    pf.setId(100);
    pf.setStato(5);
    pf.setDataInizio(CheckUtils.checkDate("01/02/2018"));
    pf.setDataFine(CheckUtils.checkDate("01/04/2018"));
    pf.setAmbito("Cloud Computing");
    pf.setDocumento("doc.pdf");
    pf.setPeriodoReport(10);
    pf.setStudente(studente);
    pf.setAzienda(azienda);
    pf.setTutorInterno(tutor);
    studente.addProgettiFormativi(pf);
  }
  
  private void saveAll()
  {
    EntityTransaction et=em.getTransaction();
    et.begin();
    for(Gruppo g:gruppi)
        em.persist(g);
    for(Utente u:utenti)
        em.persist(u);
    em.persist(azienda);
    em.persist(tutor);
    em.persist(studente);
    em.persist(pf);
    et.commit();
  }
  
  private void removeAll()
  {
    EntityTransaction et=em.getTransaction();
    et.begin();
    em.remove(em.merge(pf));
    em.remove(em.merge(studente));
    em.remove(em.merge(tutor));
    em.remove(em.merge(azienda));
    for(Utente u:utenti)
      em.remove(em.merge(u));
    for(Gruppo g:gruppi)
      em.remove(em.merge(g));
    et.commit();
  }
  
  private class GenericDaoTest<E,K> implements IGenericDao<E,K>
  {
    private EntityManager em;
    
    public GenericDaoTest(EntityManager em)
    {
      this.em=em;
    }

    @Override
    public void persist(E entity) 
    {
      em.getTransaction().begin();
      em.persist(entity);
      em.getTransaction().commit();
    }

    @Override
    public void remove(Class<E> entityClass, K id) 
    {
    }

    @Override
    public E findById(Class<E> entityClass, K id) 
    {
      return (E) em.find(entityClass,id);
    }

    @Override
    public List<E> findAll(Class<E> entityClass) 
    {
      return null;
    }
  }
}
