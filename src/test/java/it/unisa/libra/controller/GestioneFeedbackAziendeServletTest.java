package it.unisa.libra.controller;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.Iterator;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.junit.*;
import org.mockito.*;
import it.unisa.libra.bean.Domanda;
import it.unisa.libra.bean.Feedback;
import it.unisa.libra.bean.ProgettoFormativo;
import it.unisa.libra.model.dao.IDomandaDao;
import it.unisa.libra.model.dao.IFeedbackDao;
import it.unisa.libra.model.dao.IProgettoFormativoDao;


public class GestioneFeedbackAziendeServletTest {
  @Mock
  private HttpServletRequest request;
  @Mock
  private HttpServletResponse response;
  @Mock
  private ServletContext context;
  @Mock
  private RequestDispatcher dispatcher;
  private GestioneFeedbackAziendaServlet servlet = new GestioneFeedbackAziendaServlet();
  @Mock
  private IDomandaDao domandaDao;
  @Mock
  private IFeedbackDao feedDao;
  @Mock
  private IProgettoFormativoDao pfDao;
  @Mock
  private Domanda domanda;
  @Mock
  private Feedback feedback;
  @Mock
  private ProgettoFormativo pf;
  @Mock
  private Iterator<Domanda> mockIterator;
  @Mock
  List<Domanda> domande;

  @SuppressWarnings("unchecked")
  @Before
  public void setUp() throws Exception {
    request = mock(HttpServletRequest.class);
    response = mock(HttpServletResponse.class);
    context = mock(ServletContext.class);
    dispatcher = mock(RequestDispatcher.class);
    domandaDao = mock(IDomandaDao.class);
    feedDao = mock(IFeedbackDao.class);
    pfDao = mock(IProgettoFormativoDao.class);
    domanda = mock(Domanda.class);
    feedback = mock(Feedback.class);
    pf = mock(ProgettoFormativo.class);
    servlet.setFeedbackDao(feedDao);
    servlet.setProgettoFormativoDao(pfDao);
    servlet.setDomandaDao(domandaDao);
    mockIterator = mock(Iterator.class);
    domande = mock(List.class);
  }

  @After
  public void tearDown() throws Exception {
    request = null;
    response = null;
    dispatcher = null;
    context = null;
  }


  @Test
  public void doPostOnSuccessTest() throws Exception {
    String idPF = "1";
    when(domandaDao.findByType("Studente")).thenReturn(domande);
    when(request.getParameter("idProgettoFormativo")).thenReturn(idPF);
    when(domande.iterator()).thenReturn(mockIterator);
    when(mockIterator.hasNext()).thenReturn(true, false);
    when(mockIterator.next()).thenReturn(domanda);
    int iterations = 0;
    for (Domanda d : domande) {
      iterations++;
    }
    assertEquals(1, iterations);
    when(request.getServletContext()).thenReturn(context);
    when(context.getRequestDispatcher("/questionarioValutaAzienda.jsp")).thenReturn(dispatcher);
    servlet.doPost(request, response);
    verify(dispatcher).forward(request, response);

  }



  @Test
  public void persistNoteFeedbackTest() {
    String idPF = "1";
    String testoNote = "testoProva";
    when(domanda.getId()).thenReturn(25);
    when(domanda.getTesto()).thenReturn("Note");
    when(pfDao.findById(ProgettoFormativo.class, Integer.parseInt(idPF))).thenReturn(pf);
    when(request.getParameter("idProgettoFormativo")).thenReturn(idPF);
    when(request.getParameter("note")).thenReturn(testoNote);

    servlet.persistFeedback(request, domanda, Integer.parseInt(idPF));
  }

  @Test
  public void persistValutazioneFeedbackTest() {
    String idPF = "1";
    when(domanda.getId()).thenReturn(15);
    when(domanda.getTesto()).thenReturn("3");
    when(pfDao.findById(ProgettoFormativo.class, Integer.parseInt(idPF))).thenReturn(pf);
    when(request.getParameter("idProgettoFormativo")).thenReturn(idPF);
    when(request.getParameter("15")).thenReturn("4");

    servlet.persistFeedback(request, domanda, Integer.parseInt(idPF));
  }



}
