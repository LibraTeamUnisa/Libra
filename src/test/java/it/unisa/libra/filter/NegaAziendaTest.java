package it.unisa.libra.filter;

import static org.mockito.Mockito.RETURNS_DEEP_STUBS;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import it.unisa.libra.util.JspPagesIndex;

public class NegaAziendaTest {

  NegaAzienda filter = new NegaAzienda();
  HttpServletRequest request;
  HttpServletResponse response;
  FilterChain chain;

  @Before
  public void setUp() {
    request = mock(HttpServletRequest.class, RETURNS_DEEP_STUBS);
    response = mock(HttpServletResponse.class);
    chain = mock(FilterChain.class);
  }

  @After
  public void tearDown() {
    request = null;
    response = null;
    chain = null;
  }

  @Test
  public void erroreNonLoggatoTest() throws Exception {
    when(request.getSession().getAttribute("utenteRuolo")).thenReturn(null);
    when(request.getContextPath()).thenReturn("provaLibra");
    filter.doFilter(request, response, chain);
    verify(response).sendRedirect("provaLibra" + JspPagesIndex.ACCESSO_NEGATO);
  }

  @Test
  public void nonAutorizzatoTest() throws Exception {
    when(request.getSession().getAttribute("utenteRuolo")).thenReturn("Azienda");
    when(request.getContextPath()).thenReturn("provaLibra");
    filter.doFilter(request, response, chain);
    verify(response).sendRedirect("provaLibra" + JspPagesIndex.ACCESSO_NEGATO);
  }

  @Test
  public void paginaConcessaTest() throws Exception {
    when(request.getSession().getAttribute("utenteRuolo")).thenReturn("NotAziendaRuolo");
    filter.doFilter(request, response, chain);
    verify(chain).doFilter(request, response);
  }

}
