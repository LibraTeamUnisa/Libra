package it.unisa.libra.controller;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import it.unisa.libra.bean.ProgettoFormativo;
import it.unisa.libra.model.dao.IProgettoFormativoDao;
import it.unisa.libra.util.Actions;
import it.unisa.libra.util.CheckUtils;


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

  @Test
  public void successActionTopAziendeWithNullReturnTest() throws IOException, ServletException {
    when(request.getParameter(Actions.ACTION)).thenReturn(Actions.PF_TOP_AZIENDE);
    when(response.getWriter()).thenReturn(pw);
    when(request.getParameter("pastDays")).thenReturn("2010-10-10");
    when(request.getParameter("limit")).thenReturn("10");
    when(request.getParameter("status")).thenReturn("");
    when(pfDao.getTopAziendeFromNumStudenti("2010-10-10", "10", ""))
        .thenReturn(createTopAziendeMap());

    servlet.doGet(request, response);

    verify(pw).write("null");
  }

  @Test
  public void successActionCountStudentiFromAziendeWithNullReturnTest()
      throws IOException, ServletException {
    when(request.getParameter(Actions.ACTION)).thenReturn(Actions.PF_COUNT_BY_AZIENDA);
    when(response.getWriter()).thenReturn(pw);
    when(request.getParameter("pastDays")).thenReturn("2010-10-10");
    when(request.getParameter("limit")).thenReturn("10");
    when(request.getParameter("status")).thenReturn("");
    when(pfDao.countByAziendaAndDate(null, null, "2010-10-10", "10", ""))
        .thenReturn(createListOfTopAziendeMap());

    servlet.doGet(request, response);

    verify(pw).write("[]");
  }

  @Test
  public void successActionTabellaValutazioniWithNullReturnTest()
      throws IOException, ServletException {
    when(request.getParameter(Actions.ACTION)).thenReturn(Actions.PF_TABELLA_VALUTAZIONI);
    when(response.getWriter()).thenReturn(pw);
    when(request.getParameter("fromDate")).thenReturn("2010-10-10");
    when(request.getParameter("toDate")).thenReturn("2018-10-10");
    when(request.getParameter("limit")).thenReturn("10");
    when(request.getParameter("status")).thenReturn("");

    Date fromDate = CheckUtils.parseDateWithPattern("2010-10-10", "yyyy-MM-dd");
    Date toDate = CheckUtils.parseDateWithPattern("2018-10-10", "yyyy-MM-dd");

    when(pfDao.getTabellaValutazioni(fromDate, toDate, "", "RAGSOC"))
        .thenReturn(createListOfTopAziendeMap());

    servlet.doGet(request, response);

    verify(pw).write("[]");
  }

  @Test
  public void successActionNumTirociniCompletatiTest() throws IOException, ServletException {
    when(request.getParameter(Actions.ACTION)).thenReturn(Actions.PF_NUM_TIROCINI_COMPLETATI);
    when(response.getWriter()).thenReturn(pw);

    when(pfDao.getNumTirociniCompletati()).thenReturn(new Long("10"));

    servlet.doGet(request, response);

    verify(pw).write("10");
  }

  @Test
  public void failDoGet() throws Exception {

    when(request.getParameter(Actions.ACTION)).thenReturn(null);
    when(response.getWriter()).thenReturn(pw);

    servlet.doGet(request, response);

    verify(response).setStatus(HttpServletResponse.SC_BAD_REQUEST);
  }

  @Test
  public void failDoGetInvalidAction() throws Exception {

    when(request.getParameter(Actions.ACTION)).thenReturn("noAction");
    when(response.getWriter()).thenReturn(pw);

    servlet.doGet(request, response);

    verify(response).setStatus(HttpServletResponse.SC_BAD_REQUEST);
  }

  private List<Map<String, String>> createListOfTopAziendeMap() {
    // TODO Auto-generated method stub
    return null;
  }

  private Map<String, String> createTopAziendeMap() {
    return null;
  }

}
