package it.unisa.libra.controller;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import it.unisa.libra.bean.ProgettoFormativo;
import it.unisa.libra.model.dao.IProgettoFormativoDao;
import it.unisa.libra.util.Actions;

import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


public class GestionePfServletTest {

  @Mock
  private HttpServletRequest request;
  @Mock
  private HttpServletResponse response;
  @Mock
  private HttpSession session;
  @Mock
  private IProgettoFormativoDao pfDao;
  @InjectMocks
  private GestionePfServlet servlet;
  @Mock
  private RequestDispatcher dispatcher;
  @Mock
  private ServletContext context;
  @Mock
  private ServletConfig config;
  @Mock
  private ProgettoFormativo pfTest;
  @Mock
  private PrintWriter pw;

  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
    servlet.init(config);
  }

  @After
  public void tearDown() throws Exception {}

  @Test
  public void modificaStatoTest() throws Exception {
    int idTest = 25;
    int nuovoStato = 4;
    ProgettoFormativo pf = createPfObject();
    when(request.getParameter(Actions.ACTION)).thenReturn("modificaStato");
    when(request.getParameter("id")).thenReturn(Integer.toString(idTest));
    when(pfDao.findById(ProgettoFormativo.class, idTest)).thenReturn(pf);
    when(request.getParameter("stato")).thenReturn(Integer.toString(nuovoStato));

    when(response.getWriter()).thenReturn(pw);

    servlet.doPost(request, response);

    verify(pf).setStato(nuovoStato);
    verify(pfDao).persist(pf);
    verify(pw).write("true");
  }

  @Test
  public void failModificaStatoTest() throws Exception {

    when(request.getParameter(Actions.ACTION)).thenReturn("noAction");
    when(response.getWriter()).thenReturn(pw);

    servlet.doPost(request, response);

    verify(response).setStatus(HttpServletResponse.SC_BAD_REQUEST);
  }

  private ProgettoFormativo createPfObject() {
    pfTest.setId(25);
    pfTest.setStato(1);
    return pfTest;
  }

}
