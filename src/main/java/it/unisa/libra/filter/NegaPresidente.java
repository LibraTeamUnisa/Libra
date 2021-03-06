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
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class NegaPresidente. Nega l'accesso alla risorsa richiesta dal
 * Presidente.
 * 
 * @see javax.servlet.Filter
 */
public class NegaPresidente implements Filter {

  /** Default constructor. */
  public NegaPresidente() {}

  /**
   * Override.
   * 
   * @see Filter#destroy()
   */
  public void destroy() {}

  /**
   * Override. Se l'utente loggato e' un presidente, reindirizza ad una pagina di errore.
   * 
   * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
   */
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    String utenteRuolo =
        (String) ((HttpServletRequest) request).getSession().getAttribute("utenteRuolo");
    // se l'utente e' il presidente l'accesso e' negato
    if ("Presidente".equals(utenteRuolo)) {
      ((HttpServletResponse) response).sendRedirect(
          ((HttpServletRequest) request).getContextPath() + JspPagesIndex.ACCESSO_NEGATO);
      return;
    }
    chain.doFilter(request, response);
  }

  /**
   * Override.
   * 
   * @see Filter#init(FilterConfig)
   */
  public void init(FilterConfig filterConfig) throws ServletException {}
}
