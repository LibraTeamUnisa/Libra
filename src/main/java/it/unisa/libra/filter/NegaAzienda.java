package it.unisa.libra.filter;

import it.unisa.libra.util.JspPagesIndex;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * Servlet Filter implementation class NegaAzienda. Nega l'accesso alla risorsa richiesta se
 * l'utente � un'azienda.
 * 
 * @see javax.servlet.Filter
 */
public class NegaAzienda implements Filter {

  /** Default constructor. */
  public NegaAzienda() {}

  /** @see Filter#destroy() */
  public void destroy() {}

  /** @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain) */
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    String utenteRuolo =
        (String) ((HttpServletRequest) request).getSession().getAttribute("utenteRuolo");
    // se l'utente � un'azienda l'accesso � negato
    if (utenteRuolo.equals("Azienda")) {
      ((HttpServletRequest) request).getServletContext()
          .getRequestDispatcher(JspPagesIndex.ACCESSO_NEGATO).forward(request, response);
    }
    chain.doFilter(request, response);
  }

  /** @see Filter#init(FilterConfig) */
  public void init(FilterConfig fConfig) throws ServletException {}
}
