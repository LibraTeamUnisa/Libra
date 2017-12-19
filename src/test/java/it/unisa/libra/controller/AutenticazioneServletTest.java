package it.unisa.libra.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.junit.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import it.unisa.libra.model.dao.IUtenteDao;

public class AutenticazioneServletTest {

  @Mock
  private HttpServletRequest request;
  @Mock
  private HttpServletResponse response;
  private AutenticazioneServlet servlet = new AutenticazioneServlet();
  private IUtenteDao utenteDao;
  
  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
  }

  @After
  public void tearDown() throws Exception {
    request = null;
    response = null;
  }

  @Test
  public void testDoPostHttpServletRequestHttpServletResponse() {
    when(request.getParameter("email")).thenReturn("emailok");
    when(request.getParameter("password")).thenReturn("passwordok");
    //when(utente).thenReturn("");
    
  }

}
