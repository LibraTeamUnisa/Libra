package it.unisa.libra.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;
import it.unisa.libra.util.JspPagesIndex;

/**
 * Servlet Filter implementation class NegaStudente. Nega l'accesso alla risorsa richiesta dallo
 * studente.
 * 
 * @see javax.servlet.Filter
 */
public class NegaStudente implements Filter {

  /** Default constructor. */
  public NegaStudente() {}

  /** @see Filter#destroy() */
  public void destroy() {}

  /** @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain) */
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    String utenteRuolo =
        (String) ((HttpServletRequest) request).getSession().getAttribute("utenteRuolo");
    // se l'utente è uno studente l'accesso è negato
    /*
    if (StringUtils.isEmpty(utenteRuolo) || utenteRuolo.equals("Studente")) {
      ((HttpServletRequest) request).getServletContext()
          .getRequestDispatcher(JspPagesIndex.ACCESSO_NEGATO).forward(request, response);
    }
    */
    chain.doFilter(request, response);
    
  }

  /** @see Filter#init(FilterConfig) */
  public void init(FilterConfig fConfig) throws ServletException {}
}
