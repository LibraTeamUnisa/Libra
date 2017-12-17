package it.unisa.libra.controller;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({AggiungiTutorEsternoTest.class})
public class GestioneTutorEsternoServletTest {

  private GestioneTutorEsternoServlet servlet = new GestioneTutorEsternoServlet();
  private HttpServletRequest request;
  private HttpServletResponse response;

  @Before
  public void setUp() {
    request = mock(HttpServletRequest.class);
    response = mock(HttpServletResponse.class);
  }

  @Test
  public void actionNullTest() throws Exception {
    when(request.getParameter("action")).thenReturn(null);
    servlet.doPost(request, response);
    verify(response).sendError(400);
  }
}
