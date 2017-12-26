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
 * Servlet Filter implementation class NegaTutorInterno. Nega l'accesso alla risorsa richiesta dal
 * tutor interno.
 * 
 * @see javax.servlet.Filter
 */
public class NegaTutorInterno implements Filter {

  /** Default constructor. */
  public NegaTutorInterno() {}

  /** @see Filter#destroy() */
  public void destroy() {}

  /** @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain) */
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    String utenteRuolo =
        (String) ((HttpServletRequest) request).getSession().getAttribute("utenteRuolo");
    // se l'utente è un tutor interno l'accesso è negato
    /*
    if (StringUtils.isEmpty(utenteRuolo) || utenteRuolo.equals("TutorInterno")) {
      ((HttpServletRequest) request).getServletContext()
          .getRequestDispatcher(JspPagesIndex.ACCESSO_NEGATO).forward(request, response);
    }
    */
    chain.doFilter(request, response);
  }

  /** @see Filter#init(FilterConfig) */
  public void init(FilterConfig fConfig) throws ServletException {}
}
