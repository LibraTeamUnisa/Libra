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
 * Servlet Filter implementation class NegaSegreteria. Nega l'accesso alla risorsa richiesta dalla
 * Segreteria.
 * 
 * @see javax.servlet.Filter
 */
public class NegaSegreteria implements Filter {

  /** Default constructor. */
  public NegaSegreteria() {}

  /** @see Filter#destroy() */
  public void destroy() {}

  /** @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain) */
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    String utenteRuolo =
        (String) ((HttpServletRequest) request).getSession().getAttribute("utenteRuolo");
    // se l'utente è la segreteria l'accesso è negato
    if (utenteRuolo.equals("Segreteria")) {
      ((HttpServletRequest) request).getServletContext()
          .getRequestDispatcher(JspPagesIndex.ACCESSO_NEGATO).forward(request, response);
    }
    chain.doFilter(request, response);
  }

  /** @see Filter#init(FilterConfig) */
  public void init(FilterConfig fConfig) throws ServletException {}
}
