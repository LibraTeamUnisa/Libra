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
import org.apache.commons.lang.StringUtils;

/**
 * Servlet Filter implementation class NegaPresidente. Nega l'accesso alla risorsa richiesta dal
 * Presidente.
 * 
 * @see javax.servlet.Filter
 */
public class NegaPresidente implements Filter {

  /** Default constructor. */
  public NegaPresidente() {}

  /** @see Filter#destroy() */
  public void destroy() {}

  /** @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain) */
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    String utenteRuolo =
        (String) ((HttpServletRequest) request).getSession().getAttribute("utenteRuolo");
    // se l'utente è il presidente l'accesso è negato
    /*
    if (StringUtils.isEmpty(utenteRuolo) || utenteRuolo.equals("Presidente")) {
      ((HttpServletRequest) request).getServletContext()
          .getRequestDispatcher(JspPagesIndex.ACCESSO_NEGATO).forward(request, response);
    }
    */
    chain.doFilter(request, response);
  }

  /** @see Filter#init(FilterConfig) */
  public void init(FilterConfig fConfig) throws ServletException {}
}
